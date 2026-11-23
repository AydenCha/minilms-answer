package com.codeit.collection.minilms.service;

import com.codeit.collection.minilms.domain.Course;
import com.codeit.collection.minilms.domain.Enrollment;
import com.codeit.collection.minilms.domain.Student;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/** (옵션) 여러 서비스의 결과를 엮어 리포트 문자열을 만듭니다. */
public class ReportService {

    private final StudentService studentService;
    private final CourseService courseService;
    private final EnrollmentService enrollmentService;

    public ReportService(StudentService studentService, CourseService courseService,
                         EnrollmentService enrollmentService) {
        this.studentService = studentService;
        this.courseService = courseService;
        this.enrollmentService = enrollmentService;
    }

    /** 없는 강의 코드가 들어와도 NullPointerException이 나지 않도록 이름을 찾습니다. */
    private String courseName(Map<String, Course> courses, String code) {
        Course course = courses.get(code);
        if (course == null) {
            return "알 수 없는 강의(" + code + ")";
        }
        return course.getCourseName();
    }

    /** 한 학생의 성적표: "자바프로그래밍(2023-1) 95점" 목록 + 평균 */
    public String transcript(long studentId) {
        Map<String, Course> courses = courseService.byCode();
        String name = studentService.findById(studentId).map(Student::getName).orElse("알 수 없는 학생");
        List<Enrollment> mine = enrollmentService.findByStudent(studentId);

        String lines = mine.stream()
                .map(e -> "  - " + courseName(courses, e.getCourseCode())
                        + "(" + e.getSemester() + ") " + e.getScore() + "점")
                .collect(Collectors.joining("\n"));
        double avg = mine.stream().mapToInt(Enrollment::getScore).average().orElse(0.0);

        return String.format("[%s 성적표]%n%s%n  평균 %.1f점", name, lines, avg);
    }

    /** 전체 리포트 */
    public String fullReport(int topN) {
        Map<String, Course> courses = courseService.byCode();
        StringBuilder sb = new StringBuilder();

        sb.append("===== MiniLMS 리포트 =====\n");
        sb.append("학기별 수강 인원: ").append(enrollmentService.headcountBySemester()).append('\n');
        sb.append("강의별 평균 성적:\n");
        enrollmentService.averageScoreByCourse().forEach((code, avg) ->
                sb.append(String.format("  %s %-8s %.1f%n", code, courseName(courses, code), avg)));
        sb.append("전공별 학생 수: ").append(studentService.countByMajor()).append('\n');
        sb.append("인기 강의 TOP ").append(topN).append(": ")
                .append(enrollmentService.topCourses(topN).stream()
                        .map(en -> courseName(courses, en.getKey()) + "(" + en.getValue() + "명)")
                        .collect(Collectors.joining(", ")))
                .append('\n');
        sb.append("교수별 강의 수: ").append(courseService.courseCountByProfessor()).append('\n');
        sb.append("==========================");
        return sb.toString();
    }
}
