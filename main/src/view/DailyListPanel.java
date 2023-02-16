package view;

import Objects.DailyCheckList;
import Objects.RoundButton;
import Objects.Task;
import Objects.TaskDB;
import control.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.LinkedList;

import static model.AddComponentsToPanel.addComponentsToPanel;

public class DailyListPanel extends ViewPanel implements Serializable {
    static final int FONT_SIZE = 20;
    LinkedList<Task> tasks = TaskDB.getInstance().getTasks();

    /**
     * Create the view tasks panel and direct user input from the ActionListener
     *
     * @param listener the class listening for the event that signals the button was pressed
     */
    public DailyListPanel(ActionListener listener) {


//////////////////////////////////////////////////////////////////////////////////////////////

        // set initial conditions for this

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // create components for this
        JLabel jLabelTaskList = new JLabel("Daily CheckList");
        jLabelTaskList.setFont(new Font("Arial", Font.BOLD, 30));
        jLabelTaskList.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel jPanelTasks = new JPanel();
        jPanelTasks.setBackground(Color.white);

        DailyCheckList dailyList = DailyCheckList.getInstance();                    ///////////////////////////////
        dailyList.updateDailyList();
        LinkedList<Task> list = dailyList.getDailyList();

        GridLayout layout = new GridLayout(list.size(), 4);
        jPanelTasks.setLayout(layout);
        JScrollPane jScrollPaneTasks = new JScrollPane(jPanelTasks);
        jScrollPaneTasks.setBorder(null);


//        System.out.print(list.get(0).getTaskName() + " ");
//        System.out.print(list.get(0).getDueDate() + " ");
//        System.out.print(list.get(0).getCourseName() + " ");
//        System.out.println(list.get(0).isFinished());


        addTasks(jPanelTasks, listener, list);

        setBackground(Color.white);
        setOpaque(true);
        // add components to this
        Component[] components = {jLabelTaskList, Box.createRigidArea(new Dimension(0, 20)),
                jScrollPaneTasks, Box.createRigidArea(new Dimension(0, 10)),
                Box.createRigidArea(new Dimension(0, 10))};addComponentsToPanel(this, components);

//////////////////////////////////////////////////////////////////////////////////////////////
    }

    /**
     * Method to add tasks to the hashmap data structure, and populate the tasks panel
     *
     * @param panel the panel into which the items are to be added
     */
    private void addTasks(JPanel panel, ActionListener listener, LinkedList<Task> list) {
        Font font = new Font("Arial", Font.BOLD, 16);
        JButton id;
        setOpaque(false);

        for (int i = 0; i < list.size(); i++) {
            id = new JButton(list.get(i).getTaskName());
            id.setFocusPainted(false);
            id.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            id.setMargin(new Insets(0, 0, 0, 0));
            id.setContentAreaFilled(false);
            id.setBorderPainted(false);
            id.setFont(font);
            int finalI = i;
            id.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    MainPanel.setMainPanel(new TaskPanel(listener, list.get(finalI)));
                    Controller.getView().showMainView(listener);
                }
            });
            panel.add(id);
            SimpleDateFormat format = new SimpleDateFormat("MMMM d, yyyy");
            String dueDate = format.format(list.get(i).getDueDate());

            panel.add(new JLabel("Due: " + dueDate));
            panel.add(new JLabel("     Course: " + list.get(i).getCourseName() + "     "));

            RoundButton buttonStartTask = new RoundButton("Start Task");
            buttonStartTask.setBackground(new Color(179, 217, 255));
            buttonStartTask.setForeground(Color.white);

            buttonStartTask.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Controller.getView().showTaskTimerView(listener, list.get(finalI));
                }
            });
            buttonStartTask.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            panel.add(buttonStartTask);
            }
        }
        // TODO Could find a way for the tasks to also be prioritized by the estimated time to complete
        // TODO and how much time left until the due date

        // TODO Could be an option to 'prioritize task' in the TaskPanel that would move task to the top of TaskDB
    }



