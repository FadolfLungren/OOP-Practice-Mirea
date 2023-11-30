package Controllers;

import Models.DAO.BucketDAO;
import Models.Entities.Bucket;
import Models.Entities.Product;
import Services.ProductService;
import com.google.gson.JsonSyntaxException;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class BucketServlet extends HttpServlet {

    private BucketDAO bucketItemDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        bucketItemDAO = new BucketDAO();

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        String pathInfo = request.getPathInfo();

        if (pathInfo == null || pathInfo.equals("/")) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            
        } else {
            // Получаем ID товара из URL и возвращаем информацию о нем
            try {
                int bucketItemId = Integer.parseInt(pathInfo.substring(1));
                ArrayList<Product> bucketItems;
                bucketItems = bucketItemDAO.getAllByOwnerId(Integer.toString(bucketItemId));
                if (bucketItems.size() != 0) {
                    out.println(bucketItems);
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
            Bucket newBucketItem = gsonp.fromJson(sb.toString(), Bucket.class);
            bucketItemDAO.addBucketItem(newBucketItem);
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
                boolean removedUser = bucketItemDAO.deleteBucketItem(Integer.toString(productId));

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



}