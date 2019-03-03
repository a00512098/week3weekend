package com.example.week3weekend.recycleradapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.week3weekend.R;
import com.example.week3weekend.model.Employee;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    ArrayList<Employee> adapterList;

    public RecyclerAdapter(ArrayList<Employee> adapterList) {
        this.adapterList = adapterList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_employee, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Context context = viewHolder.itemView.getContext();

        viewHolder.id.setText(String.format(context.getResources().getString(R.string.id_d), adapterList.get(i).getId()));
        viewHolder.name.setText(String.format(context.getResources().getString(R.string.name_s), adapterList.get(i).getName()));
        viewHolder.birthdate.setText(String.format(context.getResources().getString(R.string.birthday_s), adapterList.get(i).getBirthDate()));
        viewHolder.wage.setText(String.format(context.getResources().getString(R.string.wage_f), adapterList.get(i).getWage()));
        viewHolder.hiredate.setText(String.format(context.getResources().getString(R.string.hire_date_s), adapterList.get(i).getHireDate()));

        Glide.with(context).load(adapterList.get(i).getImageUrl()).placeholder(R.drawable.default_avatar).centerCrop().into(viewHolder.image);
    }

    @Override
    public int getItemCount() {
        return adapterList != null ? adapterList.size() : 0;
    }

    public void update() {
        notifyDataSetChanged();
    }

    public void updateList(ArrayList<Employee> updatedList) {
        adapterList = updatedList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, id, birthdate, wage, hiredate;
        ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            id = itemView.findViewById(R.id.employeeId);
            birthdate = itemView.findViewById(R.id.birthDate);
            wage = itemView.findViewById(R.id.wage);
            hiredate = itemView.findViewById(R.id.hireDate);
            image = itemView.findViewById(R.id.image);
        }
    }
}
