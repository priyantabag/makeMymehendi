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
import com.sup.mehandi.model.User;

import java.util.List;

/**
 * Created by Shrishail on 9/30/2016.
 */
public class CustomerDashboardCardViewAdapter extends RecyclerView.Adapter<CustomerDashboardCardViewAdapter.ViewHolder> {
    private static final String TAG = "SearchBookAdapter";

    List<User> designers;
    public Context context;
    public DashboardActivity dashboardActivity;

    public CustomerDashboardCardViewAdapter(List<User> designers, Context context, DashboardActivity dashboardActivity) {
        this.designers = designers;
        this.context = context;
        this.dashboardActivity = dashboardActivity;

    }


    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {
        // create a new view
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.cv_customer_dashboard_layout, null);


        // create ViewHolder

        RecyclerView.ViewHolder viewHolder = new ViewHolder(itemLayoutView, designers);
        return (ViewHolder) viewHolder;
       /* ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;*/
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        try {
            Typeface tfHeader = Typeface.createFromAsset(context.getAssets(), "fonts/Sansation-Bold.ttf");
            Typeface tf = Typeface.createFromAsset(context.getAssets(), "fonts/Sansation-Light.ttf");
            holder.tvDesignerName.setTypeface(tfHeader);
            holder.tvDesignerAddress.setTypeface(tf);
            holder.btnDetails.setTypeface(tfHeader);

            holder.tvDesignerName.setText(designers.get(position).getName());
            holder.tvDesignerAddress.setText(designers.get(position).getAddress());
            holder.rbRatings.setRating(designers.get(position).getRatings());


        } catch (Exception e) {
            e.getStackTrace();
        }

    }

    // Replace the contents of a view (invoked by the layout manager)


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return designers.size();
    }

    // inner class to hold a reference to each item of RecyclerView
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private static final String TAG = "BookAppointment Adapter";

        public TextView tvDesignerName;
        public TextView tvDesignerAddress;
        public Button btnDetails;
        public RatingBar rbRatings;


        public ViewHolder(View view, List<User> doctors) {
            super(view);
            tvDesignerName = (TextView) view.findViewById(R.id.tv_shop_owner);
            tvDesignerAddress = (TextView) view.findViewById(R.id.tv_designer_address);
            btnDetails = (Button) view.findViewById(R.id.btn_details);
            rbRatings = (RatingBar) view.findViewById(R.id.rb_rating_bar);
            btnDetails.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            v.getContext().startActivity(new Intent(v.getContext(), DesignerDetailActivity.class));
        }


    }
}
