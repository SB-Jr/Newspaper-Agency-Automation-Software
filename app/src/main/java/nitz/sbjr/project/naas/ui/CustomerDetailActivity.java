package nitz.sbjr.project.naas.ui;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import nitz.sbjr.project.naas.R;

public class CustomerDetailActivity extends AppCompatActivity {

    private TextView mVacationTextView;
    private TextView mListHeadingTextView;
    private ListView mChildListView;
    private FloatingActionButton mAddActivityFAB;
    private CoordinatorLayout mCoordinatorLayout;

    private String customer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_detail);


        mVacationTextView = (TextView) findViewById(R.id.warning_text);
        mListHeadingTextView = (TextView) findViewById(R.id.list_heading);
        mChildListView = (ListView) findViewById(R.id.child_list);
        mAddActivityFAB = (FloatingActionButton) findViewById(R.id.generate_bill_fab);
        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator_layout);

        customer  = getIntent().getStringExtra("customer");

        final FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference ref = mFirebaseDatabase.getReference().child("vacations");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild(customer)){
                    mVacationTextView.setText("On Vacation");
                    mVacationTextView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_delivery_person_customer,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.remind_bill){
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            database.getReference().child("reminders").child(customer).setValue(customer);
            Snackbar.make(mCoordinatorLayout,"Reminder sent",Snackbar.LENGTH_SHORT).show();
        }
        else {

        }
        return super.onOptionsItemSelected(item);
    }
}
