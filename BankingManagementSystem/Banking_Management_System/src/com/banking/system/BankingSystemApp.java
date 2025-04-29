package com.banking.system;

import java.util.List;
import java.util.Scanner;

public class BankingSystemApp {

    private static final BankingService service = new BankingServiceImpl();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            printMenu();
            int choice = getUserChoice();
            handleUserChoice(choice);
        }
    }

    private static void printMenu() {
        System.out.println("\n========== Banking Management System ==========");
        System.out.println("1. Add Customer");
        System.out.println("2. Add Account");
        System.out.println("3. Add Transaction");
        System.out.println("4. Add Beneficiary");
        System.out.println("5. View Transactions by Account ID");
        System.out.println("6. View Beneficiaries by Customer ID");
        System.out.println("7. Find Customer by ID");
        System.out.println("8. Find Account by ID");
        System.out.println("9. List All Customers");
        System.out.println("10. List All Accounts");
        System.out.println("11. Exit");
        System.out.println("===============================================");
        System.out.print("Enter your choice: ");
    }

    private static int getUserChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter a valid choice.");
            return -1; // Return invalid choice
        }
    }

    private static void handleUserChoice(int choice) {
        switch (choice) {
            case 1 -> addCustomer();
            case 2 -> addAccount();
            case 3 -> addTransaction();
            case 4 -> addBeneficiary();
            case 5 -> viewTransactionsByAccountId();
            case 6 -> viewBeneficiariesByCustomerId();
            case 7 -> findCustomerById();
            case 8 -> findAccountById();
            case 9 -> listAllCustomers();
            case 10 -> listAllAccounts();
            case 11 -> exitSystem();
            default -> System.out.println("Invalid choice! Please select from the menu.");
        }
    }

    private static void addCustomer() {
        System.out.println("\nEnter Customer Details:");
        System.out.print("Customer ID: ");
        int customerId = Integer.parseInt(scanner.nextLine());
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Address: ");
        String address = scanner.nextLine();
        System.out.print("Contact: ");
        String contact = scanner.nextLine();

        Customer customer = new Customer(customerId, name, address, contact);
        service.addCustomer(customer);
        System.out.println("Customer added successfully!");
    }

    private static void addAccount() {
        System.out.println("\nEnter Account Details:");
        System.out.print("Account ID: ");
        int accountId = Integer.parseInt(scanner.nextLine());
        System.out.print("Customer ID: ");
        int customerId = Integer.parseInt(scanner.nextLine());
        System.out.print("Account Type (Saving/Current): ");
        String type = scanner.nextLine();
        System.out.print("Initial Balance: ");
        double balance = Double.parseDouble(scanner.nextLine());

        Account account = new Account(accountId, customerId, type, balance);
        service.addAccount(account);
        System.out.println("Account added successfully!");
    }

    private static void addTransaction() {
        System.out.println("\nEnter Transaction Details:");
        System.out.print("Account ID: ");
        int accountId = Integer.parseInt(scanner.nextLine());
        System.out.print("Transaction Type (Deposit/Withdrawal): ");
        String type = scanner.nextLine();
        System.out.print("Amount: ");
        double amount = Double.parseDouble(scanner.nextLine());

        Transaction transaction = new Transaction(0, accountId, type, amount, null);
        service.addTransaction(transaction);
        System.out.println("Transaction added successfully!");
    }

    private static void addBeneficiary() {
        System.out.println("\nEnter Beneficiary Details:");
        System.out.print("Beneficiary ID: ");
        int beneficiaryId = Integer.parseInt(scanner.nextLine());
        System.out.print("Customer ID: ");
        int customerId = Integer.parseInt(scanner.nextLine());
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Account Number: ");
        String accountNumber = scanner.nextLine();
        System.out.print("Bank Details: ");
        String bankDetails = scanner.nextLine();

        Beneficiary beneficiary = new Beneficiary(beneficiaryId, customerId, name, accountNumber, bankDetails);
        service.addBeneficiary(beneficiary);
        System.out.println("Beneficiary added successfully!");
    }

    private static void viewTransactionsByAccountId() {
        System.out.print("\nEnter Account ID: ");
        int accountId = Integer.parseInt(scanner.nextLine());
        List<Transaction> transactions = service.getTransactionsByAccountId(accountId);

        if (transactions.isEmpty()) {
            System.out.println("No transactions found for Account ID: " + accountId);
        } else {
            System.out.println("Transactions for Account ID " + accountId + ":");
            transactions.forEach(System.out::println);
        }
    }

    private static void viewBeneficiariesByCustomerId() {
        System.out.print("\nEnter Customer ID: ");
        int customerId = Integer.parseInt(scanner.nextLine());
        List<Beneficiary> beneficiaries = service.getBeneficiariesByCustomerId(customerId);

        if (beneficiaries.isEmpty()) {
            System.out.println("No beneficiaries found for Customer ID: " + customerId);
        } else {
            System.out.println("Beneficiaries for Customer ID " + customerId + ":");
            beneficiaries.forEach(System.out::println);
        }
    }

    private static void findCustomerById() {
        System.out.print("\nEnter Customer ID: ");
        int customerId = Integer.parseInt(scanner.nextLine());
        Customer customer = service.findCustomerById(customerId);

        if (customer == null) {
            System.out.println("Customer not found.");
        } else {
            System.out.println(customer);
        }
    }

    private static void findAccountById() {
        System.out.print("\nEnter Account ID: ");
        int accountId = Integer.parseInt(scanner.nextLine());
        Account account = service.findAccountById(accountId);

        if (account == null) {
            System.out.println("Account not found.");
        } else {
            System.out.println(account);
        }
    }

    private static void listAllCustomers() {
        List<Customer> customers = service.getAllCustomers();
        if (customers.isEmpty()) {
            System.out.println("No customers found.");
        } else {
            System.out.println("All Customers:");
            customers.forEach(System.out::println);
        }
    }

    private static void listAllAccounts() {
        List<Account> accounts = service.getAllAccounts();
        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
        } else {
            System.out.println("All Accounts:");
            accounts.forEach(System.out::println);
        }
    }

    private static void exitSystem() {
        System.out.println("Thank you for using the Banking Management System!");
        scanner.close();
        System.exit(0);
    }
}
