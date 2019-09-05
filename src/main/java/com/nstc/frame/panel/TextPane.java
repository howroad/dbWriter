package com.nstc.frame.panel;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
public class TextPane extends JPanel{
    private static final long serialVersionUID = -3947317443678310621L;
    private JLabel label;
    private JTextField text;
    public TextPane(String lable,int size) {
        super();
        label = new JLabel(lable);
        text = new JTextField();
        label.setPreferredSize(new Dimension(2 * size, 20));
        text.setPreferredSize(new Dimension(10 * size, 20));
        this.add(label);
        this.add(text);
    }
    public String getText() {
        return text.getText().trim();
    }
    public void setText(String string) {
        text.setText(string);
    }

}
