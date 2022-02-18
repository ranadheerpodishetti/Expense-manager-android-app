package com.example.moneymind;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class DailyFragment extends Fragment implements CustomAdapter.OnEntryListener {

    RecyclerView recyclerView;
    DatabaseHelper2 myDB;
    ArrayList<String> entry_id, entry_type, entry_date, entry_time, entry_category, entry_payment, entry_currency, entry_currency2, entry_note, entry_notestyle;
    ArrayList<Double> entry_amountincome, entry_amountexpense;

    CustomAdapter customAdapter;

    public DailyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view2 = inflater.inflate(R.layout.fragment_daily, container, false);
        recyclerView = view2.findViewById(R.id.recyclerView);

        // Inflate the layout for this fragment

        myDB = new DatabaseHelper2(getActivity());
        entry_id = new ArrayList<>();
        entry_type = new ArrayList<>();
        entry_date = new ArrayList<>();
        entry_time = new ArrayList<>();
        entry_payment = new ArrayList<>();
        entry_category = new ArrayList<>();
        entry_currency = new ArrayList<>();
        entry_amountincome = new ArrayList<>();
        entry_currency2 = new ArrayList<>();
        entry_amountexpense = new ArrayList<>();
        entry_note = new ArrayList<>();
        entry_notestyle = new ArrayList<>();

        storeDataInArrays();


        customAdapter = new CustomAdapter(getActivity(), entry_date, entry_category, entry_payment, entry_currency, entry_amountincome, entry_currency2, entry_amountexpense, entry_note, this);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));




        return view2;
    }

    // this will refresh the ListView every time we Resume to daily fragments
    @Override
    public void onResume() {
        super.onResume();

        // Inflate the layout for this fragment
        myDB = new DatabaseHelper2(getActivity());

        customAdapter = new CustomAdapter(getActivity(), entry_date, entry_category, entry_payment, entry_currency, entry_amountincome, entry_currency2, entry_amountexpense, entry_note, this);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }


    // the storeDataInArrays function is used to access the Data in the Database
    // first the function readAllData() is called, which returns a Cursor.
    // This function is part of the DataBaseHelper.
    // For this to work the following needs to be declared outside of the storeDataInArrays function:
    // myDB = new DatabaseHelper2(getActivity()); getActivity() is required in this case for context, because we use fragments
    // to check if this was done successfully, an if statement is issued.
    // so if cursor.getCount() is empty a Toast will be displayed.
    // otherwise the cursor will go to the last entry in the Database (its ordered by Date)
    // So the latest Entry will be displayed first.

    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0) {
            Toast.makeText(getActivity(), "No data. ", Toast.LENGTH_SHORT).show();
        }else{
            // first entry will be the last one added
            cursor.moveToLast();
            entry_id.add(cursor.getString(0));
            entry_type.add(cursor.getString(1));
            entry_date.add(cursor.getString(2));
            entry_time.add(cursor.getString(3));
            entry_payment.add(cursor.getString(4));
            entry_category.add(cursor.getString(5));
            entry_amountincome.add(cursor.getDouble(6));
            entry_currency.add(cursor.getString(7));
            entry_amountexpense.add(cursor.getDouble(8));
            entry_currency2.add(cursor.getString(9));
            entry_note.add(cursor.getString(10));
            entry_notestyle.add(cursor.getString(11));
            while (cursor.moveToPrevious()){
                entry_id.add(cursor.getString(0));
                entry_type.add(cursor.getString(1));
                entry_date.add(cursor.getString(2));
                entry_time.add(cursor.getString(3));
                entry_payment.add(cursor.getString(4));
                entry_category.add(cursor.getString(5));
                entry_amountincome.add(cursor.getDouble(6));
                entry_currency.add(cursor.getString(7));
                entry_amountexpense.add(cursor.getDouble(8));
                entry_currency2.add(cursor.getString(9));
                entry_note.add(cursor.getString(10));
                entry_notestyle.add(cursor.getString(11));
            }
        }
    }

    // go to ChangeEntryActivity
    @Override
    public void onEntryClick(int position) {

        Intent intent = new Intent(getContext(), changeEntryActivity.class);
        intent.putExtra("selected_entry_id", entry_id.get(position));
        intent.putExtra("selected_entry_type", entry_type.get(position));
        intent.putExtra("selected_entry_date", entry_date.get(position));
        intent.putExtra("selected_entry_time", entry_time.get(position));
        intent.putExtra("selected_entry_payment", entry_payment.get(position));
        intent.putExtra("selected_entry_category", entry_category.get(position));
        intent.putExtra("selected_entry_amountincome", entry_amountincome.get(position));
        intent.putExtra("selected_entry_currency", entry_currency.get(position));
        intent.putExtra("selected_entry_amountexpense", entry_amountexpense.get(position));
        intent.putExtra("selected_entry_currency2", entry_currency2.get(position));
        intent.putExtra("selected_entry_note", entry_note.get(position));
        intent.putExtra("selected_entry_notestyle", entry_notestyle.get(position));

        startActivity(intent);
    }
}