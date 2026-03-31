package mini_monopoly;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameController {

    public static final int SQ_GO = 0;
    public static final int SQ_PROPERTY = 1;
    public static final int SQ_TAX = 2;
    public static final int SQ_CHANCE = 3;
    public static final int SQ_JAIL = 4;
    public static final int SQ_GO_TO_JAIL = 5;
    public static final int SQ_FREE_PARKING = 6;

    private static final int GO_SALARY = 1500;
    private static final int INCOME_TAX = 500;
    private static final int LUXURY_TAX = 750;
    private static final int JAIL_BAIL = 200;
    private static final int JAIL_SLOT = 15;

    private static final String[] CHANCE_DESCRIPTIONS = {
        "Bank pays you dividend",
        "Doctor fees",
        "Advance to GO",
        "Go directly to Jail",
        "Building repairs cost",
        "Won competition prize",
        "Pay school fees",
        "It's your birthday!",
        "Move forward 3 spaces",
        "Move back 3 spaces"
    };

    private static final int[] CHANCE_AMOUNTS = {
        200, -150, 0, 0, -300, 500, -250, 100, 0, 0
    };

    private GameState state;
    private Random random;
    private int lastDiceRoll;
    private boolean rolled;
    private String lastMessage;
    private boolean gameOver;
    private int winnerIndex;
    private int[] squareTypes;

    public GameController(GameState state) {
        this.state = state;
        this.random = new Random();
        this.lastDiceRoll = 0;
        this.rolled = false;
        this.lastMessage = "";
        this.gameOver = false;
        this.winnerIndex = -1;
        initSquareTypes();
    }

    private void initSquareTypes() {
        squareTypes = new int[GameState.BOARD_SIZE];
        for (int i = 0; i < GameState.BOARD_SIZE; i++) {
            if (state.isPropertySlot(i)) {
                squareTypes[i] = SQ_PROPERTY;
            } else {
                squareTypes[i] = SQ_FREE_PARKING;
            }
        }
        squareTypes[0] = SQ_GO;
        squareTypes[4] = SQ_TAX;
        squareTypes[5] = SQ_CHANCE;
        squareTypes[6] = SQ_CHANCE;
        squareTypes[15] = SQ_JAIL;
        squareTypes[16] = SQ_CHANCE;
        squareTypes[17] = SQ_CHANCE;
        squareTypes[24] = SQ_CHANCE;
        squareTypes[25] = SQ_TAX;
        squareTypes[27] = SQ_CHANCE;
        squareTypes[28] = SQ_CHANCE;
        squareTypes[30] = SQ_CHANCE;
        squareTypes[32] = SQ_CHANCE;
        squareTypes[34] = SQ_CHANCE;
        squareTypes[36] = SQ_CHANCE;
        squareTypes[37] = SQ_TAX;
        squareTypes[42] = SQ_GO_TO_JAIL;
    }

    public int rollDice() {
        if (rolled || gameOver) {
            return lastDiceRoll;
        }

        Player current = state.getPlayer(state.getCurrentTurn());
        if (!current.isActive()) {
            endTurn();
            return 0;
        }

        lastMessage = "";

        if (current.isInJail()) {
            if (current.getBalance() >= JAIL_BAIL) {
                current.setBalance(current.getBalance() - JAIL_BAIL);
                current.setInJail(false);
                lastMessage += "Paid $" + JAIL_BAIL + " bail.\n";
            } else {
                current.setInJail(false);
                lastMessage += "Released from jail (couldn't afford bail).\n";
                rolled = true;
                return 0;
            }
        }

        int die1 = random.nextInt(6) + 1;
        int die2 = random.nextInt(6) + 1;
        lastDiceRoll = die1 + die2;

        int oldPos = current.getPosition();
        int newPos = (oldPos + lastDiceRoll) % GameState.BOARD_SIZE;
        current.setPosition(newPos);

        if (newPos < oldPos) {
            current.setBalance(current.getBalance() + GO_SALARY);
            lastMessage += "Passed GO! Collected $" + GO_SALARY + ".\n";
        }

        handleSquare(current, false);
        checkAllBankruptcies();
        checkWin();

        rolled = true;
        return lastDiceRoll;
    }

    private void handleSquare(Player current, boolean redirected) {
        int pos = current.getPosition();
        int type = squareTypes[pos];
        int playerIdx = state.getCurrentTurn();

        switch (type) {
            case SQ_GO:
                lastMessage += "Landed on GO.\n";
                break;
            case SQ_PROPERTY:
                handlePropertyLanding(current, playerIdx);
                break;
            case SQ_TAX:
                int tax = (pos == 37) ? LUXURY_TAX : INCOME_TAX;
                String taxName = (pos == 37) ? "Luxury" : "Income";
                current.setBalance(current.getBalance() - tax);
                lastMessage += taxName + " Tax! Paid $" + tax + ".\n";
                break;
            case SQ_CHANCE:
                if (!redirected) {
                    handleChance(current);
                }
                break;
            case SQ_JAIL:
                lastMessage += "Just visiting Jail.\n";
                break;
            case SQ_GO_TO_JAIL:
                current.setPosition(JAIL_SLOT);
                current.setInJail(true);
                lastMessage += "Go to Jail!\n";
                break;
            case SQ_FREE_PARKING:
                lastMessage += "Free Parking.\n";
                break;
        }
    }

    private void handlePropertyLanding(Player current, int playerIdx) {
        int pos = current.getPosition();
        int landIdx = state.getLandIndexForSlot(pos);
        Land land = state.getLand(landIdx);
        int owner = land.getOwnerIndex();

        if (owner >= 0 && owner != playerIdx && state.getPlayer(owner).isActive()) {
            int rent = land.getPrice() / 5;
            current.setBalance(current.getBalance() - rent);
            state.getPlayer(owner).setBalance(state.getPlayer(owner).getBalance() + rent);
            lastMessage += "Paid $" + rent + " rent to Player " + (owner + 1) + " for " + land.getName() + ".\n";
        } else if (owner == -1) {
            lastMessage += "Unowned property: " + land.getName() + " ($" + land.getPrice() + ").\n";
        }
    }

    private void handleChance(Player current) {
        int card = random.nextInt(CHANCE_DESCRIPTIONS.length);
        String desc = CHANCE_DESCRIPTIONS[card];
        int amount = CHANCE_AMOUNTS[card];

        switch (card) {
            case 2:
                current.setPosition(0);
                current.setBalance(current.getBalance() + GO_SALARY);
                lastMessage += "Chance: " + desc + "! Collected $" + GO_SALARY + ".\n";
                break;
            case 3:
                current.setPosition(JAIL_SLOT);
                current.setInJail(true);
                lastMessage += "Chance: " + desc + "!\n";
                break;
            case 7:
                int collected = 0;
                for (int i = 0; i < GameState.NUM_PLAYERS; i++) {
                    if (i != state.getCurrentTurn() && state.getPlayer(i).isActive()) {
                        state.getPlayer(i).setBalance(state.getPlayer(i).getBalance() - 100);
                        collected += 100;
                    }
                }
                current.setBalance(current.getBalance() + collected);
                lastMessage += "Chance: " + desc + "! Collected $" + collected + ".\n";
                break;
            case 8:
                int fwd = (current.getPosition() + 3) % GameState.BOARD_SIZE;
                current.setPosition(fwd);
                lastMessage += "Chance: " + desc + ".\n";
                handleSquare(current, true);
                break;
            case 9:
                int back = current.getPosition() - 3;
                if (back < 0) back += GameState.BOARD_SIZE;
                current.setPosition(back);
                lastMessage += "Chance: " + desc + ".\n";
                handleSquare(current, true);
                break;
            default:
                current.setBalance(current.getBalance() + amount);
                if (amount >= 0) {
                    lastMessage += "Chance: " + desc + "! +$" + amount + ".\n";
                } else {
                    lastMessage += "Chance: " + desc + "! -$" + Math.abs(amount) + ".\n";
                }
                break;
        }
    }

    private void checkAllBankruptcies() {
        Land[] lands = state.getLands();
        for (int i = 0; i < GameState.NUM_PLAYERS; i++) {
            Player p = state.getPlayer(i);
            if (p.isActive() && p.getBalance() < 0) {
                p.setActive(false);
                lastMessage += "Player " + (i + 1) + " is bankrupt!\n";
                for (Land land : lands) {
                    if (land.getOwnerIndex() == i) {
                        land.setOwnerIndex(-1);
                    }
                }
            }
        }
    }

    private void checkWin() {
        int activeCount = 0;
        int lastActive = -1;
        for (int i = 0; i < GameState.NUM_PLAYERS; i++) {
            if (state.getPlayer(i).isActive()) {
                activeCount++;
                lastActive = i;
            }
        }
        if (activeCount == 1) {
            gameOver = true;
            winnerIndex = lastActive;
            lastMessage += "Player " + (lastActive + 1) + " wins the game!\n";
        } else if (activeCount == 0) {
            gameOver = true;
            lastMessage += "All players bankrupt! Game over.\n";
        }
    }

    public boolean canBuyLand() {
        if (!rolled) {
            return false;
        }
        Player current = state.getPlayer(state.getCurrentTurn());
        int slot = current.getPosition();
        if (!state.isPropertySlot(slot)) {
            return false;
        }
        int landIndex = state.getLandIndexForSlot(slot);
        Land land = state.getLand(landIndex);
        return land.getOwnerIndex() == -1 && current.getBalance() >= land.getPrice();
    }

    public boolean buyLand() {
        if (!canBuyLand()) {
            return false;
        }
        Player current = state.getPlayer(state.getCurrentTurn());
        int slot = current.getPosition();
        int landIndex = state.getLandIndexForSlot(slot);
        Land land = state.getLand(landIndex);

        current.setBalance(current.getBalance() - land.getPrice());
        land.setOwnerIndex(state.getCurrentTurn());
        lastMessage = "Bought " + land.getName() + " for $" + land.getPrice() + ".";
        return true;
    }

    public boolean sellProperty(int landIndex) {
        Player current = state.getPlayer(state.getCurrentTurn());
        Land land = state.getLand(landIndex);
        if (land.getOwnerIndex() != state.getCurrentTurn()) {
            return false;
        }
        int sellPrice = land.getPrice() / 2;
        current.setBalance(current.getBalance() + sellPrice);
        land.setOwnerIndex(-1);
        lastMessage = "Sold " + land.getName() + " for $" + sellPrice + ".";
        return true;
    }

    public List<Integer> getOwnedProperties(int playerIndex) {
        List<Integer> owned = new ArrayList<>();
        Land[] lands = state.getLands();
        for (int i = 0; i < lands.length; i++) {
            if (lands[i].getOwnerIndex() == playerIndex) {
                owned.add(i);
            }
        }
        return owned;
    }

    public void endTurn() {
        rolled = false;
        lastDiceRoll = 0;
        int next = (state.getCurrentTurn() + 1) % GameState.NUM_PLAYERS;
        int checked = 0;
        while (!state.getPlayer(next).isActive() && checked < GameState.NUM_PLAYERS) {
            next = (next + 1) % GameState.NUM_PLAYERS;
            checked++;
        }
        state.setCurrentTurn(next);
    }

    public String getSquareName(int slot) {
        int type = squareTypes[slot];
        switch (type) {
            case SQ_GO: return "GO";
            case SQ_TAX: return (slot == 37) ? "Luxury Tax" : "Income Tax";
            case SQ_CHANCE: return "Chance";
            case SQ_JAIL: return "Jail";
            case SQ_GO_TO_JAIL: return "Go To Jail";
            case SQ_FREE_PARKING: return "Free Parking";
            default: return "";
        }
    }

    public GameState getState() {
        return state;
    }

    public int getLastDiceRoll() {
        return lastDiceRoll;
    }

    public boolean hasRolled() {
        return rolled;
    }

    public void setRolled(boolean rolled) {
        this.rolled = rolled;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public int getWinnerIndex() {
        return winnerIndex;
    }
}
