package com.codeit.collection.minilms.service;

import com.codeit.collection.minilms.domain.Enrollment;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/** 수강 내역을 받아 학생별 조회, 학기별, 강의별 집계를 하는 서비스. */
public class EnrollmentService {

    private final List<Enrollment> enrollments;

    public EnrollmentService(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }

    public List<Enrollment> findAll() {
        return enrollments;
    }

    /** 한 학생의 수강 내역 */
    public List<Enrollment> findByStudent(long studentId) {
        return enrollments.stream()
                .filter(e -> e.getStudentId() == studentId)
                .collect(Collectors.toList());
    }

    /** 학기별 수강 인원(같은 학생이 한 학기에 두 과목을 들어도 1명) */
    public Map<String, Long> headcountBySemester() {
        return enrollments.stream()
                .collect(Collectors.groupingBy(Enrollment::getSemester, TreeMap::new,
                        Collectors.collectingAndThen(
                                Collectors.mapping(Enrollment::getStudentId, Collectors.toSet()),
                                ids -> (long) ids.size())));
    }

    /** 강의별 평균 성적 */
    public Map<String, Double> averageScoreByCourse() {
        return enrollments.stream()
                .collect(Collectors.groupingBy(Enrollment::getCourseCode, TreeMap::new,
                        Collectors.averagingInt(Enrollment::getScore)));
    }

    /** 수강 인원이 많은 강의 TOP N. 인원이 같으면 강의 코드 순 */
    public List<Map.Entry<String, Long>> topCourses(int n) {
        Map<String, Long> counts = enrollments.stream()
                .collect(Collectors.groupingBy(Enrollment::getCourseCode, Collectors.counting()));

        // 값(인원) 내림차순, 값이 같으면 키(강의 코드) 오름차순
        Comparator<Map.Entry<String, Long>> byCountDesc = Map.Entry.comparingByValue(Comparator.reverseOrder());
        Comparator<Map.Entry<String, Long>> order = byCountDesc.thenComparing(Map.Entry.comparingByKey());

        return counts.entrySet().stream()
                .sorted(order)
                .limit(n)
                .collect(Collectors.toList());
    }
}
