import java.awt.*;
import javax.swing.*;

// Bank Account
class BankAccount {
    private double balance;
    private String pin;

    public BankAccount(double balance, String pin) {
        this.balance = balance;
        this.pin = pin;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amt) {
        if (amt > 0) balance += amt;
    }

    public boolean withdraw(double amt) {
        if (amt > 0 && amt <= balance) {
            balance -= amt;
            return true;
        }
        return false;
    }

    public boolean verifyPin(String inputPin) {
        return pin.equals(inputPin);
    }

    public void setPin(String newPin) {
        this.pin = newPin;
    }
}

public class ATMGUI extends JFrame {

    private CardLayout cardLayout;
    private JPanel mainPanel;

    private BankAccount account;
    private JLabel balanceLabel;
    private JTextField pinField;

    public ATMGUI() {

        account = new BankAccount(5000, "1234");

        setTitle("MyBank ATM");
        setSize(420, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(createLoginPanel(), "Login");
        mainPanel.add(createATMPanel(), "ATM");

        add(mainPanel);
        setVisible(true);
    }

    //Login Panel
    private JPanel createLoginPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.setBackground(new Color(30, 30, 30));

        JLabel title = new JLabel("Enter PIN", JLabel.CENTER);
        title.setForeground(Color.WHITE);

        pinField = new JTextField();
        JButton loginBtn = new JButton("Login");

        loginBtn.addActionListener(e -> {
            String input = pinField.getText();

            if (account.verifyPin(input)) {
                cardLayout.show(mainPanel, "ATM");
                updateBalance();
            } else {
                JOptionPane.showMessageDialog(this, "Incorrect PIN");
            }
        });

        panel.add(title);
        panel.add(pinField);
        panel.add(loginBtn);

        return panel;
    }

    //ATM Dashboard
    private JPanel createATMPanel() {

        JPanel panel = new JPanel(new BorderLayout());

        //Top Balance Card
        JPanel top = new JPanel(new GridLayout(2, 1));
        top.setBackground(new Color(40, 116, 240));

        JLabel title = new JLabel("Available Balance", JLabel.CENTER);
        title.setForeground(Color.WHITE);

        balanceLabel = new JLabel("", JLabel.CENTER);
        balanceLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        balanceLabel.setForeground(Color.WHITE);

        top.add(title);
        top.add(balanceLabel);

        //Buttons
        JPanel buttons = new JPanel(new GridLayout(4, 1, 15, 15));
        buttons.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JButton depositBtn = createButton("Deposit", new Color(46, 204, 113));
        JButton withdrawBtn = createButton("Withdraw", new Color(231, 76, 60));
        JButton changePinBtn = createButton("Change PIN", new Color(241, 196, 15));
        JButton logoutBtn = createButton("Logout", new Color(127, 140, 141));

        depositBtn.addActionListener(e -> deposit());
        withdrawBtn.addActionListener(e -> withdraw());
        changePinBtn.addActionListener(e -> changePin());
        logoutBtn.addActionListener(e -> cardLayout.show(mainPanel, "Login"));

        buttons.add(depositBtn);
        buttons.add(withdrawBtn);
        buttons.add(changePinBtn);
        buttons.add(logoutBtn);

        panel.add(top, BorderLayout.NORTH);
        panel.add(buttons, BorderLayout.CENTER);

        return panel;
    }

    //Button Styling
    private JButton createButton(String text, Color color) {
        JButton btn = new JButton(text);
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        return btn;
    }

    //Update Balance
    private void updateBalance() {
        balanceLabel.setText("₹ " + account.getBalance());
    }

    //Deposit
    private void deposit() {
        String input = JOptionPane.showInputDialog(this, "Enter amount:");
        try {
            double amt = Double.parseDouble(input);
            account.deposit(amt);
            updateBalance();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid input!");
        }
    }

    //Withdraw
    private void withdraw() {
        String input = JOptionPane.showInputDialog(this, "Enter amount:");
        try {
            double amt = Double.parseDouble(input);
            if (account.withdraw(amt)) {
                updateBalance();
            } else {
                JOptionPane.showMessageDialog(this, "Insufficient balance!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid input!");
        }
    }

    //Change PIN
    private void changePin() {

        String oldPin = JOptionPane.showInputDialog(this, "Enter current PIN:");
        if (!account.verifyPin(oldPin)) {
            JOptionPane.showMessageDialog(this, "Incorrect current PIN");
            return;
        }

        String newPin = JOptionPane.showInputDialog(this, "Enter new PIN:");
        String confirmPin = JOptionPane.showInputDialog(this, "Confirm new PIN:");

        if (!newPin.equals(confirmPin)) {
            JOptionPane.showMessageDialog(this, "PIN mismatch!");
            return;
        }

        if (newPin.length() != 4) {
            JOptionPane.showMessageDialog(this, "PIN must be 4 digits!");
            return;
        }

        account.setPin(newPin);
        JOptionPane.showMessageDialog(this, "PIN changed successfully!");
    }

    public static void main(String[] args) {
        new ATMGUI();
    }
}
