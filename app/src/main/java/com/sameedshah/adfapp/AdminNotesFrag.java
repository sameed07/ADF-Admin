package com.sameedshah.adfapp;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AdminNotesFrag extends Fragment {

    Button btnSave, btnSummary;
    ImageView myImg,btnCamera;
    EditText edtNotes;
    int CAM_CONS;
    String mCameraFileName;

    private static final int TAKE_PICTURE = 1;
    private Uri imageUri;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_notes,container,false);
        CAM_CONS = 1;
        btnCamera = view.findViewById(R.id.mCamera);
        myImg = view.findViewById(R.id.save_image);
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


    //Open Camera Function

    public void openCamera() {
        Intent intent = new Intent();
        // Picture from camera
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);

        // This is not the right way to do this, but for some reason, having
        // it store it in
        // MediaStore.Images.Media.EXTERNAL_CONTENT_URI isn't working right.

        Date date = new Date();
        DateFormat df = new SimpleDateFormat("-mm-ss");

        String newPicFile = "Lab Result" + df.format(date) + ".jpg";
        String outPath = "/sdcard/" + newPicFile;
        File outFile = new File(outPath);

        mCameraFileName = outFile.toString();
        Uri outuri = Uri.fromFile(outFile);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outuri);


        getActivity().startActivityForResult(intent, CAM_CONS);
    }

    //handling result
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAM_CONS) {
            if (resultCode == Activity.RESULT_OK) {

                Bitmap bmp = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream stream = new ByteArrayOutputStream();

                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();

                // convert byte array to Bitmap

                Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0,
                        byteArray.length);

                myImg.setImageBitmap(bitmap);

            }
        }
    }
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//         if (requestCode == CAM_CONS)
//            {
//                // return from file upload
//                if (resultCode == Activity.RESULT_OK)
//                {
//                    Uri uri = null;
//                    if (data != null)
//                    {
//                        uri = data.getData();
//                    }
//                    if (uri == null && mCameraFileName != null)
//                    {
//                        uri = Uri.fromFile(new File(mCameraFileName));
//                    }
//                    File file = new File(mCameraFileName);
//                    if (!file.exists()) {
//                        file.mkdir();
//                    }
//                }
//            }}

}
