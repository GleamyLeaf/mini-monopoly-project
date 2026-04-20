package mini_monopoly;

/**
 * A player in the game. Tracks balance, position on the board,
 * and whether they are still in the game (active) or bankrupt.
 */
public class Player {

    private int balance;
    private int position;
    private boolean active;

    public Player(int balance) {
        this.balance = balance;
        this.position = 0;
        this.active = true;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
