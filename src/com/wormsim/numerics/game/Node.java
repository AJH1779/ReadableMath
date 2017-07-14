/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wormsim.numerics.game;

import java.util.Collection;

/**
 *
 * @author ah810
 */
public abstract class Node {
	public Node(String name) {
		this.name = name;
	}

	private final String name;

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + ": " + name;
	}

	public abstract Collection<? extends Choices> resolve(Choices c);
}
