package Objects;

import java.io.*;
import java.util.LinkedList;
import java.io.Serializable;

/**
 * CourseList class to hold the list of courses added by the user
 */
public class CourseList implements Serializable{
    /**
     * The maximum number of courses to be saved.
     */
    public static final int MAX_NUMBER_COURSES_SAVED = 10;

    /**
     * The name of the file that stores the courses
     */
    public static final String COURSES_FILE_NAME = "courses.ser";

    public static final long serialVersionUID = 1L;

    /**
     * The sole instance of this class.
     */
    private static CourseList instance = null;

    /**
     * @return the sole instance of this class
     */
    public static CourseList getInstance() {
        if (instance == null) {
            instance = new CourseList();
        }

        return instance;
    }

    /**
     * The list that stores the courses
     */
    public LinkedList<Course> courses;

    /**
     * Read in the courses from disk and store them in the listed list courses.
     */
    @SuppressWarnings("unchecked")
    private CourseList() {
        FileInputStream fileIn = null;
        ObjectInputStream objectIn = null;

        try {
            fileIn = new FileInputStream(COURSES_FILE_NAME);
            objectIn = new ObjectInputStream(fileIn);
            courses = (LinkedList<Course>) objectIn.readObject();
            objectIn.close();
        } catch (FileNotFoundException fnfe) {
            /*
             * The file isn't found so this is the first play of the game, and no previous results
             * exist.
             */
            courses = new LinkedList<>();
        } catch (IOException ioe) {
            /*
             * This should not happen unless the file name is invalid, but continue with no previous
             * results.
             */
            ioe.printStackTrace();
            courses = new LinkedList<>();
        } catch (ClassNotFoundException cnfe) {
            /*
             * This should not happen unless file name is invalid, but continue with no previous
             * results.
             */
            cnfe.printStackTrace();
            courses = new LinkedList<>();
        }
    }

    /**
     * Method to return the course list
     * @return LinkedList containing list of courses
     */
    public LinkedList<Course> getCourses(){

        return courses;
    }

    /**
     * Method to add a new course to the list
     * @param newCourse new course to be added to the list
     */
    public void saveCourseList(Course newCourse) {
        if (courses.size() != MAX_NUMBER_COURSES_SAVED){
            courses.add(newCourse);
        }

        save();
    }

    public void saveCourseList(){
        save();
    }

    /**
     * Method to remove a course from the course list
     * @param course the course to be removed from the course list
     */
    public void removeCourse(Course course){
        if (courses.size() != 0){
            courses.remove(course);
        }
        save();
    }

    /**
     * Method to return boolean indicator if the given course name already exists in the course list
     * @param courseName name of course being searched
     * @return true if found, false otherwise
     */
    public boolean containsCourse(String courseName){

        for (int counter = 0; counter < courses.size(); counter++){
            if(courses.get(counter).getCourseName().equalsIgnoreCase(courseName)){
                return true;
            }
        }

        return false;
    }

    /**
     * Save the LinkedList of courses to disk
     */
    private void save() {
        try
        {
            FileOutputStream fileOut = new FileOutputStream(COURSES_FILE_NAME);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(courses);
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
