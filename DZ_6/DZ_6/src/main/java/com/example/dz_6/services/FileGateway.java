package com.example.dz_6.services;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.file.FileHeaders;
import org.springframework.messaging.handler.annotation.Header;

/** Блок 1 (входной блок в интеграционной структуре) **/
@MessagingGateway(defaultRequestChannel = "messageChannelInput") // передаёт Блок 2 (в канал 1 messageChannelInput), стоящий после него в структуре
public interface FileGateway //  адаптер сообщений для записи данных в файл
{
    void writeToFile(@Header(FileHeaders.FILENAME) String filename, String data);
    // @Header(FileHeaders.FILENAME) String filename:
    // Этот параметр указывает на имя файла, в который будут записаны данные.
    // Аннотация @Header используется для указания, что значение этого параметра будет взято из заголовка сообщения из метода writeToFile()
    // FileHeaders.FILENAME указывает на ключ заголовка, содержащий имя файла.
    //String data: Это данные, которые будут записаны в файл.
}
