package se.sockertoppar.a5kamp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {



    String TAG = "tag";
    
    float screenWidth;
    float boxSize;

    ArrayList<Player> playerList = new ArrayList<Player>();

    LayoutInflater inflater;
    LinearLayout llBoard;
    LinearLayout llPlayerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        screenWidth = metrics.widthPixels;

        boxSize = screenWidth/5;

        inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //itemContiner = inflater.inflate(R.layout.item_continer, null, true);

        llBoard = (LinearLayout)findViewById(R.id.ll_board);
        llPlayerName = (LinearLayout)findViewById(R.id.ll_player_name);
        RelativeLayout rlEmptyBox = (RelativeLayout)findViewById(R.id.rl_empty_box);
        llPlayerName.setMinimumHeight((int)boxSize);
        //llPlayerName.setMinimumWidth((int)screenWidth);
        rlEmptyBox.setMinimumHeight((int)boxSize);
        rlEmptyBox.setMinimumWidth((int)boxSize);
    }


    /**
     * Sätter topmenyn
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d("tag", "onCreateOptionsMenu ");
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    /**
     * Onklick på meny valen
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        View itemContiner;

        switch (item.getItemId()) {
            case R.id.menu_add_friends:
                Log.d(TAG, "onOptionsItemSelected: menu_add_friends");

                Player player = new Player("E");
                playerList.add(player);

                //lägger till en person vy
                itemContiner = inflater.inflate(R.layout.item_continer, null, true);
                llPlayerName.addView(itemContiner);
//                itemContiner.setMinimumHeight((int)boxSize);
//                itemContiner.setMinimumWidth((int)boxSize);
                itemContiner.setLayoutParams(new LinearLayout.LayoutParams((int)boxSize, (int)boxSize));

                setTextItemContiner(itemContiner, player.getName());

                return true;

            case R.id.menu_add_game:
                Log.d(TAG, "onOptionsItemSelected: menu_add_game");

                OneGame oneGame = new OneGame("kasta boll i hinken");
                //lägger till en game vy
                View gameContiner = inflater.inflate(R.layout.onegame_container, null, true);

                llBoard.addView(gameContiner);
                gameContiner.setMinimumHeight((int)boxSize);
                gameContiner.setMinimumWidth((int)boxSize);

                itemContiner = inflater.inflate(R.layout.item_continer, null, true);

                LinearLayout llOnegame = (LinearLayout)gameContiner.findViewById(R.id.ll_onegame);

                llOnegame.addView(itemContiner);
//                itemContiner.setMinimumHeight((int)boxSize);
//                itemContiner.setMinimumWidth((int)boxSize);
                itemContiner.setLayoutParams(new LinearLayout.LayoutParams((int)boxSize, (int)boxSize));

                setTextItemContiner(itemContiner, oneGame.getName());

                for (int i = 0; i < playerList.size(); i++) {

                }

                return true;

            case R.id.menu_reset:
                Log.d(TAG, "onOptionsItemSelected: menu_reset");
                return true;
            
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onClickItemContiner(View view){
        Log.d(TAG, "onClickItemContiner: ");
        TextView viewText = (TextView)view.findViewById(R.id.item_text);
        viewText.setText("P");
    }

    public void setTextItemContiner(View rl, String text){

        TextView textView = (TextView) rl.findViewById(R.id.item_text);
        textView.setText(text);

    }
}
