public class addTask{
    /**
     * Create the frame to get user input for the task data
     * @param storeWhere - place to put task after creation - "taskDB" or "checklist"
     */
    public void execute(String storeWhere){
        new addTaskFrame(storeWhere).setVisible(true);
    }
}
