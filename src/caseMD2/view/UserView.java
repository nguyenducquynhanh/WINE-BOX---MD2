package caseMD2.view;

import caseMD2.menu.Menu;
import caseMD2.model.Product;
import caseMD2.model.User;
import caseMD2.services.UserService;
import caseMD2.utils.CSVUtils;
import caseMD2.utils.ValidateUtils;

import java.util.Scanner;

import static caseMD2.menu.Menu.scanner;
import static caseMD2.services.ProductService.showAllProducts;

public class UserView {
    //    public static void runUser(){
//        do {
//            Scanner scanner = new Scanner(System.in);
//            UserView userView = new UserView();
//            Menu.menuUser();
//            try {
//                System.out.println("\nChọn chức năng");
//                System.out.println(" ⭆ ");
//                int number = Integer.parseInt(scanner.nextLine());
//                switch (number) {
//                    case 1:
//                        userView.addUser();
//                        break;
//                    case 2:
//                        userView.updateUser();
//                        break;
//                    case 3:
//                        userView.deleteUser();
//                        break;
//                    case 4:
//                        userView.showUser();
//                        break;
//                    case 8:
//                        Menu.mainMenu();
//                        break;
//                    case 0:
//                        System.exit(0);
//                        break;
//                    default:
//                        System.out.println("Chọn chức năng không hợp lệ! Vui lòng chọn lại!");
//                        break;
//                }
//            } catch (Exception e) {
//                System.out.println("Chọn chức năng không hợp lệ! Vui lòng chọn lại!");
//            }
//        }while(true);
//    }
    private static final UserService userService = new UserService();

//    public static void run() {
//        int option;
//        do {
//            show();
//            System.out.println("\nChọn chức năng ");
//            System.out.print(" ⭆ ");
//            option = Integer.parseInt(scanner.nextLine());
//            switch (option) {
//                case 1:
//                    System.out.println("Thêm người dùng");
//                    break;
//                case 2:
//                    System.out.println("Cập nhật người dùng");
//                    break;
//                case 3:
//                    System.out.println("Xóa người dùng");
//                    break;
//                case 4:
//                    System.out.println("Hiện danh sách người dùng");
//                    break;
//                case 9:
//                    System.exit(0);
//                    break;
//                case 8:
//                    break;
//                default:
//                    System.out.println("Chọn chức năng không đúng! Vui lòng chọn lại");
//                    break;
//            }
//        } while (option != 8);
//
//    }

    public static void runUser() {
        do {
            Scanner scanner = new Scanner(System.in);
            UserView managerUserView = new UserView();
            Menu.menuUser();
            try {
                System.out.println("\nChọn chức năng");
                System.out.print(" ⭆ ");
                int number = Integer.parseInt(scanner.nextLine());
                switch (number) {
                    case 1:
                        managerUserView.addUser();
                        break;
                    case 2:
                        managerUserView.updateUser();
                        break;
                    case 3:
                        managerUserView.deleteUser();
                        break;
                    case 4:
                        managerUserView.showUser();
                        break;
                    case 8:
                        Menu.mainMenu();
                        break;
                    case 0:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Chọn chức năng không hợp lệ! Vui lòng chọn lại!");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Chọn chức năng không hợp lệ! Vui lòng chọn lại!");
            }
        } while (true);
    }

    public void showUser() {
        try {
            userService.showAllUsers();
        } catch (Exception e) {
            System.out.println("Chọn chức năng không đúng! Vui lòng chọn lại!");
        }
    }


