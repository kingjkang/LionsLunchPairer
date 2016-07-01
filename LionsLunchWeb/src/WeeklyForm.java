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
//@WebServlet(name = "WeeklyForm")
@WebServlet("/WeeklyForm")
public class WeeklyForm extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String eid = request.getParameter("eid");
        String phoneNumber = request.getParameter("phoneNumber");
        String emailAddress = request.getParameter("email");
        String year = request.getParameter("classification");
        String major = request.getParameter("major");
        String gender = request.getParameter("gender");
        String personality = request.getParameter("personality");
        String recurrence = request.getParameter("recurrence");

        phoneNumber = phoneNumber.replaceAll("\\D", "");
        emailAddress = emailAddress.replaceAll("\\s+", "");

        if (name.matches("[a-zA-Z]+(\\s+[a-zA-Z]+)*") &&
                eid.matches("[a-zA-Z0-9]{3,10}") &&
                phoneNumber.length() == 10 &&
                emailAddress.contains("@") &&
                yearIsValid(year)
                ){

            request.getSession().setAttribute("name", name);
            request.getSession().setAttribute("eid", eid);
            request.getSession().setAttribute("phone", phoneNumber);
            request.getSession().setAttribute("email", emailAddress);
            request.getSession().setAttribute("year", year);
            request.getRequestDispatcher("/Confirmation.jsp").forward(request, response);
        }

        else {
            request.setAttribute("error", "Unknown person, please try again");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/LunchForm.jsp").forward(request, response);
        //not sure why we need the webinf part ill try it later without it and with it to see if it actually changes anything
    }

    private boolean yearIsValid(String year){
        if (year.equals("superSenior") || year.equals("senior") || year.equals("junior") || year.equals("sophomore") || year.equals("freshman") || year.equals("other")){
            return true;
        }
        return false;
    }

}
