package com.example.cyrptographer;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Imagecrypt#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Imagecrypt extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final int PICK_IMAGE = 1;
    ImageView selectimg ;
    TextView filename;
    String sImage;
    Button copyimg;
    ClipboardManager cpb;



    

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Imagecrypt() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Imagecrypt.
     */
    // TODO: Rename and change types and number of parameters
    public static Imagecrypt newInstance(String param1, String param2) {
        Imagecrypt fragment = new Imagecrypt();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_imagecrypt,container,false);

        cpb = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
        selectimg = (ImageView) v.findViewById(R.id.selectIMAGE);
        filename = (TextView) v.findViewById(R.id.encryptedimg);
        copyimg = (Button)v.findViewById(R.id.copyimg);
      // filename.setText("Select File ....");

        copyimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cp2(view);
            }
        });







        selectimg.setOnClickListener(this::onClick);

        
        

        Dexter.withActivity(getActivity())
                .withPermissions(new String[]{
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                }).withListener(new MultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport report) {

            }

            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {

            }
        }).check();


        // Inflate the layout for this fragment
        return v;
    }



    public void cp2(View view) {
        // get the string from the textview and trim all spaces
        String data = filename.getText().toString().trim();
        Toast.makeText(getActivity(), "Copied", Toast.LENGTH_SHORT).show();
        // check if the textview is not empty
        if (!data.isEmpty()) {

            // copy the text in the clip board
            ClipData temp = ClipData.newPlainText("text", data);
            cpb.setPrimaryClip(temp);

            // display message that the text has been copied
            Toast.makeText(getActivity(), "Copied", Toast.LENGTH_SHORT).show();
        }
    }

    private void onClick(View view) {


        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) getContext(),
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},100);
        } else {
           showChoser();
        }
        showChoser();
    }

    private void showChoser() {
        filename.setText("");


        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        //Uri data = intent.getData();
       // intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Picture"),100);









    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==100 &&grantResults[0]==PackageManager.PERMISSION_GRANTED)
        {
            showChoser();
        }
        else {
            Toast.makeText(getContext(), "PERMISSION DENIED", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==100&&resultCode==RESULT_OK && data!=null) {
            Uri uri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),uri);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);
                byte[] bytes = stream.toByteArray();
                sImage = Base64.getEncoder().encodeToString(bytes);
                //Toast.makeText(getContext(), sImage, Toast.LENGTH_SHORT).show();
                filename.setText(sImage);
            } catch (IOException e) {
                e.printStackTrace();

        }
    }
}
















}