package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {

        Student student = new Student();
        student.setsAge(22);
        student.setsName("Mayank");
        student.setRollNo(101);


        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cgf.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.persist(student);
        transaction.commit();
        session.close();
        sessionFactory.close();
    }
}