package com.nstc.log;

import com.nstc.frame.panel.LogPanel;

public class PanelLog {

    private static LogPanel logPanel;

    private PanelLog(){}

    public static LogPanel getLog(){
        if(logPanel == null){
            throw new RuntimeException("无法获取日志信息！");
        }else{
            return logPanel;
        }
    }

    public static void initLog(LogPanel logPanel0){
        if(logPanel == null){
        logPanel = logPanel0;
        }
    }

    public static void log(Object log0){
        logPanel.log(log0 == null ? null : log0.toString());
    }
}
