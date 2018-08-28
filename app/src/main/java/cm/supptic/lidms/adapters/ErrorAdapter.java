package cm.supptic.lidms.adapters;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import cm.supptic.lidms.R;
import cm.supptic.lidms.objects.Demand;

/**
 * Created by codename-tkc on 24/07/2018.
 */

public class ErrorAdapter extends RecyclerView.Adapter<ErrorAdapter.DataHolder> {

    @Override
    public DataHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.error_view, parent, false);
        return new ErrorAdapter.DataHolder(view);
    }

    @Override
    public void onBindViewHolder(DataHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        return 1;
    }

    class DataHolder extends RecyclerView.ViewHolder {


        public DataHolder(View itemView) {
            super(itemView);
        }
    }

}
