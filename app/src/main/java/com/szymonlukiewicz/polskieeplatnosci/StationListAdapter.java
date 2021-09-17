package com.szymonlukiewicz.polskieeplatnosci;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StationListAdapter extends RecyclerView.Adapter<StationListAdapter.StationViewHolder> {

    private Context context;
    private ArrayList<Station> stations;
    private StationClickListener stationClickListener;


    public StationListAdapter(Context context, StationClickListener stationClickListener) {
        this.stationClickListener = stationClickListener;
        this.context = context;
    }

    void loadData(ArrayList<Station> stations) {
        this.stations = stations;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public StationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.station_row_layout, parent, false);

        return new StationViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final StationViewHolder holder, final int position) {

        Station station = stations.get(position);

        String line1 = station.name;

        String line2;
        if (station.address == null) {
            line2 = station.city;
        } else {
            line2 = station.address + ", " + station.city;
        }
        String line3 = station.commune + ", " + station.district + ", " + station.province;

        holder.line1TextView.setText(line1);
        holder.line2TextView.setText(line2);
        holder.line3TextView.setText(line3);

        if (station.isExpanded) {
            if (station.overallIndexLevel != null) {
                int imageResourceID = 0;
                switch (station.overallIndexLevel.id) {
                    case 0:
                        imageResourceID = R.drawable.circle_0;
                        break;
                    case 1:
                        imageResourceID = R.drawable.circle_1;
                        break;
                    case 2:
                        imageResourceID = R.drawable.circle_2;
                        break;
                    case 3:
                        imageResourceID = R.drawable.circle_3;
                        break;
                    case 4:
                        imageResourceID = R.drawable.circle_4;
                        break;
                    case 5:
                        imageResourceID = R.drawable.circle_5;
                        break;
                }

                holder.airQualityImageView.setImageResource(imageResourceID);
                holder.airQualityTextView.setVisibility(View.VISIBLE);
                holder.airQualityImageView.setVisibility(View.VISIBLE);
            }
            if (station.so2IndexLevel != null) {
                int imageResourceID = 0;
                switch (station.so2IndexLevel.id) {
                    case 0:
                        imageResourceID = R.drawable.circle_0;
                        break;
                    case 1:
                        imageResourceID = R.drawable.circle_1;
                        break;
                    case 2:
                        imageResourceID = R.drawable.circle_2;
                        break;
                    case 3:
                        imageResourceID = R.drawable.circle_3;
                        break;
                    case 4:
                        imageResourceID = R.drawable.circle_4;
                        break;
                    case 5:
                        imageResourceID = R.drawable.circle_5;
                        break;
                }
                holder.so2ImageView.setImageResource(imageResourceID);
                holder.so2ConstraintLayout.setVisibility(View.VISIBLE);
            }
            if (station.no2IndexLevel != null) {
                int imageResourceID = 0;
                switch (station.no2IndexLevel.id) {
                    case 0:
                        imageResourceID = R.drawable.circle_0;
                        break;
                    case 1:
                        imageResourceID = R.drawable.circle_1;
                        break;
                    case 2:
                        imageResourceID = R.drawable.circle_2;
                        break;
                    case 3:
                        imageResourceID = R.drawable.circle_3;
                        break;
                    case 4:
                        imageResourceID = R.drawable.circle_4;
                        break;
                    case 5:
                        imageResourceID = R.drawable.circle_5;
                        break;
                }
                holder.no2ImageView.setImageResource(imageResourceID);
                holder.no2ConstraintLayout.setVisibility(View.VISIBLE);
            }
            if (station.pm10IndexLevel != null) {
                int imageResourceID = 0;
                switch (station.pm10IndexLevel.id) {
                    case 0:
                        imageResourceID = R.drawable.circle_0;
                        break;
                    case 1:
                        imageResourceID = R.drawable.circle_1;
                        break;
                    case 2:
                        imageResourceID = R.drawable.circle_2;
                        break;
                    case 3:
                        imageResourceID = R.drawable.circle_3;
                        break;
                    case 4:
                        imageResourceID = R.drawable.circle_4;
                        break;
                    case 5:
                        imageResourceID = R.drawable.circle_5;
                        break;
                }
                holder.PM10ImageView.setImageResource(imageResourceID);
                holder.PM10ConstraintLayout.setVisibility(View.VISIBLE);
            }
            if (station.pm25IndexLevel != null) {
                int imageResourceID = 0;
                switch (station.pm25IndexLevel.id) {
                    case 0:
                        imageResourceID = R.drawable.circle_0;
                        break;
                    case 1:
                        imageResourceID = R.drawable.circle_1;
                        break;
                    case 2:
                        imageResourceID = R.drawable.circle_2;
                        break;
                    case 3:
                        imageResourceID = R.drawable.circle_3;
                        break;
                    case 4:
                        imageResourceID = R.drawable.circle_4;
                        break;
                    case 5:
                        imageResourceID = R.drawable.circle_5;
                        break;
                }
                holder.PM25ImageView.setImageResource(imageResourceID);

                holder.PM25ConstraintLayout.setVisibility(View.VISIBLE);
            }
            if (station.o3IndexLevel != null) {
                int imageResourceID = 0;
                switch (station.o3IndexLevel.id) {
                    case 0:
                        imageResourceID = R.drawable.circle_0;
                        break;
                    case 1:
                        imageResourceID = R.drawable.circle_1;
                        break;
                    case 2:
                        imageResourceID = R.drawable.circle_2;
                        break;
                    case 3:
                        imageResourceID = R.drawable.circle_3;
                        break;
                    case 4:
                        imageResourceID = R.drawable.circle_4;
                        break;
                    case 5:
                        imageResourceID = R.drawable.circle_5;
                        break;
                }
                holder.o3ImageView.setImageResource(imageResourceID);
                holder.o3ConstraintLayout.setVisibility(View.VISIBLE);
            }
        } else {
            holder.airQualityTextView.setVisibility(View.GONE);
            holder.airQualityImageView.setVisibility(View.GONE);
            holder.so2ConstraintLayout.setVisibility(View.GONE);
            holder.no2ConstraintLayout.setVisibility(View.GONE);
            holder.PM10ConstraintLayout.setVisibility(View.GONE);
            holder.PM25ConstraintLayout.setVisibility(View.GONE);
            holder.o3ConstraintLayout.setVisibility(View.GONE);
        }


        ArrayList<View> clickableViews = new ArrayList<>();

        clickableViews.add(holder.line1TextView);
        clickableViews.add(holder.line2TextView);
        clickableViews.add(holder.line3TextView);
        clickableViews.add(holder.constraintLayout);

        for (View view : clickableViews) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    stationClickListener.onStationItemClick(position);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        if (stations == null) {
            return 0;
        } else {
            return stations.size();
        }
    }

    class StationViewHolder extends RecyclerView.ViewHolder {

        private TextView line1TextView;
        private TextView line2TextView;
        private TextView line3TextView;
        private ConstraintLayout constraintLayout;

        private TextView airQualityTextView;
        private ImageView airQualityImageView;

        private ImageView so2ImageView;
        private ConstraintLayout so2ConstraintLayout;

        private ImageView no2ImageView;
        private ConstraintLayout no2ConstraintLayout;

        private ImageView PM10ImageView;
        private ConstraintLayout PM10ConstraintLayout;

        private ImageView PM25ImageView;
        private ConstraintLayout PM25ConstraintLayout;

        private ImageView o3ImageView;
        private ConstraintLayout o3ConstraintLayout;

        StationViewHolder(View view) {
            super(view);

            line1TextView = view.findViewById(R.id.line1TextView);
            line2TextView = view.findViewById(R.id.line2TextView);
            line3TextView = view.findViewById(R.id.line3TextView);
            constraintLayout = view.findViewById(R.id.constraintLayout);

            airQualityTextView = view.findViewById(R.id.airQualityTextView);
            airQualityImageView = view.findViewById(R.id.airQualityImageView);

            so2ImageView = view.findViewById(R.id.so2ImageView);
            so2ConstraintLayout = view.findViewById(R.id.so2ConstraintLayout);

            no2ImageView = view.findViewById(R.id.no2ImageView);
            no2ConstraintLayout = view.findViewById(R.id.no2ConstraintLayout);

            PM10ImageView = view.findViewById(R.id.PM10ImageView);
            PM10ConstraintLayout = view.findViewById(R.id.PM10ConstraintLayout);

            PM25ImageView = view.findViewById(R.id.PM25ImageView);
            PM25ConstraintLayout = view.findViewById(R.id.PM25ConstraintLayout);

            o3ImageView = view.findViewById(R.id.o3ImageView);
            o3ConstraintLayout = view.findViewById(R.id.o3ConstraintLayout);
        }
    }

    public interface StationClickListener {
        void onStationItemClick(int position);
    }
}