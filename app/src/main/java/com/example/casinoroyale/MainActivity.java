package com.example.casinoroyale;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements IEventEnd {

    final int MIN_BET = 1, MAX_BET = 5, MAX_LINES = 3;
    int bet_amt = 1, line_bet = 1;
    int ttl_bet = 0;
    int count_done = 0;

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

        //set credit balance
        setCreditBalance();
        calcTtlBet();

        cash_out_up.setOnClickListener(v -> openDealerLogin());

        handle_up.setOnClickListener(view -> {
            if (Common.SCORE >= bet_amt) {
                handle_up.setVisibility(View.GONE);
                handle_down.setVisibility(View.VISIBLE);
                Common.SCORE -= bet_amt;
                setCreditBalance();
            } else {
                Toast.makeText(MainActivity.this, R.string.poorNotice, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void eventEnd(int result, int count) {

    }

    private void openDealerLogin() {
        Intent intent = new Intent(this, DealerLogin.class);
        startActivity(intent);
    }

    private void setCreditBalance() {
        txt_credit_balance.setText(String.valueOf(Common.SCORE));
    }

    public void cycleLineBet(View view) {
        line_bet_up.setVisibility(View.GONE);
        line_bet_down.setVisibility(View.VISIBLE);
        new Handler().postDelayed(cycleLineBet(), 500);
    }

    public Runnable cycleLineBet() {
        if (count_done == 0 && handle_up.getVisibility() == View.VISIBLE && bet_amt < MAX_BET) {
            bet_amt++;
        } else if (count_done == 0 && handle_up.getVisibility() == View.VISIBLE) {
            bet_amt = 1;
        }
        txt_bet.setText(String.valueOf(bet_amt));
        calcTtlBet();
        line_bet_up.setVisibility(View.VISIBLE);
        line_bet_down.setVisibility(View.GONE);
        return null;
    }

    public void cycleLineNumb(View view) {
        line_select_up.setVisibility(View.GONE);
        line_select_down.setVisibility(View.VISIBLE);
        new Handler().postDelayed(cycleLineNumb(), 500);
    }

    public Runnable cycleLineNumb() {
        if (count_done == 0 && handle_up.getVisibility() == View.VISIBLE && line_bet < MAX_LINES) {
            line_bet++;
        } else if (count_done == 0 && handle_up.getVisibility() == View.VISIBLE) {
            line_bet = 1;
        }
        txt_line_num.setText(String.valueOf(line_bet));
        calcTtlBet();
        line_select_up.setVisibility(View.VISIBLE);
        line_select_down.setVisibility(View.GONE);
        return null;
    }

    private void calcTtlBet() {
        ttl_bet = bet_amt * line_bet;
        txt_bet_ttl.setText(String.valueOf(ttl_bet));
    }

}
