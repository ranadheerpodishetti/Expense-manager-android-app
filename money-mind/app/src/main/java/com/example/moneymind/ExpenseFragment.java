package com.example.moneymind;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


/**
 * A simple {@link Fragment} subclass.
 */
public class ExpenseFragment extends Fragment {

    public ExpenseFragment() {
        // Required empty public constructor
    }



    //@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_expense, container, false);
        Spinner categorySpinner = (Spinner) v.findViewById(R.id.CategorySpinner);

        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<String>(v.getContext(),
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.Categories));
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(categoryAdapter);

//        //spinner
//        Spinner mySpinner = (Spinner) findViewById(R.id.spinner1);
//        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(WelcomeActivity.this,
//                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Currency));
//        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        mySpinner.setAdapter(myAdapter);

        return inflater.inflate(R.layout.fragment_expense, container, false);
    }

}
