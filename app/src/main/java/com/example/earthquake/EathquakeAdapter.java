package com.example.earthquake;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.earthquake.POJO.Feature;

import java.util.List;

public class EathquakeAdapter extends RecyclerView.Adapter<EathquakeAdapter.PropertyViewHolder> {
    private List<Feature> featureList;
    private Context context;

    public EathquakeAdapter(List<Feature> featureList, Context context) {
        this.featureList = featureList;
        this.context = context;
    }

    public void setFeatureList(List<Feature> featureList) {
        this.featureList = featureList;
        notifyDataSetChanged();
    }

    @Override
    public PropertyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycle_view,viewGroup,false);
        return new PropertyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(PropertyViewHolder propertyViewHolder, int i) {
        String magnitude = String.valueOf(featureList.get(i).getProperties().getMag());
        propertyViewHolder.place.setText(featureList.get(i).getProperties().getPlace());
        propertyViewHolder.magnitudeView.setText(magnitude);
    }

    @Override
    public int getItemCount() {
        if(featureList != null){
            return featureList.size();
        }
        return 0;

    }


    class PropertyViewHolder extends RecyclerView.ViewHolder {

        TextView magnitudeView;
        TextView place;



        public PropertyViewHolder(View itemView) {
            super(itemView);
            place = (TextView) itemView.findViewById(R.id.place);
            magnitudeView = (TextView) itemView.findViewById(R.id.magnitude);

        }


    }
}
