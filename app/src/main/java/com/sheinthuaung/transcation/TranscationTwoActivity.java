package com.sheinthuaung.transcation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class TranscationTwoActivity extends AppCompatActivity {

    TextView targetAcc;
    TextView ammount;
    Button Continue;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myAccount = database.getReference();

    String ammountNum;
    String ToaccountNum;
    String Faccountnum;
    String Fbalance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transcation_two);
        setTitle("Transaction Check");

        targetAcc = findViewById(R.id.account_no2);
        ammount = findViewById(R.id.ammount2);


        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        ToaccountNum = intent.getStringExtra("ToaccountNum");

        ammountNum = intent.getStringExtra("ammountNum");

        Faccountnum = intent.getStringExtra("FromaccountNum");

        Fbalance = intent.getStringExtra("PfromBalance");

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final String ToaccountBalance = String.valueOf(dataSnapshot.child("Account").child("accounts").child(ToaccountNum).child("accountBalance").getValue());

                final String FtoaccountBalance = String.valueOf(Integer.parseInt(ToaccountBalance) + Integer.parseInt(ammountNum));

                String fFaccountBalance = String.valueOf(Integer.parseInt(Fbalance) - Integer.parseInt(ammountNum));


                Map<String, Object> childUpdates = new HashMap<>();
                childUpdates.put("/Account/" + "/accounts/" + Faccountnum + "/accountBalance", fFaccountBalance);
                childUpdates.put("/Account/" + "/accounts/" + ToaccountNum + "/accountBalance", FtoaccountBalance);

                myAccount.updateChildren(childUpdates);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        myAccount.addValueEventListener(valueEventListener);


        targetAcc.setText(ToaccountNum);


        ammount.setText(ammountNum);

        Continue = findViewById(R.id.Continue);
        Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(TranscationTwoActivity.this, TranscationSuccessActivity.class);
                intent1.putExtra("Fromaccout", Faccountnum);
                intent1.putExtra("toaccout", ToaccountNum);
                intent1.putExtra("transamm", ammountNum);
                startActivity(intent1);
            }
        });
    }

}
