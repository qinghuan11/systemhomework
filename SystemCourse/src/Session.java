import java.util.*;

// 场次类
public class Session {
    // 属性
    private int id;
    private Movie movie; // 电影对象
    private int hall; // 放映厅编号
    private String time; // 时间段
    private double price; // 价格
    private Seat[][] seats; // 座位数组

    // 构造方法
    public Session(Movie movie, int id, int hall, String time, double price) {
        this.id = id;
        this.movie = movie;
        this.hall = hall;
        this.time = time;
        this.price = price;
        this.seats = new Seat[7][12]; // 创建一个 7 行 12 列的座位数组
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                seats[i][j] = new Seat(i + 1, j + 1); // 创建每个座位对象，并设置行号和列号
            }
        }
    }

    // 方法
    public static ArrayList<Session> SessionList = new ArrayList<Session>(); // 创建一个空的场次列表

    // 增加场次方法，无参数，无返回值
    public void addSession() {
        // 将场次对象添加到一个场次列表中，用于存储所有的场次信息
        SessionList.add(this);
        System.out.println("场次添加成功！");
    }

    // 修改场次方法，接受一个新的场次对象作为参数，无返回值
    public void modifySession(Session newSession) {
        // 如果新的场次对象不为空，且与原来的场次对象不相同，则修改场次信息，并打印相应的提示信息，否则提示用户输入无效
        if (newSession != null && !newSession.equals(this)) {
            this.id = newSession.id;
            this.movie = newSession.movie;
            this.hall = newSession.hall;
            this.time = newSession.time;
            this.price = newSession.price;
            System.out.println("场次修改成功！");
        } else {
            System.out.println("输入无效，请重新输入！");
        }
    }

    // 删除场次方法，无参数，无返回值
    public void deleteSession() {
        // 将场次对象从场次列表中移除，并打印相应的提示信息
        SessionList.remove(this);
        System.out.println("场次删除成功！");
    }

    // 列出所有场次信息方法，无参数，无返回值
    public static void listAllSessions() {
        // 遍历场次列表，打印每个场次的信息
        System.out.println("场次编号\t电影片名\t时间段\t放映厅编号\t价格");
        for (Session session : SessionList) {
            session.printInfo();
        }
    }

    // 打印场次信息方法，无参数，无返回值
    public void printInfo() {
        // 打印场次的所有属性信息
        System.out.println(this.id + "\t" +this.movie.getTitle() + "\t" + this.time + "\t" + this.hall + "\t" + this.price);

    }

    public static Session findSessionById(int id) {
        for (Session session : SessionList) {
            if (session.getId()==id) {
                return session;
            }
        }
        return null;
    }
    // 查询场次的函数
    public static List<Session> querySession(String keyword) {
        // 创建一个空的 matchedSessions 列表来存储匹配的场次
        List<Session> matchedSessions = new ArrayList<>();
        // 遍历 SessionList 中的每个场次
        for (Session session : SessionList) {
            // 如果关键字不为空，并且与场次的电影标题匹配
            if (keyword != null &&
                    (session.getMovie().getTitle() != null && session.getMovie().getTitle().contains(keyword))) {
                // 将该场次添加到 matchedSessions 列表中
                matchedSessions.add(session);
            }
        }
        return matchedSessions;
    }


    // getter 和 setter 方法，用于获取和设置属性的值
    public int getId() {
        return id;
    }

    public Movie getMovie() {
        return movie;
    }

    public int getHall() {
        return hall;
    }

    public String getTime() {
        return time;
    }

    public double getPrice() {
        return price;
    }

    public Seat[][] getSeats() {
        return seats;
    }
}

