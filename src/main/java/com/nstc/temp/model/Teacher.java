package com.nstc.temp.model;
import java.util.Date;
/** 测试教师表 */
public class Teacher {
    /** 教师主键 */
    private Integer teacherId;
    /** 教师姓名 */
    private String teacherName;
    /** 教师年龄 */
    private Integer teacherAge;
    /** 教师生日 */
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
