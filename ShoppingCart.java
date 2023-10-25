import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

class Item {
    private String name;
    private String vendor;
    private double price;
    private double cost;
    private double weight;
    private double taxRate;

    public Item(String name, String vendor, double price, double cost, double weight, double taxRate) {
        this.name = name;
        this.vendor = vendor;
        this.price = price;
        this.cost = cost;
        this.weight = weight;
        this.taxRate = taxRate;
    }

    public double calculateTax() {
        return price * (taxRate / 100.0);
    }

    public double calculateTotalCost() {
        return price + calculateTax();
    }

    public void displayDetails() {
        DecimalFormat df = new DecimalFormat("#.##");
        System.out.println("Name: " + name);
        System.out.println("Vendor: " + vendor);
        System.out.println("Price: " + df.format(price));
        System.out.println("Tax: " + df.format(calculateTax()));
        System.out.println("Total Cost: " + df.format(calculateTotalCost()));
    }
}

class PublicationItem extends Item {
    private String author;
    private String pubMonth;
    private int numPages;

    public PublicationItem(String name, String vendor, double price, double cost, double weight, double taxRate, String author, String pubMonth, int numPages) {
        super(name, vendor, price, cost, weight, taxRate);
        this.author = author;
        this.pubMonth = pubMonth;
        this.numPages = numPages;
    }

    public void displayDetails() {
        super.displayDetails();
        DecimalFormat df = new DecimalFormat("#.##");
        System.out.println("Author: " + author);
        System.out.println("Publication Month: " + pubMonth);
        System.out.println("Number of Pages: " + numPages);
    }
}

class FoodItem extends Item {
    private String sellByDate;
    private String useByDate;

    public FoodItem(String name, String vendor, double price, double cost, double weight, double taxRate, String sellByDate, String useByDate) {
        super(name, vendor, price, cost, weight, taxRate);
        this.sellByDate = sellByDate;
        this.useByDate = useByDate;
    }

    public void displayDetails() {
        super.displayDetails();
        System.out.println("Sell By Date: " + sellByDate);
        System.out.println("Use By Date: " + useByDate);
    }
}

class GroceryItem extends Item {
    // No additional fields for GroceryItem
}

class ShoppingCart {
    private ArrayList<Item> items = new ArrayList<>();

    public void addItem(Item item) {
        items.add(item);
    }

    public double calculateTotalCost() {
        double totalCost = 0.0;
        for (Item item : items) {
            totalCost += item.calculateTotalCost();
        }
        return totalCost;
    }

    public void displayItems() {
        for (Item item : items) {
            item.displayDetails();
            System.out.println();
        }
    }

    public int getItemCount() {
        return items.size();
    }

    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nShopping Cart Menu:");
            System.out.println("1. Add Item");
            System.out.println("2. Calculate Total Cost");
            System.out.println("3. Display Items");
            System.out.println("4. Get Item Count");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter item type (Publication/Food/Grocery): ");
                    String itemType = scanner.nextLine();
                    System.out.print("Enter item name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter vendor: ");
                    String vendor = scanner.nextLine();
                    System.out.print("Enter price: ");
                    double price = scanner.nextDouble();
                    System.out.print("Enter cost: ");
                    double cost = scanner.nextDouble();
                    System.out.print("Enter weight: ");
                    double weight = scanner.nextDouble();
                    System.out.print("Enter tax rate (%): ");
                    double taxRate = scanner.nextDouble();
                    scanner.nextLine();

                    if (itemType.equalsIgnoreCase("Publication")) {
                        System.out.print("Enter author: ");
                        String author = scanner.nextLine();
                        System.out.print("Enter publication month: ");
                        String pubMonth = scanner.nextLine();
                        System.out.print("Enter number of pages: ");
                        int numPages = scanner.nextInt();
                        scanner.nextLine();

                        PublicationItem publication = new PublicationItem(name, vendor, price, cost, weight, taxRate, author, pubMonth, numPages);
                        cart.addItem(publication);
                    } else if (itemType.equalsIgnoreCase("Food")) {
                        System.out.print("Enter sell by date (YYYY-MM-DD): ");
                    }
            }
        }
    }
}

