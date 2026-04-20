package mini_monopoly;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Controller class. Handles the game logic like rolling dice,
 * buying land, paying rent, trading, ending turn, and applying
 * changes from the game editor.
 */
public class GameController {

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

    // core
    public int rollDice() {
        if (rolled || model.isGameOver()) return lastDiceRoll;

        Player current = model.getPlayer(model.getCurrentTurn());
        if (!current.isActive()) { endTurn(); return 0; }

        lastMessage = "";
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
        checkWin();

        rolled = true;
        return lastDiceRoll;
    }

    private void handleLanding(Player current) {
        int pos = current.getPosition();
        int turn = model.getCurrentTurn();

        if (model.getSquareType(pos) != GameModel.SQ_PROPERTY) return;

        int landIdx = model.getLandIndexForSlot(pos);
        Land land = model.getLand(landIdx);
        int ownerIdx = land.getOwnerIndex();

        if (ownerIdx == -1) {
            lastMessage += land.getName() + " is available ($" + land.getPrice() + ").\n";
            return;
        }
        if (ownerIdx == turn || !model.getPlayer(ownerIdx).isActive()) return;

        int multiplier = consecutiveMultiplier(pos, ownerIdx);
        int rent = (land.getPrice() / 10) * multiplier;
        Player owner = model.getPlayer(ownerIdx);
        int pay = Math.min(rent, current.getBalance());

        current.setBalance(current.getBalance() - pay);
        owner.setBalance(owner.getBalance() + pay);
        String mult = multiplier > 1 ? " (x" + multiplier + ")" : "";
        lastMessage += "Paid $" + pay + " rent" + mult + " to Player " + (ownerIdx + 1) + ".\n";

        if (pay < rent) {
            current.setActive(false);
            lastMessage += "Player " + (turn + 1) + " is bankrupt!\n";
            for (Land l : model.getLands()) {
                if (l.getOwnerIndex() == turn) l.setOwnerIndex(-1);
            }
        }
    }

    private int consecutiveMultiplier(int landedSlot, int ownerIdx) {
        int side = landedSlot / 11;
        int run = 1;
        for (int s = landedSlot - 1; s >= 0 && s / 11 == side; s--) {
            if (!ownsProperty(s, ownerIdx)) break;
            run++;
        }
        for (int s = landedSlot + 1; s < GameModel.BOARD_SIZE && s / 11 == side; s++) {
            if (!ownsProperty(s, ownerIdx)) break;
            run++;
        }
        if (run >= 3) return 3;
        if (run == 2) return 2;
        return 1;
    }

    private boolean ownsProperty(int slot, int ownerIdx) {
        if (!model.isPropertySlot(slot)) return false;
        return model.getLand(model.getLandIndexForSlot(slot)).getOwnerIndex() == ownerIdx;
    }

    public boolean canBuyLand() {
        if (!rolled) return false;
        Player p = model.getPlayer(model.getCurrentTurn());
        int slot = p.getPosition();
        if (!model.isPropertySlot(slot)) return false;
        Land land = model.getLand(model.getLandIndexForSlot(slot));
        return land.getOwnerIndex() == -1 && p.getBalance() >= land.getPrice();
    }

    // core
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

    /**
     * Trade a land from seller to buyer at the agreed price.
     * Only works before rolling the dice.
     */
    public boolean tradeLand(int sellerIdx, int buyerIdx, int landIdx, int price) {
        if (rolled || model.isGameOver()) return false;
        Land land = model.getLand(landIdx);
        if (land.getOwnerIndex() != sellerIdx) return false;
        Player buyer = model.getPlayer(buyerIdx);
        Player seller = model.getPlayer(sellerIdx);
        if (buyer.getBalance() < price) return false;

        buyer.setBalance(buyer.getBalance() - price);
        seller.setBalance(seller.getBalance() + price);
        land.setOwnerIndex(buyerIdx);
        lastMessage = "Player " + (buyerIdx + 1) + " bought " + land.getName()
            + " from Player " + (sellerIdx + 1) + " for $" + price + ".";
        return true;
    }

    // core
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

    /**
     * Apply changes made in the game editor. Updates each player's
     * balance, position, active flag, the current turn, and land owners.
     */
    public void applyEditorChanges(int[] balances, int[] positions,
                                   boolean[] active, int turn, int[] landOwners) {
        for (int i = 0; i < GameModel.NUM_PLAYERS; i++) {
            Player p = model.getPlayer(i);
            p.setBalance(balances[i]);
            if (positions[i] >= 0 && positions[i] < GameModel.BOARD_SIZE)
                p.setPosition(positions[i]);
            p.setActive(active[i]);
        }
        model.setCurrentTurn(turn);
        if (landOwners != null) {
            Land[] lands = model.getLands();
            for (int i = 0; i < lands.length && i < landOwners.length; i++) {
                int owner = landOwners[i];
                if (owner < -1 || owner >= GameModel.NUM_PLAYERS) continue;
                lands[i].setOwnerIndex(owner);
            }
        }
        rolled = false;
        checkWin();
    }

    public List<Integer> getOwnedProperties(int playerIndex) {
        List<Integer> owned = new ArrayList<>();
        Land[] lands = model.getLands();
        for (int i = 0; i < lands.length; i++) {
            if (lands[i].getOwnerIndex() == playerIndex) owned.add(i);
        }
        return owned;
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
