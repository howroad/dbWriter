package com.nstc.temp.model;
import java.util.Date;
/** 测试学生表 */
public class Student {
    /** 学生表主键 */
    private Integer studentId;
    /** 学生姓名 */
    private String studentName;
    /** 学生年龄 */
    private Integer studentAge;
    /** 学生生日 */
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
