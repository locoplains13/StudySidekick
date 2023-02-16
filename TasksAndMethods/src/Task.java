import javax.swing.*;

public class Task {

    String[] month ={ "January", "February", "March", "April", "May", "June", "July", "August","September","October","November", "December" };

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
    protected Integer[] dueDate;

    /**
     * User can choose to enter how long the task should take them to complete
     */
    protected Integer[] estimatedLength;


    /**
     * Time spent on task thus far in minutes or seconds
     */
    protected int timeSpentThusFar;

    /**
     * Open text are for user to type whatever they like.
     */
    protected String comments;

    public Task(String taskName, String taskType, String courseName,Integer[] dueDate, Integer[] estimatedLength, String comments){
        this.taskName = taskName;
        this.taskType = taskType;
        this.courseName = courseName;
        this.dueDate = dueDate;
        this.estimatedLength = estimatedLength;
        this.comments = comments;
    }

    public String toString(){

        return "Task name: "+ taskName + "\nTask Type: "+ taskType+ "\nFor Course: "+courseName +"\nDue Date: " + month[dueDate[0]]+ " "+ dueDate[1]+ ", "+ dueDate[2] +"\nEstimated Length: "+ estimatedLength[0]+" days " + estimatedLength[1]+ " hours " + estimatedLength[2] +" minutes " +"\nOther information:\n " + comments;
    }

}
