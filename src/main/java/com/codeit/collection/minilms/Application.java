package com.codeit.collection.minilms;

import com.codeit.collection.minilms.repository.CourseRepository;
import com.codeit.collection.minilms.repository.EnrollmentRepository;
import com.codeit.collection.minilms.repository.StudentRepository;
import com.codeit.collection.minilms.service.CourseService;
import com.codeit.collection.minilms.service.EnrollmentService;
import com.codeit.collection.minilms.service.ReportService;
import com.codeit.collection.minilms.service.StudentService;

// 파일 이름: Application.java
public class Application {

    public static void main(String[] args) {
        EnrollmentService enrollmentService = new EnrollmentService(EnrollmentRepository.getEnrollments());
        StudentService studentService = new StudentService(StudentRepository.getStudents());
        CourseService courseService = new CourseService(CourseRepository.getCourses());
        ReportService reportService = new ReportService(studentService, courseService, enrollmentService);

        /* 1. Enrollment 서비스 테스트 */
        System.out.println("-------------------- 1. Enrollment 서비스 테스트 --------------------");
        System.out.println("전체 수강 내역:");
        enrollmentService.findAll().forEach(System.out::println);

        System.out.println("\n홍길동(2023001)의 수강 내역:");
        enrollmentService.findByStudent(2023001).forEach(System.out::println);

        System.out.println("\n학기별 수강 인원:");
        System.out.println(enrollmentService.headcountBySemester());

        System.out.println("\n강의별 평균 성적:");
        System.out.println(enrollmentService.averageScoreByCourse());

        System.out.println("\nTOP 2 인기 강의:");
        System.out.println(enrollmentService.topCourses(2));

        /* 2. Student 서비스 테스트 */
        System.out.println("-------------------- 2. Student 서비스 테스트 --------------------");
        System.out.println("전체 학생 목록:");
        studentService.findAll().forEach(System.out::println);

        System.out.println("\n이름에 '민'이 포함된 학생:");
        System.out.println(studentService.searchByName("민"));

        System.out.println("\n학년별 그룹화:");
        System.out.println(studentService.namesByGrade());

        System.out.println("\n전공별 학생 수:");
        System.out.println(studentService.countByMajor());

        System.out.println("\n성별 + 전공 그룹화:");
        System.out.println(studentService.countByGenderAndMajor());

        System.out.println("\n전공이 컴퓨터공학이고 2학년인 학생:");
        System.out.println(studentService.findByMajorAndGrade("컴퓨터공학", 2));

        /* 3. Course 서비스 테스트 */
        System.out.println("-------------------- 3. Course 서비스 테스트 --------------------");
        System.out.println("전체 강의 목록:");
        courseService.findAll().forEach(System.out::println);

        System.out.println("\n교수별 강의:");
        System.out.println(courseService.courseNamesByProfessor());

        System.out.println("\n전체 학점 합계: " + courseService.totalCredit());

        System.out.println("\n전체 학점 평균: " + courseService.averageCredit());

        System.out.println("\n강의명 오름차순 정렬:");
        System.out.println(courseService.namesSorted());

        System.out.println("\n교수 김철수의 강의:");
        System.out.println(courseService.findByProfessor("김철수"));

        /* 4. Report 서비스 테스트 */
        System.out.println("-------------------- 4. Report 서비스 테스트 --------------------");
        System.out.println("리포트 생성:");
        System.out.println(reportService.transcript(2023001));
        System.out.println(reportService.fullReport(2));
    }
}
