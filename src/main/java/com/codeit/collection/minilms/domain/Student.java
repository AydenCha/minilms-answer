package com.codeit.collection.minilms.domain;

/** 학생 한 명. 학번(studentId)으로 학생을 구분하고, 수강 내역(Enrollment)이 이 학번으로 학생을 가리킵니다. */
public class Student {

    private final long studentId;
    private final String name;
    private final int grade;
    private final String major;
    private final String gender;

    public Student(long studentId, String name, int grade, String major, String gender) {
        this.studentId = studentId;
        this.name = name;
        this.grade = grade;
        this.major = major;
        this.gender = gender;
    }

    public long getStudentId() { return studentId; }
    public String getName() { return name; }
    public int getGrade() { return grade; }
    public String getMajor() { return major; }
    public String getGender() { return gender; }

    // 출력 형식: Student{2023001, 홍길동, 1학년, 컴퓨터공학, 남}
    @Override
    public String toString() {
        return "Student{" + studentId + ", " + name + ", " + grade + "학년, " + major + ", " + gender + "}";
    }
}
