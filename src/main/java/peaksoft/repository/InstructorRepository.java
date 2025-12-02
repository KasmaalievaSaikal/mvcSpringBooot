package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import peaksoft.entity.Instructor;

import java.util.List;


@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long> {

    List<Instructor> findInstructorsByCoursesIsNull();
}
