package pl.polsl.lab.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import pl.polsl.lab.model.Organizer;

/**
 * Panel containing text and button needed for creating Category
 *
 * @author Anna Mach
 * @version 1
 */
public class AddCategoryPanel extends JPanel implements ActionListener {

    /**
     * Information about need for refreshing frame.
     */
    private boolean doWeRefresh;
    /**
     * Organizer to which class can add categories.
     */
    private final Organizer organizer;

    /**
     * Text field where user can enter name of new category.
     */
    private final JTextField newCategoryName;
    /**
     * Button which user have to press to create new category.
     */
    private final JButton addCategoryButton;

    /**
     * Costructor creating buttons and text field. Do stuff needed by swing for
     * JPanel to work.
     *
     * @param organ object of Organizer type, which contains all categories
     * avalible.
     */
    public AddCategoryPanel(Organizer organ) {
        int pWidth = 50;
        int pHeight = 300;

        this.organizer = organ;
        doWeRefresh = false;
        addCategoryButton = new JButton("Add new category");
        addCategoryButton.addActionListener(this);
        newCategoryName = new JTextField("Enter category name");

        setPreferredSize(new Dimension(pWidth, pHeight));
        setLayout(new FlowLayout(FlowLayout.CENTER));

        add(newCategoryName);
        add(addCategoryButton);
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
     * Method changing value of doWeRefresh for false
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

        if (source == addCategoryButton) {
            organizer.addCategory(newCategoryName.getText());
            doWeRefresh = true;
        }

    }
}
