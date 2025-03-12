package org.example;

import jakarta.persistence.*;

public class StudentModuleDao {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa2pro");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        try {
            Student student = new Student("John Doe");

            Module module1 = new Module("Mathematics");
            Module module2 = new Module("Computer Science");

            student.addModule(module1);
            student.addModule(module2);

            em.persist(student);

            em.getTransaction().commit();

            Student retrievedStudent = em.find(Student.class, student.getId());
            System.out.println("Student: " + retrievedStudent.getName());
            System.out.println("Modules:");
            for (Module module : retrievedStudent.getModules()) {
                System.out.println("- " + module.getName());
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
