package utils.Auth;

import Models.Entities.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class AuthContext implements IAuthContext {
    private HttpSession session;

    public AuthContext(HttpServletRequest request) {
        session = request.getSession();
    }

    public boolean isAuthenticated() {
        User.ROLE role = (User.ROLE)session.getAttribute("ROLE");
        if (role != null) {
            return true;
        }
        return false;
    }

    public boolean isInRole(User.ROLE value) {
        User.ROLE role = (User.ROLE)session.getAttribute("ROLE");
        if (role != null) {
            if(role.equals(value)) {
                return true;
            }
            return false;
        }
        return false;
    }

    public boolean signIn(User.ROLE desirableRole) {
        User.ROLE role = (User.ROLE)session.getAttribute("ROLE");
        if (role == null) {
            session.setAttribute("ROLE", desirableRole);
            return true;
        }
        return false;
    }

    public boolean signOut() {
        User.ROLE role = (User.ROLE)session.getAttribute("ROLE");
        if (role != null) {
            session.removeAttribute("ROLE");
            return true;
        }
        return false;
    }
}