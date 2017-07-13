/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wormsim.numerics.game;

import com.wormsim.numerics.formula.Formula;
import java.util.HashMap;

public class NaturalNode extends Node {
	public NaturalNode(String p_name) {
		super(p_name);
	}
	private final HashMap<Node, Formula> outcomes = new HashMap<>();

	public NaturalNode addOutcome(Formula weighting, Node node) {
		this.outcomes.put(node, weighting);
		return this;
	}
}
