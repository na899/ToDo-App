package e.pnaveena.to_do;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "todo.db";
    public static final String TABLE_NAME = "TodoList";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TODO = "Todo";



    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

}

    @Override
    public void onCreate(SQLiteDatabase db) {
        String table = "CREATE TABLE "+TABLE_NAME+"("+COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT ,"+COLUMN_TODO+" TEXT"+");";
        db.execSQL(table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addRow(String todo){
        ContentValues value=new ContentValues();
        value.put(COLUMN_TODO,todo);
        SQLiteDatabase db=getWritableDatabase();
        db.insert(TABLE_NAME,null,value);
        db.close();

    }

   public void delRow(String todo){

        SQLiteDatabase db=getWritableDatabase();
        db.execSQL("DELETE FROM "+TABLE_NAME+" WHERE "+COLUMN_TODO+" = '"+todo+"'"+";");
   }


   public ArrayList<String> display(Context context) {
    ArrayList<String>list=new ArrayList<>();
    SQLiteDatabase db =getWritableDatabase();
       Cursor cursor=db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE 1",null);
       cursor.moveToFirst();
       //Toast.makeText(context,""+cursor.getCount(),Toast.LENGTH_LONG).show();
       while (!cursor.isAfterLast()){
           if(cursor.getString(cursor.getColumnIndex(COLUMN_TODO))!=null){
             //  Toast.makeText(context,"hello",Toast.LENGTH_LONG).show();
               list.add(cursor.getString(cursor.getColumnIndex(COLUMN_TODO)));
               cursor.moveToNext();

           }

       }
       db.close();
       return list;
    }

   }


