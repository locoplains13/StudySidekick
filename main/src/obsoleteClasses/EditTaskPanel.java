package obsoleteClasses;

import Objects.Course;
import Objects.CourseList;
import Objects.Task;
import Objects.TaskDB;
import control.Controller;
import view.View;
import view.ViewPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.LinkedList;

public class EditTaskPanel extends ViewPanel{
    static final int FONT_SIZE = 20;
    public EditTaskPanel(int width, int height, ActionListener listener, Task task, View view){
        setSize(width,height);
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBackground(new Color(179, 217, 255));
        add(Box.createRigidArea(new Dimension(0, height / 10)));
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        // Create "Add Task" label
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        JLabel label = new JLabel("Edit Task");
        label.setFont(new Font("Arial", Font.BOLD, FONT_SIZE));
        label.setForeground(Color.BLACK);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(label);

        add(Box.createRigidArea(new Dimension(0, height / 5)));

        // Create panel to create separate layout for the task name, type and course name fields
        JPanel jPanelNameTypeCourse = new JPanel();
        jPanelNameTypeCourse.setBackground(new Color(179, 217, 255));
        GridLayout layout = new GridLayout(0,6);
        jPanelNameTypeCourse.setLayout(layout);
        layout.setHgap(20);

        // Create text pane to prompt for task name
        JLabel jLabelEnterName = new JLabel("Task name:");
        JTextPane jTextPaneTaskName = new JTextPane();
        jTextPaneTaskName.setText(task.getTaskName());
        jLabelEnterName.setAlignmentX(Component.CENTER_ALIGNMENT);
        jTextPaneTaskName.setAlignmentX(Component.CENTER_ALIGNMENT);
        jPanelNameTypeCourse.add(jLabelEnterName);
        jPanelNameTypeCourse.add(jTextPaneTaskName);

        // Create comboxBox and labels to prompt for user selections
        JComboBox<String> jComboBoxTaskType = new JComboBox<>(new String[]{"Assignment", "Reading", "Lecture", "Exam Prep", "Other"});
        JLabel jLabelTaskType = new JLabel("Task Type:");
        JLabel jLabelTaskCourse = new JLabel("Task Course:");
        jComboBoxTaskType.setSelectedItem(task.getTaskType());
        // Populate array with course names
        LinkedList<Course> courses = CourseList.getInstance().getCourses();
        String[] courseNames = new String[courses.size() + 1];
        courseNames[0] = "None";
        for (int i = 0; i < courses.size(); i++) {
            courseNames[i + 1] = courses.get(i).getCourseName();
        }

        // Populate comboxBox and add panel elements
        JComboBox<String> jComboBoxTaskCourse = new JComboBox<>(courseNames);
        jComboBoxTaskCourse.setSelectedItem(task.getCourseName());
        jLabelTaskType.setAlignmentX(Component.CENTER_ALIGNMENT);
        jComboBoxTaskType.setAlignmentX(Component.CENTER_ALIGNMENT);
        jLabelTaskCourse.setAlignmentX(Component.CENTER_ALIGNMENT);
        jComboBoxTaskCourse.setAlignmentX(Component.CENTER_ALIGNMENT);
        jPanelNameTypeCourse.add(jLabelTaskType);
        jPanelNameTypeCourse.add(jComboBoxTaskType);
        jPanelNameTypeCourse.add(jLabelTaskCourse);
        jPanelNameTypeCourse.add(jComboBoxTaskCourse);
        add(jPanelNameTypeCourse);

        // create panel for the the separate layout used for dueDate and estimatedTime
        add(Box.createRigidArea(new Dimension(0, height / 20)));
        JPanel jPanelDueTime = new JPanel();
        jPanelDueTime.setBackground(new Color(179, 217, 255));
        GridLayout layout2 = new GridLayout(2,7);
        jPanelDueTime.setLayout(layout2);
        layout2.setHgap(15);

        // Create comboxBoxes and labels for task information
        JLabel jLabelDueDate = new JLabel("Due Date:");
        JComboBox<String> jComboBoxMonth = new JComboBox<>(new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"});
        JComboBox<Integer> jComboBoxDay = new JComboBox<>(new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31});
        JComboBox<Integer> jComboBoxYear = new JComboBox<>(new Integer[]{2021, 2022, 2023, 2024});
        //jComboBoxMonth.setSelectedIndex(task.getDueDate()[0]);
        //jComboBoxDay.setSelectedIndex(task.getDueDate()[1]);
        //jComboBoxYear.setSelectedItem(task.getDueDate()[2]);
        JLabel jLabelMonth = new JLabel("Month:");
        JLabel jLabelDay = new JLabel("Day:");
        JLabel jLabelYear = new JLabel("Year:");

        // Set alignments and add to panel
        jLabelDueDate.setAlignmentX(Component.CENTER_ALIGNMENT);
        jComboBoxMonth.setAlignmentX(Component.CENTER_ALIGNMENT);
        jComboBoxDay.setAlignmentX(Component.CENTER_ALIGNMENT);
        jComboBoxYear.setAlignmentX(Component.CENTER_ALIGNMENT);
        jLabelMonth.setAlignmentX(Component.CENTER_ALIGNMENT);
        jLabelDay.setAlignmentX(Component.CENTER_ALIGNMENT);
        jLabelYear.setAlignmentX(Component.CENTER_ALIGNMENT);
        jPanelDueTime.add(jLabelDueDate);
        jPanelDueTime.add(jLabelMonth);
        jPanelDueTime.add(jComboBoxMonth);
        jPanelDueTime.add(jLabelDay);
        jPanelDueTime.add(jComboBoxDay);
        jPanelDueTime.add(jLabelYear);
        jPanelDueTime.add(jComboBoxYear);


        // Create comboBoxes and labels for task information
        JLabel jLabelEstimateTime = new JLabel("Time to Complete:");
        jLabelEstimateTime.setSize(200, 200);
        JLabel jLabelDays = new JLabel("Days:");
        JLabel jLabelHours = new JLabel("Hrs:");
        JLabel jLabelMinutes = new JLabel("Mins:");
        JComboBox<Integer> jComboBoxHours = new JComboBox<>(new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18,
                19, 20, 21, 22, 23});
        JComboBox<Integer> jComboBoxMinutes = new JComboBox<>(new Integer[]{0, 5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55});
        JComboBox<Integer> jComboBoxDays = new JComboBox<>(new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31});
        jComboBoxDays.setSelectedIndex(task.getEstimatedTimeToComplete()[0]);
        jComboBoxHours.setSelectedIndex(task.getEstimatedTimeToComplete()[1]);
        jComboBoxMinutes.setSelectedIndex(task.getEstimatedTimeToComplete()[2]);
        // Set alignments and add to panel
        jLabelEstimateTime.setAlignmentX(Component.CENTER_ALIGNMENT);
        jLabelDays.setAlignmentX(Component.CENTER_ALIGNMENT);
        jLabelHours.setAlignmentX(Component.CENTER_ALIGNMENT);
        jLabelMinutes.setAlignmentX(Component.CENTER_ALIGNMENT);
        jComboBoxHours.setAlignmentX(Component.CENTER_ALIGNMENT);
        jComboBoxMinutes.setAlignmentX(Component.CENTER_ALIGNMENT);
        jComboBoxDays.setAlignmentX(Component.CENTER_ALIGNMENT);

        jPanelDueTime.add(jLabelEstimateTime);
        jPanelDueTime.add(jLabelDays);
        jPanelDueTime.add(jComboBoxDays);
        jPanelDueTime.add(jLabelHours);
        jPanelDueTime.add(jComboBoxHours);
        jPanelDueTime.add(jLabelMinutes);
        jPanelDueTime.add(jComboBoxMinutes);
        add(jPanelDueTime);

        add(Box.createRigidArea(new Dimension(0, height / 20)));

        JTextField jTextFieldComments = new JTextField(task.getComments());
        // when user is typing in the comment box, the above prompt disappears

        jTextFieldComments.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(jTextFieldComments);
        jTextFieldComments.setSize(new Dimension(300, 200));


        add(Box.createRigidArea(new Dimension(0, height / 20)));
        JButton jButtonSubmit = new JButton("Submit");
        jButtonSubmit.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(jButtonSubmit);
        jButtonSubmit.setActionCommand("main");
        jButtonSubmit.addActionListener(listener);
        jButtonSubmit.addActionListener(new ActionListener() {
            @Override
            /**
             * When use presses submit button the task will be prioritized and added to taskDB
             */
            public void actionPerformed(ActionEvent e) {
                task.setDueDate(new Date());
                task.setEstimatedTimeToComplete(new Integer[]{(Integer) jComboBoxDays.getSelectedItem(), (Integer) jComboBoxHours.getSelectedItem(), (Integer) jComboBoxMinutes.getSelectedItem()});
                task.setComments(jTextFieldComments.getText());
                task.setCourseName((String) jComboBoxTaskCourse.getSelectedItem());
                task.setTaskType((String) jComboBoxTaskType.getSelectedItem());
                task.setTaskName(jTextPaneTaskName.getText());
                TaskDB.getInstance().saveEditedTask();


            }
        });

        add(Box.createRigidArea(new Dimension(0, height / 20)));
        JButton backButton = new JButton("Back");
        backButton.setForeground(Color.BLACK);
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(backButton);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.getView().showTaskView(listener,task);
            }
        });

    }
}
