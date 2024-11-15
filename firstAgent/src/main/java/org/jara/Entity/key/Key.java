package org.jara.Entity.key;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Map;

@Setter
@Getter
@Table
@Entity(name = "keys")
public class Key {
    @Id
    private long id = 1L;

    private Map<Character, Character> substitutionMap;

    private SecretKey secretKey;

    private PrivateKey privateKey;

    private PublicKey publicKey;
}
