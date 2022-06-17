package br.com.estevaocreations.dourcode.repository;

import br.com.estevaocreations.dourcode.model.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findAllByCreatorEmail(String email);
    Page<Course> findAllByOrderByIdDesc(Pageable pageable);
}
