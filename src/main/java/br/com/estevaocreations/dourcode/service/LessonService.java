package br.com.estevaocreations.dourcode.service;

import br.com.estevaocreations.dourcode.dto.LessonDto;
import br.com.estevaocreations.dourcode.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class LessonService {
    @Autowired
    private LessonRepository lessonRepository;

    public LessonDto getById(Long id) {
        try{
            return new LessonDto(this.lessonRepository.getReferenceById(id));
        }catch (EntityNotFoundException e){
            return null;
        }
    }
}
