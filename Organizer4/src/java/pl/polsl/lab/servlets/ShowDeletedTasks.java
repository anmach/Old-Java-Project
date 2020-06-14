package pl.polsl.lab.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet for showing deleted tasks
 *
 * @author Anna Mach
 * @version 1.1
 */
public class ShowDeletedTasks extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method - shows deleted tasks, about
     * which information is given in cookies.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        String categoryName = request.getParameter("categoryName");

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Show Deleted Tasks</title>");
        out.println("</head>");
        out.println("<body>");

        Cookie[] cookies = request.getCookies();
        for (Cookie cook : cookies) {
            if (cook.getValue().equals("deletedTask")) {
                out.println("<hl>" + cook.getName() + "<hl>\n <hr>");
            }
        }

        out.println("<form action=\"TasksIndex\" method=\"GET\">");
        out.println("<input type=\"hidden\" value=\"" + categoryName + "\" name=categoryName />");
        out.println("<input type=\"submit\" value=\"Return\" />");
        out.println("</form> ");

        out.println("</body>");
        out.println("</html>");
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
            out.println("<title>Servlet ShowDeletedTasks</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1> Something is not right... </h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}
