package com.codeit.collection.minilms.repository;

import com.codeit.collection.minilms.domain.Student;

import java.util.List;

/** DB 대신 List로 흉내 낸 학생 저장소(Mock DB). List.of로 만들어 수정할 수 없습니다. */
public class StudentRepository {

    private static final List<Student> STUDENTS = List.of(
            new Student(2023001, "홍길동", 1, "컴퓨터공학", "남"),
            new Student(2023002, "김민지", 2, "경영학", "여"),
            new Student(2023003, "이수빈", 1, "컴퓨터공학", "여"),
            new Student(2023004, "최준영", 3, "기계공학", "남"),
            new Student(2023005, "박지현", 2, "컴퓨터공학", "여"));

    public static List<Student> getStudents() {
        return STUDENTS;
    }
}
