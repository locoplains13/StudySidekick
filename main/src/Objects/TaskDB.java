package Objects;

import model.prioritizeTasks;

import java.io.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.LinkedList;


public class TaskDB implements Serializable {

    public static final String TASKS_FILE_NAME = "tasks.ser";

    public static final long serialVersionUID = 1L;

    /**
     * The sole instance of this class.
     */
    private static TaskDB instance = null;

    /**
     * Dictionary for tasks
     */
    public static LinkedList<Task> tasks;

    /**
     * Return the tasks instance
     *
     * @return tasks instance
     */
    public static TaskDB getInstance(){
        if (instance == null){
            instance = new TaskDB();
        }
        return instance;
    }


    private TaskDB() {
        FileInputStream fileIn = null;
        ObjectInputStream objectIn = null;

        try {
            fileIn = new FileInputStream(TASKS_FILE_NAME);
            objectIn = new ObjectInputStream(fileIn);
            tasks = (LinkedList<Task>) objectIn.readObject();
            objectIn.close();
        } catch (FileNotFoundException fnfe) {
            /*
             * The file isn't found so this is the first play of the game, and no previous results
             * exist.
             */
            tasks = new LinkedList<>();
        } catch (IOException ioe) {
            /*
             * This should not happen unless the file name is invalid, but continue with no previous
             * results.
             */
            ioe.printStackTrace();
            tasks = new LinkedList<>();
        } catch (ClassNotFoundException cnfe) {
            /*
             * This should not happen unless file name is invalid, but continue with no previous
             * results.
             */
            cnfe.printStackTrace();
            tasks = new LinkedList<>();
        }
    }

    /**
     * Remove task from the tasks hashmap
     * @param taskName the name of the task to be removed
     */
    public void removeTask(String taskName){

        if (tasks.size()==1){
            tasks.clear();
        }
        for (int i = 0; i < tasks.size(); i ++){
            if (tasks.get(i).getTaskName().compareTo(taskName)==0){
                tasks.remove(i);
            }
        }
        save();
    }
    /**
     * Returns tasks hashmap
     * @return returns tasks hash map
     */
    public LinkedList<Task> getTasks(){
        return tasks;
    }
    /**
     * Add result to tasks instance, and save the tasks to disk.
     *
     * @param newTask new task to be added
     */

    public void saveTask(Task newTask) {
        new prioritizeTasks(newTask);
        save();
    }

    /**
     * Save a task after it is edited
     */

    public void saveEditedTask() {
        save();
    }

    /**
     * Save the HashMap of tasks to disk.
     */
    private void save() {
        try {
            FileOutputStream fileOut = new FileOutputStream(TASKS_FILE_NAME);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(tasks);
            objectOut.close();
        }
        catch (FileNotFoundException fnfe) {
            /* This should not happen unless file name is invalid. */
            fnfe.printStackTrace();
        }
        catch (IOException ioe) {
            /* This should not happen unless file name is invalid. */
            ioe.printStackTrace();
        }
    }
}
