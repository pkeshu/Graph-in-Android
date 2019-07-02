package com.keshar.barchartdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.keshar.barchartdemo.com.keshar.barchartdemo.model.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private TextView addTripV;
    private Spinner tripDaySpinner;
    private EditText addTripEdt;
    private Button addValueInDbBtn;
    private Button showChartBtn, initialAddTrip;
    private FirebaseFirestore firebaseDatabase;
    private String dayList[] = {"Select day", "Mon", "Tue", "Wed", "Thu", "Fir", "Sat", "Sun"};
    private List<Model> tripList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViewe();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, dayList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tripDaySpinner.setAdapter(adapter);
        tripDaySpinner.setOnItemSelectedListener(this);
//        firebaseDatabase = FirebaseFirestore.getInstance();
        initialAddTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveInitialTripDetailInDataBase();
            }
        });
        showChartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),BarChartActivity.class));

            }
        });

    }

    private void bindViewe() {
        addTripV = findViewById(R.id.trip);
        tripDaySpinner = findViewById(R.id.trip_day);
        addTripEdt = findViewById(R.id.trip_count);
        addValueInDbBtn = findViewById(R.id.add_trip_btn1);
        showChartBtn = findViewById(R.id.show_weekly_chart_btn);
        initialAddTrip = findViewById(R.id.initial_add);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String item = adapterView.getSelectedItem().toString();
        Log.d("testing:", "Selected Item is: " + item);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void saveInitialTripDetailInDataBase() {
        Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show();
        Model model = new Model("Sun", 0);

        firebaseDatabase.collection("week1").document("Sun").set(model)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(MainActivity.this, "Trip added", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }


}
