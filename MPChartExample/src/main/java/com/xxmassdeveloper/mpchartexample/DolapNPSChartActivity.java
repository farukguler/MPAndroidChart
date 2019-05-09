
package com.xxmassdeveloper.mpchartexample;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.ChartHighlighter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.model.GradientColor;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.xxmassdeveloper.mpchartexample.notimportant.DemoBase;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("SameParameterValue")
public class DolapNPSChartActivity extends DemoBase {

    private PieChart chart;
    private PieChart chart2;
    private AppCompatImageView emoji;
    private AppCompatTextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piechart_dolap);

        setTitle("Dolap NPS");
        initChart1();
        initChart2();
    }

    private void initChart1() {
        chart = findViewById(R.id.chart1);


        chart.getDescription().setEnabled(false);
        chart.setDrawCenterText(false);

        chart.setDrawHoleEnabled(true);
        chart.setHoleColor(Color.TRANSPARENT);
        chart.setHoleRadius(58f);


        chart.setRotationEnabled(false);
        chart.setHighlightPerTapEnabled(true);

        chart.setMaxAngle(270f); // HALF CHART
        chart.setRotationAngle(135f);


        setData(11, 100);


        chart.getLegend().setEnabled(false);

        // entry label styling
//        chart.setEntryLabelColor(Color.BLACK);
        chart.setEntryLabelTypeface(tfBold);
        chart.setEntryLabelTextSize(16f);


        chart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                List<Integer> colors = chart.getData().getDataSetByIndex(h.getDataSetIndex()).getColors();
                int selectedIndex = (int) h.getX();
                boolean shouldInvalidate = true;
                for (int i = 0; i < colors.size(); i++) {
                    if ((int) h.getX() == i) {
                        if (colors.get(selectedIndex) != ColorTemplate.REFERENCE_COLORS[i]) {
                            chart.getData().getDataSetByIndex(h.getDataSetIndex()).setColorByIndex(ColorTemplate.REFERENCE_COLORS[i], selectedIndex);
//                            colors.set(selectedIndex, ColorTemplate.REFERENCE_COLORS[i]);
                            chart.getData().getDataSetByIndex(h.getDataSetIndex()).setLabelTextColor(Color.WHITE, selectedIndex);
                        } else {
                            shouldInvalidate = false;
                        }
                    } else {
                        chart.getData().getDataSetByIndex(h.getDataSetIndex()).setLabelTextColor(Color.BLACK, i);
//                        colors.set(i, ColorTemplate.DEFAULT_COLORS[0]);
                        chart.getData().getDataSetByIndex(h.getDataSetIndex()).setColorByIndex(ColorTemplate.DEFAULT_COLORS[0], i);
                    }
                }

                if (shouldInvalidate)
                    chart.invalidate();
            }

            @Override
            public void onNothingSelected() {

            }
        });
    }

    private void initChart2() {
        chart2 = findViewById(R.id.chart2);


        chart2.getDescription().setEnabled(false);
        chart2.setDrawCenterText(false);

        chart2.setDrawHoleEnabled(true);
        chart2.setHoleColor(Color.TRANSPARENT);
        chart2.setHoleRadius(92f);


        chart2.setRotationEnabled(false);
        chart2.setHighlightPerTapEnabled(true);

        chart2.setMaxAngle(270f); // HALF CHART
        chart2.setRotationAngle(135f);


        setData2(11, 100);

        chart2.getLegend().setEnabled(false);
    }

    private void setData(int count, float range) {

        ArrayList<PieEntry> values = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            values.add(new PieEntry((range / count), String.valueOf(i)));
        }

        PieDataSet dataSet = new PieDataSet(values, "");
        dataSet.setSliceSpace(2f);
        dataSet.setSelectionShift(10f);
        dataSet.setColors(ColorTemplate.DEFAULT_COLORS);
        dataSet.setHighlightEnabled(true);
        dataSet.setLabelTextColors(ColorTemplate.getDefaultTextColorForLabel(values.size()));


        PieData data = new PieData(dataSet);
        chart.setData(data);

        for (IDataSet<?> set : chart.getData().getDataSets()) {
            set.setDrawValues(!set.isDrawValuesEnabled());
        }

        chart.invalidate();
    }

    private void setData2(int count, float range) {

        ArrayList<PieEntry> values = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            values.add(new PieEntry((range / count), String.valueOf(i)));
        }

        PieDataSet dataSet = new PieDataSet(values, "");
        dataSet.setSliceSpace(2f);


        dataSet.setColors(ColorTemplate.REFERENCE_COLORS);
        dataSet.setHighlightEnabled(false);

        PieData data = new PieData(dataSet);
        chart2.setData(data);

        for (IDataSet<?> set : chart2.getData().getDataSets()) {
            set.setDrawValues(!set.isDrawValuesEnabled());
        }
        chart2.setDrawEntryLabels(!chart2.isDrawEntryLabelsEnabled());
        chart2.setTouchEnabled(false);
        chart2.invalidate();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.only_github, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.viewGithub: {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://github.com/PhilJay/MPAndroidChart/blob/master/MPChartExample/src/com/xxmassdeveloper/mpchartexample/HalfPieChartActivity.java"));
                startActivity(i);
                break;
            }
        }

        return true;
    }

    @Override
    public void saveToGallery() { /* Intentionally left empty */ }
}
