package nitz.sbjr.project.naas.adpater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import nitz.sbjr.project.naas.R;
import nitz.sbjr.project.naas.pojo.Subscription;

/**
 * Created by sbjr on 11/16/16.
 */

public class SubscriptionAdapter extends BaseAdapter {

    private ArrayList<Subscription> subs = new ArrayList<>();
    private Context context;

    public SubscriptionAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return subs.size();
    }

    @Override
    public Object getItem(int position) {
        return subs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.subscription_item,parent,false);
        }
        ((TextView) convertView.findViewById(R.id.title)).setText(subs.get(position).getTitle());
        ((TextView) convertView.findViewById(R.id.cost)).setText(subs.get(position).getPrice());
        return convertView;
    }

    public void add(Subscription s){
        subs.add(s);
    }
    public void remove(String s){
        for(Subscription sub: subs) {
            if(sub.getKey().equalsIgnoreCase(s)) {
                subs.remove(sub);
            }
        }
    }

    public String getKey(int pos){
        return subs.get(pos).getKey();
    }

}
