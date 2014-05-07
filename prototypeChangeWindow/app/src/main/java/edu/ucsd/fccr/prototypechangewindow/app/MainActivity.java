package edu.ucsd.fccr.prototypechangewindow.app;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class MainActivity extends Activity {
    // initiate ListView
    ListView listview;

    public String[] nameArray = {"x1","x2","x3","x4"};
    public double[] valueArray = {1,2,3,4};
    public double[] minArray = {1,1,1,1};
    public double[] maxArray = {5,5,5,5};
    public ArrayList<String> valueStringArray = new ArrayList<String>();
    //public String[] valueSArray = {"1","2","3","4"};

    private final int REQUEST_CODE = 1;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listview = (ListView) findViewById(R.id.listView);

        updateList();

    }

    private ArrayList<Map<String, String>> buildData() {
        ArrayList<Map<String, String>> list = new ArrayList<Map<String, String>>();
        valueStringArray = new ArrayList<String>();

        for(int i=0; i<nameArray.length; i++){

            valueStringArray.add("Value = " + Double.toString(valueArray[i]));
            list.add(putData(nameArray[i], valueStringArray.get(i)));
            //list.add(putData(nameArray[i], valueSArray[i]));

        }
        return list;
    }

    private HashMap<String, String> putData(String name, String purpose) {
        HashMap<String, String> item = new HashMap<String, String>();
        item.put("name", name);
        item.put("purpose", purpose);
        return item;
    }

    public void updateList(){

        ArrayList<Map<String, String>> list = buildData();
        String[] from = { "name", "purpose" };
        int[] to = { android.R.id.text1, android.R.id.text2 };

        SimpleAdapter adapter = new SimpleAdapter(this, list,
                android.R.layout.simple_list_item_2, from, to);

        listview.setAdapter(adapter);

        listview.setTextFilterEnabled(true);

        listview.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                Intent data = new Intent(MainActivity.this, changeWindow.class);
                data.putExtra("index", position);
                data.putExtra("name", nameArray[position]);
                data.putExtra("value", valueArray[position]);
                data.putExtra("min", minArray[position]);
                data.putExtra("max", maxArray[position]);
                startActivityForResult(data, REQUEST_CODE);

            }

        });

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){

            if(resultCode == RESULT_OK){
                index = data.getIntExtra("index", 0);
                valueArray[index] = data.getDoubleExtra("value", 100);
                //valueSArray[index] = Double.toString(valueArray[index]);
            }
            if(resultCode == RESULT_CANCELED){
                Toast fail = Toast.makeText(getApplicationContext(), "Fail", Toast.LENGTH_SHORT);
                fail.show();
            }
        }

        updateList();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}