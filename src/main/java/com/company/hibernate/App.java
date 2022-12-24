package com.company.hibernate;

import com.company.hibernate.client.Client;
import com.company.hibernate.client.ClientCrudService;
import com.company.hibernate.planet.Planet;
import com.company.hibernate.planet.PlanetCrudService;
import com.company.hibernate.ticket.Ticket;
import com.company.hibernate.ticket.TicketCrudService;

import java.util.Date;

public class App {
    public static void main(String[] args) throws Exception {

        //test for ClientCrudService.class
        Client client = new Client();
        client.setName("TestCreate");
        new ClientCrudService().create(client);

        new ClientCrudService().getById(2L);

        new ClientCrudService().modify(3L, "TestModify");

        new ClientCrudService().deleteById(9L);

        new ClientCrudService().getAll();

        //test for PlanetCrudService.class
        Planet planet = new Planet();
        planet.setId("3TESTID");
        planet.setName("TestCreateNew");
        new PlanetCrudService().create(planet);

        new PlanetCrudService().getById("4MARS");

        new PlanetCrudService().modify("2VENUS", "TestModify");

        new PlanetCrudService().deleteById("1MERCURY");

        new PlanetCrudService().getAll();

        //test for TicketCrudService.class
        Ticket ticket = new Ticket();
        ticket.setCreated_at(new Date());
        ticket.setClient(new ClientCrudService().getById(6L));
        ticket.setFromPlanet(new PlanetCrudService().getById("5JUPITER"));
        ticket.setToPlanet(new PlanetCrudService().getById("2VENUS"));
        new TicketCrudService().create(ticket);

        new TicketCrudService().getById(2L);

        Ticket ticketModified = new Ticket();
        ticketModified.setCreated_at(new Date());
        ticketModified.setClient(client);
        ticketModified.setFromPlanet(new PlanetCrudService().getById("3EARTH"));
        ticketModified.setToPlanet(new PlanetCrudService().getById("6SATURN"));
        new TicketCrudService().create(ticketModified);

        new TicketCrudService().modify(3L, ticketModified);

        new TicketCrudService().deleteById(5L);

        new TicketCrudService().getAll();
    }
}
