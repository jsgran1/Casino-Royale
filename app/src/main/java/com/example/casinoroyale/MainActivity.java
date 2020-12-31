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

import java.util.Random;

public class MainActivity extends AppCompatActivity implements IEventEnd {

    final int MAX_BET = 3;
    final int MAX_LINES = 5;
    final int BTN_DELAY = 200;
    int bet_amt = 1;
    int line_bet = 1;
    int ttl_bet = 0;
    int reel_1_spins = 0;
    int reel_2_spins = 0;
    int reel_3_spins = 0;
    int reel_1_spin_count = 0;
    int reel_2_spin_count = 0;
    int reel_3_spin_count = 0;
    // reel run-time images
    ImageView lights_off;
    ImageView lights_red;
    ImageView lights_odds;
    ImageView lights_evens;
    ImageView lights_1;
    ImageView lights_2;
    ImageView lights_3;
    ImageView handle_up;
    ImageView handle_down;
    // pay table images
    ImageView pay_table_up;
    ImageView pay_table_down;
    // line bet images
    ImageView line_bet_up;
    ImageView line_bet_down;
    // line select images
    ImageView line_select_up;
    ImageView line_select_down;
    // cash out images
    ImageView cash_out_up;
    ImageView cash_out_down;
    // text boxes
    TextView txt_bet;
    TextView txt_line_num;
    TextView txt_bet_ttl;
    TextView txt_win_amt;
    TextView txt_credit_balance;
    TextView jackpot_pot;

