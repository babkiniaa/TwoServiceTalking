package org.jara.Controller;

import lombok.RequiredArgsConstructor;
import org.jara.Dto.KeyAndTypeDto;
import org.jara.Dto.MessageAndTypeEncryptDto;
import org.jara.Service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/message")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;


    @PostMapping("/save")
    public ResponseEntity<?> saveMessage(@RequestBody Map<String, Object> requestBody){
        String message = (String) requestBody.get("message");
        messageService.saveMessage(message);
        return ResponseEntity.ok("save");
    }

    @GetMapping("/give")
    public String giveMessage(){
        return messageService.getMessage();
    }
    /**
     * На вход принимается открытое сообщение и метод шифрования, получаем зашифрованное сообщение.
     **/
    @PostMapping("/encrypt")
    public String encrypt(@RequestBody MessageAndTypeEncryptDto messageAndTypeEncrypt){
        return messageService.encrypt(messageAndTypeEncrypt);
    }

    /**
     * На вход принимается открытое сообщение и метод шифрования,
     * сообщение шифруется и отправляется на адрес собеседника
     **/
    @PostMapping("/encrypt_and_send")
    public ResponseEntity<?> encrypt_and_send(@RequestBody MessageAndTypeEncryptDto messageAndTypeEncrypt){
        messageService.encrypt_and_send(messageAndTypeEncrypt);
        
        return ResponseEntity.ok("to do");
    }

    /**
     * На вход принимается зашифрованное сообщение и метод шифрования,
     * сообщение отправляется на адрес собеседника.
     **/
    @PostMapping("/send_encrypted_msg")
    public ResponseEntity<?> send_encrypted_msg(@RequestBody MessageAndTypeEncryptDto messageAndTypeEncryptDto){
        messageService.send_encrypted_msg(messageAndTypeEncryptDto);

        return ResponseEntity.ok("to do");
    }
    /**
     *  На вход принимается зашифрованное сообщение и метод шифрования,
     *  выводится открытое сообщение
     **/
    @PostMapping("/get_encrypted_msg")
    public String get_encrypted_msg(@RequestBody MessageAndTypeEncryptDto messageAndTypeEncryptDto){
        return messageService.get_encrypted_msg(messageAndTypeEncryptDto);
    }

    /**
     * На вход принимается метод шифрования,
     * в соответсвии с методом шифрования генерируются необходимые ключи
     **/
    @PostMapping("/generate")
    public ResponseEntity<?> generate(@RequestBody Map<String, Object> requestBody){
        String method = (String) requestBody.get("method");
        messageService.generate(method);

        return ResponseEntity.ok("to do");
    }

    /**
     *  На вход принимается метод шифрования, в
     *  соответсвии с методом шифрования отправляется открытый ключ собеседнику
     **/
    @PostMapping("/send_public_key")
    public ResponseEntity<?> send_public_key(@RequestBody Map<String, Object> requestBody){
        String method = (String) requestBody.get("method");
        messageService.send_public_key(method);

        return ResponseEntity.ok("to do");
    }

    /**
     *  На вход принимается метод шифрования и открытый ключ от собеседника,
     *  открытый ключ сохраняется на стороне собеседника
     **/
    @PostMapping("/get_public_key")
    public ResponseEntity<?> get_public_key(@RequestBody KeyAndTypeDto keyAndTypeDto){
        messageService.get_public_key(keyAndTypeDto);

        return ResponseEntity.ok("to do");
    }


}
