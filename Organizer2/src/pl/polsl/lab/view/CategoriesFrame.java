package pl.polsl.lab.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import pl.polsl.lab.model.Organizer;

/**
 * Class representing frame containing panels with categories and for adding new
 * category.
 *
 * @author Anna Mach
 * @version 1.0
 */
public class CategoriesFrame extends JFrame {

    /**
     * Panel for adding new category.
     */
    private AddCategoryPanel addCategoryPanel;
    /**
     * Panel showing categories.
     */
    private CategoriesPanel categoriesPanel;
    /**
     * Organizer containing categories.
     */
    private final Organizer organizer;

    /**
     * Costructor creating panels belonging to this frame and set options like
     * size of frame.
     *
     * @param organizer Object containing all categories avalible.
     */
    public CategoriesFrame(Organizer organizer) {
        super("Organizer");
        int fY = 100;
        int fX = 100;
        int fWidth = 600;
        int fHeight = 600;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(fHeight, fWidth);
        setLocation(fX, fY);

        this.organizer = organizer;

        addCategoryPanel = new AddCategoryPanel(organizer);
        categoriesPanel = new CategoriesPanel(organizer.getCategoriesList());
    }

    /**
     * Method hiding panels and creating new versions of them in order to reset
     * all text fields and add new categories. Then showing them again.
     */
    public void showFrame() {
        addCategoryPanel.setVisible(false);
        categoriesPanel.setVisible(false);

        this.dispose();

        addCategoryPanel = null;
        categoriesPanel = null;
        addCategoryPanel = new AddCategoryPanel(organizer);
        categoriesPanel = new CategoriesPanel(organizer.getCategoriesList());

        add(categoriesPanel, BorderLayout.SOUTH);
        add(addCategoryPanel, BorderLayout.NORTH);

        pack();

        this.show();
    }

    /**
     * Method which get's from categoriesPanel last chosen by user Category
     * (last clicked button which represent category)
     *
     * @return Category got from categoriesPanel
     */
    public String getChosenCategory() {
        return categoriesPanel.getChosenCategory();
    }

    /**
     * Method which gets from categoriesPanel information if user want to see
     * tasks from one of the categories.
     *
     * @return boolean got from categoriesPanel
     */
    public boolean doWeWantToShowTasks() {
        return categoriesPanel.doWeWantToShowTasks();
    }

    /**
     * Method which is getting from addCategoriesPanel information if user added
     * new category and frame needs to be refreshed to show it.
     *
     * @return boolean got from addCategoryPanel
     */
    public boolean doWeWantToRefreshCategories() {
        return addCategoryPanel.doWeWantToRefresh();
    }

    /**
     * Method which call method from categoriesPanel to reset it's variable
     * doWeRefresh to default value.
     */
    public void resetDoWeShowTasks() {
        categoriesPanel.resetDoWeShowTasks();
    }

    /**
     * Method which call method from addCategoryPanel to reset it's variable
     * doWeRefresh to default value.
     */
    public void resetDoWeRefresh() {
        addCategoryPanel.resetDoWeRefresh();
    }
}
