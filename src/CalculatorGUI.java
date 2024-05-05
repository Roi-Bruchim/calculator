import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayDeque;
import java.util.Deque;

public class CalculatorGUI extends JFrame implements ActionListener {


    //Here we crate the Calculator and buttons
    private JTextField display;
    private String lastAnswer = ""; // Variable to store the last calculated answer
    private Library library;

    public CalculatorGUI() {
        setTitle("Math Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set frame size to fit the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (screenSize.getWidth() * 0.3);
        int height = (int) (screenSize.getHeight() * 0.7);
        setSize(width, height);

        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        display = new JTextField(20);
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setFont(display.getFont().deriveFont(Font.BOLD, 100f)); // Set font size and style
        mainPanel.add(display, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());

        //All our buttons

        String[] numberLabels = {"7", "8", "9", "4", "5", "6", "1", "2", "3"};
        String[] functionLabels = {"Clear", "Undo", "+", "*", "-", "/", "abs", "sqrt", "sin", "cos", "tan", ".", "%", "!", "C", "log", "^", "Ans", "=", "(", ")"};

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton button = createButton(numberLabels[i * 3 + j]);
                button.addActionListener(this);
                gbc.gridx = j;
                gbc.gridy = i;
                buttonPanel.add(button, gbc);
            }
        }

        JButton zeroButton = createButton("0");
        zeroButton.addActionListener(this);
        gbc.gridwidth = 3; // Span the width of all 3 columns
        gbc.gridx = 0;
        gbc.gridy = 3;
        buttonPanel.add(zeroButton, gbc);

        JPanel functionPanel = new JPanel();
        functionPanel.setLayout(new GridLayout(10, 2, 5, 5)); // Increased rows for additional functions

        for (String label : functionLabels) {
            JButton button = createButton(label);
            button.addActionListener(this);
            functionPanel.add(button);
        }

        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        mainPanel.add(functionPanel, BorderLayout.EAST);

        getContentPane().add(mainPanel);
        setLocationRelativeTo(null); // Center the frame on the screen

        library = new Library(); // Instantiate the Library object
    }

    private JButton createButton(String label) {
        JButton button = new JButton(label);
        button.setBackground(Color.WHITE);
        button.setFocusPainted(false); // Remove focus border
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                button.setBackground(Color.LIGHT_GRAY); // Change color when pressed
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                button.setBackground(Color.WHITE); // Change back to original color when released
            }
        });
        return button;
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "=":
                try {
                    String result = evaluateExpression(display.getText(), library);
                    if (result.endsWith(".0")) {
                        result = result.substring(0, result.length() - 2); // Update result
                    }
                    lastAnswer = result; // Store the last calculated result
                    display.setText(result);
                } catch (Exception ex) {
                    display.setText("Error");
                }
                break;
            case "Ans":
                display.setText(display.getText() + lastAnswer); // Append last answer to the display
                break;
            case "Clear":
                display.setText(""); // Clear the display
                break;
            case "Undo":
                String text = display.getText().trim();
                if (!text.isEmpty()) {
                    // Find the position of the last space
                    if(text.startsWith("Error"))
                        display.setText("");
                    int lastSpaceIndex = text.lastIndexOf(' ');
                    if (lastSpaceIndex >= 0) {
                        // Remove the last token from the expression
                        display.setText(text.substring(0, lastSpaceIndex).trim());
                    } else {
                        // Remove the last character from the display
                        display.setText(text.substring(0, text.length() - 1));
                    }
                }
                break;
            default:
                // Append the command to the display for other buttons
                display.setText(display.getText() + command);
                break;
        }
    }

    private String evaluateExpression(String expression, Library library) {
        try {
            // Replace "ANS" with the last answer before evaluation
            expression = expression.replaceAll("ANS", lastAnswer);

            // Split the expression into tokens
            String[] tokens = expression.split("(?<=[-+*/()^√!])|(?=[-+*/()^√!])");
            double result = evaluateExpression(tokens, library);
            return Double.toString(result);
        } catch (IllegalArgumentException e) {
            return "Error: " + e.getMessage();
        }
    }

    private double evaluateExpression(String[] tokens, Library library) {
        Deque<Double> numbers = new ArrayDeque<>();
        Deque<String> operations = new ArrayDeque<>();
        Deque<Integer> parentheses = new ArrayDeque<>();

        for (String token : tokens) {
            if (Library.isNumeric(token)) {
                numbers.push(Double.parseDouble(token));
            } else if (Library.isOperator(token)) {
                while (!operations.isEmpty() && Library.hasPrecedence(token, operations.peek())) {
                    double b = numbers.pop();
                    double a = numbers.pop();
                    String op = operations.pop();
                    numbers.push(Library.applyOperation(a, b, op));
                }
                operations.push(token);
            }
         else if (token.matches("\\d+%\\d+")) { // Check for combination format like "30%70"
            String[] parts = token.split("%");
            double n = Double.parseDouble(parts[0]);
            double k = Double.parseDouble(parts[1]);
            double result = library.percentage(n, k); // Calculate combination
            numbers.push(result);

        }
            else if (library.isFunction(token)) {
                operations.push(token); // Push function token onto operations stack
            } else if (token.equals("(")) {
                operations.push(token);
                parentheses.push(numbers.size()); // Mark the position of the opening parenthesis
            } else if (token.equals(")")) {
                int startIndex = parentheses.pop();
                while (numbers.size() > startIndex) {
                    double b = numbers.pop();
                    double a = numbers.pop();
                    String op = operations.pop();
                    numbers.push(Library.applyOperation(a, b, op));
                }
                operations.pop(); // Remove the opening parenthesis
            } else if (token.matches("\\d+C\\d+")) { // Check for combination format like "6C2"
                String[] parts = token.split("C");
                double n = Double.parseDouble(parts[0]);
                double k = Double.parseDouble(parts[1]);
                double result = library.choose(n, k); // Calculate combination
                numbers.push(result);

            }
            else if (token.equals("!")) { // Handle factorial operator
                double arg = numbers.pop();
                double result = library.fact(arg); // Calculate factorial
                numbers.push(result);
            }
            else if (token.matches("[a-zA-Z]+\\d+")) { // Check for function without parentheses like "sin90"
                String functionName = token.replaceAll("\\d", ""); // Extract function name
                double arg = Double.parseDouble(token.replaceAll("[^\\d.]", "")); // Extract argument
                double result = library.applyFunction(functionName, arg); // Apply function to the argument
                numbers.push(result);
            } else {
                throw new IllegalArgumentException("Invalid token: " + token);
            }
        }

        // Evaluate remaining operations
        while (!operations.isEmpty()) {
            double b = numbers.pop();
            double a = numbers.pop();
            String op = operations.pop();
            numbers.push(Library.applyOperation(a, b, op));
        }

        String resultString = String.valueOf(numbers.pop());
        if (resultString.endsWith(".0")) {
            resultString = resultString.substring(0, resultString.length() - 2); // Update resultString
            display.setText(resultString);
        } else {
            display.setText(resultString);
        }

        return Double.parseDouble(resultString);
    }







    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CalculatorGUI().setVisible(true));
    }
}
