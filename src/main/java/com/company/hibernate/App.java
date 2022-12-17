package com.company.hibernate;

import com.company.hibernate.client.Client;
import com.company.hibernate.client.ClientCrudService;
import com.company.hibernate.planet.Planet;
import com.company.hibernate.planet.PlanetCrudService;

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
    }
}
