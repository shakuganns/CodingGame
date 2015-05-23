package com.example.phonebook2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.phonebook.R;

import java.util.ArrayList;


/**
 *
 * Created by 圣麟 on 2015/5/23.
 */
public class MainActivity extends ActionBarActivity {

    private Intent intent;
    private ListView phoneList;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> name;
    private Button newPersonBtn;

    TextView tViewShowLetter;
    private static String[] letters;

    private void init() {
        name = new ArrayList<>();
        Database database = new Database(this);
        database.ReadDatabase();
        for(int i=0 ; Database.person[i] != null ; i++) {
            name.add(Database.person[i].getName());
        }
        phoneList = (ListView) findViewById(R.id.phoneList);
        adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,name);
        phoneList.setAdapter(adapter);
        phoneList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                intent = new Intent(MainActivity.this,SeeInfo.class);
                SeeInfo.i = i;
                startActivity(intent);
            }
        });
        newPersonBtn = (Button) findViewById(R.id.newPerson);
        newPersonBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MainActivity.this, NewPerson.class);
                startActivity(intent);
            }
        });

        SideBar sideBar = ((SideBar) findViewById(R.id.side_bar));
        sideBar.setOnLetterTouchListener(new SideBar.OnLetterTouchListener() {
            @Override
            public void onLetterTouch(String letter, int position) {
                tViewShowLetter.setVisibility(View.VISIBLE);
                tViewShowLetter.setText(letter);
                //phoneList.setSelectedGroup(position);
            }

            @Override
            public void onActionUp() {
                tViewShowLetter.setVisibility(View.GONE);
            }
        });
        sideBar.setShowString(letters);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
