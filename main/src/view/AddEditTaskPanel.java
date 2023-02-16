package view;

import Objects.*;
import control.Controller;
import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;

import static model.AddComponentsToPanel.addComponentsToPanel;

public class AddEditTaskPanel extends ViewPanel implements Serializable{
// TODO NO DUPLICATE TASKS FOR A COURSE
    static final int FONT_SIZE = 32;

    public AddEditTaskPanel(ActionListener listener, Task task) {
        Component[] components;
        LinkedList<Course> courses = CourseList.getInstance().getCourses();

//////////////////////////////////////////////////////////////////////////////////////////////
        // PANEL FOR TASK NAME, TYPE, AND COURSE NAME

        JPanel jPanelNameTypeCourse = new JPanel();

        // set initial conditions for jPanelNameTypeCourse
        jPanelNameTypeCourse.setBackground(Color.white);

        // create components for jPanelNameTypeCourse
        JLabel jLabelEnterName = new JLabel("Task name:");
        jLabelEnterName.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField jTextPaneTaskName = new JTextField();
        jTextPaneTaskName.setBorder(BorderFactory.createLineBorder(new Color(179, 217, 255)));
        jTextPaneTaskName.setAlignmentX(Component.CENTER_ALIGNMENT);
        jTextPaneTaskName.setPreferredSize(new Dimension(150,30));

        JLabel jLabelTaskType = new JLabel("Task Type:");
        jLabelTaskType.setAlignmentX(Component.CENTER_ALIGNMENT);

        JComboBox<String> jComboBoxTaskType = new JComboBox<>(new String[]
                { "Assignment", "Reading", "Lecture", "Exam Prep", "Other"});
        jComboBoxTaskType.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel jLabelTaskCourse = new JLabel("Task Course:");
        jLabelTaskCourse.setAlignmentX(Component.CENTER_ALIGNMENT);

        String[] courseNames = new String[courses.size() + 1];
        courseNames[0] = "None";
        for (int i = 0; i < courses.size(); i++) {
            courseNames[i + 1] = courses.get(i).getCourseName();
        }

        JComboBox<String> jComboBoxTaskCourse = new JComboBox<>(courseNames);
        jComboBoxTaskCourse.setAlignmentX(Component.CENTER_ALIGNMENT);

        // add components for jPanelNameTypeCourse
        components = new Component[]{jLabelEnterName,jTextPaneTaskName,jLabelTaskType,
        jComboBoxTaskType,jLabelTaskCourse,jComboBoxTaskCourse};
        addComponentsToPanel(jPanelNameTypeCourse,components);

//////////////////////////////////////////////////////////////////////////////////////////////
        // PANEL FOR DUE DATE AND ESTIMATED TIME TO COMPLETE

        JPanel jPanelDueDateTimeToComplete = new JPanel();

        // set initial conditions for jPanelDueDateTimeToComplete
        jPanelDueDateTimeToComplete.setBackground(Color.white);

        // create components for jPanelDueDateTimeToComplete
        JLabel jLabelDueDate = new JLabel("Due Date:");

        JXDatePicker chooseDueDate = new JXDatePicker();
        chooseDueDate.setDate(Calendar.getInstance().getTime());
        chooseDueDate.setFormats( new SimpleDateFormat("MMMM d, yyyy"));

        JLabel jLabelEstimateTime = new JLabel("Time to Complete:");
        jLabelEstimateTime.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel jLabelDays = new JLabel("Days:");
        jLabelDays.setAlignmentX(Component.CENTER_ALIGNMENT);

        JComboBox<Integer> jComboBoxDays = new JComboBox<>(new Integer[]{0, 1, 2, 3, 4, 5, 6, 7,
                8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31});
        jComboBoxDays.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel jLabelHours = new JLabel("Hrs:");
        jLabelHours.setAlignmentX(Component.CENTER_ALIGNMENT);

        JComboBox<Integer> jComboBoxHours = new JComboBox<>(new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
                11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23});
        jComboBoxHours.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel jLabelMinutes = new JLabel("Mins:");
        jLabelMinutes.setAlignmentX(Component.CENTER_ALIGNMENT);

        JComboBox<Integer> jComboBoxMinutes = new JComboBox<>(new Integer[]{0, 5, 10, 15, 20, 25, 30,
                35, 40, 45, 50, 55});
        jComboBoxMinutes.setAlignmentX(Component.CENTER_ALIGNMENT);

        // add components to jPanelDueDateTimeToComplete
        components = new Component[]{jLabelDueDate,chooseDueDate,jLabelEstimateTime,jLabelDays,jComboBoxDays,jLabelHours,
                jComboBoxHours,jLabelMinutes,jComboBoxMinutes};
        addComponentsToPanel(jPanelDueDateTimeToComplete, components);

//////////////////////////////////////////////////////////////////////////////////////////////

