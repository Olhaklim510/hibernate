package com.company.hibernate.client;

import jakarta.persistence.NoResultException;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;


class ClientCrudServiceTest {
    private ClientCrudService clientCrudService;

    @BeforeEach
    public void beforeEach() throws SQLException {
        final String connectionUrl = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";
        Flyway flyway = Flyway
                .configure()
                .dataSource(connectionUrl, null, null)
                .load();
        flyway.migrate();

        clientCrudService = new ClientCrudService();
    }


    @Test
    public void testThatClientCreatedCorrectly() throws Exception {
        Client fullValueClient = new Client();
        fullValueClient.setName("Test");

        clientCrudService.create(fullValueClient);

        Assertions.assertEquals(fullValueClient, clientCrudService.getById(fullValueClient.getId()));

        Assertions.assertThrowsExactly(NullPointerException.class, () -> clientCrudService.create(null));
    }

    @Test
    public void testThatModifyHandledCorrectly() throws Exception {
        Client originalClient=new Client();
        originalClient.setName("TestModify");

        clientCrudService.create(originalClient);
        originalClient.setId(originalClient.getId());

        Long id= originalClient.getId();
        originalClient.setName("NewTestModify");
        clientCrudService.modify(id, originalClient.getName());

        Assertions.assertEquals(id,originalClient.getId());
        Assertions.assertEquals("NewTestModify",clientCrudService.getById(id).getName());

        Assertions.assertThrowsExactly(Exception.class, () -> clientCrudService.modify(id,null));
        Assertions.assertThrowsExactly(Exception.class, () -> clientCrudService.modify(id,"I"));
    }

    @Test
    public void testThatDeleteByIdHandledCorrectly() throws Exception {
        Client originalClient=new Client();
        originalClient.setName("TestDeleteById");

        clientCrudService.create(originalClient);
        Long id=originalClient.getId();

        clientCrudService.deleteById(id);

        Assertions.assertThrowsExactly(NoResultException.class, () ->clientCrudService.deleteById(id));
    }

    @Test
    public void testThatGetAllListClientHandledCorrectly() throws Exception {
        Client expected=new Client();
        expected.setName("TestGetAllList");

        clientCrudService.create(expected);
        long id =expected.getId();
        expected.setId(id);

        List <Client> expectedClients= Collections.singletonList(expected);
        List <Client> actualClients=clientCrudService.getAll();
        int indexExpectedClient = 0;
        for (int i = 0; i < actualClients.size();i++) {
            if(actualClients.get(i).getId().equals(expected.getId())) {
                indexExpectedClient=i;
            }
        }
        Assertions.assertEquals(expectedClients.get(0).toString(),actualClients.get(indexExpectedClient).toString());
    }
}