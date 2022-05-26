package caseMD2.services;

import caseMD2.model.User;

import java.util.ArrayList;

public interface IUserServices {
    ArrayList<User> getUsers();

    User loginAdmin(String username, String password);

    void addUser(User newUser);
    void deleteUser(long id);

    void update(String value, String options, long id);

    boolean exist(long id);

    boolean checkEmailExist(String email);

    boolean checkPhoneExist(String phone);

    boolean checkUserNameExist(String userName);

    User getUserById(long id);

    User getUserByUserName(String userName);
}
