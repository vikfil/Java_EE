package logic;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter printWriter = response.getWriter();

        WriteAndReadUser person;

        User user = new User();
        user.setName(request.getParameter("name"));
        user.setPassword(request.getParameter("password"));
        user.setEmail(request.getParameter("email"));
        user.setCountry(request.getParameter("country"));

        String value = request.getParameter("storage");

        switch (value) {
            case "database":
                person = new UserDao();
                person.write(user);
                break;

            case "file":
                person = new UserFile();
                person.write(user);
        }
        request.getRequestDispatcher("userHtml.html").include(request, response);

        printWriter.close();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
