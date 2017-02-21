package com.sup.mehandi.features.detail_n_book;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TabHost;
import android.widget.TextView;

import com.sup.mehandi.R;
import com.sup.mehandi.adapter.DetailsCardViewAdapter;
import com.sup.mehandi.base.BaseActivity;
import com.sup.mehandi.model.Detail;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class DesignerDetailActivity extends BaseActivity {

    @BindView(R.id.rv_changes)
    RecyclerView rvChanges;
    @BindView(R.id.rv_specialist)
    RecyclerView rvSpecialist;
    @BindView(R.id.rv_discount)
    RecyclerView rvDiscount;
    @BindView(R.id.rv_reviews)
    RecyclerView rvReviews;
    @BindView(R.id.button2)
    Button btnSlot;
    @BindView(R.id.tabHost)
    TabHost tabHost;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.tv_shop_name)
    TextView tvShopName;
    @BindView(R.id.cv_designer_image)
    CircleImageView cvDesignerImage;
    @BindView(R.id.tv_shop_owner)
    TextView tvShopOwner;
    @BindView(R.id.rb_rating_bar)
    RatingBar rbRatingBar;


    private RecyclerView.Adapter changeAdapter;
    private RecyclerView.Adapter specialisteAdapter;
    private RecyclerView.Adapter discountAdapter;
    private RecyclerView.Adapter reviewsAdapter;
    private RecyclerView.LayoutManager manager;
    private RecyclerView.LayoutManager manager1;
    private RecyclerView.LayoutManager manager2;
    private RecyclerView.LayoutManager manager3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_designer_detail);
        ButterKnife.bind(this);
        Typeface tfHeader = Typeface.createFromAsset(getAssets(), "fonts/Sansation-Bold.ttf");
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/Sansation-Light.ttf");
        //setFontStyle();

        tvShopName.setTypeface(tfHeader);
        tvShopOwner.setTypeface(tf);
        btnSlot.setTypeface(tfHeader);
        toolbarTitle.setTypeface(tfHeader);
        toolbarTitle.setText("Details");

        tabHost.setup();

        TabHost.TabSpec tabChanges = tabHost.newTabSpec("changes");
        tabChanges.setContent(R.id.tab1);
        tabChanges.setIndicator("Changes");
        tabHost.addTab(tabChanges);

        TabHost.TabSpec tabSpecialist = tabHost.newTabSpec("specialist");
        tabSpecialist.setContent(R.id.tab2);
        tabSpecialist.setIndicator("Specialist");
        tabHost.addTab(tabSpecialist);

        TabHost.TabSpec tabDiscount = tabHost.newTabSpec("discount");
        tabDiscount.setContent(R.id.tab3);
        tabDiscount.setIndicator("Discount");
        tabHost.addTab(tabDiscount);

        TabHost.TabSpec tabReviews = tabHost.newTabSpec("reviews");
        tabReviews.setContent(R.id.tab4);
        tabReviews.setIndicator("Reviews");
        tabHost.addTab(tabReviews);

        setTabTextColor(tabHost, 0);
        setSelectedTabColor(tabHost);
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                switch (tabId) {
                    case "changes":
                        setTabTextColor(tabHost, 0);
                        setSelectedTabColor(tabHost);
                        //Back(host,0);

                        break;
                    case "specialist":
                        setTabTextColor(tabHost, 1);
                        setSelectedTabColor(tabHost);
                        //Back(host, 1);
                        break;
                    case "discount":
                        setTabTextColor(tabHost, 2);
                        setSelectedTabColor(tabHost);
                        //Back(host,2);
                        break;
                    case "reviews":
                        setTabTextColor(tabHost, 3);
                        setSelectedTabColor(tabHost);
                        //Back(host,2);
                        break;
                }
            }
        });

        manager = new LinearLayoutManager(getApplicationContext());
        manager1 = new LinearLayoutManager(getApplicationContext());
        manager2 = new LinearLayoutManager(getApplicationContext());
        manager3 = new LinearLayoutManager(getApplicationContext());

        rvChanges.setLayoutManager(manager);
        rvDiscount.setLayoutManager(manager1);
        rvReviews.setLayoutManager(manager2);
        rvSpecialist.setLayoutManager(manager3);

        getDetails();

    }


    List<Detail> changesDetails = new ArrayList<>();
    List<Detail> discountDetails = new ArrayList<>();
    List<Detail> specialistDetails = new ArrayList<>();
    List<Detail> reviewsDetails = new ArrayList<>();

    private void getDetails() {

        changesDetails.add(new Detail("Single hand Rs 50"));
        changesDetails.add(new Detail("Both Hands + Legs Rs 200"));

        discountDetails.add(new Detail("05 persons get up to 10% Off"));
        discountDetails.add(new Detail("10 persons get up to 20% Off"));
        discountDetails.add(new Detail("30 persons get up to 20% Off"));

        specialistDetails.add(new Detail("Rajasthani"));
        specialistDetails.add(new Detail("Arabian"));

        changeAdapter = new DetailsCardViewAdapter(changesDetails, this, this);
        discountAdapter = new DetailsCardViewAdapter(discountDetails, this, this);
        specialisteAdapter = new DetailsCardViewAdapter(specialistDetails, this, this);

        rvChanges.setAdapter(changeAdapter);
        rvDiscount.setAdapter(discountAdapter);
        rvSpecialist.setAdapter(specialisteAdapter);


    }

    @OnClick(R.id.button2)
    public void onClick() {
    }

    private void setSelectedTabColor(TabHost tabhost) {
        for (int i = 0; i < tabhost.getTabWidget().getChildCount(); i++) {
            tabhost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#FFFFFF")); //unselected
        }
        tabhost.getTabWidget().getChildAt(tabhost.getCurrentTab()).setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary)); // selecte
    }

    public void setTabTextColor(TabHost tabhost, int i) {

        //tabhost.getTabWidget().getChildAt(1).setBackgroundColor(Color.parseColor("#000000")); //unselected
        switch (i) {
            case 0: {
                TextView tvChangeTitle = (TextView) tabhost.getTabWidget().getChildAt(0).findViewById(android.R.id.title);
                tvChangeTitle.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));

                TextView tvSpecialistTitle = (TextView) tabhost.getTabWidget().getChildAt(1).findViewById(android.R.id.title);
                tvSpecialistTitle.setTextColor(Color.GRAY);

                TextView tvDiscountTitle = (TextView) tabhost.getTabWidget().getChildAt(2).findViewById(android.R.id.title);
                tvDiscountTitle.setTextColor(Color.GRAY);

                TextView tvReviewTitle = (TextView) tabhost.getTabWidget().getChildAt(3).findViewById(android.R.id.title);
                tvReviewTitle.setTextColor(Color.GRAY);

                //tabhost.getTabWidget().setDividerDrawable(R.drawable.tab_indicator);
            }
            break;

            case 1: {
                TextView tvChangeTitle = (TextView) tabhost.getTabWidget().getChildAt(0).findViewById(android.R.id.title);
                tvChangeTitle.setTextColor(Color.GRAY);

                TextView tvSpecialistTitle = (TextView) tabhost.getTabWidget().getChildAt(1).findViewById(android.R.id.title);
                tvSpecialistTitle.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));

                TextView tvDiscountTitle = (TextView) tabhost.getTabWidget().getChildAt(2).findViewById(android.R.id.title);
                tvDiscountTitle.setTextColor(Color.GRAY);

                TextView tvReviewTitle = (TextView) tabhost.getTabWidget().getChildAt(3).findViewById(android.R.id.title);
                tvReviewTitle.setTextColor(Color.GRAY);
            }

            break;

            case 2: {
                TextView tvChangeTitle = (TextView) tabhost.getTabWidget().getChildAt(0).findViewById(android.R.id.title);
                tvChangeTitle.setTextColor(Color.GRAY);

                TextView tvSpecialistTitle = (TextView) tabhost.getTabWidget().getChildAt(1).findViewById(android.R.id.title);
                tvSpecialistTitle.setTextColor(Color.GRAY);

                TextView tvDiscountTitle = (TextView) tabhost.getTabWidget().getChildAt(2).findViewById(android.R.id.title);
                tvDiscountTitle.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));

                TextView tvReviewTitle = (TextView) tabhost.getTabWidget().getChildAt(3).findViewById(android.R.id.title);
                tvReviewTitle.setTextColor(Color.GRAY);
            }
            break;

            case 3: {
                TextView tvChangeTitle = (TextView) tabhost.getTabWidget().getChildAt(0).findViewById(android.R.id.title);
                tvChangeTitle.setTextColor(Color.GRAY);

                TextView tvSpecialistTitle = (TextView) tabhost.getTabWidget().getChildAt(1).findViewById(android.R.id.title);
                tvSpecialistTitle.setTextColor(Color.GRAY);

                TextView tvDiscountTitle = (TextView) tabhost.getTabWidget().getChildAt(2).findViewById(android.R.id.title);
                tvDiscountTitle.setTextColor(Color.GRAY);

                TextView tvReviewTitle = (TextView) tabhost.getTabWidget().getChildAt(3).findViewById(android.R.id.title);
                tvReviewTitle.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
            }
            break;

        }
        TextView textView = (TextView) tabhost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
        textView.setTextColor(Color.WHITE);

        // tabhost.getTabWidget().getChildAt(tabhost.getCurrentTab()).setBackgroundColor(Color.parseColor("#000000"));
        // selected


        tabhost.getTabWidget().getChildAt(i).setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary)); //unselected


    }

}
