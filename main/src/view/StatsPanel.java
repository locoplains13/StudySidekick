package view;

import Objects.Course;
import Objects.RoundButton;
import Objects.Task;
import Objects.TaskDB;
import control.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

public class StatsPanel extends ViewPanel{
    // arrays to hold each type of Task
    private ArrayList<Task> assignments;
    private ArrayList<Task> reading;
    private ArrayList<Task> lecture;
    private ArrayList<Task> examPrep;
    private ArrayList<Task> other;

    static final int FONT_SIZE = 32;

    public static final long serialVersionUID = 1;

    public ActionListener listener;

    /**
     * Create a statistics page panel, and button to return to courseList
     * panel.
     * @param listener the class listening for the event that signals the button was pressed
     * @param course for the panel
     */
    public StatsPanel(ActionListener listener, Course course) {
        this.assignments = new ArrayList<>();
        this.reading = new ArrayList<>();
        this.lecture = new ArrayList<>();
        this.examPrep = new ArrayList<>();
        this.other = new ArrayList<>();


        // Set initial panel conditions
        setBackground(Color.white);
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        // Create label for stats page
        String statsLabel = course.getCourseName() + " Statistics";
        JLabel jLabelStats = new JLabel(statsLabel);
        jLabelStats.setFont(new Font("Arial", Font.BOLD, FONT_SIZE));
        jLabelStats.setForeground(Color.BLACK);
        jLabelStats.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(jLabelStats);
        add(Box.createRigidArea(new Dimension(0, 20)));



        // create a panel for the stats
        JPanel jPanelMiddle = new JPanel();
        jPanelMiddle.setBackground(Color.white);
        add(jPanelMiddle);
        // Split into two parts - total time and average time
        GridLayout layout = new GridLayout(1,2);
        jPanelMiddle.setLayout(layout);
        layout.setVgap(50);
        layout.setHgap(30);

        // create a panel for total time stats
        JPanel totalTimeArea = new JPanel();
        totalTimeArea.setBackground(Color.white);
        totalTimeArea.setLayout(new BoxLayout(totalTimeArea,BoxLayout.Y_AXIS));
        // create label for total time stats
        JLabel totalTimeLabel = new JLabel("Total Time Spent on Tasks");
        totalTimeLabel.setFont(new Font("Arial", Font.BOLD, 14));
        totalTimeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createRigidArea(new Dimension(0, 20)));
        // add label to the panel
        totalTimeArea.add(totalTimeLabel);
        // add total time area panel to the middle panel
        jPanelMiddle.add(totalTimeArea);

