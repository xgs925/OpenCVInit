package cn.edu.zafu.opencv;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

public class MainActivity extends AppCompatActivity {
    private Button btn,btn1;
    private ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OpenCVLoader.initDebug();
        initView();
    }

    private void initView() {
        btn= (Button) findViewById(R.id.btn);
        btn1= (Button) findViewById(R.id.btn1);
        img= (ImageView) findViewById(R.id.img);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long start=System.currentTimeMillis();
                Bitmap bitmap = ((BitmapDrawable) getResources().getDrawable(
                        R.drawable.ic)).getBitmap();
                int w = bitmap.getWidth(), h = bitmap.getHeight();
                int[] pix = new int[w * h];
                bitmap.getPixels(pix, 0, w, 0, 0, w, h);
                int [] resultPixes=OpenCVHelper.gray(pix,w,h);
                Bitmap result = Bitmap.createBitmap(w,h, Bitmap.Config.RGB_565);
                result.setPixels(resultPixes, 0, w, 0, 0,w, h);
                long end=System.currentTimeMillis();
                Log.d("gus",(end-start)+"");
                img.setImageBitmap(result);
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long start=System.currentTimeMillis();
                Mat rgbMat = new Mat();
                Mat grayMat = new Mat();
                Bitmap srcBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic);
                Bitmap grayBitmap = Bitmap.createBitmap(srcBitmap.getWidth(), srcBitmap.getHeight(), Bitmap.Config.RGB_565);
                Utils.bitmapToMat(srcBitmap, rgbMat);//convert original bitmap to Mat, R G B.
                Imgproc.cvtColor(rgbMat, grayMat, Imgproc.COLOR_RGB2GRAY);//rgbMat to gray grayMat
                Utils.matToBitmap(grayMat, grayBitmap); //convert mat to bitmap
                long end=System.currentTimeMillis();
                Log.d("gus",(end-start)+"");
                img.setImageBitmap(grayBitmap);
            }
        });
    }
}
