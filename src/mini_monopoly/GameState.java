package mini_monopoly;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class GameState {

    public static final int BOARD_SIZE = 44;
    public static final int NUM_PLAYERS = 4;
    public static final int STARTING_BALANCE = 2000;

    public static final int GO_SALARY = 1500;
    public static final int INCOME_TAX = 500;
    public static final int LUXURY_TAX = 750;
    public static final int JAIL_BAIL = 200;
    public static final int JAIL_SLOT = 15;

    public static final int SQ_GO = 0;
    public static final int SQ_PROPERTY = 1;
    public static final int SQ_TAX = 2;
    public static final int SQ_CHANCE = 3;
    public static final int SQ_JAIL = 4;
    public static final int SQ_GO_TO_JAIL = 5;
    public static final int SQ_FREE_PARKING = 6;

    public static final int[] PROPERTY_SLOTS = {
        1, 2, 3, 7, 8, 9, 10, 11, 12, 13, 14,
        19, 20, 21, 22, 23, 26, 35, 38, 39, 40, 41
    };

    private Player[] players;
    private Land[] lands;
    private int currentTurn;
    private int[] slotToLandMap;
    private int[] squareTypes;
    private boolean gameOver;
    private int winnerIndex;

    public GameState() {
        players = new Player[NUM_PLAYERS];
        for (int i = 0; i < NUM_PLAYERS; i++) {
            players[i] = new Player(STARTING_BALANCE);
        }
        currentTurn = 0;
        gameOver = false;
        winnerIndex = -1;

        slotToLandMap = new int[BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            slotToLandMap[i] = -1;
        }
        for (int i = 0; i < PROPERTY_SLOTS.length; i++) {
            slotToLandMap[PROPERTY_SLOTS[i]] = i;
        }

        initSquareTypes();
        loadLands();
    }

    private void initSquareTypes() {
        squareTypes = new int[BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (isPropertySlot(i)) {
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

    private void loadLands() {
        try {
            InputStreamReader reader = new InputStreamReader(
                getClass().getResourceAsStream("/resources/board.json"),
                StandardCharsets.UTF_8
            );
            JSONParser parser = new JSONParser();
            JSONArray array = (JSONArray) parser.parse(reader);
            reader.close();

            lands = new Land[array.size()];
            for (int i = 0; i < array.size(); i++) {
                JSONObject obj = (JSONObject) array.get(i);
                String name = (String) obj.get("name");
                int price = ((Number) obj.get("price")).intValue();
                lands[i] = new Land(name, price);
            }
        } catch (Exception e) {
            e.printStackTrace();
            lands = new Land[0];
        }
    }

    public Player getPlayer(int index) {
        return players[index];
    }

    public Land[] getLands() {
        return lands;
    }

    public Land getLand(int index) {
        return lands[index];
    }

    public int getCurrentTurn() {
        return currentTurn;
    }

    public void setCurrentTurn(int turn) {
        this.currentTurn = turn;
    }

    public int getLandIndexForSlot(int slot) {
        return slotToLandMap[slot];
    }

    public boolean isPropertySlot(int slot) {
        return slotToLandMap[slot] != -1;
    }

    public int getSquareType(int slot) {
        return squareTypes[slot];
    }

    public String getSquareName(int slot) {
        switch (squareTypes[slot]) {
            case SQ_GO: return "GO";
            case SQ_TAX: return (slot == 37) ? "Luxury Tax" : "Income Tax";
            case SQ_CHANCE: return "Chance";
            case SQ_JAIL: return "Jail";
            case SQ_GO_TO_JAIL: return "Go To Jail";
            case SQ_FREE_PARKING: return "Free Parking";
            default: return "";
        }
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public int getWinnerIndex() {
        return winnerIndex;
    }

    public void setWinnerIndex(int winnerIndex) {
        this.winnerIndex = winnerIndex;
    }
}
