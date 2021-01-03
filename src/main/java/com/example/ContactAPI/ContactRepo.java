package com.example.ContactAPI;

import org.springframework.data.jpa.repository.JpaRepository;

//Default repo to handle the data storage for our contact objects
public interface ContactRepo extends JpaRepository<Contact, Long> {

}
