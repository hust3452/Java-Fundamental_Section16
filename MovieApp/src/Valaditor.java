import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Valaditor {
    public static String getString(Scanner sc, String suggest) {
        String input = "";
        do {
            System.out.println(suggest);
            input = sc.nextLine();
            if (input.isEmpty()) {
                System.out.println("Can not Blank");
            } else {
                break;
            }
        } while (true);
        return input;
    }

    public static LocalDate getLocalDate(Scanner sc,String suggest) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        do {
            String input = getString(sc,suggest);
            try{
                return LocalDate.parse(input,formatter);
            }catch (Exception e) {
                System.out.println("Can not parse date");
            }
        }while (true);
    }
    public static int getInt(Scanner scanner, String suggest) {
        String input = "" ;
        do {
            input = getString(scanner, suggest);
            try {
                return Integer.parseInt(input);
            }catch (Exception e) {
                System.out.println("Can not parse number !");
            }
        }while (true);
    }


}
