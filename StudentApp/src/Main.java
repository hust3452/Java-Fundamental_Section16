import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        StudentManagement studentManagement = new StudentManagement();

        do {
            System.out.println("1.Hiển thị danh sách sinh viên.\n" +
                    "2.Thêm mới sinh viên.\n" +
                    "3.Sửa sinh viên.\n" +
                    "4.Xóa sinh viên.\n" +
                    "5.Tìm kiếm sinh viên.\n" +
                    "6.Thoát .");
            System.out.println("Nhập vào sự lựa chọn của bạn");

            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    StudentManagement.displayListStudent();
                    break;
                case 2:
                    studentManagement.addStudent(sc);
                    break;
                case 3:
                    studentManagement.updateStudent(sc);
                    break;
                case 4:
                    studentManagement.deleteStudent(sc);
                    break;
                case 5:
                    studentManagement.searchStudentByName(sc);
                    break;
                case 6:
                    System.out.println("Cam on ban da su dung ");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Vui long nhap su lua chon tu 1-6");
            }

        } while (true);

    }
}