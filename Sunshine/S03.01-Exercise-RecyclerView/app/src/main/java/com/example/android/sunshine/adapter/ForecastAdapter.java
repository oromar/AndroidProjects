package com.example.android.sunshine.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.sunshine.R;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ForecastAdapterViewHolder> {


    private String[] mWeatherData;

    @NonNull
    @Override
    public ForecastAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.forecast_list_item, parent, false);

        return new ForecastAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ForecastAdapterViewHolder holder, int position) {

        holder.mWeatherTextView.setText(mWeatherData[position]);
    }

    @Override
    public int getItemCount() {

        return mWeatherData == null ? 0 : mWeatherData.length;
    }

    public void setmWeatherData(String[] mWeatherData) {

        this.mWeatherData = mWeatherData;

        notifyDataSetChanged();
    }

    class ForecastAdapterViewHolder extends RecyclerView.ViewHolder {

        public final TextView mWeatherTextView;


        public ForecastAdapterViewHolder(View itemView) {

            super(itemView);

            mWeatherTextView = itemView.findViewById(R.id.tv_weather_data);
        }
    }
}
