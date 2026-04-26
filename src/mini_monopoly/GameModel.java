package mini_monopoly;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * Holds the game state -- players, lands, current turn etc
 * Board layout comes from /resources/board.json (loaded once at start, for now)
 */
public class GameModel {

    public static final int BOARD_SIZE = 44;
    public static final int NUM_PLAYERS = 4;
    public static final int STARTING_BALANCE = 2000;
    public static final int GO_SALARY = 2000;

    public static final int SQ_GO = 0;
    public static final int SQ_PROPERTY = 1;
    public static final int SQ_BLANK = 2;

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

    public GameModel() {
        players = new Player[NUM_PLAYERS];
        for (int i = 0; i < NUM_PLAYERS; i++) {
            players[i] = new Player(STARTING_BALANCE);
        }
        currentTurn = 0;
        gameOver = false;
        winnerIndex = -1;

        // map every slot to a land index, -1 if not a property slot
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
            squareTypes[i] = isPropertySlot(i) ? SQ_PROPERTY : SQ_BLANK;
        }
        squareTypes[0] = SQ_GO; // slot 0 is GO
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
                lands[i] = new Land(
                    (String) obj.get("name"),
                    ((Number) obj.get("price")).intValue()
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
            lands = new Land[0];
        }
    }

    public Player getPlayer(int i) { return players[i]; }
    public Land[] getLands() { return lands; }
    public Land getLand(int i) { return lands[i]; }

    public int getCurrentTurn() { return currentTurn; }
    public void setCurrentTurn(int t) { currentTurn = t; }

    public boolean isGameOver() { return gameOver; }
    public void setGameOver(boolean v) { gameOver = v; }

    public int getWinnerIndex() { return winnerIndex; }
    public void setWinnerIndex(int i) { winnerIndex = i; }

    public int getSquareType(int slot) { return squareTypes[slot]; }
    public int getLandIndexForSlot(int slot) { return slotToLandMap[slot]; }
    public boolean isPropertySlot(int slot) { return slotToLandMap[slot] != -1; }

    public String getSquareName(int slot) {
        return squareTypes[slot] == SQ_GO ? "GO" : "";
    }
}
