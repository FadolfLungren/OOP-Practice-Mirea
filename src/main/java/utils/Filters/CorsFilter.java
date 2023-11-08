package utils.Filters;

import Models.Entities.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utils.Auth.AuthContext;

import java.io.IOException;

public class CorsFilter implements Filter{

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {

        if(response instanceof HttpServletResponse){
            HttpServletResponse alteredResponse = ((HttpServletResponse)response);
            addCorsHeader(alteredResponse);
        }

        filterChain.doFilter(request, response);
    }

    private void addCorsHeader(HttpServletResponse response){
        //TODO: externalize the Allow-Origin
        response.addHeader("Access-Control-Allow-Origin", "http://localhost:3001");
        response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
        response.addHeader("Access-Control-Allow-Headers","X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept, Cache-Control, Pragma");
        response.addHeader("Access-Control-Max-Age", "1728000");
        response.addHeader("Access-Control-Allow-Credentials", "true");
        response.addHeader("Access-Control-Expose-Headers", "true");


    }

    @Override
    public void destroy() {}

    @Override
    public void init(FilterConfig filterConfig)throws ServletException{}
}
