package cs4720.cs.virginia.edu.listexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.support.design.widget.FloatingActionButton;
import java.util.Date;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

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

    // Called tapping on an online BucketList
    public void sendMessage(View view) {
        TextView currentItem = (TextView)view;
        Log.d("ListExample", "sendMessage to " + currentItem.getText().toString());
        // Make Toast
        Toast.makeText(this, "Sending message to " + currentItem.getText().toString(), Toast.LENGTH_LONG).show();
    }
}
