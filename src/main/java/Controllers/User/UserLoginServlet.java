package Controllers.User;

import Models.Entities.AuthForm;
import Models.Entities.User;
import Services.UserService;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.Auth.AuthContext;

import java.io.BufferedReader;
import java.io.IOException;

public class UserLoginServlet extends HttpServlet  {

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
            AuthForm authData = gsonu.fromJson(sb.toString(), AuthForm.class);
            User user = userService.getByLogin(authData.getLogin());
            if (user==null){
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

            } else {
                AuthContext context = new AuthContext(request);
                context.signIn(user.getRole());
                response.setStatus(HttpServletResponse.SC_OK);
            }

        }catch (JsonSyntaxException e){

            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }



    }

}
