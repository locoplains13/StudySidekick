package view;

import Objects.Course;
import Objects.RoundButton;
import control.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static model.AddComponentsToPanel.addComponentsToPanel;

public class AddDisplayCurrentGradePanel extends ViewPanel{

    private Course course;

    public AddDisplayCurrentGradePanel(ActionListener listener, Course course){
        Component[] components;
        this.course = course;

//////////////////////////////////////////////////////////////////////////////////////////////
        // PANEL FOR ADDING GRADES AND DISPLAYING CURRENT GRADE

        // set initial conditions for this
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.white);

        // create components for this
        JLabel overallGradeLabel = new JLabel("Current Overall Grade:");
        overallGradeLabel.setFont(new Font("Arial", Font.BOLD, 12));
        overallGradeLabel.setForeground(Color.BLACK);
        overallGradeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        RoundButton addGradeButton = new RoundButton("Add Grade");
        addGradeButton.setBackground(new Color(179, 217, 255));
        addGradeButton.setForeground(Color.white);
        addGradeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addGradeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addGradeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (course.getGrades()!=null){
                    MainPanel.setMainPanel(new AddGradePanel(listener, course));
                    Controller.getView().showMainView(listener);
                }
            }
        });

        JPanel jPanelCurrentGradedItems = new CurrentGradedItemsPanel(250, 250, listener, course);
        jPanelCurrentGradedItems.setAlignmentX(Component.CENTER_ALIGNMENT);

        JScrollPane jScrollPaneGradedItems = new JScrollPane(jPanelCurrentGradedItems);
        jScrollPaneGradedItems.setBorder(null);

        // add components to this
        components = new Component[]{overallGradeLabel,
                Box.createRigidArea(new Dimension(0, 10)),
                CurrentGrade(), Box.createRigidArea(new Dimension(0, 10)),
                addGradeButton, jScrollPaneGradedItems};
        addComponentsToPanel(this, components);

//////////////////////////////////////////////////////////////////////////////////////////////
    }

    /**
     * Calculates and creates a JLabel for current grade in course
     */
    private JLabel CurrentGrade() {
        Font font = new Font("Arial", Font.BOLD, 16);
        JLabel grade;
        grade = new JLabel(course.getOverallGrade() + "%");
        grade.setFont(font);
        grade.setAlignmentX(Component.CENTER_ALIGNMENT);
        return grade;
    }
}


