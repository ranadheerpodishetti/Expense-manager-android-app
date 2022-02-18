package com.example.moneymind;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;

public class changeEntryActivity extends AppCompatActivity {

    private static final String TAG = "changeEntryActivity";
    //For TimePicker. Calender gives us the current time, date, month and year
    EditText TimeEdit;
    TimePickerDialog TimePicker;
    Calendar calendar;
    int currentHour;
    int currentMinute;
    int currentDay;
    int currentMonth;
    int currentYear;
    private ArrayList<getCategoryImage> mCategoryList;
    private CategoryAdapter mCategoryAdapter;

    //For DatePicker
    EditText DateEdit;
    DatePickerDialog DatePicker;

    // references to buttons and other controls on the layout
    FloatingActionButton fabChangeEntryBtn, fabDeleteEntryBtn;
    EditText DateEditText, TimeEditText, AmountEditText, NoteEditText;
    TextView TransactionViewText, DateViewText, PaymentViewText, CategoryViewText, AmountViewText, NoteViewText;
    Spinner TypeSpinner, PaymentSpinner, CategorySpinner, CurrencySpinner;
    CheckBox boldCheckBox, italicCheckBox, underlineCheckBox;
    String NoteStyle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_entry);



        TypeSpinner = findViewById(R.id.ChangeTypeSpinner2);
        DateEditText = findViewById(R.id.ChangeDateEditText2);
        TimeEditText = findViewById(R.id.ChangeTimeEditText2);
        CategorySpinner = findViewById(R.id.ChangeCategorySpinner2);
        PaymentSpinner = findViewById(R.id.ChangePaymentSpinner2);
        AmountEditText = findViewById(R.id.ChangeAmountEditText2);
        CurrencySpinner = findViewById(R.id.ChangeCurrencySpinner2);
        NoteEditText = findViewById(R.id.ChangeNoteEditText2);
        fabChangeEntryBtn = findViewById(R.id.fabChangeEntryBtn);
        fabDeleteEntryBtn = findViewById(R.id.fabDeleteEntryBtn);
        boldCheckBox = findViewById(R.id.boldChangeCheckBox);
        italicCheckBox = findViewById(R.id.italicChangeCheckBox);
        underlineCheckBox = findViewById(R.id.underlineChangeCheckBox);

        // Spinners

        //Type of Transaction Spinner
        TypeSpinner = (Spinner) findViewById(R.id.ChangeTypeSpinner2);
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<String>(changeEntryActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.TypeOfTransaction));
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        TypeSpinner.setAdapter(typeAdapter);


        // Category Spinner with Icons

        initCategoryList();


     //   Spinner spinnerCategories = findViewById(R.id.ChangeCategorySpinner2);

        mCategoryAdapter = new CategoryAdapter(this, mCategoryList);
        CategorySpinner.setAdapter(mCategoryAdapter);

