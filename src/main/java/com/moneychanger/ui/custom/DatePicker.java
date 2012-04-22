/***********************************************************

 DatePicker.java
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

package com.wrapper.ui.custom;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.plaf.BorderUIResource;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

/**
 * GUI component that allows the user to choose a date from a calendar.
 * Usage is illustrated in the sample code below:
 * <PRE>
 *  JDialog dlg = new JDialog(new Frame(), true);
 *  DatePicker dp = new DatePicker();
 *  dp.setHideOnSelect(false);
 *  dlg.getContentPane().add(dp);
 *  dlg.pack();
 *  dlg.show();
 *  System.out.println(dp.getDate().toString());
 *  dlg.dispose();
 *  System.exit(0);
 * </PRE>
 * @author B. Bell
 * @version 1.1a
 */
public final class DatePicker extends JPanel {

    /**
     * X coordinate for upper left corner of week 1 day 1.
     */
    private static final int startX = 10;

    /**
     * Y coordinate for upper left corner of week 1 day 1.
     */
    private static final int startY = 60;

    /**
     * Small font.
     */
    private static final Font smallFont = new Font("Dialog", Font.PLAIN, 10);

    /**
     * Large font.
     */
    private static final Font largeFont = new Font("Dialog", Font.PLAIN, 12);

    /**
     * Insets for day components and small buttons.
     */
    private static final Insets insets = new Insets(2, 2, 2, 2);

    /**
     * Highlighted color.
     */
    private static final Color highlight = new Color(255, 255, 204);

    /**
     * Enabled color.
     */
    private static final Color white = new Color(255, 255, 255);

    /**
     * Disabled color.
     */
    private static final Color gray = new Color(204, 204, 204);

    /**
     * Most recently selected day component.
     */
    private Component selectedDay = null;

    /**
     * Currently selected date.
     */
    private GregorianCalendar selectedDate = null;

    /**
     * Tracks the original date set when the component was created.
     */
    private GregorianCalendar originalDate = null;

    /**
     * When true, the panel will be hidden as soon as a day is
     * selected by clicking the day or clicking the Today button.
     */
    private boolean hideOnSelect = true;

    /**
     * When clicked, displays the previous month.
     */
    private final JButton backButton = new JButton();

    /**
     * Displays the currently selected month and year.
     */
    private final JLabel monthAndYear = new JLabel();

    /**
     * When clicked, displays the next month.
     */
    private final JButton forwardButton = new JButton();

    /**
     * Column headings for the days of the week.
     */
    private final JTextField[] dayHeadings = new JTextField[]{
        new JTextField("S"),
        new JTextField("M"),
        new JTextField("T"),
        new JTextField("W"),
        new JTextField("T"),
        new JTextField("F"),
        new JTextField("S")};

    /**
     * 2-dimensional array for 6 weeks of 7 days each.
     */
    private final JTextField[][] daysInMonth = new JTextField[][]{
        {new JTextField(),
         new JTextField(),
         new JTextField(),
         new JTextField(),
         new JTextField(),
         new JTextField(),
         new JTextField()},
        {new JTextField(),
         new JTextField(),
         new JTextField(),
         new JTextField(),
         new JTextField(),
         new JTextField(),
         new JTextField()},
        {new JTextField(),
         new JTextField(),
         new JTextField(),
         new JTextField(),
         new JTextField(),
         new JTextField(),
         new JTextField()},
        {new JTextField(),
         new JTextField(),
         new JTextField(),
         new JTextField(),
         new JTextField(),
         new JTextField(),
         new JTextField()},
        {new JTextField(),
         new JTextField(),
         new JTextField(),
         new JTextField(),
         new JTextField(),
         new JTextField(),
         new JTextField()},
        {new JTextField(),
         new JTextField(),
         new JTextField(),
         new JTextField(),
         new JTextField(),
         new JTextField(),
         new JTextField()}
    };

    /**
     * When clicked, sets the selected day to the current date.
     */
    private final JButton todayButton = new JButton();

    /**
     * When clicked, hides the calendar and sets the selected date to "empty".
     */
    private final JButton cancelButton = new JButton();

