package org.jara.Entity;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Table
@Entity(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String message;
}
