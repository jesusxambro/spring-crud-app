package com.galvanize.crudapp.dao;

import com.galvanize.crudapp.model.Lesson;
import org.springframework.data.repository.CrudRepository;

public interface LessonRepository extends CrudRepository<Lesson, Long> {
}
