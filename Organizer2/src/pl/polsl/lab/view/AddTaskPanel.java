package pl.polsl.lab.view;

import pl.polsl.lab.model.Category;
import pl.polsl.lab.model.Task;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Panel containing buttons and text fields necessery for creating new Task.
 *
 * @author Anna Mach
 * @version 1.0
 */
public class AddTaskPanel extends JPanel implements ActionListener {

    /**
     * Category to which the new task is added.
     */
    private final Category category;
    /**
     * Text field where user can enter name of new task.
     */
    private final JTextField newTaskName;
    /**
     * Button which user have to press to create new task.
     */
    private final JButton addTaskButton;
    /**
     * Label with text encouraging user for choosing priority of task.
     */
    private final JLabel choosePriorityLabel;
    /**
     * Combobox with priorites to choose from.
     */
    private final JComboBox choosePriorityComboBox;
    /**
     * Information about need of refreshing frame.
     */
    private boolean doWeRefresh;

    /**
     * Constructor Creates all buttons, labels, comboboxes and add it to panel.
     *
     * @param category object of type Category to which we can add task
     */
    public AddTaskPanel(Category category) {
        int pheight = 200;
        int pWidth = 200;

        doWeRefresh = false;
        this.category = category;
        GridLayout panelLayout = new GridLayout(10, 1);

        Task.PriorityLevel[] priorities = {Task.PriorityLevel.LOW, Task.PriorityLevel.MEDIUM, Task.PriorityLevel.HIGH};

        choosePriorityLabel = new JLabel("Choose priority:");
        choosePriorityComboBox = new JComboBox(priorities);
        choosePriorityComboBox.addActionListener(this);
        addTaskButton = new JButton("Add new task");
        addTaskButton.addActionListener(this);
        newTaskName = new JTextField("Enter task name");

        setPreferredSize(new Dimension(pWidth, pheight));
        setLayout(panelLayout);

        add(newTaskName);
        add(choosePriorityLabel);
        add(choosePriorityComboBox);
        add(addTaskButton);
    }

    /**
     * Method which inform if it's time to refresh frame.
     *
     * @return doWeRefresh - it exuals true if there was changes requiring
     * refreshing frame
     */
    public boolean doWeWantToRefresh() {
        return doWeRefresh;
    }

    /**
     * Method changing value of doWeRefresh for false.
     */
    public void resetDoWeRefresh() {
        doWeRefresh = false;
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

        if (source == addTaskButton) {
            category.addNewTask(newTaskName.getText(), (Task.PriorityLevel) choosePriorityComboBox.getSelectedItem());
            doWeRefresh = true;
        }

    }
}
