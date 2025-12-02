package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import peaksoft.entity.Course;

import java.util.List;


@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("SELECT c FROM Course c JOIN c.instructors i WHERE i.id = :instructorId")
    List<Course> getAllCoursesByInstructorId(@Param("instructorId") Long instructorId);

    @Query("SELECT c FROM Course c JOIN c.students s WHERE s.id = :studentId")
    List<Course> getAllCoursesByStudentId(@Param("studentId") Long studentId);
}