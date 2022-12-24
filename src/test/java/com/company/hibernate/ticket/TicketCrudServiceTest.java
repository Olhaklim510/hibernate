package com.company.hibernate.ticket;

import com.company.hibernate.client.Client;
import com.company.hibernate.client.ClientCrudService;
import com.company.hibernate.planet.Planet;
import com.company.hibernate.planet.PlanetCrudService;
import jakarta.persistence.NoResultException;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static java.lang.String.*;
import static org.mockito.Mockito.mock;

class TicketCrudServiceTest {
    private TicketCrudService ticketCrudService;

    @BeforeEach
    public void beforeEach() throws SQLException {
        final String connectionUrl = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";
        Flyway flyway = Flyway
                .configure()
                .dataSource(connectionUrl, null, null)
                .load();
        flyway.migrate();

        ticketCrudService = new TicketCrudService();
    }


    @Test
    public void testThatTicketCreatedCorrectly() throws Exception {
        Ticket fullValueTicket = new Ticket();
        Client clientCreated = new ClientCrudService().getById(2L);
        Planet fromPlanetCreated=new PlanetCrudService().getById("4MARS");
        Planet toPlanetCreated=new PlanetCrudService().getById("7URANUS");

        fullValueTicket.setClient(clientCreated);
        fullValueTicket.setCreated_at(new Date());
        fullValueTicket.setFromPlanet(fromPlanetCreated);
        fullValueTicket.setToPlanet(toPlanetCreated);

        ticketCrudService.create(fullValueTicket);

        Assertions.assertEquals(fullValueTicket, ticketCrudService.getById(fullValueTicket.getId()));

        Assertions.assertThrowsExactly(NullPointerException.class, () -> ticketCrudService.create(null));
    }

    @Test
    public void testThatModifyTicketHandledCorrectly() throws Exception {
        Ticket originalTicket=new TicketCrudService().getById(3L);

        originalTicket.setCreated_at(new Date());
        originalTicket.setClient(new ClientCrudService().getById(10L));
        originalTicket.setFromPlanet(new PlanetCrudService().getById("4MARS"));
        originalTicket.setToPlanet(new PlanetCrudService().getById("7URANUS"));

        Long id= originalTicket.getId();
        ticketCrudService.modify(id, originalTicket);

        Assertions.assertEquals(id,originalTicket.getId());
        Assertions.assertEquals(originalTicket,new TicketCrudService().getById(3L));

        Assertions.assertThrowsExactly(NullPointerException.class, () -> ticketCrudService.modify(id,null));
    }

    @Test
    public void testThatDeleteByIdHandledCorrectly() throws Exception {
        Ticket originalTicket=new Ticket();
        Client clientDeleted = new ClientCrudService().getById(2L);
        Planet fromPlanetDeleted=new PlanetCrudService().getById("4MARS");
        Planet toPlanetDeleted=new PlanetCrudService().getById("7URANUS");

        originalTicket.setCreated_at(new Date());
        originalTicket.setClient(clientDeleted);
        originalTicket.setFromPlanet(fromPlanetDeleted);
        originalTicket.setToPlanet(toPlanetDeleted);

        ticketCrudService.create(originalTicket);

        Long id=originalTicket.getId();

        ticketCrudService.deleteById(id);

        Assertions.assertThrowsExactly(NoResultException.class, () ->ticketCrudService.deleteById(id));
    }

    @Test
    public void testThatGetAllListClientHandledCorrectly() throws Exception {
        Ticket expected=new Ticket();
        Client clientGetAll = new ClientCrudService().getById(2L);
        Planet fromPlanetGetAll=new PlanetCrudService().getById("4MARS");
        Planet toPlanetGetAll=new PlanetCrudService().getById("7URANUS");

        expected.setCreated_at(new Date());
        expected.setClient(clientGetAll);
        expected.setFromPlanet(fromPlanetGetAll);
        expected.setToPlanet(toPlanetGetAll);

        ticketCrudService.create(expected);

        Long id =expected.getId();
        expected.setId(id);

        List<Ticket> expectedTickets= Collections.singletonList(expected);
        List <Ticket> actualTickets=ticketCrudService.getAll();
        int indexExpectedPlanet = 0;
        for (int i = 0; i < actualTickets.size();i++) {
            if(actualTickets.get(i).getId().equals(expected.getId())) {
                indexExpectedPlanet=i;
            }
        }
        Assertions.assertEquals(expectedTickets.get(0),actualTickets.get(indexExpectedPlanet));
    }

}