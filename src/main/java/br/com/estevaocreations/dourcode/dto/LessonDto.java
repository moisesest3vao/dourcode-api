package br.com.estevaocreations.dourcode.dto;

import br.com.estevaocreations.dourcode.enums.LessonType;
import br.com.estevaocreations.dourcode.model.Lesson;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LessonDto {
    private Long id;
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
    @Enumerated(EnumType.STRING)
    private LessonType type;
    @Size(max = 256)
    @NotNull
    private String description;
    @Size(max = 3000)
    @NotNull
    @NotBlank
    private String resource;

    public LessonDto(Lesson lesson) {
        this.id = lesson.getId() != null ? lesson.getId() : null;
        this.name = lesson.getName() != null ? lesson.getName() : null;
        this.type = lesson.getType() != null ? lesson.getType() : null;
        this.description = lesson.getDescription() != null ? lesson.getDescription() : null;
        this.resource = lesson.getResource() != null ? lesson.getResource() : null;
    }

}
