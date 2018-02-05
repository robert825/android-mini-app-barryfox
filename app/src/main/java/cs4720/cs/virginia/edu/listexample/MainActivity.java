package cs4720.cs.virginia.edu.listexample;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.support.design.widget.FloatingActionButton;
import android.view.ViewGroup;
import java.text.ParseException;
import java.util.Date;
import java.util.ArrayList;
import android.content.Intent;
import android.app.Activity;
import java.text.SimpleDateFormat;
import java.util.*;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    static ArrayList<BucketItem> BucketList = BucketItem.createInitialBucketList();
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

        Intent intent = getIntent();
        try {
            String[] message = intent.getStringArrayExtra(AddActivity.EXTRA_MESSAGE);
            System.out.println(message[0]);
            System.out.println(message[3]);
            System.out.println(message[4]);
            BucketItem b = new BucketItem(message[0], message[1], Double.parseDouble(message[2]), Double.parseDouble(message[3]), dateFromString(message[4]));
            BucketList.add(b);
        } catch (NullPointerException n) {
            System.out.println("Error");
        }

//        // Create adapter passing in the sample user data
        BucketListAdapter adapter = new BucketListAdapter(this, BucketList);
        System.out.println(adapter.toString());
        System.out.println(BucketList.toString());
//        // Attach the adapter to the recyclerview to populate items
        rvBucketLists.setAdapter(adapter);
//        // Set layout manager to position the items
        rvBucketLists.setLayoutManager(new LinearLayoutManager(this));
//        // That's all!
//

        Collections.sort(BucketList, new CustomComparator());
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

    }

    // Called when you tap the Add BucketList button
    public void addBucketList(View view) {
        // Make sure it is a name
        if(nameField.getText().toString() != null && !nameField.getText().toString().equals("")) {
            // Log the action
            Log.d("ListExample", "addBucketList " + nameField.getText().toString());
            // Make a new BucketList
            BucketList.add(new BucketItem(nameField.getText().toString(), "description coming soon", 0.0, 0.1, new Date(1220227200)));
            // Get the adapter that manages the data set and let it know something new was added
            rvBucketLists.getAdapter().notifyDataSetChanged();
            // Blank the name field
            nameField.setText("");
        }
    }


    public void goToAddPage(View view)
    {
        Intent intent = new Intent(MainActivity.this, AddActivity.class);
        startActivity(intent);

    }

    public Date dateFromString(String date){
        System.out.println("INSIDE DATE THING" + date);
        try {
            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
            Date date1;
            date1 = format.parse(date);
            return date1;
        } catch (ParseException e) {
            return null;
        }
    }


    // Called tapping on an online BucketList


    public void goToEditPage(View view) {
        System.out.println("CURRENT VIEW" + view.toString());
        // Toast.makeText(this, "Congrats! You completed an activity " , Toast.LENGTH_LONG).show();
        Intent intent = new Intent(MainActivity.this, EditItemActivity.class);
        /*****We will need to change the following line*****/
        View parent = (View) findViewById(R.id.parent);
        int indexOfView = ((ViewGroup) view.getParent()).indexOfChild(view);

        int count = ((ViewGroup) view.getParent()).getChildCount();
        System.out.println("PARENT COUNT" + count);
//        for (int i = 0; i < count; i++) {
//            View child = ((ViewGroup) view.getParent()).getChildAt(i);
//
//            if (child == view) {
//                indexOfView = i;
//            }
//
//        }


        String name = BucketList.get(indexOfView).getName();
        String description = BucketList.get(indexOfView).getDescription();
        String lat = Double.toString(BucketList.get(indexOfView).getLat());
        String lon = Double.toString(BucketList.get(indexOfView).getLon());
        String date = new Long(BucketList.get(indexOfView).getDate().getTime()).toString();

        String[] messageName = {name, description, lat, lon, date};
        intent.putExtra(EXTRA_MESSAGE, messageName);
        startActivity(intent);
    }




    public class CustomComparator implements Comparator<BucketItem> {
        @Override
        public int compare(BucketItem o1, BucketItem o2) {
            return o1.getDate().compareTo(o2.getDate());
        }
    }
//    public int compareTo(Date Date1, Date Dat2){
//        if Date1.
//
//    }

}
