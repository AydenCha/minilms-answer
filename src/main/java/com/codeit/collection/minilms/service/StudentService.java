package com.codeit.collection.minilms.service;

import com.codeit.collection.minilms.domain.Student;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

/** 학생 목록을 받아 조회, 검색, 그룹화를 하는 서비스. 데이터는 생성자로 받습니다. */
public class StudentService {

    private final List<Student> students;

    public StudentService(List<Student> students) {
        this.students = students;
    }

    /** 전체 학생 목록(학번 순). comparingLong은 long 값으로 비교하는 Comparator를 만듭니다. */
    public List<Student> findAll() {
        return students.stream()
                .sorted(Comparator.comparingLong(Student::getStudentId))
                .collect(Collectors.toList());
    }

    /** 학번으로 한 명 찾기. 없을 수 있으므로 Optional */
    public Optional<Student> findById(long studentId) {
        return students.stream().filter(s -> s.getStudentId() == studentId).findFirst();
    }

    /** 이름 검색(부분 일치) */
    public List<Student> searchByName(String keyword) {
        return students.stream()
                .filter(s -> s.getName().contains(keyword))
                .collect(Collectors.toList());
    }

    /** 학년별 학생 이름. TreeMap이라 학년 순으로 출력됩니다. */
    public Map<Integer, List<String>> namesByGrade() {
        return students.stream()
                .collect(Collectors.groupingBy(Student::getGrade, TreeMap::new,
                        Collectors.mapping(Student::getName, Collectors.toList())));
    }

    /** 전공별 학생 수 */
    public Map<String, Long> countByMajor() {
        return students.stream()
                .collect(Collectors.groupingBy(Student::getMajor, TreeMap::new, Collectors.counting()));
    }

    /** (옵션) 성별 + 전공 2단계 그룹화: 인원 수 */
    public Map<String, Map<String, Long>> countByGenderAndMajor() {
        return students.stream()
                .collect(Collectors.groupingBy(Student::getGender, TreeMap::new,
                        Collectors.groupingBy(Student::getMajor, TreeMap::new, Collectors.counting())));
    }

    /** (옵션) 전공과 학년이 모두 맞는 학생 */
    public List<Student> findByMajorAndGrade(String major, int grade) {
        return students.stream()
                .filter(s -> s.getMajor().equals(major))
                .filter(s -> s.getGrade() == grade)
                .collect(Collectors.toList());
    }
}
