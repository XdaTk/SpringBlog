package com.leftcoding.blog.utils;

import com.domingosuarez.boot.autoconfigure.jade4j.JadeHelper;
import com.leftcoding.blog.setting.AppSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by XdaTk on 16/5/22.
 */
@Service
@JadeHelper("viewHelper")
public class ViewHelper {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MMMM dd, yyyy");
    private static final SimpleDateFormat DATE_FORMAT_MONTH_DAY = new SimpleDateFormat("MMM dd");

    private AppSetting appSetting;

    private String applicationEnv;

    @Autowired
    public ViewHelper(AppSetting appSetting){
        this.appSetting = appSetting;
    }

    private long startTime;

    public long getResponseTime(){
        return System.currentTimeMillis() - startTime;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public String getFormattedDate(Date date){
        return date == null ? "" : DATE_FORMAT.format(date);
    }

    public String getMonthAndDay(Date date){
        return date == null ? "" : DATE_FORMAT_MONTH_DAY.format(date);
    }

    public String metaTitle(String title){
        return title + " · " + appSetting.getSiteName();
    }

    public String getApplicationEnv() {
        return applicationEnv;
    }

    public void setApplicationEnv(String applicationEnv) {
        this.applicationEnv = applicationEnv;
    }
}