    public void addUser() {
        System.out.println("* * * * * BẤM 0 ĐỂ QUAY VỀ MENU TRƯỚC * * * * *  ");
        System.out.println("Nhập tên đăng nhập (gồm 6-32 kí tự, bắt đầu là 1 kí tự chữ và không chứa kí tự đặc biệt): ");
        String username = scanner.nextLine();
        while (userService.checkUserNameExist(username) || !ValidateUtils.isUserNameValid(username)) {
            backToPreviousMenu(username,"runUser");
            if (!ValidateUtils.isUserNameValid(username)) {
                System.out.println("Tên đăng nhập không hợp lệ! Vui lòng nhập lại: ");
            } else {
                System.out.println("Tên đăng nhập đã tồn tại! Nhập tên khác: ");
            }
            username = scanner.nextLine();
        }
//        while () {
//            System.out.println("Tên đăng nhập không hợp lệ! Vui lòng nhập lại: ");
//            username = scanner.nextLine();
//        }

        System.out.println("Nhập mật khẩu (ít nhất 8 kí tự, không bao gồm kí tự đặc biệt): ");
        String password = scanner.nextLine();
        while (!ValidateUtils.isPasswordValid(password)) {
            backToPreviousMenu(password,"runUser");
            System.out.println("Mật khẩu không hợp lệ! Vui lòng nhập lại: ");
            password = scanner.nextLine();
        }
        System.out.println("Nhập tên (viết hoa kí tự đầu tiên của mỗi chữ, VD: Quynh Anh): ");
        String name = scanner.nextLine();
        while (userService.checkNameExist(name) || !ValidateUtils.isNameValid(name)) {
            backToPreviousMenu(name,"runUser");
            if (!ValidateUtils.isNameValid(name)) {
                System.out.println("Tên không hợp lệ! Vui lòng nhập lại: ");
            } else {
                System.out.println("Tên đã tồn tại! Nhập tên khác: ");
            }
            name = scanner.nextLine();
        }

        System.out.println("Nhập số điện thoại (bắt đầu bằng số 0, 10-11 số): ");
        String phone = scanner.nextLine();
        while (!ValidateUtils.isPhoneValid(phone) || userService.checkPhoneExist(phone)) {
            backToPreviousMenu(phone,"runUser");
            if(userService.checkPhoneExist(phone)) {
                System.out.println("Số điện đã tồn tại! Vui lòng nhập lại: ");
            }else{
                System.out.println("Số điện thoại không hợp lệ! Vui lòng nhập lại: ");
            }
            phone = scanner.nextLine();
        }

        System.out.println("Nhập email (VD: quynhanh193@gmail.com): ");
        String email = scanner.nextLine();
        while (!ValidateUtils.isEmailValid(email) || userService.checkEmailExist(email)) {
            backToPreviousMenu(email,"runUser");
            if(userService.checkEmailExist(email)) {
                System.out.println("Email đã tồn tại! Vui lòng nhập lại: ");
            }else {
                System.out.println("Email không hợp lệ! Vui lòng nhập lại: ");
            }
            email = scanner.nextLine();
        }
        System.out.println("Nhập địa chỉ (không được để trống thông tin này, VD: Hue): ");
        String address = scanner.nextLine();
        while (address.length() == 0) {
            backToPreviousMenu(address,"runUser");
            System.out.println("Địa chỉ không được để trống! Vui lòng nhập lại địa chỉ khác: ");
            address = scanner.nextLine();
        }
        User user = new User(username, password, name, phone, email, address);
        userService.addUser(user);
        userService.showAllUsers();
    }

    private void backToPreviousMenu(String str, String nameMenu) {
        if (str.equals("0")) {
            if (nameMenu.equals("runUser")) {
                runUser();
            }else if(nameMenu.equals("updateUser")){
                updateUser();
            }
        }
    }


    public void updateUser() {
        do {
            try {
                Menu.menuUpdateUser();
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        updateFullInfo();
                        break;
                    case 2:
                        updateInfo();
                        break;
                    case 0:
                        System.exit(0);
                        break;
                    case 8:
                        runUser();
                        break;
                    default:
                        System.out.println("Vui lòng chọn đúng chức năng!");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Chức năng không đúng! Vui lòng chọn lại!");
            }
        } while (true);
    }

    private void updateInfo() {
        showUser();
        System.out.println("* * * * * BẤM 0 ĐỂ QUAY VỀ MENU TRƯỚC ĐÓ * * * * *");
        long id = 0;
        do {
            try {
                System.out.println("Nhập ID tài khoản muốn sửa (bấm 0 để quay lại menu trước đó): ");
                id = Long.parseLong(scanner.nextLine());
                while (!userService.exist(id)) {
                    if (id == 0) {
                        updateUser();
                    }
                    System.out.println("ID không tồn tại! Vui lòng nhập lại ID: ");
                    id = Long.parseLong(scanner.nextLine());
                }
            } catch (Exception e) {
                System.out.println("ID không hợp lệ!");
            }
        } while (!userService.exist(id));

        User user = userService.getUserById(id);
        selectEditInfoUser(user);
        showUser();
    }

