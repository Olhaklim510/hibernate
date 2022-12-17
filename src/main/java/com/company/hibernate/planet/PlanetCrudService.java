package com.company.hibernate.planet;

import com.company.hibernate.storage.CrudService;
import com.company.hibernate.storage.hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class PlanetCrudService implements CrudService <Planet, String> {
    @Override
    public void create(Planet planet) throws Exception {
        if(!planet.getId().matches("^[A-Z0-9]*$")){
            throw new Exception("Id  must contain only uppercase letters and numbers");
        }

        if (planet.getId() == null) {
            throw new NullPointerException("Id can't be null");
        }

        if (planet.getName() == null) {
            throw new NullPointerException("Name can't be null");
        }

        if (planet.getName().length()>500){
            throw new Exception("Name must be less than 500 characters");
        }

        try (Session session = HibernateUtil.getINSTANCE().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(planet);
            transaction.commit();
        }
    }

    @Override
    public Planet getById(String id) {
        Planet planet;
        try (Session session = HibernateUtil.getINSTANCE().getSessionFactory().openSession()) {
            Query<Planet> queryGetById= session.createQuery("from Planet where id=:id", Planet.class);
            queryGetById.setParameter("id", id);
            planet=queryGetById.getSingleResult();
            System.out.println("planet = " + planet);
        }
        return planet;
    }

    @Override
    public void modify(String id, String name) throws Exception {
        if(!id.matches("^[A-Z0-9]*$")){
            throw new Exception("Id  must contain only uppercase letters and numbers");
        }

        if (name == null) {
            throw new NullPointerException("Name can't be null");
        }

        if (name.length()>500){
            throw new Exception("Name must be less than 500 characters");
        }
        try (Session session = HibernateUtil.getINSTANCE().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Query<Planet> queryModify= session.createQuery("from Planet where id=:id", Planet.class);
            queryModify.setParameter("id", id);
            Planet modifyPlanet = queryModify.getSingleResult();
            modifyPlanet.setName(name);
            transaction.commit();
            System.out.println("modifyPlanet = " + modifyPlanet);
        }
    }

    @Override
    public void deleteById(String id) {
        try (Session session = HibernateUtil.getINSTANCE().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Query<Planet> queryDelete = session.createQuery("from Planet where id= :id", Planet.class);
            queryDelete.setParameter("id", id);
            Planet planetDelete=queryDelete.getSingleResult();
            session.remove(planetDelete);
            transaction.commit();
        }
    }

    @Override
    public List<Planet> getAll() {
        List<Planet> planets;
        try (Session session = HibernateUtil.getINSTANCE().getSessionFactory().openSession()) {
            planets = session.createQuery("from Planet", Planet.class).list();
            System.out.println("planets = " + planets);
        }
        return planets;
    }
}
