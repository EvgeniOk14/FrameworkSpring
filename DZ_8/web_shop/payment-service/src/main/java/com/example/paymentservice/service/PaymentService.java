package com.example.paymentservice.service;

import com.example.paymentservice.exception.PaymentFailedException;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class PaymentService
{

    public void processPayment(Long orderId, double amount)
    {
        boolean transactionSuccess = processPaymentTransaction(orderId, amount);

        if (!transactionSuccess)
        {
            throw new PaymentFailedException("Payment processing failed for order id: " + orderId);
        }
    }

    private boolean processPaymentTransaction(Long orderId, double amount)
    {
        // Пример логики выполнения платежной транзакции
        // Здесь должен быть код для взаимодействия с платежным шлюзом или внешней системой оплаты
        // Например, вызов API платежной системы, обработка ответа и возврат результата

        // Здесь просто генерируем случайное булевое значение для имитации успешной или неуспешной транзакции
        Random random = new Random();
        return random.nextBoolean(); // Возвращаем случайное булевое значение
    }
}
