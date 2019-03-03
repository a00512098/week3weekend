package com.example.week3weekend.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.week3weekend.R;
import com.example.week3weekend.model.DBHelper;
import com.example.week3weekend.model.Employee;

public class UpdateEmployeeFragment extends Fragment implements View.OnClickListener, TextWatcher {
    private OnFragmentInteractionListener mListener;
    private DBHelper database;
    private EditText employeeId, name, birthDate, wage, hireDate, imageUrl;
    private Button updateEmployeeBtn, findEmployeeBtn;
    private ConstraintLayout fieldsToUpdate;

    public UpdateEmployeeFragment() {
    }

    public static UpdateEmployeeFragment newInstance() {
        UpdateEmployeeFragment fragment = new UpdateEmployeeFragment();
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
        return inflater.inflate(R.layout.fragment_update_employee, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fieldsToUpdate = view.findViewById(R.id.fieldsToUpdate);
        employeeId = view.findViewById(R.id.employeeId);
        name = view.findViewById(R.id.name);
        birthDate = view.findViewById(R.id.birthDate);
        wage = view.findViewById(R.id.wage);
        hireDate = view.findViewById(R.id.hireDate);
        imageUrl = view.findViewById(R.id.imageUrl);
        updateEmployeeBtn = view.findViewById(R.id.updateEmployeeBtn);
        updateEmployeeBtn.setOnClickListener(this);
        findEmployeeBtn = view.findViewById(R.id.findEmployee);
        findEmployeeBtn.setOnClickListener(this);
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
        switch (v.getId()) {
            case R.id.findEmployee:
                findEmployeeWithId();
                break;
            case R.id.updateEmployeeBtn:
                updateEmployeeInfo();
                break;
        }
    }

    private void updateEmployeeInfo() {
        if (!isAnyOtherFieldEmpty()) {
            int id = Integer.parseInt(employeeId.getText().toString());
            String nameStr = name.getText().toString();
            String birthDateStr = birthDate.getText().toString();
            double wageStr = Double.parseDouble(wage.getText().toString());
            String hireStr = hireDate.getText().toString();
            String imageStr = imageUrl.getText().toString();

            Employee employee = new Employee(id, nameStr, birthDateStr, wageStr, hireStr, imageStr);
            database.updateEmployeeInDB(employee);
            if (mListener != null) {
                clearAllData();
                mListener.onUpdateEmployeeFragmentInteraction();
            }
        } else {
            Toast.makeText(getContext(), "All Fields Are Mandatory", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    private void findEmployeeWithId() {
        if (!isIdEmpty()) {
            int id = Integer.parseInt(employeeId.getText().toString());
            Employee employeeToUpdate = database.getEmployeeByID(id);
            if (employeeToUpdate.getId() == 0) {
                Toast.makeText(getContext(), "User Not Found", Toast.LENGTH_SHORT).show();
                return;
            } else {
                fillFieldsWithEmployeeInfo(employeeToUpdate);
            }
        }
    }

    private void fillFieldsWithEmployeeInfo(Employee m) {
        name.setText(m.getName());
        birthDate.setText(m.getBirthDate());
        wage.setText(String.valueOf(m.getWage()));
        hireDate.setText(m.getHireDate());
        imageUrl.setText(m.getImageUrl());
        fieldsToUpdate.setVisibility(View.VISIBLE);
        findEmployeeBtn.setVisibility(View.GONE);
        employeeId.addTextChangedListener(this);
    }

    public void attachDatabase(DBHelper database) {
        this.database = database;
    }

    private boolean isAnyOtherFieldEmpty() {
        return name.getText().toString().isEmpty() ||
                birthDate.getText().toString().isEmpty() ||
                wage.getText().toString().isEmpty() ||
                hireDate.getText().toString().isEmpty() ||
                imageUrl.getText().toString().isEmpty();
    }

    private boolean isIdEmpty() {
        return employeeId.getText().toString().isEmpty();
    }

    private void clearAllFieldsToUpdateAndDisappear() {
        name.setText("");
        birthDate.setText("");
        wage.setText("");
        hireDate.setText("");
        imageUrl.setText("");
        fieldsToUpdate.setVisibility(View.GONE);
        employeeId.removeTextChangedListener(this);
    }

    public void clearAllData() {
        clearAllFieldsToUpdateAndDisappear();
        updateEmployeeBtn.setVisibility(View.VISIBLE);
        employeeId.setText("");
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        Toast.makeText(getContext(), "ID Cannot Be Changed", Toast.LENGTH_SHORT).show();
        findEmployeeBtn.setVisibility(View.VISIBLE);
        clearAllFieldsToUpdateAndDisappear();
    }

    public interface OnFragmentInteractionListener {

        void onUpdateEmployeeFragmentInteraction();
    }
}
