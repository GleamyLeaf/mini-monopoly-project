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

    public static final int[] PROPERTY_SLOTS = {
        1, 2, 3, 7, 8, 9, 10, 11, 12, 13, 14,
        19, 20, 21, 22, 23, 26, 35, 38, 39, 40, 41
    };

    private Player[] players;
    private Land[] lands;
    private int currentTurn;
    private int[] slotToLandMap;

    public GameState() {
        players = new Player[NUM_PLAYERS];
        for (int i = 0; i < NUM_PLAYERS; i++) {
            players[i] = new Player(STARTING_BALANCE);
        }
        currentTurn = 0;

        slotToLandMap = new int[BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            slotToLandMap[i] = -1;
        }
        for (int i = 0; i < PROPERTY_SLOTS.length; i++) {
            slotToLandMap[PROPERTY_SLOTS[i]] = i;
        }

        loadLands();
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
}