//        CategorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                getCategoryImage clickedItem = (getCategoryImage) parent.getItemAtPosition(position);
//                String clickedCategoryName = clickedItem.getCategoryName();
//                Toast.makeText(changeEntryActivity.this, clickedCategoryName + " selected", Toast.LENGTH_SHORT).show();
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });

    //Currency spinner
        CurrencySpinner = (Spinner) findViewById(R.id.ChangeCurrencySpinner2);
        ArrayAdapter<String> currencyAdapter = new ArrayAdapter<String>(changeEntryActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Currency));
        currencyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        CurrencySpinner.setAdapter(currencyAdapter);

    //Payment Method spinner
        PaymentSpinner = (Spinner) findViewById(R.id.ChangePaymentSpinner2);
        ArrayAdapter<String> paymentAdapter = new ArrayAdapter<String>(changeEntryActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Payment));
        paymentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        PaymentSpinner.setAdapter(paymentAdapter);

    // Implementing the Time Picker for the TimeEditText2
        TimeEditText = findViewById(R.id.ChangeTimeEditText2);
        TimeEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                currentMinute = calendar.get(Calendar.MINUTE);

                TimePicker = new TimePickerDialog(changeEntryActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(android.widget.TimePicker view, int hourOfDay, int minute) {
                        TimeEditText.setText(String.format("%02d:%02d", hourOfDay, minute));
                    }
                },currentHour,currentMinute,true);
                TimePicker.show();
            }
        });

        //Implementing the Date Picker for the DateEditText2
        DateEditText = findViewById(R.id.ChangeDateEditText2);

        DateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                currentYear = calendar.get(Calendar.YEAR);
                currentMonth = calendar.get(Calendar.MONTH);
                currentDay = calendar.get(Calendar.DAY_OF_MONTH);
                DatePicker = new DatePickerDialog(changeEntryActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
                        //DateEdit.setText(dayOfMonth +"-" + (month+1) + "-" + year);
                        DateEditText.setText(String.format("%02d-%02d-%04d", dayOfMonth, (month+1), year));
                    }
                }, currentYear, currentMonth, currentDay );
                DatePicker.show();
            }
        });



        // getExtra to get the clicked Entry

        if(getIntent().hasExtra("selected_entry_id")){
            final String entry_id = getIntent().getStringExtra("selected_entry_id");
            final String entry_type = getIntent().getStringExtra("selected_entry_type");
            final String entry_date = getIntent().getStringExtra("selected_entry_date");
            final String entry_time = getIntent().getStringExtra("selected_entry_time");
            final String entry_payment = getIntent().getStringExtra("selected_entry_payment");
            final String entry_category = getIntent().getStringExtra("selected_entry_category");
            final Double entry_amountincome = getIntent().getDoubleExtra("selected_entry_amountincome", 0.00);
            final String entry_currency = getIntent().getStringExtra("selected_entry_currency");
            final Double entry_amountexpense = getIntent().getDoubleExtra("selected_entry_amountexpense", 0.00);
            final String entry_currency2 = getIntent().getStringExtra("selected_entry_currency2");
            final String entry_note = getIntent().getStringExtra("selected_entry_note");
            final String entry_notestyle = getIntent().getStringExtra("selected_entry_notestyle");

            // create Arraylist with the category names

           // Toast.makeText(changeEntryActivity.this, entry_category, Toast.LENGTH_SHORT).show();

            ArrayList<String> categoryname2 = new ArrayList<String>();

            categoryname2.add("Food");
            categoryname2.add("Social Life");
            categoryname2.add("Beauty");
            categoryname2.add("Clothes");
            categoryname2.add("Hobby");
            categoryname2.add("Pet");
            categoryname2.add("Health");
            for(int i = 0; i<categoryname2.size(); i++) {
                if(categoryname2.get(i).equals(entry_category)) {
                   // Toast.makeText(changeEntryActivity.this, categoryname2.get(i), Toast.LENGTH_SHORT).show();
                    final int categoryindex = i+1;
                    CategorySpinner.post(new Runnable() {
                        @Override
                        public void run() {
                        CategorySpinner.setSelection(categoryindex);
                        }
                    });

                    break;
                }
            }

            // set Checkboxes for clicked Entry

            NoteStyle = entry_notestyle;
            // Case0: No Style
            if(NoteStyle.equals("0")){
                boldCheckBox.setChecked(false);
                italicCheckBox.setChecked(false);
                underlineCheckBox.setChecked(false);
            }
            // Case1: Bold
            if(NoteStyle.equals("1")){
                boldCheckBox.setChecked(true);
                italicCheckBox.setChecked(false);
                underlineCheckBox.setChecked(false);
            }
            // Case2: Underline
            if(NoteStyle.equals("2")){
                boldCheckBox.setChecked(false);
                italicCheckBox.setChecked(false);
                underlineCheckBox.setChecked(true);
            }
            // Case3: Italic
            if(NoteStyle.equals("3")){
                boldCheckBox.setChecked(false);
                italicCheckBox.setChecked(true);
                underlineCheckBox.setChecked(false);
            }
            // Case4: Bold + Italic
            if(NoteStyle.equals("4")){
                boldCheckBox.setChecked(true);
                italicCheckBox.setChecked(true);
                underlineCheckBox.setChecked(false);
            }
            // Case5: Bold + Italic + Underline
            if(NoteStyle.equals("5")){
                boldCheckBox.setChecked(true);
                italicCheckBox.setChecked(true);
                underlineCheckBox.setChecked(true);
            }
            // Case6: Bold + Underline
            if(NoteStyle.equals("6")){
                boldCheckBox.setChecked(true);
                italicCheckBox.setChecked(false);
                underlineCheckBox.setChecked(true);
            }
            // Case7: Italic + Underline
            if(NoteStyle.equals("7")){
                boldCheckBox.setChecked(false);
                italicCheckBox.setChecked(true);
                underlineCheckBox.setChecked(true);
            }


            //   Log.d(TAG, "onCreate: " + entry_type);
//            Toast.makeText(changeEntryActivity.this, entry_amountexpense.toString(), Toast.LENGTH_SHORT).show();

            // put Data in fields
            DateEditText.setText(entry_date);
            TimeEditText.setText(entry_time);
            NoteEditText.setText(entry_note);
            PaymentSpinner.setSelection(getIndex(PaymentSpinner, entry_payment));
            CategorySpinner.setSelection(getIndex(CategorySpinner, entry_category));
            if(entry_type != null) {
                if (entry_type.equals("Expense")) {
                    TypeSpinner.setSelection(0);
                    AmountEditText.setText(entry_amountexpense.toString());
                    CurrencySpinner.setSelection(getIndex(CurrencySpinner, entry_currency));
                } else if (entry_type.equals("Income")) {
                    TypeSpinner.setSelection(1);
                    AmountEditText.setText(entry_amountincome.toString());
                    CurrencySpinner.setSelection(getIndex(CurrencySpinner, entry_currency2));
                }
            }

            // Delete data on click
            fabDeleteEntryBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatabaseHelper2 db2 = new DatabaseHelper2(changeEntryActivity.this);
                    boolean success;
                    if(entry_id.isEmpty()) {
                        success = false;
                    }
                    else {
                        success = db2.deleteOne(entry_id, entry_type, entry_date, entry_time, entry_payment, entry_category, entry_amountincome, entry_currency, entry_amountexpense, entry_currency2, entry_note, entry_notestyle);
                       // Toast.makeText(changeEntryActivity.this, "Deleting successful." + success, Toast.LENGTH_SHORT).show();
                    }
                    if(success == false) {
                        Toast.makeText(changeEntryActivity.this, "Deleting not successful.", Toast.LENGTH_SHORT).show();
                    }
                    else if(success == true) {
                        Toast.makeText(changeEntryActivity.this, "Deleting successful.", Toast.LENGTH_SHORT).show();
                    }
                    // go back to Welcome Activity
                    startActivity(new Intent(getApplicationContext(), WelcomeActivity.class));
                }

            });

        }

        // onClick for updating the clicked Entry
        fabChangeEntryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // check if there is an ID for the selected entry
                if(getIntent().hasExtra("selected_entry_id")) {
                    // if-statements to check if Data was entered correctly
                    boolean AmountisGood = false;
                    boolean DateisGood = false;


                    if (TextUtils.isEmpty(DateEditText.getText().toString())) {
                        Toast.makeText(changeEntryActivity.this, "The Date needs to be entered. ", Toast.LENGTH_SHORT).show();
                    } else {
                        DateisGood = true;

                    }

                    if (TextUtils.isEmpty(AmountEditText.getText()) || AmountEditText.getText().toString().matches("0")
                            || AmountEditText.getText().toString().matches("0.0") || AmountEditText.getText().toString().matches("0.00") || Double.parseDouble(AmountEditText.getText().toString()) <= 0) {
                        AmountEditText.setError("The Amount cannot be empty and smaller or equal to zero.");
                        Toast.makeText(changeEntryActivity.this, "The Amount cannot be 0. Please change accordingly.", Toast.LENGTH_SHORT).show();
                    } else {
                        AmountisGood = true;

                    }

                    if (CategorySpinner.getSelectedItemPosition() == 0 || PaymentSpinner.getSelectedItem().toString().equals("nothing selected")) {
                        Toast.makeText(changeEntryActivity.this, "Entry could not be added. Please make sure the required fields are filled out (category and payment method).", Toast.LENGTH_SHORT).show();
                    }

                    // Workaround for saving the Strings of the categories
                    String category_selected;
                    boolean categoryisGood;
                    if(CategorySpinner.getSelectedItemPosition() != 0) {
                        ArrayList<String> categoryname = new ArrayList<String>();

                        categoryname.add("Food");
                        categoryname.add("Social Life");
                        categoryname.add("Beauty");
                        categoryname.add("Clothes");
                        categoryname.add("Hobby");
                        categoryname.add("Pet");
                        categoryname.add("Health");

                        category_selected = categoryname.get(CategorySpinner.getSelectedItemPosition()-1);
                        //    Toast.makeText(entryActivity2.this, category_selected, Toast.LENGTH_SHORT).show();
                        categoryisGood = true;
                    }
                    else {
                        category_selected = "";
                        categoryisGood = false;
                    }

                    // Editing the TextStyle of the Notes
                    NoteStyle = "";
                    // Case0: No Style
                    if(boldCheckBox.isChecked()== false && italicCheckBox.isChecked() == false && underlineCheckBox.isChecked() == false){
                        NoteStyle = "0";
                    }
                    // Case1: Bold
                    if(boldCheckBox.isChecked() == true && italicCheckBox.isChecked() == false && underlineCheckBox.isChecked() == false) {
                        NoteStyle = "1";
                    }
                    // Case2: Underline
                    if(boldCheckBox.isChecked() == false && italicCheckBox.isChecked() == false && underlineCheckBox.isChecked() == true) {
                        NoteStyle = "2";
                    }
                    // Case3: Italic
                    if(boldCheckBox.isChecked() == false && italicCheckBox.isChecked() == true && underlineCheckBox.isChecked() == false) {
                        NoteStyle = "3";
                    }
                    // Case4: Bold + Italic
                    if(boldCheckBox.isChecked() == true && italicCheckBox.isChecked() == true && underlineCheckBox.isChecked() == false) {
                        NoteStyle = "4";
                    }
                    // Case5: Bold + Italic + Underline
                    if(boldCheckBox.isChecked() == true && italicCheckBox.isChecked() == true && underlineCheckBox.isChecked() == true) {
                        NoteStyle = "5";
                    }
                    // Case6: Bold + Underline
                    if(boldCheckBox.isChecked() == true && italicCheckBox.isChecked() == false && underlineCheckBox.isChecked() == true) {
                        NoteStyle = "6";
                    }
                    // Case7: Italic + Underline
                    if(boldCheckBox.isChecked() == false && italicCheckBox.isChecked() == true && underlineCheckBox.isChecked() == true) {
                        NoteStyle = "7";
                    }

                    // Case 1: for Income
                    //     if( TextUtils.isEmpty(AmountEditText.getText())) {

                    if (CategorySpinner.getSelectedItemPosition() != 0 && PaymentSpinner.getSelectedItemPosition() != 0
                            && AmountisGood == true && DateisGood == true && TypeSpinner.getSelectedItemPosition() == 1 && categoryisGood) {
                        DatabaseHelper2 db2 = new DatabaseHelper2(changeEntryActivity.this);
                        db2.updateEntry( getIntent().getStringExtra("selected_entry_id"), TypeSpinner.getSelectedItem().toString(), DateEditText.getText().toString(), TimeEditText.getText().toString(), PaymentSpinner.getSelectedItem().toString(),
                                category_selected, Double.parseDouble(AmountEditText.getText().toString()), CurrencySpinner.getSelectedItem().toString(),
                                Double.parseDouble("0"), CurrencySpinner.getSelectedItem().toString(), NoteEditText.getText().toString(), NoteStyle
                        );

                        // go back to Welcome Activity
                        startActivity(new Intent(getApplicationContext(), WelcomeActivity.class));
                    }

                    // Case 2: for Expense

                    if (CategorySpinner.getSelectedItemPosition() != 0 && PaymentSpinner.getSelectedItemPosition() != 0
                            && AmountisGood == true && DateisGood == true && TypeSpinner.getSelectedItemPosition() == 0 && categoryisGood) {
                        DatabaseHelper2 db2 = new DatabaseHelper2(changeEntryActivity.this);
                        db2.updateEntry(getIntent().getStringExtra("selected_entry_id"), TypeSpinner.getSelectedItem().toString(), DateEditText.getText().toString(), TimeEditText.getText().toString(), PaymentSpinner.getSelectedItem().toString(),
                                category_selected, Double.parseDouble("0"), CurrencySpinner.getSelectedItem().toString(),
                                Double.parseDouble(AmountEditText.getText().toString()), CurrencySpinner.getSelectedItem().toString(), NoteEditText.getText().toString(), NoteStyle
                        );

                        // go back to Welcome Activity
                        startActivity(new Intent(getApplicationContext(), WelcomeActivity.class));
                    }
                    // }
                }
                else {
                    Toast.makeText(changeEntryActivity.this, "Error (No Data available to update.", Toast.LENGTH_SHORT).show();
                }



            }

        });



