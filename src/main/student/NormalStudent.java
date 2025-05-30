package main.student;

import main.exceptions.InvalidDOBException;
import main.exceptions.InvalidFullNameException;
import main.exceptions.InvalidPhoneNumberException;

public class NormalStudent extends Student {
    private int englishScore;
    private double entryTestScore;

    public NormalStudent(String fullName, String doBString, String sex, String phoneNumber,
            String universityName, String gradeLevel,
            int englishScore, double entryTestScore)
            throws InvalidFullNameException, InvalidDOBException, InvalidPhoneNumberException {
        super(fullName, doBString, sex, phoneNumber, universityName, gradeLevel);
        setEnglishScore(englishScore);
        setEntryTestScore(entryTestScore);
    }

    @Override
    public void showInfo() {
        System.out.println("""
                ---- NormalStudent ----
                Full Name       : %s
                DOB             : %s
                Sex             : %s
                Phone Number    : %s
                University Name : %s
                Grade Level     : %s
                TOEIC Score     : %d
                Entry Test Score: %.2f
                ------------------------
                """.formatted(
                getFullName(),
                getDoBString(),
                getSex(),
                getPhoneNumber(),
                getUniversityName(),
                getGradeLevel(),
                getEnglishScore(),
                getEntryTestScore()));
    }

    public int getEnglishScore() {
        return englishScore;
    }

    public void setEnglishScore(int englishScore) {
        this.englishScore = englishScore;
    }

    public double getEntryTestScore() {
        return entryTestScore;
    }

    public void setEntryTestScore(double entryTestScore) {
        if (entryTestScore < 0.0 || entryTestScore > 10.0) {
            throw new IllegalArgumentException(
                    "Entry test score must be between 0.0 and 10.0. -> " + entryTestScore);
        }
        this.entryTestScore = entryTestScore;
    }
}
