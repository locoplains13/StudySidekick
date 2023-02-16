package Objects;

import java.io.*;
import java.util.LinkedList;

public class Settings implements Serializable {

    private static final String SETTINGS_FILE_NAME = "settings.ser";

    /**
     * The name of the file that stores the courses
     */

    public static final long serialVersionUID = 1L;

    /**
     * The sole instance of this class.
     */
    private static Settings instance = null;

    /**
     * @return the sole instance of this class
     */
    public static Settings getInstance() {
        if (instance == null) {
            instance = new Settings();
        }

        return instance;
    }

    /**
     * The list that stores the courses
     */
    public LinkedList<String> settings;

    /**
     * Read in the courses from disk and store them in the listed list courses.
     */
    @SuppressWarnings("unchecked")
    private Settings() {
        FileInputStream fileIn = null;
        ObjectInputStream objectIn = null;

        try {
            fileIn = new FileInputStream(SETTINGS_FILE_NAME);
            objectIn = new ObjectInputStream(fileIn);
            settings = (LinkedList<String>) objectIn.readObject();
            objectIn.close();
        } catch (FileNotFoundException fnfe) {
            /*
             * The file isn't found so this is the first play of the game, and no previous results
             * exist.
             */
            settings = new LinkedList<>();
        } catch (IOException ioe) {
            /*
             * This should not happen unless the file name is invalid, but continue with no previous
             * results.
             */
            ioe.printStackTrace();
            settings = new LinkedList<>();
        } catch (ClassNotFoundException cnfe) {
            /*
             * This should not happen unless file name is invalid, but continue with no previous
             * results.
             */
            cnfe.printStackTrace();
            settings = new LinkedList<>();
        }
    }

    /**
     * Method to return the course list
     * @return LinkedList containing list of courses
     */
    public LinkedList<String> getSettings(){
        if (settings == null){
            settings = new LinkedList<>();
            settings.add(0, "Yes");
            settings.add(1, "5");
        }
        return settings;
    }

    /**
     * Method to add a new course to the list
     */
    public void saveSettings(int index, String newAnswer) {
        settings.remove(index);
        settings.add(index,newAnswer);
        save();
    }

    /**
     * Save the LinkedList of courses to disk
     */
    private void save() {
        try
        {
            FileOutputStream fileOut = new FileOutputStream(SETTINGS_FILE_NAME);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(settings);
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
