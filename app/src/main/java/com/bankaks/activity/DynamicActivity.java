package com.bankaks.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.bankaks.R;
import com.bankaks.utils.Constants;
import com.bankaks.utils.Utils;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.bankaks.utils.JsonParser.showViewsAccordingtoType;

public class DynamicActivity extends AppCompatActivity {

    TextView screen_title;
    TextView txtScnumber,txtConsumerid,txtMonth,txtEmail,txtPhone,txtConsumername;
    LinearLayout sc_number,consumer_id,month,consumer_email,consumer_phone_number,consumer_name;
    AppCompatEditText edit_sc_number,edit_consumer_id,edit_consumer_email,
            edit_consumer_phone_number,edit_consumer_name;

    Spinner spinner_month;
    String type="0",regex_email="",regex_phone="";
    ArrayList<String> arrayListMonth= new ArrayList<>();
    Button btnProceed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle != null) {
            type = bundle.getString("type");
        }

        initViews();
        callApi();

    }

    private void callApi() {

        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        AndroidNetworking.get(Constants.BASE_URL+"task/{type}")
                .addPathParameter("type", type)
                .setPriority(Priority.HIGH)
                .build()
                .getAsString(new StringRequestListener() {

                    @Override
                    public void onResponse(String response) {
                        progressDialog.hide();
                       // Toast.makeText(DynamicActivity.this, response, Toast.LENGTH_SHORT).show();

                      // int viewType= showViewsAccordingtoType(type,response);

                        JSONObject jsonObject= null;
                        try {
                            jsonObject = new JSONObject(response);
                            String result= jsonObject.optString("result");
                            JSONObject jsonObject_result= new JSONObject(result);
                            String screen_title_str=jsonObject_result.optString("screen_title");
                            screen_title_str= Utils.nullCheck(screen_title_str);
                            screen_title.setText(screen_title_str);
                            screen_title.setVisibility(View.VISIBLE);

                            JSONArray jsonArrayFields=jsonObject_result.getJSONArray("fields");
                            for(int i=0; i<jsonArrayFields.length();i++) {
                                JSONObject jsonObjectFields = jsonArrayFields.getJSONObject(i);
                                String name = jsonObjectFields.optString("name");
                                String placeholder = jsonObjectFields.optString("placeholder");
                                String hint_text = jsonObjectFields.optString("hint_text");


                                placeholder = Utils.nullCheck(placeholder);
                                hint_text = Utils.nullCheck(hint_text);

                                if (name.equals("sc_no")) {
                                    sc_number.setVisibility(View.VISIBLE);
                                    txtScnumber.setText(placeholder);
                                    edit_sc_number.setHint(hint_text);
                                }

                                if (name.equals("customer_id")) {
                                    consumer_id.setVisibility(View.VISIBLE);
                                    txtConsumerid.setText(placeholder);
                                    edit_consumer_id.setHint(hint_text);
                                }

                                if (name.equals("month_id")) {
                                    month.setVisibility(View.VISIBLE);
                                    txtMonth.setText(placeholder);

                                    JSONObject jsonObject_ui_type = jsonObjectFields.getJSONObject("ui_type");
                                    //Toast.makeText(DynamicActivity.this, ""+jsonObject_ui_type, Toast.LENGTH_SHORT).show();
                                    JSONArray jsonArray_values = jsonObject_ui_type.getJSONArray("values");
                                    for (int j = 0; j < jsonArray_values.length(); j++) {
                                        JSONObject jsonObject_monthname = jsonArray_values.getJSONObject(j);
                                        String month_name = jsonObject_monthname.optString("name");
                                        arrayListMonth.add(month_name);
                                    }
                                    ArrayAdapter<String> adapter =
                                            new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, arrayListMonth);
                                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                                    spinner_month.setAdapter(adapter);
                                }

                                if(name.equals("customer_email")){
                                    consumer_email.setVisibility(View.VISIBLE);
                                    txtEmail.setText(placeholder);
                                    edit_consumer_email.setHint(hint_text);
                                    //regex_email = jsonObjectFields.optString("regex");
                                    regex_email ="^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
                                }

                                if(name.equals("customer_phone")){
                                    consumer_phone_number.setVisibility(View.VISIBLE);
                                    txtPhone.setText(placeholder);
                                    edit_consumer_phone_number.setHint(hint_text);
                                    regex_phone = jsonObjectFields.optString("regex");
                                }

                                if(name.equals("name")){
                                    consumer_name.setVisibility(View.VISIBLE);
                                    txtConsumername.setText(placeholder);
                                    edit_consumer_name.setHint(hint_text);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(ANError anError) {
                        progressDialog.hide();

                    }
                });
    }

    private void initViews() {
        screen_title=findViewById(R.id.screen_title);
        txtScnumber=findViewById(R.id.txtScnumber);
        txtConsumerid=findViewById(R.id.txtConsumerid);
        txtMonth=findViewById(R.id.txtMonth);
        txtEmail=findViewById(R.id.txtEmail);
        txtPhone=findViewById(R.id.txtPhone);
        txtConsumername=findViewById(R.id.txtConsumername);

        sc_number=findViewById(R.id.sc_number);
        consumer_id=findViewById(R.id.consumer_id);
        month=findViewById(R.id.month);
        consumer_email=findViewById(R.id.consumer_email);
        consumer_phone_number=findViewById(R.id.consumer_phone_number);
        consumer_name=findViewById(R.id.consumer_name);

        edit_sc_number=findViewById(R.id.edit_sc_number);
        edit_consumer_id=findViewById(R.id.edit_consumer_id);
        edit_consumer_email=findViewById(R.id.edit_consumer_email);
        edit_consumer_phone_number=findViewById(R.id.edit_consumer_phone_number);
        edit_consumer_name=findViewById(R.id.edit_consumer_name);

        spinner_month=findViewById(R.id.spinner_month);
        btnProceed=findViewById(R.id.btnProceed);
    }

    public void onClickProceed(View view){

        if(edit_sc_number.getText().toString().equals("")||edit_consumer_id.toString().equals("")||
                edit_consumer_phone_number.getText().toString().equals("")||edit_consumer_email.getText().toString().equals("")||
                edit_consumer_name.getText().toString().equals("")){
            Toast.makeText(this, "SOME FIELD(S) ARE BLANK", Toast.LENGTH_SHORT).show();
        }
        
        if(!(edit_consumer_email.getText().toString().matches(regex_email))){
            Toast.makeText(this, "WRONG EMAIL ADDRESS", Toast.LENGTH_SHORT).show();
    }
        if(!edit_consumer_phone_number.getText().toString().matches(regex_phone)){
            Toast.makeText(this, "WRONG PHONE NUMBER", Toast.LENGTH_SHORT).show();
        }


        else{
            startActivity(new Intent(DynamicActivity.this,Success.class));
        }
    }
}