    /**
     * Default constructor that sets the currently selected date to the
     * current date.
     */
    public DatePicker() {
        super();
        selectedDate = getToday();
        init();
    }

    /**
     * Alternate constructor that sets the currently selected date to the
     * specified date if non-null.
     * @param initialDate
     */
    public DatePicker(final Date initialDate) {
        super();
        if (null == initialDate)
            selectedDate = getToday();
        else
            (selectedDate = new GregorianCalendar()).setTime(initialDate);
        originalDate = new GregorianCalendar(
                selectedDate.get(Calendar.YEAR),
                selectedDate.get(Calendar.MONTH),
                selectedDate.get(Calendar.DATE));
        init();
    }

    /**
     * Returns true if the panel will be made invisible after a day is
     * selected.
     * @return true or false
     */
    public boolean isHideOnSelect() {
        return hideOnSelect;
    }

    /**
     * Controls whether the panel will be made invisible after a day is
     * selected.
     * @param hideOnSelect
     */
    public void setHideOnSelect(final boolean hideOnSelect) {
        if (this.hideOnSelect != hideOnSelect) {
            this.hideOnSelect = hideOnSelect;
            initButtons(false);
        }
    }

    /**
     * Returns the currently selected date.
     * @return date
     */
    public Date getDate() {
        if (null != selectedDate)
            return selectedDate.getTime();
        return null;
    }

