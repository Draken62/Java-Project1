package Main;

import java.util.Scanner;

import Exception.MyException;
import guest.Guest;
import guest.Lecturer;
import guest.Student;
import room.Room;
import room.Roomavailibility;
import services.Database;

public class Controller {

    private Roomavailibility hotel;

    public Controller() {
        Database fs = new Database();
        this.hotel = (Roomavailibility) fs.LoadFile("hotel.dat");
        if (this.hotel == null) {
            this.hotel = new Roomavailibility();
        }
    }

    public void displayAvailableRooms() {
        String choice = Room.getTypeOfRoom();
        System.out.println("The amount of free " + choice + "s is " + hotel.getFreeRooms(choice));
    }

    public void displayGuests() {
        String choice = Guest.getTypeOfGuest();
        System.out.println("List of current guests of type " + choice + " staying at the hotel:");
        System.out.println(hotel.getGuestList(choice));
    }

    public void processReservation() {
        int roomNo = viewReservation();
        if (roomNo >= 0) {
            try {
                Room currentRoom = hotel.getRoomByID(roomNo);
                while (!currentRoom.isFull()) {
                    String guestType = Guest.getTypeOfGuest();
                    String guestName = Guest.getNameOfGuest();
                    switch (guestType) {
                        case "lecturer":
                            currentRoom.addGuest(new Lecturer(guestName));
                            ;
                            break;
                        case "student":
                            currentRoom.addGuest(new Student(guestName));
                            ;
                            break;
                        default:
                            System.out.println("Wrong type of guest chosen.");
                    }
                }
            } catch (MyException e) {
                System.out.println("Room does not exists.");
            }
        }
    }

    public void newReservation() {
        int roomNo = hotel.nextAvailableRoom(Room.getTypeOfRoom());
        if (roomNo < 0) {
            System.out.println("Sorry, no rooms of that type are available.");
        } else {
            String guestType = Guest.getTypeOfGuest();
            String guestName = Guest.getNameOfGuest();
            switch (guestType) {
                case "lecturer":
                    hotel.addReservation(roomNo, new Lecturer(guestName));
                    System.out.println("Room #" + roomNo + " reserved to " + guestName);
                    break;
                case "student":
                    hotel.addReservation(roomNo, new Student(guestName));
                    System.out.println("Room #" + roomNo + " reserved to " + guestName);
                    break;
                default:
                    System.out.println("Wrong type of guest chosen.");
            }
        }
    }

    public int viewReservation() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Please enter room number:");
        String option = "";
        option = keyboard.nextLine();
        int roomNo;
        try {
            roomNo = Integer.parseInt(option);
            System.out.println("Room #" + option + " is reserved to " + hotel.getCustomer(roomNo));
        } catch (Exception e) {
            roomNo = -1;
            System.out.println("Room #" + option + " is not reserved.");
        }
        return roomNo;
    }

    public void displayReservations() {
        System.out.println(hotel.getReservationList());
    }

    public void cancelReservation() {
        Scanner keyboard = new Scanner(System.in);
        int option = viewReservation();
        if (option >= 0) {
            System.out.println("Please enter room number again to confirm cancellation. Type (C) to Cancel.");
            String confirm = "";
            confirm = keyboard.nextLine();
            if (!confirm.toUpperCase().equals("C")) {
                try {
                    if (option == Integer.parseInt(confirm)) {
                        hotel.getRoomByID(option).emptyRoom();
                        System.out.println("Reservation for Room #" + option + " has been cancelled.");
                    } else {
                        System.out.println("Room and confirmation values don't match. Please try again.");
                    }
                } catch (Exception e) {
                    System.out.println("Room and confirmation values don't match. Please try again.");
                }
            } else {
                System.out.println("Operation cancelled.");
            }
        }
    }

    public void processPayment() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Please enter the room you want to check out: ");
        String option = "";
        option = keyboard.nextLine();

        try {
            int roomNo = Integer.parseInt(option);
            System.out.println("Customer: " + hotel.getCustomer(roomNo));
            System.out.println("Outstanding payment: " + hotel.getPayment(roomNo) + "€");
            System.out.println("Process? (Y/N)");
            option = keyboard.nextLine();
            if (option.equalsIgnoreCase("y")) {
                hotel.emptyRoom(roomNo);
                System.out.println("Payment processed.");
                System.out.println("Guests for Room #" + roomNo + " checked out.");
            } else {
                System.out.println("Option cancelled.");
            }
        } catch (Exception e) {
            System.out.println("Room does not exist.");
        }
    }

    public void exit() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Do you want to save? (Y/N)");
        String option = "";
        while (option.equalsIgnoreCase("")) {
            option = keyboard.nextLine();
            switch (option.toUpperCase()) {
                case "Y":
                    Database fs = new Database();
                    if (fs.SaveFile("hotel.dat", this.hotel)) {
                        System.out.println("Database saved successfully. Have a nice day!");
                    } else {
                        System.out.println("There was an issue saving the file. Exit cancelled.");
                    }
                    break;
                case "N":
                    System.out.println("Logging you out...");
                    break;
                default:
                    System.out.println("Wrong option selected. Please enter Y or N.");
                    option = "";
            }
        }
    }
}