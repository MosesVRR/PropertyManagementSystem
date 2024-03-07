package com.cpm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {
    List<User> users = new ArrayList<User>();
    User activeUser;

    public void menu() {
        if (this.activeUser == null) {
            this.userMenu();
        } else {
            if (this.activeUser instanceof Owner) {
                this.ownerMenu();
            } else if (this.activeUser instanceof Tenant) {
                this.tenantMenu();
            } else if (this.activeUser instanceof Admin) {
                this.adminMenu();
            } else {
                this.visitorMenu();
            }
        }
    }

    public void userMenu() {
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.print("Command > ");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                this.login();
                break;
            case 2:
                this.register();
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }

    public void adminMenu() {
        System.out.println("Admin menu");
    }

    public void ownerMenu() {
        System.out.println();
        Owner owner = (Owner) this.activeUser;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Owner Menu:");
        System.out.println("1. Add property \n" +
                "2. See all properties \n" +
                "3. Add Unit to property \n" +
                "4. Create tenant agreement \n" +
                "5. Show all tenant agreements \n" +
                "6. Adding parking spots\n" +
                "9. Logout");

        System.out.print("Command > ");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                System.out.println();
                System.out.print("Property name: ");
                String name = scanner.next();
                System.out.print("Property address: ");
                String address = scanner.next();
                owner.addProperty(name, address);
                break;
            case 2:
                System.out.println();
                owner.showAllProperties();
                break;
            case 3:
                System.out.println();
                System.out.println("Add Unit");
                owner.showAllProperties();
                System.out.println("Select property id");
                int id = scanner.nextInt();
                Property activeProperty = null;
                for (Property property : owner.getProperties()) {
                    if (property.getId() == id) {
                        activeProperty = property;
                    }
                }
                if (activeProperty == null) {
                    break;
                }
                System.out.print("Unit No : ");
                int unitNo = scanner.nextInt();
                System.out.print("Floor No : ");
                int floorNo = scanner.nextInt();
                System.out.print(" Unit Type: ");
                for (int i = 0; i < UnitType.values().length; i += 1) {
                    System.out.print(i + 1 + ". " + UnitType.values()[i] + " ");
                }
                int type = scanner.nextInt();
                activeProperty.addUnit(unitNo, floorNo, UnitType.values()[type - 1]);
                break;
            case 4:
                owner.showAllProperties();
                System.out.println("Select property id");
                int propertyId = scanner.nextInt();
                Property propertyAgreement = null;
                for (Property property : owner.getProperties()) {
                    if (property.getId() == propertyId) {
                        propertyAgreement = property;
                    }
                }
                if (propertyAgreement == null) {
                    break;
                }
                propertyAgreement.showUnits();
                System.out.println("Select unit id");
                int unitId = scanner.nextInt();
                Unit unitAgreement = null;
                for (Unit unit : propertyAgreement.getUnits()) {
                    if (unit.getId() == unitId) {
                        unitAgreement = unit;
                    }
                }
                User tenant = null;
                for (User user : this.users) {
                    if (user instanceof Tenant) {
                        System.out.println("| Id: " + user.getId() + " | Name: " + user.getName() + " | Email: " + user.getEmail());
                    }
                }
                System.out.println("User: ");
                int userId = scanner.nextInt();
                for (User user : users) {
                    if (user.getId() == userId) {
                        tenant = user;
                        break;
                    }
                }
                if (tenant == null) {
                    break;
                }


                System.out.print("Amount : $ ");
                int amount = scanner.nextInt();
                System.out.println();
                System.out.print("Duration (in months):");
                int duration = scanner.nextInt();

                owner.createAgreement(amount, duration, (Tenant) tenant, unitAgreement);
                System.out.println("Tenant agreement created");
                break;
            case 5:
                System.out.println("Agreements");
                for (TenantAgreement agreement : owner.getTenantAgreements()) {
                    System.out.println("| Id: " + agreement.getId() + " | Duration: " + agreement.getDuration() + "| Amount: $" + agreement.getAmount() + " |");
                }
                break;
            case 6:
                System.out.println("Parkings");
                System.out.println("Select property id");
                int propertyPropertyId = scanner.nextInt();
                Property parkingProperty = null;
                for (Property property : owner.getProperties()) {
                    if (property.getId() == propertyPropertyId) {
                        parkingProperty = property;
                    }
                }
                if (parkingProperty == null) {
                    break;
                }
                System.out.println("parking price: ");
                int price = scanner.nextInt();
                parkingProperty.addParking(price);
            case 9:
                activeUser = null;
                break;
            default:
                System.out.println("Invalid command");
        }
    }

    public void tenantMenu() {
        System.out.println("Tenant menu");
    }

    public void visitorMenu() {
        System.out.println("Visitor menu");
    }


    public void login() {
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Username: ");
        String email = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        // search in Users[]
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                if (user.getEmail().equals(email) || user.getPassword().equals(password)) {
                    this.activeUser = user;
                    System.out.println("Login Successful ");
                }
            }
        }
        if (this.activeUser == null) {
            System.out.println("Login failed");
        }
    }

    public void register() {
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Username: ");
        String email = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.println("1. Admin 2. Tenant 3. Owner 4. Visitor");
        System.out.println("Role: ");
        int role = scanner.nextInt();
        switch (role) {
            case 1:
                Admin newAdmin = new Admin(email, password, name);
                users.add(newAdmin);
                break;
            case 2:
                Tenant newTenant = new Tenant(email, password, name);
                users.add(newTenant);
                break;
            case 3:
                Owner newOwner = new Owner(email, password, name);
                users.add(newOwner);
                break;
            case 4:
                Visitor newVisitor = new Visitor(email, password, name);
                users.add(newVisitor);
                break;
            case 5:
                System.out.println("Invalid Role");
        }
        System.out.println("User is registered");
    }
}
