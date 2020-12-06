package com.bankaks.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.bankaks.R;
import com.bankaks.utils.Utils;

public class MainActivity extends AppCompatActivity {

    Button btnProceed;
    Spinner options;
    String option_position="1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnProceed=findViewById(R.id.btnProceed);
        options=findViewById(R.id.options);

        btnProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Utils.hasNetwork(MainActivity.this)){
                   Intent intent =new Intent(MainActivity.this,DynamicActivity.class);
                   intent.putExtra("type",option_position);
                   startActivity(intent);

                }
                else{
                    Toast.makeText(MainActivity.this, "NO INTERNET CONNECTION . PLEASE TRY AGAIN!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        options.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                options.getSelectedItem().toString();
                option_position= String.valueOf(position+1);
                //Toast.makeText(MainActivity.this,  options.getSelectedItem().toString() + " position : "+option_position , Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}