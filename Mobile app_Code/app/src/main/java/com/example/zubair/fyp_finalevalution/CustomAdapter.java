package com.example.zubair.fyp_finalevalution;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CustomAdapter extends ArrayAdapter<DataModel> {

    private ArrayList<DataModel> dataSet;
    Context mContext;

    FirebaseFirestore db ;                                 //Firebase realted work
    DocumentReference docRef;


    public CustomAdapter(ArrayList<DataModel> data, Context context) {
        super(context, R.layout.row_view, data);
        this.dataSet = data;
        this.mContext=context;

    }






    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        TextView T;
        Button B;
        final DataModel dataModel = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_view, parent, false);
            }

        T = (TextView) convertView.findViewById(R.id.rows_text);
        T.setText(dataModel.getLocationPoint());
        B = (Button) convertView.findViewById(R.id.rows_button);

        if(dataModel.getStatus()==true)
            B.setEnabled(false);
        else
            B.setEnabled(true);



        B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Row " + position + " was clicked!", Toast.LENGTH_SHORT).show();
                Button b1=(Button) v;
                v.setEnabled(false);
                dataModel.setStatus(true);

                db = FirebaseFirestore.getInstance();
                Map<String, Object> docData = new HashMap<>();
                docData.put(dataModel.getName(), "Visited");
                docData.put("Location", dataModel.getLocationPoint());

                db.collection("Traveresed").document("Agent2").collection("Points").document(dataModel.getName())
                        .set(docData)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d("Error", "DocumentSnapshot successfully written!");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w("Error1", "Error writing document", e);
                            }
                        });

            }
        });

        return convertView;
    }
}