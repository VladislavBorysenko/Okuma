package com.androidschool.denis.okuma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import static com.androidschool.denis.okuma.AlarmManager.ADDRESS_ALARM_00;
import static com.androidschool.denis.okuma.AlarmManager.ADDRESS_ALARM_85;
import static com.androidschool.denis.okuma.AlarmManager.ADDRESS_ALARM_85000;
import static com.androidschool.denis.okuma.AlarmManager.ADDRESS_ALARM_90000;
import static com.androidschool.denis.okuma.AlarmManager.ADDRESS_ALARM_98;
import static com.androidschool.denis.okuma.AlarmManager.ADDRESS_ALARM_A;
import static com.androidschool.denis.okuma.AlarmManager.ADDRESS_ALARM_B;
import static com.androidschool.denis.okuma.AlarmManager.ADDRESS_ALARM_C;
import static com.androidschool.denis.okuma.AlarmManager.ADDRESS_ALARM_D;
import static com.androidschool.denis.okuma.AlarmManager.ADDRESS_ALARM_ERR;
import static com.androidschool.denis.okuma.AlarmManager.ADDRESS_MCD;
import static com.androidschool.denis.okuma.AlarmManager.PATTERN_ALARM;
import static com.androidschool.denis.okuma.AlarmManager.PATTERN_MCD;
import static com.androidschool.denis.okuma.AlarmManager.SPLITTER_ALARM;
import static com.androidschool.denis.okuma.AlarmManager.SPLITTER_MCD;
import static com.androidschool.denis.okuma.AlarmManager.TYPE_ALARM;
import static com.androidschool.denis.okuma.AlarmManager.TYPE_MCD;

public class ListGroupActivity extends AppCompatActivity {

    private List<MyItem> myItems;
    private Set<String> categorySet;

    //коллекция для групп
    private List<Map<String, String>> groupData;

    //общая коллекция для коллекций элементов
    private List<List<Map<String, String>>> childData;
    //в итоге получится childData = ArrayList<childDataItem>

    //список атрибутов группы или элемента
    private Map<String, String> currentMap;

    private ExpandableListView expandableListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_group);

        initList();
        initSet();

        //заполняем коллекцию групп из массива с названиями групп
        groupData = new ArrayList<>();

        for (String group : categorySet) {

            //заполняем список атрибутов для каждой группы
            currentMap = new HashMap<>();
            currentMap.put("groupName", group);//имя компании
            groupData.add(currentMap);

        }

        //список аттрибутов групп для чтения
        String[] groupFrom = new String[]{"groupName"};

        //список ID view-элементов, в которые будут помещены аттрибуты групп
        int[] groupTo = new int[]{android.R.id.text1};

        //создаём коллекцию для коллекций элементов
        childData = initChildData();

        //список атрибутов элементов для чтения
        String[] childFrom = new String[]{"myItemName"};

        //список ID view-элементов, в которые будут помещены аттрибуты элементов
        int[] childTo = new int[]{android.R.id.text1};

        SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(
                this,
                groupData,
                android.R.layout.simple_expandable_list_item_1,
                groupFrom,
                groupTo,
                childData,
                android.R.layout.simple_list_item_1,
                childFrom,
                childTo
        );

        expandableListView = (ExpandableListView)findViewById(R.id.expandable_list);
        expandableListView.setAdapter(adapter);

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {

//                System.out.println(childData);
//                System.out.println("-------------");
//                System.out.println(childData.get(i));
//                System.out.println("-------------");
//                System.out.println(childData.get(i).get(i1));

                String title = String.valueOf((childData.get(i).get(i1).values()));
                title=title.substring(1,title.length()-1);
                System.out.println("title="+title);

                Toast.makeText(getApplicationContext(),title,Toast.LENGTH_SHORT).show();

                processClick(title);

                return false;
            }
        });

