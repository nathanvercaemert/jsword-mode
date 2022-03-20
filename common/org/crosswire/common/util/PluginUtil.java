/**
 * Distribution License:
 * JSword is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License, version 2.1 as published by
 * the Free Software Foundation. This program is distributed in the hope
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * The License is available on the internet at:
 *       http://www.gnu.org/copyleft/lgpl.html
 * or by writing to:
 *      Free Software Foundation, Inc.
 *      59 Temple Place - Suite 330
 *      Boston, MA 02111-1307, USA
 *
 * Copyright: 2008
 *     The copyright to this program is held by it's authors.
 *
 * ID: $Id: PluginUtil.java 1505 2007-07-21 19:40:19Z dmsmith $
 */
package org.crosswire.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Properties;

/**
 * A plugin maps one or more implementations to an interface
 * or abstract class via a properties file whose suffix is "plugin".
 * When there is more than one implementation, one is marked as a default.
 *
 * @see gnu.lgpl.License for license details.<br>
 *      The copyright to this program is held by it's authors.
 * @author DM Smith [dmsmith555 at yahoo dot com]
 */
public final class PluginUtil
{
    /**
     * Prevent instantiation
     */
    private PluginUtil()
    {
    }

    /**
     * Get the known implementors of some interface or abstract class.
     * This is currently done by looking up a plugin file by the name of
     * the given class, and assuming that values are implementors of said
     * class. Those that are not are warned, but ignored.
     * @param clazz The class or interface to find implementors of.
     * @return The list of implementing classes.
     */
    public static Class[] getImplementors(Class clazz)
    {
        try
        {
            List matches = new ArrayList();
            Properties props = getPlugin(clazz);
            Iterator it = props.values().iterator();
            while (it.hasNext())
            {
                try
                {
                    String name = (String) it.next();
                    Class impl = ClassUtil.forName(name);
                    if (clazz.isAssignableFrom(impl))
                    {
                        matches.add(impl);
                    }
                    else
                    {
                        log.warn("Class " + impl.getName() + " does not implement " + clazz.getName() + ". Ignoring."); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                    }
                }
                catch (ClassNotFoundException ex)
                {
                    log.warn("Failed to add class to list: " + clazz.getName(), ex); //$NON-NLS-1$
                }
            }

            log.debug("Found " + matches.size() + " implementors of " + clazz.getName()); //$NON-NLS-1$ //$NON-NLS-2$
            return (Class[]) matches.toArray(new Class[matches.size()]);
        }
        catch (IOException ex)
        {
            log.error("Failed to get any classes.", ex); //$NON-NLS-1$
            return new Class[0];
        }
    }

    /**
     * Get a map of known implementors of some interface or abstract class.
     * This is currently done by looking up a plugins file by the name of
     * the given class, and assuming that values are implementors of said
     * class. Those that are not are warned, but ignored. The reply is in the
     * form of a map of keys=strings, and values=classes in case you need to get
     * at the names given to the classes in the plugin file.
     * @see PluginUtil#getImplementors(Class)
     * @param clazz The class or interface to find implementors of.
     * @return The map of implementing classes.
     */
    public static Map getImplementorsMap(Class clazz)
    {
        Map matches = new HashMap();

        try
        {
            Properties props = getPlugin(clazz);
            Iterator it = props.keySet().iterator();
            while (it.hasNext())
            {
                try
                {
                    String key = (String) it.next();
                    String value = props.getProperty(key);
                    Class impl = ClassUtil.forName(value);
                    if (clazz.isAssignableFrom(impl))
                    {
                        matches.put(key, impl);
                    }
                    else
                    {
                        log.warn("Class " + impl.getName() + " does not implement " + clazz.getName() + ". Ignoring."); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                    }
                }
                catch (ClassNotFoundException ex)
                {
                    log.warn("Failed to add class to list: " + clazz.getName(), ex); //$NON-NLS-1$
                }
            }

            log.debug("Found " + matches.size() + " implementors of " + clazz.getName()); //$NON-NLS-1$ //$NON-NLS-2$
        }
        catch (IOException ex)
        {
            log.error("Failed to get any classes.", ex); //$NON-NLS-1$
        }

        return matches;
    }

    /**
     * Get the preferred implementor of some interface or abstract class.
     * This is currently done by looking up a plugins file by the name of
     * the given class, and assuming that the "default" key is an implementation
     * of said class. Warnings are given otherwise.
     * @param clazz The class or interface to find an implementation of.
     * @return The configured implementing class.
     * @throws MalformedURLException if the plugin file can not be found
     * @throws IOException if there is a problem reading the found file
     * @throws ClassNotFoundException if the read contents are not found
     * @throws ClassCastException if the read contents are not valid
     * @see PluginUtil#getImplementors(Class)
     */
    public static Class getImplementor(Class clazz) throws IOException, ClassNotFoundException, ClassCastException
    {
        Properties props = getPlugin(clazz);
        String name = props.getProperty(DEFAULT);

        Class impl = ClassUtil.forName(name);
        if (!clazz.isAssignableFrom(impl))
        {
            throw new ClassCastException(Msg.NOT_ASSIGNABLE.toString(new Object[] { impl.getName(), clazz.getName() }));
        }

        return impl;
    }

    /**
     * Get and instantiate the preferred implementor of some interface or abstract class.
     * @param clazz The class or interface to find an implementation of.
     * @return The configured implementing class.
     * @throws MalformedURLException if the plugin file can not be found
     * @throws IOException if there is a problem reading the found file
     * @throws ClassNotFoundException if the read contents are not found
     * @throws ClassCastException if the read contents are not valid
     * @throws InstantiationException if the new object can not be instantiated
     * @throws IllegalAccessException if the new object can not be instantiated
     * @see PluginUtil#getImplementors(Class)
     */
    public static Object getImplementation(Class clazz) throws MalformedURLException, ClassCastException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        return getImplementor(clazz).newInstance();
    }

    /**
     * Get and load a plugin file by looking it up as a resource.
     * @param clazz The name of the desired resource
     * @return The found and loaded plugin file
     * @throws IOException if the resource can not be loaded
     * @throws MissingResourceException if the resource can not be found
     */
    public static Properties getPlugin(Class clazz) throws IOException
    {
        String subject = ClassUtil.getShortClassName(clazz);

        try
        {
            String lookup = subject + PluginUtil.EXTENSION_PLUGIN;
            InputStream in = ResourceUtil.getResourceAsStream(clazz, lookup);

            Properties prop = new Properties();
            prop.load(in);
            return prop;
        }
        catch (MissingResourceException e)
        {
            return new Properties();
        }
    }

    /**
     * Extension for properties files
     */
    public static final String EXTENSION_PLUGIN = ".plugin"; //$NON-NLS-1$

    /**
     * The string for default implementations
     */
    private static final String DEFAULT = "default"; //$NON-NLS-1$

    /**
     * The log stream
     */
    private static final Logger log = Logger.getLogger(PluginUtil.class);

}
