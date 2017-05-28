// Based on https://upload.wikimedia.org/wikipedia/commons/1/16/Report_Cards.jpg
package com.bogdanorzea.reportcardapp;

import java.util.ArrayList;

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
     * @param schoolYear
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
     * @param sub
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
        ret.append("\n").append(Subject.PrintBorder());
        ret.append("\n").append(Subject.PrintHeader());
        ret.append("\n").append(Subject.PrintBorder());

        for (Subject subject : subjectList) {
            ret.append("\n").append(subject.prettyPrint());
        }
        ret.append("\n");
        ret.append(Subject.PrintBorder());
        return ret.toString();
    }
}
