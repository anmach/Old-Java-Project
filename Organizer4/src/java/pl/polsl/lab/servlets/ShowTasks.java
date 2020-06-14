package pl.polsl.lab.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
 * Servlet for showing tasks.
 *
 * @author Anna Mach
 * @version 1.3
 */
public class ShowTasks extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method - shows tasks existing in category.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html; charset=ISO-8859-2");
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();

        Organizer organizer = (Organizer) session.getAttribute("organizer");
        if (organizer == null) {
            out.println("<html>\n<body>\n<hl> Lost data. <hl>\n");
        } else {

            String cat = request.getParameter("categoryName");
            try {
                Category category = organizer.getCategoryByName(cat);
                List<Task> taskList = category.getTasksList();
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet ShowTasks</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<form action=\"ShowTasks\" method=\"POST\">");
                for (Task task : taskList) {
                    if (task.isDone()) {
                        out.println("<input type=\"checkbox\" name=\"" + task.getName() + "\" checked />" + task.getName() + "<br>");
                    } else {
                        out.println("<input type=\"checkbox\" name=\"" + task.getName() + "\"/>" + task.getName() + "<br>");
                    }
                }
                out.println("<input type=\"hidden\" value=\"" + cat + "\" name=categoryName />");
                out.println("<input type=\"submit\" value=\"Save checks\" />");
                out.println("</form> ");

                out.println("<form action=\"TasksIndex\" method=\"GET\">");
                out.println("<input type=\"hidden\" value=\"" + cat + "\" name=categoryName />");
                out.println("<input type=\"submit\" value=\"Return\" />");
                out.println("</form> ");

                out.println("</body>");
                out.println("</html>");
            } catch (NullCategoryException ex) {
                out.println("<h1> Null Category Exception.</h1>");
            }
        }
    }

    /**
     * Handles the HTTP <code>POST</code> metho - update which tasks are done and which are undone.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=ISO-8859-2");
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();

        String action = null;

        Organizer organizer = (Organizer) session.getAttribute("organizer");
        if (organizer != null) {
            String cat = request.getParameter("categoryName");
            try {
                Category category = organizer.getCategoryByName(cat);
                List<Task> taskList = category.getTasksList();

                for (Task task : taskList) {
                    action = request.getParameter(task.getName());
                    if (action != null) {
                        task.makeTaskDone();
                    } else {
                        task.makeTaskUndone();
                    }
                }
            } catch (NullCategoryException ex) {
                out.println("<h1> Null Category Exception.</h1>");
            }
        }

        doGet(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
