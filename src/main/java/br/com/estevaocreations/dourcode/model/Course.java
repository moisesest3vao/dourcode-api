package br.com.estevaocreations.dourcode.model;

import br.com.estevaocreations.dourcode.dto.CourseDto;
import br.com.estevaocreations.dourcode.dto.LessonDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany
    private List<Lesson> lessonList;
    @OneToOne
    private User creator;
    @Size(max = 256)
    private String description;

    public Course(CourseDto courseDto, User user) {
        this.id = courseDto.getId() != null ? courseDto.getId() : null;
        this.name = courseDto.getName() != null ? courseDto.getName() : null;
        this.lessonList = courseDto.getLessonList() != null ? courseDto.getLessonList().stream().map(Lesson::new).collect(Collectors.toList()) : null;
        this.creator = user;
        this.description = courseDto.getDescription() != null ? courseDto.getDescription() : null;
    }


}
