package com.nstc.frame.jframe;

import static com.nstc.dbwriter.config.CommonSettings.stringToArray;
import static com.nstc.dbwriter.config.TempSettings.writeProperties;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.nstc.frame.panel.FilePane;
import com.nstc.frame.panel.LogPanel;
import com.nstc.frame.panel.SelectPanel;
import com.nstc.frame.panel.TextPane;
import com.nstc.log.PanelLog;
import org.apache.commons.lang3.Validate;

import com.nstc.dbwriter.config.CommonSettings;
import com.nstc.dbwriter.config.InnerSettings;
import com.nstc.dbwriter.config.TempSettings;
import com.nstc.dbwriter.control.ClearTemp;
import com.nstc.dbwriter.control.Start;
import com.nstc.dbwriter.util.ValidateUtil;
import com.nstc.dbwriter.util.WriteUtil;


/**
 * <p>Title: ShowFrame.java</p>
 *
 * <p>Description: </p>
 *
 * @author luhao
 * 
 * @since：2019年3月5日 下午2:37:58
 * 
 */
public class ShowFrame extends JFrame {
    
    private static final long serialVersionUID = -4760857055691612569L;
    private static final int TEXT_LENGTH = 20;
    private static final String SEQ_DIR_0 = "前置";

    private JPanel contentPanel = new JPanel(new GridLayout(1,2));

    /** 内容，后两位参数是间距 */
    private JPanel settingPanel = new JPanel(new GridLayout(12, 2, 1, 1));

    private LogPanel logPanel = new LogPanel();

    private FilePane filePanel = new FilePane("...", TEXT_LENGTH);
    private TextPane outerDirPanel = new TextPane("outer:", TEXT_LENGTH);
    private TextPane uRLPanel = new TextPane("url:", TEXT_LENGTH);
    private TextPane userNamePanel = new TextPane("user:", TEXT_LENGTH);
    private TextPane passwordPanel = new TextPane("pwd:", TEXT_LENGTH);
    private SelectPanel fromExcelPanel = new SelectPanel("frmExl", TEXT_LENGTH, "false", "true");
    private SelectPanel fromDatebasePanel = new SelectPanel("frmDb", TEXT_LENGTH, "false", "true");
    private TextPane appNoPanel = new TextPane("appNo:", TEXT_LENGTH);
    private TextPane tablesPanel = new TextPane("tbls", TEXT_LENGTH);
    private SelectPanel seqDirPanel = new SelectPanel("seqDr:", TEXT_LENGTH, "前置", "后置");
    private JPanel btnPanel = new JPanel();
    
    private JPanel custPanel = new JPanel();

    private JButton conBtn = new JButton("conn");
    private JButton runBtn = new JButton("run");
    private JButton clearBtn = new JButton("clear");
    private JButton testBtn = new JButton("test");
    
    private JButton custBtn1 = new JButton("导出SQL脚本");
    private JButton logBtn = new JButton("log");
    
    public ShowFrame() {

        PanelLog.initLog(this.logPanel);
        
        this.setBounds(0, 0, 280, 350);
        // 无相对位置
        this.setLocationRelativeTo(null);
        // 不可设置大小
        this.setResizable(false);
        // 关闭样式
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        btnPanel.add(conBtn);
        btnPanel.add(runBtn);
        btnPanel.add(clearBtn);
        btnPanel.add(testBtn);
        
        custPanel.add(custBtn1);
        custPanel.add(logBtn);
        
        this.settingPanel.add(filePanel);
        this.settingPanel.add(outerDirPanel);
        this.settingPanel.add(uRLPanel);
        this.settingPanel.add(userNamePanel);
        this.settingPanel.add(passwordPanel);
        this.settingPanel.add(appNoPanel);
        this.settingPanel.add(seqDirPanel);
        this.settingPanel.add(tablesPanel);
        this.settingPanel.add(fromExcelPanel);
        this.settingPanel.add(fromDatebasePanel);
        this.settingPanel.add(btnPanel);
        this.settingPanel.add(custPanel);

        this.contentPanel.add(settingPanel);
        this.contentPanel.add(logPanel);

        this.setTitle("dbWriter");
        this.setContentPane(contentPanel);
        pack();
        init();

    }
    
    private void init() {
        addListener();
        putDefaultValue();
        hideLog();
    }
    
