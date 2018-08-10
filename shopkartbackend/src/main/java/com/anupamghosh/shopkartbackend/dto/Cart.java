package com.anupamghosh.shopkartbackend.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Cart implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToOne
	//@JoinColumn(name = "uid")
	private User user;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	
	/*-------- Normal Case --------*/
	
	/*@Column(name = "user_id")
	private int userid;
	
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}*/
	
	@Column(name = "grand_total")
	private double grandTotal;
	@Column(name = "cart_lines")
	private int cartLines;
	
	
	
	//Getters and Setters
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getGrandTotal() {
		return grandTotal;
	}
	public void setGrandTotal(double grandTotal) {
		this.grandTotal = grandTotal;
	}
	public int getCartLines() {
		return cartLines;
	}
	public void setCartLines(int cartLines) {
		this.cartLines = cartLines;
	}
	
	
	
	
	@Override
	public String toString() {
		return "Cart [id=" + id + ", userid=" + user + ", grandTotal=" + grandTotal + ", cartLines=" + cartLines
				+ "]";
	}
	
}
