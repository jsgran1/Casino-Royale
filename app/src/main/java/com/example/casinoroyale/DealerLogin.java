package com.example.casinoroyale;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;
import java.util.Objects;

public class DealerLogin extends AppCompatActivity {

    TextInputEditText username;
    TextInputEditText password;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dealer_login);
        username = findViewById(R.id.txt_username);
        password = findViewById(R.id.txt_password);
        login = findViewById(R.id.btn_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dealerLogin();
            }
        });
    }

    public void dealerLogin() {
        if (Objects.requireNonNull(username.getText()).toString().equals("Dealer") &&
                Objects.requireNonNull(password.getText()).toString().equals("Bhole")) {
            username.setText("");
            password.setText("");
            Intent intent = new Intent(this, DealerManagement.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Incorrect login credentials", Toast.LENGTH_SHORT).show();
        }
    }

}
