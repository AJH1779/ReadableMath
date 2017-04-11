/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wormsim.numerics.formulae;

import com.wormsim.numerics.context.Context;

/**
 *
 * @author ah810
 */
public abstract class Formula {
	public Formula(String p_name) {
		this.p_name = p_name;
	}
	// TODO: Remove input as a debug string.
	private final String p_name;

	// TODO: Make this more general, allowing for any return type and for that to
	// be handled appropriately.
	public abstract double evaluate(Context con);

	public String getDebugName() {
		return p_name;
	}
}
