package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import peaksoft.entity.Course;
import peaksoft.entity.Instructor;

import java.util.List;


@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long> {

//    List<Instructor> findInstructorsByCoursesIsNull();

    @Query("""
            SELECT i FROM Instructor i
            WHERE :course NOT MEMBER OF i.courses
            """)
    List<Instructor> findInstructorsNotAssignedToCourse(@Param("course") Course course);

}
