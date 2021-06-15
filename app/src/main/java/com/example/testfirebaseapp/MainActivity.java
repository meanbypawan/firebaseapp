package com.example.testfirebaseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.testfirebaseapp.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    DatabaseReference userReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolBar);
        userReference = FirebaseDatabase.getInstance().getReference("User");

        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = userReference.push().getKey();
                String name = binding.etName.getText().toString();
                String mobile = binding.etMobile.getText().toString();
                int age = Integer.parseInt(binding.etAge.getText().toString());

                User user = new User(id,name,mobile,age);
                ProgressDialog pd = new ProgressDialog(MainActivity.this);
                pd.setMessage("Please wait..");
                pd.show();
                userReference.child(id).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                       pd.dismiss();
                        Toast.makeText(MainActivity.this, "Data Saved", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("User list");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String title = item.getTitle().toString();
        if(title.equalsIgnoreCase("User list")){
            sendUsertoUserListActivity();
        }
        return super.onOptionsItemSelected(item);
    }
    private void sendUsertoUserListActivity(){
        Intent in = new Intent(MainActivity.this,UserListActivity.class);
        startActivity(in);
    }
}