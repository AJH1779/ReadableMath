/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wormsim.numerics;

/**
 *
 * @author ah810
 */
public class Player {
	public Player(String name) {
		this.name = name;
	}
	private final String name;

	public String getName() {
		return name;
	}

	public String toString() {
		return "Player: " + name + " (" + super.toString() + ").";
	}
}
