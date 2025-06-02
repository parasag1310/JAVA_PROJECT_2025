import javax.swing.*;      // For GUI components like JFrame, JButton, etc.
import java.awt.*;         // For layout managers and more GUI tools
import java.awt.event.*;   // For event handling (e.g., button clicks)

public class Calculator extends JFrame {
    private JTextField result;      //Declares a text field to display calculation results.
    private JTextArea historyLog;   //Declares a text area to show the history of calculations.
    private JTextArea errorLog;     //Declares a text area to display error messages.

    public Calculator() {
        setTitle(" Calculator 🎮"); // Sets the title of the window
        setSize(700, 450); // Initial size of the window
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Close the app when window is closed
        setLocationRelativeTo(null); // Center the window on the screen
        setLayout(new BorderLayout()); // Use BorderLayout for placing components
        getContentPane().setBackground(new Color(240, 248, 255)); // Light blue background
        setMinimumSize(new Dimension(600, 400)); // Prevents window from becoming too small
        setResizable(true); // Allows resizing the window

        //JLabel is a component in Swing used to display text.
        // Create a JLabel with initial text and center alignment
        JLabel title = new JLabel(" Hello Guys ,This Side Paras ", JLabel.CENTER);

        // Store the full text that will appear with the typewriter effect
        String fullTitle = " Hello Guys ,This Side Paras ";

        // Clear the label text to start with an empty string
        title.setText(""); // Start with empty text

        // Create a Timer to generate the typewriter effect
        // The Timer triggers every 100 milliseconds (0.1 seconds)
        Timer typewriterTimer = new Timer(100, new ActionListener() {

            // Index to keep track of the current character position in fullTitle
            int index = 0;

            // This method is called every time the Timer ticks (every 100 ms)
            @Override
            public void actionPerformed(ActionEvent e) {

                // Check if there are still characters left to show
                if (index < fullTitle.length()) {

                    // Append the next character to the current text of the JLabel
                    title.setText(title.getText() + fullTitle.charAt(index));

                    // Move to the next character for the next tick
                    index++;
                } else {
                    // Stop the Timer once all characters have been displayed
                    ((Timer) e.getSource()).stop();
                }
            }
        });

        typewriterTimer.start();

        // Customize the title JLabel's font, color, background, and border
        title.setFont(new Font("Segoe UI Black", Font.BOLD, 20));         // Set font to Segoe UI Black, bold, size 20
        title.setForeground(Color.DARK_GRAY);                             // Text color: dark gray
        title.setOpaque(true);                                            // Make background visible
        title.setBackground(new Color(173, 216, 230));                   // Light blue background color (RGB)
        title.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));  // Padding: top and bottom 10px, no left/right padding

        // Add the title JLabel to the top (NORTH) of the container using BorderLayout
        add(title, BorderLayout.NORTH);


        // Create a JPanel to hold basic operation buttons
        // Use GridLayout: 4 rows, 1 column, 8px horizontal and vertical gaps
        JPanel leftPanel = new JPanel(new GridLayout(4, 1, 8, 8));
        leftPanel.setBorder(BorderFactory.createTitledBorder("Basic Operations"));  // Add a titled border
        leftPanel.setBackground(new Color(230, 255, 250));                         // Light teal background color

        // Array of basic operation names
        String[] basicOps = {"Add", "Subtract", "Multiply", "Divide"};

        // Loop through each basic operation to create styled buttons and add action listeners
        for (String op : basicOps) {
            JButton btn = createStyledButton(op, new Color(102, 205, 170)); // Create button with specific color (medium aquamarine)

            // Add action listener to perform the corresponding basic operation when clicked
            btn.addActionListener(e -> performBasicOperation(op));

            // Add the button to the left panel
            leftPanel.add(btn);
        }

        // Add the left panel to the west side of the container
        add(leftPanel, BorderLayout.WEST);


        // Create a JPanel for advanced operation buttons
        // GridLayout with 12 rows, 1 column, 8px horizontal and 5px vertical gaps
        JPanel rightPanel = new JPanel(new GridLayout(12, 1, 8, 5));
        rightPanel.setBorder(BorderFactory.createTitledBorder("Advanced Operations"));  // Add titled border
        rightPanel.setBackground(new Color(255, 245, 238));                            // Very light peach background color