//        lv_daily.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                EntryModel clickedEntry = (EntryModel) parent.getItemAtPosition(position);
//                databaseHelper.deleteOne(clickedEntry);
//                // update listview
//                dailyentryArrayAdapter = new ArrayAdapter<EntryModel>(getActivity(), android.R.layout.simple_list_item_1, databaseHelper.getAllEntries());
//                lv_daily.setAdapter(dailyentryArrayAdapter);
//                Toast.makeText(getActivity(), "Deleted " + clickedEntry.toString(), Toast.LENGTH_SHORT).show();
//            }
//        });

//        fabDeleteEntryBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                clickedEntry = (EntryModel);
//            }
//        });


    // Find the Position for the Spinners for the clicked data
    }
    private int getIndex(Spinner spinner, String myString) {
        for (int i = 0; i<spinner.getCount(); i++) {
            if(spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)) {
                return i;
            }
        }
        return 0;
    }

    private void initCategoryList() {

        mCategoryList = new ArrayList<>();
        mCategoryList.add(new getCategoryImage("no category", R.drawable.nothing_selected));
        mCategoryList.add(new getCategoryImage("Food", R.drawable.food));
        mCategoryList.add(new getCategoryImage("Social Life", R.drawable.social_life));
        mCategoryList.add(new getCategoryImage("Beauty", R.drawable.beauty));
        mCategoryList.add(new getCategoryImage("Clothes", R.drawable.clothes));
        mCategoryList.add(new getCategoryImage("Hobby", R.drawable.hobby));
        mCategoryList.add(new getCategoryImage("Pet", R.drawable.pet));
        mCategoryList.add(new getCategoryImage("Health", R.drawable.health));
    }

}
