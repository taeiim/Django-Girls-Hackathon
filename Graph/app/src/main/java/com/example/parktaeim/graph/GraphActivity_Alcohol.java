package com.example.parktaeim.graph;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Array;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GraphActivity_Alcohol extends AppCompatActivity implements View.OnClickListener{

    private Context mContext;
    private List<Button> buttonList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alcohol_graph);

        RelativeLayout datePickerView = (RelativeLayout) findViewById(R.id.datePickerView);
        datePickerView.setVisibility(View.GONE);

        LineChartSet();
        BarChartSet();
        ChartView();

        mContext = GraphActivity_Alcohol.this;
        initSegmentbuttons();

    }

    private void ChartView() {
        final LineChart lineChart = (LineChart) findViewById(R.id.chart_alcohol);
        final BarChart barChart = (BarChart) findViewById(R.id.chart_alcohol_Bar);

        Button lineBtn = (Button) findViewById(R.id.lineBtn);
        Button barBtn = (Button) findViewById(R.id.barBtn);

        //line 버튼 클릭 시 꺾은선 그래프
        lineBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                barChart.setVisibility(View.GONE);
                lineChart.setVisibility(View.VISIBLE);
                lineChart.animateY(1000);
            }
        });

        //bar 버튼 클릭 시 막대 그래프
        barBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lineChart.setVisibility(View.GONE);
                barChart.setVisibility(View.VISIBLE);
                barChart.animateY(1000);
            }
        });
    }

    //꺾은선 그래프
    private void LineChartSet() {
        LineChart lineChart = (LineChart) findViewById(R.id.chart_alcohol);


        ArrayList<String> labels = new ArrayList<String>();
        labels.add("Jan");
        labels.add("Feb");
        labels.add("mar");
        labels.add("fafe");

        ArrayList<Entry> entries = new ArrayList<>();
        entries.add(new Entry(0f,0));
        entries.add(new Entry(2f,1));
        entries.add(new Entry(9f,2));
        entries.add(new Entry(4f,3));

        LineDataSet dataSet = new LineDataSet(entries,"# of Calls");
        LineData data = new LineData(dataSet);
        lineChart.setData(data);

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(true);
        lineChart.getAxisRight().setDrawLabels(false);

        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        lineChart.setVisibility(View.GONE);

    }

    //막대 그래프
    private void BarChartSet() {
        BarChart barChart = (BarChart) findViewById(R.id.chart_alcohol_Bar);

        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0f,0));
        entries.add(new BarEntry(2f,1));
        entries.add(new BarEntry(9f,2));
        entries.add(new BarEntry(4f,3));

        BarDataSet barDataSet = new BarDataSet(entries,"# of Calls");

        ArrayList<String> labels = new ArrayList<>();
        labels.add("Jan");
        labels.add("Feb");
        labels.add("mar");
        labels.add("fafe");


        BarData barData = new BarData(barDataSet);
        barChart.setData(barData);


        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(true);
        barChart.getAxisRight().setDrawLabels(false);

        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        barChart.animateY(1000);


    }

    private  void initSegmentbuttons(){
        buttonList = new ArrayList<>();

        Button btnWeek = (Button) findViewById(R.id.buttonWeek);
        Button btn1Month = (Button) findViewById(R.id.button1Month);
        Button btn3Month = (Button) findViewById(R.id.button3Month);
        Button btn6Month = (Button) findViewById(R.id.button6Month);
        Button btnSelect = (Button) findViewById(R.id.buttonSelect);

        btnWeek.setOnClickListener(this);
        btn1Month.setOnClickListener(this);
        btn3Month.setOnClickListener(this);
        btn6Month.setOnClickListener(this);
        btnSelect.setOnClickListener(this);

        buttonList.add(btnWeek);
        buttonList.add(btn1Month);
        buttonList.add(btn3Month);
        buttonList.add(btn6Month);
        buttonList.add(btnSelect);
    }

    public void onClick(View view){
        RelativeLayout datePickerView = (RelativeLayout) findViewById(R.id.datePickerView);
        switch (view.getId()){
            case R.id.buttonWeek:
                datePickerView.setVisibility(View.GONE);
                break;

            case R.id.button1Month:
                datePickerView.setVisibility(View.GONE);
                break;

            case R.id.button3Month:
                datePickerView.setVisibility(View.GONE);
                break;

            case R.id.button6Month:
                datePickerView.setVisibility(View.GONE);
                break;

            case R.id.buttonSelect:
                datePickerView.setVisibility(View.VISIBLE);
                break;

        }
    }


}
