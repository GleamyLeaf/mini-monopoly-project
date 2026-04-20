package mini_monopoly;

/**
 * A land slot on the board. Has a name, price, and an owner index
 * (-1 means no owner).
 */
public class Land {

    private String name;
    private int price;
    private int ownerIndex;

    public Land(String name, int price) {
        this.name = name;
        this.price = price;
        this.ownerIndex = -1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getOwnerIndex() {
        return ownerIndex;
    }

    public void setOwnerIndex(int ownerIndex) {
        this.ownerIndex = ownerIndex;
    }
}
