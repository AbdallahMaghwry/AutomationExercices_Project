package com.automationexercices.apis;

import com.automationexercices.utils.dataReader.PropertyReader;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class Builder {
    //Builder pattern Call kza 7aga wara b3d 2le howa manily fluent pattern 2le howa rest Assured implemented lw7do bl4kl da

        private static String  BaseURI = PropertyReader.GetProperty("baseUrlApi");

        private Builder(){
            //Private constructor to prevent instantiation
        }
        //build request specification -> user management APi 2l hya b login w register w delete user details get user account details
       public static RequestSpecification getUserManagementRequestSpecification(Map<String, ?> formParams) {

           return new RequestSpecBuilder().setBaseUri(BaseURI)
                   .setContentType(ContentType.URLENC)
                   .addFormParams(formParams)
                   .build();

                /*
                1/bb3t 2lform bta3ty as a MAP 3la 7asab el parameters elly ana 3ayz a3ml register w login w delete user details w get user account details
                 2/setBaseUri(BaseURI) -> 3shan a7dd el base URI bta3ty elly hya mawgooda fe el properties file
                 3/addFormParams(formParams) -> 3shan a7dd el form parameters bta3ty elly hya el body bta3 el request w hya kza parameter bta3 register w login w delete user details w get user account details
                  4/setContentType(ContentType.URLENC) -> 3shan a7dd el content type bta3 el request bta3ty elly hya URLENC 3shan
                 5/build ->34an ybd2 y3ml 2le ana 2oltelo 3leh
                 */

       }
}









