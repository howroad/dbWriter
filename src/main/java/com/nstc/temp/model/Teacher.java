package com.nstc.temp.model;
import java.util.Date;
/** ���Խ�ʦ�� */
public class Teacher {
    /** ��ʦ���� */
    private Integer teacherId;
    /** ��ʦ���� */
    private String teacherName;
    /** ��ʦ���� */
    private Integer teacherAge;
    /** ��ʦ���� */
    private Date teacherBirth;

    public void setTeacherId(Integer teacherId){
        this.teacherId = teacherId;
    }

    public Integer getTeacherId(){
        return this.teacherId;
    }

    public void setTeacherName(String teacherName){
        this.teacherName = teacherName;
    }

    public String getTeacherName(){
        return this.teacherName;
    }

    public void setTeacherAge(Integer teacherAge){
        this.teacherAge = teacherAge;
    }

    public Integer getTeacherAge(){
        return this.teacherAge;
    }

    public void setTeacherBirth(Date teacherBirth){
        this.teacherBirth = teacherBirth;
    }

    public Date getTeacherBirth(){
        return this.teacherBirth;
    }

}
