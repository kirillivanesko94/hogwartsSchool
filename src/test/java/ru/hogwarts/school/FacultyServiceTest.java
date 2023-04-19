package ru.hogwarts.school;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;
import ru.hogwarts.school.service.StudentService;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ContextConfiguration(classes = {StudentService.class})
@ExtendWith(SpringExtension.class)
public class FacultyServiceTest {
    FacultyService facultyService;
    Faculty gryffindor = new Faculty(1L, "Грифиндор", "Красный");
    Faculty hufflepuff = new Faculty(1L, "Пуфендуй", "Желтый");
    Faculty slytherin = new Faculty(1L, "Слизерин", "Зеленый");

    @BeforeEach
    void setUp() {
        facultyService = new FacultyService();
        facultyService.addFaculty(gryffindor);
        facultyService.addFaculty(hufflepuff);
    }

    @Test
    void createFacultyTest() {
        Map<Long, Faculty> expected = new HashMap<>();
        expected.put(1L, gryffindor);
        expected.put(2L, hufflepuff);
        expected.put(3L, slytherin);

        facultyService.addFaculty(slytherin);

        assertEquals(expected, facultyService.getAllFaculty());
    }

    @Test
    void findFaculty() {
        assertEquals(gryffindor, facultyService.findFaculty(gryffindor.getId()));
    }

    @Test
    void editFaculty() {
        gryffindor = new Faculty(1L, "Gryffindor", "Крансый");
        facultyService.editFaculty(gryffindor);
        assertEquals("Gryffindor", gryffindor.getName());
    }

    @Test
    void deleteFaculty() {
        facultyService.deleteFaculty(slytherin.getId());
        assertFalse(facultyService.getAllFaculty().containsKey(slytherin.getId()));
    }

    @Test
    void getAllFacultyTest() {
        Map<Long, Faculty> actual = facultyService.getAllFaculty();

        Map<Long, Faculty> expected = new HashMap<>();
        expected.put(1L, gryffindor);
        expected.put(2L, hufflepuff);

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(strings = "Красный")
    void findByColorTest(String color) {
        Faculty gryffindor1 = new Faculty(1L, "gryffindor1", "Красный");
        facultyService.addFaculty(gryffindor1);

        Collection<Faculty> actual = facultyService.findByColor(color);

        List<Faculty> expected = new ArrayList<>();
        expected.add(gryffindor);
        expected.add(gryffindor1);

        assertEquals(expected, actual);
    }
}
