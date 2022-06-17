package br.com.estevaocreations.dourcode.controller;

import br.com.estevaocreations.dourcode.dto.LessonDto;
import br.com.estevaocreations.dourcode.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("lesson")
public class LessonController {
    @Autowired
    private LessonService lessonService;

    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        LessonDto lesson = this.lessonService.getById(id);
        return lesson != null ? ResponseEntity.ok(lesson) : ResponseEntity.notFound().build();
    }



}
