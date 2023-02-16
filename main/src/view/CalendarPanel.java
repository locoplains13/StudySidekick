package view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.util.Calendar;

import javax.swing.*;

public class CalendarPanel extends ViewPanel {
    static final int FONT_SIZE = 15;

    private static final long   serialVersionUID    = 1L;

    protected int               month;
    protected int               year;

    public ActionListener listener;

    protected String[]          monthNames          = { "January", "February",
            "March", "April", "May", "June", "July", "August", "September",
            "October", "November", "December"       };

    protected String[]          dayNames            = { "S", "M", "T", "W",
            "T", "F", "S"                           };

    public CalendarPanel(int month, int year) {
        this.month = month;
        this.year = year;

        this.add(displayCalendar());
    }

    protected JPanel displayCalendar() {
        JPanel CalendarPanel = new JPanel(true);
        CalendarPanel.setBorder(BorderFactory.createLineBorder(SystemColor.activeCaption));
        CalendarPanel.setLayout(new BorderLayout());
        CalendarPanel.setForeground(Color.BLACK);
        CalendarPanel.add(displayDate(), BorderLayout.NORTH);
        CalendarPanel.add(displayDays(), BorderLayout.SOUTH);

        add(Box.createRigidArea(new Dimension(0, 100 / 20)));

        return CalendarPanel;

    }

    protected JPanel displayDate() {
        JPanel titlePanel = new JPanel(true);
        titlePanel.setBorder(BorderFactory
                .createLineBorder(SystemColor.activeCaption));
        titlePanel.setLayout(new FlowLayout());

        JLabel label = new JLabel(monthNames[month] + " " + year);
        label.setForeground(SystemColor.activeCaption);

        titlePanel.add(label, BorderLayout.CENTER);

        return titlePanel;
    }


    protected JPanel displayDays() {
        JPanel dayPanel = new JPanel(true);
        dayPanel.setLayout(new GridLayout(0, dayNames.length));

        Calendar today = Calendar.getInstance();
        int tMonth = today.get(Calendar.MONTH);
        int tYear = today.get(Calendar.YEAR);
        int tDay = today.get(Calendar.DAY_OF_MONTH);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        Calendar iterator = (Calendar) calendar.clone();
        iterator.add(Calendar.DAY_OF_MONTH,
                -(iterator.get(Calendar.DAY_OF_WEEK) - 1));

        Calendar maximum = (Calendar) calendar.clone();
        maximum.add(Calendar.MONTH, +1);

        for (int i = 0; i < dayNames.length; i++) {
            JPanel dPanel = new JPanel(true);
            dPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            JLabel dLabel = new JLabel(dayNames[i]);
            dPanel.add(dLabel);
            dayPanel.add(dPanel);
        }

        int count = 0;
        int limit = dayNames.length * 6;

        while (iterator.getTimeInMillis() < maximum.getTimeInMillis()) {
            int lMonth = iterator.get(Calendar.MONTH);
            int lYear = iterator.get(Calendar.YEAR);

            JPanel dPanel = new JPanel(true);
            dPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            JLabel dayLabel = new JLabel();

            if ((lMonth == month) && (lYear == year)) {
                int lDay = iterator.get(Calendar.DAY_OF_MONTH);
                dayLabel.setText(Integer.toString(lDay));
                if ((tMonth == month) && (tYear == year) && (tDay == lDay)) {
                    dPanel.setBackground(Color.ORANGE);
                } else {
                    dPanel.setBackground(Color.WHITE);
                }
            } else {
                dayLabel.setText(" ");
                dPanel.setBackground(Color.WHITE);
            }
            dPanel.add(dayLabel);
            dayPanel.add(dPanel);
            iterator.add(Calendar.DAY_OF_YEAR, +1);
            count++;
        }
        
        for (int i = count; i < limit; i++) {
            JPanel dPanel = new JPanel(true);
            dPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            JLabel dayLabel = new JLabel();
            dayLabel.setText(" ");
            dPanel.setBackground(Color.WHITE);
            dPanel.add(dayLabel);
            dayPanel.add(dPanel);
        }

        return dayPanel;
    }
}
