package br.com.estevaocreations.dourcode.dto;

import br.com.estevaocreations.dourcode.model.Course;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseDto {

    private Long id;
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    private List<LessonDto> lessonList;
    private UserDto creator;
    @Size(max = 256)
    @NotNull
    @NotBlank
    private String description;

    public CourseDto(Course course) {
        this.id = course.getId() != null ? course.getId() : null;
        this.name = course.getName() != null ? course.getName() : null;
        this.lessonList = course.getLessonList() != null ? course.getLessonList().stream().map(LessonDto::new).collect(Collectors.toList()) : null;
        this.creator = course.getCreator() != null ? new UserDto(course.getCreator()) : null;
        this.description = course.getDescription() != null ? course.getDescription() : null;
    }
}
