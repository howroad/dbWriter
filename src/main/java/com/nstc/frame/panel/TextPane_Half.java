package com.nstc.frame.panel;

import javax.swing.*;
import java.awt.*;

/**
 * <p>Title: MyPanel.java</p>
 *
 * <p>Description: </p>
 *
 * <p>Company: 北京九恒星科技股份有限公司</p>
 *
 * @author luhao
 * 
 * @since：2019年3月6日 下午1:53:29
 * 
 */
public class TextPane_Half extends JPanel{
    private static final long serialVersionUID = -3947317443678310621L;
    private JTextField text;
    public TextPane_Half(int size) {
        super();
        text = new JTextField();
        text.setPreferredSize(new Dimension(10 * size, 20));
        this.add(text);
    }
    public String getText() {
        return text.getText().trim();
    }
    public void setText(String string) {
        text.setText(string);
    }

    public void makeEnabled(boolean flag){
        this.text.setEditable(flag);
    }
}
