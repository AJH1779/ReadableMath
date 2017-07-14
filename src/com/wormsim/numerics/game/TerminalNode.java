/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wormsim.numerics.game;

import com.wormsim.numerics.formula.Formula;
import com.wormsim.numerics.formula.Formula.Multiply;
import java.util.Collection;
import java.util.Collections;

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

	@Override
	public Collection<? extends Choices> resolve(Choices p_c) {
		Formula[] old_formulas = p_c.getFormulas();
		Formula[] new_formulas = old_formulas.clone();
		for (int i = 0; i < new_formulas.length; i++) {
			new_formulas[i] = new Multiply(old_formulas[i], new_formulas[i]);
		}
		return Collections.singleton(new Choices(this, p_c, new_formulas));
	}
}
