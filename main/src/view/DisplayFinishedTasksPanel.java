package view;

import Objects.Course;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static model.AddComponentsToPanel.addComponentsToPanel;

public class DisplayFinishedTasksPanel extends ViewPanel{

    public DisplayFinishedTasksPanel(ActionListener listener, Course course){
        Component[] components;

//////////////////////////////////////////////////////////////////////////////////////////////
        // PANEL FOR DISPLAYING FINISHED TASKS

        // set initial conditions for this
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.white);

        // create components for this
        JLabel taskLabel = new JLabel("Finished Task List:");
        taskLabel.setFont(new Font("Arial", Font.BOLD, 12));
        taskLabel.setForeground(Color.BLACK);
        taskLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPanel jPanelFinishedList = new FinishedTaskPanel(250, 250, listener, course);
        jPanelFinishedList.setAlignmentX(Component.CENTER_ALIGNMENT);

        // add components to this
        components = new Component[]{taskLabel, jPanelFinishedList};
        addComponentsToPanel(this, components);
//////////////////////////////////////////////////////////////////////////////////////////////
    }
}
