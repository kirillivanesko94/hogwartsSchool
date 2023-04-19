package ru.hogwarts.school;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ContextConfiguration(classes = {StudentService.class})
@ExtendWith(SpringExtension.class)
public class StudentServiceTest {

    StudentService studentService;

    Student harryPotter = new Student(0L, "Гарри Поттер", 12);
    Student hermioneGranger = new Student(1L, "Гермиона Грейнджер", 12);
    Student ronWeasley = new Student(2L, "Рон Уизли", 11);


    @BeforeEach
    void setUp() {
        studentService = new StudentService();
        studentService.addStudent(harryPotter);
        studentService.addStudent(hermioneGranger);
    }

    @Test
    void createStudentTest() {
        Map<Long, Student> expected = new HashMap<>();
        expected.put(1L, harryPotter);
        expected.put(2L, hermioneGranger);
        expected.put(3L, ronWeasley);

        studentService.addStudent(ronWeasley);

        assertEquals(expected, studentService.getAllStudent());
    }

    @Test
    void findStudentTest() {
        assertEquals(harryPotter, studentService.findStudent(harryPotter.getId()));
    }

    @Test
    void editStudent() {
        ronWeasley = new Student(ronWeasley.getId(), "Ron", 15);
        studentService.editStudent(ronWeasley);
        assertEquals("Ron", ronWeasley.getName());
    }

    @Test
    void deleteStudentTest() {
        studentService.deleteStudent(harryPotter.getId());
        assertFalse(studentService.getAllStudent().containsKey(harryPotter.getId()));
    }

    @Test
    void getAllStudentTest() {
        studentService.addStudent(ronWeasley);

        Map<Long, Student> expected = new HashMap<>();
        expected.put(1L, harryPotter);
        expected.put(2L, hermioneGranger);
        expected.put(3L, ronWeasley);

        Map<Long, Student> actual = studentService.getAllStudent();

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(longs = 12)
    void findByAgeTest(Long age) {

        List<Student> expected = new ArrayList<>();
        expected.add(harryPotter);
        expected.add(hermioneGranger);

        assertEquals(expected, studentService.findByAge(age));
    }


}
