/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package mini_monopoly;

/**
 * Hidden game editor window. Lets the user change balance, position,
 * status, turn and land owners for testing. Opened by Ctrl+E or by
 * triple clicking the bottom right corner of the main window.
 */
public class GameEditorGUI extends javax.swing.JFrame {

    private GameModel model;
    private GameController controller;
    private GameView gameView;
    private javax.swing.JTable landTable;

    /**
     * Creates new form GameEditorGUI
     */
    public GameEditorGUI() {
        initComponents();
        setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
    }

    public GameEditorGUI(GameModel model, GameController controller, GameView gameView) {
        initComponents();
        setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
        this.model = model;
        this.controller = controller;
        this.gameView = gameView;

        installLandTable();
        loadFromModel();

        saveButton.addActionListener(e -> saveToModel());
    }

    private void installLandTable() {
        Land[] lands = model.getLands();
        String[] cols = {"Slot", "Land", "Owner"};
        Object[][] rows = new Object[lands.length][3];
        for (int i = 0; i < lands.length; i++) {
            rows[i][0] = GameModel.PROPERTY_SLOTS[i];
            rows[i][1] = lands[i].getName();
            rows[i][2] = ownerLabel(lands[i].getOwnerIndex());
        }
        javax.swing.table.DefaultTableModel tm = new javax.swing.table.DefaultTableModel(rows, cols) {
            @Override public boolean isCellEditable(int r, int c) { return c == 2; }
        };
        landTable = new javax.swing.JTable(tm);
        landTable.setRowHeight(22);
        javax.swing.JComboBox<String> ownerBox = new javax.swing.JComboBox<>(
            new String[]{"None", "P1", "P2", "P3", "P4"});
        landTable.getColumnModel().getColumn(0).setMaxWidth(60);
        landTable.getColumnModel().getColumn(2).setCellEditor(new javax.swing.DefaultCellEditor(ownerBox));

        javax.swing.JScrollPane scroll = new javax.swing.JScrollPane(landTable);
        scroll.setBorder(javax.swing.BorderFactory.createTitledBorder("Land Ownership"));
        scroll.setPreferredSize(new java.awt.Dimension(320, 0));

        javax.swing.JPanel existing = (javax.swing.JPanel) getContentPane();
        javax.swing.JPanel wrapper = new javax.swing.JPanel(new java.awt.BorderLayout());
        wrapper.add(existing, java.awt.BorderLayout.CENTER);
        wrapper.add(scroll, java.awt.BorderLayout.EAST);
        setContentPane(wrapper);
        pack();
    }

    private String ownerLabel(int ownerIdx) {
        return ownerIdx < 0 ? "None" : "P" + (ownerIdx + 1);
    }

    private int parseOwner(Object cellVal) {
        if (cellVal == null) return -1;
        String s = cellVal.toString();
        if ("None".equals(s)) return -1;
        if (s.length() >= 2 && s.charAt(0) == 'P') {
            try { return Integer.parseInt(s.substring(1)) - 1; }
            catch (NumberFormatException ex) { return -1; }
        }
        return -1;
    }

    private void loadFromModel() {
        javax.swing.JTextField[] balFields = {balanceField1, balanceField2, balanceField3, balanceField4};
        javax.swing.JTextField[] posFields = {positionField1, positionField2, positionField3, positionField4};
        javax.swing.JRadioButton[] activeButtons = {player1ActiveButton, player2ActiveButton, player3ActiveButton, player4ActiveButton};
        javax.swing.JRadioButton[] bankruptButtons = {player1BankruptButton, player2BankruptButton, player3BankruptButton, player4BankruptButton};
        javax.swing.JRadioButton[] turnButtons = {player1Turn, player2Turn, player3Turn, player4Turn};

        for (int i = 0; i < GameModel.NUM_PLAYERS; i++) {
            Player p = model.getPlayer(i);
            balFields[i].setText(String.valueOf(p.getBalance()));
            posFields[i].setText(String.valueOf(p.getPosition()));
            if (p.isActive()) activeButtons[i].setSelected(true);
            else bankruptButtons[i].setSelected(true);
        }
        turnButtons[model.getCurrentTurn()].setSelected(true);
    }

