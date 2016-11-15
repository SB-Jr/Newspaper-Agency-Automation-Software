package nitz.sbjr.project.naas.adpater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import nitz.sbjr.project.naas.R;

/**
 * Created by sbjr on 11/16/16.
 */

public class ChildListAdapater extends BaseAdapter {

    private ArrayList<String> list;
    private Context context;


    public ChildListAdapater(ArrayList<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.login_type_spinner_list_item,parent,false);
        }
        ((TextView) convertView.findViewById(R.id.text)).setText(list.get(position));
        return convertView;
    }


    public void add(String s){
        list.add(s);
    }
}
