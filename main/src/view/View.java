package view;

import Objects.Task;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class View extends JFrame{

    public static final int TITLE_BAR_HEIGHT = 32;
    public static final int BORDER_WIDTH = 6;

    /**
     * Initialize the frame for the various views to be inserted into it, where width and height are
     * the dimensions for the game.
     */
    public View(int width, int height) {
        setTitle("Study Sidekick");
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));

        /*
         * Width and height passed in are those for the game panel, so add enough height for the
         * title bar and the information panel, and enough width for the border.
         */
        setSize(width + BORDER_WIDTH, height + TITLE_BAR_HEIGHT);
        setResizable(true);
        getContentPane().setLayout(new BorderLayout());
    }
    /**
     * Set the frame visible showing the panel passed in, and set the focus in the Component
     * obtained from the panel.
     *
     * @param panel the panel to be shown in the view
     */
    public void displayPanel(ViewPanel panel) {
        getContentPane().removeAll();
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().validate();
        Component focusComponent = panel.getFocusComponent();
        if (focusComponent != null)
            focusComponent.requestFocusInWindow();
        setVisible(true);
    }

    /**
     * Create the main frame that is used when the application is launched or when a user returns to home screen
     * and make it visible.
     * @param actionListener the listener for user input
     */
    public void showMainView(ActionListener actionListener) {
        ViewPanel panel = new MainPanel(getWidth(), getHeight(), actionListener);
        displayPanel(panel);
    }

    /**
     * Create the AddCourse panel and make it visible
     * @param actionListener the listener for user input
     */
    public void showAddCourseView(ActionListener actionListener) {
        ViewPanel panel = new AddCoursePanel(actionListener);
        displayPanel(panel);
    }

    /**
     * Create the TaskList panel and make it visible
     * @param actionListener the listener for user input
     */
    public void showTaskListView(ActionListener actionListener) {
        ViewPanel panel = new TaskDBPanel(actionListener);
        displayPanel(panel);
    }

    /**
     * Create the AddTask panel and make it visible
     * @param actionListener the listener for user input
     */
    public void showAddEditTaskView(ActionListener actionListener, Task task) {
        ViewPanel panel = new AddEditTaskPanel(actionListener, task);
        displayPanel(panel);
    }

    /**
     * Create a section to display the month and the current date
     * @param actionListener
     */
    public void showCalendar(ActionListener actionListener){
        ViewPanel panel = new CalendarPanel(2,2021);
        displayPanel(panel);
    }

    /**
     * Create a new frame for timer, create TimerPanel and then set visible
     * @param actionListener the listener for user input
     */
    public void showTaskTimerView(ActionListener actionListener, Task task) {
        View taskTimer = new View(450,75);
        taskTimer.setTitle("Task Timer: "+task.getCourseName()+" - "+task.getTaskName());
        ViewPanel panel = new TimerPanel(450,75,actionListener, task, taskTimer);
        taskTimer.displayPanel(panel);
    }

    /**
     * Create a page for specific task
     * @param actionListener the listener for user input
     * @param task the specific task for the page
     */
    public void showTaskView(ActionListener actionListener, Task task){
        ViewPanel panel = new TaskPanel(actionListener, task);
        this.displayPanel(panel);
    }


}

