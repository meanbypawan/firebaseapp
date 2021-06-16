package com.example.testfirebaseapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.testfirebaseapp.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(@Nullable  Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
        binding.btnSave.setText("Update");
        Intent in = getIntent();
        User user = (User) in.getSerializableExtra("user");
        if(user!=null){
            binding.etAge.setText(""+user.getAge());
            binding.etMobile.setText(user.getMobile());
            binding.etName.setText(user.getName());
        }
        // btnSave is updated here
        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int age = Integer.parseInt(binding.etAge.getText().toString());
                String mobile = binding.etMobile.getText().toString();
                String name = binding.etName.getText().toString();
                user.setAge(age);
                user.setMobile(mobile);
                user.setName(name);
                DatabaseReference userReference = FirebaseDatabase.getInstance().getReference("User");
                userReference.child(user.getId()).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(EditActivity.this, "User updated...", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });

            }
        });
    }
}
