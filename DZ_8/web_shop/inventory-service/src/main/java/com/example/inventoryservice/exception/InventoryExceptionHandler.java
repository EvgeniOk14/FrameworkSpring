package com.example.inventoryservice.exception;

import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class InventoryExceptionHandler
{

    @ExceptionHandler(InsufficientStockException.class)
    public String insufficientStockHandler(InsufficientStockException ex) {
        // Обработка исключения InsufficientStockException
        // Например, можно добавить код для вывода сообщения об ошибке или перенаправления на страницу ошибки
        return "error-page";
    }


}
