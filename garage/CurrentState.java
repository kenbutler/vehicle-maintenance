/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package garage;

import java.awt.*;
import java.awt.event.*;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.*;

public class CurrentState extends JPanel {

    static JFrame frame = new JFrame("Current Mileage");
    Calendar cal = Calendar.getInstance();
    int selDay, selMonth, selYear;

    // Dropdown lists
    JComboBox<String> monthList;
    String[] dayStrings = { "--" };
    JComboBox<String> dayList = new JComboBox<>(dayStrings);
    JComboBox<Integer> yearList;

    // Text boxes
    JTextField milesBox = new JTextField(10);

    JPanel[] panelHolder;

    JLabel makeLabel(String name) {
        JLabel out = new JLabel(name);
        out.setPreferredSize(new Dimension(90,30));
        out.setHorizontalAlignment(SwingConstants.RIGHT);
        return out;
    }

    public CurrentState() {
        super(new BorderLayout());

        JFrame frame = new JFrame("Current Mileage");

        initialize(6); // rows, account name

        setBorder(BorderFactory.createEmptyBorder(20,50,20,50));

        System.out.println("Month = " + (cal.get(Calendar.MONTH) + 1)
                + ", Day = "+ cal.get(Calendar.DATE)
                + ", Year = " + cal.get(Calendar.YEAR) + " " + this.getParent());
    }

    /** Listens to the combo box. */
    public void actionPerformed(ActionEvent e) {
        JComboBox cb = (JComboBox)e.getSource();
    }

    public void checkCalendar () {
        selYear = yearList.getSelectedIndex() - 1 + cal.get(Calendar.YEAR);
        selMonth = monthList.getSelectedIndex()+1;
        selDay = dayList.getSelectedIndex()+1;
        setDays();
    }

    void setDays() {
        // Update days after month selected
        Calendar tmpCal = Calendar.getInstance();
        tmpCal.set(selYear, selMonth, selDay);
        ArrayList<String> arrayList = new ArrayList<>();
        int maxDays = 0;

        switch (selMonth) {
            case 2:
                if ( ((selYear % 4 == 0) && (selYear % 100 != 0)) || (selYear % 400 == 0) ) {
                    System.out.println("(RegisterEntry) This is a leap year!");
                    maxDays = 29;
                }
                else
                    maxDays = 28;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                maxDays = 30;
                break;
            default:
                maxDays = 31;
                break;
        }
        for (int i=1; i<=maxDays; i++) {

            arrayList.add(Integer.toString(i));
        }
        dayStrings = arrayList.toArray(new String[arrayList.size()]);
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(dayStrings);
        dayList.setModel(model);
        dayList.setSelectedIndex(selDay-1);
    }

    public String getMonth(int month) {
        return new DateFormatSymbols().getMonths()[month-1];
    }

    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }

    boolean validityCheck() {
        boolean valid = true;
        if (milesBox.getText().equals("")) {
            milesBox.setBackground(Color.YELLOW);
            valid = false;
        } else {
            milesBox.setBackground(new JTextField().getBackground());
        }
        if (!isNumeric(milesBox.getText())) {
            milesBox.setBackground(Color.YELLOW);
            valid = false;
        } else {
            milesBox.setBackground(new JTextField().getBackground());
        }
        return valid;
    }

    void initialize(int rows) {

        panelHolder = new JPanel[rows];//[cols];
        setLayout(new GridLayout(rows,1));

        for(int m = 0; m < rows; m++) {
            panelHolder[m] = new JPanel(new FlowLayout(FlowLayout.LEFT));
            add(panelHolder[m]);
        }

        //
        String[] monthStrings = { "January", "February", "March",
                "April", "May", "June",
                "July", "August", "September",
                "October", "November", "December"};
        monthList = new JComboBox<>(monthStrings);
        //
        Integer[] yearInts = { cal.get(Calendar.YEAR) - 1, cal.get(Calendar.YEAR), cal.get(Calendar.YEAR) + 1};
        yearList = new JComboBox<>(yearInts);

        selYear = cal.get(Calendar.YEAR);
        selMonth = cal.get(Calendar.MONTH)+1;
        selDay = cal.get(Calendar.DATE);
        setDays();
        monthList.setSelectedIndex(selMonth-1);
        dayList.setSelectedIndex(selDay-1);
        yearList.setSelectedIndex(1);

        // Add action listeners
        monthList.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        checkCalendar();
                    }
                }
        );
        dayList.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        checkCalendar();
                    }
                }
        );
        yearList.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        checkCalendar();
                    }
                }
        );

        // Date
        panelHolder[0].add(makeLabel("Date : "));
        panelHolder[0].add(monthList);
        panelHolder[0].add(dayList);
        panelHolder[0].add(yearList);
        // Miles
        panelHolder[1].add(makeLabel("Miles : "));
        panelHolder[1].add(milesBox);
    }

    void setData(int day, int month, int year, int type, double amount, String description, boolean isPosted) {
        // Date
        yearList.setSelectedIndex(year + 1 - cal.get(Calendar.YEAR));
        monthList.setSelectedIndex(month-1);
        dayList.setSelectedIndex(day-1);
        checkCalendar();
        // Miles
        milesBox.setText(Double.toString(amount));
    }
}
