package com.example.usmansh.phonebook;


import android.app.Dialog;
import android.content.Context;
import android.content.Entity;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;

import static android.R.attr.start;

/**
 * Created by Usman Sh on 11/30/2016.
 */

public class adaptor extends BaseAdapter{

  ArrayList<person> personlist;

    Context contxt= null;
    person per = null;


    public  adaptor (Context c, ArrayList<person> per)

    {

        this.personlist = per;
        this.contxt =c;
    }


    @Override
    public View getView(int i, View view, ViewGroup parent)

    {

       // Toast.makeText(contxt, "In Adaptor", Toast.LENGTH_SHORT).show();

        View V = new View (contxt);

        LayoutInflater inflat= (LayoutInflater) contxt.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        if (view==null){
            V = inflat.inflate(R.layout.listrow,null);
        } else{
            V = view;
        }


        final TextView nameV = (TextView) V.findViewById(R.id.nameV);
        final TextView phoneV = (TextView)V.findViewById(R.id.phoneV);
        final ImageView imgV = ( ImageView) V.findViewById(R.id.imgV);

         per = personlist.get(i);

        nameV.setText(per.getName());
        phoneV.setText(per.getPhoneno());




        return V;



    }

    @Override
    public int getCount() {
        return  personlist.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }



}
