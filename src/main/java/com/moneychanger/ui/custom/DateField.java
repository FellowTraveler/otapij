/***********************************************************

 DateField.java
 Copyright (C) 2003 Brenda Bell

 ***********************************************************/

/***********************************************************

 This file is part of JavaDatePicker.

 JavaDatePicker is free software; you can redistribute it
 and/or modify it under the terms of the GNU Lesser
 General Public License as published by the Free Software
 Foundation; either version 2 of the License, or (at your
 option) any later version.

 JavaDatePicker is distributed in the hope that it will
 be useful, but WITHOUT ANY WARRANTY; without even the
 implied warranty of MERCHANTABILITY or FITNESS FOR A
 PARTICULAR PURPOSE.  See the GNU Lesser General Public
 License for more details.

 You should have received a copy of the GNU Lesser
 General Public License along with JavaDatePicker; if
 not, write to the Free Software Foundation, Inc., 59
 Temple Place, Suite 330, Boston, MA  02111-1307  USA

 ***********************************************************/

package com.moneychanger.ui.custom;

import java.awt.Color;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import javax.swing.*;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

/**
 * GUI component that lets the user enter a date using a drop-down DatePicker.
 * Usage is illustrated in the sample code below:
 * <PRE>
 *  JDialog dlg = new JDialog(new Frame(), true);
 *  DateField df = new DateField();
 *  dlg.getContentPane().add(df);
 *  dlg.pack();
 *  dlg.show();
 *  if (null != df.getDate())
 *    System.out.println(df.getDate().toString());
 *  dlg.dispose();
 *  System.exit(0);
 * </PRE>
 * @author B. Bell
 * @version 1.1a
 *
 */
public final class DateField extends JPanel {

    /**
     * Displays the currently selected date.
     */
    private final JTextField dateText = new JTextField();

    /**
     * When clicked, displays the DatePicker immediately under the text field.
     */
    private final JButton dropdownButton = new JButton();

    /**
     * The current DatePicker instance.
     */
    private DatePicker dp;

    /**
     * The DatePicker's container.
     */
    private JDialog dlg;

    /**
     * Listener that will catch the selected date when the DatePicker is hidden.
     */
    final class Listener extends ComponentAdapter {

        /**
         * Event handler that catches the selected date when the DatePicker is
         * hidden.
         * @param evt
         */
        public void componentHidden(final ComponentEvent evt) {
            final Date dt = ((DatePicker) evt.getSource()).getDate();
            if (null != dt)
                dateText.setText(dateToString(dt));
            dlg.dispose();
        }

    }

    /**
     * Default constructor; the initially selected date is "empty".
     */
    public DateField() {
        super();
        init();
    }

    /**
     * Alternate constructor that initializes the currently selected date
     * to the specified date.
     * @param initialDate
     */
    public DateField(final Date initialDate) {
        super();
        init();
        dateText.setText(dateToString(initialDate));
    }

    /**
     * Returns the currently selected date or null if not set.
     * @return date
     */
    public Date getDate() {
        return stringToDate(dateText.getText());
    }

    /**
     * Initializes the panel components.
     */
    private void init() {
        setLayout(new AbsoluteLayout());

        dateText.setText("");
        dateText.setEditable(false);
        dateText.setBackground(new Color(255, 255, 255));
        add(dateText, new AbsoluteConstraints(10, 10, 141, 20));

        dropdownButton.setText("...");
        dropdownButton.setMargin(new Insets(2, 2, 2, 2));
        dropdownButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                onButtonClick(evt);
            }
        });
        add(dropdownButton, new AbsoluteConstraints(151, 10, 20, 21));
    }

    /**
     * Event handler that displays the DatePicker when the button is
     * clicked.
     * @param evt
     */
    private void onButtonClick(final java.awt.event.ActionEvent evt) {
        if ("".equals(dateText.getText()))
            dp = new DatePicker();
        else
            dp = new DatePicker(stringToDate(dateText.getText()));
        dp.setHideOnSelect(true);
        dp.addComponentListener(new Listener());

        final Point p = dateText.getLocationOnScreen();
        p.setLocation(p.getX(), p.getY() - 1 + dateText.getSize().getHeight());

        dlg = new JDialog(new JFrame(), true);
        dlg.setLocation(p);
        dlg.setResizable(false);
        dlg.setUndecorated(true);
        dlg.getContentPane().add(dp);
        dlg.pack();
        dlg.show();
    }

    /**
     * Returns a short string representation for the specified date (January 1, 2003).
     * @param dt
     * @return short string
     */
    private static String dateToString(final Date dt) {
        if (null != dt)
            return DateFormat.getDateInstance(DateFormat.LONG).format(dt);
        return null;
    }

    /**
     * Constructs a Date object from a short date (January 1, 2003).
     * @param s
     * @return date
     */
    private static Date stringToDate(final String s) {
        try {
            return DateFormat.getDateInstance(DateFormat.LONG).parse(s);
        } catch (ParseException e) {
            return null;
        }
    }

}