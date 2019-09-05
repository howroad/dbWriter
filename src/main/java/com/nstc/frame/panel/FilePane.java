package com.nstc.frame.panel;

import java.awt.Dimension;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * <p>
 * Title: MyPanel.java
 * </p>
 *
 * <p>
 * Description:
 * </p>
 *
 * <p>
 * Company: 北京九恒星科技股份有限公司
 * </p>
 *
 * @author luhao
 * 
 * @since：2019年3月6日 下午1:53:29
 * 
 */
public class FilePane extends JPanel {
    private static final long serialVersionUID = -3947317443678310621L;
    private JButton button;
    private JTextField text;

    public FilePane(String lable,int size) {
        super();
        button = new JButton(lable);
        text = new JTextField();
        button.setPreferredSize(new Dimension(2 * size, 20));
        text.setPreferredSize(new Dimension(10 * size, 20));
        this.add(button);
        this.add(text);
        // java8 later
        this.button.addActionListener((e)->{
            JFileChooser jfc=new JFileChooser();  
            jfc.setFileSelectionMode(JFileChooser.FILES_ONLY );  
            jfc.showDialog(new JLabel(), "选择文件");  
            File file=jfc.getSelectedFile();  
            text.setText(file.getAbsolutePath().replace("\\", "/"));
        });
    }

    public String getText() {
        return text.getText().trim();
    }
    public void setText(String string) {
        text.setText(string);
    }
}
