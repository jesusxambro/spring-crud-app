package com.galvanize.crudapp.controller;


import com.galvanize.crudapp.dao.LessonRepository;
import com.galvanize.crudapp.model.Lesson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lessons")
public class LessonsController {
    private final LessonRepository repository;

    public LessonsController(LessonRepository repository){
        this.repository = repository;
    }

    @GetMapping("")
    public Iterable<Lesson> all(){
        return this.repository.findAll();
    }

    @PostMapping("")
    public Lesson create(@RequestBody Lesson lesson){
        return this.repository.save(lesson);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lesson> getLessonById(@PathVariable Long id){
       if(this.repository.findById(id).isPresent()){
           Lesson lesson = this.repository.findById(id).get();
           return new ResponseEntity<>(lesson, HttpStatus.ACCEPTED);
       }else{
           return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
       }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteMapping(@PathVariable long id){
        this.repository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

//    @PatchMapping("{id}")
//    public ResponseEntity<Object> findByIdAndUpdate(@PathVariable long id, @RequestBody Lesson thingToUpdate){
//        if(this.repository.findById(id).isPresent()){
//            Lesson toUpdate = this.repository.findById(id).get();
//
//        }
//    }



}
