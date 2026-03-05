import java.util.Queue;
import java.util.LinkedList;

// Reservation class representing a booking request
class Reservation {

    String guestName;
    String roomType;

    Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    void displayRequest() {
        System.out.println("Guest: " + guestName + " requested " + roomType);
    }
}

// Booking Request Queue
class BookingRequestQueue {

    private Queue<Reservation> queue;

    BookingRequestQueue() {
        queue = new LinkedList<>();
    }

    // Add request to queue
    public void addRequest(Reservation reservation) {
        queue.add(reservation);
        System.out.println("Booking request added for " + reservation.guestName);
    }

    // Display queued requests
    public void displayRequests() {
        System.out.println("\nCurrent Booking Request Queue:");

        for (Reservation r : queue) {
            r.displayRequest();
        }
    }
}

// Main Class
class BookMyStep {

    public static void main(String[] args) {

        System.out.println("Booking Request Queue System\n");

        BookingRequestQueue requestQueue = new BookingRequestQueue();

        // Simulated booking requests
        Reservation r1 = new Reservation("Alice", "Single Room");
        Reservation r2 = new Reservation("Bob", "Double Room");
        Reservation r3 = new Reservation("Charlie", "Suite Room");

        requestQueue.addRequest(r1);
        requestQueue.addRequest(r2);
        requestQueue.addRequest(r3);

        requestQueue.displayRequests();
    }
}