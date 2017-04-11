/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wormsim.numerics.context;

/**
 *
 * @author ah810
 */
public abstract class Context {
	private static final ImmutableContext GLOBAL = newGlobalInstance();

	private static ImmutableContext newGlobalInstance() {
		BasicContext context = new BasicContext();

		return new ImmutableContext(context);
	}

	public static ImmutableContext getGlobalContext() {
		return GLOBAL;
	}
}
