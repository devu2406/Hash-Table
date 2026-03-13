import java.util.*;


class ParkingLot {
    static void main() {
    }

    String[] table;
    int size;

    public ParkingLot(int size) {
        this.size = size;
        table = new String[size];
    }

    private int hash(String plate) {
        return Math.abs(plate.hashCode()) % size;
    }

    public int parkVehicle(String plate) {

        int index = hash(plate);

        int probes = 0;

        while (table[index] != null) {

            index = (index + 1) % size;
            probes++;
        }

        table[index] = plate;

        System.out.println("Assigned spot " + index + " probes: " + probes);

        return index;
    }

    public void exitVehicle(String plate) {

        int index = hash(plate);

        while (table[index] != null) {

            if (table[index].equals(plate)) {
                table[index] = null;
                return;
            }

            index = (index + 1) % size;
        }
    }
}