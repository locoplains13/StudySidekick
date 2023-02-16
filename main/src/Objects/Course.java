package Objects;

import obsoleteClasses.Grade;

import java.io.Serializable;
import java.util.LinkedList;

public class Course implements Serializable {
    public static final long serialVersionUID = 1L;

    /**
     * The identifier for the player who obtained the game result.
     */
    protected String courseName;

    //current overall course grade
    protected float overallGrade;

    //list of grades that are a part of the course
    protected LinkedList<Grade> gradeList;

    //list of finished tasks that are a part of the course
    protected LinkedList<Task> finishedTasks;

    protected RelatedGrades[] grades = null;

    /**
     * Initialize this instance with the argument values.
     *
     * @param courseName the id of the player
     */
    public Course(String courseName) {
        this.courseName = courseName;
        this.overallGrade = 0;
        this.gradeList = null;
        this.finishedTasks = new LinkedList<>();
    }


    /**
     * Set the grading scheme from the course syllabus
     * @param grades array of all grades and graded information for course
     */
    public void setGradingScheme(RelatedGrades[] grades){
        this.grades = grades;
        CourseList.getInstance().saveCourseList();
    }


    public LinkedList<Task> getFinishedTasks(){
        return this.finishedTasks;
    }

    public void removeFinishedTask(Task task){
        finishedTasks.remove(task);
        CourseList.getInstance().saveCourseList();
    }

    public void addFinishedTask(Task task){
        task.setFinished();
        finishedTasks.add(task);
        CourseList.getInstance().saveCourseList();
    }

    /**
     * Returns the name of the course
     * @return the course name
     */
    public String getCourseName() {
        return courseName;
    }



    /**
     * Calculates the current
     * @return
     */
    public void getCurrentGrade(){
        float currentWeightPercents = 0.0f;
        float totalWeightPercents = 0.0f;
        for ( int i = 0; i < grades.length; i++){
            if (grades[i].count!=0){
            currentWeightPercents+= grades[i].currentGrade()*grades[i].currentWeight();
            totalWeightPercents+=grades[i].currentWeight();
            }
        }

        overallGrade = currentWeightPercents/totalWeightPercents;
    }
    /**
     * Returns the current overall grade
     * @return the current overall grade
     */
    public float getOverallGrade(){
        return overallGrade;
    }


    public RelatedGrades[] getGrades(){
        return this.grades;
    }
    /**
     * Creates a string version of the course to easily print
     * @return string version of course
     */
    @Override
    public String toString() {
        return courseName;
    }

}
