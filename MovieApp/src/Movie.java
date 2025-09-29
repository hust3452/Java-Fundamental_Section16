import java.time.LocalDate;
import java.util.Date;

public class Movie {
    private int id;
    private static String title;
    private static String director;
    private static LocalDate year;

    public Movie() {
    }

    public Movie(int id, String title, String director, LocalDate year) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static LocalDate getYear() {
        return year;
    }

    public void setYear(LocalDate year) {
        this.year = year;
    }

    public static String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void display() {
        System.out.println("ID: " + id);
        System.out.println("Tiêu đề: " + title);
        System.out.println("Đạo diễn: " + director);
        System.out.println("Năm phát hành: " + year);
        System.out.println("---------------------------");
    }
}
