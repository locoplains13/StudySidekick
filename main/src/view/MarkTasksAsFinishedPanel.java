package view;

import Objects.Course;
import Objects.CourseList;
import Objects.*;
import Objects.TaskDB;
import control.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Objects;

import static model.AddComponentsToPanel.addComponentsToPanel;

public class MarkTasksAsFinishedPanel extends ViewPanel{

    private LinkedList<Task> tasks = TaskDB.getInstance().getTasks();
    private Course course;

    public MarkTasksAsFinishedPanel(ActionListener listener, Course course){
        Component[] components;
        this.course = course;

//////////////////////////////////////////////////////////////////////////////////////////////
        // PANEL FOR MARKING TASKS AS FINISHED

        // set initial conditions for this
        this.setBorder(BorderFactory.createEmptyBorder(25, 80, 25, 80));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.white);

        // create components for this
        JLabel jLabelTaskCourse = new JLabel("Mark Task as Finished:");
        JComboBox<String> jComboBoxTaskCourse = new JComboBox<>(getCourseTasks());
        jLabelTaskCourse.setAlignmentX(Component.CENTER_ALIGNMENT);
        jComboBoxTaskCourse.setAlignmentX(Component.CENTER_ALIGNMENT);
        RoundButton buttonFinishTask = new RoundButton("Mark Task As Finished");
        buttonFinishTask.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttonFinishTask.setBackground(new Color(179, 217, 255));
        buttonFinishTask.setForeground(Color.white);
        buttonFinishTask.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonFinishTask.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!(jComboBoxTaskCourse.getSelectedIndex() ==0)){
                for (int i = 0; i < tasks.size(); i++) {
                    if (tasks.get(i).getTaskName().compareTo((String) Objects.requireNonNull(jComboBoxTaskCourse.getSelectedItem())) == 0) {
                        course.getFinishedTasks().add(tasks.get(i));
                        TaskDB.getInstance().removeTask(tasks.get(i).getTaskName());
                        tasks.get(i).setFinished();
                    }
                }
                CourseList.getInstance().saveCourseList();
                MainPanel.setMainPanel(new CoursePagePanel(listener, course));
                Controller.getView().showMainView(listener);
                }
                else{
                    jComboBoxTaskCourse.setBorder(BorderFactory.createLineBorder(Color.RED));
                }
            }
        });

        // add components to this
        components = new Component[]{jLabelTaskCourse, jComboBoxTaskCourse, buttonFinishTask};
        addComponentsToPanel(this, components);
//////////////////////////////////////////////////////////////////////////////////////////////
    }

    /**
     * Create a list of all of the tasks for course from the TaskDB
     * @return array of the names of the tasks for the course
     */
    private String[] getCourseTasks() {
        int size = 0;
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getCourseName().compareTo(course.getCourseName()) == 0) {
                size++;
            }
        }
        String[] courseTaskNames = new String[size + 1];
        courseTaskNames[0] = "Select From List";
        int index = 1;
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getCourseName().compareTo(course.getCourseName()) == 0) {
                courseTaskNames[index] = tasks.get(i).getTaskName();
                index++;
            }
        }
        return courseTaskNames;
    }
}
