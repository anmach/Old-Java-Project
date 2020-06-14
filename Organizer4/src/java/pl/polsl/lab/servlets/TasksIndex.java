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

/**
 * Servlet for choosing option from avalible actions for tasks (showing them or
 * adding new task)
 *
 * @author Anna Mach
 * @version 1.3
 */
public class TasksIndex extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method - shows avalible actions for tasks.
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
            String cat = request.getParameter("categoryName");
            try {
                Category category = organizer.getCategoryByName(cat);

                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>" + cat + "</title>");
                out.println("</head>");
                out.println("<body>");

                out.println("<form action=\"AddNewTask\" method=\"GET\">");
                out.println("<input type=\"hidden\" value=\"" + cat + "\" name=categoryName />");
                out.println("<p>New task:<input type=text size=20 name=taskName></p>");
                out.println("<select name=\"priority\">");
                out.println("<option value=\"low\">Low priority</option>");
                out.println("<option value=\"medium\">Medium priority</option>");
                out.println("<option value=\"high\">High priority</option>");
                out.println("</select>");
                out.println("<input type=\"submit\" value=\"Add new task\" />");
                out.println("</form>");

                out.println("<hr>");

                out.println("<h1>Tasks</h1>");
                out.println("<form action=\"ShowTasks\" method=\"GET\">");
                out.println("<input type=\"hidden\" value=\"" + cat + "\" name=categoryName />");
                out.println("<input type=\"submit\" value=\"Show\" />");
                out.println("</form>");

                out.println("<form action=\"DeleteDoneTasks\" method=\"GET\">");
                out.println("<input type=\"hidden\" value=\"" + cat + "\" name=categoryName />");
                out.println("<input type=\"submit\" value=\"Delete done tasks\" />");
                out.println("</form> ");

                out.println("<form action=\"ShowDeletedTasks\" method=\"GET\">");
                out.println("<input type=\"hidden\" value=\"" + cat + "\" name=categoryName />");
                out.println("<input type=\"submit\" value=\"Show deleted tasks\" />");
                out.println("</form> ");

                out.println("<form action=\"ShowCategories\" method=\"GET\">");
                out.println("<input type=\"submit\" value=\"Return\" />");
                out.println("</form> ");

                out.println("</body>");
                out.println("</html>");
            } catch (NullCategoryException ex) {
                out.println("<html>\n<body>\n<hl> Null Category Exception. <hl>\n");;
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
            out.println("<title>Servlet DeleteDoneTasks</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1> Something is not right... </h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

}
