//package com.diverger.movies;
//
//import com.diverger.movies.completeSearch.PersonCompleteSearch;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//@SpringBootApplication
//public class PersonServiceMain implements CommandLineRunner {
//    @Autowired
//    private PersonCompleteSearch personCompleteSearch;
//
//    public static void main(String[] args) {
//        SpringApplication.run(PersonServiceMain.class, args);
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        personCompleteSearch.testFetchDataByIndexAndGetPersonInfoByName();
//    }
//
//}