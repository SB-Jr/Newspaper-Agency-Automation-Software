package nitz.sbjr.project.naas.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import nitz.sbjr.project.naas.R;

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        /*signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = mUserId.getText().toString().trim();
                String pass = mUserPassword.getText().toString().trim();

                ProgressDialog dialog = new ProgressDialog(getApplicationContext());
                dialog.setIndeterminate(true);
                dialog.setMessage("Please wait");
                dialog.setCancelable(false);
                //dialog.show();


                auth.signInWithEmailAndPassword(id,pass)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                //dialog.dismiss();
                                if(task.isComplete()){
                                    Toast.makeText(getApplicationContext(),"Complete",Toast.LENGTH_SHORT).show();
                                }
                                if(!task.isSuccessful()){
                                    Toast.makeText(getApplicationContext(),"User cant be logged in",Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    Intent intent = new Intent(getApplicationContext(), UserActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });
            }
        });*/


    }
}
