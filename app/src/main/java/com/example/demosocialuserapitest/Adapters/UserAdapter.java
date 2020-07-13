package com.example.demosocialuserapitest.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demosocialuserapitest.Activity.AboutInfoActivity;
import com.example.demosocialuserapitest.R;
import com.example.demosocialuserapitest.UserUtils.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private Context context;
    private List<User> userList;

    public UserAdapter(Context context, List<User> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_info, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder holder, int position) {
        final User userInfo = userList.get(position);

        final String useName = userInfo.getUsername();
        String fullName = userInfo.getName();
        String email = userInfo.getEmail();
        String phone = userInfo.getPhone();
        final String company = userInfo.getCompany().getName();

        holder.tvUserName.setText(useName);
        holder.tvFullName.setText(fullName);
        holder.tvEmail.setText(email);
        holder.tvPhone.setText(phone);
        holder.tvCompany.setText(company);

        holder.tvUserName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AboutInfoActivity.class);
                intent.putExtra("userInfo", userInfo);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvUserName, tvFullName, tvEmail, tvPhone, tvCompany;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvUserName = itemView.findViewById(R.id.userNameTV);
            tvFullName = itemView.findViewById(R.id.fullNameTV);
            tvEmail = itemView.findViewById(R.id.emailTV);
            tvPhone = itemView.findViewById(R.id.phoneTV);
            tvCompany = itemView.findViewById(R.id.companyTV);
        }
    }
}
