package domain;

import java.sql.Timestamp;

public class Patient {
    private int id;
    private String name;
    private Timestamp birthday;
    private Gender gender;
    private String departmentName;

    public Patient(String name, Timestamp birthday, Gender gender) {
        this.id = -1;
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.departmentName = null;
    }

    public Patient(int id, String name, Timestamp birthday, Gender gender, String departmentName) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.departmentName = departmentName;
    }

    public Patient(String name, Timestamp birthday, Gender sex, String departmentName) {
        this.id = -1;
        this.name = name;
        this.birthday = birthday;
        this.gender = sex;
        this.departmentName = departmentName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getBirthday() {
        return birthday;
    }

    public void setBirthday(Timestamp birthday) {
        this.birthday = birthday;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public String toString() {
        return id + ". "
                 + name + '\'' +
                ", год рождения: " + birthday +
                ", пол=" + gender +
                ", departmentId=" + departmentName +
                "\n";
    }
}
