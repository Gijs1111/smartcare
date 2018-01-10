package com.example.gijsdewert.smartcare.Adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gijsdewert.smartcare.Domains.EmployeeItem;
import com.example.gijsdewert.smartcare.R;

import java.util.List;

/**
 * Created by gijsdewert on 13-12-17.
 */

public class EmployeeAdapter extends ArrayAdapter<EmployeeItem> {

    private Context context;
    private int layoutResourceId;
    private List<EmployeeItem> data = null;

    public EmployeeAdapter(@NonNull Context context, int resource, @NonNull List<EmployeeItem> objects) {
        super(context, resource, objects);

        this.layoutResourceId = resource;
        this.context = context;
        this.data = objects;
    }


    static class DataHolder {
        ImageView ivEmployee;
        TextView tvEmployee;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        DataHolder dataHolder = null;

        if (convertView == null) {

            LayoutInflater inflater = ((Activity)context).getLayoutInflater();

            convertView = inflater.inflate(layoutResourceId, null);

            dataHolder = new DataHolder();

            dataHolder.ivEmployee = (ImageView)convertView.findViewById(R.id.ivEmployee);
            dataHolder.tvEmployee = (TextView)convertView.findViewById(R.id.tvEmployee);

            convertView.setTag(dataHolder);
        }
        else
        {
            dataHolder = (DataHolder)convertView.getTag();
        }

        EmployeeItem employeeItem = data.get(position);
        dataHolder.tvEmployee.setText(employeeItem.getEmployeeName());
        dataHolder.ivEmployee.setImageResource(employeeItem.getResIdThumbnail());

        return convertView;
    }
}
