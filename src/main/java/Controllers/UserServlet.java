package Controllers;

import Models.Entities.User;
import Services.UserService;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.MalformedJsonException;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class UserServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userService = new UserService();

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        String pathInfo = request.getPathInfo();

        if (pathInfo == null || pathInfo.equals("/")) {
            String limitParam = request.getParameter("limit");

            int limit = 10;
            if (limitParam != null && !limitParam.isEmpty()) {
                try {
                    limit = Integer.parseInt(limitParam);

                } catch (NumberFormatException e) {
                    // Обработка ошибки в случае, если "limitParam" не является целым числом
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    return;
                }

                if (!(limit>0 && limit<100)){
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    return;
                }

            }
            // Сделать обработку ошибок бд
            // Возвращаем всех пользователей
           out.println(userService.getList(limit));
        } else {
            // Получаем ID пользователя из URL и возвращаем информацию о нем
            try {
                int userId = Integer.parseInt(pathInfo.substring(1));
                User user = userService.getById(Integer.toString(userId));
                if (user != null) {
                    out.println(user);
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                }
            } catch (NumberFormatException e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String pathInfo = request.getPathInfo();

        if (pathInfo != null && !pathInfo.equals("/")) {
            try {
                int userId = Integer.parseInt(pathInfo.substring(1));
                boolean removedUser = userService.remove(Integer.toString(userId));

                if (removedUser) {
                    response.setStatus(HttpServletResponse.SC_OK);
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                }
            } catch (NumberFormatException e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String pathInfo = request.getPathInfo();

        if (pathInfo != null && !pathInfo.equals("/")) {
            try {
                int userId = Integer.parseInt(pathInfo.substring(1));
                User existingUser = userService.getById(Integer.toString(userId));

                if (existingUser != null) {
                    StringBuilder sb = new StringBuilder();
                    try (BufferedReader reader = request.getReader()) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            sb.append(line);
                        }
                    }

                    Gson gson = new Gson();
                    // Преобразуем JSON в объект User и обновляем существующего пользователя
                    try{
                        User newUser = gson.fromJson(sb.toString(), User.class);
                        boolean isUpdated = userService.update(newUser);
                        if (isUpdated){
                            response.setStatus(HttpServletResponse.SC_CREATED);
                        }else{
                            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                        }

                    }catch (JsonSyntaxException e){
                        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    }
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                }
            } catch (NumberFormatException e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
