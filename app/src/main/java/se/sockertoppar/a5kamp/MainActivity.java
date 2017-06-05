package se.sockertoppar.a5kamp;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.text.InputType.TYPE_CLASS_NUMBER;

public class MainActivity extends AppCompatActivity {



    String TAG = "tag";
    
    float screenWidth;
    float boxSize;

    ArrayList<Player> playerList = new ArrayList<Player>();
    ArrayList<OneGame> oneGameList = new ArrayList<OneGame>();

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
        rlEmptyBox.setMinimumHeight((int)boxSize);
        rlEmptyBox.setMinimumWidth((int)boxSize);


        addOneGame("Total poäng");
        addOneGame("PP");



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
                addItemContainer(llPlayerName, R.layout.item_continer, player.getName());

                for (OneGame oneGame: oneGameList) {
                    oneGame.addGamePoints();
                };

                final int childCount = llBoard.getChildCount();
                LinearLayout llOneGame;
                for (int i = 0; i < childCount - 1; i++) {
                    if(i < childCount - 3) {
                        llOneGame = (LinearLayout) llBoard.getChildAt(i + 1);
                        itemContiner = inflater.inflate(R.layout.item_continer_points, null, true);
                        llOneGame.addView(itemContiner);
                        itemContiner.setLayoutParams(new LinearLayout.LayoutParams((int) boxSize, (int) boxSize));
                    }else{
                        llOneGame = (LinearLayout) llBoard.getChildAt(i + 1);
                        itemContiner = inflater.inflate(R.layout.item_continer, null, true);
                        llOneGame.addView(itemContiner);
                        itemContiner.setLayoutParams(new LinearLayout.LayoutParams((int) boxSize, (int) boxSize));
                    }
                }

                return true;

            case R.id.menu_add_game:
                Log.d(TAG, "onOptionsItemSelected: menu_add_game");

                OneGame oneGame = new OneGame("kasta boll i hinken", playerList.size());
                oneGameList.add(oneGame);

                addOneGame(oneGame.getName());


                return true;

            case R.id.menu_reset:
                Log.d(TAG, "onOptionsItemSelected: menu_reset");
                return true;
            
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void addOneGame(String rowName){

        final int childCount = llBoard.getChildCount();
        Log.d(TAG, "addOneGame: childCount " + childCount);

        //lägger till en game vy
        View gameContiner = inflater.inflate(R.layout.onegame_container, null, true);
        if(childCount < 3){
            llBoard.addView(gameContiner);
        }else {
            llBoard.addView(gameContiner, childCount - 2);
        }
        gameContiner.setMinimumHeight((int)boxSize);
        gameContiner.setMinimumWidth((int)boxSize);

        //linerlayout i board
        LinearLayout llOnegame = (LinearLayout)gameContiner.findViewById(R.id.ll_onegame);

        //lägger till den första items i linerlayout men namn på spelet
        addItemContainer(llOnegame, R.layout.item_continer, rowName);

        //lägger till resten av items i linerlayout för poängen
        for (int i = 0; i < playerList.size(); i++) {
            addItemContainerEditText(llOnegame, R.layout.item_continer_points, null);
        }
    }


    public void onClickItemContiner(View view){
        Log.d(TAG, "onClickItemContiner: ");
    }

//    public void onClickItemContinerPoints(View view){
//        Log.d(TAG, "onClickItemContiner: ");
//
//        int newNumber = 5;
//
//        View viewParent = (View)view.getParent();
//        int indexInLine = ((ViewGroup) view.getParent().getParent()).indexOfChild(viewParent);
//        int indexInRow = ((ViewGroup) view.getParent()).indexOfChild(view);
//
//        oneGameList.get(indexInLine-1).getGamePoints().set(indexInRow-1, newNumber);
//
//        EditText viewText = (EditText) view.findViewById(R.id.item_text);
//        viewText.setText(String.valueOf(newNumber));
//
//    }



    public void addItemContainer(LinearLayout ll, int item, String name){
        View itemContiner = inflater.inflate(item, null, true);
        ll.addView(itemContiner);
        itemContiner.setLayoutParams(new LinearLayout.LayoutParams((int)boxSize, (int)boxSize));

        if(name != null) {
            setTextItemContiner(itemContiner, name);
        }
    }

    public void setTextItemContiner(View rl, String text){

        TextView textView = (TextView) rl.findViewById(R.id.item_text);
        textView.setText(text);
    }

    public void addItemContainerEditText(final LinearLayout ll, int item, String name){
        Log.d(TAG, "addItemContainerEditText: ");
        View itemContiner = inflater.inflate(item, null, true);
        ll.addView(itemContiner);
        itemContiner.setLayoutParams(new LinearLayout.LayoutParams((int)boxSize, (int)boxSize));
        
        final EditText editText = (EditText)itemContiner.findViewById(R.id.item_text);
        editText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                Log.d(TAG, "afterTextChanged: "  + editText);
                pointsChange(editText, editText.getText().toString());
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });
        
    }

    public void pointsChange(View Eview, String newNumber){
        Log.d(TAG, "pointsChange: Eview " + Eview);
        //Log.d(TAG, "pointsChange: " + view.getText().toString());

        ViewParent view = Eview.getParent();
        Log.d(TAG, "pointsChange: view " + view);
        View viewParent = (View)view.getParent();
        int indexInLine = ((ViewGroup) view.getParent().getParent()).indexOfChild(viewParent);
        int indexInRow = ((ViewGroup) Eview).indexOfChild(Eview);

        Log.d(TAG, "pointsChange: " + indexInLine);
        Log.d(TAG, "pointsChange: " + indexInRow);

        //oneGameList.get(indexInLine-1).getGamePoints().set(indexInRow-1, Integer.valueOf(newNumber));

        //EditText viewText = (EditText) view.findViewById(R.id.item_text);
        //viewText.setText(String.valueOf(newNumber));
    }
}
