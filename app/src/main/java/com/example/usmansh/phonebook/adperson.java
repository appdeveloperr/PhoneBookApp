package com.example.usmansh.phonebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class adperson extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adperson);

        final EditText name = (EditText)findViewById(R.id.name);
        final  EditText phone = (EditText)findViewById(R.id.phone);
        final Button   add = (Button)findViewById(R.id.add);
        final Button   cancel = (Button)findViewById(R.id.Cancel);



        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Name = name.getText().toString();
                String Phone = phone.getText().toString();

                Intent intt = new Intent();

                intt.putExtra("name",Name);
                intt.putExtra("phone",Phone);

                setResult(1,intt);
                Toast.makeText(getApplicationContext(), "In Add person", Toast.LENGTH_SHORT).show();
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
