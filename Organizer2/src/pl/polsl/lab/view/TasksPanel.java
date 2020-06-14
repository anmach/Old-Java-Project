package pl.polsl.lab.view;

import pl.polsl.lab.model.Task;
import pl.polsl.lab.model.Category;
import pl.polsl.lab.model.Task.PriorityLevel;

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
     * Category from which shown tasks are taken.
     */
    private Category category;

    /**
     * Constructor creating checkboxes with tasks.
     *
     * @param category object containing all tasks that will be shown
     */
    TasksPanel(Category category) {
        int pWidth = 600;
        int pHeight = 600;

        this.category = category;
        List<Task> tasks = category.getTasksList();

        setPreferredSize(new Dimension(pWidth, pHeight));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        tasksOfHighPriority = new ArrayList();
        tasksOfMediumPriority = new ArrayList();
        tasksOfLowPriority = new ArrayList();

        int i = 0;
        int indexCheckbox;
        for (; i < tasks.size(); i++) {
            if (tasks.get(i).getPriority().equals(PriorityLevel.MEDIUM) || tasks.get(i).getPriority().equals(PriorityLevel.LOW)) {
                break;
            }

            tasksOfHighPriority.add(new JCheckBox((tasks.get(i)).getName()));
            tasksOfHighPriority.get(i).addActionListener(this);
            if (tasks.get(i).isDone()) {
                tasksOfHighPriority.get(i).setSelected(true);
            }
            add(tasksOfHighPriority.get(i));
        }
        indexCheckbox = 0;
        for (; i < tasks.size(); i++) {
            if (tasks.get(i).getPriority().equals(PriorityLevel.LOW)) {
                break;
            }

            tasksOfMediumPriority.add(new JCheckBox((tasks.get(i)).getName()));
            tasksOfMediumPriority.get(indexCheckbox).addActionListener(this);
            if (tasks.get(i).isDone()) {
                tasksOfMediumPriority.get(indexCheckbox).setSelected(true);
            }
            add(tasksOfMediumPriority.get(indexCheckbox));
            indexCheckbox++;
        }

        indexCheckbox = 0;
        for (; i < tasks.size(); i++) {
            tasksOfLowPriority.add(new JCheckBox((tasks.get(i)).getName()));
            tasksOfLowPriority.get(indexCheckbox).addActionListener(this);
            if (tasks.get(i).isDone()) {
                tasksOfLowPriority.get(indexCheckbox).setSelected(true);
            }
            add(tasksOfLowPriority.get(indexCheckbox));
            indexCheckbox++;
        }

    }

    /**
     * Overrided actionPerformed, where we decide what happens when buttons etc.
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
                    if (category.getFirstUndoneTaskByName(tasksOfHighPriority.get(i).getText(), PriorityLevel.HIGH) != null) {
                        category.getFirstUndoneTaskByName(tasksOfHighPriority.get(i).getText(), PriorityLevel.HIGH).makeTaskDone();
                    }
                } else {
                    if (category.getLastDoneTaskByName(tasksOfHighPriority.get(i).getText(), PriorityLevel.HIGH) != null) {
                        category.getLastDoneTaskByName(tasksOfHighPriority.get(i).getText(), PriorityLevel.HIGH).makeTaskUndone();
                    }
                }
                break;
            }
        }
        for (int i = 0; i < tasksOfMediumPriority.size(); i++) {
            if (source == tasksOfMediumPriority.get(i)) {
                if (tasksOfMediumPriority.get(i).isSelected()) {
                    if (category.getFirstUndoneTaskByName(tasksOfMediumPriority.get(i).getText(), PriorityLevel.MEDIUM) != null) {
                        category.getFirstUndoneTaskByName(tasksOfMediumPriority.get(i).getText(), PriorityLevel.MEDIUM).makeTaskDone();
                    }
                } else {
                    if (category.getLastDoneTaskByName(tasksOfMediumPriority.get(i).getText(), PriorityLevel.MEDIUM) != null) {
                        category.getLastDoneTaskByName(tasksOfMediumPriority.get(i).getText(), PriorityLevel.MEDIUM).makeTaskUndone();
                    }
                }
                break;
            }
        }
        for (int i = 0; i < tasksOfLowPriority.size(); i++) {
            if (source == tasksOfLowPriority.get(i)) {
                if (tasksOfLowPriority.get(i).isSelected()) {
                    if (category.getFirstUndoneTaskByName(tasksOfLowPriority.get(i).getText(), PriorityLevel.LOW) != null) {
                        category.getFirstUndoneTaskByName(tasksOfLowPriority.get(i).getText(), PriorityLevel.LOW).makeTaskDone();
                    }
                } else {
                    if (category.getLastDoneTaskByName(tasksOfLowPriority.get(i).getText(), PriorityLevel.LOW) != null) {
                        category.getLastDoneTaskByName(tasksOfLowPriority.get(i).getText(), PriorityLevel.LOW).makeTaskUndone();
                    }
                }

                break;
            }
        }

    }
}
