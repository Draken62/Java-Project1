package guest;

import java.io.Serializable;
import java.util.Scanner;

@SuppressWarnings("serial")
public class Guest implements Serializable {
    private String name;
    private double discount;

    public Guest(String name, double discount) {
        setName(name);
        setDiscount(discount);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getDiscount() {
        return discount;
    }

    public static String getTypeOfGuest() {
        Scanner keyboard = new Scanner(System.in);
        String choice = "";
        System.out.println("Choose the type of guest:");
        System.out.println("(L)ecturer, (S)tudent");
        String option = "";
        while (option.equalsIgnoreCase("")) {
            option = keyboard.nextLine();
            switch (option.toUpperCase()) {
                case "L":
                    choice = "lecturer";
                    break;
                case "S":
                    choice = "student";
                    break;
                default:
                    System.out.println("Wrong option selected. Please enter L or S");
                    option = "";
                    break;
            }
        }

        return choice;
    }

    public static String getNameOfGuest() {
        Scanner keyboard = new Scanner(System.in);
        String name = "";
        System.out.println("Please enter the Guest name:");
        name = keyboard.nextLine();

        return name;
    }

}