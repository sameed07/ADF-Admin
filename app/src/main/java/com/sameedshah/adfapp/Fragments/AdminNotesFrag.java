package com.sameedshah.adfapp.Fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.sameedshah.adfapp.R;
import com.sameedshah.adfapp.SummaryActivity;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import static android.app.Activity.RESULT_OK;

public class AdminNotesFrag extends Fragment {

    Button btnSave, btnSummary;
    ImageView myImg,btnCamera;
    EditText edtNotes;
    int CAM_CONS;
    String mCameraFileName;
    private String mUrl;

    Bitmap mbitmap;
    //Firebase
    FirebaseStorage storage;
    StorageReference storageReference;
    FirebaseDatabase mDatabase;
    DatabaseReference mRef;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_notes,container,false);
        CAM_CONS = 1;


        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference("Admin").child("AdminNotes");

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference("Images");

        btnCamera = view.findViewById(R.id.mCamera);
        myImg = view.findViewById(R.id.save_image);
        btnSave = view.findViewById(R.id.btnSave);
        edtNotes = view.findViewById(R.id.edtNotes);
        btnSummary = view.findViewById(R.id.btnSummary);

        btnSummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SummaryActivity.class));
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                uploadImage();

            }
        });

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,
                        CAM_CONS);
                //openCamera();

            }
        });
        return view;
    }




    //handling result

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAM_CONS && resultCode == RESULT_OK ) {


                Bitmap bmp = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream stream = new ByteArrayOutputStream();

                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();

                // convert byte array to Bitmap

                Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0,
                        byteArray.length);
                 mbitmap  = bitmap;
                    myImg.setImageBitmap(bitmap);


        }
    }
    private void uploadImage() {

        if(mbitmap != null) {
            final ProgressDialog progressDialog = new ProgressDialog(getContext());
            progressDialog.setTitle("Uploading...");
            progressDialog.show();
            final String time = String.valueOf(System.currentTimeMillis());

            final StorageReference ref = storageReference.child("Notes/" + time);


            UploadTask uploadTask = ref.putBytes(getByte(mbitmap));

            Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }

                    // Continue with the task to get the download URL
                    return ref.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()) {
                        Uri downloadUri = task.getResult();
                        mUrl = downloadUri.toString();
                        String notes = edtNotes.getText().toString();

                            Map<String, Object> map = new HashMap<>();
                            map.put("Notes", notes);
                            map.put("Image", mUrl);
                            mRef.child(String.valueOf(System.currentTimeMillis())).setValue(map);
                            progressDialog.dismiss();
                            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
                            edtNotes.setText("");
                            myImg.setImageResource(R.drawable.default_img);


                    } else {
                        // Handle failures
                        // ...
                        Toast.makeText(getContext(), "Failed!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else{
            Toast.makeText(getContext(), "Select any image", Toast.LENGTH_SHORT).show();
        }

    }

    public byte[] getByte(Bitmap bitmap){

        ByteArrayOutputStream mArray = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,mArray);
        return mArray.toByteArray();
    }

}
