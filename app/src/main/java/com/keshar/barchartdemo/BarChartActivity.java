package com.keshar.barchartdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.keshar.barchartdemo.com.keshar.barchartdemo.model.Model;

import java.util.ArrayList;
import java.util.List;

public class BarChartActivity extends AppCompatActivity {
    private BarChart mBarChart;
    private List<Model> tripList = new ArrayList<>();
    private TextView totalTripCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);
        mBarChart = findViewById(R.id.barchart);
        totalTripCount = findViewById(R.id.total_trip);
        getTripChart();
//        tripList.add(new Model("Mon",254));
//        tripList.add(new Model("Mon",178));
//        tripList.add(new Model("Mon",222));
//        tripList.add(new Model("Mon",230));
//        tripList.add(new Model("Mon",179));
//        tripList.add(new Model("Sat",134));
//        tripList.add(new Model("Sun",99));

    }

    private void getTripChart() {
        mBarChart.setVisibility(View.VISIBLE);
        List<BarEntry> barEntryList = new ArrayList<>();
        barEntryList.add(new BarEntry(1, 167));
        barEntryList.add(new BarEntry(2, 180));
        barEntryList.add(new BarEntry(3, 124));
        barEntryList.add(new BarEntry(4, 56));
        barEntryList.add(new BarEntry(5, 0));
        barEntryList.add(new BarEntry(6, 0));
        barEntryList.add(new BarEntry(7, 0));
        float totalTrip=0;
        for (BarEntry barE : barEntryList) {
            Log.d("barValue", String.valueOf(barE.getY()));
            totalTrip+=barE.getY();
        }
        totalTripCount.setText("Total Trip is: "+String.valueOf(totalTrip));
        BarDataSet barDataSet = new BarDataSet(barEntryList, "Trip");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        BarData barData = new BarData(barDataSet);
        mBarChart.animateY(5000);
        mBarChart.setData(barData);
        Description description = new Description();
        description.setText("Trip rate per Day");
        mBarChart.setDescription(description);
        mBarChart.invalidate();


    }
}
