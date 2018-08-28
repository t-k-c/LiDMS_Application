package cm.supptic.lidms.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
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
 * Created by codename-tkc on 23/07/2018.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.DataHolder> {
    List<Demand> demands;
    Activity activity;

    public ListAdapter(List<Demand> demands, Activity activity) {
        this.demands = demands;
        this.activity = activity;
    }

    @Override
    public DataHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.basic_item, parent, false);
        return new DataHolder(view);
    }

    @Override
    public void onBindViewHolder(DataHolder holder, int position) {
//            green: #529979  orange: #CC6600 red: #FF0000

        final Demand demand = demands.get(position);
        holder.name.setText(demand.name);
        holder.title.setText(demand.title);
        holder.day.setText(demand.day + " days");
        holder.due.setText(demand.due);
        holder.complete.setText(demand.percentage+" %");
        int color[] = {Color.parseColor("#529979"), Color.parseColor("#CC6600"), Color.parseColor("#FF0000")};
        if (demand.status == 2) {
            holder.due.setTextColor(color[2]);
            holder.day.setTextColor(color[2]);
            holder.statusLayout.setBackgroundColor(color[2]);
            Toast.makeText(activity.getApplicationContext(),"Rejected Demand - "+demand.title,Toast.LENGTH_SHORT).show();
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new AlertDialog.Builder(activity)
                            .setTitle("Reason!")
                            .setMessage(demand.comment)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            }).create().show();
                }
            });
        } else if (demand.day > 40) {
            holder.due.setTextColor(color[1]);
            holder.day.setTextColor(color[1]);
            holder.statusLayout.setBackgroundColor(color[1]);
            Toast.makeText(activity.getApplicationContext(),"Warning Demand - "+demand.title,Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public int getItemCount() {
        return demands.size();
    }

    class DataHolder extends RecyclerView.ViewHolder {
        public TextView name, title, complete, day, due;
        public LinearLayout statusLayout;

        public DataHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            title = itemView.findViewById(R.id.title);
            complete = itemView.findViewById(R.id.complete);
            day = itemView.findViewById(R.id.days);
            due = itemView.findViewById(R.id.due);
            statusLayout = itemView.findViewById(R.id.lyz);
        }
    }

}
