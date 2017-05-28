package com.bogdanorzea.reportcardapp;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class SubjectUnitTest {
    @Test
    public void Should_Return_SubjectName() {
        Subject test = new Subject("READING");
        assertEquals("READING", test.getSubjectName());
    }

    @Test
    public void Should_Return_Grades() {
        Subject test = new Subject(null);
        test.addGrade(90);
        test.addGrade(60);
        test.addGrade(56);
        test.addGrade(44);

        assertArrayEquals(new int[]{90, 60, 56, 44}, test.getGrades());
    }

    @Test
    public void Should_Refuse_NegativeGrades() {
        Subject test = new Subject(null);
        test.addGrade(-1);

        assertArrayEquals(new int[]{0, 0, 0, 0}, test.getGrades());
    }

    @Test
    public void Should_Refuse_GraterThan100Grades() {
        Subject test = new Subject(null);
        test.addGrade(101);

        assertArrayEquals(new int[]{0, 0, 0, 0}, test.getGrades());
    }

    @Test
    public void Should_Refuse_MoreThanFourGrades() {
        Subject test = new Subject(null);
        test.addGrade(1);
        test.addGrade(2);
        test.addGrade(3);
        test.addGrade(4);
        test.addGrade(5);

        assertArrayEquals(new int[]{1, 2, 3, 4}, test.getGrades());
    }

    @Test
    public void Should_Create_SubjectWithGrades() {
        Subject test = new Subject("All", 1, 2, 3, 4);
        assertArrayEquals(new int[]{1, 2, 3, 4}, test.getGrades());
    }

    @Test
    public void Should_Create_SubjectWithonlyFourGradesFromConstructor() {
        Subject test = new Subject("All", 1, 2, 3, 4, 5, 6, 7, 8, 9);
        assertArrayEquals(new int[]{1, 2, 3, 4}, test.getGrades());
    }

    @Test
    public void Should_Create_SubjectWithIncompleteGradeArray() {
        Subject test = new Subject("All", 1, 2);
        assertArrayEquals(new int[]{1, 2, 0, 0}, test.getGrades());
    }

    @Test
    public void Should_Return_FirstSemAverage() {
        Subject test = new Subject("All", 92, 95);
        assertEquals(94, test.getFirstSemGrade());
    }

    @Test
    public void Should_Return_SecoundSemAverage() {
        Subject test = new Subject("All", 92, 95, 93, 89);
        assertEquals(91, test.getSecondSemGrade());
    }

    @Test
    public void Should_Return_SubjectAverage() {
        Subject test = new Subject("All", 92, 95, 91, 91);
        assertEquals(93, test.getFinalGrade());
    }

    @Test
    public void Should_Return_ToString() {
        Subject test = new Subject("All", 92, 95, 91, 91);
        assertEquals("Subject{subjectName='All', gradeArray=[92, 95, 91, 91]}", test.toString());
    }

    @Test
    public void Should_Return_prettyPrint() {
        Subject test = new Subject("All", 92, 95, 91, 91);
        assertEquals("|All                      |   92|   95|   94|   91|   91|   91|   93|", test.prettyPrint());
    }
}