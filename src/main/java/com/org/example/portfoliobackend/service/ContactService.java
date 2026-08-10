package com.org.example.portfoliobackend.service;

import com.org.example.portfoliobackend.dto.ContactDto;

import java.util.List;

public interface ContactService {
    ContactDto createContact(ContactDto contactDto);
    List<ContactDto> getAllContacts();
    ContactDto getContactById(Long id);
    void deleteContact(Long id);
}
