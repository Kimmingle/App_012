package com.example.myapplication_new;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {


    ImageView imageViewF1;
    ImageView imageViewF2;
    ImageView imageView_F1_Red;
    ImageView imageView_F2_Red;
    ImageView imageView_F1_Blue;
    ImageView imageView_F2_Blue;
    TextView textViewF1;
    TextView textViewF2;
    LinearLayout textView_F1;
    LinearLayout textView_F2;
    LinearLayout ClickF1;
    LinearLayout ClickF2;


    private ScaleGestureDetector mScaleGestureDetector;   //줌 기능
    private float mScaleFactor = 1.0f;
    private ImageView mImageView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        imageView_F1_Blue = (ImageView) findViewById(R.id.imageView_F1_Blue);
        imageView_F1_Red = (ImageView) findViewById(R.id.imageView_F1_Red);
        imageView_F2_Blue = (ImageView) findViewById(R.id.imageView_F2_Blue);
        imageView_F2_Red = (ImageView) findViewById(R.id.imageView_F2_Red);

        imageViewF1 = (ImageView) findViewById(R.id.imageViewF1);
        imageViewF2 = (ImageView) findViewById(R.id.imageViewF2);

        textViewF1 = (TextView) findViewById(R.id.textViewF1);
        textViewF2 = (TextView) findViewById(R.id.textViewF2);

        textView_F1 = (LinearLayout) findViewById(R.id.textView_F1);    //그래프 옆 텍스트
        textView_F2 = (LinearLayout) findViewById(R.id.textView_F2);

        colorChangeF1();     // 색 바뀌게하는 메소드 호출
        colorChangeF2();


        //클릭 이벤트를 이미지뷰가 아니라 프레임 레이아웃에 줘야할듯 ( 파란색 이미지뷰 누르면 이벤트 발생하게 해놓음 )

        imageView_F2_Blue.setOnClickListener(new View.OnClickListener() {         //setOnClickListener 함수를 이용해 이벤트 발생

            @Override
            public void onClick(View view) {          //imageView_F2_Blue 클릭시에 동작할 이벤트 - F2이미지뷰 보여주기

                changeImageView(1);
                changeText(1);
                changeLinearLayout(1);
            }
        });

        imageView_F1_Blue.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                changeImageView(0);
                changeText(0);
                changeLinearLayout(0);
            }
        });


        // 줌 기능


        // xml에 정의한 이미지뷰 찾고
        mImageView = (ImageView) findViewById(R.id.imageView2);

        // 스케일제스쳐 디텍터 인스턴스
        mScaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());

    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        //변수로 선언해 놓은 ScaleGestureDetector
        mScaleGestureDetector.onTouchEvent(motionEvent);
        return true;
    }


    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            // ScaleGestureDetector에서 factor를 받아 변수로 선언한 factor에 넣고
            mScaleFactor *= scaleGestureDetector.getScaleFactor();

            // 최대 10배, 최소 10배 줌 한계 설정
            mScaleFactor = Math.max(0.1f,
                    Math.min(mScaleFactor, 10.0f));

            // 이미지뷰 스케일에 적용
            mImageView.setScaleX(mScaleFactor);
            mImageView.setScaleY(mScaleFactor);
            return true;
        }
    }

    public void changeImageView(int j) {    //F1,2 이미지뷰(그래프) 바꾸기

        //int j = 1;

        if (j == 0) {
            imageViewF1.setVisibility(View.VISIBLE); //만약 j 가 0이면 imageViewF1 가 보이게 해라
            imageViewF2.setVisibility(View.INVISIBLE);

        } else if (j == 1) {
            imageViewF1.setVisibility(View.INVISIBLE); //만약 j 가 1이면 imageViewF2 가 보이게 해라
            imageViewF2.setVisibility(View.VISIBLE);

        }
    }


    public void changeText(int t) {                    //F_1/2 그래프 텍스트뷰 바꾸기
        if (t == 0) {
            textViewF1.setVisibility(View.VISIBLE); //만약 i 가 0이면 textViewF2 가 보이게 해라
            textViewF2.setVisibility(View.INVISIBLE);

        } else if (t == 1) {
            textViewF1.setVisibility(View.INVISIBLE); //만약 i 가 1이면 textViewF1 가 보이게 해라
            textViewF2.setVisibility(View.VISIBLE);
        }
    }

    public void changeLinearLayout(int l) {                    //F_1/2 그래프 옆 텍스트뷰 바꾸기
        if (l == 0) {
            textView_F1.setVisibility(View.VISIBLE); //만약 i 가 0이면 first_imageView 가 보이게 해라
            textView_F2.setVisibility(View.INVISIBLE);

        } else if (l == 1) {
            textView_F1.setVisibility(View.INVISIBLE); //만약 i 가 1이면 second_imageView 가 보이게 해라
            textView_F2.setVisibility(View.VISIBLE);

        }
    }



    //메서드 이안에꺼 실행할려면 메서드 실행해야함 고친다고 안댐
    public void colorChangeF1() {
        int i = 1;


        if (i == 0) {
            imageView_F1_Red.setVisibility(View.VISIBLE); //만약 i 가 0이면 imageView_F1_Red 가 보이게 해라
            imageView_F1_Blue.setVisibility(View.INVISIBLE);

        } else if (i == 1) {
            imageView_F1_Red.setVisibility(View.INVISIBLE); //만약 i 가 1이면 imageView_F1_Blue 가 보이게 해라
            imageView_F1_Blue.setVisibility(View.VISIBLE);

        }
    }


    public void colorChangeF2() {
        int a = 1;        //0이면 비정상(빨간색), 1이면 정상(파란색)


        if (a == 0) {
            imageView_F2_Red.setVisibility(View.VISIBLE); //만약 i 가 0이면 imageView_F1_Red 가 보이게 해라
            imageView_F2_Blue.setVisibility(View.INVISIBLE);

        } else if (a == 1) {
            imageView_F2_Red.setVisibility(View.INVISIBLE); //만약 i 가 1이면 imageView_F1_Blue 가 보이게 해라
            imageView_F2_Blue.setVisibility(View.VISIBLE);

        }

}}




