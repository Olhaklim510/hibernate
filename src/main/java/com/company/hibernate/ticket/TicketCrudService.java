package com.company.hibernate.ticket;

import com.company.hibernate.client.Client;
import com.company.hibernate.client.ClientCrudService;
import com.company.hibernate.planet.Planet;
import com.company.hibernate.planet.PlanetCrudService;
import com.company.hibernate.storage.CrudService;
import com.company.hibernate.storage.hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class TicketCrudService implements CrudService<Ticket, Long, Ticket> {
    @Override
    public void create(Ticket ticket) throws Exception {
        if (ticket.getClient() == null) {
            throw new NullPointerException("Client can't be null");
        }
        if (ticket.getFromPlanet() == null) {
            throw new NullPointerException("FromPlanet can't be null");
        }
        if (ticket.getToPlanet() == null) {
            throw new NullPointerException("ToPlanet can't be null");
        }

        Client client = new ClientCrudService().getById(ticket.getClient().getId());

        if (client==null) {
            throw new NullPointerException("This client does not exist.");
        }

        Planet fromPlanet = new PlanetCrudService().getById(ticket.getFromPlanet().getId());
        if (fromPlanet==null) {
            throw new NullPointerException("This fromPlanet does not exist.");
        }

        Planet toPlanet = new PlanetCrudService().getById(ticket.getToPlanet().getId());
        if (toPlanet==null) {
            throw new NullPointerException("This toPlanet does not exist.");
        }

        try (Session session = HibernateUtil.getINSTANCE().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(ticket);
            transaction.commit();
        }
    }

    @Override
    public Ticket getById(Long id) {

        try (Session session = HibernateUtil.getINSTANCE().getSessionFactory().openSession()) {
            return session.get(Ticket.class, id);
        }
    }

    @Override
    public void modify(Long id, Ticket ticket) throws Exception {
        if (ticket.getClient() == null) {
            throw new NullPointerException("Client can't be null");
        }

        if (ticket.getFromPlanet() == null) {
            throw new NullPointerException("FromPlanet can't be null");
        }
        if (ticket.getToPlanet() == null) {
            throw new NullPointerException("ToPlanet can't be null");
        }

        Client client = new ClientCrudService().getById(ticket.getClient().getId());
        if (client == null) {
            throw new Exception("This client does not exist.");
        }

        Planet fromPlanet = new PlanetCrudService().getById(ticket.getFromPlanet().getId());
        if (fromPlanet == null) {
            throw new NullPointerException("This fromPlanet does not exist.");
        }

        Planet toPlanet = new PlanetCrudService().getById(ticket.getToPlanet().getId());
        if (toPlanet == null) {
            throw new NullPointerException("This toPlanet does not exist.");
        }

        try (Session session = HibernateUtil.getINSTANCE().getSessionFactory().openSession()) {

            Transaction transaction = session.beginTransaction();
            Query<Ticket> queryModify = session.createQuery("from Ticket where id=:id", Ticket.class);
            queryModify.setParameter("id", id);
            Ticket modifyTicket = queryModify.getSingleResult();
            modifyTicket.setCreated_at(ticket.getCreated_at());
            modifyTicket.setClient(ticket.getClient());
            modifyTicket.setFromPlanet(ticket.getFromPlanet());
            modifyTicket.setToPlanet(ticket.getToPlanet());

            transaction.commit();
            System.out.println("modifyTicket = " + modifyTicket);
        }
    }

    @Override
    public void deleteById(Long id) {
        try (Session session = HibernateUtil.getINSTANCE().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Query<Ticket> queryDelete = session.createQuery("from Ticket where id= :id", Ticket.class);
            queryDelete.setParameter("id", id);
            Ticket ticketDelete = queryDelete.getSingleResult();
            session.remove(ticketDelete);
            transaction.commit();
        }
    }

    @Override
    public List<Ticket> getAll() {
        List<Ticket> tickets;
        try (Session session = HibernateUtil.getINSTANCE().getSessionFactory().openSession()) {
            tickets = session.createQuery("from Ticket", Ticket.class).list();
            System.out.println("tickets = " + tickets);
        }
        return tickets;
    }
}
