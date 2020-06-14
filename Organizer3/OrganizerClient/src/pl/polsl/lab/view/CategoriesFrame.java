package pl.polsl.lab.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;

/**
 * Class representing frame containing panels with categories and for adding new
 * category.
 *
 * @author Anna Mach
 * @version 1.2
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
     * Costructor creating panels belonging to this frame and set options like
     * size of frame.
     *
     * @param categories - String with categories to be shown in categoriesPanel
     */
    public CategoriesFrame(String categories) {
        super("Organizer");
        int fY = 100;
        int fX = 100;
        int fWidth = 600;
        int fHeight = 600;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(fHeight, fWidth);
        setLocation(fX, fY);

        addCategoryPanel = new AddCategoryPanel();
        categoriesPanel = new CategoriesPanel(categories);
        
        add(categoriesPanel, BorderLayout.SOUTH);
        add(addCategoryPanel, BorderLayout.NORTH);

        pack();

        this.show();
    }

    /**
     * Method hiding panels and creating new versions of them in order to reset
     * all text fields and add new categories. Then showing them again.
     *
     * @param categories - String with categories to be shown in categoriesPanel
     */
    public void showFrame(String categories) {
        addCategoryPanel.setVisible(false);
        categoriesPanel.setVisible(false);

        this.dispose();

        addCategoryPanel = null;
        categoriesPanel = null;
        addCategoryPanel = new AddCategoryPanel();
        categoriesPanel = new CategoriesPanel(categories);

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

    /**
     * Method which return what user want server to do.
     *
     * @return demandForServer - String with information about user demand
     */
    public String getDemandForServer() {
        return addCategoryPanel.getDemandForServer();
    }

    /**
     * Method which reset demandForServer to default value
     */
    public void resetDemandForServer() {
        addCategoryPanel.resetDemandForServer();
    }
}
