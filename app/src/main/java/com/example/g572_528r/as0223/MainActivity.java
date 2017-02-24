package com.example.g572_528r.as0223;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private Button mTxtButton;
    private Button mImgButton;
    private TextView mTextView;
    private String url = "http://www.baidu.com";
    private String imgUrl = "https://www.baidu.com/img/bd_logo1.png";
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTxtButton = (Button) findViewById(R.id.txtButton);
        mImgButton = (Button) findViewById(R.id.imgButton);
        mTextView = (TextView) findViewById(R.id.txt);
        mImageView = (ImageView) findViewById(R.id.ivPhoto);
        mTxtButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initTxtView();
            }
        });
        mImgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initView();
            }
        });
    }

    private void initTxtView() {
        //创建okhttpClient对象
        OkHttpClient mOkHttpClient = new OkHttpClient();
        //创建一个Request
        final Request request = new Request.Builder().url(url).build();
        Call call = mOkHttpClient.newCall(request);
        //请求加大调度
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                //String htmlStr =  response.body().string();
                final String htmlText = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mTextView.setText(htmlText);
                    }
                });
            }
        });
    }

    private void initView() {
        //创建okhttpClient对象
        OkHttpClient mOkHttpClient = new OkHttpClient();
        //创建一个Request
        final Request request = new Request.Builder().url(imgUrl).build();
        Call call = mOkHttpClient.newCall(request);
        //请求加大调度
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                //String htmlStr =  response.body().string();
//                final String htmlText = response.body().string();
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        mTextView.setText(htmlText);
//                    }
//                });

                byte[] imgbyte = response.body().bytes();
                final Bitmap bitmap = BitmapFactory.decodeByteArray(imgbyte, 0, imgbyte.length);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mImageView.setImageBitmap(bitmap);
                    }
                });
            }
        });
    }


}
