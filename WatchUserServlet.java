package logic;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class WatchUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter printWriter = response.getWriter();
        printWriter.println("<a href='userHtml.html'>Add New User</a");
        printWriter.println("<h1>User List</h1>");

        WriteAndReadUser writeAndReadUser;
        String person = request.getParameter("stor");

        switch (person) {
            case "database":
                writeAndReadUser = new UserDao();
                List<User> list = writeAndReadUser.readUser();
                printWriter.print("<table border='1' width='100%'");
                printWriter.print("<tr><th>Id</th><th>Name</th><th>Password</th><th>Email</th><th>Country</th></tr>");
                for(User e:list){
                    printWriter.print("<tr><td>" + e.getId() +"</td><td>"+e.getName()+"</td><td>"+e.getPassword()+"</td><td>"+e.getEmail()+"</td><td>"+e.getCountry()+"</td></tr>");
                }
                printWriter.print("</table>");

                printWriter.close();
                break;
            case "file":
                writeAndReadUser = new UserFile();
                List<User> listFile = writeAndReadUser.readUser();
                printWriter.print("<table border='1' width='100%'");
                printWriter.print("<tr><th>Name</th><th>Password</th><th>Email</th><th>Country</th></tr>");
                for(User e:listFile){
                    printWriter.print("<tr><td>"+e.getName()+"</td><td>"+e.getPassword()+"</td><td>"+e.getEmail()+"</td><td>"+e.getCountry()+"</td></tr>");
                }
                printWriter.print("</table>");

                printWriter.close();
                break;
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
}
