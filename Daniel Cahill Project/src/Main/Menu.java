package Main;

import java.util.Scanner;

public class Menu {
    Controller app;

    public Menu() {
        this.app = new Controller();
    }

    public void start() {
        Scanner keyboard = new Scanner(System.in);
        String menuOption = "";

        while (!menuOption.equals("5")) {
            printMenu();
            menuOption = keyboard.nextLine();
            switch (menuOption) {
                case "1":
                    app.displayAvailableRooms();
                    break;
                case "2":
                    app.displayGuests();
                    break;
                case "3":
                    menuReservation();
                    break;
                case "4":
                    app.processPayment();
                    break;
                case "5":
                    app.exit();
                    break;
                default:
                    System.out.println("Wrong option selected.");
                    menuOption = "";
            }
        }

    }

    private void menuReservation() {
        String option = "";
        Scanner keyboard = new Scanner(System.in);

        while (!option.equals("6")) {
            printMenuReservations();
            option = keyboard.nextLine();
            switch (option) {
                case "1":
                    app.newReservation();
                    break;
                case "2":
                    app.viewReservation();
                    break;
                case "3":
                    app.cancelReservation();
                    break;
                case "4":
                    app.displayReservations();
                    break;
                case "5":
                    app.processReservation();
                    break;
                case "6":
                    // return
                    break;
                default:
                    System.out.println("Wrong option selected.");
                    option = "";
            }
        }

    }

    public void printMenu() {
        System.out.println("Welcome to CIT Hotel Management System");
        System.out.println("**************************************");
        System.out.println("1)  Display Empty Rooms");
        System.out.println("2)  Display Checked-in Guests");
        System.out.println("3)  Reservations");
        System.out.println("4)  Process Payment");
        System.out.println("5)  Exit");
    }

    public void printMenuReservations() {
        System.out.println("Welcome to Reservations");
        System.out.println("**************************************");
        System.out.println("1)  New Reservation");
        System.out.println("2)  View Reservation");
        System.out.println("3)  Cancel Reservation");
        System.out.println("4)  List Reservations");
        System.out.println("5)  Check in Guests");
        System.out.println("6)  Return");
    }
}