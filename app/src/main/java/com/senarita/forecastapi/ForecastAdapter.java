package com.senarita.forecastapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Arthy on 11/15/2015.
 */
public class ForecastAdapter extends BaseAdapter {

    private static final String LOG_TAG=ForecastAdapter.class.getSimpleName();
    List<DailyData> mDailyDataList;
    Context mContext;
    LayoutInflater mInflater;

    public ForecastAdapter(Context context,List<DailyData> dataList) {
        mDailyDataList=dataList;
        mContext = context;
        mInflater = (LayoutInflater) context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        mInflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mDailyDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return mDailyDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        //return position;
        return(null !=mDailyDataList ? mDailyDataList.size():0);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;

        if (convertView == null) {

            view= LayoutInflater.from(parent.getContext()).inflate(R.layout.forecast_list_item,null);

        } else {

            view = convertView;
        }

        ViewHolder viewHolder = new ViewHolder(view);
        DailyData dailyData1=mDailyDataList.get(position);
        viewHolder.txtView.setText(dailyData1.getTime() +"\n"+  dailyData1.getTemperatureMin()+" \u2109");
        Picasso.with(mContext)
                .load(dailyData1.getIcon())
                .error(R.drawable.cloudy)
                .placeholder(R.drawable.download)
                .into(viewHolder.imgView);
        return view;
    }




}
