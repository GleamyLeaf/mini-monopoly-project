/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package mini_monopoly;

/**
 * Main window -- board + player panels + dice/buy/end buttons
 * also makes the model/controller/view and hooks up the editor shortcuts
 */
public class MiniMonopolyGUI extends javax.swing.JFrame {

    private GameView view;

    /**
     * Creates new form MiniMonopolyGUI
     */
    public MiniMonopolyGUI() {
        initComponents();

        GameModel model = new GameModel();
        GameController controller = new GameController(model);

        view = new GameView(model, controller, this,
            new javax.swing.JLabel[] {
                landName1, landName2, landName3,
                landName7, landName8, landName9, landName10, landName11,
                landName12, landName13, landName14,
                landName19, landName20, landName21, landName22, landName23,
                landName26, landName35,
                landName38, landName39, landName40, landName41 },
            new javax.swing.JLabel[] {
                landPrice1, landPrice2, landPrice3,
                landPrice7, landPrice8, landPrice9, landPrice10, landPrice11,
                landPrice12, landPrice13, landPrice14,
                landPrice19, landPrice20, landPrice21, landPrice22, landPrice23,
                landPrice26, landPrice35,
                landPrice38, landPrice39, landPrice40, landPrice41 },
            new javax.swing.JPanel[] {
                ownerColor1, ownerColor2, ownerColor3,
                ownerColor7, ownerColor8, ownerColor9, ownerColor10, ownerColor11,
                ownerColor12, ownerColor13, ownerColor14,
                ownerColor19, ownerColor20, ownerColor21, ownerColor22, ownerColor23,
                ownerColor26, ownerColor35,
                ownerColor38, ownerColor39, ownerColor40, ownerColor41 },
            new javax.swing.JPanel[] {
                slot0, slot1, slot2, slot3, slot4, slot5, slot6, slot7,
                slot8, slot9, slot10, slot11, slot12, slot13, slot14, slot15,
                slot16, slot17, slot18, slot19, slot20, slot21, slot22, slot23,
                slot24, slot25, slot26, slot27, slot28, slot29, slot30, slot31,
                slot32, slot33, slot34, slot35, slot36, slot37, slot38, slot39,
                slot40, slot41, slot42, slot43 },
            new javax.swing.JLabel[] {
                slotNum0, slotNum1, slotNum2, slotNum3, slotNum4, slotNum5,
                slotNum6, slotNum7, slotNum8, slotNum9, slotNum10, slotNum11,
                slotNum12, slotNum13, slotNum14, slotNum15, slotNum16, slotNum17,
                slotNum18, slotNum19, slotNum20, slotNum21, slotNum22, slotNum23,
                slotNum24, slotNum25, slotNum26, slotNum27, slotNum28, slotNum29,
                slotNum30, slotNum31, slotNum32, slotNum33, slotNum34, slotNum35,
                slotNum36, slotNum37, slotNum38, slotNum39, slotNum40, slotNum41,
                slotNum42, slotNum43 },
            new javax.swing.JLabel[] { balance1, balance2, balance3, balance4 },
            new javax.swing.JLabel[] { position1, position2, position3, position4 },
            new javax.swing.JLabel[] { status1, status2, status3, status4 },
            playerTurn, diceNum,
            diceButton, buyLandButton, endTurnButton
        );

        installEditorShortcuts();
    }

    private static final int CORNER_HIT_SIZE = 100;

