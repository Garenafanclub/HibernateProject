package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {

        Student student = new Student();
        student.setsAge(25);
        student.setsName("Sahil");
        student.setRollNo(104);


        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        student =  session.get(Student.class, 104);
        session.remove(student);
        transaction.commit();
        session.close();
        sessionFactory.close();
    }
}