// Array of advanced operation names
        String[] advOps = {
                "Square", "Square Root", "Power", "Modulus", "Logarithm", "Natural Log",
                "Factorial", "Reciprocal", "Matrix Addition", "Matrix Multiplication", "Matrix Transpose"
        };

        // Loop to create buttons for each advanced operation with action listeners
        for (String op : advOps) {
            JButton btn = createStyledButton(op, new Color(255, 160, 122));  // Button color: light salmon

            // Add action listener to perform advanced operation on click
            btn.addActionListener(e -> performAdvancedOperation(op));

            // Add button to the right panel
            rightPanel.add(btn);
        }

        // Add the right panel to the east side of the container
        add(rightPanel, BorderLayout.EAST);

        // TicTacToeGame class extends JFrame and listens for button clicks (ActionListener)
        class TicTacToeGame extends JFrame implements ActionListener {
            private JButton[][] buttons = new JButton[3][3];  // 3x3 grid of buttons for the board
            private char currentPlayer = 'X';                  // Current player symbol (X or O)
            private String playerX = "Paras";                   // Player X's name
            private String playerO = "Divya";                   // Player O's name
            private JLabel statusLabel;                         // Label to show game status (turns, winner)
            private int xWins = 0, oWins = 0;                   // Win counters for both players
            private JLabel scoreLabel;                          // Label to show scores

            // Constructor: sets up the GUI and game board
            public TicTacToeGame() {
                setTitle(" Tic-Tac-Toe: Paras vs Divya");      // Window title
                setSize(460, 530);                             // Fixed window size
                setResizable(false);                           // Disable window resizing
                setDefaultCloseOperation(DISPOSE_ON_CLOSE);  // Close only this window, not whole app
                setLocationRelativeTo(null);                   // Center window on screen
                setLayout(new BorderLayout());                  // Use BorderLayout for main frame
                getContentPane().setBackground(new Color(240, 248, 255)); // Light blue background

                // Define fonts for buttons and labels
                Font cellFont = new Font("Comic Sans MS", Font.BOLD, 48);
                Font labelFont = new Font("Segoe UI", Font.PLAIN, 18);
                Font buttonFont = new Font("Segoe UI", Font.BOLD, 16);

                // Panel to hold the 3x3 game buttons with spacing
                JPanel boardPanel = new JPanel(new GridLayout(3, 3, 4, 4));
                //GridLayout(3,3,4,4) — 3 rows, 3 columns, with 4 pixels horizontal and vertical gaps between buttons
                boardPanel.setBackground(new Color(173, 216, 230)); // Slightly darker blue
                boardPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding


                // Loop through each row (0 to 2)
                for (int i = 0; i < 3; i++) {
                    // Loop through each column (0 to 2) inside the current row
                    for (int j = 0; j < 3; j++) {
                        // Create a new JButton for each cell in the 3x3 grid with empty text
                        buttons[i][j] = new JButton("");

                        // Set the font style and size for the button text
                        buttons[i][j].setFont(cellFont);

                        // Set the button's background color to white
                        buttons[i][j].setBackground(Color.WHITE);

                        // Set the button's text color to dark gray
                        buttons[i][j].setForeground(Color.DARK_GRAY);

                        // Disable the focus painting on the button for cleaner look
                        buttons[i][j].setFocusPainted(false);

                        // Add a light-gray border around the button with 2-pixel thickness
                        buttons[i][j].setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));

                        // Add the current class (which implements ActionListener) as the action listener to handle button clicks
                        buttons[i][j].addActionListener(this);

                        // To use the button variable inside the anonymous inner class below, declare it final
                        final JButton btn = buttons[i][j];

                        // Add a mouse listener for hover effects
                        btn.addMouseListener(new java.awt.event.MouseAdapter() {
                            // When mouse enters the button area
                            public void mouseEntered(java.awt.event.MouseEvent evt) {
                                // If the button is enabled (not disabled), change background to a light cyan color
                                if (btn.isEnabled()) btn.setBackground(new Color(224, 255, 255));
                            }

                            // When mouse exits the button area
                            public void mouseExited(java.awt.event.MouseEvent evt) {
                                // If the button is enabled, revert background to white
                                if (btn.isEnabled()) btn.setBackground(Color.WHITE);
                            }
                        });

                        // Add the button to the board panel's layout
                        boardPanel.add(buttons[i][j]);
                    }
                }

                statusLabel = new JLabel("✨ " + playerX + "'s Turn (X)", SwingConstants.CENTER);
                // JLabel to show current player's turn or game status
                statusLabel.setFont(labelFont);// Set the font for the status label
                statusLabel.setForeground(new Color(0, 102, 153));// Set the text color to a dark blue
                statusLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center align the text
                statusLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 5, 0));// Add padding around the label

                // JLabel to show the score of both players
                // Initial score label with both players' names and scores
                scoreLabel = new JLabel("🏆 Score — " + playerX + ": 0 | " + playerO + ": 0", SwingConstants.CENTER);
                scoreLabel.setFont(labelFont);// Set the font for the score label
                scoreLabel.setForeground(new Color(60, 60, 60));// Set the text color to a dark gray

                // Button to restart the game
                JButton restartButton = new JButton(" Restart");
                restartButton.setFont(buttonFont);// Set the font for the restart button
                restartButton.setFocusPainted(false);// Disable focus painting for cleaner look
                // Set the background color to a light sky blue
                restartButton.setBackground(new Color(135, 206, 250));
                restartButton.setForeground(Color.WHITE);
                restartButton.setBorder(BorderFactory.createLineBorder(Color.GRAY));// Add a gray border around the button
                restartButton.setCursor(new Cursor(Cursor.HAND_CURSOR));// Change cursor to hand when hovering over the button

                // Add action listener to handle restart button clicks
                // When the restart button is clicked, show a confirmation dialog
                restartButton.addActionListener(e -> {
                    int choice = JOptionPane.showConfirmDialog(this, "Do you want to restart the game?", "Restart Game", JOptionPane.YES_NO_OPTION);
                    if (choice == JOptionPane.YES_OPTION) {
                        resetGame();
                    }
                });


                // Create a bottom panel to hold the score label, restart button, and status label
                // Use GridLayout with 3 rows, 1 column for vertical stacking
                JPanel bottomPanel = new JPanel(new GridLayout(3, 1));
                bottomPanel.setBackground(new Color(240, 248, 255));
                bottomPanel.add(scoreLabel);
                bottomPanel.add(restartButton);
                bottomPanel.add(statusLabel);

                // Add the board panel and bottom panel to the main frame
                setLayout(new BorderLayout()); // Set the layout manager to BorderLayout
                add(boardPanel, BorderLayout.CENTER);
                add(bottomPanel, BorderLayout.SOUTH);

                setVisible(true);// Make the game window visible
            }

            // This method is called when a button is clicked
            public void actionPerformed(ActionEvent e) {
                JButton btnClicked = (JButton) e.getSource();
                if (!btnClicked.getText().equals("")) return;

                btnClicked.setText(String.valueOf(currentPlayer));// Set the button text to the current player's symbol (X or O)
                btnClicked.setForeground(currentPlayer == 'X' ? new Color(0, 128, 255) : new Color(255, 69, 0));// Set text color based on player (X: blue, O: red)

                if (checkWin()) {// Check if the current player has won
                    String winnerName = (currentPlayer == 'X') ? playerX : playerO;
                    JOptionPane.showMessageDialog(this, "🎉 " + winnerName + " Wins!");
                    if (currentPlayer == 'X') xWins++;
                    else oWins++;
                    updateScore();
                    disableButtons();
                    statusLabel.setText("🎉 " + winnerName + " Wins!");
                } else if (isDraw()) {// Check if the game is a draw
                    JOptionPane.showMessageDialog(this, "🤝 It's a Draw!");
                    statusLabel.setText("🤝 It's a Draw!");
                } else {// If no win or draw, switch to the next player
                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                    String nextPlayer = (currentPlayer == 'X') ? playerX : playerO;
                    statusLabel.setText("✨ " + nextPlayer + "'s Turn (" + currentPlayer + ")");
                }
            }

            private boolean checkWin() {// Check all possible winning conditions
                // Check rows and columns for a win
                for (int i = 0; i < 3; i++) {
                    if (equal(buttons[i][0], buttons[i][1], buttons[i][2])) return true;
                    if (equal(buttons[0][i], buttons[1][i], buttons[2][i])) return true;
                }
                return equal(buttons[0][0], buttons[1][1], buttons[2][2]) ||
                        equal(buttons[0][2], buttons[1][1], buttons[2][0]);
            }

            // Check if the game is a draw (no empty buttons left)
            private boolean isDraw() {
                for (JButton[] row : buttons) {
                    for (JButton b : row) {
                        if (b.getText().equals("")) return false;
                    }
                }
                return true;
            }

            // Helper method to check if three buttons have the same text (and not empty)
            private boolean equal(JButton b1, JButton b2, JButton b3) {
                String t1 = b1.getText(), t2 = b2.getText(), t3 = b3.getText();
                return !t1.equals("") && t1.equals(t2) && t2.equals(t3);
            }

            // Reset the game state for a new game
            private void resetGame() {
                for (JButton[] row : buttons) {
                    for (JButton b : row) {
                        b.setText("");
                        b.setEnabled(true);
                        b.setBackground(Color.WHITE);
                    }
                }
                currentPlayer = 'X';
                statusLabel.setText("✨ " + playerX + "'s Turn (X)");
            }

            // Disable all buttons after a win or draw
            // This prevents further clicks and allows the game to end gracefully
            private void disableButtons() {
                for (JButton[] row : buttons) {
                    for (JButton b : row) {
                        b.setEnabled(false);
                    }
                }
            }

            // Update the score label with the current scores of both players
            private void updateScore() {
                scoreLabel.setText("🏆 Score — " + playerX + ": " + xWins + " | " + playerO + ": " + oWins);
            }
        }

        // Create a button for Tic-Tac-Toe game
        // This button will open the TicTacToeGame window when clicked
        JButton tttButton = createStyledButton("Game", new Color(255, 160, 122));
        tttButton.setMnemonic(KeyEvent.VK_T); // Alt + T opens Tic-Tac-Toe
        tttButton.setToolTipText("Click to play Tic-Tac-Toe");
        tttButton.setFont(new Font("Verdana", Font.BOLD, 14));
        tttButton.addActionListener(e -> new TicTacToeGame());
        rightPanel.add(tttButton);// Add the Tic-Tac-Toe button to the right panel


        JPanel centerPanel = new JPanel(new BorderLayout());// Create a panel for the center section
        centerPanel.setBackground(new Color(255, 250, 205));

        JLabel funLabel = new JLabel("<html><div style='text-align: center;'>💡 <b>Tip:</b> Click any operation button</div></html>", JLabel.CENTER);
        // Create a label with a fun tip and center alignment
        funLabel.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 15));// Set font to Comic Sans MS, bold and italic, size 15
        funLabel.setForeground(new Color(230, 46, 85)); // A fresher, more vibrant green (Forest Green)

        centerPanel.add(funLabel, BorderLayout.NORTH);// Add the fun tip label to the top of the center panel

        // Create text areas for history and error logs
        // JTextArea is used to display multiple lines of text, like logs or history
        historyLog = new JTextArea(8, 30);// Create a text area for history logs with 8 rows and 30 columns
        historyLog.setEditable(false);// Make the history log non-editable
        historyLog.setFont(new Font("Monospaced", Font.PLAIN, 12));// Monospaced font for better readability of logs
        historyLog.setForeground(new Color(0, 100, 0));// Dark green text color for history log
        historyLog.setLineWrap(true);// Enable line wrapping to prevent horizontal scrolling
        historyLog.setWrapStyleWord(true);// Enable line wrapping and word wrapping
        // JScrollPane allows scrolling through the text area if content exceeds visible area
        JScrollPane historyScroll = new JScrollPane(historyLog);// Create a scroll pane for the history log
        historyScroll.setBorder(BorderFactory.createTitledBorder("📜 History Log"));// Add a titled border to the scroll pane


        errorLog = new JTextArea(8, 30);// Create a text area for error logs with 8 rows and 30 columns
        errorLog.setEditable(false);// Make the error log non-editable
        errorLog.setFont(new Font("Monospaced", Font.PLAIN, 12));// Monospaced font for better readability of error logs
        errorLog.setForeground(Color.RED);// Red text color for error log to highlight errors
        errorLog.setLineWrap(true);// Enable line wrapping to prevent horizontal scrolling
        errorLog.setWrapStyleWord(true);// Enable line wrapping and word wrapping
        JScrollPane errorScroll = new JScrollPane(errorLog);// Create a scroll pane for the error log
        errorScroll.setBorder(BorderFactory.createTitledBorder("⚠️ Error Log"));// Add a titled border to the scroll pane

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, historyScroll, errorScroll);// Create a split pane to hold both logs
        splitPane.setResizeWeight(0.6);// Set the resize weight to give more space to the history log
        centerPanel.add(splitPane, BorderLayout.CENTER);// Add the split pane to the center panel

        add(centerPanel, BorderLayout.CENTER);// Add the center panel to the main frame

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));// Create a panel for the bottom section
        bottomPanel.setBackground(new Color(245, 255, 250));// Light mint background color for the bottom panel
        bottomPanel.add(new JLabel("✅ Result:"));// Add a label to indicate the result field
        result = new JTextField(20);// Create a text field for displaying results with 20 columns
        result.setFont(new Font("Consolas", Font.BOLD, 16));// Set font to Consolas, bold, size 16
        result.setEditable(false);// Make the result field non-editable
        result.setBackground(Color.WHITE);// Set background color to white for better visibility
        result.setForeground(Color.BLUE);// Set text color to blue for the result field
        bottomPanel.add(result);// Add the result text field to the bottom panel
        add(bottomPanel, BorderLayout.SOUTH);// Add the bottom panel to the main frame
        bottomPanel.setLayout(new FlowLayout()); // optional, default bhi chalega

        // Clear Button
        JButton clearBtn = new JButton("🧹 Clear All");// Create a button to clear all fields and logs
        clearBtn.setToolTipText("Clear all fields and logs"); // Add tooltip
        clearBtn.addActionListener(e -> {// Add action listener to clear fields and logs
            result.setText("");
            historyLog.setText("");
            errorLog.setText("");
        });
        // After creating 'clearBtn'
        Color originalColor = clearBtn.getBackground();// Store the original background color of the button
        Color blinkColor = new Color(255, 182, 193); // Light Pink
        Timer blinkTimer = new Timer(500, new ActionListener() {// Timer to blink the button background
            boolean isOriginal = true;

            // This method is called every time the timer ticks (every 500 ms)
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isOriginal) {
                    clearBtn.setBackground(blinkColor);// Change to blink color
                } else {
                    clearBtn.setBackground(originalColor);// Revert to original color
                }
                isOriginal = !isOriginal;// Toggle the state for the next blink

            }
        });
        blinkTimer.start();// Start the blinking timer
        bottomPanel.add(clearBtn);// Add the clear button to the bottom panel


        // Add the clear button to the bottom panel
        bottomPanel.add(clearBtn);

        add(bottomPanel, BorderLayout.SOUTH); // Add the bottom panel to the main frame

        // Set the default close operation to exit the application when the window is closed
        setVisible(true);


    }

    // Method to create a styled button with custom text and background color
    private JButton createStyledButton(String text, Color bgColor) {
        JButton btn = new JButton(text);// Create a new button with the specified text
        btn.setFont(new Font("Verdana", Font.BOLD, 14));// Set the font to Verdana, bold, size 14
        btn.setBackground(bgColor);// Set the background color of the button
        btn.setForeground(Color.WHITE);// Set the text color to white for better contrast
        btn.setFocusPainted(false);// Disable focus painting for a cleaner look
        return btn;// Return the styled button
    }

    // Method to log error messages in the error log text area
    private void logError(String msg) {
        errorLog.append(msg + "\n");
    }

    // Method to log history messages in the history log text area
    private void logHistory(String msg) {
        historyLog.append(msg + "\n");
    }

    // Method to perform basic operations like addition, subtraction, multiplication, and division
    private void performBasicOperation(String operation) {
        try {
            JOptionPane.showMessageDialog(this, "📘 This will perform: " + operation + "\nPlease enter two numbers.");
            String inputStr1 = JOptionPane.showInputDialog(this, "Enter first number:");
            if (inputStr1 == null) return;
            String inputStr2 = JOptionPane.showInputDialog(this, "Enter second number:");
            if (inputStr2 == null) return;

            double num1 = Double.parseDouble(inputStr1);
            double num2 = Double.parseDouble(inputStr2);
            double res = 0;

            switch (operation) {
                case "Add": res = num1 + num2; break;
                case "Subtract": res = num1 - num2; break;
                case "Multiply": res = num1 * num2; break;
                case "Divide":
                    if (num2 == 0) {
                        JOptionPane.showMessageDialog(this, "❌ Cannot divide by zero.");
                        logError("Attempted division by zero");
                        return;
                    }
                    res = num1 / num2;
                    break;
            }

            result.setText(String.valueOf(res));
            logHistory(operation + ": " + num1 + " and " + num2 + " => Result = " + res);
            JOptionPane.showMessageDialog(this, "🎯 Result = " + res + " 🎉");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "⚠️ Please enter valid numeric values.");
            logError("Invalid number format entered for " + operation);
        }
    }

    private void performAdvancedOperation(String operation) {
        try {
            switch (operation) {
                case "Square":
                    String sq = JOptionPane.showInputDialog(this, "Enter number:");
                    if (sq == null) return;
                    double s = Double.parseDouble(sq);
                    result.setText(String.valueOf(s * s));
                    logHistory("Square of " + s + " => " + (s * s));
                    break;

                case "Square Root":
                    String sqrt = JOptionPane.showInputDialog(this, "Enter number:");
                    if (sqrt == null) return;
                    double r = Double.parseDouble(sqrt);
                    if (r < 0) {
                        JOptionPane.showMessageDialog(this, "❌ Cannot take root of negative number.");
                        logError("Attempted square root of negative number: " + r);
                        return;
                    }
                    result.setText(String.valueOf(Math.sqrt(r)));
                    logHistory("Square root of " + r + " => " + Math.sqrt(r));
                    break;

                case "Power":
                    String base = JOptionPane.showInputDialog(this, "Enter base (x):");
                    if (base == null) return;
                    String exp = JOptionPane.showInputDialog(this, "Enter exponent (y):");
                    if (exp == null) return;
                    double b = Double.parseDouble(base);
                    double e = Double.parseDouble(exp);
                    result.setText(String.valueOf(Math.pow(b, e)));
                    logHistory(b + " raised to the power " + e + " => " + Math.pow(b, e));
                    break;

                case "Modulus":
                    String x = JOptionPane.showInputDialog(this, "Enter x:");
                    if (x == null) return;
                    String y = JOptionPane.showInputDialog(this, "Enter y:");
                    if (y == null) return;
                    double m1 = Double.parseDouble(x);
                    double m2 = Double.parseDouble(y);
                    result.setText(String.valueOf(m1 % m2));
                    logHistory("Modulus: " + m1 + " % " + m2 + " => " + (m1 % m2));
                    break;

                case "Logarithm":
                    String logInput = JOptionPane.showInputDialog(this, "Enter number > 0:");
                    if (logInput == null) return;
                    double logNum = Double.parseDouble(logInput);
                    if (logNum <= 0) {
                        JOptionPane.showMessageDialog(this, "❌ Logarithm undefined for zero or negative numbers.");
                        logError("Attempted logarithm of invalid number: " + logNum);
                        return;
                    }
                    result.setText(String.valueOf(Math.log10(logNum)));
                    logHistory("Log10 of " + logNum + " => " + Math.log10(logNum));
                    break;

                case "Natural Log":
                    String lnInput = JOptionPane.showInputDialog(this, "Enter number > 0:");
                    if (lnInput == null) return;
                    double lnNum = Double.parseDouble(lnInput);
                    if (lnNum <= 0) {
                        JOptionPane.showMessageDialog(this, "❌ Natural log undefined for zero or negative numbers.");
                        logError("Attempted natural log of invalid number: " + lnNum);
                        return;
                    }
                    result.setText(String.valueOf(Math.log(lnNum)));
                    logHistory("Natural log of " + lnNum + " => " + Math.log(lnNum));
                    break;

                case "Factorial":
                    String factInput = JOptionPane.showInputDialog(this, "Enter number:");

                    try {
                        int factNum = Integer.parseInt(factInput);

                        if (factNum < 0) {
                            JOptionPane.showMessageDialog(this, "❌ Factorial is not defined for negative numbers.");
                            logError("Attempted factorial of negative number: " + factNum);
                            return;
                        }

                        long factResult = 1;
                        for (int i = 1; i <= factNum; i++) {
                            factResult *= i;
                        }

                        result.setText("Factorial = " + factResult);
                        logHistory("Factorial of " + factNum + " = " + factResult);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "❌ Please enter a valid integer for factorial.");
                        logError("Invalid factorial input: " + factInput);
                    }


                    break;

                case "Reciprocal":
                    String recInput = JOptionPane.showInputDialog(this, "Enter number (not zero):");
                    if (recInput == null) return;
                    double recNum = Double.parseDouble(recInput);
                    if (recNum == 0) {
                        JOptionPane.showMessageDialog(this, "❌ Reciprocal undefined for zero.");
                        logError("Attempted reciprocal of zero.");
                        return;
                    }
                    result.setText(String.valueOf(1.0 / recNum));
                    logHistory("Reciprocal of " + recNum + " => " + (1.0 / recNum));
                    break;

                case "Matrix Addition":
                    int size = 2; // fixed 2x2 matrices
                    double[][] n1 = new double[size][size];
                    double[][] n2 = new double[size][size];
                    double[][] resultMatrix = new double[size][size];

                    try {
                        // Input Matrix A with logging
                        for (int i = 0; i < size; i++) {
                            for (int j = 0; j < size; j++) {
                                String val = JOptionPane.showInputDialog(this, "Matrix A[" + (i + 1) + "][" + (j + 1) + "]:");
                                if (val == null) return;
                                n1[i][j] = Double.parseDouble(val);
                                logHistory("Matrix A[" + (i + 1) + "][" + (j + 1) + "] = " + val);
                            }
                        }
                        // Input Matrix B with logging
                        for (int i = 0; i < size; i++) {
                            for (int j = 0; j < size; j++) {
                                String val = JOptionPane.showInputDialog(this, "Matrix B[" + (i + 1) + "][" + (j + 1) + "]:");
                                if (val == null) return;
                                n2[i][j] = Double.parseDouble(val);
                                logHistory("Matrix B[" + (i + 1) + "][" + (j + 1) + "] = " + val);
                            }
                        }
                        // Add matrices
                        StringBuilder sb = new StringBuilder("Matrix Addition Result:\n");
                        for (int i = 0; i < size; i++) {
                            for (int j = 0; j < size; j++) {
                                resultMatrix[i][j] = n1[i][j] + n2[i][j];
                                sb.append(String.format("%.2f", resultMatrix[i][j])).append("\t");
                            }
                            sb.append("\n");
                        }
                        result.setText("Matrix Addition done");
                        logHistory(sb.toString());
                        JOptionPane.showMessageDialog(this, sb.toString());

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "⚠️ Invalid numeric value entered.");
                        logError("Invalid matrix input for addition.");
                    }
                    break;

                case "Matrix Multiplication":
                    size = 2;
                    n1 = new double[size][size];
                    n2 = new double[size][size];
                    resultMatrix = new double[size][size];

                    try {
                        // Input Matrix A with logging
                        for (int i = 0; i < size; i++) {
                            for (int j = 0; j < size; j++) {
                                String val = JOptionPane.showInputDialog(this, "Matrix A[" + (i + 1) + "][" + (j + 1) + "]:");
                                if (val == null) return;
                                n1[i][j] = Double.parseDouble(val);
                                logHistory("Matrix A[" + (i + 1) + "][" + (j + 1) + "] = " + val);
                            }
                        }
                        // Input Matrix B with logging
                        for (int i = 0; i < size; i++) {
                            for (int j = 0; j < size; j++) {
                                String val = JOptionPane.showInputDialog(this, "Matrix B[" + (i + 1) + "][" + (j + 1) + "]:");
                                if (val == null) return;
                                n2[i][j] = Double.parseDouble(val);
                                logHistory("Matrix B[" + (i + 1) + "][" + (j + 1) + "] = " + val);
                            }
                        }
                        // Multiply matrices
                        for (int i = 0; i < size; i++) {
                            for (int j = 0; j < size; j++) {
                                resultMatrix[i][j] = 0;
                                for (int k = 0; k < size; k++) {
                                    resultMatrix[i][j] += n1[i][k] * n2[k][j];
                                }
                            }
                        }
                        StringBuilder sb = new StringBuilder("Matrix Multiplication Result:\n");
                        for (int i = 0; i < size; i++) {
                            for (int j = 0; j < size; j++) {
                                sb.append(String.format("%.2f", resultMatrix[i][j])).append("\t");
                            }
                            sb.append("\n");
                        }
                        result.setText("Matrix Multiplication done");
                        logHistory(sb.toString());
                        JOptionPane.showMessageDialog(this, sb.toString());

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "⚠️ Invalid numeric value entered.");
                        logError("Invalid matrix input for multiplication.");
                    }
                    break;
                case "Matrix Transpose":
                    size = 2;
                    double[][] matrix = new double[size][size];
                    double[][] transpose = new double[size][size];
                    try {
                        // Input matrix
                        for (int i = 0; i < size; i++) {
                            for (int j = 0; j < size; j++) {
                                String val = JOptionPane.showInputDialog(this, "Matrix[" + (i + 1) + "][" + (j + 1) + "]:");
                                if (val == null) return;
                                matrix[i][j] = Double.parseDouble(val);
                                logHistory("Matrix[" + (i + 1) + "][" + (j + 1) + "] = " + val);
                            }
                        }
                        // Transpose
                        for (int i = 0; i < size; i++) {
                            for (int j = 0; j < size; j++) {
                                transpose[j][i] = matrix[i][j];
                            }
                        }
                        // Display result
                        StringBuilder sb = new StringBuilder("Matrix Transpose Result:\n");
                        for (int i = 0; i < size; i++) {
                            for (int j = 0; j < size; j++) {
                                sb.append(String.format("%.2f", transpose[i][j])).append("\t");
                            }
                            sb.append("\n");
                        }
                        result.setText("Transpose done");
                        logHistory(sb.toString());
                        JOptionPane.showMessageDialog(this, sb.toString());

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "⚠️ Invalid numeric value entered.");
                        logError("Invalid matrix input for transpose.");
                    }
                    break;



            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "⚠️ Invalid numeric value entered.");
            logError("Invalid input for " + operation);
        }
    }

    private long factorial(int n) {
        long res = 1;
        for (int i = 2; i <= n; i++) res *= i;
        return res;
    }

    public static void main(String[] args) {
        // Set the look and feel to the system default
        // This makes the application look consistent with the user's OS theme
        SwingUtilities.invokeLater(Calculator::new);

    }
}
