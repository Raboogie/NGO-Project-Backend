package com.example.ngoprojectbackend;

import com.example.ngoprojectbackend.model.Event;
import com.example.ngoprojectbackend.repo.EventRepo;
import com.example.ngoprojectbackend.service.EventService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SpringDateEventTest {
    @Autowired
    EventRepo eventRepo;

    @Autowired
    @InjectMocks
    EventService eventService;

    @Test
    void contextLoads() {
    }

    @Test
    public void getEvent() {
        Event event = eventRepo.getOne(1);
        System.out.println("Event eventId is: " + event.getEventId());
    }

    @Test
    public void getAllEvents() {
        System.out.println(eventService.getEvents().toString());
    }

    @Test
    public void createEvent() {
        Event event = new Event(44, "EventNameTest2", "lkdjkl", "kk");

        event.setEventId(44);
        event.setEventName("EventNameTest1");
        event.setCategory("Test category 1");
        event.setLocation("Test Location 1");

        //eventRepo.save(event);
        System.out.println("saved one record in db table...");
        System.out.println(event);

    }

    @Test
    public void deleteEvent() {
        Event event = new Event(44, "EventNameTest2", "lkdjkl", "kk");

        event.setEventId(44);
        event.setEventName("EventNameTest2");
        event.setCategory("Test category 2");
        event.setLocation("Test Location 2");

        //eventRepo.delete(event);
        System.out.println("deleted one record in database table...");
        System.out.println(event);
    }

    @Test
    public void updateEvent() {
        Event event = new Event(1,"kkdj", "kjklj", "kjkj");

        Event eventInfo = new Event(1,"testName", "testCat", "testLoc");

        event.setEventName(eventInfo.getEventName());
        event.setCategory(eventInfo.getCategory());
        event.setLocation(eventInfo.getLocation());
        event.setStartDate(eventInfo.getStartDate());
        event.setEndDate(eventInfo.getEndDate());
        event.setStartTime(eventInfo.getStartTime());
        event.setEndTime(eventInfo.getEndTime());
        event.setAdultTicketPrice(eventInfo.getAdultTicketPrice());
        event.setChildTicketPrice(eventInfo.getChildTicketPrice());

        //Event updatedEvent = eventService.createEvent(event);
    }
}
