package Controllers;

import Models.Entities.User;
import Services.UserService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
            }

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

}
