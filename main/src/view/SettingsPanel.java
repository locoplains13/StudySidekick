package view;

import Objects.RoundButton;
import Objects.Settings;
import control.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static model.AddComponentsToPanel.addComponentsToPanel;

public class SettingsPanel extends ViewPanel{

    public SettingsPanel(ActionListener listener){
        Component[] components;


//////////////////////////////////////////////////////////////////////////////////////////////

        JPanel jPanelDisplaySettings = new JPanel();
        jPanelDisplaySettings.setBackground(Color.white);
        jPanelDisplaySettings.setLayout(new BoxLayout(jPanelDisplaySettings, BoxLayout.PAGE_AXIS));

        JLabel jLabelNotify = new JLabel("Get Break Notifications: " +
                Settings.getInstance().getSettings().get(0));
        jLabelNotify.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel jLabelTimeUntilNotification = new JLabel("Amount of Time Until Break Notification: "+
                Settings.getInstance().getSettings().get(1)+" minutes");
        jLabelTimeUntilNotification.setAlignmentX(Component.CENTER_ALIGNMENT);

        RoundButton buttonEditSettings = new RoundButton("Edit Settings");
        buttonEditSettings.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonEditSettings.setBackground(new Color(179, 217, 255));
        buttonEditSettings.setForeground(Color.white);

        components = new Component[]{jLabelNotify,Box.createRigidArea(new Dimension(0, 20)),
                jLabelTimeUntilNotification,Box.createRigidArea(new Dimension(0, 20)),buttonEditSettings};
        addComponentsToPanel(jPanelDisplaySettings, components);



//////////////////////////////////////////////////////////////////////////////////////////////

        JPanel jPanelEditSettings = new JPanel();

        // set initial conditions for jPanelEditSettings
        jPanelEditSettings.setVisible(false);
        jPanelEditSettings.setBackground(Color.white);
        jPanelEditSettings.setLayout(new BoxLayout(jPanelEditSettings, BoxLayout.PAGE_AXIS));

        // create components for jPanelEditSettings
        JLabel jLabelBreakNotification = new JLabel("Would you like to be notified to take a break when working on a task?");
        jLabelBreakNotification.setAlignmentX(Component.CENTER_ALIGNMENT);

        JComboBox<String> jComboBoxNotify = new JComboBox<>(new String[]{"Yes","No"});
        jComboBoxNotify.setAlignmentX(Component.CENTER_ALIGNMENT);
        jComboBoxNotify.setSelectedItem((Settings.getInstance().getSettings().get(0)));


        JLabel jLabelBreakNotificationTime = new JLabel("After how long would you like to be notified?");
        jLabelBreakNotificationTime.setAlignmentX(Component.CENTER_ALIGNMENT);

        JComboBox<Long> jComboBoxNotifyTime = new JComboBox<>(new Long[]{Long.valueOf(5), Long.valueOf(10),
                Long.valueOf(15), Long.valueOf(20), Long.valueOf(25), Long.valueOf(30),
                Long.valueOf(35), Long.valueOf(40), Long.valueOf(45), Long.valueOf(50), Long.valueOf(55), Long.valueOf(60)});

        jComboBoxNotifyTime.setAlignmentX(Component.CENTER_ALIGNMENT);
        jComboBoxNotifyTime.setSelectedItem(Long.valueOf(Settings.getInstance().getSettings().get(1)));


        RoundButton buttonSubmit = new RoundButton("Submit");
        buttonSubmit.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonSubmit.setBackground(new Color(179, 217, 255));
        buttonSubmit.setForeground(Color.white);
        buttonSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Settings.getInstance().saveSettings(0, (String)jComboBoxNotify.getSelectedItem());
                Settings.getInstance().saveSettings(1, String.valueOf(jComboBoxNotifyTime.getSelectedItem()));
                MainPanel.setMainPanel(new SettingsPanel(listener));
                Controller.getView().showMainView(listener);
            }
        });

        // add components to jPanelEditSettings
        components = new Component[]{jLabelBreakNotification,jComboBoxNotify,
                jLabelBreakNotificationTime,jComboBoxNotifyTime,buttonSubmit};
        addComponentsToPanel(jPanelEditSettings, components);

//////////////////////////////////////////////////////////////////////////////////////////////

        // set initial conditions for this
        setBackground(Color.white);
        adjustButtonColor();
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        BoxLayout layout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
        setLayout(layout);
        setSize(new Dimension(getWidth(),250));

        // add components to this
        components = new Component[]{jPanelDisplaySettings,jPanelEditSettings};
        addComponentsToPanel(this, components);

        // allow settings to be edited when button clicked
        buttonEditSettings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jPanelDisplaySettings.setVisible(false);
                jPanelEditSettings.setVisible(true);
            }
        });

//////////////////////////////////////////////////////////////////////////////////////////////
    }

    private void adjustButtonColor(){
        MainSidePanel.getJButton("Courses").setForeground(Color.white);
        MainSidePanel.getJButton("Tasks").setForeground(Color.white);
        MainSidePanel.getJButton("Dashboard").setForeground(Color.white);
        MainSidePanel.getJButton("Settings").setForeground(new Color(179, 217, 255));
    }




}
