package br.com.estevaocreations.dourcode.service;

import br.com.estevaocreations.dourcode.dto.CourseDto;
import br.com.estevaocreations.dourcode.model.Course;
import br.com.estevaocreations.dourcode.model.User;
import br.com.estevaocreations.dourcode.repository.CourseRepository;
import br.com.estevaocreations.dourcode.repository.LessonRepository;
import br.com.estevaocreations.dourcode.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private LessonRepository lessonRepository;

    public CourseDto create(CourseDto courseDto) {
        User user = this.getUser();
        if(user != null){
            Course course = save(new Course(courseDto, user));
            return new CourseDto(course);
        }
        return null;
    }

    private Course save(Course course){
        course.getLessonList().forEach(lesson -> {
            this.lessonRepository.save(lesson);
        });
        return this.courseRepository.save(course);
    }

    public List<CourseDto> getAllByUser() {
        User user = this.getUser();
        List<Course> all = this.courseRepository.findAllByCreatorEmail(user.getEmail());
        return all.stream().map(CourseDto::new).collect(Collectors.toList());
    }

    public User getUser(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        return this.userRepository.findByEmail(userDetails.getUsername()).orElse(null);
    }

    public CourseDto getById(Long id) {
        try{
            Course course = this.courseRepository.getReferenceById(id);
            return new CourseDto(course);
        }catch (EntityNotFoundException e){
            return null;
        }
    }

    public Page<Course> getAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return this.courseRepository.findAllByOrderByIdDesc(pageable);
    }
}
