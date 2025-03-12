package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class StudentDao {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa2pro");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Address address = new Address("New York", "5th Avenue");

        Student student = new Student("John Doe", address);

        em.persist(address);

        em.persist(student);


        em.getTransaction().commit();

        Student retrievedStudent = em.find(Student.class, student.getId());
        System.out.println("Student: " + retrievedStudent.getName() + ", Address: " + retrievedStudent.getAddress().getCity());

        em.close();
        emf.close();
    }
}
