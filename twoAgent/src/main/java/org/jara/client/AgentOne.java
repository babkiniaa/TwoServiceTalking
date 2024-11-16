package org.jara.client;

import org.jara.Dto.KeyAndTypeDto;
import org.jara.Dto.MessageAndTypeEncryptDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "AgentTwo", url = "http://localhost:8081")
public interface AgentOne {

    /**
     * На вход принимается открытое сообщение и метод шифрования, получаем зашифрованное сообщение.
     **/
    @PostMapping("/message/encrypt")
    void encrypt(@RequestBody MessageAndTypeEncryptDto messageAndTypeEncrypt);

    /**
     *  На вход принимается зашифрованное сообщение и метод шифрования,
     *  выводится открытое сообщение
     **/
    @PostMapping("/message/get_encrypted_msg")
    void get_encrypted_msg(@RequestBody MessageAndTypeEncryptDto messageAndTypeEncryptDto);

    /**
     *  На вход принимается метод шифрования и открытый ключ от собеседника,
     *  открытый ключ сохраняется на стороне собеседника
     **/
    @PostMapping("/message/get_public_key")
    void get_public_key(@RequestBody KeyAndTypeDto keyAndTypeDto);
}
