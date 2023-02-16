package view;

import Objects.BlinkingLabel;
import Objects.Settings;
import Objects.StopWatch;
import Objects.Task;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

//
/**
 * CURRENTLY CLOSING TIMER CLOSES PROGRAM
 */

public class TimerPanel extends ViewPanel {
    protected StopWatch timeText;

    /**
     * Contructor for timerPanel
     *
     * @param width    width of panel
     * @param height   height of panel
     * @param listener listener for events
     */
    public TimerPanel(int width, int height, ActionListener listener, Task task, View view) {
        setSize(width, height);
        // this is necessary so that the timer can run independantly from
        // everything else
        setBackground(new Color(179, 217, 255));

        // if application is closed when timer is still running, save the time
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                task.setTimeSpentThusFar(timeText.stop());
            }
        }));

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                create(task, view);
            }
        });

    }

    /**
     * Sets up the timer and sets up the proper reaction to
     * pressing start/pause button. WHen stop is pressed the window is disposed of
     * and the time is added to the time spent on that task
     *
     * @param task the task where the time will be stored
     * @param view the window where the TimerPanel will be displayed
     */
    public void create(Task task, View view) {

        // the time to be displayed
        timeText = new StopWatch();
        timeText.setFont(new Font("Dialog", Font.BOLD, 32));
        add(timeText, BorderLayout.CENTER);
        timeText.start();
        setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        // Start/pause button
        final JButton jButtonStartPause = new JButton("Pause");
        jButtonStartPause.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(jButtonStartPause);
        jButtonStartPause.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String cmd = e.getActionCommand();
                if ("Pause".equals(cmd)) {
                    timeText.pause();
                    jButtonStartPause.setText("Start");
                } else if ("Start".equals(cmd)) {
                    timeText.start();
                    jButtonStartPause.setText("Pause");
                }
            }
        });

        // Stop button
        JButton jButtonStop = new JButton("Stop");
        add(jButtonStop);
        jButtonStop.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jButtonStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                task.setTimeSpentThusFar(timeText.stop());
                view.dispose();
            }
        });

        // if window is closed then time is saved
        view.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                task.setTimeSpentThusFar(timeText.stop());
            }
        });

        JLabel jLabelEstimatedTimeExceeded = new BlinkingLabel(null);
        add(jLabelEstimatedTimeExceeded);
        jLabelEstimatedTimeExceeded.setVerticalAlignment(SwingConstants.BOTTOM);
        jLabelEstimatedTimeExceeded.setFont(new Font("Arial", Font.BOLD, 20));
        Timer notificationTimer = new Timer();
        if (convertTime(task.getEstimatedTimeToComplete()) - task.getTimeSpentThusFar() > 0) {
            notificationTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    view.setSize(new Dimension(450, 125));
                    setSize(new Dimension(450, 125));
                    jLabelEstimatedTimeExceeded.setText("YOU HAVE EXCEEDED ESTIMATED TIME");
                }
            }, convertTime(task.getEstimatedTimeToComplete()) - task.getTimeSpentThusFar());
        }

        if (Settings.getInstance().getSettings().get(0).compareTo("Yes")==0) {
            Timer breakNotification = new Timer();
            breakNotification.schedule(new TimerTask() {
                @Override
                public void run() {
                    JOptionPane.showMessageDialog(null, "YOU'VE BEEN WORKING FOR "+
                            Settings.getInstance().getSettings().get(1)+ " MINUTES\nTAKE A BREAK", "BREAK",
                            JOptionPane.WARNING_MESSAGE);
                }
            }, TimeUnit.MINUTES.toMillis(Long.parseLong(Settings.getInstance().getSettings().get(1))));
        }
    }
    /**
     * Convert estimated time to milliseconds to compare to time spent thus far
     * @param estimatedTimeToComplete the estimated time to complete task
     * @return time converted to milliseconds
     */
    public long convertTime(Integer[] estimatedTimeToComplete){
        long days = TimeUnit.DAYS.toMillis(estimatedTimeToComplete[0]);
        long hours = TimeUnit.HOURS.toMillis(estimatedTimeToComplete[1]);
        long minutes = TimeUnit.MINUTES.toMillis(estimatedTimeToComplete[2]);

        return days+hours+minutes;
    }

}



