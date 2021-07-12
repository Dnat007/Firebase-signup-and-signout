package com.example.secondfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity
{
    Button b1,b2;
    EditText e1,e2;
    ProgressBar p1;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 = (Button)findViewById(R.id.button);
        b2 = (Button)findViewById(R.id.abhishek);

        e1 = (EditText)findViewById(R.id.editTextTextPersonName);
        e2 = (EditText)findViewById(R.id.editTextTextPersonName2);
        e2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        p1 = (ProgressBar)findViewById(R.id.progressBar);
        firebaseAuth = FirebaseAuth.getInstance();
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = e1.getText().toString();
                String s2 = e2.getText().toString();
                if(s1.isEmpty())
                {
                    e1.setError("fill the username ");
                    return;
                }
                else
                {
                    if(s2.isEmpty())
                    {
                        e2.setError("fill password");
                        return;
                    }
                }
                p1.setVisibility(View.VISIBLE);

                firebaseAuth.signInWithEmailAndPassword(s1,s2).addOnCompleteListener(new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<AuthResult> task)
                    {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(MainActivity.this , "login done",Toast.LENGTH_SHORT).show();
                            p1.setVisibility(View.INVISIBLE);
                            Intent j2 = new Intent(MainActivity.this,Third.class);
                            startActivity(j2);
                            finish();
                        }
                        else
                        {

                            Toast.makeText(MainActivity.this , "Mismatch",Toast.LENGTH_SHORT).show();
                            Intent j2 = new Intent(MainActivity.this,Second.class);
                            startActivity(j2);
                            finish();

                        }
                    }
                });
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j1 = new Intent(MainActivity.this,Second.class);
                startActivity(j1);
                finish();

            }
        });
    }
}