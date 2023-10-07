import java.util.*;

// 电影票类
public class Ticket {
    // 属性
    private String ticketId; // 电影票编号
    private Movie movie; // 电影对象
    private Session session; // 场次对象
    private Seat seat; // 座位对象
    private boolean taken; // 是否已取出

    // 构造方法
    public Ticket(Movie movie, Session session, Seat seat) {
        this.movie = movie;
        this.session = session;
        this.seat = seat;
        this.ticketId = generateId(); // 调用生成编号的方法
        this.taken = false; // 默认为未取出
    }
    // 方法

    // 生成编号的方法，无参数，返回一个字符串表示编号
    public String generateId() {
        // 使用 UUID 类来生成一个随机的唯一标识符，并转换为字符串
        return UUID.randomUUID().toString();
    }

    // 打印电影票信息的方法，无参数，无返回值
    public void printInfo() {
        // 打印电影票的所有属性信息
        System.out.println("电影票编号：" + this.ticketId);
        System.out.println("电影：" + this.movie.getTitle());
        System.out.println("放映厅：" + this.session.getHall());
        System.out.println("时间段：" + this.session.getTime());
        System.out.println("价格：" + this.session.getPrice());
        System.out.println("座位：" + this.seat.getRow() + " 行 " + this.seat.getCol() + " 列");
        System.out.println("状态：" + (this.taken ? "已取出" : "未取出"));
    }

    // getter 和 setter 方法，用于获取和设置属性的值
    public String getTicketId() {
        return ticketId;
    }

    public boolean isTaken() {
        return taken;
    }

    public void setTaken(boolean taken) {
        this.taken = taken;
    }
    public double getPrice() {
        // 从场次对象中获取价格，并返回
        return this.session.getPrice();
    }
}
