package org.example;

import jakarta.persistence.*;

public class StudentSupervisorDao {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa2pro");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        try {
            // Create a student
            Student student = new Student("Ilyas");
            Student student1 = new Student("AZ");
            Student student2 = new Student("RT");

            // Create modules
            Supervisor Supervisor1 = new Supervisor("Atlas");
            Supervisor Supervisor2 = new Supervisor("Zrikem");

            // Add Supervisors to the student
            student.addSupervisor(Supervisor1);
            student.addSupervisor(Supervisor2);

            // Add students to the Supervisors
            Supervisor1.addStudent(student1);
            Supervisor2.addStudent(student2);

            // Persist the student and Supervisors
            em.persist(student);
            em.persist(student1);
            em.persist(student2);
            em.persist(Supervisor1);
            em.persist(Supervisor2);

            em.getTransaction().commit();

            // Retrieve the student and print details
            Student retrievedStudent = em.find(Student.class, student.getId());
            System.out.println("Student: " + retrievedStudent.getName());
            System.out.println("Supervisors:");
            for (Supervisor Supervisor : retrievedStudent.getSupervisors()) {
                System.out.println("- " + Supervisor.getName());
            }
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
