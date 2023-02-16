package view;

import Objects.Course;
import Objects.RelatedGrades;
import Objects.RoundButton;
import control.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static model.AddComponentsToPanel.addComponentsToPanel;


public class CreateGradingSchemePanel extends ViewPanel{
        private static final int FONT_SIZE = 32;

        public static final long serialVersionUID = 1;

        private ActionListener listener;

        private JTextPane[] data;

        private Course course;
        /**
         * Create a course page panel, and button to return to courseList
         * panel.
         * @param listener the class listening for the event that signals the button was pressed
         * @param course for the panel
         */
        public CreateGradingSchemePanel(ActionListener listener, Course course) {
            Component[] components;
            this.course = course;


//////////////////////////////////////////////////////////////////////////////////////////////
            // PANEL FOR THE NUMBER OF PARTS TO GRADING SCHEME

            JPanel jPanelNumberOfPartsInScheme = new JPanel();

            // set initial conditions for jPanelNumberOfPartsInScheme

            jPanelNumberOfPartsInScheme.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 50));
            jPanelNumberOfPartsInScheme.setBackground(Color.white);

            // create components for jPanelNumberOfPartsInScheme
            JLabel jLabelParts = new JLabel("How many parts is the grading scheme broken into?");

            JComboBox<Integer> jComboBoxNumParts = new JComboBox<>(new Integer[]{0, 1, 2, 3, 4, 5, 6, 7,
                    8, 9, 10, 11, 12, 13, 14, 15});
            RoundButton buttonSubmit = new RoundButton("Submit");
            buttonSubmit.setBackground(new Color(179, 217, 255));
            buttonSubmit.setForeground(Color.white);

            // add components to jPanelNumberOfPartsInScheme
            components = new Component[]{jLabelParts,jComboBoxNumParts,buttonSubmit};
            addComponentsToPanel(jPanelNumberOfPartsInScheme,components);


//////////////////////////////////////////////////////////////////////////////////////////////
            // PANEL FOR SKELETON SCHEME

            JPanel jPanelSkeletonScheme = new JPanel();
            jPanelSkeletonScheme.setVisible(false);

            JScrollPane jScrollPaneSkeletonScheme = new JScrollPane(jPanelSkeletonScheme);
            jScrollPaneSkeletonScheme.setPreferredSize(new Dimension(getWidth(),500));
            jScrollPaneSkeletonScheme.setBackground(Color.white);
            jScrollPaneSkeletonScheme.setBorder(null);


            // set initial conditions for jPanelSkeletonScheme
            jPanelSkeletonScheme.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
            jPanelSkeletonScheme.setBackground(Color.white);


            // create components for jPanelSkeletonScheme
            buttonSubmit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if ((Integer)jComboBoxNumParts.getSelectedItem()!=0){
                        jPanelSkeletonScheme.setLayout(new GridLayout((Integer)jComboBoxNumParts.getSelectedItem(),3,25,25));
                        data = createSkeletonScheme(jPanelSkeletonScheme, (Integer)jComboBoxNumParts.getSelectedItem());

                    }
                    else{
                        jComboBoxNumParts.setBorder(BorderFactory.createLineBorder(Color.RED));
                    }
                }
            });

//////////////////////////////////////////////////////////////////////////////////////////////
            // PANEL FOR BUTTONS AT BOTTOM OF SCREEN

            JPanel jPanelBottomButtons = new JPanel();

            // set initial conditions for jPanelBottomButtons
            jPanelBottomButtons.setLayout(new BoxLayout(jPanelBottomButtons, BoxLayout.X_AXIS));
            jPanelBottomButtons.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
            jPanelBottomButtons.setBackground(Color.white);

            // create components for jPanelBottomButtons
            RoundButton buttonSubmitScheme = new RoundButton("Submit Grading Scheme");
            buttonSubmitScheme.setBackground(new Color(179, 217, 255));
            buttonSubmitScheme.setForeground(Color.white);
            buttonSubmitScheme.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (createGradingScheme()) {
                        MainPanel.setMainPanel(new CoursePagePanel(listener, course));
                        Controller.getView().showMainView(listener);
                    }

                }
            });

            RoundButton backButton = new RoundButton("Back");
            backButton.setBackground(new Color(179, 217, 255));
            backButton.setForeground(Color.white);
            backButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    MainPanel.setMainPanel(new CoursePagePanel(listener, course));
                    Controller.getView().showMainView(listener);
                }
            });

            // add components to jPanelBottomButtons
            components = new Component[]{buttonSubmitScheme,
                    Box.createRigidArea(new Dimension(20, 0)),backButton};
            addComponentsToPanel(jPanelBottomButtons, components);

//////////////////////////////////////////////////////////////////////////////////////////////

            // Set initial conditions for this
            setBackground(Color.white);
            setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

            // create componentsAndLayouts for this
            JLabel jLabelTitle = new JLabel(course.getCourseName() +" Grading Scheme");
            jLabelTitle.setFont(new Font("Arial", Font.BOLD, FONT_SIZE));
            jLabelTitle.setForeground(Color.BLACK);
            jLabelTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

            // add componentsAndLayouts to this
            components = new Component[]{Box.createRigidArea(new Dimension(0, 8)),
                    jLabelTitle,Box.createRigidArea(new Dimension(0, 20)),jPanelNumberOfPartsInScheme,
                    jScrollPaneSkeletonScheme, Box.createRigidArea(new Dimension(0, 30)),
                    jPanelBottomButtons, Box.createRigidArea(new Dimension(0, 5)) };
            addComponentsToPanel(this, components);

