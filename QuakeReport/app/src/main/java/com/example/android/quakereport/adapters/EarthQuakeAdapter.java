package com.example.android.quakereport.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.android.quakereport.R;
import com.example.android.quakereport.model.IEarthquake;

import org.w3c.dom.Text;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;

public class EarthQuakeAdapter extends ArrayAdapter<IEarthquake>{

    public EarthQuakeAdapter(Context context, List<IEarthquake> objects) {

        super(context, R.layout.list_item, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;

        final IEarthquake item = getItem(position);

        if (convertView == null) {

            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        TextView magnitudeTextView = (TextView) listItemView.findViewById(R.id.magnitude);

        magnitudeTextView.setText(String.valueOf(item.getMagnitude()));

        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeTextView.getBackground();

        magnitudeCircle.setColor(getMagnitudeColor(item.getMagnitude()));

        TextView locationTextView = (TextView) listItemView.findViewById(R.id.location);

        locationTextView.setText(item.getLocation());

        TextView dateTextView = (TextView) listItemView.findViewById(R.id.date);

        dateTextView.setText(new SimpleDateFormat("MMM dd, yyyy").format(item.getDate()));

        TextView nearbyTextView  = (TextView) listItemView.findViewById(R.id.nearby);

        nearbyTextView.setText(item.getNearby());

        TextView timeTextView = (TextView) listItemView.findViewById(R.id.time);

        timeTextView.setText(new SimpleDateFormat("hh: mm").format(item.getDate()));

        listItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_VIEW);

                intent.setData(Uri.parse(item.getURL()));

                getContext().startActivity(intent);
            }
        });

        return listItemView;
    }

    private int getMagnitudeColor(double magnitude) {

        int mag = (int) Math.floor(magnitude);

        int resourceId = 0;

        switch (mag) {

            case 0:
            case 1:
                resourceId = R.color.magnitude1;
                break;

            case 2:
                resourceId = R.color.magnitude2;
                break;

            case 3:
                resourceId = R.color.magnitude3;
                break;

            case 4:
                resourceId = R.color.magnitude4;
                break;

            case 5:
                resourceId = R.color.magnitude5;
                break;

            case 6:
                resourceId = R.color.magnitude6;
                break;

            case 7:
                resourceId = R.color.magnitude7;
                break;

            case 8:
                resourceId = R.color.magnitude8;
                break;

            case 9:
                resourceId = R.color.magnitude9;
                break;

            case 10:
                resourceId = R.color.magnitude10plus;
                break;
        }
        return getContext().getColor(resourceId);
    }
}
