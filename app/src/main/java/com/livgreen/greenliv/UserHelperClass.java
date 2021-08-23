package com.livgreen.greenliv;

public class UserHelperClass {

    String fname,lname,email,age,gender,qualification,profession,purpose,password,userid,university,institute,pincode;

    public UserHelperClass() {

    }


    public UserHelperClass(String fname, String lname, String email, String age, String gender, String qualification, String profession, String purpose, String password, String userid, String university, String institute, String pincode) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.age = age;
        this.gender = gender;
        this.qualification = qualification;
        this.profession = profession;
        this.purpose = purpose;
        this.password = password;
        this.userid = userid;
        this.university = university;
        this.institute = institute;
        this.pincode = pincode;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
