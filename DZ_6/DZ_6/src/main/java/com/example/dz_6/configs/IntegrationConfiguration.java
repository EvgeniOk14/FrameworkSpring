package com.example.dz_6.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.GenericTransformer;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.messaging.MessageChannel;
import java.io.File;

@Configuration
public class IntegrationConfiguration
{
    /** в конфигурации необходимо описание 4 бинов: **/

    /** Бин 2 <--- это Канал-1, который идёт сразу за входным блоком **/
    @Bean
    public MessageChannel messageChannelInput()
    {
        return new DirectChannel();
    }

    /** Бин 3 <--- (Transformer) это блок, отвечающий за обработку входящей информации **/
    @Bean
    @Transformer(inputChannel = "messageChannelInput", outputChannel = "messageChannelFileWriter")
    public GenericTransformer<String, String> myTransformer()
    {
        return text -> {return text.toUpperCase();}; // возвращаем то что хотим сделать описанной логикой
    }

    /** Бин 4 <--- это Канал-2, который идёт сразу за блоком Transformer **/
    @Bean
    public MessageChannel messageChannelFileWriter()
    {
        return new DirectChannel();
    }

    /** Бин 5 <---  это блок выхода из интеграции (стоит последним в структуре) **/
    @Bean
    @ServiceActivator(inputChannel = "messageChannelFileWriter") // Эта аннотация указывает на то, что этот компонент будет активироваться для обработки сообщений, поступающих из канала с именем "messageChannelFileWriter".
    public FileWritingMessageHandler myFileWriter() // Этот класс из пакета Spring Integration предназначен для записи сообщений в файл.
    {
        FileWritingMessageHandler handler = new FileWritingMessageHandler(new File("D:/geek brains/FrameworkSpring/DZ_6/DZ_6/src/main/resources/static/messages"));
        handler.setExpectReply(false); //компонент не ожидает ответа после отправки сообщения в файл
        handler.setFileExistsMode(FileExistsMode.APPEND); // добавление новой информации в конец файла
        handler.setAppendNewLine(true); // Устанавливает добавление новой строки после каждой записи в файл, чтобы каждая запись начиналась с новой строки
        return handler;
    }
}
