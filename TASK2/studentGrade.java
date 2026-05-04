import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class studentGrade extends JFrame implements ActionListener {
private JTextField[] fields = new JTextField[5];
    private JButton calculateBtn, resetBtn;
    private JLabel resultLabel;
    public studentGrade() {
        setTitle("Advanced Grade Calculator");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        //Modern Dark Theme Background
        getContentPane().setBackground(new Color(30, 30, 30));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        Font font = new Font("Segoe UI", Font.BOLD, 14);

        // Input fields
        for (int i = 0; i < 5; i++) {
            gbc.gridx = 0;
            gbc.gridy = i;

            JLabel label = new JLabel("Subject " + (i + 1) + ":");
            label.setForeground(Color.WHITE);
            add(label, gbc);

            gbc.gridx = 1;
            fields[i] = new JTextField(10);
            fields[i].setFont(font);
            fields[i].setBackground(new Color(50, 50, 50));
            fields[i].setForeground(Color.WHITE);
            fields[i].setCaretColor(Color.WHITE);
            add(fields[i], gbc);
        }

        // Buttons
        calculateBtn = new JButton("Calculate");
        resetBtn = new JButton("Reset");

        // Modern Button Colors
        calculateBtn.setBackground(new Color(0, 123, 255)); // Blue
        calculateBtn.setForeground(Color.WHITE);

        resetBtn.setBackground(new Color(220, 53, 69)); // Red
        resetBtn.setForeground(Color.WHITE);

        gbc.gridx = 0;
        gbc.gridy = 6;
        add(calculateBtn, gbc);

        gbc.gridx = 1;
        add(resetBtn, gbc);

        // Result label
        resultLabel = new JLabel("Enter marks (0-100)");
        resultLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        resultLabel.setForeground(new Color(0, 200, 255));

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        add(resultLabel, gbc);

        calculateBtn.addActionListener(this);
        resetBtn.addActionListener(this);

        // ENTER KEY SUPPORT
        getRootPane().setDefaultButton(calculateBtn);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == calculateBtn) {
            try {
                int total = 0;

                for (int i = 0; i < 5; i++) {
                    String text = fields[i].getText();

                    if (text.isEmpty()) {
                        resultLabel.setText(" All fields are required!");
                        return;
                    }

                    int mark = Integer.parseInt(text);

                    if (mark < 0 || mark > 100) {
                        resultLabel.setText("Marks must be between 0 and 100!");
                        return;
                    }

                    total += mark;
                }

                double avg = total / 5.0;

                String grade;
                if (avg >= 90) grade = "A+";
                else if (avg >= 80) grade = "A";
                else if (avg >= 70) grade = "B";
                else if (avg >= 60) grade = "C";
                else if (avg >= 50) grade = "D";
                else grade = "F";

                String status = (avg >= 50) ? "PASS " : "FAIL ";

                resultLabel.setText(
                        String.format("Total: %d | Avg: %.2f%% | Grade: %s | %s",
                                total, avg, grade, status)
                );

            } catch (NumberFormatException ex) {
                resultLabel.setText("Enter valid numeric values!");
            }
        }

        if (e.getSource() == resetBtn) {
            for (JTextField field : fields) {
                field.setText("");
            }
            resultLabel.setText("Reset done. Enter marks again.");
        }
    }

    public static void main(String[] args) {
        new studentGrade();
    }
}
