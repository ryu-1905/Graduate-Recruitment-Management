package main;

import main.student.Student;

public class App {
    public static void main(String[] args) {
        StudentRecruitment studentRecruitment = new StudentRecruitment();
        studentRecruitment.showListStudent();

        System.out.println("\n===== Danh sách ứng viên được chọn =====");
        studentRecruitment.selectCandidates().forEach(Student::showInfo);

        studentRecruitment.showAllStudentsWithNameAndPhone();
    }
}
