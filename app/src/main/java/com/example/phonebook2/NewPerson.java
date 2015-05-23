package com.example.phonebook2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 圣麟 on 2015/5/23.
 */
public class NewPerson extends ActionBarActivity {

    private ListView inputList;
    private MyAdapter adapter;
    private EditText valuesText;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newperson_activity);
        init();
    }


    private void init() {
        valuesText = (EditText) findViewById(R.id.values);
        inputList = (ListView) findViewById(R.id.inputList);
        adapter = new MyAdapter(this,getData(),R.layout.newperson_listitem,
                new String[]{"notes", "values"}, new int[]{R.id.notes, R.id.values});
        inputList.setAdapter(adapter);
    }

    private List<Map<String, Object>> getData() {
        List<Map<String,Object>> list = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        map.put("notes","姓名:");
        map.put("values",valuesText);
        list.add(map);

        map = new HashMap<>();
        map.put("notes", "电话号码:");
        map.put("values", valuesText);
        list.add(map);

        return list;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_newperson, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.submit) {
            if (TextUtils.isEmpty(adapter.getValues(0))||TextUtils.isEmpty(adapter.getValues(1))) {
                Toast.makeText(getApplicationContext(), "请输入完整信息", Toast.LENGTH_SHORT).show();

            }
            else {
                Database database = new Database(this);
                database.WriteDatabase(adapter.getValues(0), adapter.getValues(1));
                intent = new Intent(NewPerson.this, MainActivity.class);
                startActivity(intent);
                return true;
            }
        }

        return super.onOptionsItemSelected(item);
    }

}
