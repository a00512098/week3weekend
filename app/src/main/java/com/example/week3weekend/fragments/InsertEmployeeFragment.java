package com.example.week3weekend.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.week3weekend.R;
import com.example.week3weekend.model.DBHelper;
import com.example.week3weekend.model.Employee;

public class InsertEmployeeFragment extends Fragment implements View.OnClickListener{

    private DBHelper database;
    private EditText name, birthDate, wage, hireDate, imageUrl;
    private Button button;
    private OnFragmentInteractionListener mListener;

    public InsertEmployeeFragment() {
    }

    public static InsertEmployeeFragment newInstance() {
        InsertEmployeeFragment fragment = new InsertEmployeeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_insert_employee, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        button = view.findViewById(R.id.createUserBtn);
        button.setOnClickListener(this);
        name = view.findViewById(R.id.name);
        birthDate = view.findViewById(R.id.birthDate);
        wage = view.findViewById(R.id.wage);
        hireDate = view.findViewById(R.id.hireDate);
        imageUrl = view.findViewById(R.id.imageUrl);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        if (!isAnyFieldEmpty()) {
            String nameStr = name.getText().toString();
            String birthStr = birthDate.getText().toString();
            double wageStr = Double.parseDouble(wage.getText().toString());
            String hireStr = hireDate.getText().toString();
            String imgStr = imageUrl.getText().toString();
            database.insertEmployeeIntoDB(new Employee(nameStr, birthStr, wageStr, hireStr, imgStr));
            if (mListener != null) {
                mListener.onInsertEmployeeFragmentInteraction();
            }
        } else {
            Toast.makeText(getContext(), "First Fill All The Fields", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isAnyFieldEmpty() {
        return name.getText().toString().isEmpty() ||
                birthDate.getText().toString().isEmpty() ||
                wage.getText().toString().isEmpty() ||
                hireDate.getText().toString().isEmpty() ||
                imageUrl.getText().toString().isEmpty();
    }

    public void attachDatabase(DBHelper database) {
        this.database = database;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onInsertEmployeeFragmentInteraction();
    }
}
