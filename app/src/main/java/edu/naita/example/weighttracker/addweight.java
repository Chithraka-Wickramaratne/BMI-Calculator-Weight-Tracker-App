package edu.naita.example.weighttracker;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.muddzdev.styleabletoastlibrary.StyleableToast;

public class addweight extends AppCompatActivity {

    EditText w_date,weight_input;
    Button save_weight;
    DatePickerDialog.OnDateSetListener setListener;

    AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addweight);

        w_date = findViewById(R.id.w_date);
        weight_input = findViewById(R.id.weight_input);
        save_weight = findViewById(R.id.save_weight);

        //initialize validation style
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        //Add validation
        awesomeValidation.addValidation(this,R.id.w_date,
                RegexTemplate.NOT_EMPTY,R.string.invalid_date);

        //awesomeValidation.addValidation(this, R.id.w_date, "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$", R.string.invalid_date);

        awesomeValidation.addValidation(this,R.id.weight_input,
                RegexTemplate.NOT_EMPTY,R.string.invalid_weight);

        save_weight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (awesomeValidation.validate()){
                    DatabaseHelper myDB = new DatabaseHelper(addweight.this);
                    myDB.addweight(w_date.getText().toString().trim(),
                            weight_input.getText().toString().trim());

                }else {
                    StyleableToast.makeText(getApplicationContext(), "Validation Failed",R.style.styleToast).show();
                }




            }
        });

        w_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(view.getContext(), android.R.style.Theme_Holo_Light_Dialog_MinWidth,setListener,2021,10,01);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });
        setListener= new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day_of_month) {
                month = month+1;
                String dates = year+"-"+month+"-"+day_of_month;
                w_date.setText(dates);
            }
        };



    }
}