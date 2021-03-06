package ba.unsa.etf.rma.VJ_17897;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button dugme;
    private EditText text;
    private ListView list;

    private ArrayList<String> unosi;

    private ArrayAdapter<Muzicar> adapter;

    private static ArrayList<Muzicar> muzicari;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dugme = (Button)findViewById(R.id.button);
        list = (ListView)findViewById(R.id.listView);
        text = (EditText)findViewById(R.id.editText);

        //unosi = new ArrayList<String>();
        muzicari = new ArrayList<>();
        muzicari.add(new Muzicar("ime", "prezime", "zanr", "http://www.google.com", "biografija"));

        adapter = new ArrayAdapter<Muzicar>(this, R.layout.element_liste, R.id.Itemname, muzicari);

        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Intent myIntent = new Intent(MainActivity.this, Detalji.class);
                myIntent.putExtra("imeAutora", muzicari.get(position).getIme());
                myIntent.putExtra("prezimeAutora", muzicari.get(position).getPrezime());
                myIntent.putExtra("zanr", muzicari.get(position).getZanr());
                myIntent.putExtra("webStranica", muzicari.get(position).getWebStranica());
                myIntent.putExtra("biografija", muzicari.get(position).getBiografija());
                MainActivity.this.startActivity(myIntent);
            }
        });

        dugme.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                unosi.add(0, text.getText().toString());
                adapter.notifyDataSetChanged();
                text.setText("");
            }
        });
    }
}
