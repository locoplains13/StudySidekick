package model;

import Objects.Task;
import Objects.TaskDB;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;

public class prioritizeTasks implements Serializable {
    /**
     * takes in instance of taskDB or CheckList
     */
    /**
     * assigns priority key number to task being added based on due date
     */
    /**
     * changes keys of other tasks which priority has changed
     */
    public Task task;

    public Date dueDate;

    /**
     * The instance variable for either taskDB or checkList
     */
    private static LinkedList<Task> storage;


    /**
     * Takes in a task, prioritizes the task, and adds it to storage
     * @param task - the task created from the addTaskFrame

     */
    public prioritizeTasks(Task task){
        this.task = task;
        this.dueDate = task.getDueDate();
        this.storage = TaskDB.getInstance().getTasks();
        prioritize();

    }

    /**
     * Finds priority of task and adds it to storage
     */
    public void prioritize() {
        if (storage.size() == 0){
            storage.add(0, this.task);
            return;
        }
        // find where task should be in regards to other tasks in storage
        int index = checkPriority();

        // if index is greater than size of storage, then place task at the bottom
        if (index > storage.size()){
            storage.add(storage.size(),task);
        }
        else{
            // input task at index
            storage.add(index,task);
        }
    }

    /**
     * Starting from the top of the storage container, go through each of the due dates
     * of the
     * @return
     */
    public int checkPriority(){
        int index = 0;
        while (storage.size() > index) {
            // if the year is the same for item to insert and item at current index
            if (storage.get(index).getDueDate().equals(this.dueDate)) {
                return index+1;
            }
            else if (storage.get(index).getDueDate().compareTo(this.dueDate) > 0) {
                    // move down an item in storage
                    return index;
                }
            else{
                index++;
            }
        }
        return index;
        }
        // if here then input task at the end of the taskDB


}
