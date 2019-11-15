package com.example.usmansh.phonebook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {


    //final static String DB_NAME = "PhDbb.db";
    public static final String DATA_BASENAME = "student1.db";
    public static final String TABLE_NAME = "student_table";
    public static final String col_1 = "NAME";
    public static final String col_2 = "PHONE";
    public static final String col_3 = "ID";
    Context context = null;



        //Constructor
        public DBHelper (Context contxt)
        {
            super(contxt,DATA_BASENAME,null,1);
            this.context= contxt;
        }



        @Override
        public void onCreate(SQLiteDatabase db) {


            //Creating Table first time
            try{

                db.execSQL("create table "+TABLE_NAME+" ( ID INTEGER PRIMARY KEY AUTOINCREMENT "+","+col_1+","+col_2+" )");
                Toast.makeText(context, "Table created", Toast.LENGTH_SHORT).show();

            }

            catch (Exception ex)
            {
                Toast.makeText(context, "Table not created", Toast.LENGTH_SHORT).show();
            }
        }//Ending oncreat function





        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            db.execSQL(" DROP TABLE "+TABLE_NAME);
            onCreate(db);

        }




        public void insertContact(person c) {
            try {
                SQLiteDatabase db = this.getWritableDatabase();
               // String qry = "insert into personn(name,phone)" +
                 //            "values("+"'" + c.getName() +"','" + c.getPhoneno() + "')";

                ContentValues contentValues  = new ContentValues();

                contentValues.put(" ID ",c.getId());
                contentValues.put(col_1,c.getName());
                contentValues.put(col_2,c.getPhoneno());


                db.insert(TABLE_NAME,null,contentValues);

                Toast.makeText(context, "insert ho gya", Toast.LENGTH_SHORT).show();
            }catch(Exception ex){
                Toast.makeText(context, "insert nahi ho rha", Toast.LENGTH_SHORT).show();

            }
        }






        public List<person> getAllContact(){

            person per=null;
            List<person> lst= new ArrayList<person>();

            try {

                SQLiteDatabase db = this.getWritableDatabase();
                String query = "select * from "+TABLE_NAME;

                Cursor r = db.rawQuery(query, null);

                //r.moveToFirst();

                while(r.moveToNext())

                {
                    per = new person();

                    per.setId(r.getString(0));
                    per.setName(r.getString(1));
                    per.setPhoneno(r.getString(2));

                    lst.add(per);


                }
                r.close();

            }catch(Exception ex){


            }


            return lst;

        }




    //delete Function

    public int deletedata ( String id )
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,"ID = ? ",new String [] {id});


    }




    //Update or Edit function



    public boolean updatedata(String id,person p)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(col_1,p.getName());
        contentValues.put(col_2,p.getPhoneno());

        final int update = db.update(TABLE_NAME, contentValues, "ID = ?",new String[] {id});

        return  true;

    }




        /*public List<person> getNamedContacts(String name){
            person per=null;
            List<person> lst= new ArrayList<person>();
            try {
                SQLiteDatabase db = this.getWritableDatabase();
                String query = "select * from personn where name='" + name + "'";


                Cursor r = db.rawQuery(query, null);
                r.moveToFirst();
                while(r.isAfterLast()==false) {
                    per= new person();

                    per.setName(r.getString(0));
                    per.setPhoneno(r.getString(1));

                    lst.add(per);
                    r.moveToNext();

                }
                r.close();

            }catch(Exception ex){}

            return lst;
        }*/




 }


