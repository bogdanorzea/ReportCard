package com.bogdanorzea.reportcardapp;

import java.util.Arrays;

public class Subject {
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
    public Subject(String name) {
        subjectName = name;
    }

    /**
     * Constructor for a Subject from the ReportCard providing the name and the grades
     *
     * @param name   Subject name
     * @param grades Coma separated integers or an array of integers representing grades
     */
    public Subject(String name, int... grades) {
        this(name);
        for (int grade : grades) {
            addGrade(grade);
        }
    }

    static protected String PrintHeader() {
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

    static protected String PrintBorder() {
        return new String(new char[69]).replace("\0", "-");
    }

    /**
     * @return Return the subject name
     */
    public String getSubjectName() {
        return subjectName;
    }

    /**
     * Add a grade to the Subject
     *
     * @param grade Integer between 1 and 100
     */
    public void addGrade(int grade) {
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
    public int[] getGrades() {
        return gradeArray;
    }

    /**
     * @return First semester average grade
     */
    public int getFirstSemGrade() {
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
    public int getSecondSemGrade() {
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
    public int getFinalGrade() {
        return (int) ((getFirstSemGrade() + getSecondSemGrade()) / (MAX_GRADES / 2.0) + 0.5);
    }

    @Override
    public String toString() {
        return "Subject{" +
                "subjectName='" + subjectName + '\'' +
                ", gradeArray=" + Arrays.toString(gradeArray) +
                '}';
    }

    public String prettyPrint() {
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
