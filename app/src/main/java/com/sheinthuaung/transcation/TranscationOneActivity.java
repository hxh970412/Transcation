package com.sheinthuaung.transcation;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.Tag;

public class TranscationOneActivity extends AppCompatActivity {

    private static final String TAG = "TranscationOneActivity";

    private FirebaseAnalytics mFirebaseAnalytics;

    private Button toSend;
    private EditText ammount, targetAcc, passsWord;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transcation_one);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ammount = (EditText) findViewById(R.id.ammount);
        targetAcc = (EditText) findViewById(R.id.account_no1);
        passsWord = (EditText) findViewById(R.id.password);

        toSend = (Button) findViewById(R.id.toSend);
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
                    intent.putExtra("accountNum", accountNam);
                    startActivity(intent);
                }
            }
        });


    }

    public void basicReadWrite()
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");
        myRef.setValue("Hello World");

        DatabaseReference mDatabase;

        mDatabase = FirebaseDatabase.getInstance().getReference();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                Log.d( TAG,"Value is:" + value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(TAG, "Failed to read value.", databaseError.toException());
            }
        });
    }

    public class account
    {
        public int accountNum;
        public int accountBalance;
        public String acccountPass;

        public account(){
        }

        public account (int accountNum, int accountBalance, String acccountPass )
        {
            this.acccountPass = acccountPass;
            this.accountBalance = accountBalance;
            this.accountNum = accountNum;
        }

    }

    public class transhist{
        public int transAmount;
        public int transFrom;
        public int transNum;
        public int transTo;

        public transhist(){ }

        public transhist (int transAmount, int transFrom, int transNum, int transTo){
            this.transAmount = transAmount;
            this.transFrom = transFrom;
            this.transNum = transNum;
            this.transTo = transTo;
        }

    }


}
