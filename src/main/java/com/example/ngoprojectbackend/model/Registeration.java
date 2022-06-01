package com.example.ngoprojectbackend.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
@Entity
@Table(name = "Registeration")
public class Registeration implements Serializable {
    @Id
    //@Column(name = "Event_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int RegisterId;

    @Pattern(regexp="(^$|[0-9]{10})")
    @NotBlank
    String phoneNumber;

    @NotBlank
    int AdultQty;

    @NotBlank
    int ChildQty;

    @NotBlank
    String address;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "register_event_table",
    joinColumns = @JoinColumn(name = "event_id"),
    inverseJoinColumns = @JoinColumn(name = "reg_id"))
    private Event event;


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
}
