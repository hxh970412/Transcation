package com.sheinthuaung.transcation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Random;

public class TranscationSuccessActivity extends AppCompatActivity {

    TextView random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transcation_success);
        setTitle("Transaction");
        StringRandom test = new StringRandom();
        random = (TextView) findViewById(R.id.randomTran);
        random.setText(test.getStringRandom(12));

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
}
