package com.ahmedradwan.mylrmc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FluActivity extends AppCompatActivity {

    Button backBtn, yesBtn, noBtn;
    TextView quizText;
    String[] quizList = {"1. Are you over 6 months of age?", "2. Have you had a severe allergic reaction to the flu shot prior?", "3. Do you have a serious egg allergy?", "4. Are you over 60 years old?"};
    int yesNum = 0;
    int loopNum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flu);

        backBtn = findViewById(R.id.backBtn);
        quizText = findViewById(R.id.questionText);
        yesBtn = findViewById(R.id.yesBtn);
        noBtn = findViewById(R.id.noBtn);
        quizText.setText(quizList[0]);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(loopNum < 3){
                    if(loopNum == 1 || loopNum == 2){
                        yesNum++;

                    }
                    loopNum++;
                    quizText.setText(quizList[loopNum]);

                }else{
                    if(loopNum < 4){
                        if(yesNum == 0 ){
                            quizText.setText("You should get a flue vaccine");
                        }else {
                            quizText.setText("You should not get a flue vaccine");
                        }

                    }
                }
            }
        });

        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(loopNum < 3){
                    if(loopNum == 0){
                        yesNum++;
                    }
                    loopNum++;
                    quizText.setText(quizList[loopNum]);


                }else{
                    if(loopNum < 4){
                        if(yesNum != 0 ){
                            quizText.setText("You should not get a flue vaccine");
                        }else {
                            quizText.setText("You should get a flue vaccine");
                        }
                    }
                }
            }
        });
    }
}
