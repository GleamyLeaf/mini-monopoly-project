import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MiniMonopolyGUITest extends JFrame {

    // Core game state (temporary – move to Model later)
    private final String[] playerNames = {"Player 1", "Player 2", "Player 3", "Player 4"};
    private final Color[] playerColors = {Color.BLUE, Color.RED, Color.GREEN, Color.YELLOW};
    private int currentTurn = 0;

    private int[] balances = {2000, 2000, 2000, 2000};
    private int[] positions = {0, 0, 0, 0};
    private String[] statuses = {"Active", "Active", "Active", "Active"};

    // UI Components
    private final JLabel[] playerBalanceLabels = new JLabel[4];
    private final JLabel[] playerPositionLabels = new JLabel[4];
    private final JLabel[] playerStatusLabels = new JLabel[4];
    private final JLabel currentTurnLabel = new JLabel("Current Turn: " + playerNames[0], SwingConstants.CENTER);
    private final JTextArea gameLog = new JTextArea(10, 40);

    private final JButton rollDiceButton = new JButton("Roll Dice (1-10)");
    private final JButton buyButton = new JButton("Buy Land");
    private final JButton endTurnButton = new JButton("End Turn");
    private final JButton editorButton = new JButton("Editor");  // ← new visible button

    public MiniMonopolyGUITest() {
        super("Mini-Monopoly – The Hang Seng University Edition");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1100, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        initComponents();
        updateTurnDisplay();

        setVisible(true);
    }

    private void initComponents() {
        // Top: Player status panels
        JPanel topPanel = new JPanel(new GridLayout(1, 4, 10, 10));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        for (int i = 0; i < 4; i++) {
            topPanel.add(createPlayerStatusPanel(i));
        }
        add(topPanel, BorderLayout.NORTH);

        // Center: Board + Log
        JSplitPane centerSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        centerSplit.setResizeWeight(0.65);

        centerSplit.setLeftComponent(new JScrollPane(createSimpleBoardPanel()));

        JPanel rightPanel = new JPanel(new BorderLayout(8, 8));
        rightPanel.setBorder(BorderFactory.createTitledBorder("Game Log & Actions"));

        gameLog.setEditable(false);
        gameLog.setFont(new Font("Consolas", Font.PLAIN, 13));
        rightPanel.add(new JScrollPane(gameLog), BorderLayout.CENTER);

        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        actionPanel.add(rollDiceButton);
        actionPanel.add(buyButton);
        actionPanel.add(endTurnButton);
        rightPanel.add(actionPanel, BorderLayout.SOUTH);

        centerSplit.setRightComponent(rightPanel);
        add(centerSplit, BorderLayout.CENTER);

        // Bottom: Current turn + Editor button
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));

        currentTurnLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        currentTurnLabel.setForeground(new Color(40, 80, 150));
        bottomPanel.add(currentTurnLabel, BorderLayout.CENTER);

        // Small editor button on the right
        editorButton.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        editorButton.setForeground(new Color(120, 120, 120));
        editorButton.setFocusPainted(false);
        editorButton.addActionListener(e -> openGameEditor());
        bottomPanel.add(editorButton, BorderLayout.EAST);

        add(bottomPanel, BorderLayout.SOUTH);

        // Placeholder button actions
        rollDiceButton.addActionListener(e -> log("→ " + playerNames[currentTurn] + " is rolling..."));
        buyButton.addActionListener(e -> log("→ Buy button pressed (to be implemented)"));
        endTurnButton.addActionListener(e -> {
            currentTurn = (currentTurn + 1) % 4;
            updateTurnDisplay();
            log("→ Turn passed to " + playerNames[currentTurn]);
        });
    }

    private JPanel createPlayerStatusPanel(int id) {
        JPanel p = new JPanel(new GridLayout(4, 1, 4, 4));
        p.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(playerColors[id], 2),
                playerNames[id]));

        playerBalanceLabels[id] = new JLabel("Balance: $" + balances[id]);
        playerPositionLabels[id] = new JLabel("Position: " + positions[id]);
        playerStatusLabels[id] = new JLabel("Status: " + statuses[id]);

        p.add(playerBalanceLabels[id]);
        p.add(playerPositionLabels[id]);
        p.add(playerStatusLabels[id]);
        return p;
    }

    private JPanel createSimpleBoardPanel() {
        JPanel board = new JPanel(new GridLayout(0, 8, 4, 4));
        board.setBorder(BorderFactory.createTitledBorder("Game Board (simplified linear)"));

        for (int i = 0; i < 44; i++) {
            JLabel slot = new JLabel(String.format("%2d", i), SwingConstants.CENTER);
            slot.setOpaque(true);
            slot.setBackground(i == 0 ? new Color(220, 240, 220) : Color.WHITE);
            slot.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            board.add(slot);
        }
        return board;
    }

    private void updateTurnDisplay() {
        currentTurnLabel.setText("Current Turn: " + playerNames[currentTurn]);
        currentTurnLabel.setForeground(playerColors[currentTurn]);

        rollDiceButton.setEnabled(true);
        buyButton.setEnabled(false);
        endTurnButton.setEnabled(false);
    }

    private void log(String message) {
        gameLog.append(message + "\n");
        gameLog.setCaretPosition(gameLog.getDocument().getLength());
    }

    // ─────────────────────────────────────────────
    // Game Editor Dialog
    // ─────────────────────────────────────────────
    private void openGameEditor() {
        JDialog editor = new JDialog(this, "Game Editor (Testing Tool)", true);
        editor.setSize(520, 420);
        editor.setLocationRelativeTo(this);
        editor.setLayout(new BorderLayout(10, 10));

        JPanel mainPanel = new JPanel(new GridLayout(0, 2, 10, 12));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JTextField[] balanceFields = new JTextField[4];
        JTextField[] positionFields = new JTextField[4];
        JComboBox<String>[] statusCombos = new JComboBox[4];

        for (int i = 0; i < 4; i++) {
            mainPanel.add(new JLabel(playerNames[i] + " - Balance ($):"));
            balanceFields[i] = new JTextField(String.valueOf(balances[i]));
            mainPanel.add(balanceFields[i]);

            mainPanel.add(new JLabel(playerNames[i] + " - Position (0–43):"));
            positionFields[i] = new JTextField(String.valueOf(positions[i]));
            mainPanel.add(positionFields[i]);

            mainPanel.add(new JLabel(playerNames[i] + " - Status:"));
            statusCombos[i] = new JComboBox<>(new String[]{"Active", "Bankrupt"});
            statusCombos[i].setSelectedItem(statuses[i]);
            mainPanel.add(statusCombos[i]);
        }

        mainPanel.add(new JLabel("Current Turn Player:"));
        JComboBox<String> turnCombo = new JComboBox<>(playerNames);
        turnCombo.setSelectedIndex(currentTurn);
        mainPanel.add(turnCombo);

        editor.add(new JScrollPane(mainPanel), BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 10));
        JButton applyButton = new JButton("Apply & Close");
        JButton cancelButton = new JButton("Cancel");

        applyButton.addActionListener(e -> {
            try {
                for (int i = 0; i < 4; i++) {
                    balances[i] = Integer.parseInt(balanceFields[i].getText().trim());
                    positions[i] = Integer.parseInt(positionFields[i].getText().trim());
                    statuses[i] = (String) statusCombos[i].getSelectedItem();
                }
                currentTurn = turnCombo.getSelectedIndex();

                // Refresh UI
                for (int i = 0; i < 4; i++) {
                    playerBalanceLabels[i].setText("Balance: $" + balances[i]);
                    playerPositionLabels[i].setText("Position: " + positions[i]);
                    playerStatusLabels[i].setText("Status: " + statuses[i]);
                }
                updateTurnDisplay();

                log("→ Game state updated via Editor");

                editor.dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(editor,
                        "Please enter valid numbers for balance and position.",
                        "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelButton.addActionListener(e -> editor.dispose());

        buttonPanel.add(cancelButton);
        buttonPanel.add(applyButton);
        editor.add(buttonPanel, BorderLayout.SOUTH);

        editor.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MiniMonopolyGUITest());
    }
}