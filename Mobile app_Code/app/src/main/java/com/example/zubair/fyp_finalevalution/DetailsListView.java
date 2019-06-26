package com.example.zubair.fyp_finalevalution;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.GeoPoint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DetailsListView extends AppCompatActivity {

    public ArrayList<DataModel> list_of_location;           //List view related work
    private static CustomAdapter adapter;
    ListView listView;

    FirebaseFirestore db ;                                 //Firebase realted work
    DocumentReference docRef;
    String total;
    StringBuilder fields;
    String TAG="mESSAGE OF DAY";
    boolean RecordFound=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_list_view);

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView=(ListView)findViewById(R.id.list);

        list_of_location = new ArrayList<DataModel>();





        db = FirebaseFirestore.getInstance();

        docRef = db.collection("agents").document("Agent2");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        GeoPoint Gp;

                        Gp = (GeoPoint) document.getData().get("source");
                        list_of_location.add(new DataModel(Gp.getLatitude() + "," + Gp.getLongitude(), true,"source"));  //at 0


                        //Now way points
                        Gp = (GeoPoint) document.getData().get("way1");
                        list_of_location.add(new DataModel(Gp.getLatitude() + "," + Gp.getLongitude(), false,"way1"));  //at 1

                        Gp = (GeoPoint) document.getData().get("way2");
                        list_of_location.add(new DataModel(Gp.getLatitude() + "," + Gp.getLongitude(), false,"way2"));  //at 2

                        Gp = (GeoPoint) document.getData().get("way3");
                        list_of_location.add(new DataModel(Gp.getLatitude() + "," + Gp.getLongitude(), false,"way3"));  //at 3

                        Gp = (GeoPoint) document.getData().get("way4");
                        list_of_location.add(new DataModel(Gp.getLatitude() + "," + Gp.getLongitude(), false,"way4"));  //at 4

                        Gp = (GeoPoint) document.getData().get("way5");
                        list_of_location.add(new DataModel(Gp.getLatitude() + "," + Gp.getLongitude(), false,"way5"));  //at 5

                        Gp = (GeoPoint) document.getData().get("way6");
                        list_of_location.add(new DataModel(Gp.getLatitude() + "," + Gp.getLongitude(), false,"way6"));  //at 6

                        Gp = (GeoPoint) document.getData().get("way7");
                        list_of_location.add(new DataModel(Gp.getLatitude() + "," + Gp.getLongitude(), false,"way7"));  //at 7

                        Gp = (GeoPoint) document.getData().get("way8");
                        list_of_location.add(new DataModel(Gp.getLatitude() + "," + Gp.getLongitude(), false,"way8"));  //at 8

                        Gp = (GeoPoint) document.getData().get("destination");
                        Log.v(TAG, "DocumentSnapshot data: " + Gp.getLatitude());
                        list_of_location.add(new DataModel(Gp.getLatitude() + "," + Gp.getLongitude(), false,"destination"));  //at 9




                        adapter = new CustomAdapter(list_of_location, getApplicationContext());
                        listView.setAdapter(adapter);

                    }
                    else {
                        Log.v("Hello", "No such document");
                    }
                }

                else {
                    Log.v("Hello1", "get failed with ", task.getException());
                }
            }


        });

        }
   @Override
    public boolean onCreateOptionsMenu(Menu menu)
   {
    MenuInflater inflater=getMenuInflater();
    inflater.inflate(R.menu.my_options_menu,menu);
    return true;
   }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // display a message when a button was pressed
        String message = "";
        if (item.getItemId() == R.id.SignOut) {
            message = "You selected Log out!";
            Intent In1=new Intent(this,MainActivity.class);
            startActivity(In1);
        }
        else if (item.getItemId() == R.id.Reroute) {
            message = "You selected option 2 Reroute!";
            FirebaseFirestore dbReroute ;
            dbReroute=FirebaseFirestore.getInstance();

            Map<String, Object> docData = new HashMap<>();
            docData.put("Reroute", "True");
            dbReroute.collection("Reroute").document("Status")
                    .set(docData)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d(TAG, "DocumentSnapshot successfully written!");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Error writing document", e);
                        }
                    });
        }
        else {
            message = "You started started start navigation";
            int StartIndex=-1;
            int EndIndex=-1;

            for(int i=0;i<list_of_location.size();i++)
                if(list_of_location.get(i).getStatus()==false) {
                    EndIndex = i;
                    break;
                }


            Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                    Uri.parse("http://maps.google.com/maps?saddr="+ list_of_location.get(EndIndex-1).getLocationPoint()+"&daddr="+list_of_location.get(EndIndex).getLocationPoint()));
            startActivity(intent);
        }

        // show message via toast
        Toast toast = Toast.makeText(this, list_of_location.get(0).getLocationPoint(), Toast.LENGTH_LONG);
        toast.show();

        return true;
    }

    public void StartNavigation(View v)
    {
        Toast toast = Toast.makeText(this," " +list_of_location.get(0).getLocationPoint(), Toast.LENGTH_LONG);
        toast.show();

    }


}
