package by.epam.onlinestore.bean;

import java.io.Serializable;
import java.util.Objects;

public class Role extends Bean implements Serializable {
    private String roleName;

    public Role() {
    }

    public Role(long id, String name) {
        super(id);
        this.roleName = name;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return this.getId() == role.getId() && roleName.equals(role.roleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId(), roleName);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                "id=" + this.getId() +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
