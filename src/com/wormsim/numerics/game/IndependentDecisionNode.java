/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wormsim.numerics.game;

import java.util.HashMap;

public class IndependentDecisionNode extends Node {

	public IndependentDecisionNode(String var_name, String p_name) {
		super(p_name);
		this.var_name = var_name;
	}
	private final HashMap<Node, String> outcomes = new HashMap<>();
	private final String var_name;

	public IndependentDecisionNode addOutcome(String decision_name, Node outcome) {
		this.outcomes.put(outcome, decision_name);
		return this;
	}

	/**
	 * Returns the name of the decision variable that players reaching this node
	 * use.
	 *
	 * @return
	 */
	public String getVariableName() {
		return var_name;
	}

}
