/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wormsim.numerics;

import com.wormsim.numerics.formulae.Formula;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author ah810
 */
public abstract class MultiFormula extends Formula {
	public MultiFormula(String p_input, Formula... p_params) {
		super(p_input);
		this.params = Collections.unmodifiableList(Arrays.asList(p_params));
	}
	protected final List<Formula> params;
}
