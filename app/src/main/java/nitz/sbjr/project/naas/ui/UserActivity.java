package nitz.sbjr.project.naas.ui;

import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import nitz.sbjr.project.naas.R;

public class UserActivity extends AppCompatActivity {

    private TextView mWarningTextView;
    private TextView mListHeadingTextView;
    private ListView mChildListView;
    private FloatingActionButton mAddActivityFAB;


    private String userType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        mWarningTextView = (TextView) findViewById(R.id.warning_text);
        mListHeadingTextView = (TextView) findViewById(R.id.list_heading);
        mChildListView = (ListView) findViewById(R.id.child_list);
        mAddActivityFAB = (FloatingActionButton) findViewById(R.id.add_activity_fab);


        SharedPreferences sharedPreferences = getSharedPreferences("NAAS",MODE_PRIVATE);
        userType = sharedPreferences.getString("type","customer");

        if(userType.equalsIgnoreCase("customer")){

            //TODO:check for reminder notifications

            mListHeadingTextView.setText("Subscriptions");


        }
        else if(userType.equalsIgnoreCase("manager")){

        }
        else{

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
        if(userType.equalsIgnoreCase("customer")){
            if(item.getItemId()==R.id.logout){

            }
            else{
                //TODO:Notify vacation
            }
        }
        else if(userType.equalsIgnoreCase("manager")){
            if(item.getItemId()==R.id.logout){

            }
            else{
                //TODO:Generate summary
            }
        }
        else{

        }
        return super.onOptionsItemSelected(item);
    }
}
