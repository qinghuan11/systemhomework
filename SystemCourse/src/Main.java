import java.util.*;

// 主类
public class Main {
    // 主方法
    public static void main(String[] args) {
        // 创建一个扫描器对象，用于接收用户的输入
        Scanner scanner = new Scanner(System.in);
        // 创建一个布尔值变量，用于控制程序的循环
        boolean running = true;
        // 打印欢迎信息
        System.out.println("欢迎使用影城管理系统！");
        // 进入程序的主循环

        while (running) {
            // 打印主菜单选项
            System.out.println("请选择您的角色：");
            System.out.println("1. 管理员");
            System.out.println("2. 经理");
            System.out.println("3. 前台");
            System.out.println("4. 用户");
            System.out.println("5. 退出");
            // 接收用户的输入，并转换为整数
            int choice ;
            while (true) {
                try {
                    choice = Integer.parseInt(scanner.nextLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("输入无效，请重新输入！");
                }
            }

            // 根据用户的选择，创建相应的角色对象，并跳转到相应的功能界面，或退出程序
            switch (choice) {
                case 1:
                    // 创建一个管理员对象，并调用其登录方法，如果登录成功，则跳转到管理员功能界面，否则提示用户登录失败
                    Admin admin = new Admin("admin", "ynuinfo#777");
                    System.out.println("请输入账号：");
                    String adminUsername = scanner.nextLine();
                    System.out.println("请输入密码：");
                    String adminPassword = scanner.nextLine();
                    if (admin.login(adminUsername, adminPassword)) {
                        admin.showMenu();
                    } else {
                        System.out.println("登录失败，请检查账号或密码！");
                    }
                    break;
                case 2:
                    // 创建一个经理对象，并调用其登录方法，如果登录成功，则跳转到经理功能界面，否则提示用户登录失败
                    Manager manager = new Manager("manager", "123456");
                    System.out.println("请输入账号：");
                    String managerUsername = scanner.nextLine();
                    System.out.println("请输入密码：");
                    String managerPassword = scanner.nextLine();
                    if (manager.login(managerUsername, managerPassword)) {
                        manager.showMenu();
                    } else {
                        System.out.println("登录失败，请检查账号或密码！");
                    }
                    break;
                case 3:
                    // 创建一个前台对象，并调用其登录方法，如果登录成功，则跳转到前台功能界面，否则提示用户登录失败
                    FrontDesk front = new FrontDesk("front", "123456");
                    System.out.println("请输入账号：");
                    String frontUsername = scanner.nextLine();
                    System.out.println("请输入密码：");
                    String frontPassword = scanner.nextLine();
                    if (front.login(frontUsername, frontPassword)) {
                        front.showMenu();
                    } else {
                        System.out.println("登录失败，请检查账号或密码！");
                    }
                    break;
                case 4:
                    // 跳转到用户注册或登录界面
                    User.showMenu();
                    break;
                case 5:
                    // 退出程序
                    running = false;
                    break;
                default:
                    // 提示用户输入无效
                    System.out.println("输入无效，请重新输入！");
            }
        }
        // 关闭扫描器对象
        scanner.close();
        // 打印退出信息
        System.out.println("感谢您使用影城管理系统，再见！");
    }
}