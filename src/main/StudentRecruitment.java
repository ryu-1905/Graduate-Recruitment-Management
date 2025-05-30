package main;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import main.student.GoodStudent;
import main.student.NormalStudent;
import main.student.Student;

public class StudentRecruitment {
    private List<Student> allStudents = new ArrayList<>();
    private List<GoodStudent> goodStudents = new ArrayList<>();
    private List<NormalStudent> normalStudents = new ArrayList<>();
    private StudentFileReader studentFileReader = new StudentFileReader();

    public void showListStudent() {
        try {
            System.out.println("\n===== Danh sách GoodStudent =====");
            goodStudents = studentFileReader.readGoodStudents("goodStudent.csv");
            goodStudents.forEach(GoodStudent::showInfo);

            System.out.println("\n===== Danh sách NormalStudent =====");
            normalStudents = studentFileReader.readNormalStudents("normalStudent.csv");
            normalStudents.forEach(NormalStudent::showInfo);

        } catch (Exception e) {
            System.err.println("Input files have unknow errors!");
        }
    }

    public List<Student> selectCandidates() {
        List<Student> selectedList = new ArrayList<>();

        try (Scanner scanner = new Scanner(System.in)) {
            goodStudents = studentFileReader.readGoodStudents("goodStudent.csv");
            normalStudents = studentFileReader.readNormalStudents("normalStudent.csv");

            int number;

            while (true) {
                System.out.print("Number of candidates to select (11-15): ");
                try {
                    number = scanner.nextInt();
                    if (number >= 11 && number <= 15) {
                        break;
                    }
                } catch (Exception e) {
                    scanner.nextLine();
                }
            }

            // If goodStudents enough or more, select from goodStudents
            if (goodStudents.size() >= number) {
                ArrayList<GoodStudent> tempGood = new ArrayList<>(goodStudents);
                tempGood.sort(Comparator
                        .comparingDouble(GoodStudent::getGpa).reversed()
                        .thenComparing(GoodStudent::getFullName, String.CASE_INSENSITIVE_ORDER));

                selectedList.addAll(tempGood.subList(0, number));
                return selectedList;
            }

            // If goodStudents not enough, select all goodStudents and fill with
            // normalStudents
            selectedList.addAll(goodStudents);

            int remaining = number - selectedList.size();

            List<NormalStudent> tempNormal = new ArrayList<>(normalStudents);
            tempNormal.sort(Comparator
                    .comparingDouble(NormalStudent::getEntryTestScore).reversed()
                    .thenComparing(Comparator.comparingInt(NormalStudent::getEnglishScore).reversed())
                    .thenComparing(NormalStudent::getFullName, String.CASE_INSENSITIVE_ORDER));

            // fill remaining candidates from normalStudents
            tempNormal.stream()
                    .limit(remaining)
                    .forEach(selectedList::add);

        } catch (Exception e) {
            System.err.println("Input files have unknow errors!");
        }
        return selectedList;
    }

    public void showAllStudentsWithNameAndPhone() {
        System.out.println("===== Danh sách tất cả sinh viên =====");

        try {
            goodStudents = studentFileReader.readGoodStudents("goodStudent.csv");
            normalStudents = studentFileReader.readNormalStudents("normalStudent.csv");
            allStudents.addAll(goodStudents);
            allStudents.addAll(normalStudents);
        } catch (Exception e) {
            System.err.println("Input files have unknow errors!");
        }

        List<Student> tempAllStudent = new ArrayList<>(allStudents);
        allStudents.sort(Comparator
                .comparing(Student::getFullName, String.CASE_INSENSITIVE_ORDER).reversed()
                .thenComparing(Student::getPhoneNumber));

        tempAllStudent.forEach(s -> System.out.printf("Tên: %s | Phone: %s%n", s.getFullName(), s.getPhoneNumber()));
    }

}
