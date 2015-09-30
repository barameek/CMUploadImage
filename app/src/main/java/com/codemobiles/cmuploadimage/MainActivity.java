package com.codemobiles.cmuploadimage;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.codemobiles.cmuploadimage.util.CameraIntentHelperActivity;
import com.codemobiles.cmuploadimage.util.UploadImageUtils;


public class MainActivity extends CameraIntentHelperActivity {

    public Handler mUIHandler = new Handler();
    private String selectedImagePath;
    private ImageView selectImageInCameraBtn;
    private ImageView selectImageInGalleryBtn;
    private TextView imageFileName;
    private ImageView imageView;
    private Bitmap mPhotoBitMap;
    public String mUploadedFileName;
    public int maxPixel = 500;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindWidget();
        setWidgetListener();
    }


    private void bindWidget() {
        selectImageInCameraBtn = (ImageView) findViewById(R.id.selectImageInCameraBtn);
        selectImageInGalleryBtn = (ImageView) findViewById(R.id.selectImageInGalleryBtn);
        imageFileName = (TextView) findViewById(R.id.imagename);
        imageView = (ImageView) findViewById(R.id.imageview);

    }


    public class UploadImageTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(getApplicationContext(), "Uploading...", Toast.LENGTH_SHORT).show();
        }

        protected String doInBackground(String... params) {

            String url = params[0];
            mUploadedFileName = UploadImageUtils.getRandomFileName();
            final String result = UploadImageUtils.uploadFile(
                    mUploadedFileName, url, mPhotoBitMap);
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            if (result == null || result.equals("NOK")) {
                Toast.makeText(getApplicationContext(), "Uploading...failed", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Uploading...complete", Toast.LENGTH_SHORT).show();
            }

        }


    }

    private void setWidgetListener() {

        // Select image from camera
        selectImageInCameraBtn.setOnClickListener(new OnClickListener() {

            public void onClick(View arg0) {
                startCameraIntent(maxPixel);
            }
        });

        // Select image from gallery
        selectImageInGalleryBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                startGalleryIntent(maxPixel);
            }
        });
    }

    @Override
    protected void onPhotoUriFound(Uri _photoUri, Bitmap _photoBitMap, String _filePath) {
        super.onPhotoUriFound(_photoUri, _photoBitMap, _filePath);

        mPhotoBitMap = _photoBitMap;
        imageFileName.setText(_filePath);
        imageView.setImageBitmap(mPhotoBitMap);
        new UploadImageTask().execute("http://172.16.1.194/tutorials/upload.php");
    }
}