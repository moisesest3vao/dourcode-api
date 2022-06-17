package br.com.estevaocreations.dourcode.repository;

import br.com.estevaocreations.dourcode.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {
}
