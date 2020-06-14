package pl.polsl.lab.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pl.polsl.lab.exceptions.NullCategoryException;
import pl.polsl.lab.model.Category;
import pl.polsl.lab.model.Organizer;
import pl.polsl.lab.model.Task;

/**
 * Servlet for adding new task to existing category
 *
 * @author Anna Mach
 * @version 1.3
 */
public class AddNewTask extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method - add new task.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();

        Organizer organizer = (Organizer) session.getAttribute("organizer");
        if (organizer == null) {
            out.println("<html>\n<body>\n<hl> Lost data. <hl>\n");
        } else {
            String categoryName = request.getParameter("categoryName");
            try {
                Category category = organizer.getCategoryByName(categoryName);
                String taskName = request.getParameter("taskName");

                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>AddNewTask</title>");
                out.println("</head>");
                out.println("<body>");

                if (taskName.length() == 0) {
                    out.println("<html>\n<body>\n<hl> Name cannot be empty. <hl>\n");
                } else {
                    if (category.getTaskByName(taskName) == null) {
                        switch (request.getParameter("priority")) {
                            case "low":
                                category.addNewTask(taskName, Task.PriorityLevel.LOW);
                                break;
                            case "medium":
                                category.addNewTask(taskName, Task.PriorityLevel.MEDIUM);
                                break;
                            case "high":
                                category.addNewTask(taskName, Task.PriorityLevel.HIGH);
                                break;
                            default:
                                category.addNewTask(taskName, Task.PriorityLevel.LOW);
                                break;

                        }
                        out.println("<hl> Made new task: " + taskName + "<hl>\n");
                    } else {
                        out.println("<hl> Task already exist. <hl>\n");
                    }

                    out.println("<form action=\"TasksIndex\" method=\"GET\">");
                    out.println("<input type=\"hidden\" value=\"" + categoryName + "\" name=categoryName />");
                    out.println("<input type=\"submit\" value=\"Return\" />");
                    out.println("</form> ");

                    out.println("</body>");
                    out.println("</html>");
                }
            } catch (NullCategoryException ex) {
                out.println("<h1> Null Category Exception.</h1>");
            }
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddNewTask</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1> Sometihng went wrong. </h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}
