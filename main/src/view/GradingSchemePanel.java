package view;

import Objects.Course;
import Objects.RoundButton;
import control.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static model.AddComponentsToPanel.addComponentsToPanel;

public class GradingSchemePanel extends ViewPanel {

    private Course course;

    GradingSchemePanel(ActionListener listener, Course course) {
        Component[] components;
        this.course = course;

//////////////////////////////////////////////////////////////////////////////////////////////
        // PANEL TO DISPLAY BUTTON TO ADD GRADING SCHEME OR DISPLAY SCHEME

        JPanel jPanelGradingScheme = new JPanel();

        // set initial conditions for jPanelGradingScheme
        jPanelGradingScheme.setBackground(Color.white);

        // create components for jPanelGradingScheme
        RoundButton buttonAddScheme = new RoundButton("Add Grading Scheme");
        buttonAddScheme.setBackground(new Color(179, 217, 255));
        buttonAddScheme.setPreferredSize(new Dimension(200,30));
        buttonAddScheme.setForeground(Color.white);
        buttonAddScheme.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainPanel.setMainPanel(new CreateGradingSchemePanel(listener, course));
                Controller.getView().showMainView(listener);
            }
        });

        // TODO allow user to modify grading scheme
        // if there is no grading scheme for class - show add grading scheme
        if (course.getGrades() == null){
            components = new Component[]{buttonAddScheme};
        }
        else{
            jPanelGradingScheme.setLayout(new GridLayout(course.getGrades().length, 3,15,15));
            components = addGradingScheme();

        }

        // add components to jPanelGradingScheme
        addComponentsToPanel(jPanelGradingScheme, components);

//////////////////////////////////////////////////////////////////////////////////////////////

        // set initial conditions for this
        setBackground(Color.white);
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        // create components for this
        JLabel jLabelGradingScheme = new JLabel("Grading Scheme");
        jLabelGradingScheme.setFont(new Font("Arial", Font.BOLD, 12));
        jLabelGradingScheme.setAlignmentX(Component.CENTER_ALIGNMENT);

        JScrollPane gradingSchemeScroll = new JScrollPane(jPanelGradingScheme);
        gradingSchemeScroll.setBorder(null);
        gradingSchemeScroll.setPreferredSize(new Dimension(200,150));
        gradingSchemeScroll.setSize(getPreferredSize());

        // add components to this
        components = new Component[]{Box.createRigidArea(new Dimension(0, 20)),
                jLabelGradingScheme,Box.createRigidArea(new Dimension(0, 20)),gradingSchemeScroll};
        addComponentsToPanel(this, components);

//////////////////////////////////////////////////////////////////////////////////////////////
    }

    /**
     * Creates list of components with the data from the grading scheme of the course
     * to be displayed
     */
    public Component[] addGradingScheme(){
        Component[] components = new Component[course.getGrades().length*3];
        JLabel jLabelNumber;
        JLabel jLabelType;
        JLabel jLabelWeight;
        for (int i = 0; i < course.getGrades().length*3; i+=3){
            jLabelNumber = new JLabel(course.getGrades()[i/3].getGrades().length+"");
            jLabelType = new JLabel(course.getGrades()[i/3].typeOfItems);
            jLabelWeight = new JLabel(course.getGrades()[i/3].finalWeight+"%");
            jLabelNumber.setHorizontalAlignment(SwingConstants.CENTER);
            jLabelType.setHorizontalAlignment(SwingConstants.CENTER);
            jLabelWeight.setHorizontalAlignment(SwingConstants.CENTER);
            components[i] = jLabelNumber;
            components[i+1] = jLabelType;
            components[i+2] = jLabelWeight;
        }
        return components;
    }
}

