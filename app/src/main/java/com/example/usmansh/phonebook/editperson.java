package com.example.usmansh.phonebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.usmansh.phonebook.R.id.phone;

public class editperson extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editperson);


        final EditText nmedit = (EditText)findViewById(R.id.nameedit);
        final EditText phedit = (EditText) findViewById(R.id.phedit);
        final Button   update = (Button)findViewById(R.id.update);
        final Button   cancel = (Button) findViewById(R.id.cancell);


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Name = nmedit.getText().toString();
                String Phone = phedit.getText().toString();


                Intent intt = new Intent();

                intt.putExtra("name",Name);
                intt.putExtra("phone",Phone);

                setResult(2,intt);
                Toast.makeText(getApplicationContext(), "In Edit person", Toast.LENGTH_SHORT).show();
                finish();

            }
        });


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
