package by.epam.onlinestore.bean;

import java.io.Serializable;
import java.util.Objects;

public class UserInformation extends Bean implements Serializable {
    private String userName;
    private String userSurname;
    private String userPatronymicName;
    private long userPhoneNumber;

    public UserInformation() {
    }

    public UserInformation(long id, String name, String surname,
                           String patronymic, long phoneNumber) {
        super(id);
        this.userName = name;
        this.userSurname = surname;
        this.userPatronymicName = patronymic;
        this.userPhoneNumber = phoneNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public String getUserPatronymicName() {
        return userPatronymicName;
    }

    public void setUserPatronymicName(String userPatronymicName) {
        this.userPatronymicName = userPatronymicName;
    }

    public long getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(long phoneNumber) {
        this.userPhoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInformation userInformation = (UserInformation) o;
        return this.getId() == userInformation.getId() && userName.equals(userInformation.userName)
                && userSurname.equals(userInformation.userSurname)
                && userPatronymicName.equals(userInformation.userPatronymicName)
                && userPhoneNumber == userInformation.userPhoneNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId(), userName, userSurname, userPatronymicName, userPhoneNumber);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                "id=" + this.getId() +
                ", name='" + userName + '\'' +
                ", surname='" + userSurname + '\'' +
                ", patronymic='" + userPatronymicName + '\'' +
                ", userPhoneNumber='" + userPhoneNumber + '\'' +
                '}';
    }
}