//////////////////////////////////////////////////////////////////////////////////////////////
        }

        /**
         * Add the headers and the scores to the specified panel.
         * @param panel the panel into which the items are to be added
         */
        private JTextPane[] createSkeletonScheme(JPanel panel, int numParts) {

            Component[] components = new Component[numParts*6];
            JTextPane[] entries = new JTextPane[numParts*3];

            JLabel jLabelDescription;
            JTextPane jTextPaneItem;
            JLabel jLabelTotalWeight;
            JTextPane jTextPaneWeight;
            JLabel jLabelNumItems;
            JTextPane jTextPaneNumItems;

            for (int i = 0; i< numParts*6; i+=6){
                jLabelDescription = new JLabel("Type of Item:");
                jTextPaneItem = new JTextPane();
                jTextPaneItem.setPreferredSize(new Dimension(100,30));
                jTextPaneItem.setSize(getPreferredSize());
                jTextPaneItem.setBorder(BorderFactory.createLineBorder(new Color(179, 217, 255)));
                entries[i/2] = jTextPaneItem;

                jLabelTotalWeight = new JLabel("Final Weight %:");
                jTextPaneWeight = new JTextPane();
                jTextPaneWeight.setPreferredSize(new Dimension(100,30));
                jTextPaneWeight.setSize(getPreferredSize());
                jTextPaneWeight.setBorder(BorderFactory.createLineBorder(new Color(179, 217, 255)));
                entries[i/2+1] = jTextPaneWeight;

                jLabelNumItems = new JLabel("# of Items:");
                jTextPaneNumItems = new JTextPane();
                jTextPaneNumItems.setPreferredSize(new Dimension(100,25));
                jTextPaneNumItems.setSize(getPreferredSize());
                jTextPaneNumItems.setBorder(BorderFactory.createLineBorder(new Color(179, 217, 255)));
                entries[i/2+2] = jTextPaneNumItems;

                components[i] = jLabelDescription;
                components[i+1] = entries[i/2];
                components[i+2] = jLabelTotalWeight;
                components[i+3] = entries[i/2+1];
                components[i+4] = jLabelNumItems;
                components[i+5] = entries[i/2+2];
            }
            JPanel jPanel;
            for (int i = 0; i< numParts*6; i+=6){
                jPanel = new JPanel();
                jPanel.setBackground(Color.white);
                addComponentsToPanel(jPanel, new Component[]{components[i],components[i+1],components[i+2],
                        components[i+3],components[i+4],components[i+5]});
                panel.add(jPanel);
            }
            panel.setVisible(true);
            return entries;
        }

    /**
     * Checks user input and creates grading scheme is input valid
     * @return true is all input was valid, else false
     */
    public boolean createGradingScheme() {
            RelatedGrades[] grades = new RelatedGrades[data.length/3];
            boolean validNames = validNames();
            boolean validPercentages = validPercentages();
            boolean validAmounts = validAmounts();
                if (validNames&&validPercentages&&validAmounts){
                    for (int i = 0; i < data.length; i+=3){
                        grades[i/3] = new RelatedGrades(data[i].getText(),Float.parseFloat(data[i+1].getText()),
                            Integer.parseInt(data[i+2].getText()));
                    }
                    course.setGradingScheme(grades);
                    return true;
                }
            return false;
        }

    /**
     * Check to see if any of the names given for the parts of the grading scheme
     * were left blank or were duplicates
     * @return true if all data is valid, else false
     * @postCondition: all invalid data locations will have a red border
     */
    private boolean validNames(){
            boolean dataIsValid = true;
            for (int i = 0; i < data.length; i+=3){
                data[i].setBorder(null);
                if (data[i].getText().isEmpty()){
                    data[i].setBorder(BorderFactory.createLineBorder(Color.RED));
                    dataIsValid = false;
                }
                for (int j = 0; j < data.length; j+=3){
                    if (i!=j) {
                        if (data[i].getText().equals(data[j].getText())) {
                            data[i].setBorder(BorderFactory.createLineBorder(Color.RED));
                            data[j].setBorder(BorderFactory.createLineBorder(Color.RED));
                            dataIsValid = false;
                        }
                    }
                }
            }
            return dataIsValid;
        }

    /**
     * Check to see if a percentage was given for each separate part and if the percentages
     * add up to 100
     * @return true if all data is valid, else false
     * @postCondition: all invalid data locations will have a red border
     */
    private boolean validPercentages(){
            boolean dataIsValid = true;
            float totalPercent = 0;
            for (int i = 1; i < data.length; i+=3){
                data[i].setBorder(null);
                if (data[i].getText().isEmpty()){
                    data[i].setBorder(BorderFactory.createLineBorder(Color.RED));
                    dataIsValid = false;
                }
                else if (Float.parseFloat(data[i].getText())==0){
                    data[i].setBorder(BorderFactory.createLineBorder(Color.RED));
                    dataIsValid = false;
                }
                else{
                    totalPercent+=Float.parseFloat(data[i].getText());
                }
            }
            if (totalPercent!=100.0f){
                dataIsValid = false;

                for (int i = 1; i < data.length; i+=3){
                    data[i].setBorder(BorderFactory.createLineBorder(Color.RED));
                }
            }

            return dataIsValid;
        }

    /**
     * Check to see if an amount value was given for each separate part
     * @return true if all data is valid, else false
     * @postCondition: all invalid data locations will have a red border
     */
    private boolean validAmounts(){
            boolean dataIsValid = true;
            for (int i = 2; i < data.length; i+=3){
                data[i].setBorder(null);
                if (data[i].getText().isEmpty()){
                    data[i].setBorder(BorderFactory.createLineBorder(Color.RED));
                    dataIsValid = false;
                }
                else if (Integer.parseInt(data[i].getText())==0){
                    data[i].setBorder(BorderFactory.createLineBorder(Color.RED));
                    dataIsValid = false;
                }
            }
            return dataIsValid;
        }
}

