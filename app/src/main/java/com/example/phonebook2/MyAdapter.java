package com.example.phonebook2;

import android.content.Context;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.SimpleAdapter;


import java.util.List;
import java.util.Map;

/**
 * Created by 圣麟 on 2015/5/23.
 */
public class MyAdapter extends SimpleAdapter {

    View[] view = new View[15];
    String[] values = new String[15];

    public String getValues(int i) {
        values[i] = ((EditText)view[i].findViewById(R.id.values)).getText().toString();
        return values[i];
    }

    public MyAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
    }


    public View getView(int i, View view, ViewGroup viewGroup) {
        System.out.println(i+" -------------->");
        this.view[i] = super.getView(i, view, viewGroup);
        if(i==1) {
            ((EditText)this.view[i].findViewById(R.id.values)).setImeOptions(EditorInfo.IME_ACTION_DONE);
            ((EditText)this.view[i].findViewById(R.id.values)).setInputType(InputType.TYPE_CLASS_PHONE);
        }
        return this.view[i];
    }
}
