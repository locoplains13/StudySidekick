package view;

import Objects.Course;
import Objects.CourseList;
import control.Controller;
import Objects.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.Objects;

import static model.AddComponentsToPanel.addComponentsToPanel;

public class AddGradePanel extends ViewPanel implements Serializable{

    static final int FONT_SIZE = 32;

    public AddGradePanel(ActionListener listener, Course course) {
        Component[] components;

//////////////////////////////////////////////////////////////////////////////////////////////
        // PANEL FOR BUTTONS AT THE BOTTOM OF THIS

        JPanel jPanelBottomButtons = new JPanel();

        // set initial conditions for jPanelBottomButtons
        jPanelBottomButtons.setLayout(new BoxLayout(jPanelBottomButtons, BoxLayout.X_AXIS));
        jPanelBottomButtons.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 10));
        jPanelBottomButtons.setBackground(Color.white);

        // create components for jPanelBottomButtons
        JTextPane jTextPaneGradeValue = new JTextPane();
        jTextPaneGradeValue.setBorder(BorderFactory.createLineBorder(new Color(179, 217, 255)));
        jTextPaneGradeValue.setMaximumSize(new Dimension(150,25));
        jTextPaneGradeValue.setAlignmentX(Component.CENTER_ALIGNMENT);

        int num = 0;
        for (int i = 0; i < course.getGrades().length; i++){
            if (course.getGrades()[i].count< course.getGrades()[i].getGrades().length){
                num ++;
            }
        }
        String[] gradeTypes = new String[num];
        num = 0;
        for (int i = 0; i < course.getGrades().length; i++){
            if (course.getGrades()[i].count< course.getGrades()[i].getGrades().length){
                gradeTypes[num] = course.getGrades()[i].typeOfItems;
                num++;
            }
        }

        JComboBox<String> jComboBoxGradeTypes = new JComboBox<>(gradeTypes);
        jComboBoxGradeTypes.setAlignmentX(Component.CENTER_ALIGNMENT);
        jComboBoxGradeTypes.setMaximumSize(new Dimension(150,50));

        RoundButton addGradeButton = new RoundButton("Submit Grade");
        addGradeButton.setBackground(new Color(179, 217, 255));
        addGradeButton.setForeground(Color.white);
        addGradeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = 0;
                while (course.getGrades()[index].typeOfItems.compareTo((String) Objects.requireNonNull(jComboBoxGradeTypes.getSelectedItem()))!=0){
                    index++;
                }

                if (jTextPaneGradeValue.getText().isEmpty()){
                    jTextPaneGradeValue.setBorder(BorderFactory.createLineBorder(Color.RED));
                }
                else if(Float.parseFloat(jTextPaneGradeValue.getText())>100.0f){
                    jTextPaneGradeValue.setBorder(BorderFactory.createLineBorder(Color.RED));
                }
                else{
                    course.getGrades()[index].addGrade(Float.parseFloat(jTextPaneGradeValue.getText()));
                    course.getCurrentGrade();
                    CourseList.getInstance().saveCourseList();
                    MainPanel.setMainPanel(new CoursePagePanel(listener, course));
                    Controller.getView().showMainView(listener);
                }
            }
        });

        RoundButton backButton = new RoundButton("Back");
        backButton.setForeground(Color.white);
        backButton.setBackground(new Color(179, 217, 255));
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainPanel.setMainPanel(new CoursePagePanel(listener, course));
                Controller.getView().showMainView(listener);
            }
        });

        // add components to jPanelBottomButtons
        components = new Component[]{addGradeButton,
                Box.createRigidArea(new Dimension(20, 0)),backButton};
        addComponentsToPanel(jPanelBottomButtons, components);

//////////////////////////////////////////////////////////////////////////////////////////////

        // Set initial panel conditions
        setBackground(Color.white);
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        // create components for this
        JLabel jLabelAddGrade = new JLabel("Add Grade");
        jLabelAddGrade.setFont(new Font("Arial", Font.BOLD, FONT_SIZE));
        jLabelAddGrade.setForeground(Color.BLACK);
        jLabelAddGrade.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel jLabelGradeType = new JLabel("Select Grade Type:");
        jLabelGradeType.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel jLabelGradeValue = new JLabel("Enter Grade as a Percentage:");
        jLabelGradeValue.setAlignmentX(Component.CENTER_ALIGNMENT);

        // add components to this
        components = new Component[]{Box.createRigidArea(new Dimension(0, 10)),jLabelAddGrade,
                Box.createRigidArea(new Dimension(0, 15)),
                jLabelGradeType,jComboBoxGradeTypes,jLabelGradeValue,jTextPaneGradeValue,
                Box.createRigidArea(new Dimension(0, 15)),
                jPanelBottomButtons,Box.createRigidArea(new Dimension(0, 8))};
        addComponentsToPanel(this, components);

//////////////////////////////////////////////////////////////////////////////////////////////

    }


}
