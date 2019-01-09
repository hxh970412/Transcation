package com.sheinthuaung.transcation;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;

public class TranscationOneActivity extends AppCompatActivity {

    private static final String TAG = "TranscationOneActivity";

    private FirebaseAnalytics mFirebaseAnalytics;

    private Button toSend;
    private EditText ammount, targetAcc, passsWord;

    String Cpassword;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transcation_one);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ammount = findViewById(R.id.ammount);
        targetAcc = findViewById(R.id.account_no1);
        passsWord = findViewById(R.id.password);

        final Intent intent1 = getIntent();
        Cpassword = intent1.getStringExtra("Ppassword");


        toSend = findViewById(R.id.toSend);
        toSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ammountNum = ammount.getText().toString();
                String accountNam = targetAcc.getText().toString();
                if (TextUtils.isEmpty(targetAcc.getText()) || TextUtils.isEmpty(ammount.getText()) || TextUtils.isEmpty(passsWord.getText())) {
                    Toast.makeText(TranscationOneActivity.this, "You must enter the target account", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(TranscationOneActivity.this, TranscationTwoActivity.class);
                    intent.putExtra("ammountNum", ammountNum);
                    intent.putExtra("ToaccountNum", accountNam);
                    intent.putExtra("FromaccountNum", intent1.getStringExtra("accountNum"));
                    intent.putExtra("PfromBalance", intent1.getStringExtra("fromBalance"));
                    startActivity(intent);
                }
            }
        });


    }


}
