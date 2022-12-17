package com.company.hibernate.ticket;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Objects;

@Table(name = "ticket")
@Entity
public class Ticket {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    @Column
    private Date created_at;

    @Column
    private int client_id;

    @Column
    private String from_planet_id;

    @Column
    private String to_planet_id;

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

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public String getFrom_planet_id() {
        return from_planet_id;
    }

    public void setFrom_planet_id(String from_planet_id) {
        this.from_planet_id = from_planet_id;
    }

    public String getTo_planet_id() {
        return to_planet_id;
    }

    public void setTo_planet_id(String to_planet_id) {
        this.to_planet_id = to_planet_id;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", created_at=" + created_at +
                ", client_id=" + client_id +
                ", from_planet_id=" + from_planet_id +
                ", to_planet_id=" + to_planet_id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return id == ticket.id && client_id == ticket.client_id && Objects.equals(created_at, ticket.created_at) && Objects.equals(from_planet_id, ticket.from_planet_id) && Objects.equals(to_planet_id, ticket.to_planet_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, created_at, client_id, from_planet_id, to_planet_id);
    }
}
