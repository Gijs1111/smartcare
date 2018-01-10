package com.example.gijsdewert.smartcare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener{

    //Fields
    private Intent currentIntent;
    private Intent assistentieIntent;
    private Intent rushAssitanceIntent;
    private Intent chatIntent;

    //Controls
    private TextView tvEmailDisplay;
    private Button btnAssistentie;
    private Button btnSpoedoproep;
    private Button btnChat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Init
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        currentIntent = getIntent();

        //Set eventhandlers for controls
        tvEmailDisplay = findViewById(R.id.tvEmailDisplay);
        btnAssistentie = findViewById(R.id.btnAssistentie);
        btnSpoedoproep = findViewById(R.id.btnSpoed);
        btnChat = findViewById(R.id.btnChat);
        btnAssistentie.setOnClickListener(this);
        btnSpoedoproep.setOnClickListener(this);
        btnChat.setOnClickListener(this);

        tvEmailDisplay.setText(currentIntent.getStringExtra("email"));
    }

    @Override
    public void onClick(View view) {

        //Get sender of the button
        switch(view.getId()) {
            case R.id.btnAssistentie:
                    assistentieIntent = new Intent(MenuActivity.this, AssistanceActivity.class);
                    MenuActivity.this.startActivity(assistentieIntent);
                break;
            case R.id.btnSpoed:
                    rushAssitanceIntent = new Intent(MenuActivity.this, RushAssistanceActivity.class);
                    MenuActivity.this.startActivity(rushAssitanceIntent);
                break;
            case R.id.btnChat:
                    chatIntent = new Intent(MenuActivity.this, ChatActivity.class);
                    MenuActivity.this.startActivity(chatIntent);
        }
    }
}
