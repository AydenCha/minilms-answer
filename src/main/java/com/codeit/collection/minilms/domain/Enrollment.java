package com.codeit.collection.minilms.domain;

/** 수강 내역 한 건. Student, Course 객체 대신 학번과 강의 코드만 가집니다. */
public class Enrollment {

    private final long studentId;
    private final String courseCode;
    private final String semester;
    private final int score;

    public Enrollment(long studentId, String courseCode, String semester, int score) {
        this.studentId = studentId;
        this.courseCode = courseCode;
        this.semester = semester;
        this.score = score;
    }

    public long getStudentId() { return studentId; }
    public String getCourseCode() { return courseCode; }
    public String getSemester() { return semester; }
    public int getScore() { return score; }

    // 출력 형식: Enrollment{2023001, CS101, 2023-1, 95}
    @Override
    public String toString() {
        return "Enrollment{" + studentId + ", " + courseCode + ", " + semester + ", " + score + "}";
    }
}
