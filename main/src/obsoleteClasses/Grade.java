/*
Carson Daly cjd201
CMPT 370 milestone 5
March 7 2021
 */
package obsoleteClasses;


import java.io.Serializable;

public class Grade implements Serializable{

    //give the mark as a percentage
    protected float earnedMark;

    //give the grades weight as a percentage
    protected float weight;


    //User will choose a course name from list of course names or write in
    protected String descriptor;

    /**
     * Constructor for Grade class
     *
     * @param earnedMark the mark % received on task
     */
    public Grade(float earnedMark, String descriptor){
        this.earnedMark = earnedMark;
        this.descriptor = descriptor;
    }

    /**
     * Method to return the current earned grade
     * @return current earned grade
     */
    public float getEarnedMark(){
        return this.earnedMark;
    }

    /**
     * Method to get the total course weight of the current grade
     * @return total course weight of current grade
     */
    public float getWeight(){
        return this.weight;
    }

    /**
     * Method to set the current earned grade
     * @param mark current earned grade
     */
    public void setEarnedMark(float mark){
        this.earnedMark=mark;
    }

    /**
     * Method to set the total course weight of the current grade
     * @param weight total course weight of the current grade
     */
    public void setWeight(float weight){
        this.earnedMark=weight;
    }

}
