package com.app.arra;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.provider.ContactsContract.Intents.Insert.ACTION;

public class SignUpActivity extends AppCompatActivity {

    private static final String TAG = "SignUpActivity";
    private FirebaseAuth firebaseAuth;
    EditText emailText, passwordText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();


        firebaseAuth = FirebaseAuth.getInstance();
        emailText = findViewById(R.id.emailText);
        passwordText = findViewById(R.id.passwordText);

        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        if (firebaseUser != null) {

            Intent intent = new Intent(SignUpActivity.this,MainActivity.class);
            startActivity(intent);
            finish();

        }


    }
    class PushReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction() == ACTION){
                String token = intent.getStringExtra("token");
                Log.i("New_Token_Received", token);
            }
        }

    }




    public void signInClicked (View view) {

        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();

        if (emailText.getText().toString().trim().equals("") && passwordText.getText().toString().trim().equals("")) {
            Toast.makeText(SignUpActivity.this, "Email ve Şifre Girin!", Toast.LENGTH_SHORT).show();
        } else {

            firebaseAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {

                    Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(SignUpActivity.this, e.getLocalizedMessage().toString(), Toast.LENGTH_LONG).show();
                }
            });


        }
    }

    public void signUpClicked (View view) {

        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();


        if (emailText.getText().toString().trim().equals("") && passwordText.getText().toString().trim().equals("")) {
            Toast.makeText(SignUpActivity.this, "Email ve Şifre Girin!", Toast.LENGTH_SHORT).show();
        } else {
            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    FirebaseAuth auth = FirebaseAuth.getInstance();
                    FirebaseUser firebaseUser = auth.getCurrentUser();

                    firebaseUser.sendEmailVerification()
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                }
                            });
                    Toast.makeText(SignUpActivity.this, "User Created", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(SignUpActivity.this, e.getLocalizedMessage().toString(), Toast.LENGTH_LONG).show();
                }
            });


        }
    }



}