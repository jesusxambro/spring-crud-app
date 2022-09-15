package com.galvanize.crudapp.dao;

import com.galvanize.crudapp.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
}
