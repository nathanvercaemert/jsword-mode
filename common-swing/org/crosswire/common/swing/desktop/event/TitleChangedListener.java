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
 * ID: $Id: TitleChangedListener.java 1464 2007-07-02 02:34:40Z dmsmith $
 */
package org.crosswire.common.swing.desktop.event;

import java.util.EventListener;

/**
 * A listener for changes to a Views title.
 *
 * @see gnu.lgpl.License for license details.
 *      The copyright to this program is held by it's authors.
 * @author Joe Walker [joe at eireneh dot com]
 */
public interface TitleChangedListener extends EventListener
{
    /**
     * A Select pane is telling us that it has changed, and we might want to
     * update the BibleViewPane and the AbstractViewLayout to reflect any potentially
     * new titles
     */
    void titleChanged(TitleChangedEvent ev);
}
