package com.example.gijsdewert.smartcare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.gijsdewert.smartcare.Adapters.EmployeeAdapter;
import com.example.gijsdewert.smartcare.Domains.EmployeeItem;

import java.util.ArrayList;
import java.util.List;

public class RushAssistanceActivity extends AppCompatActivity {

    //Fields
    private List<EmployeeItem> lstData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rush_assistance);
    }

    private void initializeList() {
        lstData = new ArrayList<>();

        final int PLACEHOLDER = R.drawable.placeholderuser;

        lstData.add(new EmployeeItem(PLACEHOLDER, "Rick van Deursen"));
        lstData.add(new EmployeeItem(PLACEHOLDER, "Monique van Rijt"));
        lstData.add(new EmployeeItem(PLACEHOLDER, "Sjaak de Visser"));
        lstData.add(new EmployeeItem(PLACEHOLDER, "Rico Veenstra"));
        lstData.add(new EmployeeItem(PLACEHOLDER, "Jelle Snelle"));

        ListView listView = (ListView)findViewById(R.id.lvEmployees);

        EmployeeAdapter employeeAdapter = new EmployeeAdapter(this, R.layout.itemrow, lstData);

        listView.setAdapter(employeeAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                System.out.println("Yowe itempje aangeklikt");
            }
        });
    }
}
