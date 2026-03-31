package mini_monopoly;

public class Player {

    private int balance;
    private int position;
    private boolean active;
    private boolean inJail;

    public Player(int balance) {
        this.balance = balance;
        this.position = 0;
        this.active = true;
        this.inJail = false;
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

    public boolean isInJail() {
        return inJail;
    }

    public void setInJail(boolean inJail) {
        this.inJail = inJail;
    }
}
