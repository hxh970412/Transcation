package com.sheinthuaung.transcation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

public class TranscationSuccessActivity extends AppCompatActivity {

    TextView random;

    String fromAccount;
    String toAccount;
    String transBalance;


    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myAccount = database.getReference();

    DatabaseReference databaseAccount;

    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transcation_success);
        setTitle("Transaction");

        back = findViewById(R.id.backhome);

        Intent intent2 = getIntent();
        fromAccount = intent2.getStringExtra("Fromaccout");

        databaseAccount = FirebaseDatabase.getInstance().getReference("TransferHistory");

        StringRandom test = new StringRandom();
        random = findViewById(R.id.randomTran);
        random.setText(test.getStringRandom(12));
        addTranshist(test.getStringRandom(12));

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TranscationSuccessActivity.this, HomeActivity.class);
                intent.putExtra("accountNum", fromAccount);
                startActivity(intent);
            }
        });

    }

    public class StringRandom {

        public String getStringRandom(int length) {

            String val = "";
            Random random = new Random();

            for (int i = 0; i < length; i++) {

                String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
                if ("char".equalsIgnoreCase(charOrNum)) {
                    int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                    val += (char) (random.nextInt(26) + temp);
                } else if ("num".equalsIgnoreCase(charOrNum)) {
                    val += String.valueOf(random.nextInt(10));
                }
            }
            return val;
        }
    }

    private void addTranshist(String tranNum) {

        Intent intent1 = getIntent();
        String fromAcc = intent1.getStringExtra("Fromaccout");
        String toAcc = intent1.getStringExtra("toaccout");
        String transBa = intent1.getStringExtra("transamm");

        String id = tranNum;

        transhist transhist = new transhist(transBa, fromAcc, toAcc, id);

        databaseAccount.child(id).setValue(transhist);
    }
}
