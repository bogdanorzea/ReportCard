package com.bogdanorzea.reportcardapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ReportCard reportCard = new ReportCard("Bogdan", "Orzea");
        reportCard.setSchoolName("Udacity");
        reportCard.setSchoolYear(2017);
        reportCard.addSubject(new Subject("Musical Structure", 50, 60, 70, 80));
        reportCard.addSubject(new Subject("ReportCard", 50, 60, 70, 80));
        reportCard.addSubject(new Subject("Tour Guide App", 50, 60, 70, 80));

        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(reportCard.prettyPrint());

    }
}
