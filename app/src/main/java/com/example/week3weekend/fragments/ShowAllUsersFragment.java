package com.example.week3weekend.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.week3weekend.R;
import com.example.week3weekend.model.DBHelper;
import com.example.week3weekend.model.Employee;
import com.example.week3weekend.recycleradapter.RecyclerAdapter;

import java.util.ArrayList;

public class ShowAllUsersFragment extends Fragment {

//    private OnFragmentInteractionListener mListener;
    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;
    private ArrayList<Employee> employees = new ArrayList<>();

    public ShowAllUsersFragment() {
        // Required empty public constructor
    }

    public static ShowAllUsersFragment newInstance() {
        ShowAllUsersFragment fragment = new ShowAllUsersFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show_all_users, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerEmployees);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerAdapter = new RecyclerAdapter(employees);
        recyclerView.setAdapter(recyclerAdapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void attachListOfEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    public void updateList() {
        recyclerAdapter.updateList(employees);
        recyclerAdapter.update();
    }
}
