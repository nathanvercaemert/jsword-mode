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
 * Copyright: 2007
 *     The copyright to this program is held by it's authors.
 *
 * ID: $Id: BookSelectEvent.java 1420 2007-06-21 12:01:00Z dmsmith $
 */
package org.crosswire.bibledesktop.book;

import java.util.EventObject;

import org.crosswire.jsword.book.BookProvider;

/**
 * A BookSelectEvent happens whenever a user selects a book.
 * 
 * @see gnu.gpl.License for license details.
 *      The copyright to this program is held by it's authors.
 * @author DM Smith [dmsmith555 at yahoo dot com]
 */
public class BookSelectEvent extends EventObject
{
    /**
     * Create a BookSelectionEvent, declaring that the BookProvider has a new selection.
     * 
     * @param source The thing that started this off
     */
    public BookSelectEvent(BookProvider source)
    {
        super(source);
    }

    /**
     * Get all the books.
     * @return the books
     */
    public BookProvider getBookProvider()
    {
        return (BookProvider) getSource();
    }

    /**
     * Serialization ID
     */
    private static final long serialVersionUID = 6018474028417993389L;
}
