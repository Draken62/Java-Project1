package room;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

import Exception.MyException;
import guest.Guest;

@SuppressWarnings("serial")
public class Room implements Serializable{
    private static int ROOM_NUM = 1;
    private int roomNumber;
    private int maxGuests;
    private double price;
    private ArrayList<Guest> guests; // if it's null, it's not occupied
    private Guest reservedBy; // if it's null, it's not reserved

    public Room(double price, int maxGuests) {
        roomNumber = ROOM_NUM;
        ROOM_NUM++;
        this.price = price;
        this.maxGuests = maxGuests;
        this.guests = null;
        this.reservedBy = null;
    }

    public ArrayList<Guest> getGuests() {
        return guests;
    }

    public void addGuest(Guest guest) throws MyException {
        if (guests == null) {
            this.guests = new ArrayList<Guest>();
        }
        if (!isFull()) {
            guests.add(guest);
        } else {
            throw new MyException("Maximum number of guests reached.");
        }
    }

    public void removeGuest(Guest guest) throws MyException {
        if (isOccupied()) {
            if (guests.contains(guest)) {
                guests.remove(guest);
            } else {
                throw new MyException("Guest is not staying on this room.");
            }
        } else {
            throw new MyException("This room has no guests.");
        }
    }

    public void addReservation(Guest guest) throws MyException {
        if (reservedBy == null) {
            reservedBy = guest;
        } else {
            throw new MyException("This room is already reserved.");
        }
    }

    public void removeReservation() throws MyException {
        if (reservedBy == null) {
            throw new MyException("This room is not reserved.");
        } else {
            reservedBy = null;
        }
    }

    public void emptyRoom() {
        this.guests = null;
        this.reservedBy = null;
    }

    public double paymentDue() {
        double total = 0;
        if (isOccupied()) {
            for (Guest guest : guests) {
                total += price - (price * guest.getDiscount());
            }
        }
        return total;
    }

    public boolean isOccupied() {
        return (guests != null);
    }

    public boolean isFull() {
        if (guests == null) return false;
        return (guests.size() >= maxGuests);
    }

    public int getNumber() {
        return roomNumber;
    }

    public String getReservation() {
        return this.reservedBy.getName();
    }

    public boolean isReserved() {
        return (this.reservedBy != null);
    }

    public String toString() {
        return "Room #" + roomNumber + ":" + "\nPrice: " + price + "€ pp." + "\nReservation made by " + reservedBy;
    }

    public static String getTypeOfRoom() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Choose the type of room:");
        System.out.println("Sui(T}e, (D)ouble, (S)ingle");
        String option = "", choice = "";
        while (option.equalsIgnoreCase("")) {
            option = keyboard.nextLine();
            switch (option.toUpperCase()) {
                case "T":
                    choice = "suite";
                    break;
                case "D":
                    choice = "double";
                    break;
                case "S":
                    choice = "single";
                    break;
                default:
                    System.out.println("Please enter the c12orrect option Please enter T, D  or S");
                    option = "";
                    break;
            }
        }
        return choice;
    }
}