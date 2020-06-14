package pl.polsl.lab.view;

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
 * @version 1.3
 */
public class AddTaskPanel extends JPanel implements ActionListener {

    /**
     * String with name of category to which user want to add task.
     */
    private String category;
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
     * Information what we want server to do.
     */
    private String demandForServer;

    /**
     * Constructor Creates all buttons, labels, comboboxes and add it to panel.
     *
     * @param category object of type Category to which we can add task
     */
    public AddTaskPanel(String category) {
        int pheight = 200;
        int pWidth = 200;

        this.category = category;
        demandForServer = "";
        doWeRefresh = false;
        GridLayout panelLayout = new GridLayout(10, 1);

        String[] priorities = {"LOW", "MEDIUM", "HIGH"};

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
            demandForServer = "ADD TASK ;; " + category + " ;; " + newTaskName.getText() + " ;; " + choosePriorityComboBox.getSelectedItem();
            doWeRefresh = true;
        }
    }
}
