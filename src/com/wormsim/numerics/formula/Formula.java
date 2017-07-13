/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wormsim.numerics.formula;

/**
 *
 * @author ah810
 */
public abstract class Formula {

	/**
	 * Denotes a true constant, one that is independent of context. This would
	 * include things like PI.
	 */
	public static class Constant extends Formula {
		public static final Constant FIVE = new Constant(5);
		public static final Constant FOUR = new Constant(4);
		public static final Constant MINUS_ONE = new Constant(1);
		public static final Constant ONE = new Constant(1);
		public static final Constant ONE_HUNDRED = new Constant(100);
		public static final Constant PI = new Constant(Math.PI);
		public static final Constant TEN = new Constant(10);
		public static final Constant THREE = new Constant(3);
		public static final Constant TWO = new Constant(2);
		public static final Constant ZERO = new Constant(0);

		public Constant(double value) {
			this.value = value;
		}
		private final double value;

		@Override
		public String toString() {
			return Double.toString(value) + " (const)";
		}
	}

	public static class Variable extends Formula {
		public Variable(String symbol, String name) {
			this.symbol = symbol;
			this.name = name;
		}
		private final String name;
		private final String symbol;

		private double min_bounds = Double.NEGATIVE_INFINITY;
		private double max_bounds = Double.POSITIVE_INFINITY;

		public Variable setBounds(double min, double max) {
			if (min > max) {
				throw new IllegalArgumentException("Minimum (" + min
								+ ") is greater than maximum (" + max + ")!");
			}
			this.min_bounds = min;
			this.max_bounds = max;
			return this;
		}

		@Override
		public String toString() {
			return symbol + " (" + name + ")";
		}
	}

	public static class BiFunction extends Formula {
		public BiFunction(Formula l, Formula r) {
			this.l = l;
			this.r = r;
		}
		protected final Formula l;
		protected final Formula r;
	}

	public static class Sum extends BiFunction {
		public Sum(Formula l, Formula r) {
			super(l, r);
		}
	}

	public static class Subtract extends BiFunction {
		public Subtract(Formula l, Formula r) {
			super(l, r);
		}
	}

	public static class Multiply extends BiFunction {
		public Multiply(Formula l, Formula r) {
			super(l, r);
		}
	}

	public static class Divide extends BiFunction {
		public Divide(Formula l, Formula r) {
			super(l, r);
		}
	}
}
