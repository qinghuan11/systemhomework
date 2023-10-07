import java.util.*;

// 用户类
public class User {
    // 属性
    private String username; // 用户名
    private String password; // 密码
    private String level; // 用户级别（金牌、银牌、铜牌）
    private double totalAmount; // 累计消费金额
    private int totalCount; // 累计消费次数
    private String phone; // 手机号
    private String email; // 邮箱
    public Ticket ticket;
    private double balance; //余额

    // 构造方法
    public User(String username, String password, String phone, String email) {
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.level = "铜牌"; // 默认为铜牌用户
        this.totalAmount = 0.0; // 默认为零
        this.totalCount = 0; // 默认为零
        this.balance = 0;
    }
    // 方法
    public static ArrayList<User> UserList = new ArrayList<>();

    // 显示菜单方法，无参数，无返回值
    public static void showMenu() {
        // 创建一个扫描器对象，用于接收用户的输入
        Scanner scanner = new Scanner(System.in);
        // 创建一个布尔值变量，用于控制菜单的循环
        boolean running = true;
        // 打印欢迎信息
        System.out.println("欢迎您使用电影购票系统！");
        // 进入菜单的主循环
        while (running) {
            // 打印菜单选项
            System.out.println("请选择您要执行的操作：");
            System.out.println("1. 注册");
            System.out.println("2. 登录");
            System.out.println("3. 退出");
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
            // 根据用户的选择，执行相应的操作，或退出菜单
            switch (choice) {
                case 1:
                    // 提示用户输入用户名、密码、手机号和邮箱
                    System.out.println("请输入用户名（长度不少于5个字符）：");
                    String username = scanner.nextLine();
                    System.out.println("请输入密码（长度大于8，包含大小写字母、数字和标点符号）：");
                    String password = scanner.nextLine();
                    System.out.println("请输入手机号：");
                    String phone = scanner.nextLine();
                    System.out.println("请输入邮箱：");
                    String email = scanner.nextLine();
                    // 调用注册方法
                    register(username, password, phone, email);
                    break;
                case 2:
                    // 提示用户输入用户名和密码
                    User user  = Admin.getOneUser();
                    // 调用登录方法，并接收返回的用户对象
                    System.out.println("请输入密码：");
                     password = scanner.nextLine();
                    // 如果登录成功，则显示登录后的菜单，否则返回主菜单
                    // 如果有这个账号，则进行下一步，密码比对
                    if ((user!=null)&&(user.password.equals(password))) {
                        showMenuAfterLogin(user);
                    }else{
                        System.out.println("账号或密码错误！");
                    }
                    break;
                case 3:
                    // 设置布尔值变量为 false，退出菜单循环
                    running = false;
                    break;
                default:
                    // 提示用户输入的选项不在菜单范围内，让用户重新选择
                    System.out.println("您输入的选项不正确，请重新选择！");
            }
        }
    }

    // 显示登录后的菜单方法，接受一个用户对象作为参数，无返回值
    public static void showMenuAfterLogin(User user) {
        // 创建一个扫描器对象，用于接收用户的输入
        Scanner scanner = new Scanner(System.in);
        // 创建一个布尔值变量，用于控制菜单的循环
        boolean running = true;
        // 打印欢迎信息和用户信息
        System.out.println("欢迎您，" + user.getUsername() + "！");
        System.out.println("您是" + user.getLevel() + "用户，累计消费金额为" + user.getTotalAmount() + "元，累计消费次数为" + user.getTotalCount() + "次。");
        // 进入菜单的主循环
        while (running) {
            // 打印菜单选项
            System.out.println("请选择您要执行的操作：");
            System.out.println("1. 修改密码");
            System.out.println("2. 购票");
            System.out.println("3. 取票");
            System.out.println("4. 查看购票历史");
            System.out.println("5. 充值余额");
            System.out.println("6. 返回上一级菜单");
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
            // 根据用户的选择，执行相应的操作，或返回上一级菜单
            switch (choice) {
                case 1:
                    // 提示用户输入新密码
                    System.out.println("请输入新密码：");
                    String newPassword = scanner.nextLine();
                    // 调用修改密码方法
                    user.changePassword(newPassword);
                    break;
                case 2:
                    Session.listAllSessions();
                    // 提示用户输入片名、场次编号、座位号
                    System.out.println("请输入片名：");
                    String title = scanner.nextLine();
                    System.out.println("请输入场次编号：");
                    int id = Integer.parseInt(scanner.nextLine());

                    // 调用购票方法
                    user.ticket = buyTicket(title,id,user);
                    break;
                case 3:
                    // 提示用户输入电影票编号
                    System.out.println("请输入电影票编号：");
                    String ticketId = scanner.nextLine();
                    // 调用取票方法
                    user.getTicket(ticketId,user);
                    break;
                case 4:
                    // 调用查看购票历史方法
                    user.viewHistory();
                    break;
                case 5:
                    // 提示用户输入充值金额
                    System.out.println("请输入充值金额：");
                    double amount = Double.parseDouble(scanner.nextLine());
                    // 调用充值方法
                    user.recharge(amount);
                    break;
                case 6:
                    // 设置布尔值变量为 false，退出菜单循环
                    running = false;
                    break;
                default:
                    // 提示用户输入的选项不在菜单范围内，让用户重新选择
                    System.out.println("您输入的选项不正确，请重新选择！");
            }
        }
    }


