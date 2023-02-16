package view;

import org.jdesktop.swingx.JXMonthView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class DashboardPanel extends ViewPanel{

    public DashboardPanel(ActionListener listener){

        this.setBackground(Color.white);
        adjustButtonColor();


        JXMonthView calender = new JXMonthView(java.util.Calendar.getInstance().getTime());
        add(calender);

        JPanel jPanelChecklist = new DailyListPanel(listener);
        add(jPanelChecklist);

    }

    private void adjustButtonColor(){
        MainSidePanel.getJButton("Courses").setForeground(Color.white);
        MainSidePanel.getJButton("Tasks").setForeground(Color.white);
        MainSidePanel.getJButton("Settings").setForeground(Color.white);
        MainSidePanel.getJButton("Dashboard").setForeground(new Color(179, 217, 255));
    }
}
