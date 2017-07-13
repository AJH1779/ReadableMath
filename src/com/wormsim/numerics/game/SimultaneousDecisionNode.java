/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wormsim.numerics.game;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.function.DoublePredicate;
import java.util.function.Predicate;

public class SimultaneousDecisionNode extends Node {
	public SimultaneousDecisionNode(String var_name, String p_name) {
		super(p_name);
	}
	private final ArrayList<String> choices = new ArrayList<>();
	private Node default_outcome;
	private final ArrayList<SimpleEntry<Object, Node>> outcomes = new ArrayList<>();

	public SimultaneousDecisionNode addChoice(String name) {
		this.choices.add(name);
		return this;
	}

	public SimultaneousDecisionNode addDefaultOutcome(Node outcome) {
		this.default_outcome = outcome;
		return this;
	}

	public SimultaneousDecisionNode addIndependentOutcome(
					DoublePredicate predicate,
					Node outcome) {
		this.outcomes.add(new SimpleEntry<>(predicate, outcome));
		return this;
	}

	public SimultaneousDecisionNode addOutcome(Predicate<double[]> predicate,
																						 Node outcome) {
		this.outcomes.add(new SimpleEntry<>(predicate, outcome));
		return this;
	}
}
