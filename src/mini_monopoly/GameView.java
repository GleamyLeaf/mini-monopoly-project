package mini_monopoly;

import java.awt.Color;
import java.util.List;
import javax.swing.JOptionPane;

public class GameView {

    private static final Color[] PLAYER_COLORS = {
        new Color(255, 102, 102),
        new Color(153, 255, 153),
        new Color(102, 153, 255),
        new Color(255, 255, 153)
    };

    private GameModel model;
    private GameController controller;
    private MiniMonopolyGUI gui;

    private javax.swing.JLabel[] landNameLabels;
    private javax.swing.JLabel[] landPriceLabels;
    private javax.swing.JPanel[] ownerColorPanels;
    private javax.swing.JPanel[] slotPanels;
    private javax.swing.JLabel[] slotNumLabels;
    private javax.swing.JLabel[] balanceLabels;
    private javax.swing.JLabel[] positionLabels;
    private javax.swing.JLabel[] statusLabels;
    private javax.swing.JLabel turnLabel;
    private javax.swing.JLabel diceLabel;
    private javax.swing.JButton diceButton;
    private javax.swing.JButton buyButton;
    private javax.swing.JButton endButton;

    public GameView(GameModel model, GameController controller, MiniMonopolyGUI gui,
                    javax.swing.JLabel[] landNameLabels, javax.swing.JLabel[] landPriceLabels,
                    javax.swing.JPanel[] ownerColorPanels,
                    javax.swing.JPanel[] slotPanels, javax.swing.JLabel[] slotNumLabels,
                    javax.swing.JLabel[] balanceLabels, javax.swing.JLabel[] positionLabels,
                    javax.swing.JLabel[] statusLabels,
                    javax.swing.JLabel turnLabel, javax.swing.JLabel diceLabel,
                    javax.swing.JButton diceButton, javax.swing.JButton buyButton,
                    javax.swing.JButton endButton) {
        this.model = model;
        this.controller = controller;
        this.gui = gui;
        this.landNameLabels = landNameLabels;
        this.landPriceLabels = landPriceLabels;
        this.ownerColorPanels = ownerColorPanels;
        this.slotPanels = slotPanels;
        this.slotNumLabels = slotNumLabels;
        this.balanceLabels = balanceLabels;
        this.positionLabels = positionLabels;
        this.statusLabels = statusLabels;
        this.turnLabel = turnLabel;
        this.diceLabel = diceLabel;
        this.diceButton = diceButton;
        this.buyButton = buyButton;
        this.endButton = endButton;

        javax.swing.JMenuBar menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu gameMenu = new javax.swing.JMenu("Game");
        javax.swing.JMenuItem sellItem = new javax.swing.JMenuItem("Sell Property");
        sellItem.addActionListener(e -> onSellProperty());
        gameMenu.add(sellItem);
        menuBar.add(gameMenu);
        gui.setJMenuBar(menuBar);

        refresh();
    }

    public void refresh() {
        Land[] lands = model.getLands();
        for (int i = 0; i < lands.length && i < landNameLabels.length; i++) {
            landNameLabels[i].setText(lands[i].getName());
            landPriceLabels[i].setText("$" + lands[i].getPrice());
        }

        for (int i = 0; i < lands.length && i < ownerColorPanels.length; i++) {
            int owner = lands[i].getOwnerIndex();
            ownerColorPanels[i].setBackground(owner >= 0 ? PLAYER_COLORS[owner] : Color.LIGHT_GRAY);
        }

        for (int i = 0; i < GameModel.NUM_PLAYERS; i++) {
            Player p = model.getPlayer(i);
            balanceLabels[i].setText("$" + p.getBalance());
            positionLabels[i].setText(String.valueOf(p.getPosition()));
            if (!p.isActive()) statusLabels[i].setText("Bankrupt");
            else if (p.isInJail()) statusLabels[i].setText("In Jail");
            else statusLabels[i].setText("Active");
        }

        turnLabel.setText("Player " + (model.getCurrentTurn() + 1));
        diceLabel.setText(controller.getLastDiceRoll() > 0
            ? String.valueOf(controller.getLastDiceRoll()) : "-");

        for (int i = 0; i < slotNumLabels.length; i++) {
            String name = model.getSquareName(i);
            if (!name.isEmpty()) {
                slotNumLabels[i].setText("<html><b>" + i + "</b><br>" + name + "</html>");
            } else {
                slotNumLabels[i].setText(String.valueOf(i));
            }
            slotPanels[i].setBorder(javax.swing.BorderFactory.createEtchedBorder());
        }

        for (int p = 0; p < GameModel.NUM_PLAYERS; p++) {
            Player player = model.getPlayer(p);
            if (player.isActive()) {
                int pos = player.getPosition();
                String txt = slotNumLabels[pos].getText();
                String hex = String.format("#%02x%02x%02x",
                    PLAYER_COLORS[p].getRed(), PLAYER_COLORS[p].getGreen(), PLAYER_COLORS[p].getBlue());
                String tag = "<font color='" + hex + "'><b>P" + (p + 1) + "</b></font>";
                if (txt.contains("</html>"))
                    txt = txt.replace("</html>", " " + tag + "</html>");
                else
                    txt = "<html>" + txt + " " + tag + "</html>";
                slotNumLabels[pos].setText(txt);
                slotPanels[pos].setBorder(
                    javax.swing.BorderFactory.createLineBorder(PLAYER_COLORS[p], 3));
            }
        }

        diceButton.setEnabled(!controller.hasRolled() && !model.isGameOver());
        buyButton.setEnabled(controller.canBuyLand());
        endButton.setEnabled(controller.hasRolled() && !model.isGameOver());
    }

    public void onRollDice() {
        controller.rollDice();
        refresh();
        showMessage(controller.getLastMessage());
        if (model.isGameOver() && model.getWinnerIndex() >= 0) {
            JOptionPane.showMessageDialog(gui,
                "Player " + (model.getWinnerIndex() + 1) + " wins!",
                "Game Over", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void onBuyLand() {
        controller.buyLand();
        refresh();
        showMessage(controller.getLastMessage());
    }

    public void onEndTurn() {
        controller.endTurn();
        refresh();
    }

    public void onOpenEditor() {
        new GameEditorGUI(model, controller, this).setVisible(true);
    }

    public void onSellProperty() {
        if (model.isGameOver()) return;
        List<Integer> owned = controller.getOwnedProperties(model.getCurrentTurn());
        if (owned.isEmpty()) {
            JOptionPane.showMessageDialog(gui, "You don't own any properties.");
            return;
        }
        String[] options = new String[owned.size()];
        for (int i = 0; i < owned.size(); i++) {
            Land land = model.getLand(owned.get(i));
            options[i] = land.getName() + " (sell for $" + (land.getPrice() / 2) + ")";
        }
        String choice = (String) JOptionPane.showInputDialog(gui,
            "Select property to sell:", "Sell Property",
            JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        if (choice != null) {
            for (int i = 0; i < options.length; i++) {
                if (options[i].equals(choice)) {
                    controller.sellProperty(owned.get(i));
                    refresh();
                    showMessage(controller.getLastMessage());
                    break;
                }
            }
        }
    }

    private void showMessage(String msg) {
        if (msg != null && !msg.trim().isEmpty()) {
            JOptionPane.showMessageDialog(gui, msg);
        }
    }
}
