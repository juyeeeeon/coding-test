package baekjoon;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // Create a new instance of the TeslaModels class
        TeslaModels models = new TeslaModels(new HashMap<>());

        // Add information for Tesla models: "Model 3", "Model S", and "Model X"
        models.addTeslaModel(new TeslaModel("Model 3", 39999, 250, 62));
        models.addTeslaModel(new TeslaModel("Model S", 79999, 375, 82));
        models.addTeslaModel(new TeslaModel("Model X", 89999, 330, 75));

        // Output the details of the Tesla model "Model S"
        TeslaModel modelS = models.getTeslaModel("Model S");
        System.out.println("Model Name: " + modelS.getName());
        System.out.println("Price: $" + modelS.getPrice() + " USD");
        System.out.println("Range: " + modelS.getRange() + " miles");
        System.out.println("Battery Capacity: " + modelS.getBatteryCapacity() + " kWh");

    }
}

class TeslaModels {
    // Declare the Map to store Tesla models
    Map<String, TeslaModel> models;

    // Constructor to initialize HashMap object

    public TeslaModels(Map<String, TeslaModel> models) {
        this.models = models;
    }


    // Define the addTeslaModel() Method
    public void addTeslaModel(TeslaModel model) {
        models.put(model.getName(), model);
    }

    // Define the getTeslaModel() Method
    public TeslaModel getTeslaModel(String name) {
        return models.get(name);
    }
}

class TeslaModel {
    // Declare variables for model name, price, range and battery capacity
    private String name;
    private int price;
    private int range;
    private int batteryCapacity;

    // Constructor to initialize model name, price, range and battery capacity

    public TeslaModel(String name, int price, int range, int batteryCapacity) {
        this.name = name;
        this.price = price;
        this.range = range;
        this.batteryCapacity = batteryCapacity;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getRange() {
        return range;
    }

    public int getBatteryCapacity() {
        return batteryCapacity;
    }
}