//        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
//            @Override
//            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
//                Toast.makeText(getApplicationContext(),"GROUP",Toast.LENGTH_SHORT).show();
//
//                return false;
//            }
//        });

    }

    private List<List<Map<String, String>>> initChildData() {
        List<List<Map<String, String>>> childData = new ArrayList<>();

        //заполняем список атрибутов для каждого элемента
        for (String category : categorySet) {
            List<Map<String, String>> childDataItem = new ArrayList<>();

            for (MyItem car : myItems) {
                if (car.getCategory().equals(category)) {
                    Map<String, String> myItemMap = new HashMap<>();
                    myItemMap.put("myItemName", car.getTitle());
                    childDataItem.add(myItemMap);
                }
            }
            childData.add(childDataItem);
        }
        return childData;
    }

    private void initList() {
        myItems = new ArrayList<>();
        myItems.add(new MyItem("Alarms", "ALARM-00"));
        myItems.add(new MyItem("Alarms", "ALARM-A"));
        myItems.add(new MyItem("Alarms", "ALARM-B"));
        myItems.add(new MyItem("Alarms", "ALARM-C"));
        myItems.add(new MyItem("Alarms", "ALARM-D"));
        myItems.add(new MyItem("Alarms", "ALARM-ERR"));
        myItems.add(new MyItem("Alarms", "ALARM-85"));
        myItems.add(new MyItem("Alarms", "ALARM-98"));
        myItems.add(new MyItem("Alarms", "ALARM-85000"));
        myItems.add(new MyItem("Alarms", "ALARM-90000"));
        myItems.add(new MyItem("M-CODE", "M-CODE"));

    }

    private void initSet() {
        categorySet = new TreeSet<>();

        for (MyItem myItem : myItems) {
            categorySet.add(myItem.getCategory());
        }
    }

    public void processClick(String title) {
        Class targetActivity = MainActivity.class;
        String filename = "";
        String splitter = "";
        String pattern = "";
        String type = TYPE_ALARM;

        switch (title) {
            case "ALARM-00":
                filename = ADDRESS_ALARM_00;
                splitter = SPLITTER_ALARM;
                pattern = PATTERN_ALARM;
                break;


            case "ALARM-A":
                filename = ADDRESS_ALARM_A;
                splitter = SPLITTER_ALARM;
                pattern = PATTERN_ALARM;
                break;

            case "ALARM-B":
                filename = ADDRESS_ALARM_B;
                splitter = SPLITTER_ALARM;
                pattern = PATTERN_ALARM;
                break;

            case "ALARM-C":
                filename = ADDRESS_ALARM_C;
                splitter = SPLITTER_ALARM;
                pattern = PATTERN_ALARM;
                break;

            case "ALARM-D":
                filename = ADDRESS_ALARM_D;
                splitter = SPLITTER_ALARM;
                pattern = PATTERN_ALARM;
                break;

            case "ALARM-ERR":
                filename = ADDRESS_ALARM_ERR;
                splitter = SPLITTER_ALARM;
                pattern = PATTERN_ALARM;
                break;


            case "ALARM-85":
                filename = ADDRESS_ALARM_85;
                splitter = SPLITTER_ALARM;
                pattern = PATTERN_ALARM;
                break;


            case "ALARM-98":
                filename = ADDRESS_ALARM_98;
                splitter = SPLITTER_ALARM;
                pattern = PATTERN_ALARM;
                break;

            case "ALARM-85000":
                filename = ADDRESS_ALARM_85000;
                splitter = SPLITTER_ALARM;
                pattern = PATTERN_ALARM;
                break;

            case "ALARM-90000":
                filename = ADDRESS_ALARM_90000;
                splitter = SPLITTER_ALARM;
                pattern = PATTERN_ALARM;
                break;



            case "MCD-title":
                filename = ADDRESS_MCD;
                //filename = ADDRESS_ALARM_85;
                splitter = SPLITTER_MCD;
                pattern = PATTERN_MCD;
                type = TYPE_MCD;
                break;

        }
        Intent intent = new Intent(ListGroupActivity.this, targetActivity);
        intent.putExtra("fileName", filename);
        intent.putExtra("splitter", splitter);
        intent.putExtra("pattern", pattern);
        intent.putExtra("type", type);
        startActivity(intent);
    }


}
