package pl.polsl.lab.view;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import pl.polsl.lab.model.Category;

/**
 * Class containing frame showing tasks of one category and panel for adding new task.
 * 
 * @author Anna Mach
 * @version 1
 */
public class TasksFrame extends JFrame implements ActionListener{
    /**
     * Information about need to show CategoriesFrame.
     */
    private boolean doWeShowCategories;
    /**
     * Panel for adding new task.
     */
    private AddTaskPanel addTaskPanel;
    /**
     * Panel for showing tasks.
     */
    private TasksPanel tasksPanel;
    /**
     * Category where new task will be added and from where shown tasks are taken.
     */
    private final Category category;
    /**
     * Button for going back to CategoriesFrame.
     */
    private final JButton backButton;

     /**
     * Costructor creating panels and button needed by the frame. Also sets needed by frame values.
     * 
     * @param cat Object containing tasks of one category.
     */
	public TasksFrame(Category cat) {
                super("Organizer");
                int fY = 100;
                int fX = 100;
                int fWidth = 600;
                int fHeight = 600;
                
                doWeShowCategories = false;
                
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setSize(fHeight,fWidth);
                setLocation(fX,fY);
                category = cat;
                
                backButton = new JButton("go back to categories");
                tasksPanel = new TasksPanel(category);
                addTaskPanel = new AddTaskPanel(category);

                backButton.addActionListener(this);
                pack();
	}
        
    /**
    * Method hiding panels and creating new versions of them in order to reset all text fields and add new tasks. Then showing them again.
    */        
    public void showFrame(){
            addTaskPanel.setVisible(false);
            tasksPanel.setVisible(false);            
            this.dispose();
            
            addTaskPanel = null;
            tasksPanel = null;
            addTaskPanel = new AddTaskPanel(category);
            tasksPanel = new TasksPanel(category);
            
            add(addTaskPanel, BorderLayout.EAST);
            add(tasksPanel, BorderLayout.WEST);
            add(backButton, BorderLayout.PAGE_END);
            
            pack();
            
            this.show();
    }
      
    /**
     * Method which gets information if user want to go back to frame with categories.
     * @return boolean doWeShowCategories
     */    
    public boolean doWeWantToShowCategories(){
        return doWeShowCategories;
    }
    
    /**
     * Method which reset variable doWeShowCategories to default value.
     */    
    public void resetDoWeShowCategories(){
        doWeShowCategories = false;
    }
    
    /**
     * Method which is getting from addTaskPanel information if user added new task and frame needs to be refreshed to show it.
     * @return boolean got from addTaskPanel
     */    
    public boolean doWeWantToRefreshTasks(){
        return addTaskPanel.doWeWantToRefresh();
    }
    
    /**
     * Method which call method from addCategoryPanel to reset it's variable doWeRefresh to default value.
     */    
    public void resetDoWeRefresh(){
        addTaskPanel.resetDoWeRefresh();
    }
    
    /**
    * Overrided actionPerformed, where we decide what happens when buttons etc. are clicked
    * 
    * @param e representation of action performed by user (for example clicking button)
    */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

		if(source == backButton){
                    doWeShowCategories = true;
                }
    }
}
