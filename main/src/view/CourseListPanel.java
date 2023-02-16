package view;

import Objects.Course;
import Objects.CourseList;
import Objects.RoundButton;
import control.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static model.AddComponentsToPanel.addComponentsToPanel;

/**
 * Class to create and handle interactions with the CourseListPanel
 */
public class CourseListPanel extends ViewPanel {
    static final int FONT_SIZE = 20;

    public static final long serialVersionUID = 1;
    private List<Course> courses = CourseList.getInstance().getCourses();
    private ActionListener listener;

    /**
     * Method to create the CourseListPanel and use the ActionListener to handle user interactions
     *
     * @param listener the class listening for the event that signals the button was pressed
     */
    public CourseListPanel(ActionListener listener) {
        Component[] components;
        this.listener = listener;
        adjustButtonColor();

//////////////////////////////////////////////////////////////////////////////////////////////

        // Set initial frame and activate listener
        setBackground(Color.white);
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));


        // Create "Course List" label
        JLabel label = new JLabel("Course List");
        label.setFont(new Font("Arial", Font.BOLD, FONT_SIZE));
        label.setForeground(Color.BLACK);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        RoundButton addCourseButton = new RoundButton("Add Course");
        addCourseButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addCourseButton.setBackground(new Color(179, 217, 255));
        addCourseButton.setForeground(Color.white);
        addCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainPanel.setMainPanel(new AddCoursePanel(listener));
                Controller.getView().showMainView(listener);
            }
        });

        addCourseButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // add components to this
        components = new Component[]{ Box.createRigidArea(new Dimension(0, 10)), label,
                Box.createRigidArea(new Dimension(0, 20))};
        addComponentsToPanel(this,components);

        components = addCourse();
        addComponentsToPanel(this,components);

        addComponentsToPanel(this, new Component[]{ Box.createRigidArea(new Dimension(0, 10)),
                addCourseButton});

//////////////////////////////////////////////////////////////////////////////////////////////
    }

    /**
     * Add courses to the display panel
     */
    private Component[] addCourse() {
        Component[] components = new Component[courses.size() * 2];
        Font font = new Font("Arial", Font.BOLD, 16);
        RoundButton id;

        // Add course to panel
        for (int i = 0; i < courses.size() * 2; i += 2) {
            id = new RoundButton(courses.get(i / 2).getCourseName());
            id.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            id.setFont(font);
            id.setBackground(new Color(179, 217, 255));
            id.setForeground(Color.white);
            id.setAlignmentX(Component.CENTER_ALIGNMENT);
            int finalI = i;
            id.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    MainPanel.setMainPanel(new CoursePagePanel(listener, courses.get(finalI / 2)));
                    Controller.getView().showMainView(listener);
                }
            });
            components[i] = id;
            components[i + 1] = Box.createRigidArea(new Dimension(0, getHeight() / 55));
        }
        return components;
    }

    private void adjustButtonColor(){
        MainSidePanel.getJButton("Settings").setForeground(Color.white);
        MainSidePanel.getJButton("Tasks").setForeground(Color.white);
        MainSidePanel.getJButton("Dashboard").setForeground(Color.white);
        MainSidePanel.getJButton("Courses").setForeground(new Color(179, 217, 255));
    }
}