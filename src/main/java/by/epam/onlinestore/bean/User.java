package by.epam.onlinestore.bean;

import java.io.Serializable;
import java.util.Objects;

public class User extends Bean implements Serializable {
    private String email;
    private String password;
    private long roleId;
    private long userInformationId;

    public User() {
    }

    public User(long id, String email, String password, long roleId, long userInformationId) {
        super(id);
        this.email = email;
        this.password = password;
        this.roleId = roleId;
        this.userInformationId = userInformationId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public long getUserInformationId() {
        return userInformationId;
    }

    public void setUserInformationId(long userInformationId) {
        this.userInformationId = userInformationId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return this.getId() == user.getId() && roleId == user.roleId
                && userInformationId == user.userInformationId
                && email.equals(user.email)
                && password.equals(user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId(), email, password, roleId, userInformationId);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                "id=" + this.getId() +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", roleId=" + roleId +
                ", userInformationId=" + userInformationId +
                '}';
    }
}


