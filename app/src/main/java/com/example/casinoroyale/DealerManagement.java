package com.example.casinoroyale;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DealerManagement extends AppCompatActivity {

    TextView player_score;
    TextView player_name;
    EditText new_player_name;
    EditText new_player_score;
    Button create_player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dealer_management);

        //map fields
        player_score = findViewById(R.id.player_score);
        player_name = findViewById(R.id.player_name);
        new_player_name = findViewById(R.id.new_player_name);
        new_player_score = findViewById(R.id.new_player_score);
        create_player = findViewById(R.id.ceate_new_player);

        //set previous values
        player_name.setText(new StringBuilder().append("Player: ").append(Common.PLAYER_NAME).toString());
        player_score.setText(new StringBuilder().append("Score: ").append(Common.SCORE).toString());

        //button listener
        create_player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Common.SCORE = Integer.parseInt(new_player_score.getText().toString());
                Common.PLAYER_NAME = new_player_name.getText().toString();
                mainActivity();
            }
        });
    }

    private void mainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
