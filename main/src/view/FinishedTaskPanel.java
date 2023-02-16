package view;

import Objects.Course;
import Objects.Task;
import control.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FinishedTaskPanel extends ViewPanel {

    public FinishedTaskPanel(int width, int height, ActionListener listener, Course course) {
//////////////////////////////////////////////////////////////////////////////////////////////
        setSize(width, height);
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBackground(Color.white);

        JButton jButtonTaskNames;
        // TODO when a user deletes a task from the finished task area there should be
        // TODO an option to put the task back on the TaskDB
        for (Task entry : course.getFinishedTasks()) {
            jButtonTaskNames = new JButton(entry.getTaskName());
            jButtonTaskNames.setFocusPainted(false);
            jButtonTaskNames.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            jButtonTaskNames.setMargin(new Insets(0, 0, 0, 0));
            jButtonTaskNames.setContentAreaFilled(false);
            jButtonTaskNames.setBorderPainted(false);
            jButtonTaskNames.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    MainPanel.setMainPanel(new TaskPanel(listener, entry));
                    Controller.getView().showMainView(listener);
                }
            });
            add(jButtonTaskNames);
//////////////////////////////////////////////////////////////////////////////////////////////
    }
}
}