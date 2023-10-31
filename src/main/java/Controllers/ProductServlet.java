package Controllers;

import Models.Entities.Product;
import Services.ProductService;
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

public class ProductServlet extends HttpServlet {

    private ProductService productService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        productService = new ProductService();

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

            // Возвращаем все товары
            out.println(productService.getList(limit));
        } else {
            // Получаем ID товара из URL и возвращаем информацию о нем
            try {
                int productId = Integer.parseInt(pathInfo.substring(1));
                Product product = productService.getById(Integer.toString(productId));
                if (product != null) {
                    out.println(product);
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



        Gson gsonp = new Gson();
        // Преобразуем JSON в объект product

        try{
            Product newProduct = gsonp.fromJson(sb.toString(), Product.class);
            productService.addProduct(newProduct);
            response.setStatus(HttpServletResponse.SC_CREATED);
            System.out.println("Received JSON: " + sb.toString());
        }catch (JsonSyntaxException e){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String pathInfo = request.getPathInfo();

        if (pathInfo != null && !pathInfo.equals("/")) {
            try {
                int productId = Integer.parseInt(pathInfo.substring(1));
                boolean removedUser = productService.remove(Integer.toString(productId));

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
                int productId = Integer.parseInt(pathInfo.substring(1));
                Product existingUser = productService.getById(Integer.toString(productId));

                if (existingUser != null) {
                    StringBuilder sb = new StringBuilder();
                    try (BufferedReader reader = request.getReader()) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            sb.append(line);
                        }
                    }

                    Gson gson = new Gson();
                    // Преобразуем JSON в объект Product и обновляем существующий товар
                    try{
                        Product newProduct = gson.fromJson(sb.toString(), Product.class);
                        boolean isUpdated = productService.update(newProduct);
                        if (isUpdated){
                            response.setStatus(HttpServletResponse.SC_CREATED);
                        }else{
                            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                        }

                    }catch (JsonSyntaxException e){
                        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                        return;
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
