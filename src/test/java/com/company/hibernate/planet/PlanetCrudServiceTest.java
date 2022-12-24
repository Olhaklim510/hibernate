package com.company.hibernate.planet;

import jakarta.persistence.NoResultException;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;


class PlanetCrudServiceTest {
    private PlanetCrudService planetCrudService;

    @BeforeEach
    public void beforeEach() throws SQLException {
        final String connectionUrl = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";
        Flyway flyway = Flyway
                .configure()
                .dataSource(connectionUrl, null, null)
                .load();
        flyway.migrate();

        planetCrudService = new PlanetCrudService();
    }


    @Test
    public void testThatPlanetCreatedCorrectly() throws Exception {
        Planet fullValuePlanet = new Planet();
        fullValuePlanet.setId("1TESTID");
        fullValuePlanet.setName("Test");

        planetCrudService.create(fullValuePlanet);

        Assertions.assertEquals(fullValuePlanet, planetCrudService.getById(fullValuePlanet.getId()));

        Assertions.assertThrowsExactly(NullPointerException.class, () -> planetCrudService.create(null));
    }

    @Test
    public void testThatModifyPlanetHandledCorrectly() throws Exception {
        Planet originalPlanet=new Planet();
        originalPlanet.setId("1TESTMODIFYID");
        originalPlanet.setName("TestModifyName");

        planetCrudService.create(originalPlanet);

        originalPlanet.setName("NewTestModify");
        planetCrudService.modify("1TESTMODIFYID", originalPlanet.getName());

        Assertions.assertEquals("1TESTMODIFYID",originalPlanet.getId());
        Assertions.assertEquals("NewTestModify",planetCrudService.getById("1TESTMODIFYID").getName());

        Assertions.assertThrowsExactly(NullPointerException.class, () -> planetCrudService.modify("1TESTMODIFYID",null));
    }

    @Test
    public void testThatDeletePlanetByIdHandledCorrectly() throws Exception {
        Planet originalPlanet=new Planet();
        originalPlanet.setId("1TESTDELETEBYID");
        originalPlanet.setName("TestDeleteById");

        planetCrudService.create(originalPlanet);
        String id=originalPlanet.getId();

        planetCrudService.deleteById(id);

        Assertions.assertThrowsExactly(NoResultException.class, () ->planetCrudService.deleteById(id));
    }

    @Test
    public void testThatGetAllListPlanetHandledCorrectly() throws Exception {
        Planet expected=new Planet();
        expected.setId("1TESTGETALLLIST");
        expected.setName("TestGetAllList");

        planetCrudService.create(expected);
        String id =expected.getId();
        expected.setId(id);

        List<Planet> expectedPlanets= Collections.singletonList(expected);
        List <Planet> actualPlanets=planetCrudService.getAll();
        int indexExpectedPlanet = 0;
        for (int i = 0; i < actualPlanets.size();i++) {
            if(actualPlanets.get(i).getId().equals(expected.getId())) {
                indexExpectedPlanet=i;
            }
        }
        Assertions.assertEquals(expectedPlanets.get(0).toString(),actualPlanets.get(indexExpectedPlanet).toString());
    }
}