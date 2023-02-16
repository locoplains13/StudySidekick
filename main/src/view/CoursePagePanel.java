package view;

import Objects.Course;
import Objects.CourseList;
import Objects.RoundButton;
import control.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static model.AddComponentsToPanel.addComponentsToPanel;


public class CoursePagePanel extends ViewPanel {
    static final int FONT_SIZE = 32;

    public static final long serialVersionUID = 1;

    public ActionListener listener;

    /**
     * Create a course page panel, and button to return to courseList
     * panel.
     * @param listener the class listening for the event that signals the button was pressed
     * @param course   for the panel
     */
    public CoursePagePanel(ActionListener listener, Course course) {
        Component[] components;

//////////////////////////////////////////////////////////////////////////////////////////////
        // PANEL CONTAINING THE FOUR PANELS - GRADING SCHEME, MARKING TASKS AS FINISHED,
        // DISPLAYING AND ADDING GRADES

        JPanel jPanelMiddle = new JPanel();

        // set initial conditions for jPanelMiddle
        jPanelMiddle.setBackground(Color.white);
        GridLayout layout = new GridLayout(2, 2);
        jPanelMiddle.setLayout(layout);
        layout.setVgap(50);
        layout.setHgap(30);
        GradingSchemePanel gradingSchemePanel = new GradingSchemePanel(listener, course);

        // create components for jPanelMiddle
        MarkTasksAsFinishedPanel markTasksAsFinishedPanel = new MarkTasksAsFinishedPanel(listener, course);
        AddDisplayCurrentGradePanel addDisplayCurrentGradePanel = new AddDisplayCurrentGradePanel(listener, course);
        DisplayFinishedTasksPanel displayFinishedTasksPanel = new DisplayFinishedTasksPanel(listener, course);

        // add components to jPanelMiddle
        components = new Component[]{gradingSchemePanel, markTasksAsFinishedPanel,
                addDisplayCurrentGradePanel, displayFinishedTasksPanel};
        addComponentsToPanel(jPanelMiddle, components);

//////////////////////////////////////////////////////////////////////////////////////////////
        // PANEL FOR BUTTONS AT BOTTOM OF SCREEN

        JPanel jPanelBottomButtons = new JPanel();
        // set initial conditions for jPanelBottomButtons
        jPanelBottomButtons.setLayout(new BoxLayout(jPanelBottomButtons, BoxLayout.X_AXIS));
        jPanelBottomButtons.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        jPanelBottomButtons.setBackground(Color.white);

        // create components for jPanelBottomButtons
        RoundButton removeButton = new RoundButton("Remove Course");
        removeButton.setBackground(new Color(179, 217, 255));
        removeButton.setForeground(Color.white);
        removeButton.setActionCommand("removeCourse");
        removeButton.addActionListener(listener);
        removeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        removeButton.addActionListener(e -> CourseList.getInstance().removeCourse(course));

        RoundButton backButton = new RoundButton("Back");
        backButton.setBackground(new Color(179, 217, 255));
        backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backButton.setForeground(Color.white);
        backButton.addActionListener(e -> {
            MainPanel.setMainPanel(new CourseListPanel(listener));
            Controller.getView().showMainView(listener);
        });

        RoundButton statsButton = new RoundButton("Statistics");
        statsButton.setBackground(new Color(179, 217, 255));
        statsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        statsButton.setForeground(Color.white);
        statsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainPanel.setMainPanel(new StatsPanel(listener, course));
                Controller.getView().showMainView(listener);

            }
        });

        // add components to jPanelBottomButtons
        components = new Component[]{removeButton, backButton, statsButton};
        addComponentsToPanel(jPanelBottomButtons, components);

//////////////////////////////////////////////////////////////////////////////////////////////
        // THIS PANEL

        // Set initial panel conditions

        setBackground(Color.white);
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        // create non-panel components for this
        JLabel jLabelCourseName = new JLabel(course.getCourseName());
        jLabelCourseName.setFont(new Font("Arial", Font.BOLD, FONT_SIZE));
        jLabelCourseName.setForeground(Color.BLACK);
        jLabelCourseName.setAlignmentX(Component.CENTER_ALIGNMENT);

        // add components to this
        components = new Component[]{jLabelCourseName,
                Box.createRigidArea(new Dimension(0, 20)),
                jPanelMiddle, Box.createRigidArea(new Dimension(0, 10)),
                jPanelBottomButtons, Box.createRigidArea(new Dimension(0, 5))};
        addComponentsToPanel(this, components);

//////////////////////////////////////////////////////////////////////////////////////////////

    }
}




