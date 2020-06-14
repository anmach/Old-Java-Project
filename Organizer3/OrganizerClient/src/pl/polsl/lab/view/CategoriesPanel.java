package pl.polsl.lab.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.util.List;
import java.util.ArrayList;

/**
 * Panel showing used categories avalible in program.
 *
 * @author Anna Mach
 * @version 1.3
 */
public class CategoriesPanel extends JPanel implements ActionListener {

    /**
     * List of buttons representing categories.
     */
    private final List<JButton> categoriesButtons;
    /**
     * String containing name of category represented by button last clicked by
     * user.
     */
    private String theChosenOneCategory;
    /**
     * Information about need to show TasksPanel.
     */
    private boolean doWeShowTasks;

    /**
     * Constructor creating buttons in the image of categories in list given in
     * parameters.
     *
     * @param categoriesList List with categories to be shown.
     */
    CategoriesPanel(String categories) {
        int pHeight = 300;
        int pWidth = 600;
        String[] categoriesSplitted = categories.split(" ;; ");

        doWeShowTasks = false;
        theChosenOneCategory = "";
        setPreferredSize(new Dimension(pWidth, pHeight));
        setLayout(new FlowLayout(FlowLayout.CENTER));

        categoriesButtons = new ArrayList();
        int i = 0;
        
        for (String category : categoriesSplitted) {
            categoriesButtons.add(new JButton(category));
            categoriesButtons.get(i).addActionListener(this);
            add(categoriesButtons.get(i));
            i++;
        }
    }

    /**
     * Getter for Category last chosen by user (by clicking button)
     *
     * @return String theChosenOneCategory
     */
    public String getChosenCategory() {
        return theChosenOneCategory;
    }

    /**
     * Getter for information if user clicked any category and want to see tasks
     * of this category.
     *
     * @return boolean doWeShowTasks
     */
    public boolean doWeWantToShowTasks() {
        return doWeShowTasks;
    }

    /**
     * Method reseting doWeShowTasks to default value.
     */
    public void resetDoWeShowTasks() {
        doWeShowTasks = false;
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

        for (JButton button : categoriesButtons) {
            if (source == button) {
                theChosenOneCategory = button.getText();
                doWeShowTasks = true;
                break;
            }
        }
    }
}
