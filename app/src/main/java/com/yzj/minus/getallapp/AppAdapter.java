package com.yzj.minus.getallapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class AppAdapter extends RecyclerView.Adapter<AppAdapter.ViewHolder> {
    private Context context;
    private List<JSONObject> dataList;

    public AppAdapter(Context context, List<JSONObject> dataList){
        this.context = context;
        this.dataList = dataList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_app, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        JSONObject data = dataList.get(position);
        try {
            holder.dataView.setText(data.toString(2));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return dataList==null?0:dataList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView dataView;
        public ViewHolder(View view){
            super(view);
            dataView = view.findViewById(R.id.app_json_info);
        }
    }
}
