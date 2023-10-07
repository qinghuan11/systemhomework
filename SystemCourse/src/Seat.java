// 座位类

public class Seat {
    // 属性
    private int row; // 行号
    private int col; // 列号
    private String status; // 状态（"O"表示空闲，"X"表示占用）

    // 构造方法
    public Seat(int row, int col) {
        this.row = row;
        this.col = col;
        this.status = "O"; // 默认为空闲
    }

    // 方法

    // 锁定座位方法，无参数，无返回值
    public void lockSeat() {
        // 如果座位状态为空闲，则将状态设为占用，并打印相应的提示信息，否则提示用户该座位已被占用
        if (this.status.equals("O")) {
            this.status = "X";
            System.out.println("座位 " + this.row + " 行 " + this.col + " 列 已被锁定！");
        } else {
            System.out.println("该座位已被占用，请选择其他座位！");
        }
    }

    // 打印座位信息方法，无参数，无返回值
    public static void printSeatInfo(Seat[][] seats) {
        // 获取数组的行数和列数
        int row = seats.length;
        int col = seats[0].length;
        // 打印表头，显示列号
        System.out.print("  ");
        for (int j = 0; j < col; j++) {
            System.out.print((j + 1) + " ");
        }
        System.out.println();
        // 遍历每一行座位
        for (int i = 0; i < row; i++) {
            // 打印行号
            System.out.print((i + 1) + " ");
            // 遍历每一列座位
            for (int j = 0; j < col; j++) {
                // 打印座位状态
                System.out.print(seats[i][j].status + " ");
            }
            System.out.println();
        }
    }


    // getter 和 setter 方法，用于获取和设置属性的值
    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public String getStatus() {
        return status;
    }

}
