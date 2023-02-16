package Objects;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

public class StopWatch extends JLabel implements ActionListener {

    /**Boolean to keep track of current state of timer */
    private boolean isRunning;
    
    /** Timer to be displayed*/
    private Timer timer = new javax.swing.Timer(100, this);
    
    /** Time when the stopWatch was initialized */
    private long initTime = System.currentTimeMillis();
    
    /**Time when stopWatch is started */
    private long startTime;

    /**Time when stopWatch is paused */
    private long pauseTime;

    /**
     * Constructor for stopWatch
     */
    public StopWatch() {
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setText(getCurrentTime(System.currentTimeMillis() - initTime));
    }

    /**
     * Reaction to actionListener - updates time display
     * @param event the action event recorded by the ActionListener
     */
    public void actionPerformed(ActionEvent event) {
        setText(getCurrentTime(System.currentTimeMillis() - startTime));
    }

    /**
     * Starts the timer and properly adjusts the startTime
      */    
    public void start() {
        if (!isRunning) {
            startTime = System.currentTimeMillis();
        } else {
            startTime = System.currentTimeMillis() - (pauseTime - startTime);
        }
        isRunning = true;
        timer.start();
    }


    /**
     * Records current time and then stops the timer
     */
    public void pause() {
        pauseTime = System.currentTimeMillis();
        timer.stop();
    }

    /**
     * Records current time and then stops the timer
     */
    public long stop() {

        timer.stop();
        return System.currentTimeMillis()- initTime;
    }

    /**
     * Returns current time in the correct format
     * @param time the current time
     * @return
     */
    private String getCurrentTime(long time) {
        return myFormat(time);
    }

    /**
     * Formats the current time so that it can be seen as hr:min:sec.ms
     * @param time current time in milliseconds
     * @return the current time as a formatted sting
     */
    private String myFormat(final long time) {
        final long hr = TimeUnit.MILLISECONDS.toHours(time);
        final long min = TimeUnit.MILLISECONDS.toMinutes(time
                - TimeUnit.HOURS.toMillis(hr));
        final long sec = TimeUnit.MILLISECONDS.toSeconds(time
                - TimeUnit.HOURS.toMillis(hr) - TimeUnit.MINUTES.toMillis(min));
        final long ms = TimeUnit.MILLISECONDS.toMillis(time
                - TimeUnit.HOURS.toMillis(hr) - TimeUnit.MINUTES.toMillis(min)
                - TimeUnit.SECONDS.toMillis(sec));
        return String.format("%02d:%02d:%02d.%01d", hr, min, sec, ms/100);
    }



}