package pl.polsl.lab.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pl.polsl.lab.model.Category;
import pl.polsl.lab.model.Organizer;

/**
 * Servlet for showing avalible categories.
 *
 * @author Anna Mach
 * @version 1.1
 */
public class ShowCategories extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method - shows categories avalible.
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
            out.println("<html>\n<body>\n<hl> No categories avalible. <hl>\n");
        } else {
            List<Category> categoriesList = organizer.getCategoriesList();
            for (Category cat : categoriesList) {
                out.println("<form action=\"TasksIndex\" method=\"GET\">");
                out.println("<input type=hidden size=20 name=categoryName value=" + cat.getName() + ">");
                out.println("<input type=\"submit\" value=\"" + cat.getName() + "\"/>");
                out.println("</form> ");
            }
        }

        out.println("<form action=\"index\" method=\"GET\">");
        out.println("<input type=\"submit\" value=\"Return\" />");
        out.println("</form> ");
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
            out.println("<title>Servlet ShowCategories</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1> Sometihng went wrong. </h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}