    private void saveToModel() {
        javax.swing.JTextField[] balFields = {balanceField1, balanceField2, balanceField3, balanceField4};
        javax.swing.JTextField[] posFields = {positionField1, positionField2, positionField3, positionField4};
        javax.swing.JRadioButton[] activeButtons = {player1ActiveButton, player2ActiveButton, player3ActiveButton, player4ActiveButton};
        javax.swing.JRadioButton[] turnButtons = {player1Turn, player2Turn, player3Turn, player4Turn};

        int[] balances = new int[GameModel.NUM_PLAYERS];
        int[] positions = new int[GameModel.NUM_PLAYERS];
        boolean[] activeFlags = new boolean[GameModel.NUM_PLAYERS];
        int turn = 0;

        for (int i = 0; i < GameModel.NUM_PLAYERS; i++) {
            Player p = model.getPlayer(i);
            try { balances[i] = Integer.parseInt(balFields[i].getText().trim()); }
            catch (NumberFormatException ex) { balances[i] = p.getBalance(); }
            try { positions[i] = Integer.parseInt(posFields[i].getText().trim()); }
            catch (NumberFormatException ex) { positions[i] = p.getPosition(); }
            activeFlags[i] = activeButtons[i].isSelected();
        }

        for (int i = 0; i < GameModel.NUM_PLAYERS; i++) {
            if (turnButtons[i].isSelected()) { turn = i; break; }
        }

        if (landTable.isEditing()) landTable.getCellEditor().stopCellEditing();
        Land[] lands = model.getLands();
        int[] landOwners = new int[lands.length];
        for (int i = 0; i < lands.length; i++) {
            landOwners[i] = parseOwner(landTable.getModel().getValueAt(i, 2));
        }

        controller.applyEditorChanges(balances, positions, activeFlags, turn, landOwners);
        gameView.refresh();
        this.dispose();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        player1StatusGroup = new javax.swing.ButtonGroup();
        player2StatusGroup = new javax.swing.ButtonGroup();
        player3StatusGroup = new javax.swing.ButtonGroup();
        player4StatusGroup = new javax.swing.ButtonGroup();
        playerTurnGroup = new javax.swing.ButtonGroup();
        titlePanel = new javax.swing.JPanel();
        title = new javax.swing.JLabel();
        player1Panel = new javax.swing.JPanel();
        player1Label = new javax.swing.JLabel();
        balanceLabel1 = new javax.swing.JLabel();
        balanceField1 = new javax.swing.JTextField();
        positionLabel1 = new javax.swing.JLabel();
        positionField1 = new javax.swing.JTextField();
        statusLabel1 = new javax.swing.JLabel();
        player1ActiveButton = new javax.swing.JRadioButton();
        player1BankruptButton = new javax.swing.JRadioButton();
        player2Panel = new javax.swing.JPanel();
        player2Label = new javax.swing.JLabel();
        balanceLabel2 = new javax.swing.JLabel();
        balanceField2 = new javax.swing.JTextField();
        positionLabel2 = new javax.swing.JLabel();
        positionField2 = new javax.swing.JTextField();
        statusLabel2 = new javax.swing.JLabel();
        player2ActiveButton = new javax.swing.JRadioButton();
        player2BankruptButton = new javax.swing.JRadioButton();
        player3Panel = new javax.swing.JPanel();
        player3Label = new javax.swing.JLabel();
        balanceLabel3 = new javax.swing.JLabel();
        balanceField3 = new javax.swing.JTextField();
        positionLabel3 = new javax.swing.JLabel();
        positionField3 = new javax.swing.JTextField();
        statusLabel3 = new javax.swing.JLabel();
        player3ActiveButton = new javax.swing.JRadioButton();
        player3BankruptButton = new javax.swing.JRadioButton();
        player4Panel = new javax.swing.JPanel();
        player4Label = new javax.swing.JLabel();
        balanceLabel4 = new javax.swing.JLabel();
        balanceField4 = new javax.swing.JTextField();
        positionLabel4 = new javax.swing.JLabel();
        positionField4 = new javax.swing.JTextField();
        statusLabel4 = new javax.swing.JLabel();
        player4ActiveButton = new javax.swing.JRadioButton();
        player4BankruptButton = new javax.swing.JRadioButton();
        actionPanel = new javax.swing.JPanel();
        currentTurnLabel = new javax.swing.JLabel();
        player1Turn = new javax.swing.JRadioButton();
        player2Turn = new javax.swing.JRadioButton();
        player3Turn = new javax.swing.JRadioButton();
        player4Turn = new javax.swing.JRadioButton();
        cancelButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        titlePanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        title.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title.setText("Game Editor");

        javax.swing.GroupLayout titlePanelLayout = new javax.swing.GroupLayout(titlePanel);
        titlePanel.setLayout(titlePanelLayout);
        titlePanelLayout.setHorizontalGroup(
            titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, titlePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        titlePanelLayout.setVerticalGroup(
            titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, titlePanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(title)
                .addContainerGap())
        );

        player1Panel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        player1Label.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        player1Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        player1Label.setText("Player 1");

        balanceLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        balanceLabel1.setText("Balance");

        balanceField1.setText("2000");
        balanceField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                balanceField1ActionPerformed(evt);
            }
        });

        positionLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        positionLabel1.setText("Position");

        positionField1.setText("0");

        statusLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        statusLabel1.setText("Status");

        player1StatusGroup.add(player1ActiveButton);
        player1ActiveButton.setText("Active");
        player1ActiveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                player1ActiveButtonActionPerformed(evt);
            }
        });

        player1StatusGroup.add(player1BankruptButton);
        player1BankruptButton.setText("Bankrupt");

        javax.swing.GroupLayout player1PanelLayout = new javax.swing.GroupLayout(player1Panel);
        player1Panel.setLayout(player1PanelLayout);
        player1PanelLayout.setHorizontalGroup(
            player1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(player1Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(player1PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(player1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(player1PanelLayout.createSequentialGroup()
                        .addComponent(statusLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(player1ActiveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(player1BankruptButton, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(player1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(player1PanelLayout.createSequentialGroup()
                            .addComponent(balanceLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(balanceField1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(player1PanelLayout.createSequentialGroup()
                            .addComponent(positionLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(positionField1))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        player1PanelLayout.setVerticalGroup(
            player1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(player1PanelLayout.createSequentialGroup()
                .addComponent(player1Label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(player1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(balanceLabel1)
                    .addComponent(balanceField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(player1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(positionLabel1)
                    .addComponent(positionField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(player1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusLabel1)
                    .addComponent(player1ActiveButton)
                    .addComponent(player1BankruptButton))
                .addGap(0, 11, Short.MAX_VALUE))
        );

        player2Panel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        player2Label.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        player2Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        player2Label.setText("Player 2");

        balanceLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        balanceLabel2.setText("Balance");

        balanceField2.setText("2000");
        balanceField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                balanceField2ActionPerformed(evt);
            }
        });

        positionLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        positionLabel2.setText("Position");

        positionField2.setText("0");

        statusLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        statusLabel2.setText("Status");

        player2StatusGroup.add(player2ActiveButton);
        player2ActiveButton.setText("Active");
        player2ActiveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                player2ActiveButtonActionPerformed(evt);
            }
        });

        player2StatusGroup.add(player2BankruptButton);
        player2BankruptButton.setText("Bankrupt");

        javax.swing.GroupLayout player2PanelLayout = new javax.swing.GroupLayout(player2Panel);
        player2Panel.setLayout(player2PanelLayout);
        player2PanelLayout.setHorizontalGroup(
            player2PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(player2Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(player2PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(player2PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(player2PanelLayout.createSequentialGroup()
                        .addComponent(statusLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(player2ActiveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(player2BankruptButton, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(player2PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(player2PanelLayout.createSequentialGroup()
                            .addComponent(balanceLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(balanceField2, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(player2PanelLayout.createSequentialGroup()
                            .addComponent(positionLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(positionField2))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        player2PanelLayout.setVerticalGroup(
            player2PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(player2PanelLayout.createSequentialGroup()
                .addComponent(player2Label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(player2PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(balanceLabel2)
                    .addComponent(balanceField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(player2PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(positionLabel2)
                    .addComponent(positionField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(player2PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusLabel2)
                    .addComponent(player2ActiveButton)
                    .addComponent(player2BankruptButton))
                .addGap(0, 11, Short.MAX_VALUE))
        );

        player3Panel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        player3Label.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        player3Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        player3Label.setText("Player 3");

        balanceLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        balanceLabel3.setText("Balance");

        balanceField3.setText("2000");
        balanceField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                balanceField3ActionPerformed(evt);
            }
        });

        positionLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        positionLabel3.setText("Position");

        positionField3.setText("0");

        statusLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        statusLabel3.setText("Status");

        player3StatusGroup.add(player3ActiveButton);
        player3ActiveButton.setText("Active");
        player3ActiveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                player3ActiveButtonActionPerformed(evt);
            }
        });

        player3StatusGroup.add(player3BankruptButton);
        player3BankruptButton.setText("Bankrupt");

        javax.swing.GroupLayout player3PanelLayout = new javax.swing.GroupLayout(player3Panel);
        player3Panel.setLayout(player3PanelLayout);
        player3PanelLayout.setHorizontalGroup(
            player3PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(player3Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(player3PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(player3PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(player3PanelLayout.createSequentialGroup()
                        .addComponent(statusLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(player3ActiveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(player3BankruptButton, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(player3PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(player3PanelLayout.createSequentialGroup()
                            .addComponent(balanceLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(balanceField3, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(player3PanelLayout.createSequentialGroup()
                            .addComponent(positionLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(positionField3))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        player3PanelLayout.setVerticalGroup(
            player3PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(player3PanelLayout.createSequentialGroup()
                .addComponent(player3Label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(player3PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(balanceLabel3)
                    .addComponent(balanceField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(player3PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(positionLabel3)
                    .addComponent(positionField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(player3PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusLabel3)
                    .addComponent(player3ActiveButton)
                    .addComponent(player3BankruptButton))
                .addGap(0, 11, Short.MAX_VALUE))
        );

        player4Panel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        player4Label.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        player4Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        player4Label.setText("Player 4");

        balanceLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        balanceLabel4.setText("Balance");

        balanceField4.setText("2000");
        balanceField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                balanceField4ActionPerformed(evt);
            }
        });

        positionLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        positionLabel4.setText("Position");

        positionField4.setText("0");

        statusLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        statusLabel4.setText("Status");

        player4StatusGroup.add(player4ActiveButton);
        player4ActiveButton.setText("Active");
        player4ActiveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                player4ActiveButtonActionPerformed(evt);
            }
        });

        player4StatusGroup.add(player4BankruptButton);
        player4BankruptButton.setText("Bankrupt");

        javax.swing.GroupLayout player4PanelLayout = new javax.swing.GroupLayout(player4Panel);
        player4Panel.setLayout(player4PanelLayout);
        player4PanelLayout.setHorizontalGroup(
            player4PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(player4Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(player4PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(player4PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(player4PanelLayout.createSequentialGroup()
                        .addComponent(statusLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(player4ActiveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(player4BankruptButton, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(player4PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(player4PanelLayout.createSequentialGroup()
                            .addComponent(balanceLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(balanceField4, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(player4PanelLayout.createSequentialGroup()
                            .addComponent(positionLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(positionField4))))
                .addContainerGap(262, Short.MAX_VALUE))
        );
        player4PanelLayout.setVerticalGroup(
            player4PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(player4PanelLayout.createSequentialGroup()
                .addComponent(player4Label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(player4PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(balanceLabel4)
                    .addComponent(balanceField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(player4PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(positionLabel4)
                    .addComponent(positionField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(player4PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusLabel4)
                    .addComponent(player4ActiveButton)
                    .addComponent(player4BankruptButton))
                .addGap(0, 11, Short.MAX_VALUE))
        );

        actionPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        currentTurnLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        currentTurnLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        currentTurnLabel.setText("Current Turn:");

        playerTurnGroup.add(player1Turn);
        player1Turn.setText("Player 1");
        player1Turn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                player1TurnActionPerformed(evt);
            }
        });

        playerTurnGroup.add(player2Turn);
        player2Turn.setText("Player 2");
        player2Turn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                player2TurnActionPerformed(evt);
            }
        });

        playerTurnGroup.add(player3Turn);
        player3Turn.setText("Player 3");
        player3Turn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                player3TurnActionPerformed(evt);
            }
        });

        playerTurnGroup.add(player4Turn);
        player4Turn.setText("Player 4");
        player4Turn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                player4TurnActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        saveButton.setText("Save & Close");

        javax.swing.GroupLayout actionPanelLayout = new javax.swing.GroupLayout(actionPanel);
        actionPanel.setLayout(actionPanelLayout);
        actionPanelLayout.setHorizontalGroup(
            actionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, actionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(actionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(saveButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(currentTurnLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                    .addComponent(player1Turn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(player2Turn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(player3Turn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(player4Turn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cancelButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        actionPanelLayout.setVerticalGroup(
            actionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(actionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(currentTurnLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(player1Turn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(player2Turn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(player3Turn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(player4Turn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(titlePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(player4Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(player3Panel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(player2Panel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(player1Panel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(actionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(titlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(player1Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(player2Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(player3Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(player4Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(actionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void balanceField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_balanceField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_balanceField1ActionPerformed

    private void player1ActiveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_player1ActiveButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_player1ActiveButtonActionPerformed

    private void balanceField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_balanceField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_balanceField2ActionPerformed

    private void player2ActiveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_player2ActiveButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_player2ActiveButtonActionPerformed

    private void balanceField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_balanceField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_balanceField3ActionPerformed

    private void player3ActiveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_player3ActiveButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_player3ActiveButtonActionPerformed

    private void balanceField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_balanceField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_balanceField4ActionPerformed

    private void player4ActiveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_player4ActiveButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_player4ActiveButtonActionPerformed

    private void player1TurnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_player1TurnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_player1TurnActionPerformed

    private void player2TurnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_player2TurnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_player2TurnActionPerformed

    private void player3TurnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_player3TurnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_player3TurnActionPerformed

    private void player4TurnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_player4TurnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_player4TurnActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GameEditorGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GameEditorGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GameEditorGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GameEditorGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GameEditorGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel actionPanel;
    private javax.swing.JTextField balanceField1;
    private javax.swing.JTextField balanceField2;
    private javax.swing.JTextField balanceField3;
    private javax.swing.JTextField balanceField4;
    private javax.swing.JLabel balanceLabel1;
    private javax.swing.JLabel balanceLabel2;
    private javax.swing.JLabel balanceLabel3;
    private javax.swing.JLabel balanceLabel4;
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel currentTurnLabel;
    private javax.swing.JRadioButton player1ActiveButton;
    private javax.swing.JRadioButton player1BankruptButton;
    private javax.swing.JLabel player1Label;
    private javax.swing.JPanel player1Panel;
    private javax.swing.ButtonGroup player1StatusGroup;
    private javax.swing.JRadioButton player1Turn;
    private javax.swing.JRadioButton player2ActiveButton;
    private javax.swing.JRadioButton player2BankruptButton;
    private javax.swing.JLabel player2Label;
    private javax.swing.JPanel player2Panel;
    private javax.swing.ButtonGroup player2StatusGroup;
    private javax.swing.JRadioButton player2Turn;
    private javax.swing.JRadioButton player3ActiveButton;
    private javax.swing.JRadioButton player3BankruptButton;
    private javax.swing.JLabel player3Label;
    private javax.swing.JPanel player3Panel;
    private javax.swing.ButtonGroup player3StatusGroup;
    private javax.swing.JRadioButton player3Turn;
    private javax.swing.JRadioButton player4ActiveButton;
    private javax.swing.JRadioButton player4BankruptButton;
    private javax.swing.JLabel player4Label;
    private javax.swing.JPanel player4Panel;
    private javax.swing.ButtonGroup player4StatusGroup;
    private javax.swing.JRadioButton player4Turn;
    private javax.swing.ButtonGroup playerTurnGroup;
    private javax.swing.JTextField positionField1;
    private javax.swing.JTextField positionField2;
    private javax.swing.JTextField positionField3;
    private javax.swing.JTextField positionField4;
    private javax.swing.JLabel positionLabel1;
    private javax.swing.JLabel positionLabel2;
    private javax.swing.JLabel positionLabel3;
    private javax.swing.JLabel positionLabel4;
    private javax.swing.JButton saveButton;
    private javax.swing.JLabel statusLabel1;
    private javax.swing.JLabel statusLabel2;
    private javax.swing.JLabel statusLabel3;
    private javax.swing.JLabel statusLabel4;
    private javax.swing.JLabel title;
    private javax.swing.JPanel titlePanel;
    // End of variables declaration//GEN-END:variables
}
