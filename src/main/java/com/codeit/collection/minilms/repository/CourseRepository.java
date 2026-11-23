package com.codeit.collection.minilms.repository;

import com.codeit.collection.minilms.domain.Course;

import java.util.List;

/** DB 대신 List로 흉내 낸 강의 저장소. */
public class CourseRepository {

    private static final List<Course> COURSES = List.of(
            new Course("CS101", "자바프로그래밍", "김철수", 3),
            new Course("CS102", "자료구조", "이영희", 3),
            new Course("MG201", "경영학원론", "박민정", 2),
            new Course("ME301", "열역학", "오성진", 3),
            new Course("CS201", "운영체제", "김철수", 3));

    public static List<Course> getCourses() {
        return COURSES;
    }
}
