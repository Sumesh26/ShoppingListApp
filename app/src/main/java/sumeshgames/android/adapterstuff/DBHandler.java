package sumeshgames.android.adapterstuff;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import static android.R.attr.name;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

/**
 * Created by Sumesh on 24-10-2016.
 */

public class DBHandler extends SQLiteOpenHelper {
    private static final int db_version=3;
    private static final  String db_name="ItemManager";
    private static final String table_name="Items";
    private static final String KEY_ID="id";
    private static final String KEY_NAME="name";
    private static final String KEY_DONE="done";
    private static final String KEY_BOUGHT="bought";
    public DBHandler(Context context) {
        super(context, db_name, null, db_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table Items (id integer primary key,name text,done int)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       //sqLiteDatabase.execSQL("Drop if table exists Items");
       // onCreate(sqLiteDatabase);
        if (newVersion > oldVersion) {
            db.execSQL("ALTER TABLE Items ADD COLUMN bought INTEGER DEFAULT 0");

        }
    }

    public void addItem(Item_obj item)
    {

        SQLiteDatabase db=this.getWritableDatabase();
      //  String done="false";
        ContentValues values=new ContentValues();

        values.put(KEY_NAME,item.getName());
        Log.v("ADDING"," "+item.isDone());
        values.put(KEY_DONE,item.isDone());
        values.put(KEY_BOUGHT,item.isBought());

        db.insert(table_name,null,values);
        db.close();
    }

   /* public Item_obj getItem(int id)
    {
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor=db.query(table_name,new String[]{KEY_ID,KEY_NAME,KEY_DONE},KEY_ID+"=?",new String[]{String.valueOf(id)},null,null,null,null);

        if(cursor!=null)
                cursor.moveToFirst();

        Item_obj item=new Item_obj(cursor.getString(1),Boolean.parseBoolean(cursor.getString(2)));


        return item;
    }*/

    public ArrayList<Item_obj> GetAllItems()
    {
       SQLiteDatabase db=getReadableDatabase();

        ArrayList<Item_obj> itemsList=new ArrayList<Item_obj>();

        String theQuery="select * from Items";
        Cursor cursor=db.rawQuery(theQuery,null);

        if(cursor.moveToFirst())
        {
            do {
                boolean bool=FALSE;
                if(cursor.getInt(2)==1)
                  bool=TRUE;


                Item_obj item=new Item_obj(cursor.getString(1),bool);
                itemsList.add(item);
                //Log.v("getall",cursor.getString(1)+" | "+cursor.getInt(2));

            }while(cursor.moveToNext());
        }

        return itemsList;
    }



    public void check(String name)
    {
        SQLiteDatabase db=getWritableDatabase();

       name=name.replaceAll("\'","''");
        String updateQuery ="update Items set done=1 where  name='"+name+"'";
        Cursor c= db.rawQuery(updateQuery, null);

        c.moveToFirst();
        c.close();
    }
    public void bought(String name)
    {
        SQLiteDatabase db=getWritableDatabase();
        //name.replaceAll("'","\'");
        name=name.replaceAll("\'","''");

        String updateQuery ="update Items set bought=1 where  name='"+name+"'";
        Cursor c= db.rawQuery(updateQuery, null);

        c.moveToFirst();
        c.close();
    }
    public void uncheck(String name)
    {
        SQLiteDatabase db=getWritableDatabase();
       // name.replaceAll("'","\'");
        name=name.replaceAll("\'","''");

        String updateQuery="update Items set done=0 where name='"+name+"'";
        Cursor c= db.rawQuery(updateQuery, null);

        c.moveToFirst();
        c.close();
    }
    public void delete(String name)
    {
        SQLiteDatabase db=getWritableDatabase();
        //name.replaceAll("'","\'");
        name=name.replaceAll("\'","''");

        String updateQuery="delete from Items where name='"+name+"'";
        Cursor c= db.rawQuery(updateQuery, null);

        c.moveToFirst();
        c.close();
    }
    public ArrayList<String> GetBought()
    {
        SQLiteDatabase db=getReadableDatabase();
        ArrayList<String> itemsList=new ArrayList<String>();
        String theQuery="select * from Items where  bought=1";
        Cursor cursor=db.rawQuery(theQuery,null);

        if(cursor.moveToFirst())
        {
            do{
                String item=cursor.getString(1);
                itemsList.add(item);
            }while(cursor.moveToNext());
        }
        return itemsList;
    }
    public ArrayList<String> GetToBuy()
    {
        SQLiteDatabase db=getReadableDatabase();

        ArrayList<String> itemsList=new ArrayList<String>();

        String theQuery="select * from Items where  done=1 and bought=0";
        Cursor cursor=db.rawQuery(theQuery,null);

        if(cursor.moveToFirst())
        {
            do {



               String item=cursor.getString(1);
                itemsList.add(item);
                //Log.v("getall",cursor.getString(1)+" | "+cursor.getInt(2));

            }while(cursor.moveToNext());
        }

        return itemsList;
    }
    public void clearall()
    {
        SQLiteDatabase db=getWritableDatabase();
        String query="delete from Items";
        Cursor cursor=db.rawQuery(query,null);
        cursor.moveToFirst();
        cursor.close();
    }
}
