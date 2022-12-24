package com.company.hibernate.ticket;

import com.company.hibernate.client.Client;
import com.company.hibernate.planet.Planet;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Table(name = "ticket")
@Entity
public class Ticket {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column
    private Date created_at;

    @JoinColumn(name = "client_id")
    @ManyToOne
    private Client client;

    @JoinColumn(name = "from_planet_id")
    @OneToOne
    private Planet fromPlanet;

    @JoinColumn(name = "to_planet_id")
    @OneToOne
    private Planet toPlanet;

    public Ticket() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Planet getFromPlanet() {
        return fromPlanet;
    }

    public void setFromPlanet(Planet fromPlanet) {
        this.fromPlanet = fromPlanet;
    }

    public Planet getToPlanet() {
        return toPlanet;
    }

    public void setToPlanet(Planet toPlanet) {
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
