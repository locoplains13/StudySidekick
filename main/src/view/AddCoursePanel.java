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

public class AddCoursePanel extends ViewPanel{
    static final int FONT_SIZE = 32;

    public static final long serialVersionUID = 1;

    /**
     * Create a panel so the user can add a course to the list of courses
     *
     * @param listener ActionListener for detecting user input
     */
    public AddCoursePanel(ActionListener listener) {
        Component[] components;

//////////////////////////////////////////////////////////////////////////////////////////////
        // PANEL FOR ADDING COURSES TO COURSE LIST

        // Set initial conditions for this
        setBackground(Color.white);
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        // create components for this
        JLabel jLabelTitle = new JLabel("Add Course");
        jLabelTitle.setFont(new Font("Arial", Font.BOLD, FONT_SIZE));
        jLabelTitle.setForeground(Color.BLACK);
        jLabelTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel jLabelEnterName = new JLabel("Enter Course name: ");
        JTextPane jTextPaneCourseName = new JTextPane();
        jTextPaneCourseName.setBorder(BorderFactory.createLineBorder(new Color(179, 217, 255)));
        jTextPaneCourseName.setMaximumSize(new Dimension(150,20));
        jLabelEnterName.setAlignmentX(Component.CENTER_ALIGNMENT);
        jTextPaneCourseName.setAlignmentX(Component.CENTER_ALIGNMENT);



        RoundButton buttonCreateCoursePage = new RoundButton("Create Course Page");
        buttonCreateCoursePage.setBackground(new Color(179, 217, 255));
        buttonCreateCoursePage.setForeground(Color.white);
        buttonCreateCoursePage.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonCreateCoursePage.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttonCreateCoursePage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jTextPaneCourseName.getText().isEmpty()){
                    jTextPaneCourseName.setBorder(BorderFactory.createLineBorder(Color.RED));

                } else if (CourseList.getInstance().containsCourse(jTextPaneCourseName.getText())){
                    jTextPaneCourseName.setBorder(BorderFactory.createLineBorder(Color.RED));
                }
                else{
                    CourseList.getInstance().saveCourseList(new Course(jTextPaneCourseName.getText()));
                    MainPanel.setMainPanel(new CourseListPanel(listener));
                    Controller.getView().showMainView(listener);
                }
            }
        });

        RoundButton backButton = new RoundButton("Back");
        backButton.setBackground(new Color(179, 217, 255));
        backButton.setForeground(Color.white);
        backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainPanel.setMainPanel(new CourseListPanel(listener));
                Controller.getView().showMainView(listener);
            }
        });

        // add components to this
        components = new Component[]{Box.createRigidArea(new Dimension(0, 20)),
                jLabelTitle,Box.createRigidArea(new Dimension(0, 10)),
                jLabelEnterName,jTextPaneCourseName,
                Box.createRigidArea(new Dimension(0, 80)),
                buttonCreateCoursePage,Box.createRigidArea(new Dimension(0, 20)),
                backButton };
        addComponentsToPanel(this, components);

//////////////////////////////////////////////////////////////////////////////////////////////
    }
}
