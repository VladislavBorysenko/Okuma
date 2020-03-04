package com.androidschool.denis.okuma;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static com.androidschool.denis.okuma.AlarmManager.TYPE_ALARM;
import static com.androidschool.denis.okuma.AlarmManager.TYPE_MCD;
import static com.androidschool.denis.okuma.AlarmManager.getAlarmsArrayFromTextString;
import static com.androidschool.denis.okuma.AlarmManager.getAlarmsList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<ExampleItem> mExampleList;
    private ArrayList<McdItem> mMcdList;
    private RecyclerView mRecyclerView;
    private ExampleAdapter mExampleAdapter;
    private McdAdapter mMcdAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Intent intent = getIntent();
        String filename = intent.getStringExtra("fileName");
        String splitter = intent.getStringExtra("splitter");
        String pattern = intent.getStringExtra("pattern");
        String type = intent.getStringExtra("type");

        //String filename = ADDRESS_ALARM_00;
        //System.out.println("onCreate() of MainActivity");

        createList(filename, splitter, pattern, type);
        buildRecyclerView(type);

    }

    public static String readFromAssets(Context context, String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(context.getAssets().open(filename)));
        //System.out.println(">>>>>>>>>>>>>>>>>begin");
        // do reading, usually loop until end of file reading
        StringBuilder sb = new StringBuilder();
        String mLine = reader.readLine();
        while (mLine != null) {
            sb.append(mLine); // process line
            mLine = reader.readLine();
            //System.out.println("-----------LINE:---------------");
            //System.out.println(mLine+"\n");
        }
        reader.close();
        //System.out.println("-----------RESULT_OF_THE_METHOD---------------");
        //System.out.println(sb.toString());
        //System.out.println(">>>>>>>>>>>>>>>>>>end");
        return sb.toString();
    }


    public void createList(String file, String splitter, String pattern, String type) {

        System.out.println("creation of the list!");

        mExampleList = new ArrayList<>();
        mMcdList = new ArrayList<>();


        Context context = getApplicationContext();

        try {
            String filename = file;
            String ALARM_String = readFromAssets(context, filename);

            String[] alarmsStringArray = getAlarmsArrayFromTextString(ALARM_String, splitter);

            List<ExampleItem> alarmsList = getAlarmsList(alarmsStringArray, pattern, type);

            while (alarmsList.remove(null)) {
            }


            if(type.equals(TYPE_ALARM)) {
                for (ExampleItem exampleItem : alarmsList) {
                    mExampleList.add(exampleItem);
                }
            }
            if(type.equals(TYPE_MCD)) {
                for (ExampleItem exampleItem : alarmsList) {
                    mMcdList.add(new McdItem(exampleItem.getAlarmName(),exampleItem.getAlarmDescription()));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void buildRecyclerView(String type) {

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        //mLayoutManager = new LinearLayoutManager(this);
        if(type.equals(TYPE_ALARM)) {
            mExampleAdapter = new ExampleAdapter(mExampleList);
            mRecyclerView.setLayoutManager(mLayoutManager);
            mRecyclerView.setAdapter(mExampleAdapter);
        }
        if(type.equals(TYPE_MCD)) {
            mMcdAdapter = new McdAdapter(mMcdList);
            mRecyclerView.setLayoutManager(mLayoutManager);
            mRecyclerView.setAdapter(mMcdAdapter);
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Intent intent = getIntent();
                String type = intent.getStringExtra("type");
                //-------------------
                if(type.equals(TYPE_ALARM)) {
                    mExampleAdapter.getFilter().filter(newText);
                }
                if(type.equals(TYPE_MCD)) {
                    mMcdAdapter.getFilter().filter(newText);
                }
                return false;
            }
        });
        return true;
    }
}


