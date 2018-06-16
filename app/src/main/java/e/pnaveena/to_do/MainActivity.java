

package e.pnaveena.to_do;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;



public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    ListView list;
    EditText text;
    ArrayList<String> items=new ArrayList<>();
    ArrayAdapter<String> adapter;
    Database db=new Database(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list=findViewById(R.id.list);
        text=findViewById(R.id.text);
        items=db.display(this);
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,items);
        list.setAdapter(adapter);
        list.setOnItemClickListener(this);
    }


    public void add(View v){

        items.add(text.getText().toString());
        adapter.notifyDataSetChanged();

        db.addRow(text.getText().toString());
        text.setText("");

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        db.delRow(items.get(position));
        items.remove(position);
        adapter.notifyDataSetChanged();

    }
}


