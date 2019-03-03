package com.example.week3weekend.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;

import static com.example.week3weekend.model.DBContract.COL_BIRTH_DATE;
import static com.example.week3weekend.model.DBContract.COL_HIRE_DATE;
import static com.example.week3weekend.model.DBContract.COL_ID;
import static com.example.week3weekend.model.DBContract.COL_IMAGE_URL;
import static com.example.week3weekend.model.DBContract.COL_NAME;
import static com.example.week3weekend.model.DBContract.COL_WAGE;
import static com.example.week3weekend.model.DBContract.DB_NAME;
import static com.example.week3weekend.model.DBContract.DB_VERSION;
import static com.example.week3weekend.model.DBContract.TABLE_NAME;
import static com.example.week3weekend.model.DBContract.createQuery;
import static com.example.week3weekend.model.DBContract.getAllEmployeesQuery;
import static com.example.week3weekend.model.DBContract.getOneEmployeeById;
import static com.example.week3weekend.model.DBContract.getWhereClauseById;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createQuery());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    public long insertEmployeeIntoDB(Employee employee) {
        SQLiteDatabase writableDB = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NAME, employee.getName());
        contentValues.put(COL_BIRTH_DATE, employee.getBirthDate());
        contentValues.put(COL_WAGE, employee.getWage());
        contentValues.put(COL_HIRE_DATE, employee.getHireDate());
        contentValues.put(COL_IMAGE_URL, employee.getImageUrl());

        return writableDB.insert(TABLE_NAME, null, contentValues);
    }

    public ArrayList<Employee> getAllEmployeesFromDB() {
        SQLiteDatabase readableDB = getReadableDatabase();
        Cursor cursor = readableDB.rawQuery(getAllEmployeesQuery(), null);
        ArrayList<Employee> employees = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(COL_ID));
                String name = cursor.getString(cursor.getColumnIndex(COL_NAME));
                String birthDate = cursor.getString(cursor.getColumnIndex(COL_BIRTH_DATE));
                double wage = cursor.getDouble(cursor.getColumnIndex(COL_WAGE));
                String hireDate = cursor.getString(cursor.getColumnIndex(COL_HIRE_DATE));
                String image = cursor.getString(cursor.getColumnIndex(COL_IMAGE_URL));

                employees.add(new Employee(id, name, birthDate, wage, hireDate, image));
                Log.d("Log.d", "User id: " + id);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return employees;
    }

    public Employee getEmployeeByID(int id) {
        SQLiteDatabase readableDB = getReadableDatabase();
        Cursor cursor = readableDB.rawQuery(getOneEmployeeById(id), null);
        Employee employee = new Employee();
        if (cursor.moveToFirst()) {
            employee.setId(cursor.getInt(cursor.getColumnIndex(COL_ID)));
            employee.setName(cursor.getString(cursor.getColumnIndex(COL_NAME)));
            employee.setBirthDate(cursor.getString(cursor.getColumnIndex(COL_BIRTH_DATE)));
            employee.setWage(cursor.getDouble(cursor.getColumnIndex(COL_WAGE)));
            employee.setHireDate(cursor.getString(cursor.getColumnIndex(COL_HIRE_DATE)));
            employee.setImageUrl(cursor.getString(cursor.getColumnIndex(COL_IMAGE_URL)));
        }
        cursor.close();
        return employee;
    }

    public long updateEmployeeInDB(Employee employee) {
        SQLiteDatabase writableDB = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_ID, employee.getId());
        contentValues.put(COL_NAME, employee.getName());
        contentValues.put(COL_BIRTH_DATE, employee.getBirthDate());
        contentValues.put(COL_WAGE, employee.getWage());
        contentValues.put(COL_HIRE_DATE, employee.getHireDate());
        contentValues.put(COL_IMAGE_URL, employee.getImageUrl());

        return writableDB.update(TABLE_NAME,
                contentValues,
                getWhereClauseById() + employee.getId(),
                null);
    }

    public long deleteUserFromDBById(int id) {
        SQLiteDatabase writableDB = getWritableDatabase();
        return writableDB.delete(TABLE_NAME,
                getWhereClauseById() + id,
                null);
    }
}
