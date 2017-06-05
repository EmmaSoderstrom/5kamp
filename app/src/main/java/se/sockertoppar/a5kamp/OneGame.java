package se.sockertoppar.a5kamp;

import android.util.Log;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * Created by User on 2017-06-05.
 */

public class OneGame {

    String TAG = "tag";

    String name;
    int numberOfPlayers;
    ArrayList<Integer> gamePoints = new ArrayList<Integer>();

    public OneGame(String name, int numberOfPlayers){
        this.name = name;
        this.numberOfPlayers = numberOfPlayers;
        setGamePoints();
    }

    public String getName() {
        return name;
    }

    public void setGamePoints() {
        for (int i = 0; i < numberOfPlayers; i++) {
            gamePoints.add(0);
        }

        Log.d(TAG, "setGamePoints: gamePoints " + gamePoints);
    }

    public void addGamePoints() {
        gamePoints.add(0);

        Log.d(TAG, "setGamePoints: gamePoints " + gamePoints);
    }

    public ArrayList<Integer> getGamePoints() {
        Log.d(TAG, "getGamePoints: " + gamePoints);
        return gamePoints;
    }
}
