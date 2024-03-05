package com.example.rss.config;

import com.rometools.rome.feed.synd.SyndEntry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.feed.dsl.Feed;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.integration.transformer.AbstractPayloadTransformer;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

@Configuration
public class IntegrationConfig
{
    /** настраивает интеграционный поток (Integration Flow) в Spring Integration для чтения данных из RSS-ленты и их обработки **/
    @Bean
    public IntegrationFlow feedFlow() throws MalformedURLException {
        return IntegrationFlow.from(Feed.inboundAdapter(new URL("https://lenta.ru/rss"), "news"),
                        e -> e.poller(p -> p.fixedDelay(5000)))
                .transform(extractLinkFromRSS())
                .handle(messageHandler())
                .get();
    }
        /** пояснения к методу feedFlow() **/
        // IntegrationFlow.from() <--- метод настройки интеграционного потока
        // Feed.inboundAdapter(): Этот метод создает адаптер для приема данных из RSS-ленты,
        // принимает URL RSS-ленты и строку-идентификатор (идентификатор канала) в качестве параметров
        // Здесь происходит преобразование данных из RSS-ленты. Метод transform() принимает объект-трансформер,
        // который преобразует входящие сообщения.
        // В данном случае extractLinkFromRSS() - это метод или компонент,
        // который извлекает ссылки из полученных данных
        // handle(messageHandler()) - метод определяет обработчик для обработки преобразованных данных.
        // Обработчик (messageHandler()) может выполнять различные действия, такие как сохранение данных в базу данных,
        // отправка сообщений на другие каналы
        // get(): Этот метод завершает настройку интеграционного потока и возвращает экземпляр IntegrationFlow,
        // который представляет собой настроенный интеграционный поток.
        // e -> e.poller(p -> p.fixedDelay(5000)) - это конфигурация опросника (poller) для интеграционного потока.
        //
        //В контексте Spring Integration, опросник используется для периодического опроса источника данных или канала сообщений. В данном случае fixedDelay(5000) означает, что опросник будет опрашивать источник данных каждые 5000 миллисекунд (5 секунд).
        //
        //Общая структура выражения e -> e.poller(p -> p.fixedDelay(5000)):
        //e -> указывает на конфигурацию опросника для интеграционного потока.
        //e - это объект IntegrationFlowDefinition, представляющий текущий поток.
        //poller() - метод для настройки опросника.
        //p -> - указывает на конфигурацию параметров опросника.
        //fixedDelay(5000) - устанав


        /** Transformer **/
    @Bean
    public AbstractPayloadTransformer<SyndEntry, String> extractLinkFromRSS() {
        return new AbstractPayloadTransformer<SyndEntry, String>() {
            @Override
            protected String transformPayload(SyndEntry payload) {
                return payload.getTitle() + "\n" + payload.getAuthor() + "\n" + payload.getLink();
            }
        };
    }
    /** пояснения к методу extractLinkFromRSS() **/
    // AbstractPayloadTransformer<SyndEntry, String>: Это обобщенный класс,
    // который является базовым классом для трансформеров сообщений.
    // В данном случае он преобразует объекты типа SyndEntry в строки.
    //extractLinkFromRSS(): Это метод, который создает и настраивает трансформер.
    //transformPayload(SyndEntry payload): Это метод, который принимает объект типа SyndEntry в качестве входного параметра
    // и выполняет преобразование этого объекта в строку,
    // в которой содержится информация о заголовке, авторе и ссылке на элемент RSS.



    /** ServiceActivator **/
    @Bean
    public FileWritingMessageHandler messageHandler() {
        FileWritingMessageHandler handler =
                new FileWritingMessageHandler(new File(
                        "D:/geek brains/FrameworkSpring/ДЗ_12/rss/src/main/resources/static/information"));
        handler.setExpectReply(false);
        handler.setFileExistsMode(FileExistsMode.APPEND);
        handler.setAppendNewLine(true);
        handler.setAutoCreateDirectory(true);
        handler.setCharset("UTF8");
        handler.setExpectReply(false);

        return handler;
    }
    /**пояснения к методу messageHandler() **/
    //FileWritingMessageHandler: Это класс из Spring Integration, предназначенный для записи сообщений в файл.
    //messageHandler(): Это метод, который создает и настраивает обработчик сообщений.
    //new File("D:/geek brains/FrameworkSpring/ДЗ_12/rss/src/main/resources/static/information"): Это путь к файлу, в который будут записываться сообщения.
    //setExpectReply(false): Это метод, который указывает, что обработчик не ожидает ответа после отправки сообщения в файл.
    //setFileExistsMode(FileExistsMode.APPEND): Этот метод устанавливает режим поведения при существующем файле. В данном случае сообщения будут добавляться в конец файла.
    //setAppendNewLine(true): Этот метод устанавливает добавление новой строки после каждой записи в файл.
    //setAutoCreateDirectory(true): Этот метод указывает, что если директория для файла не существует, она будет автоматически создана.
    //setCharset("UTF8"): Этот метод устанавливает кодировку символов для записи в файл. В данном случае используется UTF-8.
    //setExpectReply(false): Дублирующий вызов, который указывает, что обработчик не ожидает ответа после отправки сообщения в файл.

}
