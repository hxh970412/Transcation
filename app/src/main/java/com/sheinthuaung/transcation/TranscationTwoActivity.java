package com.sheinthuaung.transcation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class TranscationTwoActivity extends AppCompatActivity {

    TextView targetAcc;
    TextView ammount;
    Button Continue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transcation_two);
        setTitle("Transaction Check");

        targetAcc = (TextView) findViewById(R.id.account_no2);
        ammount = (TextView) findViewById(R.id.ammount2);


        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        String accountNum = intent.getStringExtra("accountNum");
        targetAcc.setText(accountNum);

        String ammountNum = intent.getStringExtra("ammountNum");
        ammount.setText(ammountNum);

        Continue = (Button) findViewById(R.id.Continue);
        Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(TranscationTwoActivity.this, TranscationSuccessActivity.class);
                startActivity(intent1);
            }
        });
    }

}
