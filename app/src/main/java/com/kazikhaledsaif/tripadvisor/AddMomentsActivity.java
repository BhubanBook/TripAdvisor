package com.kazikhaledsaif.tripadvisor;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.kazikhaledsaif.tripadvisor.POJO.Event;
import com.kazikhaledsaif.tripadvisor.POJO.Moments;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddMomentsActivity extends AppCompatActivity {

    private EditText momentDetailsEt;
    private ImageView momentImageView;
    private Button loadImageButton, saveMomentButton;
    private Bitmap bitmap;
    private static final int LOAD_IMAGE_REQUEST = 7;
    private static final int LOAD_CAMERA_REQUEST = 8;
    private String imageData;
    private ArrayAdapter<String> dataAdapter;
    private FirebaseUser user;
    private DatabaseReference rootReference;
    Spinner mSpinner;
    private List<String> list = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_moments);

        momentDetailsEt = findViewById(R.id.moment_details);
        momentImageView = findViewById(R.id.moment_image_view);
        loadImageButton = findViewById(R.id.load_image_button);
        saveMomentButton = findViewById(R.id.save_moment_button);

        rootReference = FirebaseDatabase.getInstance().getReference();

        user= FirebaseAuth.getInstance().getCurrentUser();
        String userId = user.getUid();

        Query query = FirebaseDatabase.getInstance().getReference("Events")
                .orderByChild("userId")
                .equalTo(userId);
        query.keepSynced(true);
        dataAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, list);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mSpinner = (Spinner)findViewById(R.id.addMomentsSpinner);
        mSpinner.setAdapter(dataAdapter);



        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list.add("Select Your Event");
                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    Event data = snapshot.getValue(Event.class);
                    list.add(data.getEventDesc());

                }
                dataAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }


    public void LoadImage(View view) {

        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(intent, LOAD_IMAGE_REQUEST);
    }


    public void Capture(View v) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, LOAD_CAMERA_REQUEST);
        }

    }
        @Override
        protected void onActivityResult ( int requestCode, int resultCode, Intent data){
            super.onActivityResult(requestCode, resultCode, data);

            if (requestCode == LOAD_IMAGE_REQUEST && resultCode == RESULT_OK) {
                Uri uri = data.getData();

                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                    imageData = encodeImage(bitmap, 25);
                    momentImageView.setImageBitmap(decodeImage(imageData));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (requestCode == LOAD_CAMERA_REQUEST && resultCode == RESULT_OK) {

                Bundle extras = data.getExtras();
                Bitmap bitmap = (Bitmap) extras.get("data");
                imageData = encodeImage(bitmap, 100);
                momentImageView.setImageBitmap(decodeImage(imageData));
            }


        }




        public String encodeImage(Bitmap bitmap,int quality) {

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, byteArrayOutputStream);
            return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0);
        }

        public Bitmap decodeImage(String imageString) {
            byte[] bytes = Base64.decode(imageString, 0);
            return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        }


    public void Save(View view) {


        String details = momentDetailsEt.getText().toString();

        if (!TextUtils.isEmpty(details) && !imageData.isEmpty()) {
            //Do firebase code here
          String  key = rootReference.push().getKey();
            String eventKey =mSpinner.getSelectedItem().toString();
            Moments moment = new Moments(imageData,details,key,eventKey);
            rootReference.child("Moment").child(key).setValue(moment).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    finish();
                    Toast.makeText(AddMomentsActivity.this, "Moment saved", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(AddMomentsActivity.this, "Failed, Try Again", Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            Toast.makeText(AddMomentsActivity.this, "Fields can not be empty", Toast.LENGTH_SHORT).show();
        }

    }
}

