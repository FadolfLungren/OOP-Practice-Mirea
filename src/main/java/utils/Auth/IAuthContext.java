package utils.Auth;

import Models.Entities.User;

public interface IAuthContext {
    public boolean isAuthenticated();
    public boolean isInRole(User.ROLE value);
    public boolean signIn(User.ROLE value);
    public boolean signOut();
}
