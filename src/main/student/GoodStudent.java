package main.student;

import main.exceptions.InvalidDOBException;
import main.exceptions.InvalidFullNameException;
import main.exceptions.InvalidPhoneNumberException;

public class GoodStudent extends Student {
    private double gpa;
    private String bestRewardName;

    public GoodStudent(String fullName, String doBString, String sex, String phoneNumber,
            String universityName, String gradeLevel,
            double gpa, String bestRewardName)
            throws InvalidFullNameException, InvalidDOBException, InvalidPhoneNumberException {
        super(fullName, doBString, sex, phoneNumber, universityName, gradeLevel);
        setGpa(gpa);
        setBestRewardName(bestRewardName);
    }

    @Override
    public void showInfo() {
        System.out.println("""
                ----- GoodStudent -----
                Full Name       : %s
                DOB             : %s
                Sex             : %s
                Phone Number    : %s
                University Name : %s
                Grade Level     : %s
                GPA             : %.2f
                Best Reward     : %s
                ------------------------
                """.formatted(
                getFullName(),
                getDoBString(),
                getSex(),
                getPhoneNumber(),
                getUniversityName(),
                getGradeLevel(),
                getGpa(),
                getBestRewardName()));
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        if (gpa < 0.0 || gpa > 10.0) {
            throw new IllegalArgumentException("Input files have unknow errors!");
        }
        this.gpa = gpa;
    }

    public String getBestRewardName() {
        return bestRewardName;
    }

    public void setBestRewardName(String bestRewardName) {
        this.bestRewardName = bestRewardName;
    }
}
