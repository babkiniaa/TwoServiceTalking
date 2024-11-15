package org.jara.Entity.key;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
@Table
@Entity(name = "keys")
public class Key {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Map<Character, Character> substitutionMap;
}
