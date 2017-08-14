package com.example.korsn.mojkuvar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Korsn on 9.8.2017..
 */

public class DBHelper extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "cs330.sqlite";
    public static final String TABLE_NAME = "recepti";
    public static final String RECEPTI_ID = "id";
    public static final String RECEPTI_IME = "ime";
    public static final String RECEPTI_VRSTA = "vrsta";
    public static final String RECEPTI_SASTOJCI = "sastojci";
    public static final String RECEPTI_OPIS = "opis";

    private HashMap hash;

    public DBHelper(Context context) {

        super(context, DATABASE_NAME, null, 3 );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL( "create table recepti " +
                "(id integer primary key autoincrement, ime text, vrsta text, sastojci text, opis text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS recepti");
        onCreate(db);
    }

    public boolean addRecept(String ime,String vrsta,String sastojci,String opis){
        /*,*/
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues receptiSadrzaj = new ContentValues();
        receptiSadrzaj.put("ime",ime);
        receptiSadrzaj.put("vrsta", vrsta);
        receptiSadrzaj.put("sastojci",sastojci);
        receptiSadrzaj.put("opis",opis);

        db.insert("recepti", null, receptiSadrzaj);
        db.close();
        return true;
    }

    public boolean updateRecept(Integer id, String ime,String vrsta,String sastojci,String opis){

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues receptiSadrzaj = new ContentValues();
        receptiSadrzaj.put("ime",ime);
        receptiSadrzaj.put("vrsta", vrsta);
        receptiSadrzaj.put("sastojci",sastojci);
        receptiSadrzaj.put("opis",opis);

        db.update("recepti", receptiSadrzaj, "id = ?", new String[]{Integer.toString(id)});
        return  true;
    }

    public Integer deleteRecept(Integer id) {
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete("recepti", "id = ?",new String[]{Integer.toString(id)});
    }

    public Cursor getData(int receptId) {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res = db.rawQuery("Select * from recepti where id = " + receptId + "", null);
        return  res;
    }

  /*  public ArrayList<String> getAllRecepti() {
        ArrayList<String> arraylist= new ArrayList<String>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor c = db.rawQuery("Select * from recepti",null);

       if (c.moveToFirst()) {
            do {
                arraylist.add(c.getString(c.getColumnIndex(RECEPTI_ID)));


            }
            while (c.moveToNext());
        }
    return arraylist;


    }*/


  public ArrayList<Recept> getAllRecepti() {

      String sql = "select * from recepti where vrsta='slano' ";
      SQLiteDatabase db=this.getReadableDatabase();
      ArrayList<Recept> arraylist= new ArrayList<Recept>();
      Cursor c = db.rawQuery(sql, null);

      if (c.moveToFirst()) {
          do {
              int id = Integer.parseInt(c.getString(0));
              String ime = c.getString(1);
              String vrsta = c.getString(2);
              String sastojci = c.getString(3);
              String opis = c.getString(4);
              arraylist.add(new Recept(id,ime,vrsta,sastojci,opis));
          }
            while (c.moveToNext());
      }
      c.close();
      return  arraylist;
  }

    public ArrayList<Recept> getAllRecepti2() {

        String sql = "select * from recepti where vrsta='slatko' ";
        SQLiteDatabase db=this.getReadableDatabase();
        ArrayList<Recept> arraylist= new ArrayList<Recept>();
        Cursor c = db.rawQuery(sql, null);

        if (c.moveToFirst()) {
            do {
                int id = Integer.parseInt(c.getString(0));
                String ime = c.getString(1);
                String vrsta = c.getString(2);
                String sastojci = c.getString(3);
                String opis = c.getString(4);
                arraylist.add(new Recept(id,ime,vrsta,sastojci,opis));
            }
            while (c.moveToNext());
        }
        c.close();
        return  arraylist;
    }


}
