package com.company.hibernate.ticket;

import com.company.hibernate.client.Client;
import com.company.hibernate.planet.Planet;
import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

@Table(name = "ticket")
@Entity
public class Ticket {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    @Column
    private Date created_at;

    @Column(name = "client_id")
    @OneToMany(mappedBy = "id")
    private List<Client> client;

    @Column(name = "from_planet_id")
    @OneToMany(mappedBy = "id")
    private List<Planet> fromPlanet;

    @Column(name = "to_planet_id")
    @OneToMany(mappedBy = "id")
    private List<Planet> toPlanet;

    public Ticket() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public List<Client> getClient() {
        return client;
    }

    public void setClient(List<Client> client) {
        this.client = client;
    }

    public List<Planet> getFromPlanet() {
        return fromPlanet;
    }

    public void setFromPlanet(List<Planet> fromPlanet) {
        this.fromPlanet = fromPlanet;
    }

    public List<Planet> getToPlanet() {
        return toPlanet;
    }

    public void setToPlanet(List<Planet> toPlanet) {
        this.toPlanet = toPlanet;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", created_at=" + created_at +
                ", client=" + client +
                ", fromPlanet=" + fromPlanet +
                ", toPlanet=" + toPlanet +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return id == ticket.id && Objects.equals(created_at, ticket.created_at) && Objects.equals(client, ticket.client) && Objects.equals(fromPlanet, ticket.fromPlanet) && Objects.equals(toPlanet, ticket.toPlanet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, created_at, client, fromPlanet, toPlanet);
    }
}
