package com.bankaks.activity;
/**
 *  This is a Success Page - after the successfull validation of user input fields
 *
 * @author Debashish
 * @version 2020.12.06
 * @since 1.0
 */
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.bankaks.R;

public class Success extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);
    }
}