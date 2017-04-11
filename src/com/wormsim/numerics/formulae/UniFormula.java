/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wormsim.numerics.formulae;

public abstract class UniFormula extends Formula {
	public UniFormula(String p_input, Formula p_param) {
		super(p_input);
		this.param = p_param;
	}
	protected final Formula param;
}
