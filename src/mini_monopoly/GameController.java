package mini_monopoly;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameController {

    private static final String[] CHANCE_CARDS = {
        "Bank dividend: +$200",
        "Doctor fees: -$150",
        "Competition prize: +$500",
        "School fees: -$250",
        "Repair costs: -$300",
        "Bonus: +$100"
    };
    private static final int[] CHANCE_VALUES = {200, -150, 500, -250, -300, 100};

    private GameModel model;
    private Random random;
    private int lastDiceRoll;
    private boolean rolled;
    private String lastMessage;

    public GameController(GameModel model) {
        this.model = model;
        this.random = new Random();
        this.lastMessage = "";
    }

    public int rollDice() {
        if (rolled || model.isGameOver()) return lastDiceRoll;

        Player current = model.getPlayer(model.getCurrentTurn());
        if (!current.isActive()) { endTurn(); return 0; }

        lastMessage = "";

        if (current.isInJail()) {
            if (current.getBalance() >= GameModel.JAIL_BAIL) {
                current.setBalance(current.getBalance() - GameModel.JAIL_BAIL);
                current.setInJail(false);
                lastMessage += "Paid $" + GameModel.JAIL_BAIL + " bail.\n";
            } else {
                current.setInJail(false);
                lastMessage += "Released from jail.\n";
                rolled = true;
                return 0;
            }
        }

        int die1 = random.nextInt(6) + 1;
        int die2 = random.nextInt(6) + 1;
        lastDiceRoll = die1 + die2;

        int oldPos = current.getPosition();
        int newPos = (oldPos + lastDiceRoll) % GameModel.BOARD_SIZE;
        current.setPosition(newPos);

        if (newPos < oldPos) {
            current.setBalance(current.getBalance() + GameModel.GO_SALARY);
            lastMessage += "Passed GO! +$" + GameModel.GO_SALARY + ".\n";
        }

        handleLanding(current);
        checkBankruptcy();
        checkWin();

        rolled = true;
        return lastDiceRoll;
    }

    private void handleLanding(Player current) {
        int pos = current.getPosition();
        int type = model.getSquareType(pos);
        int turn = model.getCurrentTurn();

        switch (type) {
            case GameModel.SQ_PROPERTY:
                int landIdx = model.getLandIndexForSlot(pos);
                Land land = model.getLand(landIdx);
                int owner = land.getOwnerIndex();
                if (owner >= 0 && owner != turn && model.getPlayer(owner).isActive()) {
                    int rent = land.getPrice() / 10;
                    current.setBalance(current.getBalance() - rent);
                    model.getPlayer(owner).setBalance(model.getPlayer(owner).getBalance() + rent);
                    lastMessage += "Paid $" + rent + " rent to Player " + (owner + 1) + ".\n";
                } else if (owner == -1) {
                    lastMessage += land.getName() + " is available ($" + land.getPrice() + ").\n";
                }
                break;

            case GameModel.SQ_TAX:
                current.setBalance(current.getBalance() - GameModel.TAX_AMOUNT);
                lastMessage += "Tax! -$" + GameModel.TAX_AMOUNT + ".\n";
                break;

            case GameModel.SQ_CHANCE:
                int card = random.nextInt(CHANCE_CARDS.length);
                current.setBalance(current.getBalance() + CHANCE_VALUES[card]);
                lastMessage += "Chance: " + CHANCE_CARDS[card] + "\n";
                break;

            case GameModel.SQ_GO_TO_JAIL:
                current.setPosition(GameModel.JAIL_SLOT);
                current.setInJail(true);
                lastMessage += "Go to Jail!\n";
                break;

            case GameModel.SQ_GO:
                lastMessage += "Landed on GO.\n";
                break;

            case GameModel.SQ_JAIL:
                lastMessage += "Just visiting Jail.\n";
                break;

            default:
                lastMessage += "Free Parking.\n";
                break;
        }
    }

    public boolean canBuyLand() {
        if (!rolled) return false;
        Player p = model.getPlayer(model.getCurrentTurn());
        int slot = p.getPosition();
        if (!model.isPropertySlot(slot)) return false;
        Land land = model.getLand(model.getLandIndexForSlot(slot));
        return land.getOwnerIndex() == -1 && p.getBalance() >= land.getPrice();
    }

    public boolean buyLand() {
        if (!canBuyLand()) return false;
        Player p = model.getPlayer(model.getCurrentTurn());
        int idx = model.getLandIndexForSlot(p.getPosition());
        Land land = model.getLand(idx);
        p.setBalance(p.getBalance() - land.getPrice());
        land.setOwnerIndex(model.getCurrentTurn());
        lastMessage = "Bought " + land.getName() + " for $" + land.getPrice() + ".";
        return true;
    }

    public boolean sellProperty(int landIndex) {
        Player p = model.getPlayer(model.getCurrentTurn());
        Land land = model.getLand(landIndex);
        if (land.getOwnerIndex() != model.getCurrentTurn()) return false;
        int price = land.getPrice() / 2;
        p.setBalance(p.getBalance() + price);
        land.setOwnerIndex(-1);
        lastMessage = "Sold " + land.getName() + " for $" + price + ".";
        return true;
    }

    public void endTurn() {
        rolled = false;
        lastDiceRoll = 0;
        int next = (model.getCurrentTurn() + 1) % GameModel.NUM_PLAYERS;
        int count = 0;
        while (!model.getPlayer(next).isActive() && count < GameModel.NUM_PLAYERS) {
            next = (next + 1) % GameModel.NUM_PLAYERS;
            count++;
        }
        model.setCurrentTurn(next);
    }

    public void applyEditorChanges(int[] balances, int[] positions,
                                   boolean[] active, int turn) {
        for (int i = 0; i < GameModel.NUM_PLAYERS; i++) {
            Player p = model.getPlayer(i);
            p.setBalance(balances[i]);
            if (positions[i] >= 0 && positions[i] < GameModel.BOARD_SIZE)
                p.setPosition(positions[i]);
            p.setActive(active[i]);
        }
        model.setCurrentTurn(turn);
        rolled = false;
    }

    public List<Integer> getOwnedProperties(int playerIndex) {
        List<Integer> owned = new ArrayList<>();
        Land[] lands = model.getLands();
        for (int i = 0; i < lands.length; i++) {
            if (lands[i].getOwnerIndex() == playerIndex) owned.add(i);
        }
        return owned;
    }

    private void checkBankruptcy() {
        Land[] lands = model.getLands();
        for (int i = 0; i < GameModel.NUM_PLAYERS; i++) {
            Player p = model.getPlayer(i);
            if (p.isActive() && p.getBalance() < 0) {
                p.setActive(false);
                lastMessage += "Player " + (i + 1) + " is bankrupt!\n";
                for (Land l : lands) {
                    if (l.getOwnerIndex() == i) l.setOwnerIndex(-1);
                }
            }
        }
    }

    private void checkWin() {
        int active = 0, last = -1;
        for (int i = 0; i < GameModel.NUM_PLAYERS; i++) {
            if (model.getPlayer(i).isActive()) { active++; last = i; }
        }
        if (active == 1) {
            model.setGameOver(true);
            model.setWinnerIndex(last);
            lastMessage += "Player " + (last + 1) + " wins!\n";
        } else if (active == 0) {
            model.setGameOver(true);
        }
    }

    public GameModel getModel() { return model; }
    
    public int getLastDiceRoll() { return lastDiceRoll; }
    public boolean hasRolled() { return rolled; }
    public void setRolled(boolean v) { rolled = v; }
    
    public String getLastMessage() { return lastMessage; }
}