        // Set initial conditions for this
        setBackground(Color.white);
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 50));

        // create components for this
        JLabel jLabelTitle = new JLabel();
        if (task != null){
            jLabelTitle.setText("Edit Task");
        }
        else{
            jLabelTitle.setText("Add Task");
        }
        jLabelTitle.setFont(new Font("Arial", Font.BOLD, FONT_SIZE));
        jLabelTitle.setForeground(Color.BLACK);
        jLabelTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel jLabelComments = new JLabel("Comments:");
        jLabelComments.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField jTextFieldComments = new JTextField();
        jTextFieldComments.setAlignmentX(Component.CENTER_ALIGNMENT);
        jTextFieldComments.setBorder(BorderFactory.createLineBorder(new Color(179, 217, 255)));
        jTextFieldComments.setPreferredSize(new Dimension(getWidth(),100));
        jTextFieldComments.setSize(getPreferredSize());

        if (task == null){
            jTextFieldComments.setText("Enter all other information here");

            // when user is typing in the comment box, the above prompt disappears
            jTextFieldComments.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    jTextFieldComments.setText("");
                }
                @Override
                public void focusLost(FocusEvent e) {
                }
            });
        }

        RoundButton buttonAddEditTask = new RoundButton();
        buttonAddEditTask.setBackground(new Color(179, 217, 255));
        buttonAddEditTask.setForeground(Color.white);
        buttonAddEditTask.setAlignmentX(Component.CENTER_ALIGNMENT);

        if (task != null){
            buttonAddEditTask.setText("Submit Changes");
        }
        else{
            buttonAddEditTask.setText("Add Task");
        }

        buttonAddEditTask.addActionListener(new ActionListener() {
            @Override
            /**
             * When use presses submit button the task will be prioritized and added to taskDB
             */
            public void actionPerformed(ActionEvent e) {

                // if all of the field for estimated time to complete are empty
                if (((Integer) jComboBoxDays.getSelectedItem() == 0 &&
                        (Integer) jComboBoxHours.getSelectedItem() == 0 &&
                        (Integer) jComboBoxMinutes.getSelectedItem() == 0) || jTextPaneTaskName.getText().isEmpty()) {
                    if (jTextPaneTaskName.getText().isEmpty()) {
                        jTextPaneTaskName.setBorder(BorderFactory.createLineBorder(Color.RED));
                    }
                    if (((Integer) jComboBoxDays.getSelectedItem() == 0 &&
                            (Integer) jComboBoxHours.getSelectedItem() == 0 &&
                            (Integer) jComboBoxMinutes.getSelectedItem() == 0)){
                        jComboBoxDays.setBorder(BorderFactory.createLineBorder(Color.RED));
                        jComboBoxHours.setBorder(BorderFactory.createLineBorder(Color.RED));
                        jComboBoxMinutes.setBorder(BorderFactory.createLineBorder(Color.RED));
                    }
                }
                else {
                    long timeSpentThusFar = 0;
                    boolean isFinished = false;
                    if (task != null) {
                        if (task.getDueDate() != chooseDueDate.getDate()){
                            timeSpentThusFar = task.getTimeSpentThusFar();
                            isFinished = task.isFinished();
                            TaskDB.getInstance().removeTask(task.getTaskName());
                        }
                    }
                    Date dueDate = chooseDueDate.getDate();
                    Integer[] estimatedTimeToComplete = {(Integer) jComboBoxDays.getSelectedItem(),
                            (Integer) jComboBoxHours.getSelectedItem(), (Integer) jComboBoxMinutes.getSelectedItem()};
                    Task task = new Task(jTextPaneTaskName.getText(), jComboBoxTaskType.getSelectedItem() +
                            "", jComboBoxTaskCourse.getSelectedItem() + "",
                            dueDate, estimatedTimeToComplete, jTextFieldComments.getText());

                    if (timeSpentThusFar!= 0){
                        task.setTimeSpentThusFar(timeSpentThusFar);
                    }
                    if (isFinished){
                        task.setFinished();
                    }
                    TaskDB.getInstance().saveTask(task);
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
            }
        });

        RoundButton backButton = new RoundButton("Back");
        backButton.setForeground(Color.white);
        backButton.setBackground(new Color(179, 217, 255));
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (task != null) {

                    if (!task.isFinished()){
                        MainPanel.setMainPanel(new TaskDBPanel(listener));
                    }
                    else{
                        for (int i = 0; i < courses.size(); i ++){
                            if (courses.get(i).getCourseName().compareTo(task.getCourseName())==0){
                                MainPanel.setMainPanel(new CoursePagePanel(listener, courses.get(i)));
                            }
                        }
                    }
                }
                else{
                    MainPanel.setMainPanel(new TaskDBPanel(listener));
                }
                Controller.getView().showMainView(listener);
            }
        });

        // if editing task - make original values as the values already chosen for task
        if (task != null){
            jTextPaneTaskName.setText(task.getTaskName());
            jComboBoxTaskType.setSelectedItem(task.getTaskType());
            jComboBoxTaskCourse.setSelectedItem(task.getCourseName());
            chooseDueDate.setDate(task.getDueDate());
            jComboBoxDays.setSelectedItem(task.getEstimatedTimeToComplete()[0]);
            jComboBoxHours.setSelectedItem(task.getEstimatedTimeToComplete()[1]);
            jComboBoxMinutes.setSelectedItem(task.getEstimatedTimeToComplete()[2]);
            jTextFieldComments.setText(task.getComments());
        }

        // add components to this
        components = new Component[]{Box.createRigidArea(new Dimension(0, 10)),jLabelTitle,
                Box.createRigidArea(new Dimension(0, 25)),jPanelNameTypeCourse,
                Box.createRigidArea(new Dimension(0, 10)),jPanelDueDateTimeToComplete,
                Box.createRigidArea(new Dimension(0, 30)),jLabelComments,
                Box.createRigidArea(new Dimension(0, 15)),jTextFieldComments,
                Box.createRigidArea(new Dimension(0, 10)), buttonAddEditTask,
                Box.createRigidArea(new Dimension(0, 10)), backButton,
                Box.createRigidArea(new Dimension(0, 10))};
        addComponentsToPanel(this, components);

//////////////////////////////////////////////////////////////////////////////////////////////
    }


}