package com.example.testfirebaseapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testfirebaseapp.databinding.UserItemListBinding;
import com.example.testfirebaseapp.databinding.UserListBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
          this.binding.ivMenu.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  PopupMenu popupMenu = new PopupMenu(context,binding.ivMenu);
                  Menu menu = popupMenu.getMenu();
                  menu.add("Edit");
                  menu.add("Delete");
                  popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                      @Override
                      public boolean onMenuItemClick(MenuItem item) {
                          String title = item.getTitle().toString();
                          int position = getAdapterPosition();
                          User user = al.get(position);
                          if(title.equalsIgnoreCase("Edit")){
                            sendUserToEditActivity(user);
                          }
                          else if(title.equalsIgnoreCase("Delete")){
                              AlertDialog.Builder ab = new AlertDialog.Builder(context);
                              ab.setTitle("Confirmation");
                              ab.setMessage("Are you sure ?");
                              ab.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                  @Override
                                  public void onClick(DialogInterface dialog, int which) {
                                    DatabaseReference userReference = FirebaseDatabase.getInstance().getReference("User");
                                    userReference.child(user.getId()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(context, "Record removed..", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                  }
                              });
                              ab.setNegativeButton("No",null);
                              ab.show();

                          }
                          return false;
                      }
                  });
                  popupMenu.show();
              }
          });
      }

  }
  private void sendUserToEditActivity(User user){
      Intent in = new Intent(context,EditActivity.class);
      in.putExtra("user",user);
      context.startActivity(in);
  }
}
