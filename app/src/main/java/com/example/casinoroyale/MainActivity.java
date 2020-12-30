package com.example.casinoroyale;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements IEventEnd {

    ImageView lights_off;
    ImageView lights_red;
    ImageView lights_odds;
    ImageView lights_evens;
    ImageView lights_1;
    ImageView lights_2;
    ImageView lights_3;
    ImageView handle_up;
    ImageView handle_down;

    ImageView pay_table_up;
    ImageView pay_table_down;

    ImageView line_bet_up;
    ImageView line_bet_down;

    ImageView line_select_up;
    ImageView line_select_down;

    ImageView cash_out_up;
    ImageView cash_out_down;

    TextView txt_bet;
    TextView txt_line_num;
    TextView txt_bet_ttl;
    TextView txt_win_amt;
    TextView txt_credit_balance;
    TextView jackpot_pot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        //define objects
        lights_off = findViewById(R.id.lights_off);
        lights_red = findViewById(R.id.lights_red);
        lights_odds = findViewById(R.id.lights_odds);
        lights_evens = findViewById(R.id.lights_evens);
        lights_1 = findViewById(R.id.lights_1);
        lights_2 = findViewById(R.id.lights_2);
        lights_3 = findViewById(R.id.lights_3);
        handle_up = findViewById(R.id.handle_up);
        handle_down = findViewById(R.id.handle_down);

        pay_table_up = findViewById(R.id.pay_table_up);
        pay_table_down = findViewById(R.id.pay_table_down);

        line_bet_up = findViewById(R.id.line_bet_up);
        line_bet_down = findViewById(R.id.line_bet_down);

        line_select_up = findViewById(R.id.line_select_up);
        line_select_down = findViewById(R.id.line_select_down);

        cash_out_up = findViewById(R.id.cash_out_up);
        cash_out_down = findViewById(R.id.cash_out_down);

        txt_bet = findViewById(R.id.txt_bet);
        txt_line_num = findViewById(R.id.txt_line_num);
        txt_bet_ttl = findViewById(R.id.txt_bet_ttl);
        txt_win_amt = findViewById(R.id.txt_win_amt);
        txt_credit_balance = findViewById(R.id.txt_credit_balance);
        jackpot_pot = findViewById(R.id.jackpot_pot);

        cash_out_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDealerLogin();
            }
        });
    }

    @Override
    public void eventEnd(int result, int count) {

    }

    public void cycleLineBet() {

    }

    public void cycleLineNumb() {

    }

    private void openDealerLogin() {
        Intent intent = new Intent(this, DealerLogin.class);
        startActivity(intent);
    }
}
