package Controllers.User;

import Models.Entities.User;
import Services.UserService;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;

public class UserRegisterServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userService = new UserService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = request.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        }

        Gson gsonu = new Gson();
        // Преобразуем JSON в объект User

        try{
            User newUser = gsonu.fromJson(sb.toString(), User.class);
            userService.add(newUser);
            response.setStatus(HttpServletResponse.SC_CREATED);

        }catch (JsonSyntaxException e){

            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

    }
}