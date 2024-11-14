package edu.naita.example.weighttracker;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class updateweight extends AppCompatActivity {

    EditText w_date,weight_input;
    Button update_weight, delete_weight;

    String id,date,weight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updateweight);

        w_date = findViewById(R.id.w_date2);
        weight_input = findViewById(R.id.weight_input2);

        update_weight = findViewById(R.id.update_weight);
        delete_weight = findViewById(R.id.delete_weight);

        getAndSetIntentData();
        ActionBar ab = getSupportActionBar();
        if (ab != null){
            ab.setTitle(date);
        }


        update_weight.setOnClickListener(view ->  {
            com.example.a8gate.DatabaseHelper myDB = new com.example.a8gate.DatabaseHelper(updateweight.this);
            date = w_date.getText().toString().trim();
            weight = weight_input.getText().toString().trim();

            myDB.updateWeightData(id, date, weight);


        });
        delete_weight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });



    }

    void getAndSetIntentData() {
        if(getIntent().hasExtra("id") &&
                getIntent().hasExtra("date") && getIntent().hasExtra("weight")) {

            id = getIntent().getStringExtra("id");
            date = getIntent().getStringExtra("date");
            weight = getIntent().getStringExtra("weight");

            w_date.setText(date);
            weight_input.setText(weight);

        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete "+ date + "?");
        builder.setMessage("Do you want to Delete "+ date + " Record ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                com.example.a8gate.DatabaseHelper myDB = new com.example.a8gate.DatabaseHelper(updateweight.this);
                myDB.deleteOneRowWeight(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}