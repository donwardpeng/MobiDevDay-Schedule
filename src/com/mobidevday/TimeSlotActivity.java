package com.mobidevday;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * The TimeSlotActivity class extends ListActivity and is responsible for filling in the individual TimeSlot tabs
 * with the Room, Presenter and Presentation list items.
 *   
 *     
 * @author Don Ward
 */

public class TimeSlotActivity extends ListActivity {
private String timeSlotName;
private ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
private String  timeSlot;
	

/**Called when the activity is first created. */	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       //Get the Time Slot Name which is passed in the Intent Extras Bundle
        Bundle extras = getIntent().getExtras();
        timeSlotName = extras.getString("intentTimeSlotName");

        //Populate a Hashmap with the rooms being used for this time slot
        HashMap<String, String> roomData = new HashMap<String,String>();
        roomData = Startup.timeSlotMap.get(timeSlotName);
        
        //Iterate through all of the rooms for this time slot and 
        //get the presenter and presentation title
        Iterator it =roomData.keySet().iterator();
        ArrayList<String> presenters = new ArrayList<String>();
        while (it.hasNext())
        {
        String key = (String)it.next();
        //Get the presenters name
        String value = roomData.get(key);
        //Strip the number off the presenters name if one exists
        String[] values = value.split("\\d");
        //result = "Room Name - Presenter" 
        String result = key + " - " + values[0];
        //Get the Presentation title based on the original presenters name
        String name = Startup.presentations.getPresentationTitle(value);
        //if this is a empty string then do not append it
        if (!name.equals(""))
        //result = "Room Name - Presenter \n Presentation Name"
        result = result + "\n ** " + name + " **";
        //Add to the Presenters Array List
        presenters.add(result);
        }
        //Sort the Arraylist - sorting by Room Name primarily
        Collections.sort(presenters);
          
        //Add the ArrayList to the List of items
        setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item,presenters));     
        ListView lv = getListView();
        lv.setTextFilterEnabled(true);
        
        //On Click, lookup the presentation title and display the proper info on the 
        //presenter and presentation
        lv.setOnItemClickListener(new OnItemClickListener() {
          public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
          //get the text of the selected list item
        	 String listItem = ((TextView)view).getText().toString();
        	 String s1[] = null;
        	 //if the text contains "*" then there exists a Presentation Title 
        	 if  (listItem.contains("*"))
        	 {
        	 s1 = listItem.split("\\*\\* ");
        	 }
        	 else
        	 {
        		 return;
        	 }
        	 String key;
        	//if the key (Presentation Title) is null, then do nothing
        	 if (s1 ==null || s1[0] == null)
        		 return;
        	 //else get the Presentation Title
        	 String[] s2 = s1[1].split(" \\*\\*");
        	 key = s2[0];
        	 //if the key is null, then do nothing
        	 if (key == null)
        		 return;
        	 //Lookup the Presentation Description corresponding to the Presentation Title,
        	 // for the WebView displayed next
        	 String desc = Startup.presentations.getPresentationDescription(key);
        	 // Create an Intent to launch an Activity for the tab (to be reused)
          Intent intent = new Intent(view.getContext(), PresentationActivity.class);
          intent.putExtra("presentation_Desc",desc);
          startActivity(intent);
          }
        });
    }
}
