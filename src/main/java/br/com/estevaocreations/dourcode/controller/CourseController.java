package br.com.estevaocreations.dourcode.controller;

import br.com.estevaocreations.dourcode.dto.CourseDto;
import br.com.estevaocreations.dourcode.dto.LessonDto;
import br.com.estevaocreations.dourcode.model.Course;
import br.com.estevaocreations.dourcode.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid CourseDto courseDto){
        CourseDto course = this.courseService.create(courseDto);
        return course != null ? ResponseEntity.ok(course) : ResponseEntity.badRequest().build();
    }

    @GetMapping
    public ResponseEntity<?> getAllByUser(){
        List<CourseDto> courses = this.courseService.getAllByUser();
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id){
        CourseDto course = this.courseService.getById(id);
        return course != null ? ResponseEntity.ok(course) : ResponseEntity.notFound().build();
    }

    @GetMapping("all")
    public ResponseEntity<?> getAll(@RequestParam Integer page, @RequestParam Integer size){
        Page<Course> courses = this.courseService.getAll(page, size);
        return courses != null ? ResponseEntity.ok(courses) : ResponseEntity.notFound().build();
    }
}
