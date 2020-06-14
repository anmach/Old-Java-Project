package pl.polsl.lab.tcp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import pl.polsl.lab.model.Category;
import pl.polsl.lab.exceptions.NullCategoryException;
import pl.polsl.lab.model.Organizer;
import pl.polsl.lab.model.Task;

/**
 * The server class servicing a single connection
 *
 * @author Anna Mach
 * @version 1.2
 */
class Service implements Closeable {

    /**
     * socket representing connection to the client
     */
    private Socket socket;
    /**
     * buffered input character stream
     */
    private BufferedReader input;
    /**
     * Formatted output character stream
     */
    private PrintWriter output;

    /**
     * Constructor of Service
     *
     * @param socket socket representing connection to the client
     * @throws IOException
     */
    public Service(Socket socket) throws IOException {
        this.socket = socket;
        output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    /**
     * Method realizing the service
     * @param organizer - object of Organizer class
     */
    public void realize(Organizer organizer) {
        try {
            String str;
            String catName = "";
            String taskName = "";
            String priority = "";
            output.println("FROM SERVER :: You have connected to Organizer. Write HELP to get list of avalible commands");
            Category category = null;
            Task task = null;

            OUTER:
            while (true) {
                str = input.readLine();
                System.out.println("Client sent: " + str);
                switch (str.toUpperCase()) {
                    case "QUIT":
                        output.println("FROM SERVER :: Clients will close soon.");
                        break OUTER;
                    case "GET CATEGORIES":
                        output.println("FROM SERVER :: " + organizer.getNamesOfCategoriesInOneString());
                        break;
                    case "HELP":
                        output.println("FROM SERVER :: Avalible commands: ADD CATEGORY, ADD CATEGORY ;; category_name, ADD TASK, ADD TASK ;; category ;; name_of_task ;; priority(low, medium or high),"
                                + " DELETE ALL DONE TASKS, DELETE DONE TASKS, DELETE DONE TASKS ;; category_of_tasks, HELP, MAKE TASK DONE, MAKE TASK DONE ;; category ;; task ;; priority(low, medium or high),"
                                + " SHOW CATEGORIES, SHOW TASKS, QUIT. Notice that commands ADD TASK, ADD CATEGORY, DELETE DONE TASKS, MAKE TASK DONE and SHOW TASKS will communicate you with the server,"
                                + " giving additional server to get informations that may be given in parameters.");
                        break;
                    case "ADD CATEGORY":
                        output.println("FROM SERVER :: Enter the name of new category.");
                        str = input.readLine();
                        try {
                            category = organizer.getCategoryByName(str);
                            output.println("FROM SERVER :: Category already exist");
                        } catch (NullCategoryException e) {
                            organizer.addCategory(str);
                            output.println("FROM SERVER :: Added new category - " + str);
                        }
                        break;
                    case "ADD TASK":
                        output.println("FROM SERVER :: Started procedure for adding task. Enter the name of category to which you want to add task:");
                        catName = input.readLine();
                        try {
                            category = organizer.getCategoryByName(catName);
                            output.println("FROM SERVER :: Category was found. Enter the name of new task:");
                            str = input.readLine();

                            category.addNewTask(str, Task.PriorityLevel.LOW);
                            output.println("FROM SERVER :: Added new task - " + str);
                        } catch (NullCategoryException e) {
                            output.println("FROM SERVER :: ERROR :: Category does not exist.");
                        }
                        break;
                    case "SHOW CATEGORIES":
                        output.println("FROM SERVER :: " + organizer.getNamesOfCategoriesInOneString());
                        break;
                    case "SHOW TASKS":
                        output.println("FROM SERVER :: Started procedure for showing tasks. Enter the name of category which tasks you want to see:");
                        str = input.readLine();
                        try {
                            category = organizer.getCategoryByName(str);

                            output.println("FROM SERVER :: " + category.getTasksNamesAndStatusInOneString());
                        } catch (NullCategoryException e) {

                            output.println("FROM SERVER :: ERROR :: Category does not exist.");
                        }

                        break;
                    case "MAKE TASK DONE":
                        output.println("FROM SERVER :: Started procedure for making task done. Enter the name of category which contains this task:");
                        str = input.readLine();
                        try {
                            category = organizer.getCategoryByName(str);
                            output.println("FROM SERVER :: Category was found. Enter the name of the task:");
                            str = input.readLine();
                            task = category.getFirstUndoneTaskByName(str, Task.PriorityLevel.LOW);
                            if (task != null) {
                                task.makeTaskDone();
                                output.println("FROM SERVER :: Task " + str + " made done.");
                            } else {
                                output.println("FROM SERVER :: ERROR :: Task does not exist.");
                            }
                        } catch (NullCategoryException e) {
                            output.println("FROM SERVER :: ERROR :: Category does not exist.");
                        }
                        break;
                    case "DELETE DONE TASKS":
                        output.println("FROM SERVER :: Started procedure for deleting tasks. Enter the name of category which tasks you want to delete:");
                        str = input.readLine();
                        try {
                            category = organizer.getCategoryByName(str);
                            category.deleteDoneTasks();
                            output.println("FROM SERVER :: Done tasks has been deleted.");
                        } catch (NullCategoryException e) {
                            output.println("FROM SERVER :: ERROR :: Category does not exist.");
                        }
                        break;
                    case "DELETE ALL DONE TASKS":
                        organizer.deleteAllDoneTasks();
                        output.println("FROM SERVER :: Deleted all done tasks");
                        break;
                    default:
                        String[] request = str.split(" ;; ");
                        int i = 1;
                        boolean isItGet = false;
                        boolean isItDelete = false;
                        boolean isItMake = false;
                        catName = "";
                        taskName = "";
                        REQUESTLOOP:
                        for (String demand : request) {
                            switch (i) {
                                case 0:
                                    catName = "";
                                    taskName = "";
                                    isItGet = false;
                                    isItMake = false;
                                    isItDelete = false;
                                    break REQUESTLOOP;
                                case 1:
                                    if (demand.toUpperCase().equals("ADD CATEGORY")) {
                                        i = 2;
                                    } else if (demand.toUpperCase().equals("ADD TASK")) {
                                        i = 3;
                                    } else if (demand.toUpperCase().equals("GET TASKS")) {
                                        i = 6;
                                    } else if (demand.toUpperCase().equals("MAKE TASK DONE")) {
                                        i = 7;
                                    } else if (demand.toUpperCase().equals("DELETE DONE TASKS")) {
                                        i = 10;
                                    } else {
                                        i = 0;
                                    }
                                    break;
                                case 2: //ADD CATEGORY
                                    catName = demand;
                                    i = 0;
                                    break;
                                case 3: //ADD TASK
                                    catName = demand;
                                    i++;
                                    break;
                                case 4: //ADD TASK
                                    taskName = demand;
                                    i++;
                                    break;
                                case 5: //ADD TASK
                                    priority = demand;
                                    i = 0;
                                    break;
                                case 6: //GET TASKS
                                    catName = demand;
                                    i = 0;
                                    isItGet = true;
                                    break;
                                case 7: //MAKE TASK DONE
                                    catName = demand;
                                    i++;
                                    break;
                                case 8: //MAKE TASK DONE
                                    taskName = demand;
                                    i++;
                                    break;
                                case 9: //MAKE TASK DONE
                                    priority = demand;
                                    isItMake = true;
                                    i = 0;
                                    break;
                                case 10: //DELETE DONE TASKS
                                    catName = demand;
                                    isItDelete = true;
                                    i = 0;
                                    break;
                                default:
                                    break;
                            }
                        }
                        if (isItGet) {
                            try {
                                category = organizer.getCategoryByName(catName);
                                output.println("FROM SERVER ::  " + category.getTasksNamesAndStatusInOneString());
                            } catch (NullCategoryException e) {
                                output.println("FROM SERVER :: ERROR :: Category does not exist.");
                            }
                        } else if (isItMake) {
                            try {
                                category = organizer.getCategoryByName(catName);
                                switch (priority.toUpperCase()) {
                                    case "LOW":
                                        task = category.getFirstUndoneTaskByName(taskName, Task.PriorityLevel.LOW);
                                        break;
                                    case "MEDIUM":
                                        task = category.getFirstUndoneTaskByName(taskName, Task.PriorityLevel.MEDIUM);
                                        break;
                                    case "HIGH":
                                        task = category.getFirstUndoneTaskByName(taskName, Task.PriorityLevel.HIGH);
                                        break;
                                }

                                if (task != null) {
                                    task.makeTaskDone();
                                    output.println("FROM SERVER :: Task " + taskName + " was set as done.");
                                } else {
                                    output.println("FROM SERVER :: ERROR :: Could not find task.");
                                }

                            } catch (NullCategoryException e) {
                                output.println("FROM SERVER :: ERROR :: Category does not exist.");
                            }
                        } else if (isItDelete) {
                            try {
                                category = organizer.getCategoryByName(catName);
                                category.deleteDoneTasks();
                                output.println("FROM SERVER :: Deleted done tasks in " + catName);
                            } catch (NullCategoryException e) {
                                output.println("FROM SERVER :: ERROR :: Category does not exist.");
                            }
                        } else if (!taskName.equals("")) {
                            try {
                                category = organizer.getCategoryByName(catName);
                                switch (priority.toUpperCase()) {
                                    case "LOW":
                                        category.addNewTask(taskName, Task.PriorityLevel.LOW);
                                        output.println("FROM SERVER :: Added new task - " + taskName);
                                        break;
                                    case "MEDIUM":
                                        category.addNewTask(taskName, Task.PriorityLevel.MEDIUM);
                                        output.println("FROM SERVER :: Added new task - " + taskName);
                                        break;
                                    case "HIGH":
                                        category.addNewTask(taskName, Task.PriorityLevel.HIGH);
                                        output.println("FROM SERVER :: Added new task - " + taskName);
                                        break;
                                    default:
                                        output.println("FROM SERVER :: ERROR :: Wrong priority level");
                                        break;
                                }
                            } catch (NullCategoryException e) {
                                output.println("FROM SERVER :: ERROR :: Category does not exist.");
                            }
                        } else if (!catName.equals("")) {
                            organizer.addCategory(catName);
                            output.println("FROM SERVER :: Added new category - " + catName);
                        } else {
                            output.println("FROM SERVER :: ERROR :: Unknown command. Check if you have entered too much spaces or have not added it where it should be - it is a common mistake");
                        }
                        break;
                }
            }
            System.out.println("Closing...");
        } catch (IOException e) {
            output.println("FROM SERVER :: ERROR :: Caught IOException");
            System.err.println(e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                output.println("FROM SERVER :: ERROR :: Caught IOException");
                System.err.println(e.getMessage());
            }
        }
    }

    /**
     * Overrided method close()
     * @throws IOException 
     */
    @Override
    public void close() throws IOException {
        if (socket != null) {
            socket.close();
        }
    }
}
