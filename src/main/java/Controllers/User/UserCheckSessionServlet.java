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

public class UserCheckSessionServlet extends HttpServlet {

    private UserService userService;
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userService = new UserService();

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try{
            AuthContext context = new AuthContext(request);
            if(context.isAuthenticated()){
                response.setStatus(HttpServletResponse.SC_OK);
            }else{
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }


        }catch (JsonSyntaxException e){

            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }



    }

}
