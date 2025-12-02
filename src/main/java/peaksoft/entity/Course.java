package peaksoft.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "courses")

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_gen")
    @SequenceGenerator(name = "course_gen", sequenceName = "course_seq", allocationSize = 1)

    Long id;
    String title;
    String imageUrl;
    @Column(name = "date_of_start")
    LocalDate dateOfStart;
    String description;
    @ManyToMany(mappedBy = "courses", cascade = {
            CascadeType.REMOVE,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH
    })
    List<Instructor> instructors;


    @ManyToMany(mappedBy = "courses", cascade = {
            CascadeType.REMOVE,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH
    })
    List<Student> students;


    @OneToMany(mappedBy = "course", cascade = {
            CascadeType.REMOVE,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH
    })
    List<Lesson> lessons;

    public Course(String title, LocalDate dateOfStart, String description) {
        this.title = title;
        this.dateOfStart = dateOfStart;
        this.description = description;
    }
}

