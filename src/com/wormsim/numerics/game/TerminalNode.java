/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wormsim.numerics.game;

import com.wormsim.numerics.formula.Formula;

/**
 * Denotes the scoring output for a player that reaches this node.
 *
 * @author ah810
 */
public class TerminalNode extends Node {
	public TerminalNode(String name, Formula outcome) {
		super(name);
		this.outcome = outcome;
	}
	private final Formula outcome;
}
