package br.com.estevaocreations.dourcode.model;

import br.com.estevaocreations.dourcode.dto.LessonDto;
import br.com.estevaocreations.dourcode.enums.LessonType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 2, max = 100)
    private String name;
    @Enumerated(EnumType.STRING)
    private LessonType type;
    @Size(max = 256)
    private String description;
    @Size(max = 3000)
    private String resource;

    public Lesson(LessonDto lessonDto) {
        this.id = lessonDto.getId() != null ? lessonDto.getId() : null;
        this.name = lessonDto.getName() != null ? lessonDto.getName() : null;
        this.type = lessonDto.getType() != null ? lessonDto.getType() : null;
        this.description = lessonDto.getDescription() != null ? lessonDto.getDescription() : null;
        this.resource = lessonDto.getResource() != null ? lessonDto.getResource() : null;
    }
}
