package utils.Filters;

import Models.Entities.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utils.Auth.AuthContext;

import java.io.IOException;

@WebFilter(urlPatterns = {"/admin", "/posts/insert", "/posts/update", "/posts/delete, /me/posts"})
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {


        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse)response;

        AuthContext context = new AuthContext(req);

        if (!context.isAuthenticated())
            ((HttpServletResponse)response).sendRedirect("untitled1hgghgh_war_exploded/users/666");
        else
        if (((HttpServletRequest)request).getServletPath().startsWith("/admin")){
            if (!context.isInRole(User.ROLE.ADMIN)) {
                request.getRequestDispatcher("/views/errors/unauthorized.jsp").forward(request, response);
            }else {
                chain.doFilter(request, response);
            }
        }else {
            ((HttpServletResponse)response).sendRedirect("untitled1hgghgh_war_exploded/users/111");
            //chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

}