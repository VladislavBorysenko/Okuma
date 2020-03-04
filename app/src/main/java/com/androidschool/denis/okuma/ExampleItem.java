package com.androidschool.denis.okuma;

public class ExampleItem {
    private String mAlarmNumber;
    private String mAlarmName;
    private String mAlarmDescription;



    public ExampleItem(String AlarmNumber, String AlarmName, String AlarmDescription) {
        mAlarmNumber = AlarmNumber;
        mAlarmName = AlarmName;
        mAlarmDescription = AlarmDescription;
    }



    public String getAlarmNumber() {
        return mAlarmNumber;
    }

    public String getAlarmName() {
        return mAlarmName;
    }

    public String getAlarmDescription() {
        return mAlarmDescription;
    }

    @Override
    public String toString() {
        return "ExampleItem{" +
                "mAlarmNumber='" + mAlarmNumber + '\'' +
                ", mAlarmName='" + mAlarmName + '\'' +
                ", mAlarmDescription='" + mAlarmDescription + '\'' +
                '}';
    }
}
