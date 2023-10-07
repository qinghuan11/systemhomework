import java.util.*;

// 电影类
public class Movie {
    // 属性
    private String title; // 片名
    private String director; // 导演
    private String cast; // 主演
    private String summary; // 剧情简介
    private int duration; // 时长（分钟）

    // 构造方法
    public Movie(String title, String director, String cast, String summary, int duration) {
        this.title = title;
        this.director = director;
        this.cast = cast;
        this.summary = summary;
        this.duration = duration;
    }

    // 方法
    public static ArrayList<Movie> MovieList = new ArrayList<Movie>();
    // 添加电影方法，无参数，无返回值
    public void addMovie() {
        // 将电影对象添加到一个电影列表中，用于存储所有的电影信息
        MovieList.add(this);
        System.out.println("电影添加成功！");
    }

    // 修改电影方法，接受一个新的电影对象作为参数，无返回值
    public void modifyMovie(Movie newMovie) {
        // 如果新的电影对象不为空，且与原来的电影对象不相同，则修改电影信息，并打印相应的提示信息，否则提示用户输入无效
        if (newMovie != null && !newMovie.equals(this)) {
            this.title = newMovie.title;
            this.director = newMovie.director;
            this.cast = newMovie.cast;
            this.summary = newMovie.summary;
            this.duration = newMovie.duration;
            System.out.println("电影修改成功！");
        } else {
            System.out.println("输入无效，请重新输入！");
        }
    }

    // 删除电影方法，无参数，无返回值
    public void deleteMovie() {
        // 将电影对象从电影列表中移除，并打印相应的提示信息
        MovieList.remove(this);
        System.out.println("电影删除成功！");
    }

    // 查询电影方法，接受一个关键字作为参数，返回一个 Movie 类型的对象
    public static List<Movie> queryMovie(String keyword) {
        // 如果关键字不为空，且与电影的片名、导演或主演相匹配，则返回当前电影对象，否则返回 null
        List<Movie> matchedMovies = new ArrayList<>();
        for (Movie movie : MovieList) {
            if (keyword != null && (movie.getTitle().contains(keyword) || movie.getDirector().contains(keyword) || movie.getCast().contains(keyword))) {
                matchedMovies.add(movie);
            }
        }
        return matchedMovies;
    }

    // 打印电影信息方法，无参数，无返回值
    public void printInfo() {
        // 打印电影的所有属性信息
        System.out.println("片名\t导演\t主演\t剧情简介\t时长（分钟）");
        System.out.println(this.title + "\t" + this.director + "\t" + this.cast + "\t" + this.summary + "\t" + this.duration);
    }

    //通过电影名名查找电影的方法
    public static Movie findMovieByTitle(String title) {
        for (Movie movie : MovieList) {
            if (movie.getTitle().equals(title)) {
                return movie;
            }
        }
        return null;
    }
    // getter 和 setter 方法，用于获取和设置属性的值
    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public String getCast() {
        return cast;
    }

    public String getSummary() {
        return summary;
    }

    public int getDuration() {
        return duration;
    }
}
