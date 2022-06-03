package com.example.ngoprojectbackend.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
@Entity
@Table(name = "Registeration")
public class Registeration implements Serializable {
    @Id
    //@Column(name = "Event_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int RegisterId;

    @NotBlank
    String Fullname;

    String phoneNumber;

    @NotNull
    int AdultQty;

    @NotNull
    int ChildQty;

    @NotBlank
    String address;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "register_event_table",
    joinColumns = @JoinColumn(name = "event_id"),
    inverseJoinColumns = @JoinColumn(name = "reg_id"))
    private Event event;

public Registeration(){}
    public Registeration(int registerId, String fullname, String phoneNumber, int adultQty, int childQty, String address, Event event) {
        RegisterId = registerId;
        Fullname = fullname;
        this.phoneNumber = phoneNumber;
        AdultQty = adultQty;
        ChildQty = childQty;
        this.address = address;
        this.event = event;
    }

    public int getRegisterId() {
        return RegisterId;
    }

    public void setRegisterId(int registerId) {
        RegisterId = registerId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getAdultQty() {
        return AdultQty;
    }

    public void setAdultQty(int adultQty) {
        AdultQty = adultQty;
    }

    public int getChildQty() {
        return ChildQty;
    }

    public void setChildQty(int childQty) {
        ChildQty = childQty;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String getFullname() {
        return Fullname;
    }

    public void setFullname(String fullname) {
        Fullname = fullname;
    }
}
