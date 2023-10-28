package Controllers;


import Models.DAO.UserDAO;
import Models.Entities.User;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        UserDAO userInterface = new UserDAO();
        User user = userInterface.getById("1");
        System.out.println(user);
    }
}
