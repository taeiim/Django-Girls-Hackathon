package com.example.parktaeim.graph;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
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
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Array;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class GraphActivity_Alcohol extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener{

    private Context mContext;
    private List<Button> buttonList = null;
    private EditText startDateEditText ;
    private EditText finishDateEditText;
    private int sYear,sMonth,sDay;
    private int fYear,fMonth,fDay;
    static final int DATE_ID = 0;
    Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alcohol_graph);

        RelativeLayout datePickerView = (RelativeLayout) findViewById(R.id.datePickerView);
        startDateEditText =(EditText) findViewById(R.id.startdateEditText);
        finishDateEditText = (EditText) findViewById(R.id.finishdateEditText);
        datePickerView.setVisibility(View.GONE);

        LineChartSet();
        BarChartSet();
        ChartView();

        mContext = GraphActivity_Alcohol.this;
        initSegmentbuttons();

        startDateEditText.setInputType(0);
        finishDateEditText.setInputType(0);
        startDateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(GraphActivity_Alcohol.this,dateSetListener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });
    }

    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
            String msg = String.format("%d / %d / %d",year,monthOfYear+1,dayOfMonth);
            startDateEditText.setText(msg);

        }
    };


//    @Override
//    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
//        sYear = year;
//        sMonth = monthOfYear;
//        sDay = dayOfMonth;
//        updateDisplay();
//
//    }
//
//
//
//    private void DateSet() {
//        sYear = calendar.get(Calendar.YEAR);
//        sMonth = calendar.get(Calendar.MONTH);
//        sDay = calendar.get(Calendar.DAY_OF_MONTH);
//
//        startDateEditText.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                new DatePickerDialog(GraphActivity_Alcohol.this,dateSetListener,sYear,sMonth,sDay).show();
//            }
//        });
//    }




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
        entries.add(new Entry(0,0f));
        entries.add(new Entry(1,2f));
        entries.add(new Entry(2,9f));
        entries.add(new Entry(3,4f));

        LineDataSet dataSet = new LineDataSet(entries,"# of Calls");
        LineData data = new LineData(dataSet);
        lineChart.setData(data);

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(true);
        lineChart.getAxisRight().setDrawLabels(false);

        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        lineChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));

        lineChart.setVisibility(View.GONE);

    }

    //막대 그래프
    private void BarChartSet() {
        BarChart barChart = (BarChart) findViewById(R.id.chart_alcohol_Bar);

        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0,0f));
        entries.add(new BarEntry(1,2f));
        entries.add(new BarEntry(2,3f));
        entries.add(new BarEntry(3,4f));

        BarDataSet barDataSet = new BarDataSet(entries,"Dates");

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
        barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));


    }

    //1주, 1개월, 3개월, 6개월, 직접 입력 SegmentedGroup
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


    //SegmentedGroup 각각 클릭했을 때
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

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
        RelativeLayout datePickerView = (RelativeLayout) findViewById(R.id.datePickerView);

        switch (i){
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
