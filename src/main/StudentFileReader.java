package main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import main.exceptions.InvalidDOBException;
import main.exceptions.InvalidFullNameException;
import main.exceptions.InvalidPhoneNumberException;
import main.student.GoodStudent;
import main.student.NormalStudent;

import java.util.ArrayList;

public class StudentFileReader {
    public List<GoodStudent> readGoodStudents(String filePath)
            throws IOException, InvalidFullNameException, InvalidDOBException, InvalidPhoneNumberException {
        var list = new ArrayList<GoodStudent>();

        List<String> lines = Files.readAllLines(Path.of(filePath));
        for (String line : lines) {
            if (line.isBlank()) {
                continue;
            }

            String[] columns = line.split(",");

            GoodStudent goodStudent;
            try {
                double gpa = Double.parseDouble(columns[6]);
                goodStudent = new GoodStudent(
                        columns[0], // fullName
                        columns[1], // doB
                        columns[2], // sex
                        columns[3], // phoneNumber
                        columns[4], // universityName
                        columns[5], // gradeLevel
                        gpa, // gpa
                        columns[7] // bestRewardName
                );
            } catch (Exception e) {
                throw new IOException("Input files have unknow errors!");
            }

            list.add(goodStudent);
        }
        return list;
    }

    public List<NormalStudent> readNormalStudents(String filePath) throws IOException {
        var list = new ArrayList<NormalStudent>();

        List<String> lines = Files.readAllLines(Path.of(filePath));
        for (String line : lines) {
            if (line.isBlank()) {
                continue;
            }

            String[] columns = line.split(",");
            NormalStudent normalStudent;
            try {
                normalStudent = new NormalStudent(
                        columns[0], // fullName
                        columns[1], // doB
                        columns[2], // sex
                        columns[3], // phoneNumber
                        columns[4], // universityName
                        columns[5], // gradeLevel
                        Integer.parseInt(columns[6]), // englishScore
                        Double.parseDouble(columns[7]) // entryTestScore
                );
            } catch (Exception e) {
                throw new IOException("Input files have unknow errors!");
            }

            list.add(normalStudent);
        }
        return list;
    }
}
