package aynassignment.bhagyawant.com.aynassignment.adapters;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import aynassignment.bhagyawant.com.aynassignment.R;
import aynassignment.bhagyawant.com.aynassignment.fragments.AddUserFragment;
import aynassignment.bhagyawant.com.aynassignment.pojo.User;
import aynassignment.bhagyawant.com.aynassignment.utils.DatabaseHelper;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder> {

    private List<User> users;
    private Activity activity;

    public UserListAdapter(List<User> users, Activity activity) {
        this.users = users;
        this.activity = activity;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        final User user = users.get(position);
        holder.tvEmail.setText(user.getEmailId());
        holder.tvUserName.setText(user.getUserName());
        holder.tvMobile.setText(user.getMobileNo());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, ""+user.userID, Toast.LENGTH_SHORT).show();
                showEditOrDeteleDialog(user, position);
            }
        });

    }

    private void showEditOrDeteleDialog(final User user, final int position) {
        final android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(activity);
        final View view = View.inflate(activity, R.layout.dialog_edit_delete, null);
        builder.setView(view);
        final android.support.v7.app.AlertDialog dialog = builder.create();

        final Button btnEdit = view.findViewById(R.id.btnEdit);
        final Button btnDelete = view.findViewById(R.id.btnDelete);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddUserFragment addUserFragment = new AddUserFragment();
                Bundle args = new Bundle();
                args.putInt("ID", user.getUserID());
                addUserFragment.setArguments(args);
                activity.getFragmentManager().beginTransaction().replace(R.id.frameLayout,addUserFragment).commitAllowingStateLoss();
                dialog.dismiss();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper databaseHelper = new DatabaseHelper(activity);
                databaseHelper.removeUser(user);
                users.remove(user);
                notifyDataSetChanged();
                dialog.dismiss();
            }
        });


        dialog.show();
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final TextView tvUserName, tvMobile, tvEmail;

        public ViewHolder(View itemView) {
            super(itemView);
            tvEmail = itemView.findViewById(R.id.tvEmail);
            tvUserName = itemView.findViewById(R.id.tvUserName);
            tvMobile = itemView.findViewById(R.id.tvMobile);
        }
    }
}
