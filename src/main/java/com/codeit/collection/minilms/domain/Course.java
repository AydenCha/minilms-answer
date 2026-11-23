package com.codeit.collection.minilms.domain;

/** 강의 하나. 강의 코드(courseCode)로 강의를 구분하고, 수강 내역(Enrollment)이 이 코드로 강의를 가리킵니다. */
public class Course {

    private final String courseCode;
    private final String courseName;
    private final String professor;
    private final int credit;

    public Course(String courseCode, String courseName, String professor, int credit) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.professor = professor;
        this.credit = credit;
    }

    public String getCourseCode() { return courseCode; }
    public String getCourseName() { return courseName; }
    public String getProfessor() { return professor; }
    public int getCredit() { return credit; }

    // 출력 형식: Course{CS101, 자바프로그래밍, 김철수, 3학점}
    @Override
    public String toString() {
        return "Course{" + courseCode + ", " + courseName + ", " + professor + ", " + credit + "학점}";
    }
}
