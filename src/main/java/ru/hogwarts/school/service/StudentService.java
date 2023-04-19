package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.*;

@Service
public class StudentService {
    private final Map<Long, Student> studentMap = new HashMap<>();
    private Long idCount = 0L;

    public Student addStudent(Student student) {
        student.setId(++idCount);
        studentMap.put(student.getId(), student);
        return student;
    }

    public Student findStudent(Long id) {
        return studentMap.get(id);
    }

    public Student editStudent(Student student) {
        if (!studentMap.containsKey(student.getId())) {
            return null;
        }
        studentMap.put(student.getId(), student);
        return student;
    }

    public Student deleteStudent(Long id) {
        return studentMap.remove(id);
    }

    public Map<Long, Student> getAllStudent() {
        return studentMap;
    }

    public Collection<Student> findByAge(Long age) {
        List<Student> result = new ArrayList<>();
        for (Student student : studentMap.values()) {
            if (student.getAge() == age) {
                result.add(student);
            }
        }
        return result;
    }
}
