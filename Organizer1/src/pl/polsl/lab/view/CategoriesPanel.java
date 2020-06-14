package pl.polsl.lab.view;
import java.awt.Color;
import pl.polsl.lab.model.Category;

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
 * @version 1
 */

public class CategoriesPanel extends JPanel implements ActionListener{
    /**
     * List of buttons representing categories.
     */
    private final List<JButton> categoriesButtons;
    /**
     * String containing name of category represented by button last clicked by user.
     */
    private String theChosenOneCategory;
    /**
     * Information about need to show TasksPanel.
     */
    private boolean doWeShowTasks;
    
    /**
    * Constructor creating buttons in the image of categories in list given in parameters.
    * 
    * @param categoriesList List with categories to be shown.
    */
    CategoriesPanel(List<Category> categoriesList){
        int  pHeight = 300;
        int pWidth = 600;
        
        doWeShowTasks = false;
        theChosenOneCategory = "";
        setPreferredSize(new Dimension(pWidth,pHeight));
        setLayout(new FlowLayout(FlowLayout.CENTER));

        categoriesButtons = new ArrayList();
        for(int i = 0; i<categoriesList.size(); i++){
            categoriesButtons.add(new JButton((categoriesList.get(i)).getName()));
            categoriesButtons.get(i).addActionListener(this);
            add(categoriesButtons.get(i));
        }           
    }
    
    /**
     * Getter for Category last chosen by user (by clicking button)
     * @return String theChosenOneCategory
     */
    public String getChosenCategory(){
        return theChosenOneCategory;
    }
    
    /**
     * Getter for information if user clicked any category and want to see tasks of this category.
     * @return boolean doWeShowTasks
     */
    public boolean doWeWantToShowTasks(){
        return doWeShowTasks;
    }
    
    /**
     * Method reseting doWeShowTasks to default value.
     */
    public void resetDoWeShowTasks(){
        doWeShowTasks = false;
    }
    
    /**
     * Overrided actionPerformed, where we decide what happens when buttons etc. are clicked
     * 
     * @param e representation of action performed by user (for example clicking button)
     */    
    @Override
	public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();
            
            for(int i = 0; i<categoriesButtons.size(); i++){
                if(source == categoriesButtons.get(i)){
                    theChosenOneCategory = categoriesButtons.get(i).getText();
                    doWeShowTasks = true;
                    break;
                }                
            }
              
	}
        
        
}
