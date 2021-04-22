package com.example.opencvapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.WindowManager;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

public class MainActivity extends AppCompatActivity implements CameraBridgeViewBase.CvCameraViewListener2 {

    private CameraBridgeViewBase mOpenCvCameraView;
    private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            switch (status){
                case LoaderCallbackInterface.SUCCESS:
                    mOpenCvCameraView.enableView();break;
                default:super.onManagerConnected(status);break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_main);
        mOpenCvCameraView = findViewById(R.id.camera_surface);
        mOpenCvCameraView.setVisibility(SurfaceView.VISIBLE);
        mOpenCvCameraView.setCvCameraViewListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mOpenCvCameraView !=null){
            mOpenCvCameraView.disableView();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!OpenCVLoader.initDebug()){
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_4_0,this,mLoaderCallback);
        }
    }

    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
        Mat result = new Mat();
        //Imgproc.medianBlur(inputFrame.gray(),result,15);
        //Imgproc.Canny(inputFrame.rgba(),result,80,200);
        return result;
    }


    @Override
    public void onCameraViewStarted(int width, int height) {

    }

    @Override
    public void onCameraViewStopped() {

    }


}
