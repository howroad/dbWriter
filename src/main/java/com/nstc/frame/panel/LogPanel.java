package com.nstc.frame.panel;

import com.nstc.dbwriter.config.CommonSettings;
import com.nstc.dbwriter.config.InnerSettings;
import com.nstc.dbwriter.config.TempSettings;
import com.nstc.dbwriter.control.ClearTemp;
import com.nstc.dbwriter.control.Start;
import com.nstc.dbwriter.util.ValidateUtil;
import com.nstc.dbwriter.util.WriteUtil;
import com.nstc.frame.panel.FilePane;
import com.nstc.frame.panel.SelectPanel;
import com.nstc.frame.panel.TextPane;
import org.apache.commons.lang3.Validate;

import javax.swing.*;
import java.awt.*;

import static com.nstc.dbwriter.config.CommonSettings.stringToArray;
import static com.nstc.dbwriter.config.TempSettings.writeProperties;


/**
 * <p>Title: LogFrame.java</p>
 *
 * <p>Description: </p>
 *
 * @author luhao
 * @since：20190904
 */
public class LogPanel extends JPanel {

    private static final long serialVersionUID = 7178981797120622698L;

    private StringBuffer stringBuffer = new StringBuffer();

    private JTextArea jTextArea = new JTextArea(null,"[log]",22,20);

    private JScrollPane jScrollPane = new JScrollPane(jTextArea);


    public LogPanel() {
        //分别设置水平和垂直滚动条自动出现
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        //分别设置水平和垂直滚动条总是出现
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        //jTextArea.setEditable(false);

        this.add(jScrollPane);
    }

    public void log(String log){
        stringBuffer.append(log + "\r\n");
        jTextArea.setText(stringBuffer.toString());
    }

}
