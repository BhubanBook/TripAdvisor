package com.kazikhaledsaif.tripadvisor;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

import java.io.ByteArrayOutputStream;

public class AddProfileActivity extends AppCompatActivity {

    private EditText mFullNameET, mPhoneNoET, mAddressET;
    private ImageView mPictureIV;
    private Button mPhotoBTN, mGalleryBTN, mSaveBTN;
    private String TAG = "Firebase";
    private FirebaseAuth mAuth;
    private String mPhotoData;


    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int MY_CAMERA_REQUEST_CODE = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_add);
        mAuth = FirebaseAuth.getInstance();

        mFullNameET = findViewById(R.id.profileFullNameET);
        mPhoneNoET = findViewById(R.id.profilePhoneNoET);
        mAddressET = findViewById(R.id.profileAddressET);

        mPictureIV = findViewById(R.id.profilePictureIV);

        mPhotoBTN = findViewById(R.id.takePhotoBTN);
        mGalleryBTN = findViewById(R.id.galleryBTN);
        mSaveBTN = findViewById(R.id.profileSaveBTN);

        getCameraPermission();

        mPhotoBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();


            }

            private void dispatchTakePictureIntent() {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
            }
        });

    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {

            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mPhotoData = encodeToBase64(imageBitmap,Bitmap.CompressFormat.JPEG, 60);
            System.out.println(mPhotoData);
            mPictureIV.setImageBitmap(imageBitmap);
        }
    }


    private void getCameraPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (this.checkSelfPermission(android.Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_REQUEST_CODE);
            }
        }
    }


    public void Save(View view) {
        String FullName = mFullNameET.getText().toString();
        String PhoneNO = mPhoneNoET.getText().toString();
        String Address = mAddressET.getText().toString();
    }


    public static String encodeToBase64(Bitmap image, Bitmap.CompressFormat compressFormat, int quality)
    {
        ByteArrayOutputStream byteArrayOS = new ByteArrayOutputStream();
        image.compress(compressFormat, quality, byteArrayOS);
        return Base64.encodeToString(byteArrayOS.toByteArray(), Base64.DEFAULT);
    }


    public static Bitmap decodeBase64(String input)
    {
        byte[] decodedBytes = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }


    public void Skip(View view) {
        Intent intent = new Intent(AddProfileActivity.this, DashboardActivity.class);
        finish();
        startActivity(intent);
    }
}
