package reding.com.testcv;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            switch (status) {
                case LoaderCallbackInterface.SUCCESS: {

                }
                break;
                default: {
                    super.onManagerConnected(status);
                }
                break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_2_0, this, mLoaderCallback);
        setContentView(R.layout.activity_main);
        ImageView src = (ImageView) findViewById(R.id.image_src);
        ImageView des = (ImageView) findViewById(R.id.image_des);
        try {
            Bitmap srcBm = BitmapFactory.decodeStream(getAssets().open("1.png"));
            src.setImageBitmap(srcBm);
            Mat srcMat = Mat.zeros(src.getWidth(), src.getHeight(), CvType.CV_8UC1);
            Utils.bitmapToMat(srcBm, srcMat);

            Mat grayMat = Mat.zeros(srcMat.size(), CvType.CV_8UC1);


            Imgproc.cvtColor(srcMat, grayMat, Imgproc.COLOR_BGR2GRAY);

            Mat edge = Mat.zeros(srcMat.size(), CvType.CV_8UC1);
            Imgproc.blur(grayMat, edge, new Size(3, 3));

            Imgproc.Canny(edge, edge, 3, 9);


            Utils.matToBitmap(edge, srcBm);

            des.setImageBitmap(srcBm);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
