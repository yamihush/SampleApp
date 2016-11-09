package com.example.logeshwa.projonesql;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by logeshwa on 10/16/2016.
 */
public class TableManipulationActivity extends Activity {

    EditText etFirstname;
    EditText etLastname;
    EditText etEmail;

    Button btnDML;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.table_manipulation);
        getAllWidgets();
        bindWidgetsWithEvent();
        checkForRequest();
    }

    private void getAllWidgets() {
        etFirstname = (EditText) findViewById(R.id.etFirstName);
        etLastname = (EditText) findViewById(R.id.etLastname);
        etEmail = (EditText) findViewById(R.id.etEmail);
        btnDML = (Button) findViewById(R.id.btnDML);
    }

    private void bindWidgetsWithEvent() {
        btnDML.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick();
            }
        });
    }

    private void onButtonClick() {
        if (etFirstname.getText().toString().equals("") || etLastname.getText().toString().equals("") || etEmail.getText().toString().equals("") ) {
            Toast.makeText(getApplicationContext(), "Add All Fields", Toast.LENGTH_LONG).show();
        } else {
            Intent intent = new Intent();
            intent.putExtra(Constants.FIRST_NAME, etFirstname.getText().toString());
            intent.putExtra(Constants.LAST_NAME, etLastname.getText().toString());
            intent.putExtra(Constants.EMAIL_ADD, etEmail.getText().toString());
            setResult(RESULT_OK, intent);
            finish();
        }
    }


    private void checkForRequest() {
        String request = getIntent().getExtras().get(Constants.DML_TYPE).toString();
        if (request.equals(Constants.UPDATE)) {
            btnDML.setText(Constants.UPDATE);
            etFirstname.setText(getIntent().getExtras().get(Constants.FIRST_NAME).toString());
            etLastname.setText(getIntent().getExtras().get(Constants.LAST_NAME).toString());
            etEmail.setText(getIntent().getExtras().get(Constants.EMAIL_ADD).toString());
        } else {
            btnDML.setText(Constants.INSERT);
        }
    }

}
