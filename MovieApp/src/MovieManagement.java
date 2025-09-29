import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Database.movieDatabase;

public class MovieManagement {
    public static void list_movies() {
        List<Movie> movieList = new ArrayList<>();

        try (Connection connection = movieDatabase.getConnection()) {
            CallableStatement callableStatement = connection.prepareCall("{call get_all_movie()}");
            ResultSet resultSet = callableStatement.executeQuery();

            while (resultSet.next()) {
                Movie movie = new Movie();
                movie.setId(resultSet.getInt("id"));
                movie.setTitle(resultSet.getString("title"));
                movie.setDirector(resultSet.getString("director"));
                movie.setYear(resultSet.getDate("year").toLocalDate());
                movieList.add(movie);
            }
        } catch (Exception e) {
            System.err.println("Lấy dữ liệu không thành công !!!");
        }
        if (movieList.isEmpty()) {
            System.out.println("Danh sách Phim trống !!!");
        } else {
            System.out.println("Danh sách Phim hiện có : ");
            for (Movie movie : movieList) {
                movie.display();
            }
        }
    }

    public void add_movie(Scanner sc) {
        Movie addMovie = new Movie();
        addMovie.setTitle(Valaditor.getString(sc, "Nhap vao Title"));
        addMovie.setDirector(Valaditor.getString(sc, "Nhap vao Director"));
        addMovie.setYear(Valaditor.getLocalDate(sc, "Nhap vao birthday dd/mm/yyyy"));
        try (Connection connection = movieDatabase.getConnection()) {
            CallableStatement callableStatement = connection.prepareCall("{call add_movie(?,?,?)}");
            callableStatement.setString(1, Movie.getTitle());
            callableStatement.setString(2, Movie.getDirector());
            callableStatement.setDate(3, Date.valueOf(Movie.getYear()));
            boolean rs = callableStatement.executeUpdate() > 0;
            if (rs) {
                System.out.println("Them thanh cong du lieu!!!");
            } else {
                System.out.println("Them du lieu that bai!!!");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            ;
        }
    }

    public void update_film(Scanner scanner) {
        int id = Valaditor.getInt(scanner, "Enter movie Id want to be updated : ");
        Movie oldMovie = findById(id);
        if (oldMovie == null) {
            System.out.println("Student not found");
        } else {
            Movie movie = new Movie();
            movie.setId(oldMovie.getId());
            movie.setTitle(Valaditor.getString(scanner, "Enter Title : "));
            movie.setDirector(Valaditor.getString(scanner, "Enter Director : "));
            movie.setYear(Valaditor.getLocalDate(scanner, "Enter Date (dd/mm/yyyy) : "));
            try (Connection connection = movieDatabase.getConnection()) {
                CallableStatement callableStatement = connection.prepareCall("{call update_film(?,?,?,?)}");
                callableStatement.setInt(1, movie.getId());
                callableStatement.setString(2, movie.getTitle());
                callableStatement.setString(3, movie.getDirector());
                callableStatement.setDate(4, Date.valueOf(movie.getYear()));
                boolean rs = callableStatement.executeUpdate() > 0;
                if (rs) {
                    System.out.println("Student has been updated");
                } else {
                    System.out.println("Student has not been updated");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void delete_movie(Scanner scanner) {
        int movieId = Valaditor.getInt(scanner, "Enter movie Id want to be deleted : ");
        Movie movie = findById(movieId);
        if (movie == null) {
            System.out.println("Movie not found");
        }else {
            try (Connection connection = movieDatabase.getConnection()) {
                CallableStatement callableStatement = connection.prepareCall("{call delete_movie(?)}");
                callableStatement.setInt(1,movieId);
                boolean rs = callableStatement.executeUpdate() > 0;
                if (rs){
                    System.out.println("Xóa thành công !");
                }else {
                    System.out.println("Không thể xóa !");
                }
            }catch (Exception e) {
                System.out.println("Xóa thất bại !");
            }
        }
    }


    public Movie findById(int findId) {

        try (Connection connection = movieDatabase.getConnection()) {
            CallableStatement callableStatement = connection.prepareCall("{call find_movie_by_in(?)}");
            callableStatement.setInt(1, findId);
            ResultSet resultSet = callableStatement.executeQuery();
            if (resultSet.next()) {
                Movie movie = new Movie();
                movie.setId(resultSet.getInt("id"));
                movie.setTitle(resultSet.getString("title"));
                movie.setDirector(resultSet.getString("director"));
                movie.setYear(resultSet.getDate("year").toLocalDate());
                return movie;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
