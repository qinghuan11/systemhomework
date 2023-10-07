import java.util.*;

//管理员类
public class Admin {
    // 属性
    private String username; // 用户名
    private String password; // 密码

    // 构造方法
    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // 方法

    // 登录方法，接受一个用户名和密码作为参数，返回一个布尔值表示是否登录成功
    public boolean login(String username, String password) {
        // 如果用户名和密码匹配，返回 true，否则返回 false
        return this.username.equals(username) && this.password.equals(password);
    }

    // 显示菜单方法，无参数，无返回值
    public void showMenu() {
        // 创建一个扫描器对象，用于接收用户的输入
        Scanner scanner = new Scanner(System.in);
        // 创建一个布尔值变量，用于控制菜单的循环
        boolean running = true;
        // 打印欢迎信息
        System.out.println("欢迎您，管理员 " + this.username + "！");
        // 进入菜单的主循环
        while (running) {
            // 打印菜单选项
            System.out.println("请选择您要执行的操作：");
            System.out.println("1. 修改管理员密码");
            System.out.println("2. 列出所有用户信息");
            System.out.println("3. 查看单个用户信息");
            System.out.println("4. 删除单个用户信息");
            System.out.println("5. 帮助用户重置密码");
            System.out.println("6. 返回上一级");
            // 接收用户的输入，并转换为整数
            int choice;
            while (true) {
                try {
                    choice = Integer.parseInt(scanner.nextLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("输入无效，请重新输入！");
                }
            }

            // 根据用户的选择，执行相应的操作，或退出菜单
            switch (choice) {
                case 1:
                    // 调用修改管理员密码的方法
                    System.out.println("请输入新密码：");
                    this.changePassword();
                    break;
                case 2:
                    // 列出所有用户信息的方法
                    listAllUsers();
                    break;
                case 3:
                    // 查看单个用户信息的方法
                    User user = getOneUser();
                    System.out.println("用户名: " + user.getUsername());
                    System.out.println("用户级别: " + user.getLevel());
                    System.out.println("累计消费金额: " + user.getTotalAmount());
                    System.out.println("累计消费次数: " + user.getTotalCount());
                    System.out.println("手机号: " + user.getPhone());
                    System.out.println("邮箱: " + user.getEmail());
                    break;
                case 4:
                    // 删除单个用户信息的方法
                    deleteOneUser();
                    break;
                case 5:
                    // 帮助用户重置密码的方法
                    resetPassword();
                    break;
                case 6:
                    // 退出菜单
                    running = false;
                    break;
                default:
                    // 提示用户输入无效
                    System.out.println("输入无效，请重新输入！");
            }
        }
        // 打印退出信息
        System.out.println("感谢您使用管理员功能菜单，再见！");
    }

    // 列出所有用户信息
    public void listAllUsers() {
        for (User user : User.UserList) {
            System.out.println("用户名: " + user.getUsername());
            System.out.println("用户级别: " + user.getLevel());
            System.out.println("累计消费金额: " + user.getTotalAmount());
            System.out.println("累计消费次数: " + user.getTotalCount());
            System.out.println("手机号: " + user.getPhone());
            System.out.println("邮箱: " + user.getEmail());
            System.out.println("------------------------");
        }
    }

    // 帮助用户重置密码的方法，无参数，无返回值
    public void resetPassword() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入您要帮助的用户的用户名：");
        // 接收用户输入的用户名，并从动态数组中查找匹配的用户对象
        String username = scanner.nextLine();
        User user = User.findUserByUsername(username);
        // 如果找到了匹配的用户对象，则询问用户是否确认重置密码
        if (user != null) {
            System.out.println("您确定要帮助 " + user.getUsername() + " 重置密码吗？（Y/N）");
            String confirm = scanner.nextLine();
            // 如果用户确认重置密码，则调用用户对象的 resetPassword 方法，并打印提示信息
            if (confirm.equalsIgnoreCase("Y")) {
                user.resetPassword();
                System.out.println("密码重置成功！");
            } else {
                // 如果用户取消重置密码，则打印提示信息
                System.out.println("密码重置取消！");
            }
        } else {
            // 如果没有找到匹配的用户对象，则打印提示信息
            System.out.println("没有找到该用户名的用户，请检查输入是否正确！");
        }
    }

    // 用户修改密码的方法
    public void changePassword() {
        Scanner scanner = new Scanner(System.in);
        String newPassword = scanner.nextLine();
        // 如果新密码符合要求（长度大于8，包含大小写字母、数字和标点符号），则修改密码，否则提示用户输入不合法
        if (newPassword.length() > 8 && newPassword.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[\\p{Punct}]).+$")) {
            this.password = newPassword;
            System.out.println("密码修改成功！");
        } else {
            System.out.println("密码输入不合法，请重新输入！");
        }
    }

    // 查询单个用户信息
    public static User getOneUser() {
        System.out.println("请输入用户用户名：");
        Scanner scanner = new Scanner(System.in);
        String username = scanner.nextLine();
        for (User user : User.UserList) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null; // 如果没有找到用户，返回null
    }

    // 删除单个用户信息
    public static void deleteOneUser() {
        System.out.println("请输入用户用户名：");
        Scanner scanner = new Scanner(System.in);
        String username = scanner.nextLine();
        for (int i = 0; i < User.UserList.size(); i++) {
            if (User.UserList.get(i).getUsername().equals(username)) {
                System.out.println("您确定要删除这个账号吗？（Y/N）");
                String confirm = scanner.nextLine();
                // 如果用户确认删除，则从动态数组中删除账号，并打印提示信息
                if (confirm.equalsIgnoreCase("Y")) {
                    User.UserList.remove(i);
                    ;
                    System.out.println("账号删除成功！");
                } else {
                    // 如果用户取消删除，则打印提示信息
                    System.out.println("账号删除取消！");
                }
                break;
            }
        }
    }
}