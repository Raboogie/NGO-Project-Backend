package com.example.ngoprojectbackend.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "Event")
public class Event implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int eventId;

    @NotNull
    //@Column(name = "Event")
    @Size(min = 2, message = "Event name should have at least 2 characters")
    String eventName;

    @NotNull
    //@Column(name = "Category")
    @Size(min = 2, message = "category should have at least 2 characters")
    String category;

    @NotNull
    //@Column(name = "Location")
    @Size(min = 2, message = "location should have at least 2 characters")
    String location;

    @NotNull
    //@Column(name = "Start Date")
    LocalDate startDate;

    // Event end date
    //@Column(name = "End Date")
    LocalDate endDate;

    // Event start time
    //@Column(name = "Start Time")
    LocalTime startTime;

    // Event end time
    //@Column(name = "End Time")
    LocalTime endTime;

    //@Column(name = "Adult Ticket Price")
    double adultTicketPrice;

    //@Column(name = "Child Ticket Price")
    double childTicketPrice;

    public int getEventId() {
        return eventId;
    }

    public Event() {
    }

    public Event(int eventId, String eventName, String category, String location) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.category = category;
        this.location = location;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public double getAdultTicketPrice() {
        return adultTicketPrice;
    }

    public void setAdultTicketPrice(double adultTicketPrice) {
        this.adultTicketPrice = adultTicketPrice;
    }

    public double getChildTicketPrice() {
        return childTicketPrice;
    }

    public void setChildTicketPrice(double childTicketPrice) {
        this.childTicketPrice = childTicketPrice;
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventId=" + eventId +
                ", eventName='" + eventName + '\'' +
                ", category='" + category + '\'' +
                ", location='" + location + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", adultTicketPrice=" + adultTicketPrice +
                ", childTicketPrice=" + childTicketPrice +
                '}';
    }
}
