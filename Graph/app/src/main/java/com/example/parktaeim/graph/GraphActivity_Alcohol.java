package com.example.parktaeim.graph;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class GraphActivity_Alcohol extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener{


    final int iOneWeek = 1;
    final int iOneMonth = 2;
    final int i3Month = 3;
    final int i6Month = 6;

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
        BarChartSet(iOneWeek);
        ChartView();

        mContext = GraphActivity_Alcohol.this;
        initSegmentbuttons();

        //EditText 클릭시 키보드 안나오게 (datePickerDialog 만 뜨게)
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
        labels.add("may");
        labels.add("june");

        ArrayList<Entry> entries = new ArrayList<>();
        entries.add(new BarEntry(0,17f));
        entries.add(new BarEntry(1,14f));
        entries.add(new BarEntry(2,30f));
        entries.add(new BarEntry(3,4f));
        entries.add(new BarEntry(4,10f));
        entries.add(new BarEntry(5,6f));

        LineDataSet dataSet = new LineDataSet(entries,"Dates");
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


    private BarEntry calcTotal(int iNum, int iMode) {


        return new BarEntry(1, 2f);
    }


    //막대 그래프
    private void BarChartSet(int iMode) {
        BarChart barChart = (BarChart) findViewById(R.id.chart_alcohol_Bar);

        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0, 1f));
        entries.add(new BarEntry(1,2f));
        entries.add(new BarEntry(2,30f));
        entries.add(new BarEntry(3,4f));
        entries.add(new BarEntry(4,10f));
        entries.add(new BarEntry(5,6f));
        entries.add(new BarEntry(6, 1f));
//        entries.add(new BarEntry(7,2f));
//        entries.add(new BarEntry(8,30f));
//        entries.add(new BarEntry(9,4f));
//        entries.add(new BarEntry(10,10f));
//        entries.add(new BarEntry(11,6f));
//        entries.add(new BarEntry(12, 1f));
//        entries.add(new BarEntry(13,2f));
//        entries.add(new BarEntry(14,30f));
//        entries.add(new BarEntry(15,4f));
//        entries.add(new BarEntry(16,10f));
//        entries.add(new BarEntry(17,6f));
//        entries.add(new BarEntry(18,10f));
//        entries.add(new BarEntry(19,6f));
//        entries.add(new BarEntry(20, 1f));
//        entries.add(new BarEntry(21,2f));
//        entries.add(new BarEntry(22,30f));
//        entries.add(new BarEntry(23,4f));
//        entries.add(new BarEntry(24,10f));
//        entries.add(new BarEntry(25,6f));

        BarDataSet barDataSet = new BarDataSet(entries,"Dates");

        ArrayList<String> labels = new ArrayList<>();

        //dateFormat
        String dateFormat = "MM-dd";
        String MonthFormat = "MM";

        //캘린더 초기화 (초기화 안해주면 버튼 누를때마다 - 마이너스 됨
        calendar = Calendar.getInstance();

        // 1주, 1개월, 3개월, 6개월 그래프 마다 x축 설정
        switch(iMode) {

            case iOneWeek:
                for(int i=0; i<7; i++) {
                    //Log.i("aaaa", "test:"+calendar.getTime());
                    labels.add(new SimpleDateFormat(dateFormat).format(calendar.getTime()));
                    calendar.add(Calendar.DAY_OF_WEEK, -1);
                }
                break;

            case iOneMonth:
                for(int i=0; i<30; i++) {
                    labels.add(new SimpleDateFormat(dateFormat).format(calendar.getTime()));
                    calendar.add(Calendar.DAY_OF_WEEK, -1);
                }
                break;

            case i3Month:
                for(int i=0; i<12; i++) {
                    labels.add(new SimpleDateFormat(dateFormat).format(calendar.getTime()));
                    calendar.add(Calendar.WEEK_OF_MONTH, -1);
                }
                break;

            case i6Month:
                for(int i=0; i<6; i++) {
                    labels.add(new SimpleDateFormat(MonthFormat).format(calendar.getTime()));
                    calendar.add(Calendar.MONTH, -1);
                }
                break;


        }


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
        barChart.notifyDataSetChanged();
        barChart.refreshDrawableState();
        barChart.invalidate();

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
                BarChartSet(iOneWeek);
                break;

            case R.id.button1Month:
                datePickerView.setVisibility(View.GONE);
                BarChartSet(iOneMonth);
                break;

            case R.id.button3Month:
                datePickerView.setVisibility(View.GONE);
                BarChartSet(i3Month);
                break;

            case R.id.button6Month:
                datePickerView.setVisibility(View.GONE);
                BarChartSet(i6Month);
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

                break;

            case R.id.button1Month:
                break;

            case R.id.button3Month:
                break;

            case R.id.button6Month:
                break;

            case R.id.buttonSelect:
                break;

        }
    }


}
