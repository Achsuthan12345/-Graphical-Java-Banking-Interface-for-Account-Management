package project;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Customer {    
    // Overview: This is a customer in the banking system, and it has a username, password, balance, and level.   
    // In this class you can make modifications to the balance, which modifies the level as well making it mutable.
    //
    // The abstraction function is
    //
    // AF(c) = ("Customer: username=" + username + ", password=" + password + ", balance=" + balance;")
    //
    //
    // The rep invariant is:
    //
    // RI(c) = true if (username != null && !username.isEmpty())
    //         true if (password != null && !password.isEmpty())
    //         true if (balance >= 0)
    //

    private String username;
    private String password;
    private Level level;
    private double balance;
    private String customer;

   public Customer(String username, String password, double balance) {
        // Effect: New Customer object with the specified username, password, and balance.
        // Requires: Password and username cannot be null or empty and balance must be postive.
        // Modifies: New customer object is made
        this.username = username;
        this.password = password; 
        this.balance = balance;
    }

    public String getLevel() {
        // Effect: Returns the level
        // Requires: None.
        // Modifies: None.
        return level.toString();
    }

    public double getBalance() {
        // Effect: Returns the balance, and updates level if needed.
        // Requires: None.
        // Modifies: None.
        updateLevel();
        return balance;
    }

    public void deposit(double amount) {
        // Effect: Deposits the amount into the balance, and updates the level if needed.
        // Requires: The amount cannot be negative.
        // Modifies: Modifies the balance and the level if needed.
        if (amount > 0 ){
            balance += amount;
            updateLevel();
        }
    }
     
    public double getCalculateFee() {
        // Effect: Calculates fee depending on the level.
        // Requires: None.
        // Modifies: None.
        if (level != null) {
            return level.calculateFee();
        } else {
            return 0.0;
        }
    }

    public boolean withdraw(double amount) {
        // Effect: Withdraws the amount from the balance, and updates the level if needed.
        // Requires: The amount to withdraw must not exceed the current balance.
        // Modifies: Modifies the balance and the level if needed.
        if (balance >= amount) {
            balance -= amount;
            updateLevel();
            return true;
        } else {
            return false;
        }
    }
    
    public void updateLevel() {
        // Effect: Depending on the balance the level is updated.
        // Requires: None.
        // Modifies: Changes the level.
        if (this.balance < 10000) {
            this.level = new SilverLevel();
        } else if (this.balance < 20000) {
            this.level = new GoldLevel();
        } else {
            this.level = new PlatinumLevel();
        }
    }

    public void saveToFile() throws IOException {
        // Effect: Creates and updates the text file with new information. 
        // Requires: None.
        // Modifies: None.
        String filename = username + ".txt";
        File file = new File("src/project/BankAccounts", filename);
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(this.username);
        writer.newLine();
        writer.write(this.password);
        writer.newLine();
        writer.write(Double.toString(this.balance));
        writer.newLine();
        writer.write("Role: Customer");
        writer.flush();
        writer.close();
    }
      
    public boolean repOk() {
        // Effect: Checks if the rep invariant is satisfied.
        // Requires: None.
        // Modifies: None.
        if (username != null && !username.isEmpty() && password != null && !password.isEmpty() && balance >= 0) {
            return true;
        } else {
            return false;
        }
    }
        
    public String toString() {
    // Effect: String representation of the Customer object.
    // Requires: None.
    // Modifies: None.
    return "Customer: username=" + username + ", password=" + password + ", balance=" + balance;
    }    
        
}
    
    
    


   
