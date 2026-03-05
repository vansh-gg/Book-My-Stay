import java.util.HashMap;

// Room domain model
class Room {

    String type;
    int beds;
    int size;
    double price;

    Room(String type, int beds, int size, double price) {
        this.type = type;
        this.beds = beds;
        this.size = size;
        this.price = price;
    }

    void displayDetails(int available) {
        System.out.println(type + ":");
        System.out.println("Beds: " + beds);
        System.out.println("Size: " + size + " sqft");
        System.out.println("Price per night: " + price);
        System.out.println("Available: " + available);
        System.out.println();
    }
}

// Centralized inventory (state holder)
class RoomInventory {

    private HashMap<String, Integer> inventory;

    RoomInventory() {
        inventory = new HashMap<>();

        inventory.put("Single Room", 5);
        inventory.put("Double Room", 3);
        inventory.put("Suite Room", 2);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }
}

// Search Service (read-only)
class RoomSearchService {

    private RoomInventory inventory;

    RoomSearchService(RoomInventory inventory) {
        this.inventory = inventory;
    }

    public void searchAvailableRooms() {

        Room single = new Room("Single Room", 1, 250, 1500.0);
        Room doubleRoom = new Room("Double Room", 2, 400, 2500.0);
        Room suite = new Room("Suite Room", 3, 750, 5000.0);

        int singleAvailable = inventory.getAvailability("Single Room");
        int doubleAvailable = inventory.getAvailability("Double Room");
        int suiteAvailable = inventory.getAvailability("Suite Room");

        System.out.println("Available Rooms\n");

        if (singleAvailable > 0)
            single.displayDetails(singleAvailable);

        if (doubleAvailable > 0)
            doubleRoom.displayDetails(doubleAvailable);

        if (suiteAvailable > 0)
            suite.displayDetails(suiteAvailable);
    }
}

// Main class
class BookMyStep {

    public static void main(String[] args) {

        System.out.println("Room Search System\n");

        RoomInventory inventory = new RoomInventory();
        RoomSearchService searchService = new RoomSearchService(inventory);

        searchService.searchAvailableRooms();
    }
}