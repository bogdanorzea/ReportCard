// Based on https://upload.wikimedia.org/wikipedia/commons/1/16/Report_Cards.jpg
package com.bogdanorzea.reportcardapp;

import java.util.ArrayList;
import java.util.Arrays;

public class ReportCard {
    private final String studentFirstName;
    private final String studentLastName;
    private final ArrayList<Subject> subjectList = new ArrayList<>();
    private String schoolName = null;
    private int schoolYear = -1;

    public ReportCard(String studentFirstName, String studentLastName) {
        this.studentFirstName = studentFirstName.trim();
        this.studentLastName = studentLastName.trim();
    }

    /**
     * @return School name for the report card
     */
    public String getSchoolName() {
        return schoolName;
    }

    /**
     * Sets the school name for the report card. Once set, it cannot be changed.
     *
     * @param schoolName SchoolName
     */
    public void setSchoolName(String schoolName) {
        if (this.schoolName == null) {
            this.schoolName = schoolName;
        }
    }

    /**
     * @return School year
     */
    public int getSchoolYear() {
        return schoolYear;
    }

    /**
     * Sets the school year
     *
     * @param schoolYear integer grader than 1970
     */
    public void setSchoolYear(int schoolYear) {
        if (this.schoolYear == -1) {
            // Limit the school year to 1970
            if (schoolYear >= 1970) {
                this.schoolYear = schoolYear;
            }
        }
    }

    /**
     * @return Student first name
     */
    public String getStudentFirstName() {
        return studentFirstName;
    }

    /**
     * @return Student last name
     */
    public String getStudentLastName() {
        return studentLastName;
    }

    /**
     * @return Student full name
     */
    public String getStudentFullName() {
        return getStudentFirstName() + ' ' + getStudentLastName();
    }

    /**
     * Add uniquely named subject to ReportCard
     *
     * @param sub Subject
     */
    public void addSubject(Subject sub) {
        if (!hasSubject(sub.getSubjectName())) {
            subjectList.add(sub);
        }
    }

    /**
     * @return Check if the Subject is in the ReportCard list
     */
    public boolean hasSubject(String subjectName) {
        for (Subject subject : subjectList) {
            if (subject.getSubjectName().equals(subjectName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return List of Subjects from the ReportCard
     */
    public ArrayList<Subject> getSubjectList() {
        return subjectList;
    }

    @Override
    public String toString() {
        return "ReportCard{" +
                "studentFirstName='" + studentFirstName + '\'' +
                ", studentLastName='" + studentLastName + '\'' +
                ", subjectList=" + subjectList +
                ", schoolName='" + schoolName + '\'' +
                ", schoolYear=" + schoolYear +
                '}';
    }

    /**
     * @return ReportCard formatted as a table
     */
    public String prettyPrint() {
        StringBuilder ret = new StringBuilder();
        ret.append("First Name: ").append(getStudentFirstName()).append('\n');
        ret.append("Last Name: ").append(getStudentLastName()).append('\n');
        ret.append("School Name: ").append(getSchoolName()).append('\n');
        ret.append("School Year: ").append(getSchoolYear()).append('\n');
        ret.append("\n").append(Subject.printBorder());
        ret.append("\n").append(Subject.printHeader());
        ret.append("\n").append(Subject.printBorder());

        for (Subject subject : subjectList) {
            ret.append("\n").append(subject.prettyPrint());
        }
        ret.append("\n");
        ret.append(Subject.printBorder());
        return ret.toString();
    }
}

class Subject {
    private final static int MAX_GRADES = 4;

    // Each subject has four grades ranging from 1 to 100, two for each semester.
    private int gradeArray[] = new int[MAX_GRADES];

    private String subjectName;

    private Subject() {
    }

    /**
     * Constructor for a Subject from the ReportCard providing the name
     *
     * @param name Subject name
     */
    Subject(String name) {
        subjectName = name;
    }

    /**
     * Constructor for a Subject from the ReportCard providing the name and the grades
     *
     * @param name   Subject name
     * @param grades Coma separated integers or an array of integers representing grades
     */
    Subject(String name, int... grades) {
        this(name);
        for (int grade : grades) {
            addGrade(grade);
        }
    }

    static String printHeader() {
        return String.format("|%1$-25s|%2$5s|%3$5s|%6$5s|%4$5s|%5$5s|%7$5s|%8$5s|",
                "Subject Name",
                "1st",
                "2nd",
                "3rd",
                "4th",
                "SEM 1",
                "SEM 2",
                "FNL");
    }

    static String printBorder() {
        return new String(new char[69]).replace("\0", "-");
    }

    /**
     * @return Return the subject name
     */
    String getSubjectName() {
        return subjectName;
    }

    /**
     * Add a grade to the Subject
     *
     * @param grade Integer between 1 and 100
     */
    void addGrade(int grade) {
        if (grade < 1 || grade > 100) {
            return;
        }
        for (int i = 0; i < MAX_GRADES; i++) {
            if (gradeArray[i] < 1) {
                gradeArray[i] = grade;
                return;
            }
        }
    }

    /**
     * @return Returns array of integers representing the Subject's grades
     */
    int[] getGrades() {
        return gradeArray;
    }

    /**
     * @return First semester average grade
     */
    int getFirstSemGrade() {
        int i = 0;
        int sum = 0;
        for (; i < MAX_GRADES / 2; i++) {
            sum += gradeArray[i];
        }
        return (int) (sum / (MAX_GRADES / 2.0) + 0.5);
    }

    /**
     * @return First semester average grade
     */
    int getSecondSemGrade() {
        int i = MAX_GRADES / 2;
        int sum = 0;
        for (; i < MAX_GRADES; i++) {
            sum += gradeArray[i];
        }
        return (int) (sum / (MAX_GRADES / 2.0) + 0.5);
    }

    /**
     * @return Subject final grade
     */
    int getFinalGrade() {
        return (int) ((getFirstSemGrade() + getSecondSemGrade()) / (MAX_GRADES / 2.0) + 0.5);
    }

    @Override
    public String toString() {
        return "Subject{" +
                "subjectName='" + subjectName + '\'' +
                ", gradeArray=" + Arrays.toString(gradeArray) +
                '}';
    }

    String prettyPrint() {
        return String.format("|%1$-25s|%2$5d|%3$5d|%6$5d|%4$5d|%5$5d|%7$5d|%8$5d|",
                subjectName,
                gradeArray[0],
                gradeArray[1],
                gradeArray[2],
                gradeArray[3],
                getFirstSemGrade(),
                getSecondSemGrade(),
                getFinalGrade());
    }
}
