import Database.Database;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class StudentManagement {

    public static void displayListStudent() {
        List<student> students = new ArrayList<>();

        try (Connection connection = Database.getConnection()) {
            CallableStatement callableStatement = connection.prepareCall("{call get_all_students()}");
            ResultSet resultSet = callableStatement.executeQuery();

            while (resultSet.next()) {
                student student = new student();

                student.setStudentId(resultSet.getInt("student_id"));
                student.setStudentName(resultSet.getString("student_name"));
                student.setDateOfBirth(resultSet.getDate("date_of_birth").toLocalDate());
                student.setEmail(resultSet.getString("email"));

                students.add(student);
            }
        } catch (SQLException e) {
            System.err.println("Lay du lieu khong thanh cong !!!");
            ;
        }
        if (students.isEmpty()) {
            System.out.println("Danh sach sinh vien trong !!!");
        } else {
            System.out.println("Danh sach sinh vien");
            for (student student : students) {
                student.display();
            }
        }
    }


    public void addStudent(Scanner sc) {
        student addStudent = new student();
        addStudent.setStudentName(Valaditor.getString(sc, "Nhap vao fullname"));
        addStudent.setEmail(Valaditor.getString(sc, "Nhap vao email"));
        addStudent.setDateOfBirth(Valaditor.getLocalDate(sc, "Nhap vao birthday dd/mm/yyyy"));
        try (Connection connection = Database.getConnection()) {
            CallableStatement callableStatement = connection.prepareCall("{call add_student(?,?,?)}");
            callableStatement.setString(1, student.getStudentName());
            callableStatement.setString(2, student.getEmail());
            callableStatement.setDate(3, Date.valueOf(student.getDateOfBirth()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateStudent(Scanner sc) {
        int id = Valaditor.getInt(sc, "Nhap vao id muon Update");
        student oldStudent = findById(id);
        if (oldStudent == null) {
            System.out.println("Khong tim thay sinh vien nao !!!");
        }else{
            student updateStudent = new student();
            updateStudent.setStudentId(oldStudent.getStudentId());
            updateStudent.setStudentName(Valaditor.getString(sc, "Nhap vao fullname"));
            updateStudent.setEmail(Valaditor.getString(sc, "Nhap vao email"));
            updateStudent.setDateOfBirth(Valaditor.getLocalDate(sc, "Nhap vao birthday dd/mm/yyyy"));

            try(Connection connection = Database.getConnection()){
                CallableStatement callableStatement =connection.prepareCall("{call update_student(?,?,?,?)}");
                callableStatement.setInt(1,updateStudent.getStudentId());
                callableStatement.setString(2,oldStudent.getStudentName());
                callableStatement.setString(3,updateStudent.getEmail());
                callableStatement.setDate(4,Date.valueOf(updateStudent.getDateOfBirth()));
                boolean rs = callableStatement.executeUpdate() > 0;

                if(rs){
                    System.out.println("Student has been updated");
                }else{
                    System.out.println("Student has not been updated");
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }


        }

    }

    public student findById(int studentId) {
        try (Connection connection = Database.getConnection()) {
            CallableStatement callableStatement = connection.prepareCall("{call find_student_by_in(?)}");
            callableStatement.setInt(1, studentId);
            ResultSet resultSet = callableStatement.executeQuery();
            if (resultSet.next()) {
                student findStudent = new student();
                findStudent.setStudentId(resultSet.getInt("student_id"));
                findStudent.setStudentName(resultSet.getString("student_name"));
                findStudent.setDateOfBirth(resultSet.getDate("date_of_birth").toLocalDate());
                findStudent.setEmail(resultSet.getString("email"));
                return findStudent;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteStudent(Scanner sc) {
        int studentId = Valaditor.getInt(sc,"Nhap vao Id muon xoa");
        student delStudent = findById(studentId);

        if (delStudent == null) {
            System.out.println("Khong tim thay Id can xoa");
        }else{
            try (Connection connection = Database.getConnection()){
                CallableStatement callableStatement = connection.prepareCall("{call delete_student(?)}");
                callableStatement.setInt(1,studentId);
                boolean rs = callableStatement.executeUpdate()>0;

                if (rs){
                    System.out.println("Xoa thanh cong!");
                }else{
                    System.out.println("Khong the xoa!");
                }
            } catch (SQLException e) {
                System.out.println("Xoa that bai!");;
            }
        }
    }

    public void searchStudentByName(Scanner sc){
        String search = Valaditor.getString(sc,"Nhap vao ten ban can tim kiem : ");
        List<student> students = new ArrayList<>();
        try(Connection connection = Database.getConnection()){
            CallableStatement callableStatement = connection.prepareCall("{call search_student_by_name}");
            callableStatement.setString(1,search);
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()){
                student searchStudent = new student();
                searchStudent.setStudentId(resultSet.getInt("student_id"));
                searchStudent.setStudentName(resultSet.getString("full_name"));
                searchStudent.setEmail(resultSet.getString("email"));
                searchStudent.setDateOfBirth(resultSet.getDate("date_of_birth").toLocalDate());
                students.add(searchStudent);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (students.isEmpty()){
            System.out.println("Khong tim thay sinh vien nao");
        }else{
            System.out.println("Ket qua tim kiem");
            for (student Student:students){
                Student.display();
            }
        }
    }



}
