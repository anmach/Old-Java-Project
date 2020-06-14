package pl.polsl.lab.servlets;

import pl.polsl.lab.model.Organizer;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pl.polsl.lab.exceptions.NullCategoryException;

/**
 * Class of the servlet that add new category.
 *
 * @author Anna Mach
 * @version 1.3
 */
public class AddNewCategory extends HttpServlet {

    /**
     * Constructor of class AddNewCategory
     */
    public AddNewCategory() {
    }

    /**
     * Handles the HTTP <code>GET</code> method - adds new category if it
     * doesn't already exist.
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
            organizer = new Organizer();
            session.setAttribute("organizer", organizer);
        }

        String categoryName = request.getParameter("categoryName");

        if (categoryName.length() == 0) {
            out.println("<html>\n<body>\n<hl> Name cannot be empty. <hl>\n");
        } else {
            try {
                if (organizer.getCategoryByName(categoryName) != null) {
                    out.println("<html>\n<body>\n<hl> Category already exist. <hl>\n");
                }
            } catch (NullCategoryException ex) {
                organizer.addCategory(categoryName);
                out.println("<html>\n<body>\n<hl> Made new category: " + categoryName + "<hl>\n");

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
        response.setContentType("text/plain; charset=ISO-8859-2");

        PrintWriter out = response.getWriter();

        out.println("Passed parameters:");

        Enumeration enumeration = request.getParameterNames();
        while (enumeration.hasMoreElements()) {
            String name = (String) enumeration.nextElement();
            out.println(name + " = " + request.getParameter(name));
        }
    }
}
