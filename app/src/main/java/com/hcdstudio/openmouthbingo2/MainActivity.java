package com.hcdstudio.openmouthbingo2;

import android.graphics.Color;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button submitBtn;
    TextView title;
    TextView subtitle;
    EditText et;
    Boolean gaming = false;
    int rand;
    int smallRange;
    int bigRange;
    int guessTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        submitBtn = (Button)findViewById(R.id.button);
        title = (TextView)findViewById(R.id .mainTitle);
        et = (EditText)findViewById(R.id.editText);
        et.setAlpha(0);
        subtitle = (TextView)findViewById(R.id.subTitle);
        subtitle.setAlpha(0);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(gaming){
                    int i;
                    try{
                        i = Integer.parseInt(String.valueOf(et.getText()));
                        if(i<=smallRange||i>=bigRange){
                            throw new NumberFormatException();
                        }else {
                            title.setTextColor(Color.GRAY);
                            guessTime++;
                            if(i>rand){
                                title.setText(smallRange+"至"+i);
                                bigRange = i;
                                et.setText("");
                            }else if(i<rand){
                                title.setText(i+"至"+bigRange);
                                smallRange = i;
                                et.setText("");
                            }else if(i==rand){
                                title.setText("开口中!");
                                title.setTextColor(Color.RED);
                                et.setTextColor(Color.RED);
                                gaming = false;
                                submitBtn.setText("重来");
                                subtitle.setAlpha(1);
                                subtitle.setText("猜测"+guessTime+"次");
                            }

                        }
                    }catch (NumberFormatException e){
                        title.setTextColor(Color.RED);
                        et.setText("");
                    }

                }else{
                    title.setText("1至100");
                    title.setTextColor(Color.GRAY);
                    submitBtn.setText("提交");
                    et.setText("");
                    et.setTextColor(Color.BLACK);
                    et.setAlpha(1);
                    gaming = true;
                    rand = (int)(Math.random()*98)+2;
                    Log.e("rand",rand+"");
                    smallRange = 1;
                    bigRange = 100;
                    guessTime = 0;
                    subtitle.setAlpha(0);
                }

            }
        });
    }
}
