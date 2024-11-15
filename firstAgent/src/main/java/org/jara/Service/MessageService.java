package org.jara.Service;

import org.jara.Dto.Key.KeyDto;
import org.jara.Dto.KeyAndTypeDto;
import org.jara.Dto.MessageAndTypeEncryptDto;
import org.jara.Mapper.KeyMapper;
import org.jara.Mapper.MessageMapper;
import org.jara.Repository.KeyRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class MessageService {

    private MessageMapper messageMapper;
    private KeyRepository keyRepository;
    private KeyMapper keyMapper;
    private HashMap<String, Function<String, ?>> toDoMap = new HashMap<>();

    public String encrypt(MessageAndTypeEncryptDto messageAndTypeEncryptDto) {
        return encryptPermutationOrReplacementCipher(messageAndTypeEncryptDto.getMessage());
    }

    public void encrypt_and_send(MessageAndTypeEncryptDto messageAndTypeEncryptDto){
        messageAndTypeEncryptDto.setMessage(encryptPermutationOrReplacementCipher(messageAndTypeEncryptDto.getMessage()));
        //Тут будет отправка на клиента сообщения
    }

    public void send_encrypted_msg(MessageAndTypeEncryptDto messageAndTypeEncryptDto){
        messageAndTypeEncryptDto.setMessage(deEncryptPermutationOrReplacementCipher(messageAndTypeEncryptDto.getMessage()));
        //Тут будет отправка на клиента сообщения
    }

    public String get_encrypted_msg(MessageAndTypeEncryptDto messageAndTypeEncryptDto){
        messageAndTypeEncryptDto.setMessage(encryptPermutationOrReplacementCipher(messageAndTypeEncryptDto.getMessage()));

        return messageAndTypeEncryptDto.getMessage();
    }

    public void generate(String method){
        generatePermutationOrReplacementCipher();
    }

    public void send_public_key(String method){
        sendKeyPermutationOrReplacementCipher();
    }

    public void get_public_key(KeyAndTypeDto keyAndTypeDto){
        getKeyPermutationOrReplacementCipher(keyAndTypeDto);
    }


    /**
     * Шифр перестановки или замены Генератор Установка ключа
     */
    public void getKeyPermutationOrReplacementCipher(KeyAndTypeDto keyAndTypeDto){
        KeyDto keyDto = keyMapper.keyDtoToKey(keyRepository.findById(1L).get());
        keyDto.setSubstitutionMap(keyAndTypeDto.getSubstitutionMap());
        keyRepository.delete(keyRepository.findById(1L).get());
        keyRepository.save(keyMapper.keyToKeyDto(keyDto));
    }

    /**
     * Шифр перестановки или замены Генератор Отправка ключа
     */
    public void sendKeyPermutationOrReplacementCipher(){
        Map<Character, Character> substitutionMap = new HashMap<>();
        KeyDto keyDto = keyMapper.keyDtoToKey(keyRepository.findById(1L).get());
        //Отправка ключа через клиент
    }

    /**
     * Шифр перестановки или замены Генератор ключа
     */
    public void generatePermutationOrReplacementCipher(){
        Map<Character, Character> substitutionMap = new HashMap<>();
        KeyDto keyDto = keyMapper.keyDtoToKey(keyRepository.findById(1L).get());

        substitutionMap.put('H', 'Z');
        substitutionMap.put('E', 'Y');
        substitutionMap.put('L', 'X');
        substitutionMap.put('O', 'W');
        keyDto.setSubstitutionMap(substitutionMap);
        keyRepository.delete(keyRepository.findById(1L).get());
        keyRepository.save(keyMapper.keyToKeyDto(keyDto));
    }

    /**
     * Шифр перестановки или замены Шифр
     */
    public String encryptPermutationOrReplacementCipher(String message){
        KeyDto keyDto = keyMapper.keyDtoToKey(keyRepository.findById(1L).get());
        StringBuilder encryptedText = new StringBuilder();

        for (char c : message.toCharArray()) {
            if (keyDto.getSubstitutionMap().containsKey(c)) {
                encryptedText.append(keyDto.getSubstitutionMap().get(c));
            } else {
                encryptedText.append(c);
            }
        }

        return encryptedText.toString();
    }

    /**
     * Шифр перестановки или замены Расшифр
     */
    public String deEncryptPermutationOrReplacementCipher(String message){
        KeyDto keyDto = keyMapper.keyDtoToKey(keyRepository.findById(1L).get());
        Map<Character, Character> reverseMap = new HashMap<>();
        for (Map.Entry<Character, Character> entry : keyDto.getSubstitutionMap().entrySet()) {
            reverseMap.put(entry.getValue(), entry.getKey());
        }

        StringBuilder decryptedText = new StringBuilder();

        for (char c : message.toCharArray()) {
            if (reverseMap.containsKey(c)) {
                decryptedText.append(reverseMap.get(c));
            } else {
                decryptedText.append(c);
            }
        }

        return decryptedText.toString();
    }
}

