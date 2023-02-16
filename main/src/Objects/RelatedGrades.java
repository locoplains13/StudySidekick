package Objects;

import java.io.Serializable;

/**
 * This class is for keeping track of related items and their grades such as
 * assignments, midterms, quizzes, participation marks. Anything that
 * takes a specific weight of final grade.
 */
public class RelatedGrades implements Serializable {

    /**
     * The weight towards the final mark
     */
    public float finalWeight;

    /**
     * The amount of items that count towards the weight
     */
    public int count;

    /**
     * Descriptor for the item(s)
     * Ex. assignments
     */
    public String typeOfItems;

    /**
     * Array of grades for each individual item that counts towards
     * finalWeight
     */
    private final float[] grades;


    public RelatedGrades(String typeOfItems, float finalWeight, int count){
        this.finalWeight = finalWeight;
        this.typeOfItems = typeOfItems;
        grades = new float[count];
        this.count = 0;

    }

    /**
     * Add grade to the list of grades
     * @param grade the grade to add
     */
    public void addGrade(float grade){
        grades[count] = grade;
        count++;
    }

    /**
     * Get the hashmap of grades
     * @return hashmap of grades
     */
    public float[] getGrades(){
        return grades;
    }

    /**
     * Calculate and return the current grade for these items
     * @return current grade
     */
    public float currentGrade(){
        float totalMarks = 0.0f;
        for (int i = 0; i < count; i ++){
            totalMarks+= grades[i];
        }

        return totalMarks/(count);
    }

    /**
     * Return the amount of weight currently graded
     * @return the percentage of weight for these items that already have a grade
     */
    public float currentWeight(){
        return ((this.finalWeight)/grades.length)*this.count;
    }

}
