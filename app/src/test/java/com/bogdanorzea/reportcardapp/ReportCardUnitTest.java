package com.bogdanorzea.reportcardapp;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ReportCardUnitTest {
    @Test
    public void Should_Return_StudentFirstName() {
        ReportCard report = new ReportCard("John", "Doe");
        assertEquals("John", report.getStudentFirstName());
    }

    @Test
    public void Should_Return_StudentLastName() {
        ReportCard report = new ReportCard("John", "Doe");
        assertEquals("Doe", report.getStudentLastName());
    }

    @Test
    public void Should_Return_StudentFullName() {
        ReportCard report = new ReportCard("John", "Doe");
        assertEquals("John Doe", report.getStudentFullName());
    }

    @Test
    public void Should_Return_SchoolName() {
        ReportCard report = new ReportCard("John", "Doe");
        report.setSchoolName("Hogwards");
        assertEquals("Hogwards", report.getSchoolName());
    }

    @Test
    public void Should_Return_OriginalSchoolName() {
        ReportCard report = new ReportCard("John", "Doe");
        report.setSchoolName("Hogwards");
        report.setSchoolName("MIT");
        assertEquals("Hogwards", report.getSchoolName());
    }

    @Test
    public void Should_Return_SchoolYear() {
        ReportCard report = new ReportCard("John", "Doe");
        report.setSchoolYear(1997);
        assertEquals(1997, report.getSchoolYear());
    }

    @Test
    public void Should_NotAdd_SchoolYearForOldYears() {
        ReportCard report = new ReportCard("John", "Doe");
        report.setSchoolYear(1966);
        assertEquals(-1, report.getSchoolYear());
    }

    @Test
    public void Should_Return_TrueForAddedSubject() {
        ReportCard report = new ReportCard("John", "Doe");
        Subject sub = new Subject("Reading");
        report.addSubject(sub);

        assertTrue(report.hasSubject("Reading"));
    }

    @Test
    public void Should_Return_FalseForInexistentSubject() {
        ReportCard report = new ReportCard("John", "Doe");
        Subject sub = new Subject("Reading");
        report.addSubject(sub);

        assertFalse(report.hasSubject("Writing"));
    }

    @Test
    public void Should_Return_SubjectList() {
        ReportCard report = new ReportCard("John", "Doe");
        Subject subReading = new Subject("Reading");
        Subject subWriting = new Subject("Writing");
        report.addSubject(subReading);
        report.addSubject(subWriting);

        ArrayList<Subject> testList = new ArrayList<>();
        testList.add(subReading);
        testList.add(subWriting);

        assertTrue(report.getSubjectList().equals(testList));
    }

    @Test
    public void Should_Return_toSting() {
        ReportCard report = new ReportCard("John", "Doe");
        report.addSubject(new Subject("Reading", 90, 91, 92, 93));
        report.addSubject(new Subject("Writing", 90, 90, 90, 90));
        report.setSchoolName("MIT");
        report.setSchoolYear(2017);
        assertEquals("ReportCard{studentFirstName='John', studentLastName='Doe', subjectList=[Subject{subjectName='Reading', gradeArray=[90, 91, 92, 93]}, Subject{subjectName='Writing', gradeArray=[90, 90, 90, 90]}], schoolName='MIT', schoolYear=2017}", report.toString());
    }

    @Test
    public void Should_Return_prettyPrint() {
        ReportCard report = new ReportCard("John", "Doe");
        report.addSubject(new Subject("Reading", 90, 91, 92, 93));
        report.addSubject(new Subject("Writing", 90, 90, 90, 90));
        report.setSchoolName("MIT");
        report.setSchoolYear(2017);
        String pretty = "First Name: John\n" +
                "Last Name: Doe\n" +
                "School Name: MIT\n" +
                "School Year: 2017\n" +
                "\n" +
                "---------------------------------------------------------------------\n" +
                "|Subject Name             |  1st|  2nd|SEM 1|  3rd|  4th|SEM 2|  FNL|\n" +
                "---------------------------------------------------------------------\n" +
                "|Reading                  |   90|   91|   91|   92|   93|   93|   92|\n" +
                "|Writing                  |   90|   90|   90|   90|   90|   90|   90|\n" +
                "---------------------------------------------------------------------";

        assertEquals(pretty, report.prettyPrint());
    }
}
