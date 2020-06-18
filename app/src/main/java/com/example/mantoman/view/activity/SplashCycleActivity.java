package com.example.mantoman.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mantoman.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SplashCycleActivity extends AppCompatActivity {

    @BindView(R.id.checkBox_scroll)
    CheckBox checkBoxScroll;
    @BindView(R.id.next_BTN_scroll)
    Button nextBTNScroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_cycle);
        ButterKnife.bind(this);

        nextBTNScroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check()==true){
                    Intent intent = new Intent(SplashCycleActivity.this,AuthCycleActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private Boolean check(){
     if (checkBoxScroll.isChecked()){
         return true;
     }else {
         return false;
     }
    }

}
