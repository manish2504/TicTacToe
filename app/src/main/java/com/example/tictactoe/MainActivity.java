package com.example.tictactoe;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.tictactoe.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    boolean gameActive=true;
    int activePlayer=0;
    int [] gameState={2,2,2,2,2,2,2,2,2};
    int [][] winPositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    public void ptap(View view){
        ImageView img = (ImageView) view;
        int tappedImage=Integer.parseInt( img.getTag().toString());
        if(!gameActive){
            gameReset(view);
        }
        if(gameState[tappedImage]==2&&gameActive){
            gameState[tappedImage]=activePlayer;
            img.setTranslationY(-1000f);
            if(activePlayer==0){
                img.setImageResource(R.drawable.x);
                activePlayer=1;
                TextView status=findViewById(R.id.status);
                status.setText("O's Turn Tap to Play");
            }
            else{
                img.setImageResource(R.drawable.o);
                activePlayer=0;
                TextView status=findViewById(R.id.status);
                status.setText("X's Turn Tap to Play");
            }
            img.animate().translationYBy(1000f).setDuration(300);
        }
        for(int[] winPosition: winPositions){
          if(gameState[winPosition[0]]==gameState[winPosition[1]]&&gameState[winPosition[1]]==gameState[winPosition[2]]&&
                    gameState[winPosition[0]]!=0){
              String winnerStr;
              gameActive=false;
              if(gameState[winPosition[0]]==0){
                  winnerStr="X has won";
              }
              else{
                  winnerStr="O has won";
              }
              TextView status = findViewById(R.id.status);
              status.setText(winnerStr);
              }
          }
        }
    public void gameReset(View view){
        gameActive=true;
        activePlayer=0;
        for(int i=0;i<gameState.length;i++){
            gameState[i]=2;
        }
        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
        
    }

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);


    }









    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}




