package cs4720.cs.virginia.edu.listexample;

import java.util.ArrayList;
import java.util.Date;

public class BucketItem {
    private String mName;
    private String mDescription;
    private double mlat;
    private double mlon;
    private Date mdueDate;
    private boolean mchecked;

    public BucketItem(String name, String description, double lat, double lon, Date dueDate, boolean checked) {
        mName = name;
        mDescription = description;
        mlat = lat;
        mlon = lon;
        mdueDate = dueDate;
        mchecked = checked;


    }

    public String getName() {
        return mName;
    }
    public String getDescription() {
        return mDescription;
    }
    public double getLat() {
        return mlat;
    }
    public double getLon() {
        return mlon;
    }
    public boolean getChecked() {
        return mchecked;
    }
    public Date getDate() {
        return mdueDate;
    }
    public void setChecked(boolean b) {
        mchecked = b;

    }

//    public boolean isOnline() {
//        return mOnline;
//    }

    private static int lastBucketListId = 0;

    public static ArrayList<BucketItem> createInitialBucketList() {

        ArrayList<BucketItem> BucketListsItems = new ArrayList<BucketItem>();
        BucketListsItems.add(new BucketItem("Register for Graduation ", "register on sis", 0.0,0.0, new Date(System.currentTimeMillis()), false));
        BucketListsItems.add(new BucketItem("New thing to do! ", "do something new", 0.1,0.1, new Date(System.currentTimeMillis()) , false));
        BucketListsItems.add(new BucketItem("Finish CS 4720", "get an A", 0.0,0.0, new Date(System.currentTimeMillis()), false));
//        for (int i = 1; i <= numBucketLists; i++) {
//            BucketListsItems.add(new BucketItem("Person " + ++lastBucketListId, i <= numBucketLists / 2));
//        }

        return BucketListsItems;
    }
}
