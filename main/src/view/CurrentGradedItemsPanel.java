package view;

import Objects.Course;
import Objects.RelatedGrades;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CurrentGradedItemsPanel extends ViewPanel{

    public CurrentGradedItemsPanel(int width, int height, ActionListener listener, Course course){

//////////////////////////////////////////////////////////////////////////////////////////////
        setSize(width,height);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.white);
        JLabel jLabelGrade;
        if (course.getGrades()!=null){
        for (RelatedGrades grades : course.getGrades()){
            for (int i = 0; i < grades.getGrades().length; i++){
                if (grades.getGrades()[i]!=0.0){
                jLabelGrade = new JLabel(grades.typeOfItems+" "+(i+1)
                        +"  "+grades.getGrades()[i]+"%");
                add(jLabelGrade);
                jLabelGrade.setAlignmentX(Component.CENTER_ALIGNMENT);
                }
            }
        }
        // TODO allow users to delete or modify a grade they have entered

            add(Box.createRigidArea(new Dimension(0, 10)));
//////////////////////////////////////////////////////////////////////////////////////////////

        }
    }
}
