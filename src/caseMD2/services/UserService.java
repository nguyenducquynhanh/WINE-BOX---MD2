package caseMD2.services;

import caseMD2.model.User;
import caseMD2.utils.CSVUtils;

import java.util.ArrayList;
import java.util.List;

public class UserService implements IUserServices {
    private static ArrayList<User> listUser = new ArrayList<>();
    private final String fileName = "F:\\MD2\\CaseMD2\\data\\user.csv";

    @Override
    public ArrayList<User> getUsers() {
        if(listUser.size() == 0){
            List<String> records = CSVUtils.read(fileName);

            for (String record : records){
                listUser.add(new User(record));
            }
            return listUser;
        }
        return null;
    }
//
    @Override
    public User loginAdmin(String username, String password) {
        for (User user : listUser) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void addUser(User newUser) {
        listUser.add(newUser);
        update();
    }

    @Override
    public void deleteUser(long id) {
        int index = findIndexById(id);
        listUser.remove(index);
        update();
    }

    public void update(){
        CSVUtils.write(fileName,listUser);
    }
    public int findIndexById(long id) {
        for( int i =0; i< listUser.size();i++) {
            if (listUser.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void update(String value, String options, long id) {

    }

    @Override
    public boolean exist(long id) {
        for (User user : listUser) {
            if (id == user.getId()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkEmailExist(String email) {
        for (User user : listUser) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkPhoneExist(String phone) {
        for (User user : listUser) {
            if (user.getPhone().equals(phone)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkUserNameExist(String userName) {
        for (User user : listUser) {
            if (user.getUsername().equals(userName)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkNameExist (String name) {
        for (User product : listUser) {
            if (name.equals(product.getName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public User getUserById(long id) {
        for (User user : listUser) {
            if (id == user.getId()) {
                return user;
            }
        }
        return null;
    }

    @Override
    public User getUserByUserName(String userName) {
        for (User user : listUser) {
            if (user.getUsername().equals(userName)) {
                return user;
            }
        }
        return null;
    }

    public void showAllUsers() {
        if (listUser.size() == 0) {
            System.out.println(" ");
            System.out.println("===============  Danh sách người dùng trống!  ===============");
            System.out.println(" ");
        } else {
            System.out.println("----------------------------------------------------  LIST CUSTOMER  -----------------------------------------------");

            System.out.printf("     %-10s %-15s %-15s %-15s %-23s %-24s %-15s \n", "ID", "Tên đăng nhập", "Mật khẩu", "Tên", "Số điện thoại", "Email", "Địa chỉ");
            for (User user : listUser) {
                System.out.printf("%-15s %-15s %-15s %-15s %-15s %-32s %-15s\n", user.getId(), user.getUsername(), user.getPassword(), user.getName(), user.getPhone(), user.getEmail(), user.getAddress());
            }
            System.out.println("--------------------------------------------------------------------------------------------------------------------");
            System.out.println(" ");
        }
    }
}