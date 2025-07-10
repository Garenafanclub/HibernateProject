package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {

        Laptop laptop = new Laptop();
        laptop.setBrand("ASUS");
        laptop.setModel("Rog");
        laptop.setRam(16);

        Alien alien = new Alien();
        alien.setAid(102);
        alien.setAname("Chandan");
        alien.setTech("Java");
        alien.setLaptop(laptop);

        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Alien.class)
                .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        alien = session.byId(Alien.class).load(102);
        System.out.println(alien);
        session.persist(alien);
        transaction.commit();
        session.close();
        sessionFactory.close();
    }
}