    /**
     * Initializes the panel components according to the current value
     * of selectedDate.
     */
    private void init() {
        setLayout(new AbsoluteLayout());
        this.setMinimumSize(new Dimension(161, 226));
        this.setMaximumSize(getMinimumSize());
        this.setPreferredSize(getMinimumSize());
        this.setBorder(new BorderUIResource.EtchedBorderUIResource());

        backButton.setFont(smallFont);
        backButton.setText("<");
        backButton.setMargin(insets);
        backButton.setDefaultCapable(false);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                onBackClicked(evt);
            }
        });
        add(backButton, new AbsoluteConstraints(10, 10, 20, 20));

        monthAndYear.setFont(largeFont);
        monthAndYear.setHorizontalAlignment(JTextField.CENTER);
        monthAndYear.setText(formatDateText(selectedDate.getTime()));
        add(monthAndYear, new AbsoluteConstraints(30, 10, 100, 20));

        forwardButton.setFont(smallFont);
        forwardButton.setText(">");
        forwardButton.setMargin(insets);
        forwardButton.setDefaultCapable(false);
        forwardButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                onForwardClicked(evt);
            }
        });
        add(forwardButton, new AbsoluteConstraints(130, 10, 20, 20));

        // layout the column headings for the days of the week
        int x = startX;
        for (int ii = 0; ii < dayHeadings.length; ii++) {
            dayHeadings[ii].setBackground(gray);
            dayHeadings[ii].setEditable(false);
            dayHeadings[ii].setFont(smallFont);
            dayHeadings[ii].setHorizontalAlignment(JTextField.CENTER);
            dayHeadings[ii].setFocusable(false);
            add(dayHeadings[ii], new AbsoluteConstraints(x, 40, 21, 21));
            x += 20;
        }

        // layout the days of the month
        x = startX;
        int y = startY;
        for (int ii = 0; ii < daysInMonth.length; ii++) {
            for (int jj = 0; jj < daysInMonth[ii].length; jj++) {
                daysInMonth[ii][jj].setBackground(gray);
                daysInMonth[ii][jj].setEditable(false);
                daysInMonth[ii][jj].setFont(smallFont);
                daysInMonth[ii][jj].setHorizontalAlignment(JTextField.RIGHT);
                daysInMonth[ii][jj].setText("");
                daysInMonth[ii][jj].setFocusable(false);
                daysInMonth[ii][jj].addMouseListener(new MouseAdapter() {
                    public void mouseClicked(final MouseEvent evt) {
                        onDayClicked(evt);
                    }
                });
                add(daysInMonth[ii][jj], new AbsoluteConstraints(x, y, 21, 21));
                x += 20;
            }
            x = startX;
            y += 20;
        }

        initButtons(true);

        calculateCalendar();
    }

    /**
     * Initializes Today and Cancel buttons dependent on whether hideOnSelect
     * is set; if the panel will stay open, the Cancel button is invisible.
     * @param firstTime
     */
    private void initButtons(final boolean firstTime) {
        if (firstTime) {
            final Dimension buttonSize = new Dimension(68, 24);
            todayButton.setFont(largeFont);
            todayButton.setText("Today");
            todayButton.setMargin(insets);
            todayButton.setMaximumSize(buttonSize);
            todayButton.setMinimumSize(buttonSize);
            todayButton.setPreferredSize(buttonSize);
            todayButton.setDefaultCapable(true);
            todayButton.setSelected(true);
            todayButton.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent evt) {
                    onToday(evt);
                }
            });

            cancelButton.setFont(largeFont);
            cancelButton.setText("Cancel");
            cancelButton.setMargin(insets);
            cancelButton.setMaximumSize(buttonSize);
            cancelButton.setMinimumSize(buttonSize);
            cancelButton.setPreferredSize(buttonSize);
            cancelButton.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent evt) {
                    onCancel(evt);
                }
            });
        } else {
            this.remove(todayButton);
            this.remove(cancelButton);
        }

        if (hideOnSelect) {
            add(todayButton, new AbsoluteConstraints(25, 190, 52, -1));
            add(cancelButton, new AbsoluteConstraints(87, 190, 52, -1));
        } else {
            add(todayButton, new AbsoluteConstraints(55, 190, 52, -1));
        }
    }

    /**
     * Event handler for the Today button that sets the currently selected
     * date to the current date.
     * @param evt
     */
    private void onToday(final java.awt.event.ActionEvent evt) {
        selectedDate = getToday();
        setVisible(!hideOnSelect);
        if (isVisible()) { // don't bother with calculation if not visible
            monthAndYear.setText(formatDateText(selectedDate.getTime()));
            calculateCalendar();
        }
    }

    /**
     * Event handler for the Cancel button that unsets the currently selected date.
     * @param evt
     */
    private void onCancel(final ActionEvent evt) {
        selectedDate = originalDate;
        setVisible(!hideOnSelect);
    }

    /**
     * Event handler for the forward button that increments the currently
     * selected month.
     * @param evt
     */
    private void onForwardClicked(final java.awt.event.ActionEvent evt) {
        final int day = selectedDate.get(Calendar.DATE);
        selectedDate.set(Calendar.DATE, 1);
        selectedDate.add(Calendar.MONTH, 1);
        selectedDate.set(Calendar.DATE,
                Math.min(day, calculateDaysInMonth(selectedDate)));
        monthAndYear.setText(formatDateText(selectedDate.getTime()));
        calculateCalendar();
    }

    /**
     * Event handler for the back button that decrements the currently selected
     * month.
     * @param evt
     */
    private void onBackClicked(final java.awt.event.ActionEvent evt) {
        final int day = selectedDate.get(Calendar.DATE);
        selectedDate.set(Calendar.DATE, 1);
        selectedDate.add(Calendar.MONTH, -1);
        selectedDate.set(Calendar.DATE,
                Math.min(day, calculateDaysInMonth(selectedDate)));
        monthAndYear.setText(formatDateText(selectedDate.getTime()));
        calculateCalendar();
    }

    /**
     * Event handler that sets the currently selected date to the clicked day.
     * @param evt
     */
    private void onDayClicked(final java.awt.event.MouseEvent evt) {
        final javax.swing.JTextField fld = (javax.swing.JTextField) evt.getSource();
        if (!"".equals(fld.getText())) {
            if (null != selectedDay) {
                selectedDay.setBackground(white);
            }
            fld.setBackground(highlight);
            selectedDay = fld;
            selectedDate.set(
                    Calendar.DATE,
                    Integer.parseInt(fld.getText()));
            setVisible(!hideOnSelect);
        }
    }

    /**
     * Returns the current date.
     * @return date
     */
    private static GregorianCalendar getToday() {
        final GregorianCalendar gc = new GregorianCalendar();
        gc.set(Calendar.HOUR_OF_DAY, 0);
        gc.set(Calendar.MINUTE, 0);
        gc.set(Calendar.SECOND, 0);
        gc.set(Calendar.MILLISECOND, 0);
        return gc;
    }

    /**
     * Calculates the days of the month.
     */
    private void calculateCalendar() {
        // clear the selected date
        if (null != selectedDay) {
            selectedDay.setBackground(white);
            selectedDay = null;
        }

        // get the first day of the selected year and month
        final GregorianCalendar c = new GregorianCalendar(
                selectedDate.get(Calendar.YEAR),
                selectedDate.get(Calendar.MONTH),
                1);

        // figure out the maximum number of days in the month
        final int maxDay = calculateDaysInMonth(c);

        // figure out the day that should be selected in this month
        // based on the previously selected day and the maximum number
        // of days in the month
        final int selectedDay = Math.min(maxDay, selectedDate.get(
                Calendar.DATE));

        // clear the days up to the first day of the month
        int dow = c.get(Calendar.DAY_OF_WEEK);
        for (int dd = 0; dd < dow; dd++) {
            daysInMonth[0][dd].setText("");
            daysInMonth[0][dd].setBackground(gray);
        }

        // construct the days in the selected month
        int week;
        do {
            week = c.get(Calendar.WEEK_OF_MONTH);
            dow = c.get(Calendar.DAY_OF_WEEK);
            final JTextField fld = this.daysInMonth[week - 1][dow - 1];
            fld.setText(Integer.toString(c.get(Calendar.DATE)));
            if (selectedDay == c.get(Calendar.DATE)) {
                fld.setBackground(highlight);
                this.selectedDay = fld;
            } else
                fld.setBackground(white);
            if (c.get(Calendar.DATE) >= maxDay)
                break;
            c.add(Calendar.DATE, 1);
        } while (c.get(Calendar.DATE) <= maxDay);

        // clear all the days after the last day of the month
        week--;
        for (int ww = week; ww < daysInMonth.length; ww++) {
            for (int dd = dow; dd < daysInMonth[ww].length; dd++) {
                daysInMonth[ww][dd].setText("");
                daysInMonth[ww][dd].setBackground(gray);
            }
            dow = 0;
        }

        // set the currently selected date
        c.set(Calendar.DATE, selectedDay);
        selectedDate = c;
    }

    /**
     * Calculates the number of days in the specified month.
     * @param c
     * @return number of days in the month
     */
    private static int calculateDaysInMonth(final Calendar c) {
        int daysInMonth = 0;
        switch (c.get(Calendar.MONTH)) {
            case 0:
            case 2:
            case 4:
            case 6:
            case 7:
            case 9:
            case 11:
                daysInMonth = 31;
                break;
            case 3:
            case 5:
            case 8:
            case 10:
                daysInMonth = 30;
                break;
            case 1:
                final int year = c.get(Calendar.YEAR);
                daysInMonth =
                        (0 == year % 1000) ? 29 :
                        (0 == year % 100) ? 28 :
                        (0 == year % 4) ? 29 : 28;
                break;
        }
        return daysInMonth;
    }

    /**
     * Returns a short string representation of the specified date (January, 2001).
     * @param dt
     * @return short string
     */
    private static String formatDateText(final Date dt) {
        final DateFormat df = DateFormat.getDateInstance(DateFormat.LONG);

        final StringBuffer mm = new StringBuffer();
        final StringBuffer yy = new StringBuffer();
        final FieldPosition mmfp = new FieldPosition(DateFormat.MONTH_FIELD);
        final FieldPosition yyfp = new FieldPosition(DateFormat.YEAR_FIELD);
        df.format(dt, mm, mmfp);
        df.format(dt, yy, yyfp);
        return (mm.toString().substring(mmfp.getBeginIndex(), mmfp.getEndIndex()) +
                " " + yy.toString().substring(yyfp.getBeginIndex(), yyfp.getEndIndex()));
    }

}