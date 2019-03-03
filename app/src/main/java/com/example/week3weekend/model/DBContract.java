package com.example.week3weekend.model;

import java.util.Locale;

public class DBContract {
    public static final String DB_NAME = "db_employees";
    public static final int DB_VERSION = 1;

    // Use this values to refer to columns on the DB
    public static final String TABLE_NAME = "employees";
    public static final String COL_ID = "id";
    public static final String COL_NAME = "name";
    public static final String COL_WAGE = "wage";
    public static final String COL_BIRTH_DATE = "birthdate";
    public static final String COL_HIRE_DATE = "hiredate";
    public static final String COL_IMAGE_URL = "image";

    public static String createQuery() {
        StringBuilder query = new StringBuilder();
        query.append("CREATE TABLE ");
        query.append(TABLE_NAME);
        query.append(" ( ");
        query.append(COL_ID);
        query.append(" INTEGER PRIMARY KEY AUTOINCREMENT, ");
        query.append(COL_NAME);
        query.append(" TEXT, ");
        query.append(COL_WAGE);
        query.append(" FLOAT, ");
        query.append(COL_HIRE_DATE);
        query.append(" TEXT, ");
        query.append(COL_BIRTH_DATE);
        query.append(" TEXT, ");
        query.append(COL_IMAGE_URL);
        query.append(" TEXT )");

        return query.toString();
    }

    public static String getAllEmployeesQuery() {
        return "SELECT * FROM " + TABLE_NAME;
    }

    public static String getOneEmployeeById(int id) {
        return String.format("SELECT * FROM %s WHERE %s = \"%d\"", TABLE_NAME, COL_ID, id);
    }

    public static String getWhereClauseById() {
        return String.format(Locale.US, "%s = ", COL_ID);
    }
}
