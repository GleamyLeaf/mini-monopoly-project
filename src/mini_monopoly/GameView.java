package mini_monopoly;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * View class. Reads the model and updates the UI widgets. Also
 * contains the handlers wired up by the main GUI buttons and menu.
 */
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
        javax.swing.JMenuItem tradeItem = new javax.swing.JMenuItem("Trade");
        tradeItem.addActionListener(e -> onOpenTrade());
        gameMenu.add(tradeItem);
        menuBar.add(gameMenu);
        gui.setJMenuBar(menuBar);

        refresh();
    }

    /** Reads the model and updates all labels / button states. */
    public void refresh() {
        // update land name and price labels in ui
        Land[] lands = model.getLands();
        for (int i = 0; i < lands.length && i < landNameLabels.length; i++) {
            landNameLabels[i].setText(lands[i].getName());
            landPriceLabels[i].setText("$" + lands[i].getPrice());
        }
        
        // update owner color panel (indicator)
        for (int i = 0; i < lands.length && i < ownerColorPanels.length; i++) {
            int owner = lands[i].getOwnerIndex();
            ownerColorPanels[i].setBackground(owner >= 0 ? PLAYER_COLORS[owner] : Color.LIGHT_GRAY);
        }
        
        // update each player's status info
        for (int i = 0; i < GameModel.NUM_PLAYERS; i++) {
            Player p = model.getPlayer(i);
            
            balanceLabels[i].setText("$" + p.getBalance());
            positionLabels[i].setText(String.valueOf(p.getPosition()));
            statusLabels[i].setText(p.isActive() ? "Active" : "Bankrupt");
        }
        
        // update turn and dice label
        turnLabel.setText("Player " + (model.getCurrentTurn() + 1));
        diceLabel.setText(controller.getLastDiceRoll() > 0 ? String.valueOf(controller.getLastDiceRoll()) : "-");
        
        // update slot number label
        for (int i = 0; i < slotNumLabels.length; i++) {
            String name = model.getSquareName(i);
            if (!name.isEmpty()) {
                slotNumLabels[i].setText("<html><b>" + i + "</b><br>" + name + "</html>");
            } else {
                slotNumLabels[i].setText(String.valueOf(i));
            }
            slotPanels[i].setBorder(javax.swing.BorderFactory.createEtchedBorder());
        }
        
        // 
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
        
        // enable all dice buy and end button
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

    /**
     * Opens the trade dialog. Only allowed before rolling the dice.
     * The current player picks buy or sell, picks the counterparty,
     * picks the slot, enters a price, and the other player confirms.
     */
    public void onOpenTrade() {
        if (model.isGameOver()) {
            JOptionPane.showMessageDialog(gui, "Game is over.");
            return;
        }
        if (controller.hasRolled()) {
            JOptionPane.showMessageDialog(gui,
                "Trading is only allowed before rolling the dice.");
            return;
        }
        int currentIdx = model.getCurrentTurn();
        if (!model.getPlayer(currentIdx).isActive()) return;

        Object[] dirs = {"Sell", "Buy"};
        int dir = JOptionPane.showOptionDialog(gui,
            "Player " + (currentIdx + 1) + " — trade direction:",
            "Trade", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
            null, dirs, dirs[0]);
        if (dir < 0) return;
        if (dir == 0) trySell(currentIdx);
        else tryBuy(currentIdx);
    }

    private void trySell(int sellerIdx) {
        List<Integer> owned = controller.getOwnedProperties(sellerIdx);
        if (owned.isEmpty()) {
            JOptionPane.showMessageDialog(gui, "You don't own any properties.");
            return;
        }
        int landIdx = pickLand(owned, "Select property to sell:");
        if (landIdx < 0) return;

        List<Integer> buyers = new ArrayList<>();
        for (int i = 0; i < GameModel.NUM_PLAYERS; i++) {
            if (i != sellerIdx && model.getPlayer(i).isActive()) buyers.add(i);
        }
        if (buyers.isEmpty()) {
            JOptionPane.showMessageDialog(gui, "No active counterparty.");
            return;
        }
        int buyerIdx = pickPlayer(buyers, "Select buyer:");
        if (buyerIdx < 0) return;

        int price = askPrice();
        if (price < 0) return;
        doTrade(sellerIdx, buyerIdx, landIdx, price);
    }

    private void tryBuy(int buyerIdx) {
        List<Integer> sellers = new ArrayList<>();
        for (int i = 0; i < GameModel.NUM_PLAYERS; i++) {
            if (i != buyerIdx && model.getPlayer(i).isActive()
                && !controller.getOwnedProperties(i).isEmpty()) sellers.add(i);
        }
        if (sellers.isEmpty()) {
            JOptionPane.showMessageDialog(gui, "Nobody has a property to sell.");
            return;
        }
        int sellerIdx = pickPlayer(sellers, "Select seller:");
        if (sellerIdx < 0) return;

        List<Integer> owned = controller.getOwnedProperties(sellerIdx);
        int landIdx = pickLand(owned, "Select property to buy:");
        if (landIdx < 0) return;

        int price = askPrice();
        if (price < 0) return;
        doTrade(sellerIdx, buyerIdx, landIdx, price);
    }

    private void doTrade(int sellerIdx, int buyerIdx, int landIdx, int price) {
        Land land = model.getLand(landIdx);
        String msg = "Player " + (sellerIdx + 1) + " sells " + land.getName()
            + " to Player " + (buyerIdx + 1) + " for $" + price + ".\nBoth agree?";
        int confirm = JOptionPane.showConfirmDialog(gui, msg,
            "Confirm Trade", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;

        boolean ok = controller.tradeLand(sellerIdx, buyerIdx, landIdx, price);
        if (!ok) {
            JOptionPane.showMessageDialog(gui, "Trade failed.");
            return;
        }
        refresh();
        showMessage(controller.getLastMessage());
    }

    private int pickLand(List<Integer> landIndices, String prompt) {
        String[] opts = new String[landIndices.size()];
        for (int i = 0; i < landIndices.size(); i++) {
            Land l = model.getLand(landIndices.get(i));
            opts[i] = "Slot " + GameModel.PROPERTY_SLOTS[landIndices.get(i)]
                + " - " + l.getName() + " ($" + l.getPrice() + ")";
        }
        String choice = (String) JOptionPane.showInputDialog(gui, prompt, "Trade",
            JOptionPane.PLAIN_MESSAGE, null, opts, opts[0]);
        if (choice == null) return -1;
        for (int i = 0; i < opts.length; i++) if (opts[i].equals(choice)) return landIndices.get(i);
        return -1;
    }

    private int pickPlayer(List<Integer> playerIndices, String prompt) {
        String[] opts = new String[playerIndices.size()];
        for (int i = 0; i < playerIndices.size(); i++) {
            opts[i] = "Player " + (playerIndices.get(i) + 1);
        }
        String choice = (String) JOptionPane.showInputDialog(gui, prompt, "Trade",
            JOptionPane.PLAIN_MESSAGE, null, opts, opts[0]);
        if (choice == null) return -1;
        for (int i = 0; i < opts.length; i++) if (opts[i].equals(choice)) return playerIndices.get(i);
        return -1;
    }

    private int askPrice() {
        String input = JOptionPane.showInputDialog(gui, "Enter agreed price:");
        if (input == null) return -1;
        try {
            int p = Integer.parseInt(input.trim());
            if (p < 0) return -1;
            return p;
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(gui, "Invalid price.");
            return -1;
        }
    }

    private void showMessage(String msg) {
        if (msg != null && !msg.trim().isEmpty()) {
            JOptionPane.showMessageDialog(gui, msg);
        }
    }
}
