package com.automationexercices.apis;

import com.automationexercices.utils.dataReader.PropertyReader;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class Builder {

        private static String  BaseURI = PropertyReader.GetProperty("baseUrlApi");

        private Builder(){
            //Private constructor to prevent instantiation
        }
       public static RequestSpecification getUserManagementRequestSpecification(Map<String, ?> formParams) {

           return new RequestSpecBuilder().setBaseUri(BaseURI)
                   .setContentType(ContentType.URLENC)
                   .addFormParams(formParams)
                   .build();
       }
}









