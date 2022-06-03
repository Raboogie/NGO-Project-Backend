package com.example.ngoprojectbackend.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
//@Table(name="Userview")
public class UserView {
	@Id
	//@Column(name="ID")
	private Integer userViewId;

	//@Column(name=" Event")
	@ManyToOne
	@JoinColumn(name = "Event")
	private Event event;

	//@Column(name=" Location")
	private String Location;

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

}
