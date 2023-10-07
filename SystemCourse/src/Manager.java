import java.util.*;

// 经理类
public class Manager {
    // 属性
    private String username; // 用户名
    private String password; // 密码

    // 构造方法
    public Manager(String username, String password) {
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
        System.out.println("欢迎您，经理 " + this.username + "！");
        // 进入菜单的主循环
        while (running) {
            // 打印菜单选项
            System.out.println("请选择您要执行的操作：");
            System.out.println("1. 影片管理");
            System.out.println("2. 排片管理");
            System.out.println("3. 返回上一级");
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
                    // 调用影片管理的方法
                    movieManagement();
                    break;
                case 2:
                    // 调用排片管理的方法
                    sessionManagement();
                    break;
                case 3:
                    // 退出菜单
                    running = false;
                    break;
                default:
                    // 提示用户输入无效
                    System.out.println("输入无效，请重新输入！");
            }
        }
        // 打印退出信息
        System.out.println("感谢您使用经理功能菜单，再见！");
    }

    // 影片管理方法，无参数，无返回值
    public void movieManagement() {
        // 创建一个扫描器对象，用于接收用户的输入
        Scanner scanner = new Scanner(System.in);
        // 创建一个布尔值变量，用于控制子菜单的循环
        boolean running = true;
        // 进入子菜单的主循环
        while (running) {
            // 打印子菜单选项
            System.out.println("请选择您要执行的操作：");
            System.out.println("1. 列出所有正在上映影片的信息");
            System.out.println("2. 添加影片的信息");
            System.out.println("3. 修改影片的信息");
            System.out.println("4. 删除影片的信息");
            System.out.println("5. 查询影片的信息");
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

            // 根据用户的选择，执行相应的操作，或退出子菜单
            switch (choice) {
                case 1:
                    // 调用列出所有正在上映影片的信息的方法
                    listAllMovies();
                    break;
                case 2:
                    // 调用添加影片的信息的方法
                    addMovie();
                    break;
                case 3:
                    // 调用修改影片的信息的方法
                    modifyMovie();
                    break;
                case 4:
                    // 调用删除影片的信息的方法
                    deleteMovie();
                    break;
                case 5:
                    // 调用查询影片的信息的方法
                    queryMovie();
                    break;
                case 6:
                    // 退出子菜单
                    running = false;
                    break;
                default:
                    // 提示用户输入无效
                    System.out.println("输入无效，请重新输入！");
            }
        }
    }

    // 排片管理方法，无参数，无返回值
    public void sessionManagement() {
        // 创建一个扫描器对象，用于接收用户的输入
        Scanner scanner = new Scanner(System.in);
        // 创建一个布尔值变量，用于控制子菜单的循环
        boolean running = true;
        // 进入子菜单的主循环
        while (running) {
            // 打印子菜单选项
            System.out.println("请选择您要执行的操作：");
            System.out.println("1. 增加场次");
            System.out.println("2. 修改场次");
            System.out.println("3. 删除场次");
            System.out.println("4. 列出所有场次信息");
            System.out.println("5. 返回上一级");
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
            // 根据用户的选择，执行相应的操作，或退出子菜单
            switch (choice) {
                case 1:
                    // 调用增加场次的方法
                    addSession();
                    break;
                case 2:
                    // 调用修改场次的方法
                    modifySession();
                    break;
                case 3:
                    // 调用删除场次的方法
                    deleteSession();
                    break;
                case 4:
                    // 调用列出所有场次信息的方法
                    Session.listAllSessions();
                    break;
                case 5:
                    // 退出子菜单
                    running = false;
                    break;
                default:
                    // 提示用户输入无效
                    System.out.println("输入无效，请重新输入！");
            }
        }
    }

    public void addMovie() {
        Scanner scanner = new Scanner(System.in);

        // 获取用户输入的电影信息
        System.out.println("请输入片名：");
        String title = scanner.nextLine();
        System.out.println("请输入导演：");
        String director = scanner.nextLine();
        System.out.println("请输入主演：");
        String cast = scanner.nextLine();
        System.out.println("请输入剧情简介：");
        String summary = scanner.nextLine();
        System.out.println("请输入时长（分钟）：");
        int duration = scanner.nextInt();

        // 创建一个新的电影对象
        Movie newMovie = new Movie(title, director, cast, summary, duration);

        // 使用addMovie方法将电影添加到列表中
        newMovie.addMovie();
    }

    public void modifyMovie() {
        // 创建一个扫描器对象，用于接收用户的输入
        Scanner scanner = new Scanner(System.in);
        // 打印提示信息
        System.out.println("请输入您要修改的电影的片名：");
        // 接收用户输入的电影片名，并从中查找匹配的电影对象
        String title = scanner.nextLine();
        Movie movie = Movie.findMovieByTitle(title);
        if (movie != null) {

            // 获取用户输入的电影信息
            System.out.println("请输入导演：");
            String director = scanner.nextLine();
            System.out.println("请输入主演：");
            String cast = scanner.nextLine();
            System.out.println("请输入剧情简介：");
            String summary = scanner.nextLine();
            System.out.println("请输入时长（分钟）：");
            int duration = scanner.nextInt();

            // 创建一个新的电影对象
            Movie newMovie = new Movie(title, director, cast, summary, duration);
            movie.modifyMovie(newMovie);
        } else {
            // 如果没有找到匹配的电影对象，则打印提示信息
            System.out.println("没有找到该片名的电影，请检查输入是否正确！");
        }
    }

    public void queryMovie() {
        Scanner scanner = new Scanner(System.in);
        // 打印提示信息
        System.out.println("请输入您要查询的电影的片名、导演或主演：");
        // 接收用户输入的电影的片名、导演或主演，并从数据库中查找匹配的电影对象
        String keyword = scanner.nextLine();
        List<Movie> matchedMovies = Movie.queryMovie(keyword);
        if (matchedMovies.isEmpty()) {
            System.out.println("对不起，没有找到匹配的电影。");
        } else {
            System.out.println("片名\t导演\t主演\t剧情简介\t时长（分钟）");
            for (Movie movie : matchedMovies) {
                String title = movie.getTitle();
                String director = movie.getDirector();
                String cast = movie.getCast();
                String summary = movie.getSummary();
                int duration = movie.getDuration();
                System.out.println(title + "\t" + director + "\t" + cast + "\t" + summary + "\t" + duration);
            }
        }
    }

    // 删减电影及电影场次的方法，无参数，无返回值
    public void deleteMovie() {
        // 创建一个扫描器对象，用于接收用户的输入
        Scanner scanner = new Scanner(System.in);
        // 打印提示信息
        System.out.println("请输入您要删除的电影的片名：");
        // 接收用户输入的电影片名，查找匹配的电影对象
        String title = scanner.nextLine();
        Movie movie = Movie.findMovieByTitle(title);
        // 如果找到了匹配的电影对象，则打印电影信息，并询问用户是否确认删除
        if (movie != null) {
            movie.printInfo();
            System.out.println("您确定要删除这部电影吗？（Y/N）");
            String confirm = scanner.nextLine();
            // 如果用户确认删除，删除该电影对象
            if (confirm.equalsIgnoreCase("Y")) {
                movie.deleteMovie();
            } else {
                // 如果用户取消删除，则打印提示信息
                System.out.println("电影删除取消！");
            }
        } else {
            // 如果没有找到匹配的电影对象，则打印提示信息
            System.out.println("没有找到该片名的电影，请检查输入是否正确！");
        }
    }

    // 列出所有正在上映影片的信息的方法，无参数，无返回值
    public static void listAllMovies() {
        // 判断电影列表是否为空
        if (Movie.MovieList.isEmpty()) {
            // 如果为空，打印提示信息
            System.out.println("暂无电影信息，请先添加！");
        } else {
            // 如果不为空，打印表头
            System.out.println("片名\t导演\t主演\t剧情简介\t时长（分钟）");
            // 遍历电影列表
            for (Movie movie : Movie.MovieList) {
                // 获取每个电影对象的属性值，并用制表符分隔
                String title = movie.getTitle();
                String director = movie.getDirector();
                String cast = movie.getCast();
                String summary = movie.getSummary();
                int duration = movie.getDuration();
                // 打印每个电影对象的信息
                System.out.println(title + "\t" + director + "\t" + cast + "\t" + summary + "\t" + duration);
            }
        }
    }

    // 增加场次的方法，无参数，无返回值
    public void addSession() {
        Scanner scanner = new Scanner(System.in);

        // 获取用户输入的场次信息
        System.out.println("请输入电影片名：");
        String title = scanner.nextLine();
        // 查找匹配的电影对象
        Movie movie = Movie.findMovieByTitle(title);
        if (movie != null) {
            System.out.println("请输入场次编号：");
            int id = scanner.nextInt();
            System.out.println("请输入放映厅编号（1-5）：");
            int hall = scanner.nextInt();
            System.out.println("请输入时间段（格式为HH:MM）：");
            String time = scanner.next();
            System.out.println("请输入价格（元）：");
            double price = scanner.nextDouble();

            // 创建一个新的场次对象
            Session newSession = new Session(movie, id, hall, time, price);

            // 使用addSession方法将场次添加到列表中
            newSession.addSession();
        } else {
            // 如果没有找到匹配的电影对象，则打印提示信息
            System.out.println("没有找到该片名的电影，请检查输入是否正确！");
        }
    }

    // 修改场次的方法，无参数，无返回值
    public void modifySession() {
        Scanner scanner = new Scanner(System.in);
        // 打印提示信息
        System.out.println("请输入您要修改的场次的编号：");
        // 接收用户输入的场次编号，并查找匹配的场次对象
        int id = scanner.nextInt();
        Session session = Session.findSessionById(id);
        if (session != null) {
            System.out.println("请输入电影片名：");
            String title = scanner.next();
            System.out.println("请输入放映厅编号（1-5）：");
            int hall = scanner.nextInt();
            System.out.println("请输入时间段（格式为HH:MM）：");
            String time = scanner.next();
            System.out.println("请输入价格（元）：");
            double price = scanner.nextDouble();
            // 创建一个新的场次对象
            Movie movie = Movie.findMovieByTitle(title);
            Session newSession = new Session(movie, id, hall, time, price);
            session.modifySession(newSession);
        } else {
            // 如果没有找到匹配的场次对象，则打印提示信息
            System.out.println("没有找到该编号的场次，请检查输入是否正确！");
        }
    }

    // 删减电影场次的方法，无参数，无返回值
    public void deleteSession() {
        // 创建一个扫描器对象，用于接收用户的输入
        Scanner scanner = new Scanner(System.in);
        // 打印提示信息
        System.out.println("请输入您要删减的场次的编号：");
        // 接收用户输入的场次编号，并查找匹配的场次对象
        String id = scanner.nextLine();
        Session session = Session.findSessionById(Integer.parseInt(id));
        // 如果找到了匹配的场次对象，则打印场次信息，并询问用户是否确认删除
        if (session != null) {
            session.printInfo();
            System.out.println("您确定要删除这个场次吗？（Y/N）");
            String confirm = scanner.nextLine();
            // 如果用户确认删除，则删除该场次对象，并打印提示信息
            if (confirm.equalsIgnoreCase("Y")) {
                session.deleteSession();
            } else {
                // 如果用户取消删除，则打印提示信息
                System.out.println("场次删除取消！");
            }
        } else {
            // 如果没有找到匹配的场次对象，则打印提示信息
            System.out.println("没有找到该编号的场次，请检查输入是否正确！");
        }
    }
}