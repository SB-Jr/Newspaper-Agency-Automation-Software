package nitz.sbjr.project.naas.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import nitz.sbjr.project.naas.R;
import nitz.sbjr.project.naas.adpater.SubscriptionAdapter;
import nitz.sbjr.project.naas.pojo.Subscription;

public class ModifySubscriptionActivity extends AppCompatActivity {

    ListView mSubList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_subscription);

        mSubList = (ListView) findViewById(R.id.child_list);

        final SubscriptionAdapter adapter = new SubscriptionAdapter(this);
        mSubList.setAdapter(adapter);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference reference = database.getReference().child("subscriptions");

        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Subscription sub = dataSnapshot.getValue(Subscription.class);
                sub.setKey(dataSnapshot.getKey());
                adapter.add(sub);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        String user = getSharedPreferences("NAAS",MODE_PRIVATE).getString("user","user3");

        DatabaseReference reference1= database.getReference().child("customers").child(user).child("subscriptions");
        reference1.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String sub = dataSnapshot.getValue(String.class);
                adapter.remove(sub);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        mSubList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String newSub = adapter.getKey(position);
                DatabaseReference reference2 = database.getReference().child("subscriptions");
                reference2.child(newSub).setValue(newSub);
            }
        });

    }
}
