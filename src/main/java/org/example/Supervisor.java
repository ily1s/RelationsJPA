package org.example;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "SupervisorUni")
public class Supervisor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "supervisors")
    List<Student> students = new ArrayList<>();


    public Supervisor() {
    }

    public Supervisor(String name) {
        this.name = name;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Student> getStudents() {
        return students;
    }

    public Supervisor(List<Student> students, Long id) {
        this.students = students;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void addStudent(Student student) {
        students.add(student);
        student.getSupervisors().add(this);
    }

}
