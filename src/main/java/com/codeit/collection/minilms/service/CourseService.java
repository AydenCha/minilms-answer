package com.codeit.collection.minilms.service;

import com.codeit.collection.minilms.domain.Course;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/** 강의 목록을 받아 조회, 그룹화, 통계를 하는 서비스. */
public class CourseService {

    private final List<Course> courses;

    public CourseService(List<Course> courses) {
        this.courses = courses;
    }

    public List<Course> findAll() {
        return courses;
    }

    /** 강의 코드 -> 강의 조회표. 다른 서비스가 강의명을 붙일 때 씁니다. */
    public Map<String, Course> byCode() {
        return courses.stream().collect(Collectors.toMap(Course::getCourseCode, c -> c));
    }

    /** 교수별 강의명 */
    public Map<String, List<String>> courseNamesByProfessor() {
        return courses.stream()
                .collect(Collectors.groupingBy(Course::getProfessor, TreeMap::new,
                        Collectors.mapping(Course::getCourseName, Collectors.toList())));
    }

    /** 교수별 강의 수 */
    public Map<String, Long> courseCountByProfessor() {
        return courses.stream()
                .collect(Collectors.groupingBy(Course::getProfessor, TreeMap::new, Collectors.counting()));
    }

    public int totalCredit() {
        return courses.stream().mapToInt(Course::getCredit).sum();
    }

    public double averageCredit() {
        return courses.stream().mapToInt(Course::getCredit).average().orElse(0.0);
    }

    /** 강의명 오름차순 */
    public List<String> namesSorted() {
        return courses.stream().map(Course::getCourseName).sorted().collect(Collectors.toList());
    }

    /** 특정 교수의 강의 */
    public List<Course> findByProfessor(String professor) {
        return courses.stream().filter(c -> c.getProfessor().equals(professor)).collect(Collectors.toList());
    }
}
