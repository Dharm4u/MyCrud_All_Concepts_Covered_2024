package com.example.demo.Exception;
// this is a specific class to handle the exception but I wnt to handle the exception in a global manner  for that i will create a global exception class
public class ResourceNotFoundException extends RuntimeException {// herer i have createa a custom esception and i am extendinf runtime exception , because it is unchecked exception and i want to make it as child class of runtime exception which is a parent exception for all the unchecked exceptions
    private String ResourceName; //database table name
    private String FieldName; //database column name
    private Long FeldValue; // content of that perticar column
    // so these 3 thing i want to sent to the client /user whenver he/she gives invalid input tp fetch the data from database


    public  ResourceNotFoundException(String resourceName, String fieldName, Long feldValue) {
        super(String.format("%s not found with %s: '%s'", resourceName, fieldName, feldValue)); //this line %s will replace values it will become  Post not  found with id : -5
        ResourceName = resourceName;
        FieldName = fieldName;
        FeldValue = feldValue;
    }


}
