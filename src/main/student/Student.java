package main.student;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import main.exceptions.InvalidDOBException;
import main.exceptions.InvalidFullNameException;
import main.exceptions.InvalidPhoneNumberException;

public abstract class Student {
    protected String fullName;
    protected LocalDate doB;
    protected String sex;
    protected String phoneNumber;
    protected String universityName;
    protected String gradeLevel;

    public static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Student(String fullName, String doBString, String sex, String phoneNumber, String universityName,
            String gradeLevel) throws InvalidFullNameException, InvalidDOBException, InvalidPhoneNumberException {

        setFullName(fullName);

        setDoB(doBString);

        setSex(sex);

        setPhoneNumber(phoneNumber);

        setUniversityName(universityName);

        setGradeLevel(gradeLevel);
    }

    public abstract void showInfo();

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) throws InvalidFullNameException {
        if (fullName == null || fullName.length() < 10 || fullName.length() > 50) {
            throw new InvalidFullNameException("Full name must be between 10 and 50 characters.");
        }
        this.fullName = fullName.trim();
    }

    public LocalDate getDoB() {
        return doB;
    }

    public String getDoBString() {
        return doB.format(DTF);
    }

    public void setDoB(LocalDate doB) {
        this.doB = doB;
    }

    public void setDoB(String doBString) throws InvalidDOBException {
        try {
            this.doB = LocalDate.parse(doBString.trim(), DTF);
        } catch (DateTimeParseException e) {
            throw new InvalidDOBException(e.getMessage());
        }
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) throws InvalidPhoneNumberException {
        if (!phoneNumber.matches("\\d{10}")) {
            throw new InvalidPhoneNumberException("Phone number must have 10 digits.");
        }
        String prefix = phoneNumber.substring(0, 3);
        if (!InvalidPhoneNumberException.validPrefixes.contains(prefix)) {
            throw new InvalidPhoneNumberException("Phone must start with valid prefixes.");
        }
        this.phoneNumber = phoneNumber;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public String getGradeLevel() {
        return gradeLevel;
    }

    public void setGradeLevel(String gradeLevel) {
        this.gradeLevel = gradeLevel;
    }
}
