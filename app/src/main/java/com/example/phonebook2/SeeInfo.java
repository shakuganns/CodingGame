package com.example.phonebook2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.phonebook.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 圣麟 on 2015/5/23.
 */
public class SeeInfo extends ActionBarActivity {

    public static int i;
    private ListView infoList;
    private SimpleAdapter adapter;

    private void init() {
        infoList = (ListView) findViewById(R.id.seeinfoList);
        adapter = new SimpleAdapter(this,getData(), R.layout.seeinfo_listitem,
                new String[]{"notes", "values"}, new int[]{R.id.notes_seeinfo, R.id.values_seeinfo});
        infoList.setAdapter(adapter);
        infoList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==1) {
                    Intent intent=new Intent();

                    intent.setAction("android.intent.action.CALL");

                    intent.setData(Uri.parse("tel:" + Database.person[SeeInfo.i].getPhone()));

                    startActivity(intent);
                }
            }
        });
    }

    private List<Map<String, Object>> getData() {
        List<Map<String,Object>> list = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        map.put("notes","姓名:");
        map.put("values",Database.person[i].getName());
        list.add(map);

        map = new HashMap<>();
        map.put("notes", "电话号码:");
        map.put("values", Database.person[i].getPhone()+"(点击拨号)");
        list.add(map);

        return list;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seeinfo_activity);
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