    private void putDefaultValue() {
        if(TempSettings.hasConfig) {
            filePanel.setText(TempSettings.EXCEL_PATH);
            outerDirPanel.setText(TempSettings.OUT_DIR);
            uRLPanel.setText(TempSettings.URL.replace("jdbc:oracle:thin:@", ""));
            userNamePanel.setText(TempSettings.USER);
            passwordPanel.setText(TempSettings.PASSWORD);
            appNoPanel.setText(TempSettings.appNo);
            tablesPanel.setText(TempSettings.TABLES);
        }else {
            filePanel.setText(CommonSettings.EXCEL_PATH);
            outerDirPanel.setText(InnerSettings.OUT_DIR);
            uRLPanel.setText(CommonSettings.URL.replace("jdbc:oracle:thin:@", ""));
            userNamePanel.setText(CommonSettings.USER);
            passwordPanel.setText(CommonSettings.PASSWORD);
            appNoPanel.setText(CommonSettings.appNo);
            tablesPanel.setText("WF_MASTER_USER");
        }
    }
    
    private void addListener() {
        conBtn.addActionListener((e) -> {
            putValue();
            try {
                WriteUtil.getDataBySQL("SELECT 1 FROM DUAL");
                JOptionPane.showMessageDialog(null, "连接成功");
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage());
            }
        });
        
        runBtn.addActionListener((e) -> {
            putValue();
            try {
                if(!TempSettings.fromDatebase && !TempSettings.fromExcel) {
                    JOptionPane.showMessageDialog(null, "请选择一个生成类型！");
                    return;
                }
                Start.start();
                JOptionPane.showMessageDialog(null, "生成成功！");
                writeProperties();
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage());
                e1.printStackTrace();
            }
        });
        clearBtn.addActionListener((e) -> {
            putValue();
            try {
//                ClearTemp.clearAndRebuild();
                ClearTemp.clear();
                JOptionPane.showMessageDialog(null, "清除成功");
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage());
                e1.printStackTrace();
            }
        });
        testBtn.setEnabled(false);
        
        //自定义事件
        custBtn1.addActionListener(e ->{
            putValue();
            try {
                //Start.ta0723BuildSql();
                Start.N0801SQL();
                JOptionPane.showMessageDialog(null, "生成成功");
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage());
                e1.printStackTrace();
            }            
        });

        logBtn.addActionListener(e -> {
            showOrHideLog();
        });
    }
    
    private void putValue() {
        
        TempSettings.EXCEL_PATH = filePanel.getText();
        TempSettings.OUT_DIR = outerDirPanel.getText();
        TempSettings.URL = "jdbc:oracle:thin:@" + uRLPanel.getText();
        TempSettings.USER = userNamePanel.getText();
        TempSettings.PASSWORD = passwordPanel.getText();
        TempSettings.appNo = appNoPanel.getText();
        TempSettings.fromExcel = "true".equals(fromExcelPanel.getText());
        TempSettings.fromDatebase = "true".equals(fromDatebasePanel.getText());
        TempSettings.SEQ_DIR = seqDirPanel.getText();
        TempSettings.TABLES = tablesPanel.getText().replaceAll("\\s", "");

        
        try {
            validateValue();
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "不能为空！");
            throw new RuntimeException();
        }
        CommonSettings.EXCEL_PATH = TempSettings.EXCEL_PATH;
        InnerSettings.OUT_DIR = TempSettings.OUT_DIR;
        CommonSettings.URL = TempSettings.URL;
        CommonSettings.USER = TempSettings.USER.toUpperCase();
        CommonSettings.PASSWORD = TempSettings.PASSWORD.toUpperCase();
        CommonSettings.appNo = TempSettings.appNo;
        CommonSettings.fromExcel = TempSettings.fromExcel;
        CommonSettings.fromDatebase = TempSettings.fromDatebase;
        CommonSettings.tablesFromDB = stringToArray(TempSettings.TABLES);
        
        if(SEQ_DIR_0.equals(TempSettings.SEQ_DIR)) {
            CommonSettings.SEQ_DIR = 0;
        }else {
            CommonSettings.SEQ_DIR = 1;
        }
        
        CommonSettings.initMap();
    }
    
    public void validateValue() {
        
        ValidateUtil.checkVersion();
        
        Validate.notEmpty(TempSettings.OUT_DIR);
        Validate.notEmpty(TempSettings.URL);
        Validate.notEmpty(TempSettings.USER);
        Validate.notEmpty(TempSettings.PASSWORD);
        Validate.notEmpty(TempSettings.appNo);
        Validate.notEmpty(TempSettings.TABLES);
        
    }

    public void showOrHideLog(){
        if(logPanel.isVisible()){
            hideLog();
        }else{
            showLog();
        }
    }
    public void hideLog(){
        logPanel.setVisible(false);
        contentPanel.remove(logPanel);
        pack();
    }
    public void showLog(){
        logPanel.setVisible(true);
        contentPanel.add(logPanel);
        pack();
    }
    
    
}
