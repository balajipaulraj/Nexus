package com.example.admin.bibleapp.Views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.admin.bibleapp.Database.DBhelper;
import com.example.admin.bibleapp.R;

import java.io.PrintStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CalendarView extends LinearLayout {
    private static final String DATE_FORMAT = "MMM";
    private static final int DAYS_COUNT = 42;
    private static final String LOGTAG = "Calendar View";
    private Calendar currentDate = Calendar.getInstance();
    private String dateFormat;
    DBhelper dbhelper;
    private EventHandler eventHandler = null;
    private GridView grid;
    private LinearLayout header;
    int lastpos = 0;
    private ImageView leftarrow;
    ArrayList<String> list = new ArrayList();
    ArrayList<String> list1 = new ArrayList();
    ArrayList<String> list2 = new ArrayList();
    ArrayList<String> list3 = new ArrayList();
    private View monthUnder;
    int pos;
    private ImageView rightarrow;
    private TextView txtDate;
    int year;

    class C02212 implements OnClickListener {
        C02212() {
        }

        public void onClick(View v) {
            CalendarView.this.currentDate.add(2, 1);
            Log.e("Day", CalendarView.this.currentDate + "");
            CalendarView.this.updateCalendar();
        }
    }

    class C02223 implements OnClickListener {
        C02223() {
        }

        public void onClick(View v) {
            CalendarView.this.currentDate.add(2, -1);
            Log.e("Day", CalendarView.this.currentDate + "");
            CalendarView.this.updateCalendar();
        }
    }

    class C02234 implements AdapterView.OnItemClickListener {
        C02234() {
        }

        public void onItemClick(AdapterView<?> view, View cell, int position, long id) {
            Date today = (Date) view.getItemAtPosition(position);
            Log.e("yer", today.getYear() + "");
            if (today.getYear() != 70) {
                CalendarView.this.eventHandler.onDayPress((Date) view.getItemAtPosition(position));
                for (int i = 0; i < view.getCount(); i++) {
                      view.getChildAt(lastpos).setBackground(CalendarView.this.getResources( ).getDrawable(R.drawable.appwhite));
                  view.getChildAt(position).setBackground(CalendarView.this.getResources().getDrawable(R.drawable.appselect));
                }
            }
            CalendarView.this.lastpos = position;
        }
    }

    private class CalendarAdapter extends ArrayAdapter<Date> {
        int f5i = 0;
        private LayoutInflater inflater;
        Context mContext;
        private Date makeupdays;

        public CalendarAdapter(Context context, ArrayList<Date> days, Date eventDays) {
            super(context, R.layout.control_calendar_day, days);
            this.mContext = context;
            this.inflater = LayoutInflater.from(context);
            this.makeupdays = eventDays;
        }

        public View getView(int position, View view, ViewGroup parent) {
            Date date = (Date) getItem(position);
            int day = date.getDate();
            int month = date.getMonth();
            CalendarView.this.year = date.getYear();
            Date today = new Date();
            if (view == null) {
                view = this.inflater.inflate(R.layout.control_calendar_day, parent, false);
            }
            TextView tv = (TextView) ((LinearLayout) view).findViewById(R.id.tv_single_date);
            view.setBackgroundResource(0);
            if (CalendarView.this.list != null) {
                if (CalendarView.this.list.contains(day + " " + month) && month == today.getMonth()) {
                    if (day == today.getDate()) {
                        tv.setTextColor(CalendarView.this.getResources().getColor(R.color.black));
                    }
                } else if (!(month == today.getMonth() && CalendarView.this.year == today.getYear())) {
                    tv.setTextColor(CalendarView.this.getResources().getColor(R.color.black));
                }
                Log.e("List in calen", CalendarView.this.list + "");
            }
            if (CalendarView.this.year == 70) {
                tv.setTextColor(CalendarView.this.getResources().getColor(R.color.white));
                tv.setVisibility(4);
                tv.setEnabled(false);
                tv.setClickable(false);
            } else {
                tv.setVisibility(0);
                tv.setEnabled(false);
                tv.setClickable(false);
                tv.setTextColor(CalendarView.this.getResources().getColor(R.color.black));
            }
            if (day == today.getDate() && month == today.getMonth()) {
                tv.setTextColor(CalendarView.this.getResources().getColor(R.color.colorPrimary));
            }
            if (CalendarView.this.list1 != null && CalendarView.this.list1.contains(day + " " + month)) {
                tv.setBackgroundColor(CalendarView.this.getResources().getColor(R.color.dailreadingbg));
                Log.e("List1 in calen", CalendarView.this.list1 + "");
            }
            if (CalendarView.this.list2 != null && CalendarView.this.list2.contains(day + " " + month)) {
                tv.setBackgroundColor(CalendarView.this.getResources().getColor(R.color.dailreadingbg));
                Log.e("List2 in calen", CalendarView.this.list2 + "");
            }
            if (CalendarView.this.list3 != null && CalendarView.this.list3.contains(day + " " + month)) {
                tv.setBackgroundColor(CalendarView.this.getResources().getColor(R.color.ash));
                Log.e("List3 in calen", CalendarView.this.list3 + "");
            }
            tv.setText(String.valueOf(date.getDate()));
            return view;
        }
    }

    public interface EventHandler {
        void onDayPress(Date date);
    }

    public CalendarView(Context context) {
        super(context);
    }

    public CalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initControl(context, attrs);
    }

    public CalendarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initControl(context, attrs);
    }

    private void initControl(Context context, AttributeSet attrs) {
        ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.calendarcontrol, this);
        loadDateFormat(attrs);
        assignUiElements();
        assignClickHandlers();
        updateCalendar();
    }

    private void loadDateFormat(AttributeSet attrs) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.ActionBar);
        try {
            this.dateFormat = ta.getString(0);
            if (this.dateFormat == null) {
                this.dateFormat = DATE_FORMAT;
            }
            ta.recycle();
        } catch (Throwable th) {
            ta.recycle();
        }
    }

    private void assignUiElements() {
        this.header = (LinearLayout) findViewById(R.id.calendar_header);
        this.leftarrow = (ImageView) findViewById(R.id.calendar_previous_button);
        this.rightarrow = (ImageView) findViewById(R.id.calendar_next_button);
        this.txtDate = (TextView) findViewById(R.id.calendar_date_display);
        this.monthUnder = findViewById(R.id.month_underline);
        this.grid = (GridView) findViewById(R.id.calendar_grid);
    }

    private void assignClickHandlers() {
        this.txtDate.setOnTouchListener(new OnSwipeTouchListener2(getContext()) {
            public void onSwipeRight() {
                CalendarView.this.currentDate.add(2, -1);
                CalendarView.this.updateCalendar();
            }

            public void onSwipeLeft() {
                CalendarView.this.currentDate.add(2, 1);
                Log.e("Day", CalendarView.this.currentDate + "");
                CalendarView.this.updateCalendar();
            }
        });
        this.rightarrow.setOnClickListener(new C02212());
        this.leftarrow.setOnClickListener(new C02223());
        this.grid.setOnItemClickListener(new C02234());
    }

    public void updateCalendar() {
        updateCalendar(null);
    }

    public void updateCalendar(Date events) {
        ArrayList<Date> cells = new ArrayList();
        Calendar calendar = (Calendar) this.currentDate.clone();
        Calendar calendar1 = (Calendar) this.currentDate.clone();
        calendar.set(5, 1);
        calendar1.set(5, 1);
        calendar.add(5, -(calendar.get(7) - 1));
        while (cells.size() < 42) {
            SimpleDateFormat sdf1 = new SimpleDateFormat(this.dateFormat);
            if (sdf1.format(calendar.getTime()).equals(sdf1.format(calendar1.getTime()))) {
                cells.add(calendar.getTime());
            } else {
                try {
                    String str;
                    Date date1 = new SimpleDateFormat(" ").parse(" ");
                    PrintStream printStream = System.out;
                    if (date1 == null) {
                        str = " ";
                    } else {
                        str = DateFormat.getDateTimeInstance().format(date1);
                    }
                    printStream.println(str);
                    cells.add(date1);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            calendar.add(5, 1);
        }
        this.grid.setAdapter(new CalendarAdapter(getContext(), cells, events));
        displaymonth(new SimpleDateFormat(this.dateFormat).format(this.currentDate.getTime()));
    }

    public void updateCalendar1(ArrayList lis) {
        this.list = lis;
    }

    public void updateCalendar2(ArrayList lis) {
        this.list1 = lis;
    }

    public void updateCalendar3(ArrayList lis) {
        this.list2 = lis;
    }

    public void updateCalendar4(ArrayList lis) {
        this.list3 = lis;
    }

    public void setEventHandler(EventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    private void displaymonth(String str_image_month) {
        if (str_image_month.equals("Jan")) {
            this.txtDate.setText("JANUARY");
            this.monthUnder.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        } else if (str_image_month.equals("Feb")) {
            this.txtDate.setText("FEBURARY");
            this.monthUnder.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        } else if (str_image_month.equals("Mar")) {
            this.txtDate.setText("MARCH");
            this.monthUnder.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        } else if (str_image_month.equals("Apr")) {
            this.txtDate.setText("APRIL");
            this.monthUnder.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        } else if (str_image_month.equals("May")) {
            this.txtDate.setText("MAY");
            this.monthUnder.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        } else if (str_image_month.equals("Jun")) {
            this.txtDate.setText("JUNE");
            this.monthUnder.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        } else if (str_image_month.equals("Jul")) {
            this.txtDate.setText("JULY");
            this.monthUnder.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        } else if (str_image_month.equals("Aug")) {
            this.txtDate.setText("AUGUST");
            this.monthUnder.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        } else if (str_image_month.equals("Sep")) {
            this.txtDate.setText("SEPTEMBER");
            this.monthUnder.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        } else if (str_image_month.equals("Oct")) {
            this.txtDate.setText("OCTOBER");
            this.monthUnder.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        } else if (str_image_month.equals("Nov")) {
            this.txtDate.setText("NOVEMBER");
            this.monthUnder.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        } else if (str_image_month.equals("Dec")) {
            this.txtDate.setText("DECEMBER");
            this.monthUnder.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        }
    }
}
