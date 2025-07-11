package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Arrays;
import java.util.List;

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

        Alien alien1 = new Alien();
        alien1.setAid(102);
        alien1.setAname("Chandan");
        alien1.setTech("SpringBoot");

        Alien alien2 = new Alien();
        alien2.setAid(103);
        alien2.setAname("Aryan");
        alien2.setTech("C++");

        alien.setLaptop(Arrays.asList(l1,l2));
        alien1.setLaptop(Arrays.asList(l2,l3));
        alien2.setLaptop(List.of(l1));

        l1.setAliens(Arrays.asList(alien, alien2));
        l2.setAliens(Arrays.asList(alien, alien2));
        l3.setAliens(List.of(alien1));

        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Alien.class)
                .addAnnotatedClass(Laptop.class)
                .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        session.persist(l1);
        session.persist(l2);
        session.persist(l3);

        session.persist(alien);
        session.persist(alien1);
        session.persist(alien2);

        transaction.commit();
    try{
        Alien a4 = session.byId(Alien.class).load(alien2.getAid());
        System.out.println("Loaded Alien: " + a4.getAname());
    } catch (Exception e) {
        System.err.println("Error: " + e.getMessage());
    } finally {
        // Close session only after all operations
        if (session.isOpen()) {
            session.close();
        }
    }
        sessionFactory.close();
    }
}