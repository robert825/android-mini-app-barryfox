package cs4720.cs.virginia.edu.listexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.CalendarView;
import android.widget.TextView;

import java.util.Date;


public class EditItemActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    EditText name;
    EditText description;
    EditText latitude;
    EditText longitude;
    CalendarView calendar;

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        try {
            String[] message = intent.getStringArrayExtra(MainActivity.EXTRA_MESSAGE);

            name = (EditText) findViewById(R.id.name);
            description = (EditText) findViewById(R.id.description);
            latitude = (EditText) findViewById(R.id.latitude);
            longitude = (EditText) findViewById(R.id.longitude);
            calendar = (CalendarView) findViewById(R.id.calendarView);

            name.setText(message[0], TextView.BufferType.EDITABLE);
            description.setText(message[1], TextView.BufferType.EDITABLE);
            latitude.setText(message[2], TextView.BufferType.EDITABLE);
            longitude.setText(message[3], TextView.BufferType.EDITABLE);
            calendar.setDate(Long.parseLong(message[4]));
        } catch (NullPointerException n) {
            System.out.println("Error: null intent");
        }


    }


    public void sendMessage(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        String dateString = Long.toString(calendar.getDate());
        String[] messageName = {name.getText().toString(), description.getText().toString(), latitude.getText().toString(), longitude.getText().toString(), dateString};
        intent.putExtra(EXTRA_MESSAGE, messageName);
        startActivity(intent);
    }

}
