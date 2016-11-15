package nitz.sbjr.project.naas.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import nitz.sbjr.project.naas.R;
import nitz.sbjr.project.naas.adpater.ChildListAdapater;

public class UserActivity extends AppCompatActivity {

    private TextView mWarningTextView;
    private TextView mListHeadingTextView;
    private ListView mChildListView;
    private FloatingActionButton mAddActivityFAB;


    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;


    private String userType;
    private String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        mWarningTextView = (TextView) findViewById(R.id.warning_text);
        mListHeadingTextView = (TextView) findViewById(R.id.list_heading);
        mChildListView = (ListView) findViewById(R.id.child_list);
        mAddActivityFAB = (FloatingActionButton) findViewById(R.id.add_activity_fab);

        mFirebaseDatabase = FirebaseDatabase.getInstance();

        SharedPreferences sharedPreferences = getSharedPreferences("NAAS",MODE_PRIVATE);
        userType = sharedPreferences.getString("type","customer");
        user = sharedPreferences.getString("user","user3");

        if(userType.equalsIgnoreCase("customer")){

            //TODO:check for reminder notifications
            mListHeadingTextView.setText("Subscriptions");

            //addding subscriptions
            ArrayList<String> subs = new ArrayList<>();
            final ChildListAdapater adapater = new ChildListAdapater(subs,this);
            mChildListView.setAdapter(adapater);
            mDatabaseReference = mFirebaseDatabase.getReference().child("customers").child(user).child("subscriptions");
            mDatabaseReference.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    String sub = dataSnapshot.getValue(String.class);
                    adapater.add(sub);
                    adapater.notifyDataSetChanged();
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


        }
        else if(userType.equalsIgnoreCase("manager")){
            mListHeadingTextView.setText("Delivery Person");

            ArrayList<String> subs = new ArrayList<>();
            final ChildListAdapater adapater = new ChildListAdapater(subs,this);
            mChildListView.setAdapter(adapater);
            mDatabaseReference = mFirebaseDatabase.getReference().child("deliveryperson").child(user).child("customers");
            mDatabaseReference.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    String sub = dataSnapshot.getValue(String.class);
                    adapater.add(sub);
                    adapater.notifyDataSetChanged();
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
        }
        else{
            mListHeadingTextView.setText("Customers");
        }

        mAddActivityFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userType.equalsIgnoreCase("customer")){

                }
                else if(userType.equalsIgnoreCase("manager")){

                }
                else{

                }
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        if(userType.equalsIgnoreCase("customer")){
            getMenuInflater().inflate(R.menu.menu_customer,menu);
        }
        else if(userType.equalsIgnoreCase("manager")){
            getMenuInflater().inflate(R.menu.menu_manager,menu);
        }
        else{
            getMenuInflater().inflate(R.menu.menu_delivery_person,menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.logout){
            SharedPreferences sharedPreferences = getSharedPreferences("NAAS",MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove("type");
            editor.remove("user");
            editor.remove("userid");
            editor.commit();
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class) ;
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
        if(userType.equalsIgnoreCase("customer")){

                //TODO:Notify vacation
        }
        else if(userType.equalsIgnoreCase("manager")){
                //TODO:Generate summary
        }
        else{

        }
        return super.onOptionsItemSelected(item);
    }
}
