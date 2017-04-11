/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wormsim.numerics;

import com.wormsim.numerics.formulae.Formula;

/**
 *
 * @author ah810
 */
public abstract class BiFormula extends Formula {
	public BiFormula(String p_name, Formula p_left, Formula p_right) {
		super(p_name);
		left = p_left;
		right = p_right;
	}
	protected final Formula left, right;
}
