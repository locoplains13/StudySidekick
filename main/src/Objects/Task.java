package Objects;

import java.io.Serializable;
import java.util.Date;

public class Task implements Serializable {

    private static final long serialVersionUID = 1L;

    protected boolean isFinished;
    /**
     * User will choose from a list of task types or choose other and write in
     */
    protected String taskName;

    /**
     * User will choose from a list of task types or choose other and write in
     */
    protected String taskType;

    /**
     * User will choose a course name from list of course names or write in
     */
    protected String courseName;

    /**
     * User can choose a date from a calender for due date
     */
    protected Date dueDate;
    /**
     * User can choose to enter how long the task should take them to complete
     */
    protected Integer[] estimatedLength;


    /**
     * Time spent on task thus far in minutes or seconds
     */
    protected long timeSpentThusFar;

    /**
     * Open text area for user to type whatever they like.
     */
    protected String comments;

    public Task(String taskName, String taskType, String courseName, Date dueDate, Integer[] estimatedTimeToComplete, String comments){
        this.taskName = taskName;
        this.taskType = taskType;
        this.courseName = courseName;
        this.dueDate = dueDate;
        this.estimatedLength = estimatedTimeToComplete;
        this.comments = comments;
        this.setUnfinished();
    }

    /**
     * Add time to the time spent on task
     * @param time the new amount of time to add on
     */
    public void setTimeSpentThusFar(long time){
        this.timeSpentThusFar+=time;
        TaskDB.getInstance().saveEditedTask();
    }

    /**
     * Return the total amount of time spent on task
     * @return the time spent so far
     */
    public long getTimeSpentThusFar(){
        return this.timeSpentThusFar;
    }

    /**
     * Method to return the task due date
     * @return due date of task
     */
    public Date getDueDate(){
        return this.dueDate;
    }
    /**
     * Change the due date of the task
     */
    public void setDueDate(Date dueDate){
       this.dueDate = dueDate;
    }

    /**
     * Return the estimated time to finish task
     * @return estimated time to finish
     */
    public Integer[] getEstimatedTimeToComplete(){
        return this.estimatedLength;
    }

    /**
     * Change the estimated amount of time to finish task
     */
    public void setEstimatedTimeToComplete(Integer[] estimatedTimeToComplete){
        this.estimatedLength = estimatedTimeToComplete;
    }

    /**
     * Method to return the name of the task
     * @return name of the task
     */
    public String getTaskName(){
        return this.taskName;
    }

    /**
     * change the name of the task
     */
    public void setTaskName(String taskName){
        this.taskName = taskName;
    }

    /**
     * Method to return the name of the course the task is assigned too
     * @return name of course the task is for
     */
    public String getCourseName(){
        return this.courseName;
    }

    /**
     * Change the course of the task
     */
    public void setCourseName(String courseName){
        this.courseName = courseName;
    }

    /**
     * Method to return the comments on the task
     * @return comments made on the task
     */
    public String getComments(){
        return this.comments;
    }

    /**
     * Change the comments on the task
     */
    public void setComments(String comments){
        this.comments = comments;
    }

    /**
     * Method to return the type of task
     * @return type of task
     */
    public String getTaskType(){
        return this.taskType;
    }

    /**
     * Change the type of task
     */
    public void setTaskType(String taskType){
        this.taskType = taskType;
    }

    public void setFinished(){
        this.isFinished = true;
    }

    public void setUnfinished(){
        this.isFinished = false;
    }

    public boolean isFinished(){
        return this.isFinished;
    }


    public int compareTo(Task t) {
        return this.taskName.compareTo(t.taskName);
    }
}
