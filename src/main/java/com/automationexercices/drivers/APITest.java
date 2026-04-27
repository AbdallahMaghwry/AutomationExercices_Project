package com.automationexercices.drivers;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)//Retention policy specifies how long annotations are to be retained. RUNTIME is used when the annotation should be available at runtime....Retention policy byt7dd lma 2lruntime t7sl
@Target({ElementType.METHOD,ElementType.TYPE})//Target specifies where annotations can be applied. Here, it's applied to both methods and types (classes)......hna ana b2olo han7otha 3la eh
public @interface APITest {
}
