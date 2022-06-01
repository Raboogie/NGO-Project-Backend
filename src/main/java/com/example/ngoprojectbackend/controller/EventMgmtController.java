package com.example.ngoprojectbackend.controller;

import com.example.ngoprojectbackend.exception.ResourceNotFoundException;
import com.example.ngoprojectbackend.model.Event;
import com.example.ngoprojectbackend.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200") // replace with update url when pushed to AWS EC2 server
@RestController
@Transactional
public class EventMgmtController {
    @Autowired
    private EventService eventService;

    // get all Events
    @GetMapping("/getAllEvents")
    public List<Event> EventList() {
        return eventService.getEvents();
    }

    // get Event by id rest api
    @GetMapping("/getEvent/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable int id) throws ResourceNotFoundException {
        Event event = eventService.getEventById(id);

        if (id != event.getEventId()) {
            throw new ResourceNotFoundException("Event id: " + id + "does not exist.");
        } else {
            return ResponseEntity.ok(event);
        }
    }

    // create event rest api
    @PostMapping("/addEvent")
    public Event addEvent(@Valid @RequestBody Event event) {
        return eventService.createEvent(event);
    }

    // update event rest api
    @PutMapping("/updateEvent/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable int id, @RequestBody Event eventInfo) throws ResourceNotFoundException {
        Event event = eventService.getEventById(id);

        if (id != event.getEventId()) {
            throw new ResourceNotFoundException("Event id: " + id + "does not exist.");
        } else {
            event.setEventName(eventInfo.getEventName());
            event.setCategory(eventInfo.getCategory());
            event.setLocation(eventInfo.getLocation());
            event.setStartDate(eventInfo.getStartDate());
            event.setEndDate(eventInfo.getEndDate());
            event.setStartTime(eventInfo.getStartTime());
            event.setEndTime(eventInfo.getEndTime());
            event.setAdultTicketPrice(eventInfo.getAdultTicketPrice());
            event.setChildTicketPrice(eventInfo.getChildTicketPrice());

            Event updatedEvent = eventService.createEvent(event);
            return ResponseEntity.ok(updatedEvent);
        }
    }

    // delete event rest api
    @DeleteMapping("/deleteEvent/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEvent(@PathVariable int id) throws ResourceNotFoundException {
        Event event = eventService.getEventById(id);

        if (id != event.getEventId()) {
            throw new ResourceNotFoundException("Event id: " + id + "does not exist.");
        } else {
            eventService.deleteEvent(event.getEventId());
            Map<String, Boolean> response = new HashMap<>();
            response.put("deleted", Boolean.TRUE);
            return ResponseEntity.ok(response);
        }
    }
}
