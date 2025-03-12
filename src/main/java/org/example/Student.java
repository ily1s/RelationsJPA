package org.example;


import jakarta.persistence.*;

import javax.security.auth.Subject;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "StudentUni")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Address address;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    List<Module> modules = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "students_supervisors",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "suprvisor_id")
    )
    private List<Supervisor> supervisors = new ArrayList<>();

    public Student() {
        super();
    }

    public Student(String name, Address adress) {
        super();
        this.name = name;
        this.address = adress;
        address.setStudent(this);
    }

    public Student(String name) {
        this.name = name;
    }

    public List<Module> getModules() {
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addModule(Module module) {
        modules.add(module);
        module.setStudent(this);
    }

    public void addSupervisor(Supervisor supervisor) {
        supervisors.add(supervisor);
        supervisor.getStudents().add(this);
    }

    public void setSupervisors(List<Supervisor> supervisors) {
        this.supervisors = supervisors;
    }

    public List<Supervisor> getSupervisors() {
        return supervisors;
    }
}
