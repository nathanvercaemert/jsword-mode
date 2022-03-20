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
 * Copyright: 2005
 *     The copyright to this program is held by it's authors.
 *
 * ID: $Id: LayoutType.java 1776 2008-03-08 22:17:31Z dmsmith $
 */
package org.crosswire.common.swing.desktop;

import java.io.Serializable;


/**
 * Types of ViewLayouts. Currently there are two types of desktop layouts:
 * <ul>
 * <li>TDI - tabbed document interface.</li>
 * <li>MDI - multiple document interface (sub-windows)</li>
 * </ul>
 *
 * @see gnu.lgpl.License for license details.
 *      The copyright to this program is held by it's authors.
 * @author Joe Walker [joe at eireneh dot com]
 * @author DM Smith [dmsmith555 at yahoo dot com]
 */
public abstract class LayoutType implements Serializable
{
    /**
     * Tabbed View
     */
    public static final LayoutType TDI = new LayoutType("TDI") //$NON-NLS-1$
    {
        /* (non-Javadoc)
         * @see org.crosswire.common.swing.desktop.LayoutType#createLayout()
         */
        public AbstractViewLayout createLayout()
        {
            return new TDIViewLayout();
        }

        /* (non-Javadoc)
         * @see org.crosswire.common.swing.desktop.LayoutType#toString()
         */
        public String toString()
        {
            return UserMsg.TDI.toString();
        }

        /**
         * Serialization ID
         */
        private static final long serialVersionUID = 3257572784669800241L;
    };

    /**
     * Multiple Document View
     */
    public static final LayoutType MDI = new LayoutType("MDI") //$NON-NLS-1$
    {
        /* (non-Javadoc)
         * @see org.crosswire.common.swing.desktop.LayoutType#createLayout()
         */
        public AbstractViewLayout createLayout()
        {
            return new MDIViewLayout();
        }

        /* (non-Javadoc)
         * @see org.crosswire.common.swing.desktop.LayoutType#toString()
         */
        public String toString()
        {
            return UserMsg.MDI.toString();
        }

        /**
         * Serialization ID
         */
        private static final long serialVersionUID = 3904681587101874488L;
    };

    /**
     * Simple ctor
     */
    protected LayoutType(String name)
    {
        this.name = name;
    }

    /**
     * Return the layout
     *
     * @return the layout
     */
    public AbstractViewLayout getLayout()
    {
        // In order to get the proper LAF it needs to be created after the LAF is set
        // So we delay it until it is actually needed.
        if (layout == null)
        {
            layout = createLayout();
        }
        return layout;
    }

    /**
     * Create the appropriate kind of view layout
     * @return the created view layout
     */
    public abstract AbstractViewLayout createLayout();

    /**
     * Get an integer representation for this LayoutType
     */
    public int toInteger()
    {
        for (int i = 0; i < VALUES.length; i++)
        {
            if (equals(VALUES[i]))
            {
                return i;
            }
        }
        // cannot get here
        assert false;
        return -1;
    }

    /**
     * Lookup method to convert from a String
     */
    public static LayoutType fromString(String name)
    {
        for (int i = 0; i < VALUES.length; i++)
        {
            LayoutType obj = VALUES[i];
            if (obj.name.equalsIgnoreCase(name))
            {
                return obj;
            }
        }
        // cannot get here
        assert false;
        return null;
    }

    /**
     * Lookup method to convert from an integer
     */
    public static LayoutType fromInteger(int i)
    {
        return VALUES[i];
    }

    /**
     * Prevent subclasses from overriding canonical identity based Object methods
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public final boolean equals(Object o)
    {
        return super.equals(o);
    }

    /**
     * Prevent subclasses from overriding canonical identity based Object methods
     * @see java.lang.Object#hashCode()
     */
    public final int hashCode()
    {
        return super.hashCode();
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        return name;
    }

    /**
     * The name of the LayoutType
     */
    private String name;

    /**
     * The actual layout
     */
    protected AbstractViewLayout layout;

    // Support for serialization
    private static int nextObj;
    private final int obj = nextObj++;

    Object readResolve()
    {
        return VALUES[obj];
    }

    private static final LayoutType[] VALUES =
    {
        TDI,
        MDI,
    };
}
