package com.example.aspect_dz_8.aspects;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) // указывает, что аннотация TrackUserAction будет доступна во время выполнения программы, что позволяет использовать ее с помощью механизмов рефлексии
@Target(ElementType.METHOD) // указывает, что аннотацию TrackUserAction можно применять только к методам.
public @interface TrackUserAction
{

}

