import java.util.*;

// Reservation request
class Reservation {

    String guestName;
    String roomType;

    Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

// Booking Request Queue
class BookingRequestQueue {

    Queue<Reservation> queue = new LinkedList<>();

    void addRequest(Reservation r) {
        queue.add(r);
    }

    Reservation getNextRequest() {
        return queue.poll();
    }

    boolean hasRequests() {
        return !queue.isEmpty();
    }
}

// Inventory Service
class RoomInventory {

    HashMap<String, Integer> inventory = new HashMap<>();

    RoomInventory() {
        inventory.put("Single", 5);
        inventory.put("Double", 3);
        inventory.put("Suite", 2);
    }

    int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    void decrement(String roomType) {
        inventory.put(roomType, inventory.get(roomType) - 1);
    }
}

// Booking Service (Allocation)
class BookingService {

    HashMap<String, Set<String>> allocatedRooms = new HashMap<>();
    Set<String> allRoomIds = new HashSet<>();

    RoomInventory inventory;

    BookingService(RoomInventory inventory) {
        this.inventory = inventory;
    }

    void processBooking(Reservation r) {

        if (inventory.getAvailability(r.roomType) > 0) {

            String roomId = r.roomType + "-" + (allRoomIds.size() + 1);

            while (allRoomIds.contains(roomId)) {
                roomId = r.roomType + "-" + (allRoomIds.size() + 1);
            }

            allRoomIds.add(roomId);

            allocatedRooms
                    .computeIfAbsent(r.roomType, k -> new HashSet<>())
                    .add(roomId);

            inventory.decrement(r.roomType);

            System.out.println(
                    "Processing booking for Guest: "
                            + r.guestName
                            + ", Room Type: "
                            + r.roomType
            );

        } else {
            System.out.println("No rooms available for " + r.roomType);
        }
    }
}

// Main Class
class BookMyStep {

    public static void main(String[] args) {

        System.out.println("Booking Request Queue");

        BookingRequestQueue queue = new BookingRequestQueue();
        RoomInventory inventory = new RoomInventory();
        BookingService bookingService = new BookingService(inventory);

        // Add requests
        queue.addRequest(new Reservation("Abhi", "Single"));
        queue.addRequest(new Reservation("Subha", "Double"));
        queue.addRequest(new Reservation("Vanmathi", "Suite"));

        // Process queue FIFO
        while (queue.hasRequests()) {
            Reservation r = queue.getNextRequest();
            bookingService.processBooking(r);
        }
    }
}