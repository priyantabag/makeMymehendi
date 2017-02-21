package com.sup.mehandi.base;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;

import com.sup.mehandi.R;
import com.sup.mehandi.utils.FontChangeCrawler;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

    }

    public void setFontStyle() {
        FontChangeCrawler fontChanger = new FontChangeCrawler(getAssets(), "fonts/Sansation-Light.ttf");
        fontChanger.replaceFonts((ViewGroup) this.findViewById(android.R.id.content));
    }

}