    private void selectEditInfoUser(User user) {
       do {
           Menu.menuUpdateInfoUser();

           int choice = -1;
           do {
               try {
                   choice = Integer.parseInt(scanner.nextLine());
                   while (choice < 0 || (choice > 5 && choice != 8)){
                       System.out.println("Vui lòng chọn đúng chức năng: ");
                       choice = Integer.parseInt(scanner.nextLine());
                   }
               } catch (Exception e) {
                   System.out.println("Chọn lựa chọn phù hợp!");
               }
           } while (choice < 0 || (choice > 5 && choice != 8));

           switch (choice) {
               case 1:
                   String password = checkPasswordUser(user,"selectEditInfoUser");
                   user.setPassword(password);
                   userService.update();
                   break;
               case 2:
                   String name = checkNameUser(user,"selectEditInfoUser");
                   user.setName(name);
                   userService.update();
                   break;
               case 3:
                   String phone = checkPhoneUser(user,"selectEditInfoUser");
                   user.setPhone(phone);
                   userService.update();
                   break;
               case 4:
                   String email = checkEmailUser(user,"selectEditInfoUser");
                   user.setEmail(email);
                   userService.update();
                   break;
               case 5:
                   String address = checkAddressUser(user,"selectEditInfoUser");
                   user.setAddress(address);
                   userService.update();
                   break;
               case 8:
                   updateUser();
                   break;
               case 0:
                   System.exit(0);
                   break;
               default:
                   System.out.println("Nhập lại lựa chọn phù hợp!");
                   break;
           }
           System.out.println("Thông tin của bạn đã được thay đổi thành công!");
       }while (true);
    }

    private void updateFullInfo() {
//        try {
        showUser();
        System.out.println("* * * * * * NHẤN 0 ĐỂ QUAY LẠI MENU GẦN NHẤT * * * * * * ");
        int id = 0;
        do {
            try {
                System.out.println("Nhập ID muốn sửa: ");
                id = Integer.parseInt(scanner.nextLine());
                while (!userService.exist(id)) {
                    if (id == 0) {
                        updateUser();
                    } else {
                        System.out.println("Không tồn tại người dùng có ID này! Vui lòng nhập lại ID khác: ");
                    }
                    id = Integer.parseInt(scanner.nextLine());
                }
            } catch (Exception e) {
                System.out.println("ID không hợp lệ!");
            }
        } while (!userService.exist(id));
        User user = userService.getUserById(id);
        String password = checkPasswordUser(user,"updateUser");
        String name = checkNameUser(user,"updateUser");
        String phone = checkPhoneUser(user,"updateUser");
        String email = checkEmailUser(user,"updateUser");
        String address = checkAddressUser(user,"updateUser");

        if(password.equals("0") || name.equals("0") || phone.equals("0") || email.equals("0") || address.equals("0")){
            System.out.println("Thông tin người dùng chưa được thay đổi!");
        }else {
            user.setPassword(password);
            user.setName(name);
            user.setPhone(phone);
            user.setEmail(email);
            user.setAddress(address);
            System.out.println("Thông tin người dùng đã được chỉnh sửa thành công!");
            showUser();
            userService.update();
        }
//        } catch (Exception e) {
//            System.out.println("Thông tin nhập không hợp lệ!");
//        }
    }

    private String checkAddressUser(User user , String str) {
        System.out.println("Nhập địa chỉ (không được để trống thông tin này, VD: Hue): ");
        String address = scanner.nextLine();
        while (user.getAddress().equals(address) || address.length() == 0) {
            if(str.equals("updateUser")) {
                backToPreviousMenu(address, "updateUser");
            }else if(address.equals("0")) {
                selectEditInfoUser(user);
            }
            if (user.getAddress().equals(address)) {
                System.out.println("Địa chỉ trùng với địa chỉ ban đầu! Vui lòng nhập lại địa chỉ: ");
            } else {
                System.out.println("Địa chỉ không được để trống! Vui lòng nhập lại: ");
            }
            address = scanner.nextLine();
        }
        return address;
    }

