package com.example.usmansh.phonebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class dialer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialer);

        final TextView shownum = (TextView)findViewById(R.id.dialnum);

        final Button b1 = (Button)findViewById(R.id.b1);
        final Button b2 = (Button)findViewById(R.id.b2);
        final Button b3 = (Button)findViewById(R.id.b3);
        final Button b4 = (Button)findViewById(R.id.b4);
        final Button b5 = (Button)findViewById(R.id.b5);
        final Button b6 = (Button)findViewById(R.id.b6);
        final Button b7 = (Button)findViewById(R.id.b7);
        final Button b8 = (Button)findViewById(R.id.b8);
        final Button b9 = (Button)findViewById(R.id.b9);
        final Button b0 = (Button)findViewById(R.id.b0);
        final Button bstr = (Button)findViewById(R.id.bstr);
        final Button bhash = (Button)findViewById(R.id.bhash);
        final Button bcall = (Button)findViewById(R.id.call);
        final Button bmsg = (Button)findViewById(R.id.msg);
        final Button bc = (Button)findViewById(R.id.btC);
        final Button bca = (Button)findViewById(R.id. btCA);




        b1.setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View v)
            {


                shownum.setText(shownum.getText()+"1");
            }
        });


        b2.setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View v)
            {


                shownum.setText(shownum.getText()+"2");
            }
        });


        b3.setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View v)
            {


                shownum.setText(shownum.getText()+"3");
            }
        });


        b4.setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View v)
            {


                shownum.setText(shownum.getText()+"4");
            }
        });


        b5.setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View v)
            {


                shownum.setText(shownum.getText()+"5");
            }
        });


        b6.setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View v)
            {


                shownum.setText(shownum.getText()+"6");
            }
        });


        b7.setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View v)
            {


                shownum.setText(shownum.getText()+"7");
            }
        });


        b8.setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View v)
            {


                shownum.setText(shownum.getText()+"8");
            }
        });

        b9.setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View v)
            {


                shownum.setText(shownum.getText()+"9");
            }
        });

        b0.setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View v)
            {


                shownum.setText(shownum.getText()+"0");
            }
        });


        bstr.setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View v)
            {


                shownum.setText(shownum.getText()+"*");
            }
        });


        bhash.setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View v)
            {


                shownum.setText(shownum.getText()+"#");
            }
        });




        bc.setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View v)
            {
                String clr = shownum.getText().toString();

                clr = clr.substring(0,clr.length()-1);

                shownum.setText(clr);

            }
        });



        bca.setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View v)
            {

                shownum.setText("");

            }
        });


        /*bcall.setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View v)
            {

              ///calling Action manager to call on entered number

            }
        });


        bmsg.setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View v)
            {

                //calling Action manager to send Msg on entered number

                Intent n = new Intent (Intent.ACTION_SEND);
                startActivity(n);



            }
        });*/








    }
}
