import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        do {
            MovieManagement movieManagement = new MovieManagement();

            do {
                System.out.println("1.Thêm mới Phim.\n" +
                        "2.Liệt Kê Phim.\n" +
                        "3.Sửa Phim.\n" +
                        "4.Xóa Phim.\n" +
                        "5.Thoát .");
                System.out.println("Nhập vào sự lựa chọn của bạn");

                int choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1:
                        movieManagement.add_movie(sc);
                        break;
                    case 2:
                        movieManagement.list_movies();
                        break;
                    case 3:
                        movieManagement.update_film(sc);
                        break;
                    case 4:
                        movieManagement.delete_movie(sc);
                        break;
                    case 5:
                        System.out.println("Cam on ban da su dung ");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Vui long nhap su lua chon tu 1-6");
                }

            } while (true);
        }while(true);
        }
    }