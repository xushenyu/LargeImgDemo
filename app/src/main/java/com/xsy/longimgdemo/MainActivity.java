package com.xsy.longimgdemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private List<Bitmap> mList = new ArrayList<>();
    private ImageAdapter imageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        imageAdapter = new ImageAdapter(this, mList);
        listView.setAdapter(imageAdapter);
        getBitmapRegion();
    }

    private void getBitmapRegion() {
            try {
                InputStream inputStream = getAssets().open("superlarge.jpg");
                BitmapRegionDecoder bitmapRegionDecoder = BitmapRegionDecoder.newInstance(inputStream, false);
                int width = bitmapRegionDecoder.getWidth();
                int height = bitmapRegionDecoder.getHeight();
                int s = height / width;
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inPreferredConfig = Bitmap.Config.RGB_565;
                options.inJustDecodeBounds = true;
                Bitmap bitmap;
                for (int i = 0; i < s+1; i++) {
                    if (i!=s){
                        bitmap = bitmapRegionDecoder.decodeRegion(new Rect(0, i*width, width, (i+1)*width), options);
                    }else{
                        bitmap = bitmapRegionDecoder.decodeRegion(new Rect(0, width, width, height), options);
                    }
                    mList.add(bitmap);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        imageAdapter.notifyDataSetChanged();
    }
}
