import java.time.LocalDate;
import org.w3c.dom.ls.LSOutput;

public class student {
    private int studentId;
    private static String studentName;
    private static LocalDate dateOfBirth;
    private static String email;

    public student() {
    }

    public student(int studentId, String studentName, LocalDate dateOfBirth, String email) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public static String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public static LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public static String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void display(){
        System.out.printf("Student ID: %d | studentName: %s | DateOfBirth: %s | Email: %s \n" , studentId,studentName,dateOfBirth,email);
    }
}
