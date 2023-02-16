package Objects;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BlinkingLabel extends JLabel {
    private static final long serialVersionUID = 1L;

    private static final int BLINKING_RATE = 1000; // in ms

    private boolean blinkingOn = true;

    public BlinkingLabel(String text) {
        super(text);
        Timer timer = new Timer( BLINKING_RATE , new TimerListener(this));
        timer.setInitialDelay(0);
        timer.start();
    }



    private class TimerListener implements ActionListener {
        private BlinkingLabel blinkingLabel;
        private Color backgroundColour;
        private Color foregroundColour;
        private boolean isForeground = true;

        public TimerListener(BlinkingLabel blinkingLabel) {
            this.blinkingLabel = blinkingLabel;
            foregroundColour = blinkingLabel.getForeground();
            backgroundColour = blinkingLabel.getBackground();
        }

        public void actionPerformed(ActionEvent e) {
            if (blinkingLabel.blinkingOn) {
                if (isForeground) {
                    blinkingLabel.setForeground(foregroundColour);
                }
                else {
                    blinkingLabel.setForeground(backgroundColour);
                }
                isForeground = !isForeground;
            }
            else {
                // here we want to make sure that the label is visible
                // if the blinking is off.
                if (isForeground) {
                    blinkingLabel.setForeground(foregroundColour);
                    isForeground = false;
                }
            }
        }

    }
}