package view;

import Objects.*;
import control.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static model.AddComponentsToPanel.addComponentsToPanel;

public class TaskPanel extends ViewPanel{

    static final int FONT_SIZE = 20;

    public TaskPanel(ActionListener listener, Task task) {
        Component[] components;
        LinkedList<Task> tasks = TaskDB.getInstance().getTasks();
        List<Course> courses = CourseList.getInstance().getCourses();
        // TODO be able to finish task through this panel
        // TODO Could be an option to 'prioritize task' that would move task to the top of TaskDB

//////////////////////////////////////////////////////////////////////////////////////////////
        // PANEL FOR TYPE, COURSE, AND DUE DATE

        JPanel jPanelInfo = new JPanel();

        // set initial conditions for jPanelInfo
        jPanelInfo.setBackground(Color.white);
        GridLayout layout = new GridLayout(0, 3);
        jPanelInfo.setLayout(layout);
        jPanelInfo.setMaximumSize(new Dimension(500, 30));

        // create components for jPanelInfo
        JLabel jLabelType = new JLabel("Type: " + task.getTaskType());

        JLabel jLabelCourse = new JLabel("Course: " + task.getCourseName());

        // add due date
        SimpleDateFormat format = new SimpleDateFormat("MMMM d, yyyy");
        String dueDate = format.format(task.getDueDate());
        JLabel jLabelDueDate = new JLabel("Due Date: " + dueDate);

        // add components to jPanelInfo
        components = new Component[]{jLabelType, jLabelCourse, jLabelDueDate};
        addComponentsToPanel(jPanelInfo, components);

//////////////////////////////////////////////////////////////////////////////////////////////

        // set initial conditions for this
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBackground(Color.white);

        // create components for this
        JLabel jLabelTaskName = new JLabel(task.getTaskName());
        jLabelTaskName.setFont(new Font("Arial", Font.BOLD, FONT_SIZE));
        jLabelTaskName.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel jLabelEstimatedTime = new JLabel("Estimated Time: " + task.getEstimatedTimeToComplete()[0]
                + " days " + task.getEstimatedTimeToComplete()[1] + " hours " + task.getEstimatedTimeToComplete()[2] + " minutes ");
        jLabelEstimatedTime.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel jLabelComments = new JLabel("Comments: " + task.getComments());
        jLabelComments.setAlignmentX(Component.CENTER_ALIGNMENT);

        // properly format text for timeSpent so that it is no longer in milliseconds
        long time = task.getTimeSpentThusFar();
        long hr = TimeUnit.MILLISECONDS.toHours(time);
        long min = TimeUnit.MILLISECONDS.toMinutes(time
                - TimeUnit.HOURS.toMillis(hr));
        long sec = TimeUnit.MILLISECONDS.toSeconds(time
                - TimeUnit.HOURS.toMillis(hr) - TimeUnit.MINUTES.toMillis(min));

        JLabel jLabelTimeSpent = new JLabel("Time Spent: " + String.format("%2d hours %2d minutes %2d seconds", hr, min, sec));
        jLabelTimeSpent.setAlignmentX(Component.CENTER_ALIGNMENT);

        RoundButton buttonEdit = new RoundButton("Edit Task");
        buttonEdit.setBackground(new Color(179, 217, 255));
        buttonEdit.setForeground(Color.white);
        buttonEdit.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainPanel.setMainPanel(new AddEditTaskPanel(listener, task));
                Controller.getView().showMainView(listener);
            }
        });

        RoundButton buttonRemoveTask = new RoundButton("Remove Task");
        buttonRemoveTask.setBackground(new Color(179, 217, 255));
        buttonRemoveTask.setForeground(Color.white);
        buttonRemoveTask.setAlignmentX(Component.CENTER_ALIGNMENT);

        if (!task.isFinished()) {
            buttonRemoveTask.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TaskDB.getInstance().removeTask(task.getTaskName());
                    MainPanel.setMainPanel(new TaskDBPanel(listener));
                    Controller.getView().showMainView(listener);
                }
            });
        }
        else {
            buttonRemoveTask.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    for (int i = 0; i < courses.size();i++) {
                        if (courses.get(i).getCourseName().compareTo(task.getCourseName()) == 0) {
                            courses.get(i).removeFinishedTask(task);
                            MainPanel.setMainPanel(new CoursePagePanel(listener, courses.get(i)));
                            Controller.getView().showMainView(listener);
                        }
                    }
                }
            });
        }

        RoundButton buttonMarkTaskAsFinishedUnfinished = new RoundButton();
        buttonMarkTaskAsFinishedUnfinished.setBackground(new Color(179, 217, 255));
        buttonMarkTaskAsFinishedUnfinished.setForeground(Color.white);
        buttonMarkTaskAsFinishedUnfinished.setAlignmentX(Component.CENTER_ALIGNMENT);
        if (!task.isFinished()) {
            buttonMarkTaskAsFinishedUnfinished.setText("Mark Task As Finished");
            buttonMarkTaskAsFinishedUnfinished.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TaskDB.getInstance().removeTask(task.getTaskName());
                    task.setFinished();
                    for (int i = 0; i < courses.size(); i++) {
                        if (courses.get(i).getCourseName().compareTo(task.getCourseName()) == 0) {
                            courses.get(i).addFinishedTask(task);
                            MainPanel.setMainPanel(new TaskDBPanel(listener));
                            Controller.getView().showMainView(listener);
                        }
                    }
                }
            });
        }
        else{
            buttonMarkTaskAsFinishedUnfinished.setText("Mark Task As Unfinished");
            buttonMarkTaskAsFinishedUnfinished.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    for (int i = 0; i < courses.size(); i++) {
                        if (courses.get(i).getCourseName().compareTo(task.getCourseName()) == 0) {
                            courses.get(i).removeFinishedTask(task);
                            task.setUnfinished();
                            TaskDB.getInstance().saveTask(task);
                            MainPanel.setMainPanel(new CoursePagePanel(listener,courses.get(i)));
                            Controller.getView().showMainView(listener);
                        }
                    }
                }
            });
        }

        RoundButton backButton = new RoundButton("Back");
        backButton.setBackground(new Color(179, 217, 255));
        backButton.setForeground(Color.white);
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (task.isFinished()){
                    for (int i = 0; i < courses.size(); i ++){
                        if (courses.get(i).getCourseName().compareTo(task.getCourseName())==0){
                            MainPanel.setMainPanel(new CoursePagePanel(listener, courses.get(i)));
                        }
                    }
                }
                else{
                    MainPanel.setMainPanel(new TaskDBPanel(listener));
                }
                Controller.getView().showMainView(listener);
            }
        });

        // add components to this
        components = new Component[]{Box.createRigidArea(new Dimension(0, 20)),jLabelTaskName,
                Box.createRigidArea(new Dimension(0, 20)),jPanelInfo,
                Box.createRigidArea(new Dimension(0, 20)),jLabelEstimatedTime,
                Box.createRigidArea(new Dimension(0, 20)),jLabelComments,
                Box.createRigidArea(new Dimension(0, 20)),jLabelTimeSpent,
                Box.createRigidArea(new Dimension(0, 20)),buttonEdit,
                Box.createRigidArea(new Dimension(0, 10)),buttonMarkTaskAsFinishedUnfinished,
                Box.createRigidArea(new Dimension(0, 10)),buttonRemoveTask,
                Box.createRigidArea(new Dimension(0, 10)),backButton};
        addComponentsToPanel(this,components);

//////////////////////////////////////////////////////////////////////////////////////////////

    }
}
