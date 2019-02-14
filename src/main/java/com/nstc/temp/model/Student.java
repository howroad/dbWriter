package com.nstc.temp.model;
import java.util.Date;
/** ����ѧ���� */
public class Student {
    /** ѧ�������� */
    private Integer studentId;
    /** ѧ������ */
    private String studentName;
    /** ѧ������ */
    private Integer studentAge;
    /** ѧ������ */
    private Date studentBirth;

    public void setStudentId(Integer studentId){
        this.studentId = studentId;
    }

    public Integer getStudentId(){
        return this.studentId;
    }

    public void setStudentName(String studentName){
        this.studentName = studentName;
    }

    public String getStudentName(){
        return this.studentName;
    }

    public void setStudentAge(Integer studentAge){
        this.studentAge = studentAge;
    }

    public Integer getStudentAge(){
        return this.studentAge;
    }

    public void setStudentBirth(Date studentBirth){
        this.studentBirth = studentBirth;
    }

    public Date getStudentBirth(){
        return this.studentBirth;
    }

}
