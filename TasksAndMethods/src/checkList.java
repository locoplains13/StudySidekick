import java.util.TreeMap;

public class checkList extends taskDB{
    /**
     * Dictionary for Patients
     */
    private static TreeMap<Integer, Task> dictionary;
    /**
     * Private constructor to ensure that no instance of this class
     is created.
     */
    protected checkList(){}
    /**
     * Return the dictionary that maps health card numbers to patient
     *
     * @return the dictionary that maps health card numbers to patient
     */
    public static TreeMap<Integer, Task> getInstance(){
        if (dictionary == null){
            dictionary = new TreeMap<Integer,Task>();
        }
        return dictionary;
    }
}
