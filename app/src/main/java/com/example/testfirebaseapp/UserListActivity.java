package com.example.testfirebaseapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.testfirebaseapp.databinding.UserListBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UserListActivity extends AppCompatActivity {
    UserListBinding binding;
    DatabaseReference userReference;
    ArrayList<User>al;
    UserListAdapter adapter;
    @Override
    protected void onCreate(@Nullable  Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = UserListBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
        userReference = FirebaseDatabase.getInstance().getReference("User");


        userReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //Iterable<DataSnapshot> dataSnapshots = snapshot.getChildren();
                  al = new ArrayList<>();
                  for(DataSnapshot singleSnapshot: snapshot.getChildren()){
                      User user = singleSnapshot.getValue(User.class);
                      al.add(user);
                      Log.e("User Data===>",user.getId());
                  }
                  adapter = new UserListAdapter(UserListActivity.this,al);
                  binding.rv.setAdapter(adapter);
                  binding.rv.setLayoutManager(new LinearLayoutManager(UserListActivity.this));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
