package obsoleteClasses;

import Objects.Course;
import Objects.CourseList;
import view.ViewPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.Serializable;
import java.util.LinkedList;

public class AddTaskPanel extends ViewPanel implements Serializable{

    static final int FONT_SIZE = 32;

    public AddTaskPanel(int width, int height, ActionListener listener) {
        // Set initial panel conditions
        setSize(width, height);
        setBackground(new Color(179, 217, 255));
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 50));

        add(Box.createRigidArea(new Dimension(0, height / 20)));

        // Create "Add Task" label
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        JLabel label = new JLabel("Add Task");
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

        jLabelEnterName.setAlignmentX(Component.CENTER_ALIGNMENT);
        jTextPaneTaskName.setAlignmentX(Component.CENTER_ALIGNMENT);
        jPanelNameTypeCourse.add(jLabelEnterName);
        jPanelNameTypeCourse.add(jTextPaneTaskName);

        // Create comboxBox and labels to prompt for user selections
        JComboBox<String> jComboBoxTaskType = new JComboBox<>(new String[]{"Select One", "Assignment", "Reading", "Lecture", "Exam Prep", "Other"});
        JLabel jLabelTaskType = new JLabel("Task Type:");
        JLabel jLabelTaskCourse = new JLabel("Task Course:");

        // Populate array with course names
        LinkedList<Course> courses = CourseList.getInstance().getCourses();
        String[] courseNames = new String[courses.size() + 1];
        courseNames[0] = "None";
        for (int i = 0; i < courses.size(); i++) {
            courseNames[i + 1] = courses.get(i).getCourseName();
        }

        // Populate comboxBox and add panel elements
        JComboBox<String> jComboBoxTaskCourse = new JComboBox<>(courseNames);
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

        JTextField jTextFieldComments = new JTextField("Enter all other information here");
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
        jTextFieldComments.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(jTextFieldComments);
        jTextFieldComments.setSize(new Dimension(300, 200));


        add(Box.createRigidArea(new Dimension(0, height / 20)));
        JButton jButtonAddTask = new JButton("Add Task");
        jButtonAddTask.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(jButtonAddTask);

        jButtonAddTask.addActionListener(new ActionListener() {
            @Override
            /**
             * When use presses submit button the task will be prioritized and added to taskDB
             */
            public void actionPerformed(ActionEvent e) {
                /*Integer[] dueDate = {jComboBoxMonth.getSelectedIndex(), (Integer) jComboBoxDay.getSelectedItem(), (Integer) jComboBoxYear.getSelectedItem()};
                Integer[] time = {(Integer) jComboBoxDays.getSelectedItem(), (Integer) jComboBoxHours.getSelectedItem(), (Integer) jComboBoxMinutes.getSelectedItem()};
                Task task = new Task(jTextPaneTaskName.getText(), jComboBoxTaskType.getSelectedItem() + "", jComboBoxTaskCourse.getSelectedItem() + "", dueDate, time, jTextFieldComments.getText());
                TaskDB.getInstance().saveTask(task);
                Controller.getView().showMainView(listener);*/
            }
        });

        add(Box.createRigidArea(new Dimension(0, height / 20)));
        JButton backButton = new JButton("Back to Main");
        backButton.setForeground(Color.BLACK);
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(backButton);
        backButton.setActionCommand("main");
        backButton.addActionListener(listener);
        add(Box.createRigidArea(new Dimension(0, height / 20)));

    }
}
