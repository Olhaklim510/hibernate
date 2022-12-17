package com.company.hibernate.client;

import com.company.hibernate.storage.CrudService;
import com.company.hibernate.storage.hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

public class ClientCrudService implements CrudService <Client, Long>{
    @Override
    public void create(Client client) throws Exception {

        if (client.getName() == null) {
            throw new NullPointerException("Name can't be null");
        }

        if (client.getName().length()<3 | client.getName().length()>200){
            throw new Exception("Name must be more than 3 characters and less than 200 characters");
        }

        try (Session session = HibernateUtil.getINSTANCE().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(client);
            transaction.commit();
        }
    }

    @Override
    public Client getById(Long id) {
        Client client;
        try (Session session = HibernateUtil.getINSTANCE().getSessionFactory().openSession()) {
            Query<Client> queryGetById= session.createQuery("from Client where id=:id", Client.class);
            queryGetById.setParameter("id", id);
            client=queryGetById.getSingleResult();
            System.out.println("client = " + client);
        }
        return client;
    }

    @Override
    public void modify(Long id, String name) throws Exception {
        if (name == null) {
            throw new Exception("Name can't be null");
        }

        if (name.length()<3 | name.length()>200){
            throw new Exception("Name must be more than 3 characters and less than 200 characters");
        }
          try (Session session = HibernateUtil.getINSTANCE().getSessionFactory().openSession()) {
              Transaction transaction = session.beginTransaction();
              Query<Client> queryModify= session.createQuery("from Client where id=:id", Client.class);
              queryModify.setParameter("id", id);
              Client modifyClient = queryModify.getSingleResult();
              modifyClient.setName(name);
              transaction.commit();
              System.out.println("modifyClient = " + modifyClient);
          }
    }

    @Override
    public void deleteById(Long id) {
        try (Session session = HibernateUtil.getINSTANCE().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Query<Client> queryDelete = session.createQuery("from Client where id= :id", Client.class);
            queryDelete.setParameter("id", id);
            Client clientDelete=queryDelete.getSingleResult();
            session.remove(clientDelete);
            transaction.commit();
        }
    }

    @Override
    public List<Client> getAll() {
        List<Client> clients;
        try (Session session = HibernateUtil.getINSTANCE().getSessionFactory().openSession()) {
            clients = session.createQuery("from Client", Client.class).list();
            System.out.println("clients = " + clients);
        }
        return clients;
    }
}
