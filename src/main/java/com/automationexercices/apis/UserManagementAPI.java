package com.automationexercices.apis;

import com.automationexercices.utils.Logs.LogsManager;
import com.automationexercices.validations.Verification;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

public class UserManagementAPI {
    RequestSpecification requestSpecification;
    Response response;
    Verification Verification ;
    public UserManagementAPI(){
        requestSpecification= RestAssured.given();
        Verification = new Verification();//m4 m basy gwah 7aga 34an ana 3mlo 3ndo fady
    }

    //endpoint
    private static final String createAccount_endpoint ="/createAccount";
    private static final String deleteAccount_endpoint ="/deleteAccount";


    @Step("Create new account with full details by API Request")
    public UserManagementAPI createRegisterUserAccount(String name, String email, String password,String title, String birth_date, String birth_month, String birth_year, String firstname, String lastname,
                                                       String company, String address1, String address2, String country, String zipcode, String state, String city, String mobile_number)
    {

        Map<String, String> formParams = new  HashMap<>();
                formParams.put("name", name);
                formParams.put("email", email);
                formParams.put("password", password);
                formParams.put("title", title);
                formParams.put("birth_date", birth_date);
                formParams.put("birth_month", birth_month);
                formParams.put("birth_year", birth_year);
                formParams.put("firstname", firstname);
                formParams.put("lastname", lastname);
                formParams.put("company", company);
              formParams.put("address1", address1);
                formParams.put("address2", address2);
                formParams.put("country", country);
                formParams.put("zipcode", zipcode);
                formParams.put("state", state);
                formParams.put("city", city);
                formParams.put("mobile_number", mobile_number);
        response = requestSpecification.spec(Builder.getUserManagementRequestSpecification(formParams))
                    .post(createAccount_endpoint);
        LogsManager.Info(response.asPrettyString());
        return this;
    }


    @Step("Create new account with minimal details by API Request")
    public UserManagementAPI createRegisterUserAccount(String name, String email, String password,String firstname, String lastname)
    {
        Map<String, String> formParams = new  HashMap<>();
        formParams.put("name", name);
        formParams.put("email", email);
        formParams.put("password", password);
        formParams.put("title", "Mr");
        formParams.put("birth_date", "12");
        formParams.put("birth_month", "May");
        formParams.put("birth_year", "2001");
        formParams.put("firstname", firstname);
        formParams.put("lastname", lastname);
        formParams.put("company", "company");
        formParams.put("address1", "address1");
        formParams.put("address2", "address2");
        formParams.put("country", "country");
        formParams.put("zipcode", "zipcode");
        formParams.put("state", "state");
        formParams.put("city", "city");
        formParams.put("mobile_number","mobile_number");
        response = requestSpecification.spec(Builder.getUserManagementRequestSpecification(formParams))
                .post(createAccount_endpoint);
        LogsManager.Info(response.asPrettyString());
        return this;
    }




    @Step("Delete User Account By API Layer")
    public UserManagementAPI  deleteUserAccount(String email,String password ){
         Map<String, String> formParams = new  HashMap<>();
         formParams.put("email",email);
         formParams.put("password",password);
        response = requestSpecification.spec(Builder.getUserManagementRequestSpecification(formParams))
                .delete(deleteAccount_endpoint);
        LogsManager.Info(response.asPrettyString());
        return this;
    }


    //Validation
    @Step("Verify that the account is created successfully")
    public UserManagementAPI verifyAccountCreatedSuccessfully(){

    Verification.isEquals(response.jsonPath().get("message"),"User created!",
            "User is not created successful");

        return this;
    }
    @Step("Verify that the account is deleted successfully")
    public UserManagementAPI verifyAccountDeletedSuccessfully(){

        Verification.isEquals(response.jsonPath().get("message"),"Account deleted!",
                "User is not deleted successful");

        return this;
    }

}
