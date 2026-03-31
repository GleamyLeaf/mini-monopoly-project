package mini_monopoly;

import java.util.Random;

public class GameController {

    private GameState state;
    private Random random;
    private int lastDiceRoll;
    private boolean rolled;

    public GameController(GameState state) {
        this.state = state;
        this.random = new Random();
        this.lastDiceRoll = 0;
        this.rolled = false;
    }

    public int rollDice() {
        if (rolled) {
            return lastDiceRoll;
        }

        int die1 = random.nextInt(6) + 1;
        int die2 = random.nextInt(6) + 1;
        lastDiceRoll = die1 + die2;

        Player current = state.getPlayer(state.getCurrentTurn());
        int newPos = (current.getPosition() + lastDiceRoll) % GameState.BOARD_SIZE;
        current.setPosition(newPos);
        rolled = true;

        return lastDiceRoll;
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

        return true;
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
}
