package com.example.moneymind;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class WeeklyFragment extends Fragment {

    RecyclerView recyclerView;
    DatabaseHelper2 myDB;
    ArrayList<String> entry_id, entry_type, entry_date, entry_time, entry_category, entry_payment, entry_currency, entry_currency2, entry_note;
    ArrayList<Double> entry_amountincome, entry_amountexpense;

    CustomAdapter customAdapter;

    public WeeklyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view2 = inflater.inflate(R.layout.fragment_weekly, container, false);
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

        storeDataInArrays();


//        customAdapter = new CustomAdapter(getActivity(), entry_date, entry_category, entry_payment, entry_currency, entry_amountincome, entry_currency2, entry_amountexpense);
//        recyclerView.setAdapter(customAdapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


//        recyclerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                EntryModel clickedEntry = (EntryModel) parent.getItemAtPosition(position);
//                Intent intent = new Intent(getActivity(), changeEntryActivity.class);
//                startActivity(intent);
//
//            }
//        });

        return view2;
    }

    // this will refresh the ListView every time we Resume to daily fragments
    @Override
    public void onResume() {
        super.onResume();

        // Inflate the layout for this fragment
        myDB = new DatabaseHelper2(getActivity());
//        storeDataInArrays();

//        customAdapter = new CustomAdapter(getActivity(), entry_date, entry_category, entry_payment, entry_currency, entry_amountincome, entry_currency2, entry_amountexpense);
//        recyclerView.setAdapter(customAdapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    // go to ChangeEntryActivity


    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0) {
            Toast.makeText(getActivity(), "No data. ", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
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
            }
        }
    }
}
