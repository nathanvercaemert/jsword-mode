/**
 * Distribution License:
 * BibleDesktop is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License, version 2 as published by
 * the Free Software Foundation. This program is distributed in the hope
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 * The License is available on the internet at:
 *       http://www.gnu.org/copyleft/gpl.html
 * or by writing to:
 *      Free Software Foundation, Inc.
 *      59 Temple Place - Suite 330
 *      Boston, MA 02111-1307, USA
 *
 * Copyright: 2005
 *     The copyright to this program is held by it's authors.
 *
 * ID: $Id: Msg.java 758 2005-06-22 03:50:55Z dmsmith $
 */
package org.crosswire.bibledesktop.passage;

import org.crosswire.common.util.MsgBase;

/**
 * Compile safe Msg resource settings.
 * 
 * @see gnu.gpl.License for license details.
 *      The copyright to this program is held by it's authors.
 * @author Joe Walker [joe at eireneh dot com]
 */
final class Msg extends MsgBase
{
    static final Msg WHOLE_BIBLE = new Msg("BibleTreeNode.WholeBible"); //$NON-NLS-1$
    static final Msg PART_BIBLE = new Msg("BibleTreeNode.PartBible"); //$NON-NLS-1$
    static final Msg ERROR = new Msg("WholeBibleTreeNode.Error"); //$NON-NLS-1$
    static final Msg WHOLE = new Msg("WholeBibleTreeNode.Whole"); //$NON-NLS-1$

    /**
     * Passthrough ctor
     */
    private Msg(String name)
    {
        super(name);
    }
}
