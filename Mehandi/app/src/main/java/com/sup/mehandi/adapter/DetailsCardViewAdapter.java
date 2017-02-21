package com.sup.mehandi.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.sup.mehandi.R;
import com.sup.mehandi.features.dashboard.DashboardActivity;
import com.sup.mehandi.features.detail_n_book.DesignerDetailActivity;
import com.sup.mehandi.model.Detail;
import com.sup.mehandi.model.User;

import java.util.List;

/**
 * Created by Shrishail on 9/30/2016.
 */
public class DetailsCardViewAdapter extends RecyclerView.Adapter<DetailsCardViewAdapter.ViewHolder> {
    private static final String TAG = "SearchBookAdapter";

    List<Detail> details;
    public Context context;
    public DesignerDetailActivity activity;

    public DetailsCardViewAdapter(List<Detail> details, Context context, DesignerDetailActivity dashboardActivity) {
        this.details = details;
        this.context = context;
        this.activity = dashboardActivity;

    }


    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {
        // create a new view
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.cv_details_layout, null);


        // create ViewHolder

        RecyclerView.ViewHolder viewHolder = new ViewHolder(itemLayoutView, details);
        return (ViewHolder) viewHolder;
       /* ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;*/
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        try {
            Typeface tfHeader = Typeface.createFromAsset(context.getAssets(), "fonts/Sansation-Bold.ttf");
            Typeface tf = Typeface.createFromAsset(context.getAssets(), "fonts/Sansation-Light.ttf");
            holder.tvDetail.setTypeface(tf);


            holder.tvDetail.setText(details.get(position).getDescription());



        } catch (Exception e) {
            e.getStackTrace();
        }

    }

    // Replace the contents of a view (invoked by the layout manager)


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return details.size();
    }

    // inner class to hold a reference to each item of RecyclerView
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private static final String TAG = "BookAppointment Adapter";

        public TextView tvDetail;



        public ViewHolder(View view, List<Detail> details) {
            super(view);
            tvDetail = (TextView) view.findViewById(R.id.tv_detail);

        }

        @Override
        public void onClick(View v) {
            v.getContext().startActivity(new Intent(v.getContext(), DesignerDetailActivity.class));
        }


    }
}
