package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        Laptop l1 = new Laptop();
        l1.setLid(1);
        l1.setBrand("Hp");
        l1.setModel("15s");
        l1.setRam(16);

        Laptop l2 = new Laptop();
        l2.setLid(2);
        l2.setBrand("Dell");
        l2.setModel("Book");
        l2.setRam(32);

        Laptop l3 = new Laptop();
        l3.setLid(3);
        l3.setBrand("Macbook");
        l3.setModel("m3");
        l3.setRam(16);

        Alien alien = new Alien();
        alien.setAid(101);
        alien.setAname("Mayank");
        alien.setTech("Java");
        alien.setLaptop(Arrays.asList(l1,l2));

        Alien alien1 = new Alien();
        alien1.setAid(101);
        alien1.setAname("Mayank");
        alien1.setTech("Java");
        alien1.setLaptop(Arrays.asList(l1,l2));

        l1.setAlien(alien);
        l2.setAlien(alien);

        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Alien.class)
                .addAnnotatedClass(Laptop.class)
                .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        session.persist(l1);
        session.persist(l2);
        session.persist(alien);

        transaction.commit();
        session.close();
        sessionFactory.close();
    }
}