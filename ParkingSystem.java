import java.util.ArrayList;
import java.util.List;

// 1. Slot Class for Parking System
class TraceSlot {
    private int slotId;
    private String slotType; // e.g., "Regular", "VIP"
    private boolean isOccupied;

    public TraceSlot(int slotId, String slotType) {
        this.slotId = slotId;
        this.slotType = slotType;
        this.isOccupied = false;
    }

    public int getSlotId() { return slotId; }
    public String getSlotType() { return slotType; }
    public boolean isOccupied() { return isOccupied; }

    public void reserveSlot() { this.isOccupied = true; }
    public void releaseSlot() { this.isOccupied = false; }
}

// 2. Client Class
class TraceClient {
    private String clientName;

    public TraceClient(String clientName) {
        this.clientName = clientName;
    }

    public String getClientName() { return clientName; }
}

// 3. Main System Class (Matches File Name: ParkingSystem.java)
public class ParkingSystem {
    private List<TraceSlot> slots;

    public ParkingSystem(int totalSlots) {
        slots = new ArrayList<>();
        for (int i = 1; i <= totalSlots; i++) {
            String type = (i % 3 == 0) ? "VIP" : "Regular";
            slots.add(new TraceSlot(i, type));
        }
    }

    public void displayStatus() {
        System.out.println("\n--- 🅿️ Trace Slot: Current Parking Status ---");
        for (TraceSlot slot : slots) {
            String status = slot.isOccupied() ? "[RESERVED]" : "[AVAILABLE]";
            System.out.printf("Slot ID: %2d | Type: %-8s | Status: %s\n",
                    slot.getSlotId(), slot.getSlotType(), status);
        }
    }

    public void reserveSlot(int slotId, TraceClient client) {
        for (TraceSlot slot : slots) {
            if (slot.getSlotId() == slotId) {
                if (!slot.isOccupied()) {
                    slot.reserveSlot();
                    System.out.println("\n[SUCCESS] Slot " + slotId + " reserved successfully for " + client.getClientName() + "!");
                    return;
                } else {
                    System.out.println("\n[WARNING] Slot " + slotId + " is already occupied!");
                    return;
                }
            }
        }
        System.out.println("\n[ERROR] Slot ID " + slotId + " not found!");
    }

    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println("    Welcome to Trace Slot Management System      ");
        System.out.println("            Developed by: Karim                  ");
        System.out.println("=================================================");

        ParkingSystem system = new ParkingSystem(5);
        TraceClient client = new TraceClient("Karim");

        // 1. Initial Status
        system.displayStatus();

        // 2. Reserving Slots
        System.out.println("\n>>> Processing slot reservation for Karim...");
        system.reserveSlot(2, client);
        system.reserveSlot(3, client);

        // 3. Status After Reservation
        system.displayStatus();

        // 4. Test Error Handling
        System.out.println("\n>>> Attempting to reserve Slot 2 again:");
        system.reserveSlot(2, client);
    }
}