package cs4720.cs.virginia.edu.listexample;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toolbar;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import android.util.Log;
import java.util.Locale;
public class AddActivity extends AppCompatActivity {


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    public String final_date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        CalendarView calendar = (CalendarView) findViewById(R.id.calendarView);

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month,
                                            int dayOfMonth) {
                int correctMonth = month + 1;
                String date = correctMonth + "/" + dayOfMonth+ "/" +year;
                Log.d("DATE", date);
                final_date = date;
            }
        });
//
//
    }


    public void sendMessage(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        EditText name = (EditText) findViewById(R.id.name);
        EditText description = (EditText) findViewById(R.id.description);
        EditText latitude = (EditText) findViewById(R.id.latitude);
        EditText longitude = (EditText) findViewById(R.id.longitude);
        CalendarView calendar = (CalendarView) findViewById(R.id.calendarView);
        String dateString = Long.toString(calendar.getDate());


        String[] messageName = {name.getText().toString(), description.getText().toString(), latitude.getText().toString(), longitude.getText().toString(), final_date};
        intent.putExtra(EXTRA_MESSAGE, messageName);
        startActivity(intent);
    }
}

