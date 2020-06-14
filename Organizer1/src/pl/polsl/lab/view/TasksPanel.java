package pl.polsl.lab.view;
import pl.polsl.lab.model.Task;
import pl.polsl.lab.model.Category;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.BoxLayout;

import java.util.List;
import java.util.ArrayList;

/**
 * Class showing user tasks created in category as checkboxes so user can check it as done.
 * 
 * @author Anna Mach
 * @version 1
 */

public class TasksPanel extends JPanel implements ActionListener{
    /**
     * List for check boxes representing tasks.
     */
    private final List<JCheckBox> tasksCheckBoxes;
    /**
     * Category from which shown tasks are taken.
     */
    private Category category;
    
    /**
     * Constructor creating checkboxes with tasks.
     * @param category object containing all tasks that will be shown
     */
    TasksPanel(Category category){
        int pWidth = 600;
        int pHeight = 600;
        
        this.category = category;
        List<Task> tasks = category.getTasksList();
        
        setPreferredSize(new Dimension(pWidth,pHeight));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        tasksCheckBoxes = new ArrayList();
        for(int i = 0; i<tasks.size(); i++){
            tasksCheckBoxes.add(new JCheckBox((tasks.get(i)).getName()));
            tasksCheckBoxes.get(i).addActionListener(this);
            
            if(tasks.get(i).isDone())
                tasksCheckBoxes.get(i).setSelected(true);
            add(tasksCheckBoxes.get(i));
        }
    }
    
    /**
     * Overrided actionPerformed, where we decide what happens when buttons etc. are clicked
     * 
     * @param e representation of action performed by user (for example clicking button)
     */    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
       
        for(int i = 0; i<tasksCheckBoxes.size(); i++){
            if(source == tasksCheckBoxes.get(i)){
                if(tasksCheckBoxes.get(i).isSelected()){
                    if(category.getFirstUndoneTaskByName(tasksCheckBoxes.get(i).getText())!=null)
                        category.getFirstUndoneTaskByName(tasksCheckBoxes.get(i).getText()).makeTaskDone();
                }
                else{
                    if(category.getLastDoneTaskByName(tasksCheckBoxes.get(i).getText())!=null)
                        category.getLastDoneTaskByName(tasksCheckBoxes.get(i).getText()).makeTaskUndone();
                    }
                        
                break;
            }
        }
    }
}