        // create a panel for average time stats
        JPanel averageTimeArea = new JPanel();
        averageTimeArea.setBackground(Color.white);
        averageTimeArea.setLayout(new BoxLayout(averageTimeArea,BoxLayout.Y_AXIS));
        // create label for total time stats
        JLabel averageTimeLabel = new JLabel("Average Time Spent on One Task");
        averageTimeLabel.setFont(new Font("Arial", Font.BOLD, 14));
        averageTimeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createRigidArea(new Dimension(0, 20)));
        // add label to the panel
        averageTimeArea.add(averageTimeLabel);
        // add total time area panel to the middle panel
        jPanelMiddle.add(averageTimeArea);


        this.findTasks(TaskDB.getInstance().getTasks(), course);
        this.findTasks(course.getFinishedTasks(), course);


        // find the total time of studying for each type of task
        // stats for assignments
        long assignmentTotalTime = 0;
        for (Task task : assignments) {
            assignmentTotalTime += task.getTimeSpentThusFar();
        }
        // stats for reading
        long readingTotalTime = 0;
        for (Task task : reading) {
            readingTotalTime += task.getTimeSpentThusFar();
        }
        // stats for lectures
        long lecturesTotalTime = 0;
        for (Task task : lecture) {
            lecturesTotalTime += task.getTimeSpentThusFar();
        }
        // stats for exam prep
        long examPrepTotalTime = 0;
        for (Task task : examPrep) {
            examPrepTotalTime += task.getTimeSpentThusFar();
        }
        // stats for other
        long otherTotalTime = 0;
        for (Task task : other) {
            otherTotalTime += task.getTimeSpentThusFar();
        }


        // create a new panel to display the total time stats
        JPanel totalTimeTable = new JPanel();
        totalTimeTable.setBackground(Color.white);
        JPanel averageTimeTable = new JPanel();
        averageTimeTable.setBackground(Color.white);
        totalTimeArea.add(totalTimeTable);
        averageTimeArea.add(averageTimeTable);

        // set up total time stats
        totalTimeTable.setLayout(new GridLayout(5,2,10,10));
        JLabel assignmentsLabel = new JLabel("Assignments");
        JLabel readingLabel = new JLabel("Reading");
        JLabel lectureLabel = new JLabel("Lecture");
        JLabel examPrepLabel = new JLabel("Exam Prep");
        JLabel otherLabel = new JLabel("Other");

        totalTimeTable.add(assignmentsLabel);
        totalTimeTable.add(new JLabel(myFormat(assignmentTotalTime)));

        totalTimeTable.add(readingLabel);
        totalTimeTable.add(new JLabel(myFormat(readingTotalTime)));

        totalTimeTable.add(lectureLabel);
        totalTimeTable.add(new JLabel(myFormat(lecturesTotalTime)));

        totalTimeTable.add(examPrepLabel);
        totalTimeTable.add(new JLabel(myFormat(examPrepTotalTime)));

        totalTimeTable.add(otherLabel);
        totalTimeTable.add(new JLabel(myFormat(otherTotalTime)));

        assignmentsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        readingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lectureLabel.setHorizontalAlignment(SwingConstants.CENTER);
        examPrepLabel.setHorizontalAlignment(SwingConstants.CENTER);
        otherLabel.setHorizontalAlignment(SwingConstants.CENTER);


        // set up average time area stats
        averageTimeTable.setLayout(new GridLayout(5,2,10,10));
        JLabel assignmentsLabel2 = new JLabel("Assignments");
        JLabel readingLabel2 = new JLabel("Reading");
        JLabel lectureLabel2 = new JLabel("Lecture");
        JLabel examPrepLabel2 = new JLabel("Exam Prep");
        JLabel otherLabel2 = new JLabel("Other");

        averageTimeTable.add(assignmentsLabel2);
        if (assignments.size() > 0) averageTimeTable.add(new JLabel(myFormat(assignmentTotalTime / assignments.size())));
        else averageTimeTable.add(new JLabel(myFormat(assignmentTotalTime)));

        averageTimeTable.add(readingLabel2);
        if (reading.size() > 0) averageTimeTable.add(new JLabel(myFormat(readingTotalTime / reading.size())));
        else averageTimeTable.add(new JLabel(myFormat(readingTotalTime)));

        averageTimeTable.add(lectureLabel2);
        if (lecture.size() > 0) averageTimeTable.add(new JLabel(myFormat(lecturesTotalTime / lecture.size())));
        else averageTimeTable.add(new JLabel(myFormat(lecturesTotalTime)));

        averageTimeTable.add(examPrepLabel2);
        if (examPrep.size() > 0) averageTimeTable.add(new JLabel(myFormat(examPrepTotalTime / examPrep.size())));
        else averageTimeTable.add(new JLabel(myFormat(examPrepTotalTime)));

        averageTimeTable.add(otherLabel2);
        if (other.size() > 0) averageTimeTable.add(new JLabel(myFormat(otherTotalTime / other.size())));
        else averageTimeTable.add(new JLabel(myFormat(otherTotalTime)));

        assignmentsLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        readingLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        lectureLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        examPrepLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        otherLabel2.setHorizontalAlignment(SwingConstants.CENTER);

        // Create new panel for buttons
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.X_AXIS));
        buttonPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        buttonPane.setBackground(Color.white);

        // create back and main menu buttons
        RoundButton backButton = new RoundButton("Back");
        backButton.setBackground(new Color(179, 217, 255));
        backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backButton.setForeground(Color.white);
        buttonPane.add(Box.createRigidArea(new Dimension(20, 0)));
        buttonPane.add(backButton);

        // set command for back button to jump to course page
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainPanel.setMainPanel(new CoursePagePanel(listener, course));
                Controller.getView().showMainView(listener);
            }
        });
        add(buttonPane, BorderLayout.PAGE_END);
    }

    /**
     * Takes a LinkedList of Tasks and walks through the list. If a task is assigned to the current course
     * the method takes the task and adds it to one of the 5 lists based off of the task type
     * @param tasks: A linked list of tasks
     * @param course: The course the statistics is eing generated for
     */
    private void findTasks(LinkedList<Task> tasks, Course course) {
        // runs through the task data base to find tasks associated with the current course
        // adds each task to the proper Array based on its type
        for (int x = 0; x < tasks.size(); x++) {
            if (tasks.get(x).getCourseName().compareTo(course.getCourseName())==0){
                if (tasks.get(x).getTaskType().equals("Assignment")) {
                    assignments.add(tasks.get(x));
                }
                else if (tasks.get(x).getTaskType().equals("Reading")) {
                    reading.add(tasks.get(x));
                }
                else if (tasks.get(x).getTaskType().equals("Lecture")) {
                    lecture.add(tasks.get(x));
                }
                else if (tasks.get(x).getTaskType().equals("Exam Prep")) {
                    examPrep.add(tasks.get(x));
                }
                else {
                    other.add(tasks.get(x));
                }
            }
        }

    }
    /**
     * Formats the current time so that it can be seen as hr:min:sec
     * @param time current time in milliseconds
     * @return the current time as a formatted sting
     */
    private String myFormat(long time) {
        final long hr = TimeUnit.MILLISECONDS.toHours(time);
        final long min = TimeUnit.MILLISECONDS.toMinutes(time
                - TimeUnit.HOURS.toMillis(hr));
        final long sec = TimeUnit.MILLISECONDS.toSeconds(time
                - TimeUnit.HOURS.toMillis(hr) - TimeUnit.MINUTES.toMillis(min));
        return String.format("%02d:%02d:%02d", hr, min, sec);
    }
}
