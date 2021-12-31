package com.telecom.numberportability.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "request")
public class Request {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String status;

	@Column(name = "phone_number")
	private String phoneNumber;

	@ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name = "from_organization_id")
	private Organization fromOrganization;
	@ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name = "to_organization_id")
	private Organization toOrganization;

	private Date requestDate ;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Organization getFromOrganization() {
		return fromOrganization;
	}

	public void setFromOrganization(Organization fromOrganization) {
		this.fromOrganization = fromOrganization;
	}

	public Organization getToOrganization() {
		return toOrganization;
	}

	public void setToOrganization(Organization toOrganization) {
		this.toOrganization = toOrganization;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