    private String checkEmailUser(User user, String str) {
        System.out.println("Nhập email (VD: quynhanh193@gmail.com): ");
        String email = scanner.nextLine();
        while (user.getEmail().equals(email) || !ValidateUtils.isEmailValid(email)) {
            if(str.equals("updateUser")) {
                backToPreviousMenu(email,"updateUser");
            }else if(email.equals("0")) {
                selectEditInfoUser(user);
            }
            if (user.getEmail().equals(email)) {
                System.out.println("Email trùng với email ban đầu! Vui lòng nhập lại email: ");
            } else {
                System.out.println("Email không hơp lệ! Vui lòng nhập lại email (VD: quynhanh193@gmail.com):");
            }
            email = scanner.nextLine();
        }
        return email;
    }

    private String checkPhoneUser(User user, String str) {
        System.out.println("Nhập số điện thoại (bắt đầu bằng số 0, 10-11 số): ");
        String phone = scanner.nextLine();
        while (user.getPhone().equals(phone) || !ValidateUtils.isPhoneValid(phone)) {
            if(str.equals("updateUser")) {
                backToPreviousMenu(phone,"updateUser");
            }else if(phone.equals("0")){
                selectEditInfoUser(user);
            }
            if (user.getPhone().equals(phone)) {
                System.out.println("Số điện thoại trùng với số điện thoại ban đầu! Vui lòng nhập lại SĐT: ");
            } else {
                System.out.println("Mật khẩu phải ít nhất 8 kí tự và không bao gồm kí tự đặc biệt!");
            }
            phone = scanner.nextLine();
        }
        return phone;
    }

    private String checkNameUser(User user, String str) {
        System.out.println("Nhập tên mới (viết hoa kí tự đầu tiên của mỗi chữ, VD: Quynh Anh): ");
        String name = scanner.nextLine();
        while (user.getName().equals(name) || !ValidateUtils.isNameValid(name)) {
            if(str.equals("updateUser")) {
                backToPreviousMenu(name, "updateUser");
            }else if(name.equals("0")){
                selectEditInfoUser(user);
            }
            if (user.getName().equals(name)) {
                System.out.println("Tên trùng với tên ban đầu! Vui lòng nhập lại tên khác: ");
            } else {
                System.out.println("Tên viết hoa kí tự đầu tiên của mỗi chữ (VD: Quynh Anh)! Vui lòng nhập lại tên khác: ");
            }
            name = scanner.nextLine();
        }
        return name;
    }

    private String checkPasswordUser(User user , String str) {
        System.out.println("Nhập mật khẩu mới (ít nhất 8 kí tự, không bao gồm kí tự đặc biệt): ");
        String password = scanner.nextLine();
        while (user.getPassword().equals(password) || !ValidateUtils.isPasswordValid(password)) {
            if(str.equals("updateUser")) {
                backToPreviousMenu(password, "updateUser");
            }else if(password.equals("0")){
                selectEditInfoUser(user);
            }
            if (user.getPassword().equals(password)) {
                System.out.println("Mật khẩu trùng với mật khẩu ban đầu! Vui lòng nhập lại mật khẩu khác: ");
            } else {
                System.out.println("Mật khẩu phải ít nhất 8 kí tự, không bao gồm kí tự đặc biệt! Vui lòng nhập mật khẩu khác: ");
            }
            password = scanner.nextLine();
        }
        return password;
    }

    public void deleteUser() {
        try {
            showUser();
            System.out.println("* * * * * BẤM 0 ĐỂ QUAY LẠI MENU TRƯỚC ĐÓ * * * * * ");
            System.out.println("Nhập ID muốn xóa: ");
            long id = Long.parseLong(scanner.nextLine());
            while (!userService.exist(id)) {
                backToPreviousMenu(Long.toString(id),"runUser");
                System.out.println("ID không tồn tại! Vui lòng nhập lại: ");
                id = Long.parseLong(scanner.nextLine());
            }
            userService.deleteUser(id);
            System.out.println("Người dùng có ID là " + id + " đã được xóa!\n");
            showUser();
        } catch (Exception e) {
            System.out.println("Vui lòng nhập ID hợp lệ!");
            deleteUser();
        }
    }

}

