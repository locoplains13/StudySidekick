package control;

import view.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//import util.PropertiesDiskStorage;

/**
 * Controller class to start the application. Controller is used to control the actions of the application such as
 * opening/closing frames and using the ActionListener to direct user input
 */
public class Controller implements ActionListener {

    public static final int WIDTH = 1200;
    public static final int HEIGHT = 600;

    private static View view;

    /**
     * Method to start the application and open the main frame of the application
     */
    public void start() {
        view = new View(WIDTH, HEIGHT);
        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view.showMainView(this); // this is passed in as an ActionListener
    }

    /**
     * Method to return the system's main view frame
     * @return the one occurance of view
     */
    public static View getView(){ // **METHOD IS NOT IMPLEMENTED**
        return view;
    }

    /**
     * Direct the user input taken from the ActionListener to the correct reaction
     * Example: Opening the correct frame when the user clicks a button
     * @param event the action event recorded by the ActionListener
     */
    public void actionPerformed(ActionEvent event) {
        String actionCommand = event.getActionCommand();

        if (actionCommand.equals("main")) {
            view.setVisible(false);
            view.showMainView(this);
            view.setVisible(true);
        }
        else if (actionCommand.equals("addCourse")){
            view.showAddCourseView(this);
        }
        else if (actionCommand.equals("addTask")){
            view.showAddEditTaskView(this,null);
        }
        else if (actionCommand.equals("submitTask")){
            view.showTaskListView(this);
        }
        else if(actionCommand.equals("calendar")){
            view.showCalendar(this);
        }
        else if (actionCommand.equals("quit")) {
            System.exit(0);
        }

    }


}
