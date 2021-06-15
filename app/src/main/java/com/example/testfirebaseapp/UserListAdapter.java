package com.example.testfirebaseapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testfirebaseapp.databinding.UserItemListBinding;
import com.example.testfirebaseapp.databinding.UserListBinding;

import java.util.ArrayList;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UserViewHolder> {
    Context context;
    ArrayList<User>al;
    public UserListAdapter(Context context,ArrayList<User>al){
        this.context = context;
        this.al = al;
    }
    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        UserItemListBinding binding = UserItemListBinding.inflate(LayoutInflater.from(context),parent,false);
        return new UserViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull  UserListAdapter.UserViewHolder holder, int position) {
      User user = al.get(position);
      holder.binding.tvUserData.setText("Name: "+user.getName()+"" +
              "\nMobile : "+user.getMobile()+
      "\nAge : "+user.getAge());
    }

    @Override
    public int getItemCount() {
        return al.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder{
      UserItemListBinding binding;
      public UserViewHolder(UserItemListBinding binding){
          super(binding.getRoot());
          this.binding = binding;
      }
  }
}
