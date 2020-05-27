package com.example.admin.bibleapp.Parser;

import android.content.Context;
import android.content.SharedPreferences;
import com.example.admin.bibleapp.Activity.Notes;
import com.example.admin.bibleapp.ViewPager.CalContentView;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class XMLPullParserHandler {
    private Employee employee;
    List<Employee> employees = new ArrayList();
    Context mcon;
    String table_plan;
    private String text;

    public XMLPullParserHandler(Context con) {
        this.mcon = con;
    }

    public List<Employee> getEmployees() {
        return this.employees;
    }

    public List<Employee> parse(InputStream is) {
        SharedPreferences sharedPreferences = this.mcon.getSharedPreferences("MyPrefs", 0);
        this.table_plan = sharedPreferences.getString("tablePlan", null);
        String activi = sharedPreferences.getString("activit", null);
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(is, null);
            for (int eventType = parser.getEventType(); eventType != 1; eventType = parser.next()) {
                String tagname = parser.getName();
                switch (eventType) {
                    case 2:
                        if (!tagname.equalsIgnoreCase("LoadData")) {
                            break;
                        }
                        this.employee = new Employee();
                        break;
                    case 3:
                        if (tagname.equalsIgnoreCase("LoadData")) {
                            this.employees.add(this.employee);
                        } else if (tagname.equalsIgnoreCase("portion1")) {
                            this.employee.setInput1(this.text);
                        } else if (tagname.equalsIgnoreCase("id")) {
                            this.employee.setId(this.text);
                        } else if (tagname.equalsIgnoreCase("portion2")) {
                            this.employee.setInput2(this.text);
                        } else if (tagname.equalsIgnoreCase("portion3")) {
                            this.employee.setInput3(this.text);
                        } else if (tagname.equalsIgnoreCase("portion4")) {
                            this.employee.setInput4(this.text);
                        }
                        if (!activi.equals("notes")) {
                            if (!activi.equals("calcontent")) {
                                break;
                            }
                            ((CalContentView) this.mcon).load(this.employee.getId(), this.employee.getInput1(), this.employee.getInput2(), this.employee.getInput3(), this.employee.getInput4());
                            break;
                        }
                        ((Notes) this.mcon).load(this.table_plan, this.employee.getId(), this.employee.getInput1(), this.employee.getInput2(), this.employee.getInput3(), this.employee.getInput4());
                        break;
                    case 4:
                        this.text = parser.getText();
                        break;
                    default:
                        break;
                }
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        return this.employees;
    }
}
