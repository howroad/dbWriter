package com.nstc.frame;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.apache.commons.lang3.Validate;

import static com.nstc.dbwriter.config.CommonSettings.stringToArray;

import com.nstc.dbwriter.builder.TableBuilder;
import com.nstc.dbwriter.config.CommonSettings;
import com.nstc.dbwriter.config.InnerSettings;
import com.nstc.dbwriter.control.ClearTemp;
import com.nstc.dbwriter.control.Start;
import com.nstc.dbwriter.util.ValidateUtil;


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
    
    /** 内容，后两位参数是间距 */
    private JPanel contentPanel = new JPanel(new GridLayout(11, 2, 1, 1));

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

    private JButton conBtn = new JButton("conn");
    private JButton runBtn = new JButton("run");
    private JButton clearBtn = new JButton("clear");
    private JButton testBtn = new JButton("test");
    
    private String excelPath;
    private String outerPath;
    private String url;
    private String username;
    private String password;
    private String appNo;
    private String tables;
    private boolean fromExcel;
    private boolean fromDatebase;
    private String seqDir;
    
    public ShowFrame() {
        
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
        
        this.contentPanel.add(filePanel);
        this.contentPanel.add(outerDirPanel);
        this.contentPanel.add(uRLPanel);
        this.contentPanel.add(userNamePanel);
        this.contentPanel.add(passwordPanel);
        this.contentPanel.add(appNoPanel);
        this.contentPanel.add(seqDirPanel);
        this.contentPanel.add(tablesPanel);
        this.contentPanel.add(fromExcelPanel);
        this.contentPanel.add(fromDatebasePanel);
        this.contentPanel.add(btnPanel);

        this.setTitle("dbWriter");
        this.setContentPane(contentPanel);
        pack();
        init();
    }
    
    private void init() {
        addListener();
        putDefaultValue();
        
    }
    
    private void putDefaultValue() {
        filePanel.setText("C:/Users/Administrator/Desktop/model/1.xlsx");
        outerDirPanel.setText("C:/Users/Administrator/Desktop/model/");
        uRLPanel.setText("192.168.20.33:1521:nstestsid");
        userNamePanel.setText("HAIMA_FSS20180831");
        passwordPanel.setText("123456");
        appNoPanel.setText("temp");
        tablesPanel.setText("bmm_warehouse_bill");
    }
    
    private void addListener() {
        conBtn.addActionListener((e) -> {
            try {
                TableBuilder.buildTableFromDB("wf_master_user");
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage());
            }
        });
        
        runBtn.addActionListener((e) -> {
            putValue();
            try {
                Start.start();
                JOptionPane.showMessageDialog(null, "生成成功！");
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage());
                e1.printStackTrace();
            }
        });
        clearBtn.addActionListener((e) -> {
            putValue();
            try {
                ClearTemp.clear();
                JOptionPane.showMessageDialog(null, "清除成功");
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage());
                e1.printStackTrace();
            }
        });
        testBtn.setEnabled(false);
    }
    
    private void putValue() {
        
        excelPath = filePanel.getText();
        outerPath = outerDirPanel.getText();
        url = "jdbc:oracle:thin:@" + uRLPanel.getText();
        username = userNamePanel.getText();
        password = passwordPanel.getText();
        appNo = appNoPanel.getText();
        fromExcel = "true".equals(fromExcelPanel.getText());
        fromDatebase = "true".equals(fromDatebasePanel.getText());
        seqDir = seqDirPanel.getText();
        tables = tablesPanel.getText().replaceAll("\\s", "");

        
        try {
            validateValue();
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "不能为空！");
            throw new RuntimeException();
        }
        CommonSettings.EXCEL_PATH = excelPath;
        InnerSettings.OUT_DIR = outerPath;
        CommonSettings.URL = url;
        CommonSettings.USER = username;
        CommonSettings.PASSWORD = password;
        CommonSettings.appNo = appNo;
        CommonSettings.fromExcel = fromExcel;
        CommonSettings.fromDatebase = fromDatebase;
        CommonSettings.tablesFromDB = stringToArray(tables);
        
        if(SEQ_DIR_0.equals(seqDir)) {
            CommonSettings.SEQ_DIR = 0;
        }else {
            CommonSettings.SEQ_DIR = 1;
        }
    }
    
    public void validateValue() {
        
        ValidateUtil.checkVersion();
        
        Validate.notEmpty(outerPath);
        Validate.notEmpty(url);
        Validate.notEmpty(username);
        Validate.notEmpty(password);
        Validate.notEmpty(appNo);
        Validate.notEmpty(tables);
        
    }
    
    
    
}
