package Objects;

import javax.swing.*;
import java.awt.*;

//RoundButton.java
//Based on an example found on the Sun web site.

public class RoundButton extends JButton
{

    public RoundButton(String label)
    {
        super(label);

        //Enlarge button to make a circle rather than an oval.
        Dimension size = new Dimension(100,30);
        setPreferredSize(size);

        //This call causes the JButton *not* to paint the background
        //and allows us to paint a round background instead.
        setContentAreaFilled(false);
        setBorderPainted(false);

    }

    public RoundButton(){
        super();

        Dimension size = new Dimension(100,30);
        setPreferredSize(size);

        //This call causes the JButton *not* to paint the background
        //and allows us to paint a round background instead.
        setContentAreaFilled(false);
        setBorderPainted(false);
    }

    //Paint the round background and label.
    protected void paintComponent(Graphics g)
    {
        if (getModel().isArmed())
            //You might want to make the highlight color
            //a property of the RoundButton class.
            g.setColor(Color.lightGray);
        else
            g.setColor(getBackground());
        g.fillRoundRect(0, 0, getSize().width-1, getSize().height-1,20,20);
        //This call will paint label and focus rectangle.
        super.paintComponent(g);
    }

    //Paint the border of the button using a simple stroke.
    protected void paintBorder(Graphics g)
    {
        g.setColor(getForeground());
        g.drawRoundRect(0, 0, getSize().width-1, getSize().height-1,20,20);
    }
}