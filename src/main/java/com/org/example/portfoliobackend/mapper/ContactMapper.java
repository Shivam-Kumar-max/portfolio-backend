package com.org.example.portfoliobackend.mapper;

import com.org.example.portfoliobackend.dto.ContactDto;
import com.org.example.portfoliobackend.entity.Contact;

public class ContactMapper {

    public static ContactDto mapToContactDto(Contact contact){
        return new ContactDto(
                contact.getId(),
                contact.getFirstName(),
                contact.getLastName(),
                contact.getMessage(),
                contact.getEmail(),
                contact.getSubject()
        );
    }

    public static Contact mapToContact(ContactDto contactDto){
        return new Contact(
                contactDto.getId(),
                contactDto.getFirstName(),
                contactDto.getLastName(),
                contactDto.getMessage(),
                contactDto.getEmail(),
                contactDto.getSubject()
        );
    }
}

