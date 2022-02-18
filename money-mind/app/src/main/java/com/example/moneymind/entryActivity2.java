package com.example.moneymind;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class entryActivity2 extends AppCompatActivity {
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
    FloatingActionButton fabSaveEntryBtn;
    EditText DateEditText, TimeEditText, AmountEditText, NoteEditText;
    TextView TransactionViewText, DateViewText, PaymentViewText, CategoryViewText, AmountViewText, NoteViewText;
    Spinner TypeSpinner, PaymentSpinner, CategorySpinner, CurrencySpinner;
    CheckBox boldCheckBox, italicCheckBox, underlineCheckBox;


    private static final String TAG = "entryActivity";
    //Context context = this;
    DatabaseHelper mDatabaseHelper;
    //SQLiteDatabase SqLiteDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry2);

        AmountEditText = findViewById(R.id.AmountEditText2);
        NoteEditText = findViewById(R.id.NoteEditText2);
        fabSaveEntryBtn = findViewById(R.id.fabSaveEntryBtn);

        TypeSpinner = findViewById(R.id.TypeSpinner2);
        CategorySpinner = findViewById(R.id.CategorySpinner2);
        CurrencySpinner = findViewById(R.id.CurrencySpinner2);
        PaymentSpinner = findViewById(R.id.PaymentSpinner2);
        TimeEditText = findViewById(R.id.TimeEditText2);
        DateEditText = findViewById(R.id.DateEditText2);
        boldCheckBox = findViewById(R.id.boldCheckBox);
        italicCheckBox = findViewById(R.id.italicCheckBox);
        underlineCheckBox = findViewById(R.id.underlineCheckBox);




        //Type of Transaction Spinner
        TypeSpinner = (Spinner) findViewById(R.id.TypeSpinner2);
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<String>(entryActivity2.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.TypeOfTransaction));
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        TypeSpinner.setAdapter(typeAdapter);


        ;

        // Category Spinner with Icons

        initCategoryList();

        //Spinner spinnerCategories = findViewById(R.id.CategorySpinner2);

        mCategoryAdapter = new CategoryAdapter(this, mCategoryList);
        CategorySpinner.setAdapter(mCategoryAdapter);

        CategorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getCategoryImage clickedItem = (getCategoryImage) parent.getItemAtPosition(position);
                String clickedCategoryName = clickedItem.getCategoryName();
                Toast.makeText(entryActivity2.this, clickedCategoryName + " selected", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        //Currency spinner
        CurrencySpinner = (Spinner) findViewById(R.id.CurrencySpinner2);
        ArrayAdapter<String> currencyAdapter = new ArrayAdapter<String>(entryActivity2.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Currency));
        currencyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        CurrencySpinner.setAdapter(currencyAdapter);

        CurrencySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String item2 = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), "Selected: " +item2,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //

            }
        });

        //Payment Method spinner
        PaymentSpinner = (Spinner) findViewById(R.id.PaymentSpinner2);
        ArrayAdapter<String> paymentAdapter = new ArrayAdapter<String>(entryActivity2.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Payment));
        paymentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        PaymentSpinner.setAdapter(paymentAdapter);

        PaymentSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position).equals("Nothing selected.")){
                    //selected nothing warning
                    Toast.makeText(parent.getContext(), "Pick an option" ,Toast.LENGTH_SHORT).show();
                }else{
                    //on selecting a spinner item
                    String item2 = parent.getItemAtPosition(position).toString();
                    Toast.makeText(parent.getContext(), "Selected:" +item2,Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //

            }
        });

        //Implementing the Time Picker for the TimeEditText2
        TimeEditText = findViewById(R.id.TimeEditText2);
        TimeEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                currentMinute = calendar.get(Calendar.MINUTE);

                TimePicker = new TimePickerDialog(entryActivity2.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(android.widget.TimePicker view, int hourOfDay, int minute) {
                        TimeEditText.setText(String.format("%02d:%02d", hourOfDay, minute));
                    }
                },currentHour,currentMinute,true);
                TimePicker.show();
            }
        });

        //Implementing the Date Picker for the DateEditText2
        DateEditText = findViewById(R.id.DateEditText2);
        DateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                currentYear = calendar.get(Calendar.YEAR);
                currentMonth = calendar.get(Calendar.MONTH);
                currentDay = calendar.get(Calendar.DAY_OF_MONTH);
                DatePicker = new DatePickerDialog(entryActivity2.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
                        //DateEdit.setText(dayOfMonth +"-" + (month+1) + "-" + year);
                        DateEditText.setText(String.format("%02d-%02d-%04d", dayOfMonth, (month+1), year));
                    }
                }, currentYear, currentMonth, currentDay );
                DatePicker.show();
            }
        });



        // on click listener for adding Data to the Table (for recyclerview)
        fabSaveEntryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // if-statements to check if Data was entered correctly

                boolean AmountisGood = false;
                boolean DateisGood = false;

                if (TextUtils.isEmpty(DateEditText.getText().toString())) {
                    Toast.makeText(entryActivity2.this, "The Date needs to be entered. ", Toast.LENGTH_SHORT).show();
                }
                else{
                    DateisGood = true;
                }

                if( TextUtils.isEmpty(AmountEditText.getText()) || AmountEditText.getText().toString().matches("0")
                        || AmountEditText.getText().toString().matches("0.0") || AmountEditText.getText().toString().matches("0.00") || Double.parseDouble(AmountEditText.getText().toString()) <= 0) {
                    AmountEditText.setError("The Amount cannot be empty and smaller or equal to zero.");
                    Toast.makeText(entryActivity2.this, "The Amount cannot be 0. Please change accordingly.", Toast.LENGTH_SHORT).show();
                }
                else{
                    AmountisGood = true;
                }

                if( CategorySpinner.getSelectedItemPosition() == 0 || PaymentSpinner.getSelectedItem().toString().equals("nothing selected")) {
                    Toast.makeText(entryActivity2.this, "Entry could not be added. Please make sure the required fields are filled out (category and payment method).", Toast.LENGTH_SHORT).show();
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
                String NoteStyle = "";
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

                    if (CategorySpinner.getSelectedItemPosition() != 0 && PaymentSpinner.getSelectedItemPosition() != 0
                             && AmountisGood == true && DateisGood == true && TypeSpinner.getSelectedItemPosition() == 1 && categoryisGood == true) {
                        DatabaseHelper2 db2 = new DatabaseHelper2(entryActivity2.this);
                        db2.addEntry(TypeSpinner.getSelectedItem().toString(), DateEditText.getText().toString(), TimeEditText.getText().toString(), PaymentSpinner.getSelectedItem().toString(),
                                category_selected, Double.parseDouble(AmountEditText.getText().toString()), CurrencySpinner.getSelectedItem().toString(),
                                Double.parseDouble("0"), CurrencySpinner.getSelectedItem().toString(), NoteEditText.getText().toString(), NoteStyle
                        );
                        // go back to Welcome Activity
                        startActivity(new Intent(getApplicationContext(), WelcomeActivity.class));
                    }



                // Case 2: for Expense
                    if (CategorySpinner.getSelectedItemPosition() != 0 && PaymentSpinner.getSelectedItemPosition() != 0
                            && AmountisGood == true && DateisGood == true && TypeSpinner.getSelectedItemPosition() == 0) {
                        DatabaseHelper2 db2 = new DatabaseHelper2(entryActivity2.this);
                        db2.addEntry(TypeSpinner.getSelectedItem().toString(), DateEditText.getText().toString(), TimeEditText.getText().toString(), PaymentSpinner.getSelectedItem().toString(),
                                category_selected, Double.parseDouble("0"), CurrencySpinner.getSelectedItem().toString(),
                                Double.parseDouble(AmountEditText.getText().toString()), CurrencySpinner.getSelectedItem().toString(), NoteEditText.getText().toString(), NoteStyle
                        );
                        // go back to Welcome Activity
                        startActivity(new Intent(getApplicationContext(), WelcomeActivity.class));
                    }

               // }
            }
        });


        AmountEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if ( hasFocus == true ) {
                    if(AmountEditText.getText().toString().compareTo("0.00")==0) {
                        AmountEditText.setText("");
                    }
                }
            }
        });
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





