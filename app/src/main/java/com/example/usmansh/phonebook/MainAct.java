package com.example.usmansh.phonebook;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainAct extends AppCompatActivity {


    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 10;
    ArrayList<person> perslist = new ArrayList<person>();
    Intent intt;
    int posi;
    int editposition;
    EditText srchtext;
    ListView Gridlist;
    //adaptor apr = new adaptor(this, perslist);
    DBHelper db = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        srchtext = (EditText) findViewById(R.id.SrcText);
        final Button adbt = (Button) findViewById(R.id.AddBt);
        final Button dialerbt = (Button) findViewById(R.id.dialerBt);
        final Button readperm = (Button) findViewById(R.id.readperm);
        Gridlist = (ListView) findViewById(R.id.gridv);

        readperm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                manageBtnClick();
            }
        });


        db = new DBHelper(this);
        perslist = (ArrayList<person>) db.getAllContact();
        adaptor adap = new adaptor(this, perslist);
        Gridlist.setAdapter(adap);


        //Searching Function coding
        srchtext.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                //When user changed the Text
                ArrayList<person> secondarr = new ArrayList<person>();

                for (int i = 0; i < perslist.size(); i++) {

                    person per = perslist.get(i);

                    if (per.getName().contains(cs))

                    {
                        secondarr.add(per);
                    }

                }
                Gridlist.setAdapter(new adaptor(MainAct.this, secondarr));

            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
            }
        });


        //Add new contact button
        adbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intt = new Intent(adbt.getContext(), adperson.class);
                startActivityForResult(intt, 1);

            }
        });


        //open Dialer Button
        dialerbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent dial = new Intent(dialerbt.getContext(), dialer.class);
                Toast.makeText(MainAct.this, "going to dialer", Toast.LENGTH_SHORT).show();
                startActivity(dial);
            }
        });


        //click on row dialog box will open
        Gridlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {


                posi = position;
                show_Dialog(view, position);

            }
        });

    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //getting result of Newly Added Person
        if (requestCode == 1 && resultCode == 1 && data != null) {

            String N = data.getStringExtra("name");
            String P = data.getStringExtra("phone");

            person per = new person();
            per.setName(N);
            per.setPhoneno(P);

            db.insertContact(per);

            //perslist.add(per);
            perslist = (ArrayList<person>) db.getAllContact();
            adaptor apr = new adaptor(this, perslist);
            Gridlist.setAdapter(apr);

        }

        //getting result of Edit person information
        else if (requestCode == 2 && resultCode == 2 && data != null) {


            String N = data.getStringExtra("name");
            String P = data.getStringExtra("phone");

            person per = perslist.get(editposition);
            per.setName(N);
            per.setPhoneno(P);

            db.updatedata(per.getId(), per);
            perslist.add(per);
            //perslist = (ArrayList<person>) db.getAllContact();
            adaptor apr = new adaptor(this, perslist);

            Gridlist.setAdapter(apr);

        }

    }


    //Dialog Box Function and its Coding
    public void show_Dialog(View v, final int i) {

        final Dialog dialog = new Dialog(v.getContext());
        dialog.setContentView(R.layout.dialog);

        //getting specific person object the user click on that
        final person per = perslist.get(i);

        //linking with dialog.xml components
        final TextView namev = (TextView) dialog.findViewById(R.id.namev);
        final TextView phonev = (TextView) dialog.findViewById(R.id.phonev);
        final Button editbt = (Button) dialog.findViewById(R.id.editbt);
        final Button deletebt = (Button) dialog.findViewById(R.id.deletebt);
        final Button call = (Button) dialog.findViewById(R.id.call);
        final Button msg = (Button) dialog.findViewById(R.id.msg);

        namev.setText(per.getName());
        phonev.setText(per.getPhoneno());

        deletebt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Integer isdeleted = db.deletedata(per.getId());

                if (isdeleted > 0)

                {
                    Toast.makeText(MainAct.this, "Data deleted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainAct.this, "Data is not Deleted", Toast.LENGTH_SHORT).show();
                }
                perslist.remove(i);
                Gridlist.setAdapter(new adaptor(deletebt.getContext(), perslist));
                dialog.dismiss();

            }
        });


        editbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editposition = i;

                Toast.makeText(MainAct.this, "going to edit person act", Toast.LENGTH_SHORT).show();
                intt = new Intent(MainAct.this, editperson.class);
                startActivityForResult(intt, 2);
                dialog.dismiss();
            }
        });


        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent call = new Intent(Intent.ACTION_CALL);
                call.setData(Uri.parse("tel:" + per.getPhoneno()));
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(call);
            }
        });


        msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent sms = new Intent(Intent.ACTION_SEND);
                sms.setType("text/plain");

                startActivity(sms);
                /*SmsManager sms = SmsManager.getDefault();
                  sms.sendTextMessage(per.getPhoneno(),null,"Message",null,null);*/
            }
        });

        dialog.show();

    }//dialog function end


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);



    }




    private void manageBtnClick() {

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(this,
                    new String [] {Manifest.permission.READ_CONTACTS,Manifest.permission.CALL_PHONE,Manifest.permission.SEND_SMS},
                    MY_PERMISSIONS_REQUEST_READ_CONTACTS);
        }
        else{
            readContacts();
        }
    }


    private void readContacts() {

        Toast.makeText(this, "Read Contacts features call", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {

            case MY_PERMISSIONS_REQUEST_READ_CONTACTS:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    readContacts();
                } else {

                    if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_CONTACTS)) {

                        new AlertDialog.Builder(this).setTitle("Read Contacts permission").
                                setMessage("You need to grant read contacts permission to use read" +
                                        "contacts feature. Retry and grant it..!").show();
                    } else {

                        new AlertDialog.Builder(this).setTitle("Read Contacts permission denied").
                                setMessage("You denied read contacts permission. So, the feature will be disabled." +
                                        "To Enable it, go on settings and grant read contacts for the application");
                    }

                }

                break;

        }


    }
    
}



