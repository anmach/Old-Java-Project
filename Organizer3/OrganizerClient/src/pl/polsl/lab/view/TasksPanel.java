package pl.polsl.lab.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.BoxLayout;

import java.util.List;
import java.util.ArrayList;

/**
 * Class showing user tasks created in category as checkboxes so user can check
 * it as done.
 *
 * @author Anna Mach
 * @version 1.1
 */
public class TasksPanel extends JPanel implements ActionListener {

    /**
     * List for check boxes representing tasks with high priority.
     */
    private final List<JCheckBox> tasksOfHighPriority;
    /**
     * List for check boxes representing tasks with medium priority.
     */
    private final List<JCheckBox> tasksOfMediumPriority;
    /**
     * List for check boxes representing tasks with low priority.
     */
    private final List<JCheckBox> tasksOfLowPriority;

    /**
     * Name of the category from which shown tasks are taken.
     */
    private String category;
    /**
     * Information what we want server to do.
     */
    private String demandForServer;

    /**
     * Constructor creating checkboxes with tasks.
     *
     * @param category object containing all tasks that will be shown
     */
    TasksPanel(String category, String tasks) {
        int pWidth = 600;
        int pHeight = 600;

        this.category = category;
        demandForServer = "";

        setPreferredSize(new Dimension(pWidth, pHeight));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        tasksOfHighPriority = new ArrayList();
        tasksOfMediumPriority = new ArrayList();
        tasksOfLowPriority = new ArrayList();

        if (!tasks.toUpperCase().equals("NO TASKS FOUND")) {
            String[] tasksSplitted = tasks.split(" ;; ");
            String[] taskInfo;
            String taskPriority = "";
            String taskName = "";
            boolean isDone = false;
            int h = 0;
            int l = 0;
            int m = 0;
            int j;
            for (String taskDescription : tasksSplitted) {
                j = 0;
                taskInfo = taskDescription.split(" -- ");
                for (String info : taskInfo) {
                    switch (j) {
                        case 0:
                            if (info.equals("DONE")) {
                                isDone = true;
                            } else {
                                isDone = false;
                            }
                            j++;
                            break;
                        case 1:
                            taskName = info;
                            j++;
                            break;
                        case 2:
                            taskPriority = info;
                            j++;
                            break;
                        case 3:
                            System.err.println("Error in server response!");
                            break;
                    }
                }

                if (taskPriority.toUpperCase().equals("HIGH")) {
                    tasksOfHighPriority.add(new JCheckBox(taskName));
                    tasksOfHighPriority.get(h).addActionListener(this);
                    if (isDone) {
                        tasksOfHighPriority.get(h).setSelected(true);
                    }
                    add(tasksOfHighPriority.get(h));
                    h++;
                } else if (taskPriority.toUpperCase().equals("MEDIUM")) {
                    tasksOfMediumPriority.add(new JCheckBox(taskName));
                    tasksOfMediumPriority.get(m).addActionListener(this);
                    if (isDone) {
                        tasksOfMediumPriority.get(m).setSelected(true);
                    }
                    add(tasksOfMediumPriority.get(m));
                    m++;
                } else if (taskPriority.toUpperCase().equals("LOW")) {
                    tasksOfLowPriority.add(new JCheckBox(taskName));
                    tasksOfLowPriority.get(l).addActionListener(this);
                    if (isDone) {
                        tasksOfLowPriority.get(l).setSelected(true);
                    }
                    else{
                        
                        tasksOfLowPriority.get(l).setSelected(false);
                    }
                    add(tasksOfLowPriority.get(l));
                    l++;
                }
            }
        }
    }

    /**
     * Method which return what user want server to do.
     *
     * @return demandForServer - String with information about user demand
     */
    public String getDemandForServer() {
        return demandForServer;
    }

    /**
     * Method which reset demandForServer to default value
     */
    public void resetDemandForServer() {
        demandForServer = "";
    }

    /**
     * Overrided actionPerformed, where we decide what happens when buttons etc
     * are clicked
     *
     * @param e representation of action performed by user (for example clicking
     * button)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        for (int i = 0; i < tasksOfHighPriority.size(); i++) {
            if (source == tasksOfHighPriority.get(i)) {
                if (tasksOfHighPriority.get(i).isSelected()) {
                    demandForServer = "MAKE TASK DONE ;; " + category + " ;; " + tasksOfHighPriority.get(i).getText() + " ;; HIGH";
                } else {
                    demandForServer = "MAKE TASK UNDONE ;; " + category + " ;; " + tasksOfHighPriority.get(i).getText() + " ;; HIGH";
                }
                break;
            }
        }
        for (int i = 0; i < tasksOfMediumPriority.size(); i++) {
            if (source == tasksOfMediumPriority.get(i)) {
                if (tasksOfMediumPriority.get(i).isSelected()) {
                    demandForServer = "MAKE TASK DONE ;; " + category + " ;; " + tasksOfMediumPriority.get(i).getText() + " ;; MEDIUM";
                } else {
                    demandForServer = "MAKE TASK UNDONE ;; " + category + " ;; " + tasksOfMediumPriority.get(i).getText() + " ;; MEDIUM";
                }
                break;
            }
        }
        for (int i = 0; i < tasksOfLowPriority.size(); i++) {
            if (source == tasksOfLowPriority.get(i)) {
                if (tasksOfLowPriority.get(i).isSelected()) {
                    demandForServer = "MAKE TASK DONE ;; " + category + " ;; " + tasksOfLowPriority.get(i).getText() + " ;; LOW";
                } else {
                    demandForServer = "MAKE TASK UNDONE ;; " + category + " ;; " + tasksOfLowPriority.get(i).getText() + " ;; LOW";
                }
                break;
            }
        }

    }
}
