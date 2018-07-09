package com.example.kuroshiro.demoasynctask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {


    Button btLoad;
    ImageView img;
    TextView load;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btLoad = findViewById(R.id.btLoad);
        img = findViewById(R.id.imageV);
        load = findViewById(R.id.textView);


        btLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new LoadImage().execute("http://getwallpapers.com/wallpaper/full/b/a/5/168584.jpg");
            }
        });
    }

    private class LoadImage extends AsyncTask<String, Void, Bitmap>
    {

        Bitmap mBit = null;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Bitmap doInBackground(String... strings) {


            
            try {
                URL murl = new URL(strings[0]);

                InputStream mInput = murl.openConnection().getInputStream();

                mBit = BitmapFactory.decodeStream(mInput);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return mBit;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            img.setImageBitmap(bitmap);
        }

    }
}
