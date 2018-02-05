package cs4720.cs.virginia.edu.listexample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.TextView;

import java.util.List;

// Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ViewHolder which gives us access to our views
public class BucketListAdapter extends
        RecyclerView.Adapter<BucketListAdapter.ViewHolder> {

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public CheckedTextView nameTextView;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            nameTextView = (CheckedTextView) itemView.findViewById(R.id.BucketList_name);

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
    public void onBindViewHolder(BucketListAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        BucketItem BucketList = mBucketLists.get(position);

        // Set item views based on your views and data model
        CheckedTextView textView = viewHolder.nameTextView;
        System.out.println(BucketList.getName());
        textView.setText(BucketList.getName());
//        if(!BucketList.isOnline()) {
//            textView.setClickable(false);
//            textView.setActivated(false);
//            textView.setEnabled(false);
//
//        }

    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mBucketLists.size();
    }
}