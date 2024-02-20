package com.example.aspect_dz_8.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UserActionAspect
{

    /**  Это определение точки среза (pointcut) в аспекте.
     * Точка среза определяет, где именно в коде должны быть применены перехваты (advice).
     * В данном случае, точка среза trackUserAction() определяет места,
     * где должны применяться советы (advice) к методам, которые помечены аннотацией @TrackUserAction.
     **/
    @Pointcut("@annotation(TrackUserAction)")
    public void trackUserAction()
    {

    }

    /** @AfterReturning(value = "trackUserAction()", returning = "result"):
     * Это совет (advice) типа "после успешного возврата".
     * Он указывает, что метод afterReturning должен быть выполнен после возврата значения из метода,
     * на который была применена аннотация @TrackUserAction.

    public void afterReturning(JoinPoint joinPoint, Object result):
    Это реализация метода совета afterReturning.
    Он будет выполнен после возврата значения из метода, помеченного аннотацией @TrackUserAction.
    JoinPoint предоставляет информацию о точке соединения (join point), на которой выполняется совет.
    result - это возвращаемое значение из целевого метода.

    joinPoint.getSignature().getName():
    Этот код получает имя метода, к которому был применен совет.

    joinPoint.getTarget().getClass().getName():
    Этот код получает имя класса, к которому принадлежит метод, к которому был применен совет.

    System.out.println("User action: Method " + methodName + " of class " + className + " called."):
    Этот код просто выводит в консоль сообщение о том, какой метод какого класса был вызван.
    Таким образом, это позволяет отслеживать и регистрировать действия пользователя в приложении.
     **/
    @AfterReturning(value = "trackUserAction()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result)
    {
        String methodName = joinPoint.getSignature().getName(); // <-- доступ к сигнатуре метода (имя метода, типы его параметров и возращаемый тип)
        String className = joinPoint.getTarget().getClass().getName(); // <--- доступ к классу в котором лежит данный метод ()
        System.out.println("User action: Method " + methodName + " of class " + className + " called.");
    }
}

