package com.androidschool.denis.okuma;

import java.io.*;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.appcompat.app.AppCompatActivity;

public class AlarmManager extends AppCompatActivity {
    //Folders
    public static final String pathToHtmlFolderLPA = "html/OSP-P300S/LPA/ENG/";
    public static final String pathToHtmlFolderLPP = "html/OSP-P300S/LPP/ENG/";
    //LPA
    public static final String ADDRESS_ALARM_00 = pathToHtmlFolderLPA + "ALARM-00.HTM";

    public static final String ADDRESS_ALARM_A = pathToHtmlFolderLPA + "ALARM-A.HTM";
    public static final String ADDRESS_ALARM_B = pathToHtmlFolderLPA + "ALARM-B.HTM";
    public static final String ADDRESS_ALARM_C = pathToHtmlFolderLPA + "ALARM-C.HTM";
    public static final String ADDRESS_ALARM_D = pathToHtmlFolderLPA + "ALARM-D.HTM";

    public static final String ADDRESS_ALARM_ERR = pathToHtmlFolderLPA + "ALARM-ERR.HTM";

    public static final String TYPE_ALARM = "Alarm";
    public static final String TYPE_MCD = "MCD";
    //LPP


    public static final String ADDRESS_ALARM_85 = pathToHtmlFolderLPP + "ALARM-85.HTM";
    public static final String ADDRESS_ALARM_98 = pathToHtmlFolderLPP + "ALARM-98.HTM";
    public static final String ADDRESS_ALARM_85000 = pathToHtmlFolderLPP + "ALARM-85000.HTM";
    public static final String ADDRESS_ALARM_90000 = pathToHtmlFolderLPP + "ALARM-90000.HTM";

    public static final String ADDRESS_MCD = pathToHtmlFolderLPP + "MCD.HTM";
    public static final String ADDRESS_ALARM = "ALARM-D.HTM";

    public static final String SPLITTER_ALARM = "</PRE>";
    public static final String SPLITTER_MCD = "</TD></TR>";

    public static final String PATTERN_ALARM = "(.*)(<A NAME=\")(\\d+)(.*)(<FONT color = \"WHITE\" size = \"5\">)(.*)(</FONT></TD></TR>)(.*)(<PRE>)(.*)";
    //---------------------------------------1------2----------3---4------------------------5-----------------6------------7----------8----9----10
//int groupForNumber=3, int groupForTitle=6, int groupForDesc=10

    public static final String PATTERN_MCD = "(.*)(<FONT SIZE = \"5\" COLOR = \"BLUE\">)(.+)(</FONT></A></TD>)(<TD WIDTH = \"400\">)(.*)(<BR>)(.*)";
    //-----------------------------------------1-------------------2----------------------3---------4----------------------5----------6----7----8
//int groupForNumber=3, int groupForTitle=3, int groupForDesc=6


//    String separator = File.separator;
//    String path = "D:"+separator+"Users"+separator+"Dream Store"+separator+"IdeaProjects"+separator+"Split"+separator+"AlarmsC";


    public static String getResultTextFromHtml(String fileName) {

        File file = new File(fileName);
        FileReader fr = null;
        StringBuilder resultText = new StringBuilder();
        try {
            fr = new FileReader(file);

            BufferedReader br = new BufferedReader(fr);
            String line;

            while ((line = br.readLine()) != null) {
                //process the line
                resultText.append(line).append('\n');
                //System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(resultText);

        String resultTextString = String.valueOf(resultText);
        return resultTextString;
    }

    public String getStringFromHtml(String fileName) {
        StringBuffer strBuffer = new StringBuffer();
        try {
            FileInputStream fileInput = openFileInput(fileName);
//            FileInputStream fileInput = new File(fileName);
            InputStreamReader reader = new InputStreamReader(fileInput);
            BufferedReader br = new BufferedReader(reader);
            String lines;
            while ((lines = br.readLine()) != null) {
                strBuffer.append(lines).append("\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String resultTextString = String.valueOf(strBuffer);
        return resultTextString;
    }

    public static String[] getAlarmsArrayFromTextString(String resultTextString, String splitter) {
        //String[] errorsArray = resultTextString.split("</PRE>");
        String[] errorsArray = resultTextString.split(splitter);


        return errorsArray;
    }

    public static ExampleItem getAlarmObjectFromAlarmString(String alarmString, String pattern, int groupForNumber, int groupForTitle, int groupForDesc) {

        ExampleItem alarm = null;

        String line = alarmString;
        //(.)(<A NAME=")(number)(\"></A>)()(<FONT color = \"WHITE\" size = \"5\">)(title)(</FONT></TD></TR>)()(<PRE>)(description)

        // Create a Pattern object
        Pattern r = Pattern.compile(pattern);

        // Now create matcher object.
        Matcher m = r.matcher(line);
        if (m.find()) {
//            for (int i = 0; i <= 10; i++) {
//                System.out.println("m.group(" + i + "): " + m.group(i));
//            }


            String number = m.group(groupForNumber);
            String title = m.group(groupForTitle);
            String desc = m.group(groupForDesc);
            //System.out.println("old desc="+desc);
            desc=desc.replaceAll("       ","\n");
            //System.out.println("new desc="+desc);
            alarm = new ExampleItem(number, title, desc);
            //System.out.println(">>>>>>>>>>>>>>>>>");
            //System.out.println("alarm="+alarm);
        } else {
//            System.out.println("NO MATCH");
        }
        return alarm;
    }

    public static List<ExampleItem> getAlarmsList(String[] alarmsStringArray, String pattern, String type) {
        List<ExampleItem> alarmsList = new ArrayList<>();

        if (type.equals(TYPE_ALARM)) {
            for (String alarmString : alarmsStringArray) {
                ExampleItem alarm = getAlarmObjectFromAlarmString(alarmString, pattern, 3, 6, 10);
                alarmsList.add(alarm);
            }
        } else if (type.equals(TYPE_MCD)) {
            for (String alarmString : alarmsStringArray) {
                ExampleItem alarm = getAlarmObjectFromAlarmString(alarmString, pattern, 3, 3, 6);
                alarmsList.add(alarm);
            }
        } else {
            System.out.println("Error in type");
        }


        return alarmsList;
    }

    public static void printAlarmList(List<ExampleItem> alarmsList) {
        for (ExampleItem alarm : alarmsList) {
            System.out.println(alarm);
        }
    }

    public static void main(String[] args) {
//        String delimiter = "\n" + "        \n";
//
//        String[] alarmsStringArray = getAlarmsArrayFromTextString(s1, delimiter);
//
//        System.out.println("---------alarmsStringArray----------");
//        for (int i = 0; i < alarmsStringArray.length; i++) {
//            System.out.println(alarmsStringArray[i]);
//        }

    }
}
