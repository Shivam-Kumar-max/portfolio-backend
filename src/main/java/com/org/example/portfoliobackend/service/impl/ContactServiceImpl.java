package com.org.example.portfoliobackend.service.impl;

import com.org.example.portfoliobackend.dto.ContactDto;
import com.org.example.portfoliobackend.entity.Contact;
import com.org.example.portfoliobackend.exception.ResourceNotFoundException;
import com.org.example.portfoliobackend.mapper.ContactMapper;
import com.org.example.portfoliobackend.repository.ContactRepository;
import com.org.example.portfoliobackend.service.ContactService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ContactServiceImpl implements ContactService {
    private final ContactRepository contactRepository;

    @Override
    public ContactDto createContact(ContactDto contactDto) {
        Contact contact = ContactMapper.mapToContact(contactDto);
        Contact savedContact = contactRepository.save(contact);
        return ContactMapper.mapToContactDto(savedContact);
    }

    @Override
    public List<ContactDto> getAllContacts() {
        return contactRepository.findAll()
                .stream()
                .map(ContactMapper::mapToContactDto)
                .toList();
    }

    @Override
    public ContactDto getContactById(Long id) {
        Contact contact = contactRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contact message not found with id: " + id));
        return ContactMapper.mapToContactDto(contact);
    }

    @Override
    public void deleteContact(Long id) {
        Contact contact = contactRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contact message not found with id: " + id));
        contactRepository.delete(contact);
    }
}
