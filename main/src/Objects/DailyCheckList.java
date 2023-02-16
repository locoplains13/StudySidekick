package Objects;

import java.time.*;
import java.util.LinkedList;

public class DailyCheckList {
    private Instant lastOpened;

    private LinkedList<Task> dailyList;

    private static DailyCheckList instance = null;


    public static DailyCheckList getInstance(){
        // if this is the first time opening checklist
        if (instance == null){
            instance = new DailyCheckList();
        }
        return instance;
    }


    private DailyCheckList() {
        this.lastOpened = Instant.now();
        this.dailyList = new LinkedList<>();
        // populate list
        if (TaskDB.getInstance().getTasks().size() < 5) {
            this.dailyList.addAll(TaskDB.getInstance().getTasks());
        }
        // add 5 tasks to the daily list
        else {
            this.dailyList.add(TaskDB.getInstance().getTasks().get(0));
            this.dailyList.add(TaskDB.getInstance().getTasks().get(1));
            this.dailyList.add(TaskDB.getInstance().getTasks().get(2));
            this.dailyList.add(TaskDB.getInstance().getTasks().get(3));
            this.dailyList.add(TaskDB.getInstance().getTasks().get(4));
        }
    }

    /**
     * returns a task at the given index
     * @param index: an index
     * @return
     */
    public Task getDailyTask(int index) throws IndexOutOfBoundsException{
        if (this.dailyList.size() - 1 < index) throw new IndexOutOfBoundsException("Error, index out of bounds");
        return this.dailyList.get(index);
    }

    /**
     * returns a task at the given index
     * @return the daily list
     */
    public LinkedList<Task> getDailyList() throws IndexOutOfBoundsException{
        return this.dailyList;
    }

    /**
     * updates the daily list if its a new day or if its been 24 hours since opening the list
     */
    public void updateDailyList() {
        getInstance();
        ZonedDateTime nowZoned = ZonedDateTime.now();
        Instant midnight = nowZoned.toLocalDate().atStartOfDay(nowZoned.getZone()).toInstant();

        Duration duration1 = Duration.between(midnight, Instant.now());
        Duration duration2 = Duration.between(midnight, this.lastOpened);
        Duration duration3 = Duration.between(this.lastOpened, Instant.now());

        if (duration1.toMillis() < duration2.toMillis() || duration3.toMillis() > 86400000) {
            instance = new DailyCheckList();
        }

        instance = new DailyCheckList();

    }
}

