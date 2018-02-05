package cs4720.cs.virginia.edu.listexample;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
<<<<<<< HEAD
import android.view.ViewParent;
import android.widget.CheckedTextView;
=======
>>>>>>> master
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.support.design.widget.FloatingActionButton;

import java.io.StringBufferInputStream;
import java.util.Date;
import java.util.ArrayList;
import android.content.Intent;
import android.widget.TableRow;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    ArrayList<BucketItem> BucketList;
    EditText nameField;
    RecyclerView rvBucketLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//
//        // Lookup the recyclerview in activity layout
        rvBucketLists = (RecyclerView) findViewById(R.id.rvBucketLists);
        nameField = (EditText)findViewById(R.id.personName);
//
//        // Initialize BucketLists
        BucketList = BucketItem.createInitialBucketList();
        Intent intent = getIntent();
        try {
            String[] message = intent.getStringArrayExtra(AddItemActivity.EXTRA_MESSAGE);
            BucketItem b = new BucketItem(message[0], message[1], Double.parseDouble(message[2]), Double.parseDouble(message[3]), new Date(1220227200));
            BucketList.add(b);
        } catch (NullPointerException n) {
            System.out.println("Error");
        }

//        // Create adapter passing in the sample user data
        BucketListAdapter adapter = new BucketListAdapter(this, BucketList);
//        // Attach the adapter to the recyclerview to populate items
        rvBucketLists.setAdapter(adapter);
//        // Set layout manager to position the items
        rvBucketLists.setLayoutManager(new LinearLayoutManager(this));
//        // That's all!
//
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

    }




    public void goToAddPage(View view)
    {
        Intent intent = new Intent(MainActivity.this, AddItemActivity.class);
        startActivity(intent);

    }

    public void goToEditPage(View view) {
       // Toast.makeText(this, "Congrats! You completed an activity " , Toast.LENGTH_LONG).show();
        Intent intent = new Intent(MainActivity.this, EditItemActivity.class);
        /*****We will need to change the following line*****/
        int indexOfView = 0; //((TableRow) view.getParent()).indexOfChild(view);
        String name = BucketList.get(indexOfView).getName();
        String description = BucketList.get(indexOfView).getDescription();
        String lat = Double.toString(BucketList.get(indexOfView).getLat());
        String lon = Double.toString(BucketList.get(indexOfView).getLon());
        String date = new Long(BucketList.get(indexOfView).getDate().getTime()).toString();

        String[] messageName = {name, description, lat, lon, date};
        intent.putExtra(EXTRA_MESSAGE, messageName);
        startActivity(intent);
    }

    public void toggle_checked(View view) {
        CheckedTextView currentItem = (CheckedTextView)view;
        //Log.d("ListExample", "sendMessage to " + currentItem.getText().toString());
        // Make Toas
        Drawable d = new Drawable();
        Toast.makeText(this, "Congrats! You completed an activity " , Toast.LENGTH_LONG).show();
        if (!currentItem.isChecked()){
            currentItem.setCheckMarkDrawable();
        }
    }
}
