import java.util.*;

//前台类
public class FrontDesk {
    // 属性
    private String username; // 用户名
    private String password; // 密码

    // 构造方法
    public FrontDesk(String username, String password) {
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
        System.out.println("欢迎您，前台 " + this.username + "！");
        // 进入菜单的主循环
        while (running) {
            // 打印菜单选项
            System.out.println("请选择您要执行的操作：");
            System.out.println("1. 列出所有正在上映影片的信息");
            System.out.println("2. 列出所有场次的信息");
            System.out.println("3. 列出指定电影各场次的信息");
            System.out.println("4. 列出指定电影和场次的信息");
            System.out.println("5. 售票功能");
            System.out.println("6. 退出");
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
                    // 调用列出所有正在上映影片的信息的方法
                    Manager.listAllMovies();
                    break;
                case 2:
                    // 调用列出所有场次的信息的方法
                    Session.listAllSessions();
                    break;
                case 3:
                    // 调用列出指定电影各场次的信息的方法
                    queryMovieAndSession();
                    break;
                case 4:
                    // 调用列出指定电影和场次的信息的方法
                    System.out.println("请输入片名：");
                    String title = scanner.nextLine();
                    System.out.println("请输入场次编号：");
                    int id ;
                    while (true) {
                        try {
                            id = Integer.parseInt(scanner.nextLine());
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("场次编号输入无效，请重新输入！");
                        }
                    }
                    listSessionInfo(title,id);
                    break;
                case 5:
                    // 提示用户输入片名、场次编号、用户名/手机号、支付金额
                    System.out.println("请输入片名：");
                    title = scanner.nextLine();
                    System.out.println("请输入场次编号：");
                    while (true) {
                        try {
                            id = Integer.parseInt(scanner.nextLine());
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("场次编号输入无效，请重新输入！");
                        }
                    }
                    System.out.println("请输入用户名：");
                    String name = scanner.nextLine();
                    System.out.println("请输入手机号：");
                    String phone = scanner.nextLine();
                    // 调用售票功能方法
                   sellTicket(title,id,name,phone);
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
        System.out.println("感谢您使用前台功能菜单，再见！");
    }

    // 售票功能方法
    public Ticket sellTicket(String title, int id, String name, String phone) {
        Session session = Session.findSessionById(id);
        if (session==null||!title.equals(session.getMovie().getTitle())) {
            System.out.println("对不起，您输入的片名或场次编号有误，请重新输入！");
            return null;
        }
        // 判断用户输入的用户名，手机号是否与用户对象匹配，如果不匹配，打印提示信息并返回 null。
        User user = User.findUserByUsername(name);
        if (user==null||!phone.equals(user.getPhone())) {
            System.out.println("对不起，您输入的用户名或手机号有误，请重新输入！");
            return null;
        }

        // 判断用户余额是否大于等于电影票价，如果不是，打印提示信息并返回 null。
        if (user.getBalance() < getActualAmount(session,user)) {
            System.out.println("对不起，您输入的支付金额不足，请充值！");
            return null;
        }

        user.setBalance(user.getBalance()-getActualAmount(session,user));
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
        Session.findSessionById(id).getSeats()[row-1][col-1].lockSeat();
        Ticket ticket = new Ticket(session.getMovie(),session, Session.findSessionById(id).getSeats()[row-1][col-1]);

        user.setTotalAmount(user.getTotalAmount()+ticket.getPrice());
        user.setTotalCount(user.getTotalCount()+1);
        user.updateLevel(); // 更新用户级别
        ticket.printInfo(); // 打印电影票信息
        Seat.printSeatInfo(session.getSeats());
        user.ticket = ticket;
        // 返回电影票对象
        return ticket;
    }

    public void queryMovieAndSession() {
        Scanner scanner = new Scanner(System.in);
        // 打印提示信息
        System.out.println("请输入您要查询的场次的电影片名：");
        // 接收用户输入的电影片名和场次编号，并从数据库中查找匹配的场次对象
        String keyword = scanner.nextLine();
        List<Session> matchedSessions = Session.querySession(keyword);
        if (matchedSessions.isEmpty()) {
            System.out.println("对不起，没有找到匹配的场次。");
        } else {
            System.out.println("场次编号\t电影片名\t时间段\t放映厅编号\t价格");
            for (Session session : matchedSessions) {
                int id = session.getId();
                String title = session.getMovie().getTitle();
                String time = session.getTime();
                int hall = session.getHall();
                double price = session.getPrice();
                System.out.println(id + "\t" +title + "\t" + time + "\t" + hall + "\t" + price);
            }
        }
    }

    // 列出指定电影和场次的信息
    public void listSessionInfo(String title, int id) {
        boolean found = false;
        // 遍历所有场次
        for (Session session : Session.SessionList) {
            // 如果找到了匹配的电影和场次
            if (session.getMovie().getTitle().equals(title) && session.getId() == id) {
                found = true;
                // 打印该场次的信息
                System.out.println("以下是指定电影和场次的信息：");
                session.printInfo(); // 调用场次类的 toString 方法打印场次对象
                // 打印该场次座位信息
                System.out.println("以下是该场次座位信息：");
                Seat.printSeatInfo(session.getSeats()); // 调用场次类的 printSeatInfo 方法打印座位信息
                // 跳出循环
                break;
            }
        }
        // 如果没有找到匹配的电影和场次
        if (!found) {
            // 打印提示信息
            System.out.println("没有找到指定电影和场次，请检查您的输入是否正确！");
        }
    }
    public static double getActualAmount(Session session,User user){
        if (session == null) {
            System.out.println("对不起，场次信息有误！");}
            if ( user == null) {
                System.out.println("对不起，用户信息有误！");
            return 0;
        }
        // 根据用户的级别，计算出实际支付的金额
        double actualAmount;
        switch (user.getLevel()) {
            case "金牌":
                actualAmount = session.getPrice() * 0.88; // 金牌用户享受 88 折
                break;
            case "银牌":
                actualAmount = session.getPrice() * 0.95; // 银牌用户享受 95 折
                break;
            case "铜牌":
                actualAmount = session.getPrice(); // 铜牌用户不享受优惠
                break;
            default:
                System.out.println("用户级别有误！");
                return 0;
        }
        return actualAmount;
    }
}