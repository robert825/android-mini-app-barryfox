package cs4720.cs.virginia.edu.listexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toolbar;


public class AddActivity extends AppCompatActivity {

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
    }


    public void sendMessage(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        EditText name = (EditText) findViewById(R.id.name);
        EditText description = (EditText) findViewById(R.id.description);
        EditText latitude = (EditText) findViewById(R.id.latitude);
        EditText longitude = (EditText) findViewById(R.id.longitude);
        String[] messageName = {name.getText().toString(), description.getText().toString(),latitude.getText().toString(),longitude.getText().toString()};
        intent.putExtra(EXTRA_MESSAGE, messageName);
        startActivity(intent);
    }
}
