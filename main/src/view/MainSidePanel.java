package view;

import control.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static model.AddComponentsToPanel.addComponentsToPanel;

public class MainSidePanel extends ViewPanel{
    static final int FONT_SIZE = 25;
    static final int BUTTON_WIDTH = 140;
    static final int BUTTON_HEIGHT = 30;

    private static JButton jButtonDashboard;
    private static JButton jButtonCourses;
    private static JButton jButtonTaskDB;
    private static JButton jButtonSettings;


    public MainSidePanel(ActionListener listener) {

        Component[] components;

//////////////////////////////////////////////////////////////////////////////////////////////

        JPanel jPanelCredits = new JPanel();

        // set initial conditions for jPanelCredits
        jPanelCredits.setBackground(new Color(138, 138, 138));
        jPanelCredits.setAlignmentX(Component.LEFT_ALIGNMENT);
        jPanelCredits.setLayout(new BorderLayout());

        // create components for jPanelCredits
        JLabel jLabelCredits = new JLabel("All copyrights @Team Delta");
        jLabelCredits.setHorizontalAlignment(SwingConstants.CENTER);
        jLabelCredits.setFont(new Font("Copperplate", Font.BOLD, 10));

        // add components to jPanelCredits
        jPanelCredits.add(jLabelCredits,BorderLayout.PAGE_END);

//////////////////////////////////////////////////////////////////////////////////////////////

        // set inital conditions for this
        setBackground(new Color(138, 138, 138));
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBorder(BorderFactory.createEmptyBorder(15,15,15,15));

        // create components for this

        JLabel jLabelWelcome = new JLabel("WELCOME");
        jLabelWelcome.setAlignmentX(Component.LEFT_ALIGNMENT);
        jLabelWelcome.setForeground(Color.white);
        jLabelWelcome.setFont(new Font("Copperplate", Font.BOLD, FONT_SIZE));

        getJButton("Dashboard").addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                MainPanel.setMainPanel(new DashboardPanel(listener));
                Controller.getView().showMainView(listener);
            }
        });
        getJButton("Courses").addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                MainPanel.setMainPanel(new CourseListPanel(listener));
                Controller.getView().showMainView(listener);
            }
        });
        getJButton("Tasks").addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                MainPanel.setMainPanel(new TaskDBPanel(listener));
                Controller.getView().showMainView(listener);
            }
        });
        getJButton("Settings").addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                MainPanel.setMainPanel(new SettingsPanel(listener));
                Controller.getView().showMainView(listener);
            }
        });

        JButton jButtonQuit = new JButton("Quit");
        jButtonQuit.addActionListener(listener);
        jButtonQuit.setActionCommand("quit");
        makeButtonConditions(jButtonQuit);

        // add components to this
        components = new Component[]{jLabelWelcome,
                Box.createRigidArea(new Dimension(0, 20)), getJButton("Dashboard"),
                getJButton("Courses"),getJButton("Tasks"),getJButton("Settings"),jButtonQuit,jPanelCredits};
        addComponentsToPanel(this, components);

//////////////////////////////////////////////////////////////////////////////////////////////

    }

    private static void makeButtonConditions(JButton button){
        button.setSize(BUTTON_WIDTH,BUTTON_HEIGHT);
        button.setFont(new Font("Copperplate", Font.BOLD, 20));
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        button.setForeground(Color.white);
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setMargin(new Insets(0, 0, 0, 0));
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
    }

    public static JButton getJButton(String type){
        if (type.compareTo("Dashboard")==0){
            if (jButtonDashboard == null) {
                jButtonDashboard = new JButton("Dashboard");
                makeButtonConditions(jButtonDashboard);
            }

            return jButtonDashboard;

        }
        else if (type.compareTo("Courses")==0){
            if (jButtonCourses == null){
                jButtonCourses = new JButton("Courses");
                makeButtonConditions(jButtonCourses);
            }

            return jButtonCourses;

        }
        else if (type.compareTo("Tasks")==0){
            if (jButtonTaskDB == null){
                jButtonTaskDB = new JButton("Tasks");
                makeButtonConditions(jButtonTaskDB);
            }
            return jButtonTaskDB;
        }
        else{
            if (jButtonSettings == null){
                jButtonSettings = new JButton("Settings");
                makeButtonConditions(jButtonSettings);
            }
            return jButtonSettings;
        }
    }
}