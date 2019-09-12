package com.nstc.frame.panel;

import com.nstc.frame.jframe.ShowFrame;

import javax.swing.*;
import java.awt.*;

/**
 * <p>Title: JoinSqlLine.java</p>
 * <p>Description: </p>
 * <p>Company: 北京九恒星科技股份有限公司</p>
 *
 * @author luhao
 * @since：2019-09-12 11:06
 */
public class JoinSqlLine extends JPanel{

    private TextPane_Half tb1;
    private SelectPanel sel;
    private TextPane_Half tb2;
    private JLabel on = new JLabel("on");
    private TextPane_Half where;

    public JoinSqlLine(boolean enabled) {
        this.tb1 = new TextPane_Half(ShowFrame.TEXT_LENGTH/2);
        this.sel = new SelectPanel(null, ShowFrame.TEXT_LENGTH/2, "LEFT JOIN ", "INNER JOIN ");
        this.tb2 = new TextPane_Half(ShowFrame.TEXT_LENGTH/2);;
        this.on = new JLabel("ON");
        this.where = new TextPane_Half(ShowFrame.TEXT_LENGTH);
        this.setLayout(new FlowLayout());
        this.add(tb1);
        this.add(sel);
        this.add(tb2);
        this.add(on);
        this.add(where);
        makeEnabled(enabled);
    }

    void makeEnabled(boolean enabled){
        this.tb1.makeEnabled(enabled);
    }

    public TextPane_Half getTb1() {
        return tb1;
    }

    public void setTb1(TextPane_Half tb1) {
        this.tb1 = tb1;
    }

    public SelectPanel getSel() {
        return sel;
    }

    public void setSel(SelectPanel sel) {
        this.sel = sel;
    }

    public TextPane_Half getTb2() {
        return tb2;
    }

    public void setTb2(TextPane_Half tb2) {
        this.tb2 = tb2;
    }

    public JLabel getOn() {
        return on;
    }

    public void setOn(JLabel on) {
        this.on = on;
    }

    public TextPane_Half getWhere() {
        return where;
    }

    public void setWhere(TextPane_Half where) {
        this.where = where;
    }
}
