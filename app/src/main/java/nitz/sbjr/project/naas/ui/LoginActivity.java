package nitz.sbjr.project.naas.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import nitz.sbjr.project.naas.R;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatbasereference;


    private EditText mUserId;
    private EditText mUserPassword;
    private Button login;
    private Spinner logintTypeSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Firebase
        auth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatbasereference.child("users");


        mUserId = (EditText) findViewById(R.id.login_id);
        mUserPassword = (EditText) findViewById(R.id.login_password);
        login = (Button) findViewById(R.id.signin_btn);
        logintTypeSpinner = (Spinner) findViewById(R.id.logint_type_spinner);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = mUserId.getText().toString().trim();
                String pass = mUserPassword.getText().toString().trim();
                if(id.length()<4){
                    Toast.makeText(getApplicationContext(),"Id too short",Toast.LENGTH_SHORT).show();
                }
                if(pass.length()<4){
                    Toast.makeText(getApplicationContext(),"password too short",Toast.LENGTH_SHORT).show();
                }
                else {

                    final ProgressDialog dialog = new ProgressDialog(getApplicationContext());
                    dialog.setIndeterminate(true);
                    dialog.setMessage("Please wait");
                    dialog.setCancelable(false);
                    //dialog.show();
                    Toast.makeText(getApplicationContext(),"making user....",Toast.LENGTH_SHORT).show();
                    auth.createUserWithEmailAndPassword(id, pass)
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    //dialog.dismiss();
                                    if(task.isComplete()){
                                        Toast.makeText(getApplicationContext(),"Complete",Toast.LENGTH_SHORT).show();
                                    }
                                    if(!task.isSuccessful()){
                                        Toast.makeText(getApplicationContext(),"User cant be created",Toast.LENGTH_SHORT).show();
                                    }
                                    else {
                                        Intent intent = new Intent(getApplicationContext(), UserActivity.class) ;
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            });
                }
            }
        });
    }


}
