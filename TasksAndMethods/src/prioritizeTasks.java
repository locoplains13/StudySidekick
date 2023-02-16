import java.util.TreeMap;

public class prioritizeTasks {
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
    Integer[] dueDate;

    /**
     * The instance variable for either taskDB or checkList
     */
    private static TreeMap<Integer, Task> storage;


    /**
     * Takes in a task, prioritizes the task, and adds it to storage
     * @param task - the task created from the addTaskFrame
     * @param storeWhere - the place where task will be stored ( taskDB, checklist etc.)
     */
    public prioritizeTasks(Task task, String storeWhere){
        this.task = task;
        dueDate = task.dueDate;
        if (storeWhere.compareTo("taskDB") == 0){
            storage = taskDB.getInstance();
        }
        else{
            storage = checkList.getInstance();
        }
        prioritze();
        System.out.print(storage.get(0));
    }

    /**
     * Finds priority of task and adds it to storage
     */
    public void prioritze() {
        if (storage.size() == 0){
            storage.put(0, this.task);
            return;
        }
        // find where task should be in regards to other tasks in storage
        int index = checkPriority();
        
        // if index is greater than size of storage, then place task at the bottom
        if (index > storage.size()){
            storage.put(storage.size(),task);
        }
        else{
            // start at the bottom of the list and work your way up
            for (int i = storage.size(); i > index; i--){
                // add one to the current key to 'shift' all tasks down 1
                    storage.put(i, storage.get(i-1));
                    // remove the old version of task with now wrong priority
                    storage.remove(i-1);
            }
            // input task at index
            storage.put(index,task);
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
            // if the month is the same for item to insert and item at current index
           if (storage.get(index).dueDate[0].equals(task.dueDate[0])) {
               // check the day
                if (storage.get(index).dueDate[1].equals(task.dueDate[1])) {
                    return index+1;
                    // put at index directly beneath shift everything down
                }
                // if the current index day is larger than the day for task to be inserted
                else if( storage.get(index).dueDate[1] > task.dueDate[1]){
                    // return index - this will be where it is inputted into the storage structure
                    return index;
                }
            }
           // if the month for the current index task is larger than month for task to input
            else if (storage.get(index).dueDate[0] > task.dueDate[0]) {
                return index;
            }
            else{
                // move down an item in storage
                index ++;
           }
        }
        // if here then input task at the end of the
        return index;
    }
}
