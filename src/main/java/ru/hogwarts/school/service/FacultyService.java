package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;

import java.util.*;

@Service
public class FacultyService {
    private final Map<Long, Faculty> facultyMap = new HashMap<>();
    private Long idCount = 0L;

    public Faculty addFaculty(Faculty faculty) {
        faculty.setId(++idCount);
        facultyMap.put(faculty.getId(), faculty);
        return faculty;
    }

    public Faculty findFaculty(Long id) {
        return facultyMap.get(id);
    }

    public Faculty editFaculty(Faculty faculty) {
        if (!facultyMap.containsKey(faculty.getId())) {
            return null;
        }
        facultyMap.put(faculty.getId(), faculty);
        return faculty;
    }

    public Faculty deleteFaculty(Long id) {
        return facultyMap.remove(id);
    }

    public Collection<Faculty> findByColor(String color) {
        List<Faculty> result = new ArrayList<>();
        for (Faculty faculty : facultyMap.values()) {
            if (faculty.getColor().equals(color)) {
                result.add(faculty);
            }
        }
        return result;
    }

    public Map<Long, Faculty> getAllFaculty() {
        return facultyMap;
    }
}
