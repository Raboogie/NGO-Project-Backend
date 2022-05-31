package com.example.ngoprojectbackend.service;

import com.example.ngoprojectbackend.model.Event;
import com.example.ngoprojectbackend.repo.EventRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EventService {
    @Autowired
    private EventRepo eventRepo;

    public List<Event> getEvents() {
        return eventRepo.findAll();
    }

    public Event getEventById(int id) {
        return eventRepo.findById(id).get();
    }

    public Event createEvent(Event event) {
        eventRepo.save(event);
        return event;
    }

    public void deleteEvent(int id) {
        eventRepo.deleteById(id);
    }
}