    // 充值方法，接受一个金额作为参数，无返回值
    public void recharge(double amount) {
        // 如果金额大于零，则更新用户的累计消费金额，并打印相应的提示信息，否则提示用户输入无效
        if (amount > 0) {
            this.balance += amount;
            System.out.println("充值成功，您充值了 " + amount + " 元！现在有"+this.balance+"元");
        } else {
            System.out.println("充值失败，金额输入无效，请重新输入！");
        }
    }


    // 注册方法，接受一个用户名、密码、手机号和邮箱作为参数，返回一个用户对象
    public static void register(String username, String password, String phone, String email) {
        // 如果用户名符合要求（长度不少于5个字符），则创建一个用户对象，并将其添加到用户列表中，否则提示用户输入不合法
        if ((username.length() >= 5)&&(password.length() > 8 && password.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[\\p{Punct}]).+$"))) {
            User user = new User(username, password, phone, email);
            UserList.add(user);
            System.out.println("注册成功！");
        } else {
            System.out.println("用户名或密码输入不合法，请重新输入！");
        }
    }


    // 修改密码方法，接受一个新密码作为参数，无返回值
    public void changePassword(String newPassword) {
        // 如果新密码符合要求（长度大于8，包含大小写字母、数字和标点符号），则修改密码，否则提示用户输入不合法
        if (newPassword.length() > 8 && newPassword.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[\\p{Punct}]).+$")) {
            this.password = newPassword;
            System.out.println("密码修改成功！");
        } else {
            System.out.println("密码输入不合法，请重新输入！");
        }
    }
    public void resetPassword() {
        this.password = "123456";
        System.out.println("密码成功重置为：123456");
    }
    // 购票方法，接受一个电影票对象作为参数，无返回值
    public static Ticket buyTicket(String title, int id, User user) {
        Session session = Session.findSessionById(id);
        if ( (session==null)||(!title.equals(session.getMovie().getTitle()))) {
            System.out.println("对不起，您输入的片名或场次编号有误，请重新输入！");
            return null;
        }

        // 判断用户输入的支付金额是否大于等于电影票的原价，如果不是，打印提示信息并返回 null。
        if(user.balance < FrontDesk.getActualAmount(session,user)) {
            System.out.println("对不起，您输入的支付金额不足，请充值！");
            return null;
        }
        user.balance -= FrontDesk.getActualAmount(session,user);
        Seat.printSeatInfo(session.getSeats());
        System.out.println("请输入行和列选择你的座位：");
        Scanner scanner = new Scanner(System.in);
        int row,col ;
        while (true) {
            try {
                row = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("行输入无效，请重新输入！");
            }
        }
        while (true) {
            try {
                col = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("列输入无效，请重新输入！");
            }
        }
        if(row<=0||row>7||col>12||col<=0){
            System.out.println("对不起，您输入的座位有误，请重新输入！");
            return null;
        }
        if (!session.getSeats()[row-1][col-1].getStatus().equals("O")){
            System.out.println("该座位已被占用，请选择其他座位！");
            return null;
        }
        session.getSeats()[row-1][col-1].lockSeat();
        Ticket ticket = new Ticket(session.getMovie(),session, session.getSeats()[row-1][col-1]);
        user.totalAmount += ticket.getPrice();
        user.totalCount++;
        user.updateLevel(); // 更新用户级别
        ticket.printInfo(); // 打印电影票信息
        Seat.printSeatInfo(session.getSeats());
        // 返回电影票对象
        return ticket;
    }


    // 取票方法，接受一个电影票编号作为参数，无返回值
    public void getTicket(String ticketId,User user) {
        //    如果电影票编号不为空，且与用户购买的电影票编号匹配，且电影票未被取出，则打印电影票信息，并将电影票状态设为已取出，否则提示用户取票失败
        if (ticketId != null && ticketId.equals(user.ticket.getTicketId()) && !user.ticket.isTaken()) {
            user.ticket.setTaken(true);
            user.ticket.printInfo();
            System.out.println("取票成功！");
        } else {
            System.out.println("取票失败，请检查电影票编号或状态！");
        }
    }


    // 查看购票历史方法，无参数，无返回值
    public void viewHistory() {
        // 如果用户有购买过电影票，则打印用户的购票历史信息，否则提示用户没有购票历史
        if (this.totalCount > 0) {
            System.out.println("您的购票历史如下：");
            System.out.println("用户名：" + this.username);
            System.out.println("用户级别：" + this.level);
            System.out.println("累计消费金额：" + this.totalAmount);
            System.out.println("累计消费次数：" + this.totalCount);
        } else {
            System.out.println("您没有购票历史！");
        }
    }


    // 更新用户级别方法，无参数，无返回值
    public void updateLevel() {
        // 根据用户的累计消费金额和次数，更新用户的级别，并打印相应的提示信息
        if (this.totalAmount >= 1000 && this.totalCount >= 10) {
            this.level = "金牌";
            System.out.println("恭喜您升级为金牌用户，享受 88 折优惠！");
        } else if (this.totalAmount >= 500 && this.totalCount >= 5) {
            this.level = "银牌";
            System.out.println("恭喜您升级为银牌用户，享受 95 折优惠！");
        } else {
            this.level = "铜牌";
            System.out.println("您是铜牌用户，不享受优惠！");
        }
    }

    //通过用户名查找用户的方法
    public static User findUserByUsername(String username) {
        for (User user : UserList) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    // getter 和 setter 方法，用于获取和设置属性的值
    public String getUsername() {
        return username;
    }

    public String getLevel() {
        return level;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