    ImageViewScrolling r1_img1;
    ImageViewScrolling r2_img1;
    ImageViewScrolling r3_img1;
    ImageViewScrolling r1_img2;
    ImageViewScrolling r2_img2;
    ImageViewScrolling r3_img2;
    ImageViewScrolling r1_img3;
    ImageViewScrolling r2_img3;
    ImageViewScrolling r3_img3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set view
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
        r1_img1 = findViewById(R.id.r1_img1);
        r2_img1 = findViewById(R.id.r2_img1);
        r3_img1 = findViewById(R.id.r3_img1);
        r1_img2 = findViewById(R.id.r1_img2);
        r2_img2 = findViewById(R.id.r2_img2);
        r3_img2 = findViewById(R.id.r3_img2);
        r1_img3 = findViewById(R.id.r1_img3);
        r2_img3 = findViewById(R.id.r2_img3);
        r3_img3 = findViewById(R.id.r3_img3);
        //set credit balance
        setCreditBalance();
        calcTtlBet();
        initialReelImages();
        //BUTTON LISTENER - cash out
        cash_out_up.setOnClickListener(v -> openDealerLogin());
        //BUTTON LISTENER - spin reel
        handle_up.setOnClickListener(view -> {
            if (Common.SCORE >= ttl_bet) {
                handle_up.setVisibility(View.INVISIBLE);
                handle_down.setVisibility(View.VISIBLE);
                Common.SCORE -= ttl_bet;
                setCreditBalance();
                startReelSpin();
            } else {
                Toast.makeText(MainActivity.this, R.string.poorNotice, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void startReelSpin() {
        //determine reel spin counts
        //reel_1_spin_count = (new Random().nextInt(9)) + 10;
        reel_1_spin_count = 3;
        reel_2_spin_count = (new Random().nextInt(9)) + reel_1_spin_count;
        reel_3_spin_count = (new Random().nextInt(9)) + reel_2_spin_count;
        //rotate images
        spinReels();
    }

    private void spinReels() {
        if (reel_1_spins <= reel_1_spin_count) {
            System.out.print(r1_img1.getCurrValue());
            r1_img3.setImage("next", r1_img2.getCurrValue());
            r1_img2.setImage("next", r1_img1.getCurrValue());
            pickNextR1Image();
            r1_img1.spinReel();
            r1_img2.spinReel();
            r1_img3.spinReel();
            reel_1_spins++;
        }
        if (reel_2_spins <= reel_2_spin_count) {
            pickNextR2Image();
            r2_img1.spinReel();
            r2_img1.setImage("current",r2_img1.getNextValue());
            r2_img2.setImage("next", r2_img1.getCurrValue());
            r2_img2.spinReel();
            r2_img2.setImage("current",r2_img2.getNextValue());
            r2_img3.setImage("next", r2_img2.getCurrValue());
            r2_img3.spinReel();
            r2_img3.setImage("current",r2_img3.getNextValue());
            reel_2_spins++;
        }
        pickNextR3Image();
        r3_img1.spinReel();
        r3_img1.setImage("current",r3_img1.getNextValue());
        r3_img2.setImage("next", r3_img1.getCurrValue());
        r3_img2.spinReel();
        r3_img2.setImage("current",r3_img2.getNextValue());
        r3_img3.setImage("next", r3_img2.getCurrValue());
        r3_img3.spinReel();
        r3_img3.setImage("current",r3_img3.getNextValue());
        reel_3_spins++;
        if (reel_3_spins <= reel_3_spin_count) {
            (new Handler()).postDelayed(this::spinReels, Common.ANIMATION_DUR);
        } else {
            finishReelSpins();
        }
    }

    private void finishReelSpins () {
        reel_1_spins = 0;
        reel_2_spins = 0;
        reel_3_spins = 0;
        reel_1_spin_count = 0;
        reel_2_spin_count = 0;
        reel_3_spin_count = 0;
        handle_up.setVisibility(View.VISIBLE);
        handle_down.setVisibility(View.INVISIBLE);
    }

    private void initialReelImages () {
        // set images
        r1_img1.setImage("current", determineNextSymbol());
        r2_img1.setImage("current", determineNextSymbol());
        r3_img1.setImage("current", determineNextSymbol());
        r1_img2.setImage("current", determineNextSymbol());
        r2_img2.setImage("current", determineNextSymbol());
        r3_img2.setImage("current", determineNextSymbol());
        r1_img3.setImage("current", determineNextSymbol());
        r2_img3.setImage("current", determineNextSymbol());
        r3_img3.setImage("current", determineNextSymbol());
        r1_img1.setImage("next", r1_img1.getCurrValue());
        r2_img1.setImage("next", r2_img1.getCurrValue());
        r3_img1.setImage("next", r3_img1.getCurrValue());
        r1_img2.setImage("next", r1_img2.getCurrValue());
        r2_img2.setImage("next", r2_img2.getCurrValue());
        r3_img2.setImage("next", r3_img2.getCurrValue());
        r1_img3.setImage("next", r1_img3.getCurrValue());
        r2_img3.setImage("next", r2_img3.getCurrValue());
        r3_img3.setImage("next", r3_img3.getCurrValue());
    } //set images on Create

    private void pickNextR1Image() {
        r1_img1.setImage("next", determineNextSymbol());
    }
    private void pickNextR2Image() {
        r2_img1.setImage("next", determineNextSymbol());
    }
    private void pickNextR3Image() {
        r3_img1.setImage("next", determineNextSymbol());
    }

    private int determineNextSymbol() {
        /*
        Images will be determined in 3 pools:
            - Suits         (50%)
            - Face Cards    (35%)
            - Wilds         (15%)
        After pool has been determined, one of the images in that pool is randomly selected (even rates)
         */
        int i = new Random().nextInt(99);
        if (i < 35) {
            //35% chance to get face card
            i = new Random().nextInt(4);
        } else if (i < 85) {
            //50% chance to get suit
            i = 5 + (new Random().nextInt(3));
        } else {
            i = 9;
        }
        return i;
    } //set symbol for each position

    @Override
    public void eventEnd(int result, int count) {

    } // what to do when reels stop spinning

    private void openDealerLogin() {
        Intent intent = new Intent(this, DealerLogin.class);
        startActivity(intent);
    }

    private void setCreditBalance() {
        txt_credit_balance.setText(String.valueOf(Common.SCORE));
    }

    public void cycleLineBet(View view) {
        line_bet_up.setVisibility(View.INVISIBLE);
        line_bet_down.setVisibility(View.VISIBLE);
        (new Handler()).postDelayed(this::cycleLineBet, BTN_DELAY);
    }
    public Runnable cycleLineBet() {
        if (handle_up.getVisibility() == View.VISIBLE && bet_amt < MAX_BET) {
            bet_amt++;
        } else if (handle_up.getVisibility() == View.VISIBLE) {
            bet_amt = 1;
        }
        txt_bet.setText(String.valueOf(bet_amt));
        calcTtlBet();
        line_bet_up.setVisibility(View.VISIBLE);
        line_bet_down.setVisibility(View.INVISIBLE);
        return null;
    }

    public void cycleLineNumb(View view) {
        line_select_up.setVisibility(View.INVISIBLE);
        line_select_down.setVisibility(View.VISIBLE);
        (new Handler()).postDelayed(this::cycleLineNumb, BTN_DELAY);
    }
    public Runnable cycleLineNumb() {
        if (handle_up.getVisibility() == View.VISIBLE && line_bet < (MAX_LINES - 1)) {
            line_bet += 2;
        } else if (handle_up.getVisibility() == View.VISIBLE) {
            line_bet = 1;
        }
        txt_line_num.setText(String.valueOf(line_bet));
        calcTtlBet();
        line_select_up.setVisibility(View.VISIBLE);
        line_select_down.setVisibility(View.INVISIBLE);
        return null;
    }

    private void calcTtlBet() {
        ttl_bet = bet_amt * line_bet;
        txt_bet_ttl.setText(String.valueOf(ttl_bet));
    }

}