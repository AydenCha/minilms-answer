package com.codeit.collection.minilms.repository;

import com.codeit.collection.minilms.domain.Enrollment;

import java.util.List;

/** DB 대신 List로 흉내 낸 수강 내역 저장소. 한 줄이 (학번, 강의 코드, 학기, 점수) 한 건입니다. */
public class EnrollmentRepository {

    private static final List<Enrollment> ENROLLMENTS = List.of(
            new Enrollment(2023001, "CS101", "2023-1", 95),
            new Enrollment(2023002, "MG201", "2023-1", 88),
            new Enrollment(2023003, "CS101", "2023-1", 91),
            new Enrollment(2023004, "ME301", "2023-1", 75),
            new Enrollment(2023001, "CS102", "2023-2", 87),
            new Enrollment(2023002, "CS102", "2023-2", 93),
            new Enrollment(2023005, "CS201", "2023-2", 97));

    public static List<Enrollment> getEnrollments() {
        return ENROLLMENTS;
    }
}
