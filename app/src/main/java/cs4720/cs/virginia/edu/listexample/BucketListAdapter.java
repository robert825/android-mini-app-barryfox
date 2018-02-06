package cs4720.cs.virginia.edu.listexample;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.TextView;
import android.content.Intent;
import android.widget.CheckBox;
import org.w3c.dom.Text;

import java.util.List;

// Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ViewHolder which gives us access to our views
public class BucketListAdapter extends
        RecyclerView.Adapter<BucketListAdapter.ViewHolder> {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    public static final String EXTRA_MESSAGE2 = "com.example.myfirstapp.MESSAGE2";
    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView nameTextView;
        public TextView dateView;
        public CheckBox cb;
        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.BucketList_name);
            dateView = (TextView) itemView.findViewById(R.id.date);
            cb = (CheckBox) itemView.findViewById(R.id.checkbox);

        }
    }

    // Store a member variable for the BucketLists
    private List<BucketItem> mBucketLists;
    // Store the context for easy access
    private Context mContext;

    // Pass in the BucketList array into the constructor
    public BucketListAdapter(Context context, List<BucketItem> BucketLists) {
        mBucketLists = BucketLists;
        mContext = context;
    }

    // Easy access to the context object in the recyclerview
    private Context getContext() {
        return mContext;
    }

    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public BucketListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View BucketListView = inflater.inflate(R.layout.item_contact, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(BucketListView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(BucketListAdapter.ViewHolder viewHolder, final int position) {
        // Get the data model based on position
        BucketItem BucketList = mBucketLists.get(position);

        // Set item views based on your views and data model
        TextView textView = viewHolder.nameTextView;
        TextView dateDisplay = viewHolder.dateView;
        final CheckBox checkDisplay = viewHolder.cb;
        String date_str = BucketList.getDate().toString();
        date_str = date_str.substring(4, 10) + " " + date_str.substring(date_str.length()- 4);
        System.out.println(BucketList.getName());
        textView.setText(BucketList.getName());

        dateDisplay.setText(date_str);
        if (BucketList.getChecked()) {
            checkDisplay.setChecked(true);
        }


//        if(!BucketList.isOnline()) {
//            textView.setClickable(false);
//            textView.setActivated(false);
//            textView.setEnabled(false);
//
//        }

        checkDisplay.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean checked = mBucketLists.get(position).getChecked();
                if(checked) {
                    mBucketLists.get(position).setChecked(false);
                }
                else
                    mBucketLists.get(position).setChecked(true);
                Intent i = new Intent(view.getContext(), MainActivity.class);
                view.getContext().startActivity(i);
            }

        });

        textView.setOnClickListener(new TextView.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), EditItemActivity.class);
                System.out.println("CURRENT VIEW" + view.toString());
                // Toast.makeText(this, "Congrats! You completed an activity " , Toast.LENGTH_LONG).show();

                /*****We will need to change the following line*****/
                Integer indexOfView = position;




                String name = mBucketLists.get(indexOfView).getName();
                String description = mBucketLists.get(indexOfView).getDescription();
                String lat = Double.toString(mBucketLists.get(indexOfView).getLat());
                String lon = Double.toString(mBucketLists.get(indexOfView).getLon());
                String date = new Long(mBucketLists.get(indexOfView).getDate().getTime()).toString();
                Boolean checked = mBucketLists.get(indexOfView).getChecked();

                String[] messageName = {name, description, lat, lon, date, indexOfView.toString(), checked.toString()};
                intent.putExtra(EXTRA_MESSAGE, messageName);
                view.getContext().startActivity(intent);
            }
        });
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mBucketLists.size();
    }
//    public void toggleChecked(View v) {
//        CheckBox c = (CheckBox) v.findViewById(R.id.checkbox);
//        if() {
//
//        }
//    }
}

