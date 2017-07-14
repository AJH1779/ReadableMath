/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wormsim.numerics.game;

import com.wormsim.numerics.formula.Formula;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

/**
 *
 * @author ah810
 */
public class Choices {
	@SuppressWarnings("unchecked")
	public Choices(Node p_node, Formula p_formula, int p_player_no) {
		this.node = p_node;
		this.formulas = new Formula[p_player_no];
		Arrays.fill(formulas, p_formula);
		this.choices = new HashMap[p_player_no];
	}

	public Choices(Node p_node, Choices prev, Formula[] p_formula) {
		this.node = p_node;
		this.formulas = p_formula;
		this.choices = Arrays.copyOf(prev.choices, prev.choices.length);
	}
	private final HashMap<String, Double>[] choices;
	private final Formula[] formulas;
	private final Node node;

	public Node getNode() {
		return node;
	}

	public Formula[] getFormulas() {
		return this.formulas;
	}

	public Collection<? extends Choices> resolve() {
		return node.resolve(this);
	}
}
