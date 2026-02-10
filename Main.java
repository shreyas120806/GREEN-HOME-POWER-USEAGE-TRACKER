import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class PowerLog {   // ✅ No public keyword here
    private String applianceName;
    private double powerInWatts;
    private double durationInHours;

    public PowerLog(String applianceName, double powerInWatts, double durationInHours) {
        this.applianceName = applianceName;
        this.powerInWatts = powerInWatts;
        this.durationInHours = durationInHours;
    }

    public double getEnergyInKWh() {
        return (powerInWatts * durationInHours) / 1000;
    }

    @Override
    public String toString() {
        return applianceName + " | " + powerInWatts + "W | " + durationInHours + "h | " +
               String.format("%.2f", getEnergyInKWh()) + " kWh";
    }
}

public class Main {  // ✅ Programiz needs this class name
    private List<PowerLog> usageLogs;

    public Main() {
        this.usageLogs = new ArrayList<>();
    }

    public void addLog(PowerLog log) {
        usageLogs.add(log);
        System.out.println("✅ Log added successfully!");
    }

    public void showLogs() {
        if (usageLogs.isEmpty()) {
            System.out.println("⚠ No power usage data recorded yet!");
            return;
        }
        System.out.println("\n--- Power Usage Logs ---");
        for (PowerLog log : usageLogs) {
            System.out.println(log);
        }
    }

    public void calculateTotalEnergy() {
        double totalEnergy = 0;
        for (PowerLog log : usageLogs) {
            totalEnergy += log.getEnergyInKWh();
        }
        System.out.println("\n🔋 Total Energy Consumed: " +
                           String.format("%.2f", totalEnergy) + " kWh");

        // Eco suggestion
        if (totalEnergy > 10) {
            System.out.println("🌿 Tip: High usage! Try using energy-efficient appliances.");
        } else if (totalEnergy > 5) {
            System.out.println("🌿 Tip: Moderate usage. Remember to switch off unused devices.");
        } else {
            System.out.println("🌿 Great job! Your energy usage is eco-friendly.");
        }
    }

    public static void main(String[] args) {
        Main tracker = new Main();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== Green Home Power Usage Tracker ===");
            System.out.println("1. Add Power Usage Log");
            System.out.println("2. View All Logs");
            System.out.println("3. Calculate Total Energy Usage");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter appliance name: ");
                    scanner.nextLine(); // buffer clear
                    String name = scanner.nextLine();
                    System.out.print("Enter power consumption (Watts): ");
                    double watts = scanner.nextDouble();
                    System.out.print("Enter duration used (Hours): ");
                    double hours = scanner.nextDouble();
                    tracker.addLog(new PowerLog(name, watts, hours));
                    break;

                case 2:
                    tracker.showLogs();
                    break;

                case 3:
                    tracker.calculateTotalEnergy();
                    break;

                case 4:
                    System.out.println("Exiting... Stay eco-friendly! 🌍");
                    break;

                default:
                    System.out.println("❌ Invalid choice! Try again.");
            }
        } while (choice != 4);

        scanner.close();
    }
}
