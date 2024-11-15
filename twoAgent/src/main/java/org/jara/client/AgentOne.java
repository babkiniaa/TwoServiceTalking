package org.jara.client;

import org.jara.Dto.KeyAndTypeDto;
import org.jara.Dto.MessageAndTypeEncryptDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "AgentTwo", url = "http://localhost:8081")
public interface AgentOne {

    /**
     * На вход принимается открытое сообщение и метод шифрования, получаем зашифрованное сообщение.
     **/
    @GetMapping("/encrypt")
    String encrypt(@RequestBody MessageAndTypeEncryptDto messageAndTypeEncrypt);

    /**
     *  На вход принимается зашифрованное сообщение и метод шифрования,
     *  выводится открытое сообщение
     **/
    @GetMapping("/get_encrypted_msg")
    String get_encrypted_msg(@RequestBody MessageAndTypeEncryptDto messageAndTypeEncryptDto);

    /**
     *  На вход принимается метод шифрования и открытый ключ от собеседника,
     *  открытый ключ сохраняется на стороне собеседника
     **/
    @GetMapping("/get_public_key")
    ResponseEntity<?> get_public_key(@RequestBody KeyAndTypeDto keyAndTypeDto);
}
