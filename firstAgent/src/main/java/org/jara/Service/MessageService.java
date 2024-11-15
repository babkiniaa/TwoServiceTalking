package org.jara.Service;

import lombok.RequiredArgsConstructor;
import org.jara.Dto.Key.KeyDto;
import org.jara.Dto.KeyAndTypeDto;
import org.jara.Dto.MessageAndTypeEncryptDto;
import org.jara.Entity.key.Key;
import org.jara.Mapper.KeyMapper;
import org.jara.Mapper.MessageMapper;
import org.jara.Repository.KeyRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.crypto.*;
import java.security.*;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class MessageService {

    private String message;
    private final MessageService messageService;
    private final MessageMapper messageMapper;
    private final KeyRepository keyRepository;
    private final KeyMapper keyMapper;
    private HashMap<String, Consumer<KeyAndTypeDto>> installKey = new HashMap<>();
    private HashMap<String, Runnable> generateKey = new HashMap<>();
    private HashMap<String, Runnable> sendingKey = new HashMap<>();
    private HashMap<String, Function<String, String>> encrypt = new HashMap<>();
    private HashMap<String, Function<String, String>> deEncrypt = new HashMap<>();

    {
        installKey.put("Replace", this::getKeyPermutationOrReplacementCipher);
        installKey.put("Simmetric", this::getKeySimmetric);
        installKey.put("Asimmetric", this::getKeyAsimmetric);

        generateKey.put("Replace", this::generatePermutationOrReplacementCipher);
        generateKey.put("Simmetric", this::generateAESKey);
        generateKey.put("Asimmetric", this::generateKeyPair);

        sendingKey.put("Replace", this::sendKeyPermutationOrReplacementCipher);
        sendingKey.put("Simmetric", this::sendKeySimetric);
        sendingKey.put("Asimmetric", this::sendKeyAssimetric);

        encrypt.put("Replace", this::encryptPermutationOrReplacementCipher);
        encrypt.put("Simmetric", this::symmetricEncrypt);
        encrypt.put("Asimmetric", this::assimitricEncrypt);

        deEncrypt.put("Replace", this::deEncryptPermutationOrReplacementCipher);
        deEncrypt.put("Simmetric", this::symmetricDecrypt);
        deEncrypt.put("Asimmetric", this::assimitricDecrypt);
    }

    public void saveMessage(String messagein){
        message = messagein;
    }

    public String getMessage(){
        return message;
    }

    public String encrypt(MessageAndTypeEncryptDto messageAndTypeEncryptDto) {
        message = encrypt.get(messageAndTypeEncryptDto.getType()).apply(messageAndTypeEncryptDto.getMessage());

        return message;
    }

    public void encrypt_and_send(MessageAndTypeEncryptDto messageAndTypeEncryptDto){
        encrypt.get(messageAndTypeEncryptDto.getType()).apply(messageAndTypeEncryptDto.getMessage());
        //Тут будет отправка на клиента сообщения
    }

    public void send_encrypted_msg(MessageAndTypeEncryptDto messageAndTypeEncryptDto){
        //Тут будет отправка на клиента сообщения и метода
    }

    public String get_encrypted_msg(MessageAndTypeEncryptDto messageAndTypeEncryptDto){
        message = deEncrypt.get(messageAndTypeEncryptDto.getType()).apply(messageAndTypeEncryptDto.getMessage());

        return message;
    }

    public void generate(String method){
        generateKey.get(method).run();
    }

    public void send_public_key(String method){
        sendingKey.get(method).run();
    }

    public void get_public_key(KeyAndTypeDto keyAndTypeDto){
        installKey.get(keyAndTypeDto.getMethod()).accept(keyAndTypeDto);
    }


    /**
     * Шифр перестановки или замены Генератор Установка ключа
     */

    private void getKeyPermutationOrReplacementCipher(KeyAndTypeDto substitutionMap){
        KeyDto keyDto = keyMapper.keyDtoToKey(keyRepository.findById(1L).get());
        keyDto.setSubstitutionMap(substitutionMap.getSubstitutionMap());
        keyRepository.delete(keyRepository.findById(1L).get());
        keyRepository.save(keyMapper.keyToKeyDto(keyDto));
    }

    /**
     * Шифр симметричный Установка ключа
     */
    private void getKeySimmetric(KeyAndTypeDto secretKey){
        KeyDto keyDto = keyMapper.keyDtoToKey(keyRepository.findById(1L).get());
        keyDto.setSecretKey(secretKey.getSecretKey());
        keyRepository.delete(keyRepository.findById(1L).get());
        keyRepository.save(keyMapper.keyToKeyDto(keyDto));
    }

    /**
     * Шифр ассимметричный Установка ключа
     */
    private void getKeyAsimmetric(KeyAndTypeDto publicKey){
        KeyDto keyDto = keyMapper.keyDtoToKey(keyRepository.findById(1L).get());
        keyDto.setPublicKey(publicKey.getPublicKey());
        keyRepository.delete(keyRepository.findById(1L).get());
        keyRepository.save(keyMapper.keyToKeyDto(keyDto));
    }

    /**
     * Шифр Симметричный Отправка ключа
     */
    private void sendKeySimetric(){
        SecretKey secretKey = keyMapper.keyDtoToKey(keyRepository.findById(1L).get()).getSecretKey();
        //Отправка ключа через клиент
    }

    /**
     * Шифр Ассимитричный Отправка ключа
     */
    private void sendKeyAssimetric(){
        PublicKey publicKey = keyMapper.keyDtoToKey(keyRepository.findById(1L).get()).getPublicKey();
        //Отправка ключа через клиент
    }

    /**
     * Шифр перестановки или замены Генератор Отправка ключа
     */
    private void sendKeyPermutationOrReplacementCipher(){
        Map<Character, Character> substitutionMap = keyMapper.keyDtoToKey(keyRepository.findById(1L).get()).getSubstitutionMap();;
        //Отправка ключа через клиент
    }

    /**
     * Шифр перестановки или замены Генератор ключа
     */
    private void generatePermutationOrReplacementCipher(){
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
    private String encryptPermutationOrReplacementCipher(String message){
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
    private String deEncryptPermutationOrReplacementCipher(String message){
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

    /**
     * Шифр Симметричный Шифровки
     */
    private String symmetricEncrypt(String message) {
        SecretKey key = keyRepository.findById(1L).get().getSecretKey();
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        }
        try {
            cipher.init(Cipher.ENCRYPT_MODE, key);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }

        byte[] encryptedBytes = null;
        try {
            encryptedBytes = cipher.doFinal(message.getBytes());
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        }

        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    /**
     * Шифр Симметричный Расшифр
     */
    private String symmetricDecrypt(String message)  {
        SecretKey key = keyRepository.findById(1L).get().getSecretKey();
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        }
        try {
            cipher.init(Cipher.DECRYPT_MODE, key);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }

        byte[] encryptedBytes = Base64.getDecoder().decode(message);
        byte[] decryptedBytes = null;
        try {
            decryptedBytes = cipher.doFinal(encryptedBytes);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        }

        return new String(decryptedBytes);
    }

    /**
     * Шифр Симметричный Генератор Ключа
     */
    private void generateAESKey() {
        KeyGenerator keyGenerator = null;
        try {
            keyGenerator = KeyGenerator.getInstance("AES");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        keyGenerator.init(128);
        Key key = keyRepository.findById(1L).get();
        SecretKey secretKey = keyGenerator.generateKey();
        keyRepository.delete(key);
        key.setSecretKey(secretKey);
        keyRepository.save(key);
    }

    /**
     * Шифр Ассимметричный Шифровки
     */
    private String assimitricEncrypt(String message) {
        PublicKey publicKey = keyRepository.findById(1L).get().getPublicKey();
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        }
        try {
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }

        byte[] encryptedData = null;
        try {
            encryptedData = cipher.doFinal(message.getBytes());
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        }

        return Base64.getEncoder().encodeToString(encryptedData);
    }

    /**
     * Шифр Ассимметричный Расшифр
     */
    private String assimitricDecrypt(String message) {
        PrivateKey privateKey = keyRepository.findById(1L).get().getPrivateKey();
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        }
        try {
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }

        byte[] decodedData = Base64.getDecoder().decode(message);
        byte[] decryptedData = null;
        try {
            decryptedData = cipher.doFinal(decodedData);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        }

        return new String(decryptedData);
    }

    /**
     * Шифр Симметричный Генератор Ключа
     */
    private void generateKeyPair() {
        KeyPairGenerator keyPairGenerator = null;
        try {
            keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        Key key = keyRepository.findById(1L).get();
        keyRepository.delete(key);
        key.setPrivateKey(keyPair.getPrivate());
        key.setPublicKey(keyPair.getPublic());
        keyRepository.save(key);

    }

}

