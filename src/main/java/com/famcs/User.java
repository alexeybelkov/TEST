package famcs;

import java.util.Objects;

public class User {

    private String username = "";
    private String login = "";
    private String email = "";
    private String password = "";
    private String role = "USER";
    private boolean adminCheck = false;
    private boolean reg = false;

    User() {

    }
    User(String args[]) {
        if (args.length < 4 || args.length > 5) {
            throw new IllegalArgumentException("Error: wrong number of fields in the argument");
        }

        this.username = args[0];
        this.login = args[1];
        this.email = args[2];
        this.password = args[3];

        if (args.length == 5 && args[4].compareToIgnoreCase("ADMIN") == 0) {
            this.role = args[4];
            this.adminCheck = true;
        }

        this.reg = true;
    }

    User (User u) {
        this.username = u.username;
        this.login = u.login;
        this.email = u.email;
        this.password = u.password;
        this.role = u.role;
        this.adminCheck = u.adminCheck;
        this.reg = u.reg;
    }

    boolean getAdminCheck() {
        return  adminCheck;
    }

    void setLogin(String login) {
        this.login = login;
    }

    String getLogin() {
        return login;
    }

    void setReg(boolean b) {
        this.reg = b;
    }

    boolean getReg() {
        return reg;
    }

    String getPassword() {
        return password;
    }

    public String toString() {
        return username + ";" + login + ";" + email + ";" + password + ";" + role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return adminCheck == user.adminCheck &&
                reg == user.reg &&
                Objects.equals(username, user.username) &&
                Objects.equals(login, user.login) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                Objects.equals(role, user.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, login, email, password, role, adminCheck, reg);
    }
}