    private void installEditorShortcuts() {
        // ok ctrl+E opens the editor
        javax.swing.KeyStroke ctrlE = javax.swing.KeyStroke.getKeyStroke(
            java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_DOWN_MASK);
        getRootPane().getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW)
            .put(ctrlE, "openEditor");
        getRootPane().getActionMap().put("openEditor", new javax.swing.AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                view.onOpenEditor();
            }
        });

        // triple click bottom-right corner = open editor (kinda janky but it works)
        java.awt.Toolkit.getDefaultToolkit().addAWTEventListener(event -> {
            if (!(event instanceof java.awt.event.MouseEvent)) return;
            java.awt.event.MouseEvent me = (java.awt.event.MouseEvent) event;
            if (me.getID() != java.awt.event.MouseEvent.MOUSE_CLICKED) return;
            if (me.getClickCount() != 3) return;
            if (!javax.swing.SwingUtilities.isDescendingFrom(me.getComponent(), this)) return;
            java.awt.Container pane = getContentPane();
            java.awt.Point p = javax.swing.SwingUtilities.convertPoint(
                me.getComponent(), me.getPoint(), pane);
            if (p.x >= pane.getWidth() - CORNER_HIT_SIZE
                    && p.y >= pane.getHeight() - CORNER_HIT_SIZE) {
                view.onOpenEditor();
            }
        }, java.awt.AWTEvent.MOUSE_EVENT_MASK);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        player1Panel = new javax.swing.JPanel();
        player1Label = new javax.swing.JLabel();
        balanceLabel1 = new javax.swing.JLabel();
        positionLabel1 = new javax.swing.JLabel();
        statusLabel1 = new javax.swing.JLabel();
        colorPanel1 = new javax.swing.JPanel();
        balance1 = new javax.swing.JLabel();
        position1 = new javax.swing.JLabel();
        status1 = new javax.swing.JLabel();
        player2Panel = new javax.swing.JPanel();
        player2Label = new javax.swing.JLabel();
        balanceLabel2 = new javax.swing.JLabel();
        positionLabel2 = new javax.swing.JLabel();
        statusLabel2 = new javax.swing.JLabel();
        colorPanel2 = new javax.swing.JPanel();
        balance2 = new javax.swing.JLabel();
        position2 = new javax.swing.JLabel();
        status2 = new javax.swing.JLabel();
        player3Panel = new javax.swing.JPanel();
        player3Label = new javax.swing.JLabel();
        balanceLabel3 = new javax.swing.JLabel();
        positionLabel3 = new javax.swing.JLabel();
        statusLabel3 = new javax.swing.JLabel();
        colorPanel3 = new javax.swing.JPanel();
        status3 = new javax.swing.JLabel();
        balance3 = new javax.swing.JLabel();
        position3 = new javax.swing.JLabel();
        player4Panel = new javax.swing.JPanel();
        player4Label = new javax.swing.JLabel();
        balanceLabel4 = new javax.swing.JLabel();
        positionLabel4 = new javax.swing.JLabel();
        statusLabel4 = new javax.swing.JLabel();
        colorPanel4 = new javax.swing.JPanel();
        balance4 = new javax.swing.JLabel();
        status4 = new javax.swing.JLabel();
        position4 = new javax.swing.JLabel();
        gameBoardPanel = new javax.swing.JPanel();
        slot0 = new javax.swing.JPanel();
        slotNum0 = new javax.swing.JLabel();
        slot1 = new javax.swing.JPanel();
        slotNum1 = new javax.swing.JLabel();
        landName1 = new javax.swing.JLabel();
        landPrice1 = new javax.swing.JLabel();
        ownerColor1 = new javax.swing.JPanel();
        slot2 = new javax.swing.JPanel();
        slotNum2 = new javax.swing.JLabel();
        landName2 = new javax.swing.JLabel();
        landPrice2 = new javax.swing.JLabel();
        ownerColor2 = new javax.swing.JPanel();
        slot3 = new javax.swing.JPanel();
        slotNum3 = new javax.swing.JLabel();
        landName3 = new javax.swing.JLabel();
        landPrice3 = new javax.swing.JLabel();
        ownerColor3 = new javax.swing.JPanel();
        slot4 = new javax.swing.JPanel();
        slotNum4 = new javax.swing.JLabel();
        slot5 = new javax.swing.JPanel();
        slotNum5 = new javax.swing.JLabel();
        slot6 = new javax.swing.JPanel();
        slotNum6 = new javax.swing.JLabel();
        slot7 = new javax.swing.JPanel();
        slotNum7 = new javax.swing.JLabel();
        landName7 = new javax.swing.JLabel();
        landPrice7 = new javax.swing.JLabel();
        ownerColor7 = new javax.swing.JPanel();
        slot8 = new javax.swing.JPanel();
        slotNum8 = new javax.swing.JLabel();
        landName8 = new javax.swing.JLabel();
        landPrice8 = new javax.swing.JLabel();
        ownerColor8 = new javax.swing.JPanel();
        slot9 = new javax.swing.JPanel();
        slotNum9 = new javax.swing.JLabel();
        landName9 = new javax.swing.JLabel();
        landPrice9 = new javax.swing.JLabel();
        ownerColor9 = new javax.swing.JPanel();
        slot10 = new javax.swing.JPanel();
        slotNum10 = new javax.swing.JLabel();
        landName10 = new javax.swing.JLabel();
        landPrice10 = new javax.swing.JLabel();
        ownerColor10 = new javax.swing.JPanel();
        slot11 = new javax.swing.JPanel();
        slotNum11 = new javax.swing.JLabel();
        landName11 = new javax.swing.JLabel();
        landPrice11 = new javax.swing.JLabel();
        ownerColor11 = new javax.swing.JPanel();
        slot12 = new javax.swing.JPanel();
        slotNum12 = new javax.swing.JLabel();
        landName12 = new javax.swing.JLabel();
        landPrice12 = new javax.swing.JLabel();
        ownerColor12 = new javax.swing.JPanel();
        slot13 = new javax.swing.JPanel();
        slotNum13 = new javax.swing.JLabel();
        landName13 = new javax.swing.JLabel();
        landPrice13 = new javax.swing.JLabel();
        ownerColor13 = new javax.swing.JPanel();
        slot14 = new javax.swing.JPanel();
        slotNum14 = new javax.swing.JLabel();
        landName14 = new javax.swing.JLabel();
        landPrice14 = new javax.swing.JLabel();
        ownerColor14 = new javax.swing.JPanel();
        slot15 = new javax.swing.JPanel();
        slotNum15 = new javax.swing.JLabel();
        slot16 = new javax.swing.JPanel();
        slotNum16 = new javax.swing.JLabel();
        slot17 = new javax.swing.JPanel();
        slotNum17 = new javax.swing.JLabel();
        slot18 = new javax.swing.JPanel();
        slotNum18 = new javax.swing.JLabel();
        slot19 = new javax.swing.JPanel();
        slotNum19 = new javax.swing.JLabel();
        landName19 = new javax.swing.JLabel();
        landPrice19 = new javax.swing.JLabel();
        ownerColor19 = new javax.swing.JPanel();
        slot20 = new javax.swing.JPanel();
        slotNum20 = new javax.swing.JLabel();
        landName20 = new javax.swing.JLabel();
        landPrice20 = new javax.swing.JLabel();
        ownerColor20 = new javax.swing.JPanel();
        slot21 = new javax.swing.JPanel();
        slotNum21 = new javax.swing.JLabel();
        landName21 = new javax.swing.JLabel();
        landPrice21 = new javax.swing.JLabel();
        ownerColor21 = new javax.swing.JPanel();
        slot22 = new javax.swing.JPanel();
        slotNum22 = new javax.swing.JLabel();
        landName22 = new javax.swing.JLabel();
        landPrice22 = new javax.swing.JLabel();
        ownerColor22 = new javax.swing.JPanel();
        slot23 = new javax.swing.JPanel();
        slotNum23 = new javax.swing.JLabel();
        landName23 = new javax.swing.JLabel();
        landPrice23 = new javax.swing.JLabel();
        ownerColor23 = new javax.swing.JPanel();
        slot24 = new javax.swing.JPanel();
        slotNum24 = new javax.swing.JLabel();
        slot25 = new javax.swing.JPanel();
        slotNum25 = new javax.swing.JLabel();
        slot26 = new javax.swing.JPanel();
        slotNum26 = new javax.swing.JLabel();
        landName26 = new javax.swing.JLabel();
        landPrice26 = new javax.swing.JLabel();
        ownerColor26 = new javax.swing.JPanel();
        slot27 = new javax.swing.JPanel();
        slotNum27 = new javax.swing.JLabel();
        slot28 = new javax.swing.JPanel();
        slotNum28 = new javax.swing.JLabel();
        slot29 = new javax.swing.JPanel();
        slotNum29 = new javax.swing.JLabel();
        slot30 = new javax.swing.JPanel();
        slotNum30 = new javax.swing.JLabel();
        slot31 = new javax.swing.JPanel();
        slotNum31 = new javax.swing.JLabel();
        slot32 = new javax.swing.JPanel();
        slotNum32 = new javax.swing.JLabel();
        slot33 = new javax.swing.JPanel();
        slotNum33 = new javax.swing.JLabel();
        slot34 = new javax.swing.JPanel();
        slotNum34 = new javax.swing.JLabel();
        slot35 = new javax.swing.JPanel();
        slotNum35 = new javax.swing.JLabel();
        landName35 = new javax.swing.JLabel();
        landPrice35 = new javax.swing.JLabel();
        ownerColor35 = new javax.swing.JPanel();
        slot36 = new javax.swing.JPanel();
        slotNum36 = new javax.swing.JLabel();
        slot37 = new javax.swing.JPanel();
        slotNum37 = new javax.swing.JLabel();
        slot38 = new javax.swing.JPanel();
        slotNum38 = new javax.swing.JLabel();
        landName38 = new javax.swing.JLabel();
        landPrice38 = new javax.swing.JLabel();
        ownerColor38 = new javax.swing.JPanel();
        slot39 = new javax.swing.JPanel();
        slotNum39 = new javax.swing.JLabel();
        landName39 = new javax.swing.JLabel();
        landPrice39 = new javax.swing.JLabel();
        ownerColor39 = new javax.swing.JPanel();
        slot40 = new javax.swing.JPanel();
        slotNum40 = new javax.swing.JLabel();
        landName40 = new javax.swing.JLabel();
        landPrice40 = new javax.swing.JLabel();
        ownerColor40 = new javax.swing.JPanel();
        slot41 = new javax.swing.JPanel();
        slotNum41 = new javax.swing.JLabel();
        landName41 = new javax.swing.JLabel();
        landPrice41 = new javax.swing.JLabel();
        ownerColor41 = new javax.swing.JPanel();
        slot42 = new javax.swing.JPanel();
        slotNum42 = new javax.swing.JLabel();
        slot43 = new javax.swing.JPanel();
        slotNum43 = new javax.swing.JLabel();
        actionPanel = new javax.swing.JPanel();
        diceButton = new javax.swing.JButton();
        diceNum = new javax.swing.JLabel();
        buyLandButton = new javax.swing.JButton();
        endTurnButton = new javax.swing.JButton();
        currentTurnLabel = new javax.swing.JLabel();
        playerTurn = new javax.swing.JLabel();

        jScrollPane1.setViewportView(jEditorPane1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        player1Panel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        player1Label.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        player1Label.setText("Player 1");

        balanceLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        balanceLabel1.setText("Balance:");

        positionLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        positionLabel1.setText("Position:");

        statusLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        statusLabel1.setText("Status:");

        colorPanel1.setBackground(new java.awt.Color(255, 102, 102));
        colorPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout colorPanel1Layout = new javax.swing.GroupLayout(colorPanel1);
        colorPanel1.setLayout(colorPanel1Layout);
        colorPanel1Layout.setHorizontalGroup(
            colorPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 34, Short.MAX_VALUE)
        );
        colorPanel1Layout.setVerticalGroup(
            colorPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 29, Short.MAX_VALUE)
        );

        balance1.setText("$2000");

        position1.setText("0");

        status1.setText("Active");

        javax.swing.GroupLayout player1PanelLayout = new javax.swing.GroupLayout(player1Panel);
        player1Panel.setLayout(player1PanelLayout);
        player1PanelLayout.setHorizontalGroup(
            player1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(player1PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(player1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(player1PanelLayout.createSequentialGroup()
                        .addGroup(player1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(player1Label, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(player1PanelLayout.createSequentialGroup()
                                .addComponent(balanceLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(balance1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                        .addComponent(colorPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(player1PanelLayout.createSequentialGroup()
                        .addGroup(player1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(player1PanelLayout.createSequentialGroup()
                                .addComponent(statusLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(status1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(player1PanelLayout.createSequentialGroup()
                                .addComponent(positionLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(position1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        player1PanelLayout.setVerticalGroup(
            player1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(player1PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(player1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(colorPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(player1PanelLayout.createSequentialGroup()
                        .addComponent(player1Label)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(player1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(balanceLabel1)
                            .addComponent(balance1))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(player1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(positionLabel1)
                    .addComponent(position1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(player1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusLabel1)
                    .addComponent(status1))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        player2Panel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        player2Label.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        player2Label.setText("Player 2");

        balanceLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        balanceLabel2.setText("Balance:");

        positionLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        positionLabel2.setText("Position:");

        statusLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        statusLabel2.setText("Status:");

        colorPanel2.setBackground(new java.awt.Color(153, 255, 153));
        colorPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout colorPanel2Layout = new javax.swing.GroupLayout(colorPanel2);
        colorPanel2.setLayout(colorPanel2Layout);
        colorPanel2Layout.setHorizontalGroup(
            colorPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 34, Short.MAX_VALUE)
        );
        colorPanel2Layout.setVerticalGroup(
            colorPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 29, Short.MAX_VALUE)
        );

        balance2.setText("$2000");

        position2.setText("0");

        status2.setText("Active");

        javax.swing.GroupLayout player2PanelLayout = new javax.swing.GroupLayout(player2Panel);
        player2Panel.setLayout(player2PanelLayout);
        player2PanelLayout.setHorizontalGroup(
            player2PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(player2PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(player2PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(player2PanelLayout.createSequentialGroup()
                        .addComponent(player2Label, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 91, Short.MAX_VALUE))
                    .addGroup(player2PanelLayout.createSequentialGroup()
                        .addGroup(player2PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(balanceLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(positionLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(player2PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(balance2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(player2PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(status2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(position2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addComponent(colorPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        player2PanelLayout.setVerticalGroup(
            player2PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(player2PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(player2PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(colorPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(player2PanelLayout.createSequentialGroup()
                        .addComponent(player2Label)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(player2PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(balanceLabel2)
                            .addGroup(player2PanelLayout.createSequentialGroup()
                                .addComponent(balance2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(player2PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(position2)
                                    .addComponent(positionLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(player2PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(status2)
                                    .addComponent(statusLabel2))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        player3Panel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        player3Label.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        player3Label.setText("Player 3");

        balanceLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        balanceLabel3.setText("Balance:");

        positionLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        positionLabel3.setText("Position:");

        statusLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        statusLabel3.setText("Status:");

        colorPanel3.setBackground(new java.awt.Color(102, 153, 255));
        colorPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout colorPanel3Layout = new javax.swing.GroupLayout(colorPanel3);
        colorPanel3.setLayout(colorPanel3Layout);
        colorPanel3Layout.setHorizontalGroup(
            colorPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 34, Short.MAX_VALUE)
        );
        colorPanel3Layout.setVerticalGroup(
            colorPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 29, Short.MAX_VALUE)
        );

        status3.setText("Active");

        balance3.setText("$2000");

        position3.setText("0");

        javax.swing.GroupLayout player3PanelLayout = new javax.swing.GroupLayout(player3Panel);
        player3Panel.setLayout(player3PanelLayout);
        player3PanelLayout.setHorizontalGroup(
            player3PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(player3PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(player3PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(player3PanelLayout.createSequentialGroup()
                        .addComponent(player3Label, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE))
                    .addGroup(player3PanelLayout.createSequentialGroup()
                        .addGroup(player3PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(balanceLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(positionLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(player3PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(balance3, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(player3PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(status3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(position3, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addComponent(colorPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        player3PanelLayout.setVerticalGroup(
            player3PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(player3PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(player3PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(colorPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(player3PanelLayout.createSequentialGroup()
                        .addComponent(player3Label)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(player3PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(player3PanelLayout.createSequentialGroup()
                                .addComponent(balanceLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(positionLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusLabel3))
                            .addGroup(player3PanelLayout.createSequentialGroup()
                                .addComponent(balance3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(position3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(status3)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        player4Panel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        player4Label.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        player4Label.setText("Player 4");

        balanceLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        balanceLabel4.setText("Balance:");

        positionLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        positionLabel4.setText("Position:");

        statusLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        statusLabel4.setText("Status:");

        colorPanel4.setBackground(new java.awt.Color(255, 255, 153));
        colorPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout colorPanel4Layout = new javax.swing.GroupLayout(colorPanel4);
        colorPanel4.setLayout(colorPanel4Layout);
        colorPanel4Layout.setHorizontalGroup(
            colorPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 34, Short.MAX_VALUE)
        );
        colorPanel4Layout.setVerticalGroup(
            colorPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 29, Short.MAX_VALUE)
        );

        balance4.setText("$2000");

        status4.setText("Active");

        position4.setText("0");

        javax.swing.GroupLayout player4PanelLayout = new javax.swing.GroupLayout(player4Panel);
        player4Panel.setLayout(player4PanelLayout);
        player4PanelLayout.setHorizontalGroup(
            player4PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(player4PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(player4PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(player4PanelLayout.createSequentialGroup()
                        .addComponent(player4Label, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE))
                    .addGroup(player4PanelLayout.createSequentialGroup()
                        .addGroup(player4PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(balanceLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(positionLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(player4PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(balance4, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(player4PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(status4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(position4, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addComponent(colorPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        player4PanelLayout.setVerticalGroup(
            player4PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(player4PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(player4PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(colorPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(player4PanelLayout.createSequentialGroup()
                        .addComponent(player4Label)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(player4PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(player4PanelLayout.createSequentialGroup()
                                .addComponent(balanceLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(positionLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusLabel4))
                            .addGroup(player4PanelLayout.createSequentialGroup()
                                .addComponent(balance4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(position4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(status4)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        gameBoardPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        gameBoardPanel.setLayout(null);

        slot0.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        slotNum0.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        slotNum0.setText("0");

        javax.swing.GroupLayout slot0Layout = new javax.swing.GroupLayout(slot0);
        slot0.setLayout(slot0Layout);
        slot0Layout.setHorizontalGroup(
            slot0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(slotNum0, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
        );
        slot0Layout.setVerticalGroup(
            slot0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(slot0Layout.createSequentialGroup()
                .addComponent(slotNum0)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        gameBoardPanel.add(slot0);
        slot0.setBounds(8, 2, 75, 117);

        slot1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        slotNum1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        slotNum1.setText("1");

        landName1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        landName1.setText("Land 1");

        landPrice1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        landPrice1.setText("$3400");

        ownerColor1.setBackground(new java.awt.Color(153, 153, 153));

        javax.swing.GroupLayout ownerColor1Layout = new javax.swing.GroupLayout(ownerColor1);
        ownerColor1.setLayout(ownerColor1Layout);
        ownerColor1Layout.setHorizontalGroup(
            ownerColor1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        ownerColor1Layout.setVerticalGroup(
            ownerColor1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout slot1Layout = new javax.swing.GroupLayout(slot1);
        slot1.setLayout(slot1Layout);
        slot1Layout.setHorizontalGroup(
            slot1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(slotNum1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(landName1, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
            .addComponent(landPrice1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(ownerColor1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        slot1Layout.setVerticalGroup(
            slot1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(slot1Layout.createSequentialGroup()
                .addComponent(slotNum1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(landName1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(landPrice1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ownerColor1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        gameBoardPanel.add(slot1);
        slot1.setBounds(95, 2, 75, 117);

        slot2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        slotNum2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        slotNum2.setText("2");

        landName2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        landName2.setText("Land 2");

        landPrice2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        landPrice2.setText("$1800");

        ownerColor2.setBackground(new java.awt.Color(153, 153, 153));

        javax.swing.GroupLayout ownerColor2Layout = new javax.swing.GroupLayout(ownerColor2);
        ownerColor2.setLayout(ownerColor2Layout);
        ownerColor2Layout.setHorizontalGroup(
            ownerColor2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        ownerColor2Layout.setVerticalGroup(
            ownerColor2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout slot2Layout = new javax.swing.GroupLayout(slot2);
        slot2.setLayout(slot2Layout);
        slot2Layout.setHorizontalGroup(
            slot2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(slotNum2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(landName2, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
            .addComponent(landPrice2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(ownerColor2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        slot2Layout.setVerticalGroup(
            slot2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(slot2Layout.createSequentialGroup()
                .addComponent(slotNum2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(landName2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(landPrice2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ownerColor2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        gameBoardPanel.add(slot2);
        slot2.setBounds(176, 2, 75, 117);

        slot3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        slotNum3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        slotNum3.setText("3");

        landName3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        landName3.setText("Land 3");

        landPrice3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        landPrice3.setText("$1800");

        ownerColor3.setBackground(new java.awt.Color(153, 153, 153));

        javax.swing.GroupLayout ownerColor3Layout = new javax.swing.GroupLayout(ownerColor3);
        ownerColor3.setLayout(ownerColor3Layout);
        ownerColor3Layout.setHorizontalGroup(
            ownerColor3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        ownerColor3Layout.setVerticalGroup(
            ownerColor3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout slot3Layout = new javax.swing.GroupLayout(slot3);
        slot3.setLayout(slot3Layout);
        slot3Layout.setHorizontalGroup(
            slot3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(slotNum3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(landName3, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
            .addComponent(landPrice3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(ownerColor3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        slot3Layout.setVerticalGroup(
            slot3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(slot3Layout.createSequentialGroup()
                .addComponent(slotNum3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(landName3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(landPrice3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ownerColor3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        gameBoardPanel.add(slot3);
        slot3.setBounds(257, 2, 75, 117);

        slot4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        slotNum4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        slotNum4.setText("4");

        javax.swing.GroupLayout slot4Layout = new javax.swing.GroupLayout(slot4);
        slot4.setLayout(slot4Layout);
        slot4Layout.setHorizontalGroup(
            slot4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(slotNum4, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
        );
        slot4Layout.setVerticalGroup(
            slot4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(slot4Layout.createSequentialGroup()
                .addComponent(slotNum4)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        gameBoardPanel.add(slot4);
        slot4.setBounds(338, 2, 75, 117);

        slot5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        slotNum5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        slotNum5.setText("5");

        javax.swing.GroupLayout slot5Layout = new javax.swing.GroupLayout(slot5);
        slot5.setLayout(slot5Layout);
        slot5Layout.setHorizontalGroup(
            slot5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(slotNum5, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
        );
        slot5Layout.setVerticalGroup(
            slot5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(slot5Layout.createSequentialGroup()
                .addComponent(slotNum5)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        gameBoardPanel.add(slot5);
        slot5.setBounds(419, 2, 75, 117);

        slot6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        slotNum6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        slotNum6.setText("6");

        javax.swing.GroupLayout slot6Layout = new javax.swing.GroupLayout(slot6);
        slot6.setLayout(slot6Layout);
        slot6Layout.setHorizontalGroup(
            slot6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(slotNum6, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
        );
        slot6Layout.setVerticalGroup(
            slot6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(slot6Layout.createSequentialGroup()
                .addComponent(slotNum6)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        gameBoardPanel.add(slot6);
        slot6.setBounds(500, 2, 75, 117);

        slot7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        slotNum7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        slotNum7.setText("7");

        landName7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        landName7.setText("Land 3");

        landPrice7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        landPrice7.setText("$1800");

        ownerColor7.setBackground(new java.awt.Color(153, 153, 153));

        javax.swing.GroupLayout ownerColor7Layout = new javax.swing.GroupLayout(ownerColor7);
        ownerColor7.setLayout(ownerColor7Layout);
        ownerColor7Layout.setHorizontalGroup(
            ownerColor7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        ownerColor7Layout.setVerticalGroup(
            ownerColor7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 49, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout slot7Layout = new javax.swing.GroupLayout(slot7);
        slot7.setLayout(slot7Layout);
        slot7Layout.setHorizontalGroup(
            slot7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(slotNum7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(landName7, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
            .addComponent(landPrice7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(ownerColor7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        slot7Layout.setVerticalGroup(
            slot7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(slot7Layout.createSequentialGroup()
                .addComponent(slotNum7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(landName7, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(landPrice7, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ownerColor7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        gameBoardPanel.add(slot7);
        slot7.setBounds(581, 2, 80, 117);

        slot8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        slotNum8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        slotNum8.setText("8");

        landName8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        landName8.setText("Land 3");

        landPrice8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        landPrice8.setText("$1800");

        ownerColor8.setBackground(new java.awt.Color(153, 153, 153));

        javax.swing.GroupLayout ownerColor8Layout = new javax.swing.GroupLayout(ownerColor8);
        ownerColor8.setLayout(ownerColor8Layout);
        ownerColor8Layout.setHorizontalGroup(
            ownerColor8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        ownerColor8Layout.setVerticalGroup(
            ownerColor8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout slot8Layout = new javax.swing.GroupLayout(slot8);
        slot8.setLayout(slot8Layout);
        slot8Layout.setHorizontalGroup(
            slot8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(slotNum8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(landName8, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
            .addComponent(landPrice8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(ownerColor8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        slot8Layout.setVerticalGroup(
            slot8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(slot8Layout.createSequentialGroup()
                .addComponent(slotNum8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(landName8, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(landPrice8, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ownerColor8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        gameBoardPanel.add(slot8);
        slot8.setBounds(667, 2, 81, 117);

        slot9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        slotNum9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        slotNum9.setText("9");

        landName9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        landName9.setText("Land 3");

        landPrice9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        landPrice9.setText("$1800");

        ownerColor9.setBackground(new java.awt.Color(153, 153, 153));

        javax.swing.GroupLayout ownerColor9Layout = new javax.swing.GroupLayout(ownerColor9);
        ownerColor9.setLayout(ownerColor9Layout);
        ownerColor9Layout.setHorizontalGroup(
            ownerColor9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        ownerColor9Layout.setVerticalGroup(
            ownerColor9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout slot9Layout = new javax.swing.GroupLayout(slot9);
        slot9.setLayout(slot9Layout);
        slot9Layout.setHorizontalGroup(
            slot9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(slotNum9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(landName9, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
            .addComponent(landPrice9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(ownerColor9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        slot9Layout.setVerticalGroup(
            slot9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(slot9Layout.createSequentialGroup()
                .addComponent(slotNum9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(landName9, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(landPrice9, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ownerColor9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        gameBoardPanel.add(slot9);
        slot9.setBounds(754, 2, 80, 117);

        slot10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        slotNum10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        slotNum10.setText("10");

        landName10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        landName10.setText("Land 3");

        landPrice10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        landPrice10.setText("$1800");

        ownerColor10.setBackground(new java.awt.Color(153, 153, 153));

        javax.swing.GroupLayout ownerColor10Layout = new javax.swing.GroupLayout(ownerColor10);
        ownerColor10.setLayout(ownerColor10Layout);
        ownerColor10Layout.setHorizontalGroup(
            ownerColor10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        ownerColor10Layout.setVerticalGroup(
            ownerColor10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 49, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout slot10Layout = new javax.swing.GroupLayout(slot10);
        slot10.setLayout(slot10Layout);
        slot10Layout.setHorizontalGroup(
            slot10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(slotNum10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(landName10, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
            .addComponent(landPrice10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(ownerColor10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        slot10Layout.setVerticalGroup(
            slot10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(slot10Layout.createSequentialGroup()
                .addComponent(slotNum10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(landName10, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(landPrice10, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ownerColor10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        gameBoardPanel.add(slot10);
        slot10.setBounds(10, 130, 70, 117);

        slot11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        slotNum11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        slotNum11.setText("11");

        landName11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        landName11.setText("Land 3");

        landPrice11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        landPrice11.setText("$1800");

        ownerColor11.setBackground(new java.awt.Color(153, 153, 153));

        javax.swing.GroupLayout ownerColor11Layout = new javax.swing.GroupLayout(ownerColor11);
        ownerColor11.setLayout(ownerColor11Layout);
        ownerColor11Layout.setHorizontalGroup(
            ownerColor11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        ownerColor11Layout.setVerticalGroup(
            ownerColor11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 49, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout slot11Layout = new javax.swing.GroupLayout(slot11);
        slot11.setLayout(slot11Layout);
        slot11Layout.setHorizontalGroup(
            slot11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(slotNum11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(landName11, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
            .addComponent(landPrice11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(ownerColor11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        slot11Layout.setVerticalGroup(
            slot11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(slot11Layout.createSequentialGroup()
                .addComponent(slotNum11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(landName11, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(landPrice11, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ownerColor11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        gameBoardPanel.add(slot11);
        slot11.setBounds(90, 130, 70, 117);

        slot12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        slotNum12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        slotNum12.setText("12");

        landName12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        landName12.setText("Land 3");

        landPrice12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        landPrice12.setText("$1800");

        ownerColor12.setBackground(new java.awt.Color(153, 153, 153));

        javax.swing.GroupLayout ownerColor12Layout = new javax.swing.GroupLayout(ownerColor12);
        ownerColor12.setLayout(ownerColor12Layout);
        ownerColor12Layout.setHorizontalGroup(
            ownerColor12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        ownerColor12Layout.setVerticalGroup(
            ownerColor12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 49, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout slot12Layout = new javax.swing.GroupLayout(slot12);
        slot12.setLayout(slot12Layout);
        slot12Layout.setHorizontalGroup(
            slot12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(slotNum12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(landName12, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
            .addComponent(landPrice12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(ownerColor12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        slot12Layout.setVerticalGroup(
            slot12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(slot12Layout.createSequentialGroup()
                .addComponent(slotNum12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(landName12, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(landPrice12, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ownerColor12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        gameBoardPanel.add(slot12);
        slot12.setBounds(170, 130, 70, 117);

        slot13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        slotNum13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        slotNum13.setText("13");

        landName13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        landName13.setText("Land 3");

        landPrice13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        landPrice13.setText("$1800");

        ownerColor13.setBackground(new java.awt.Color(153, 153, 153));

        javax.swing.GroupLayout ownerColor13Layout = new javax.swing.GroupLayout(ownerColor13);
        ownerColor13.setLayout(ownerColor13Layout);
        ownerColor13Layout.setHorizontalGroup(
            ownerColor13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        ownerColor13Layout.setVerticalGroup(
            ownerColor13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 49, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout slot13Layout = new javax.swing.GroupLayout(slot13);
        slot13.setLayout(slot13Layout);
        slot13Layout.setHorizontalGroup(
            slot13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(slotNum13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(landName13, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
            .addComponent(landPrice13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(ownerColor13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        slot13Layout.setVerticalGroup(
            slot13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(slot13Layout.createSequentialGroup()
                .addComponent(slotNum13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(landName13, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(landPrice13, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ownerColor13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        gameBoardPanel.add(slot13);
        slot13.setBounds(250, 130, 70, 117);

        slot14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        slotNum14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        slotNum14.setText("14");

        landName14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        landName14.setText("Land 3");

        landPrice14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        landPrice14.setText("$1800");

        ownerColor14.setBackground(new java.awt.Color(153, 153, 153));

        javax.swing.GroupLayout ownerColor14Layout = new javax.swing.GroupLayout(ownerColor14);
        ownerColor14.setLayout(ownerColor14Layout);
        ownerColor14Layout.setHorizontalGroup(
            ownerColor14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        ownerColor14Layout.setVerticalGroup(
            ownerColor14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 49, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout slot14Layout = new javax.swing.GroupLayout(slot14);
        slot14.setLayout(slot14Layout);
        slot14Layout.setHorizontalGroup(
            slot14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(slotNum14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(landName14, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
            .addComponent(landPrice14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(ownerColor14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        slot14Layout.setVerticalGroup(
            slot14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(slot14Layout.createSequentialGroup()
                .addComponent(slotNum14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(landName14, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(landPrice14, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ownerColor14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        gameBoardPanel.add(slot14);
        slot14.setBounds(330, 130, 70, 117);

        slot15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        slotNum15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        slotNum15.setText("15");

        javax.swing.GroupLayout slot15Layout = new javax.swing.GroupLayout(slot15);
        slot15.setLayout(slot15Layout);
        slot15Layout.setHorizontalGroup(
            slot15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(slotNum15, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
        );
        slot15Layout.setVerticalGroup(
            slot15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(slot15Layout.createSequentialGroup()
                .addComponent(slotNum15)
                .addGap(0, 99, Short.MAX_VALUE))
        );

        gameBoardPanel.add(slot15);
        slot15.setBounds(410, 130, 75, 117);

        slot16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        slotNum16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        slotNum16.setText("16");

        javax.swing.GroupLayout slot16Layout = new javax.swing.GroupLayout(slot16);
        slot16.setLayout(slot16Layout);
        slot16Layout.setHorizontalGroup(
            slot16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(slotNum16, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
        );
        slot16Layout.setVerticalGroup(
            slot16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(slot16Layout.createSequentialGroup()
                .addComponent(slotNum16)
                .addGap(0, 99, Short.MAX_VALUE))
        );

        gameBoardPanel.add(slot16);
        slot16.setBounds(490, 130, 75, 117);

        slot17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        slotNum17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        slotNum17.setText("17");

        javax.swing.GroupLayout slot17Layout = new javax.swing.GroupLayout(slot17);
        slot17.setLayout(slot17Layout);
        slot17Layout.setHorizontalGroup(
            slot17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(slotNum17, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
        );
        slot17Layout.setVerticalGroup(
            slot17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(slot17Layout.createSequentialGroup()
                .addComponent(slotNum17)
                .addGap(0, 99, Short.MAX_VALUE))
        );

        gameBoardPanel.add(slot17);
        slot17.setBounds(580, 130, 75, 117);

        slot18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        slotNum18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        slotNum18.setText("18");

        javax.swing.GroupLayout slot18Layout = new javax.swing.GroupLayout(slot18);
        slot18.setLayout(slot18Layout);
        slot18Layout.setHorizontalGroup(
            slot18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(slotNum18, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
        );
        slot18Layout.setVerticalGroup(
            slot18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(slot18Layout.createSequentialGroup()
                .addComponent(slotNum18)
                .addGap(0, 99, Short.MAX_VALUE))
        );

        gameBoardPanel.add(slot18);
        slot18.setBounds(660, 130, 75, 117);

        slot19.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        slotNum19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        slotNum19.setText("19");

        landName19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        landName19.setText("Land 3");

        landPrice19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        landPrice19.setText("$1800");

        ownerColor19.setBackground(new java.awt.Color(153, 153, 153));

        javax.swing.GroupLayout ownerColor19Layout = new javax.swing.GroupLayout(ownerColor19);
        ownerColor19.setLayout(ownerColor19Layout);
        ownerColor19Layout.setHorizontalGroup(
            ownerColor19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        ownerColor19Layout.setVerticalGroup(
            ownerColor19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 49, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout slot19Layout = new javax.swing.GroupLayout(slot19);
        slot19.setLayout(slot19Layout);
        slot19Layout.setHorizontalGroup(
            slot19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(slotNum19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(landName19, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
            .addComponent(landPrice19, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(ownerColor19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        slot19Layout.setVerticalGroup(
            slot19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(slot19Layout.createSequentialGroup()
                .addComponent(slotNum19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(landName19, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(landPrice19, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ownerColor19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        gameBoardPanel.add(slot19);
        slot19.setBounds(750, 130, 80, 117);

        slot20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        slotNum20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        slotNum20.setText("20");

        landName20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        landName20.setText("Land 3");

        landPrice20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        landPrice20.setText("$1800");

        ownerColor20.setBackground(new java.awt.Color(153, 153, 153));

        javax.swing.GroupLayout ownerColor20Layout = new javax.swing.GroupLayout(ownerColor20);
        ownerColor20.setLayout(ownerColor20Layout);
        ownerColor20Layout.setHorizontalGroup(
            ownerColor20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        ownerColor20Layout.setVerticalGroup(
            ownerColor20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 32, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout slot20Layout = new javax.swing.GroupLayout(slot20);
        slot20.setLayout(slot20Layout);
        slot20Layout.setHorizontalGroup(
            slot20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(slotNum20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(landName20, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
            .addComponent(landPrice20, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(ownerColor20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        slot20Layout.setVerticalGroup(
            slot20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(slot20Layout.createSequentialGroup()
                .addComponent(slotNum20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(landName20, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(landPrice20, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ownerColor20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        gameBoardPanel.add(slot20);
        slot20.setBounds(10, 260, 80, 100);

        slot21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        slotNum21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        slotNum21.setText("21");

        landName21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        landName21.setText("Land 3");

        landPrice21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        landPrice21.setText("$1800");

        ownerColor21.setBackground(new java.awt.Color(153, 153, 153));

        javax.swing.GroupLayout ownerColor21Layout = new javax.swing.GroupLayout(ownerColor21);
        ownerColor21.setLayout(ownerColor21Layout);
        ownerColor21Layout.setHorizontalGroup(
            ownerColor21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        ownerColor21Layout.setVerticalGroup(
            ownerColor21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 32, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout slot21Layout = new javax.swing.GroupLayout(slot21);
        slot21.setLayout(slot21Layout);
        slot21Layout.setHorizontalGroup(
            slot21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(slotNum21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(landName21, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
            .addComponent(landPrice21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(ownerColor21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        slot21Layout.setVerticalGroup(
            slot21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(slot21Layout.createSequentialGroup()
                .addComponent(slotNum21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(landName21, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(landPrice21, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ownerColor21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        gameBoardPanel.add(slot21);
        slot21.setBounds(100, 260, 80, 100);

        slot22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        slotNum22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        slotNum22.setText("22");

        landName22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        landName22.setText("Land 3");

        landPrice22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        landPrice22.setText("$1800");

        ownerColor22.setBackground(new java.awt.Color(153, 153, 153));

        javax.swing.GroupLayout ownerColor22Layout = new javax.swing.GroupLayout(ownerColor22);
        ownerColor22.setLayout(ownerColor22Layout);
        ownerColor22Layout.setHorizontalGroup(
            ownerColor22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        ownerColor22Layout.setVerticalGroup(
            ownerColor22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 32, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout slot22Layout = new javax.swing.GroupLayout(slot22);
        slot22.setLayout(slot22Layout);
        slot22Layout.setHorizontalGroup(
            slot22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(slotNum22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(landName22, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
            .addComponent(landPrice22, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(ownerColor22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        slot22Layout.setVerticalGroup(
            slot22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(slot22Layout.createSequentialGroup()
                .addComponent(slotNum22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(landName22, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(landPrice22, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ownerColor22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        gameBoardPanel.add(slot22);
        slot22.setBounds(190, 260, 80, 100);

        slot23.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        slotNum23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        slotNum23.setText("23");

        landName23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        landName23.setText("Land 3");

        landPrice23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        landPrice23.setText("$1800");

        ownerColor23.setBackground(new java.awt.Color(153, 153, 153));

        javax.swing.GroupLayout ownerColor23Layout = new javax.swing.GroupLayout(ownerColor23);
        ownerColor23.setLayout(ownerColor23Layout);
        ownerColor23Layout.setHorizontalGroup(
            ownerColor23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        ownerColor23Layout.setVerticalGroup(
            ownerColor23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 32, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout slot23Layout = new javax.swing.GroupLayout(slot23);
        slot23.setLayout(slot23Layout);
        slot23Layout.setHorizontalGroup(
            slot23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(slotNum23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(landName23, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
            .addComponent(landPrice23, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(ownerColor23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        slot23Layout.setVerticalGroup(
            slot23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(slot23Layout.createSequentialGroup()
                .addComponent(slotNum23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(landName23, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(landPrice23, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ownerColor23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        gameBoardPanel.add(slot23);
        slot23.setBounds(280, 260, 80, 100);

        slot24.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        slotNum24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        slotNum24.setText("24");

        javax.swing.GroupLayout slot24Layout = new javax.swing.GroupLayout(slot24);
        slot24.setLayout(slot24Layout);
        slot24Layout.setHorizontalGroup(
            slot24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(slotNum24, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
        );
        slot24Layout.setVerticalGroup(
            slot24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(slot24Layout.createSequentialGroup()
                .addComponent(slotNum24)
                .addGap(0, 82, Short.MAX_VALUE))
        );

        gameBoardPanel.add(slot24);
        slot24.setBounds(370, 260, 75, 100);

        slot25.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        slotNum25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        slotNum25.setText("25");

        javax.swing.GroupLayout slot25Layout = new javax.swing.GroupLayout(slot25);
        slot25.setLayout(slot25Layout);
        slot25Layout.setHorizontalGroup(
            slot25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(slotNum25, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
        );
        slot25Layout.setVerticalGroup(
            slot25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(slot25Layout.createSequentialGroup()
                .addComponent(slotNum25)
                .addGap(0, 82, Short.MAX_VALUE))
        );

        gameBoardPanel.add(slot25);
        slot25.setBounds(460, 260, 75, 100);

        slot26.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        slotNum26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        slotNum26.setText("26");

        landName26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        landName26.setText("Land 3");

        landPrice26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        landPrice26.setText("$1800");

        ownerColor26.setBackground(new java.awt.Color(153, 153, 153));

        javax.swing.GroupLayout ownerColor26Layout = new javax.swing.GroupLayout(ownerColor26);
        ownerColor26.setLayout(ownerColor26Layout);
        ownerColor26Layout.setHorizontalGroup(
            ownerColor26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        ownerColor26Layout.setVerticalGroup(
            ownerColor26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 32, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout slot26Layout = new javax.swing.GroupLayout(slot26);
        slot26.setLayout(slot26Layout);
        slot26Layout.setHorizontalGroup(
            slot26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(slotNum26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(landName26, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
            .addComponent(landPrice26, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(ownerColor26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        slot26Layout.setVerticalGroup(
            slot26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(slot26Layout.createSequentialGroup()
                .addComponent(slotNum26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(landName26, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(landPrice26, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ownerColor26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        gameBoardPanel.add(slot26);
        slot26.setBounds(540, 260, 80, 100);

        slot27.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        slotNum27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        slotNum27.setText("27");

        javax.swing.GroupLayout slot27Layout = new javax.swing.GroupLayout(slot27);
        slot27.setLayout(slot27Layout);
        slot27Layout.setHorizontalGroup(
            slot27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(slotNum27, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
        );
        slot27Layout.setVerticalGroup(
            slot27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(slot27Layout.createSequentialGroup()
                .addComponent(slotNum27)
                .addGap(0, 82, Short.MAX_VALUE))
        );

        gameBoardPanel.add(slot27);
        slot27.setBounds(630, 260, 75, 100);

        slot28.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        slotNum28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        slotNum28.setText("28");

        javax.swing.GroupLayout slot28Layout = new javax.swing.GroupLayout(slot28);
        slot28.setLayout(slot28Layout);
        slot28Layout.setHorizontalGroup(
            slot28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(slotNum28, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
        );
        slot28Layout.setVerticalGroup(
            slot28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(slot28Layout.createSequentialGroup()
                .addComponent(slotNum28)
                .addGap(0, 82, Short.MAX_VALUE))
        );

        gameBoardPanel.add(slot28);
        slot28.setBounds(710, 260, 75, 100);

        slot29.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        slotNum29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        slotNum29.setText("29");

        javax.swing.GroupLayout slot29Layout = new javax.swing.GroupLayout(slot29);
        slot29.setLayout(slot29Layout);
        slot29Layout.setHorizontalGroup(
            slot29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(slotNum29, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
        );
        slot29Layout.setVerticalGroup(
            slot29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(slot29Layout.createSequentialGroup()
                .addComponent(slotNum29)
                .addGap(0, 82, Short.MAX_VALUE))
        );

        gameBoardPanel.add(slot29);
        slot29.setBounds(790, 260, 60, 100);

        slot30.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        slotNum30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        slotNum30.setText("30");

        javax.swing.GroupLayout slot30Layout = new javax.swing.GroupLayout(slot30);
        slot30.setLayout(slot30Layout);
        slot30Layout.setHorizontalGroup(
            slot30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(slotNum30, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
        );
        slot30Layout.setVerticalGroup(
            slot30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(slot30Layout.createSequentialGroup()
                .addComponent(slotNum30)
                .addGap(0, 82, Short.MAX_VALUE))
        );

        gameBoardPanel.add(slot30);
        slot30.setBounds(10, 370, 75, 100);

        slot31.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        slotNum31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        slotNum31.setText("31");

        javax.swing.GroupLayout slot31Layout = new javax.swing.GroupLayout(slot31);
        slot31.setLayout(slot31Layout);
        slot31Layout.setHorizontalGroup(
            slot31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(slotNum31, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
        );
        slot31Layout.setVerticalGroup(
            slot31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(slot31Layout.createSequentialGroup()
                .addComponent(slotNum31)
                .addGap(0, 82, Short.MAX_VALUE))
        );

        gameBoardPanel.add(slot31);
        slot31.setBounds(100, 370, 75, 100);

        slot32.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        slotNum32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        slotNum32.setText("32");

        javax.swing.GroupLayout slot32Layout = new javax.swing.GroupLayout(slot32);
        slot32.setLayout(slot32Layout);
        slot32Layout.setHorizontalGroup(
            slot32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(slotNum32, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
        );
        slot32Layout.setVerticalGroup(
            slot32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(slot32Layout.createSequentialGroup()
                .addComponent(slotNum32)
                .addGap(0, 82, Short.MAX_VALUE))
        );

        gameBoardPanel.add(slot32);
        slot32.setBounds(180, 370, 75, 100);

        slot33.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        slotNum33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        slotNum33.setText("33");

        javax.swing.GroupLayout slot33Layout = new javax.swing.GroupLayout(slot33);
        slot33.setLayout(slot33Layout);
        slot33Layout.setHorizontalGroup(
            slot33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(slotNum33, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
        );
        slot33Layout.setVerticalGroup(
            slot33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(slot33Layout.createSequentialGroup()
                .addComponent(slotNum33)
                .addGap(0, 82, Short.MAX_VALUE))
        );

        gameBoardPanel.add(slot33);
        slot33.setBounds(260, 370, 75, 100);

        slot34.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        slotNum34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        slotNum34.setText("34");

        javax.swing.GroupLayout slot34Layout = new javax.swing.GroupLayout(slot34);
        slot34.setLayout(slot34Layout);
        slot34Layout.setHorizontalGroup(
            slot34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(slotNum34, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
        );
        slot34Layout.setVerticalGroup(
            slot34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(slot34Layout.createSequentialGroup()
                .addComponent(slotNum34)
                .addGap(0, 82, Short.MAX_VALUE))
        );

        gameBoardPanel.add(slot34);
        slot34.setBounds(340, 370, 75, 100);

        slot35.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        slotNum35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        slotNum35.setText("35");

        landName35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        landName35.setText("Land 3");

        landPrice35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        landPrice35.setText("$1800");

        ownerColor35.setBackground(new java.awt.Color(153, 153, 153));

        javax.swing.GroupLayout ownerColor35Layout = new javax.swing.GroupLayout(ownerColor35);
        ownerColor35.setLayout(ownerColor35Layout);
        ownerColor35Layout.setHorizontalGroup(
            ownerColor35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        ownerColor35Layout.setVerticalGroup(
            ownerColor35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 32, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout slot35Layout = new javax.swing.GroupLayout(slot35);
        slot35.setLayout(slot35Layout);
        slot35Layout.setHorizontalGroup(
            slot35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(slotNum35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(landName35, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
            .addComponent(landPrice35, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(ownerColor35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        slot35Layout.setVerticalGroup(
            slot35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(slot35Layout.createSequentialGroup()
                .addComponent(slotNum35)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(landName35, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(landPrice35, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ownerColor35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        gameBoardPanel.add(slot35);
        slot35.setBounds(420, 370, 80, 100);

        slot36.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        slotNum36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        slotNum36.setText("36");

        javax.swing.GroupLayout slot36Layout = new javax.swing.GroupLayout(slot36);
        slot36.setLayout(slot36Layout);
        slot36Layout.setHorizontalGroup(
            slot36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(slotNum36, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
        );
        slot36Layout.setVerticalGroup(
            slot36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(slot36Layout.createSequentialGroup()
                .addComponent(slotNum36)
                .addGap(0, 82, Short.MAX_VALUE))
        );

        gameBoardPanel.add(slot36);
        slot36.setBounds(510, 370, 75, 100);

        slot37.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        slotNum37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        slotNum37.setText("37");

        javax.swing.GroupLayout slot37Layout = new javax.swing.GroupLayout(slot37);
        slot37.setLayout(slot37Layout);
        slot37Layout.setHorizontalGroup(
            slot37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(slotNum37, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
        );
        slot37Layout.setVerticalGroup(
            slot37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(slot37Layout.createSequentialGroup()
                .addComponent(slotNum37)
                .addGap(0, 82, Short.MAX_VALUE))
        );

        gameBoardPanel.add(slot37);
        slot37.setBounds(590, 370, 75, 100);

        slot38.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        slotNum38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        slotNum38.setText("38");

        landName38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        landName38.setText("Land 3");

        landPrice38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        landPrice38.setText("$1800");

        ownerColor38.setBackground(new java.awt.Color(153, 153, 153));

        javax.swing.GroupLayout ownerColor38Layout = new javax.swing.GroupLayout(ownerColor38);
        ownerColor38.setLayout(ownerColor38Layout);
        ownerColor38Layout.setHorizontalGroup(
            ownerColor38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        ownerColor38Layout.setVerticalGroup(
            ownerColor38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 32, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout slot38Layout = new javax.swing.GroupLayout(slot38);
        slot38.setLayout(slot38Layout);
        slot38Layout.setHorizontalGroup(
            slot38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(slotNum38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(landName38, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
            .addComponent(landPrice38, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(ownerColor38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        slot38Layout.setVerticalGroup(
            slot38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(slot38Layout.createSequentialGroup()
                .addComponent(slotNum38)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(landName38, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(landPrice38, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ownerColor38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        gameBoardPanel.add(slot38);
        slot38.setBounds(670, 370, 80, 100);

        slot39.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        slotNum39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        slotNum39.setText("39");

        landName39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        landName39.setText("Land 3");

        landPrice39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        landPrice39.setText("$1800");

        ownerColor39.setBackground(new java.awt.Color(153, 153, 153));

        javax.swing.GroupLayout ownerColor39Layout = new javax.swing.GroupLayout(ownerColor39);
        ownerColor39.setLayout(ownerColor39Layout);
        ownerColor39Layout.setHorizontalGroup(
            ownerColor39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        ownerColor39Layout.setVerticalGroup(
            ownerColor39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 32, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout slot39Layout = new javax.swing.GroupLayout(slot39);
        slot39.setLayout(slot39Layout);
        slot39Layout.setHorizontalGroup(
            slot39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(slotNum39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(landName39, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
            .addComponent(landPrice39, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(ownerColor39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        slot39Layout.setVerticalGroup(
            slot39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(slot39Layout.createSequentialGroup()
                .addComponent(slotNum39)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(landName39, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(landPrice39, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ownerColor39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        gameBoardPanel.add(slot39);
        slot39.setBounds(760, 370, 80, 100);

        slot40.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        slotNum40.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        slotNum40.setText("40");

        landName40.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        landName40.setText("Land 3");

        landPrice40.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        landPrice40.setText("$1800");

        ownerColor40.setBackground(new java.awt.Color(153, 153, 153));

        javax.swing.GroupLayout ownerColor40Layout = new javax.swing.GroupLayout(ownerColor40);
        ownerColor40.setLayout(ownerColor40Layout);
        ownerColor40Layout.setHorizontalGroup(
            ownerColor40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        ownerColor40Layout.setVerticalGroup(
            ownerColor40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 32, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout slot40Layout = new javax.swing.GroupLayout(slot40);
        slot40.setLayout(slot40Layout);
        slot40Layout.setHorizontalGroup(
            slot40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(slotNum40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(landName40, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
            .addComponent(landPrice40, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(ownerColor40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        slot40Layout.setVerticalGroup(
            slot40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(slot40Layout.createSequentialGroup()
                .addComponent(slotNum40, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(landName40, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(landPrice40, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ownerColor40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        gameBoardPanel.add(slot40);
        slot40.setBounds(10, 480, 80, 100);

        slot41.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        slotNum41.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        slotNum41.setText("41");

        landName41.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        landName41.setText("Land 3");

        landPrice41.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        landPrice41.setText("$1800");

        ownerColor41.setBackground(new java.awt.Color(153, 153, 153));

        javax.swing.GroupLayout ownerColor41Layout = new javax.swing.GroupLayout(ownerColor41);
        ownerColor41.setLayout(ownerColor41Layout);
        ownerColor41Layout.setHorizontalGroup(
            ownerColor41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        ownerColor41Layout.setVerticalGroup(
            ownerColor41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 32, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout slot41Layout = new javax.swing.GroupLayout(slot41);
        slot41.setLayout(slot41Layout);
        slot41Layout.setHorizontalGroup(
            slot41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(slotNum41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(landName41, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
            .addComponent(landPrice41, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(ownerColor41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        slot41Layout.setVerticalGroup(
            slot41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(slot41Layout.createSequentialGroup()
                .addComponent(slotNum41, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(landName41, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(landPrice41, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ownerColor41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        gameBoardPanel.add(slot41);
        slot41.setBounds(100, 480, 80, 100);

        slot42.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        slotNum42.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        slotNum42.setText("42");

        javax.swing.GroupLayout slot42Layout = new javax.swing.GroupLayout(slot42);
        slot42.setLayout(slot42Layout);
        slot42Layout.setHorizontalGroup(
            slot42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(slotNum42, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
        );
        slot42Layout.setVerticalGroup(
            slot42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(slot42Layout.createSequentialGroup()
                .addComponent(slotNum42)
                .addGap(0, 82, Short.MAX_VALUE))
        );

        gameBoardPanel.add(slot42);
        slot42.setBounds(190, 480, 75, 100);

        slot43.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        slotNum43.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        slotNum43.setText("43");

        javax.swing.GroupLayout slot43Layout = new javax.swing.GroupLayout(slot43);
        slot43.setLayout(slot43Layout);
        slot43Layout.setHorizontalGroup(
            slot43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(slotNum43, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
        );
        slot43Layout.setVerticalGroup(
            slot43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(slot43Layout.createSequentialGroup()
                .addComponent(slotNum43)
                .addGap(0, 82, Short.MAX_VALUE))
        );

        gameBoardPanel.add(slot43);
        slot43.setBounds(270, 480, 75, 100);

        actionPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        diceButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        diceButton.setText("Roll Dice");
        diceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                diceButtonActionPerformed(evt);
            }
        });

        diceNum.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        diceNum.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        diceNum.setText("8");

        buyLandButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        buyLandButton.setText("Buy Land");
        buyLandButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buyLandButtonActionPerformed(evt);
            }
        });

        endTurnButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        endTurnButton.setText("End Turn");
        endTurnButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                endTurnButtonActionPerformed(evt);
            }
        });

        currentTurnLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        currentTurnLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        currentTurnLabel.setText("Current Turn:");

        playerTurn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        playerTurn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        playerTurn.setText("Player 1");

        javax.swing.GroupLayout actionPanelLayout = new javax.swing.GroupLayout(actionPanel);
        actionPanel.setLayout(actionPanelLayout);
        actionPanelLayout.setHorizontalGroup(
            actionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(actionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(actionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(diceNum, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(diceButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buyLandButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(endTurnButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(currentTurnLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(playerTurn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        actionPanelLayout.setVerticalGroup(
            actionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(actionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(diceButton, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(diceNum, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buyLandButton, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(endTurnButton, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(currentTurnLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(playerTurn)
                .addContainerGap(292, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(gameBoardPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(actionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(player1Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(player2Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(player3Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(player4Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(player3Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(player2Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(player1Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(player4Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(gameBoardPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(actionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void diceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_diceButtonActionPerformed
        view.onRollDice();
    }//GEN-LAST:event_diceButtonActionPerformed

    private void endTurnButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_endTurnButtonActionPerformed
        view.onEndTurn();
    }//GEN-LAST:event_endTurnButtonActionPerformed

    private void buyLandButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buyLandButtonActionPerformed
        view.onBuyLand();
    }//GEN-LAST:event_buyLandButtonActionPerformed

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
            java.util.logging.Logger.getLogger(MiniMonopolyGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MiniMonopolyGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MiniMonopolyGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MiniMonopolyGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MiniMonopolyGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel actionPanel;
    private javax.swing.JLabel balance1;
    private javax.swing.JLabel balance2;
    private javax.swing.JLabel balance3;
    private javax.swing.JLabel balance4;
    private javax.swing.JLabel balanceLabel1;
    private javax.swing.JLabel balanceLabel2;
    private javax.swing.JLabel balanceLabel3;
    private javax.swing.JLabel balanceLabel4;
    private javax.swing.JButton buyLandButton;
    private javax.swing.JPanel colorPanel1;
    private javax.swing.JPanel colorPanel2;
    private javax.swing.JPanel colorPanel3;
    private javax.swing.JPanel colorPanel4;
    private javax.swing.JLabel currentTurnLabel;
    private javax.swing.JButton diceButton;
    private javax.swing.JLabel diceNum;
    private javax.swing.JButton endTurnButton;
    private javax.swing.JPanel gameBoardPanel;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel landName1;
    private javax.swing.JLabel landName10;
    private javax.swing.JLabel landName11;
    private javax.swing.JLabel landName12;
    private javax.swing.JLabel landName13;
    private javax.swing.JLabel landName14;
    private javax.swing.JLabel landName19;
    private javax.swing.JLabel landName2;
    private javax.swing.JLabel landName20;
    private javax.swing.JLabel landName21;
    private javax.swing.JLabel landName22;
    private javax.swing.JLabel landName23;
    private javax.swing.JLabel landName26;
    private javax.swing.JLabel landName3;
    private javax.swing.JLabel landName35;
    private javax.swing.JLabel landName38;
    private javax.swing.JLabel landName39;
    private javax.swing.JLabel landName40;
    private javax.swing.JLabel landName41;
    private javax.swing.JLabel landName7;
    private javax.swing.JLabel landName8;
    private javax.swing.JLabel landName9;
    private javax.swing.JLabel landPrice1;
    private javax.swing.JLabel landPrice10;
    private javax.swing.JLabel landPrice11;
    private javax.swing.JLabel landPrice12;
    private javax.swing.JLabel landPrice13;
    private javax.swing.JLabel landPrice14;
    private javax.swing.JLabel landPrice19;
    private javax.swing.JLabel landPrice2;
    private javax.swing.JLabel landPrice20;
    private javax.swing.JLabel landPrice21;
    private javax.swing.JLabel landPrice22;
    private javax.swing.JLabel landPrice23;
    private javax.swing.JLabel landPrice26;
    private javax.swing.JLabel landPrice3;
    private javax.swing.JLabel landPrice35;
    private javax.swing.JLabel landPrice38;
    private javax.swing.JLabel landPrice39;
    private javax.swing.JLabel landPrice40;
    private javax.swing.JLabel landPrice41;
    private javax.swing.JLabel landPrice7;
    private javax.swing.JLabel landPrice8;
    private javax.swing.JLabel landPrice9;
    private javax.swing.JPanel ownerColor1;
    private javax.swing.JPanel ownerColor10;
    private javax.swing.JPanel ownerColor11;
    private javax.swing.JPanel ownerColor12;
    private javax.swing.JPanel ownerColor13;
    private javax.swing.JPanel ownerColor14;
    private javax.swing.JPanel ownerColor19;
    private javax.swing.JPanel ownerColor2;
    private javax.swing.JPanel ownerColor20;
    private javax.swing.JPanel ownerColor21;
    private javax.swing.JPanel ownerColor22;
    private javax.swing.JPanel ownerColor23;
    private javax.swing.JPanel ownerColor26;
    private javax.swing.JPanel ownerColor3;
    private javax.swing.JPanel ownerColor35;
    private javax.swing.JPanel ownerColor38;
    private javax.swing.JPanel ownerColor39;
    private javax.swing.JPanel ownerColor40;
    private javax.swing.JPanel ownerColor41;
    private javax.swing.JPanel ownerColor7;
    private javax.swing.JPanel ownerColor8;
    private javax.swing.JPanel ownerColor9;
    private javax.swing.JLabel player1Label;
    private javax.swing.JPanel player1Panel;
    private javax.swing.JLabel player2Label;
    private javax.swing.JPanel player2Panel;
    private javax.swing.JLabel player3Label;
    private javax.swing.JPanel player3Panel;
    private javax.swing.JLabel player4Label;
    private javax.swing.JPanel player4Panel;
    private javax.swing.JLabel playerTurn;
    private javax.swing.JLabel position1;
    private javax.swing.JLabel position2;
    private javax.swing.JLabel position3;
    private javax.swing.JLabel position4;
    private javax.swing.JLabel positionLabel1;
    private javax.swing.JLabel positionLabel2;
    private javax.swing.JLabel positionLabel3;
    private javax.swing.JLabel positionLabel4;
    private javax.swing.JPanel slot0;
    private javax.swing.JPanel slot1;
    private javax.swing.JPanel slot10;
    private javax.swing.JPanel slot11;
    private javax.swing.JPanel slot12;
    private javax.swing.JPanel slot13;
    private javax.swing.JPanel slot14;
    private javax.swing.JPanel slot15;
    private javax.swing.JPanel slot16;
    private javax.swing.JPanel slot17;
    private javax.swing.JPanel slot18;
    private javax.swing.JPanel slot19;
    private javax.swing.JPanel slot2;
    private javax.swing.JPanel slot20;
    private javax.swing.JPanel slot21;
    private javax.swing.JPanel slot22;
    private javax.swing.JPanel slot23;
    private javax.swing.JPanel slot24;
    private javax.swing.JPanel slot25;
    private javax.swing.JPanel slot26;
    private javax.swing.JPanel slot27;
    private javax.swing.JPanel slot28;
    private javax.swing.JPanel slot29;
    private javax.swing.JPanel slot3;
    private javax.swing.JPanel slot30;
    private javax.swing.JPanel slot31;
    private javax.swing.JPanel slot32;
    private javax.swing.JPanel slot33;
    private javax.swing.JPanel slot34;
    private javax.swing.JPanel slot35;
    private javax.swing.JPanel slot36;
    private javax.swing.JPanel slot37;
    private javax.swing.JPanel slot38;
    private javax.swing.JPanel slot39;
    private javax.swing.JPanel slot4;
    private javax.swing.JPanel slot40;
    private javax.swing.JPanel slot41;
    private javax.swing.JPanel slot42;
    private javax.swing.JPanel slot43;
    private javax.swing.JPanel slot5;
    private javax.swing.JPanel slot6;
    private javax.swing.JPanel slot7;
    private javax.swing.JPanel slot8;
    private javax.swing.JPanel slot9;
    private javax.swing.JLabel slotNum0;
    private javax.swing.JLabel slotNum1;
    private javax.swing.JLabel slotNum10;
    private javax.swing.JLabel slotNum11;
    private javax.swing.JLabel slotNum12;
    private javax.swing.JLabel slotNum13;
    private javax.swing.JLabel slotNum14;
    private javax.swing.JLabel slotNum15;
    private javax.swing.JLabel slotNum16;
    private javax.swing.JLabel slotNum17;
    private javax.swing.JLabel slotNum18;
    private javax.swing.JLabel slotNum19;
    private javax.swing.JLabel slotNum2;
    private javax.swing.JLabel slotNum20;
    private javax.swing.JLabel slotNum21;
    private javax.swing.JLabel slotNum22;
    private javax.swing.JLabel slotNum23;
    private javax.swing.JLabel slotNum24;
    private javax.swing.JLabel slotNum25;
    private javax.swing.JLabel slotNum26;
    private javax.swing.JLabel slotNum27;
    private javax.swing.JLabel slotNum28;
    private javax.swing.JLabel slotNum29;
    private javax.swing.JLabel slotNum3;
    private javax.swing.JLabel slotNum30;
    private javax.swing.JLabel slotNum31;
    private javax.swing.JLabel slotNum32;
    private javax.swing.JLabel slotNum33;
    private javax.swing.JLabel slotNum34;
    private javax.swing.JLabel slotNum35;
    private javax.swing.JLabel slotNum36;
    private javax.swing.JLabel slotNum37;
    private javax.swing.JLabel slotNum38;
    private javax.swing.JLabel slotNum39;
    private javax.swing.JLabel slotNum4;
    private javax.swing.JLabel slotNum40;
    private javax.swing.JLabel slotNum41;
    private javax.swing.JLabel slotNum42;
    private javax.swing.JLabel slotNum43;
    private javax.swing.JLabel slotNum5;
    private javax.swing.JLabel slotNum6;
    private javax.swing.JLabel slotNum7;
    private javax.swing.JLabel slotNum8;
    private javax.swing.JLabel slotNum9;
    private javax.swing.JLabel status1;
    private javax.swing.JLabel status2;
    private javax.swing.JLabel status3;
    private javax.swing.JLabel status4;
    private javax.swing.JLabel statusLabel1;
    private javax.swing.JLabel statusLabel2;
    private javax.swing.JLabel statusLabel3;
    private javax.swing.JLabel statusLabel4;
    // End of variables declaration//GEN-END:variables
}
