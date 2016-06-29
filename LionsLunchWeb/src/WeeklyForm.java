import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by justinkang on 6/27/16.
 */
@WebServlet(name = "WeeklyForm")
//@WebServlet("/WeeklyForm")
public class WeeklyForm extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String eid = request.getParameter("eid");
        if (name.equals("justin")){
            request.getSession().setAttribute("name", name);
            response.sendRedirect("Confirmation.jsp");
        } else {
            request.setAttribute("error", "Unknown person, please try again");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/LunchForm.jsp").forward(request, response);
        //not sure why we need the webinf part ill try it later without it and with it to see if it actually changes anything
    }
}
