package com.imperialsoupgmail.tesseractexample;

import android.provider.BaseColumns;


public final class SampleDBContract {


    public static final String SELECT_Expense = "SELECT * " +
            "FROM " + Expense.TABLE_NAME  + "exp WHERE " +
            "exp." + Expense.COLUMN_DATE + " like ? AND exp." + Expense.COLUMN_TOTAL + " like ?";

    private SampleDBContract() {
    }



    public static class Expense implements BaseColumns {
        public static final String TABLE_NAME = "expense";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_TOTAL = "total";


        public static final String CREATE_TABLE = "CREATE TABLE " +
                TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_DATE + " TEXT, " +
                COLUMN_TOTAL + " TEXT )";

    }
}
