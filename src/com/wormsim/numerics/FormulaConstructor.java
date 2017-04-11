/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wormsim.numerics;

import com.wormsim.numerics.formulae.Formula;
import com.wormsim.numerics.formulae.UniFormula;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author ah810
 */
public final class FormulaConstructor {
	private static final Logger LOG = Logger.getLogger(FormulaConstructor.class
					.getName());

	public FormulaConstructor() {
		throw new UnsupportedOperationException("Not Yet Implemented");
	}
	private final ArrayList<Check<Formula>> checks;
	private final String comment_string;

	private Formula interpret(final String p_formula,
														final HashMap<String, Formula> p_funcs) {
		HashMap<String, Formula> funcs = p_funcs == null
						? new HashMap<>()
						: p_funcs;
		String[] formula = new String[]{p_formula.trim()};

		while (process(formula, funcs)) {
			LOG.finer(formula[0]);
		}

		Formula f = funcs.get(formula[0]);
		if (f == null) {
			f = formula[0].matches("-?\\d+(\\.\\d+)?")
							? new Constant(Double.valueOf(formula[0]))
							: new Variable(formula[0]);
		}
		return f;
	}

	private boolean process(final String[] p_formula,
													final HashMap<String, Formula> p_funcs) {
		return checks.stream().anyMatch((check)
						-> (check.check(this, p_formula, p_funcs)));
	}

	public Formula interpret(final String p_formula) {
		return interpret(p_formula, null);
	}

	public static class BiCheck extends Check<BiFormula> {
		public BiCheck() {
			throw new UnsupportedOperationException("Not Yet Implemented");
		}
		protected final Constructor<? extends BiFormula> constructor;
		protected final String operator;

		@Override
		public boolean check(final FormulaConstructor p_const,
												 final String[] p_formula,
												 final HashMap<String, Formula> p_funcs) {
			Matcher m = pattern.matcher(p_formula[0]);
			boolean flag = false;
			while (m.find()) {
				flag = true;
				String match = m.group();
				int opindex = match.indexOf(operator);
				Formula left = p_const.interpret(match.substring(0, opindex).trim(),
								p_funcs);
				Formula right = p_const.interpret(match.substring(opindex + 1).trim(),
								p_funcs);
				String key = p_const.comment_string + p_funcs.size();
				try {
					p_funcs.put(key, constructor.newInstance(match, left, right));
				} catch (InstantiationException | IllegalAccessException
								| IllegalArgumentException | InvocationTargetException ex) {
					LOG.log(Level.SEVERE, null, ex);
					throw new AssertionError(ex);
				}
				p_formula[0] = Pattern.compile(match, Pattern.LITERAL)
								.matcher(p_formula[0]).replaceFirst(key);
			}
			return flag;
		}
	}

	public static class BracketCheck extends Check<Formula> {
		public BracketCheck() {
			throw new UnsupportedOperationException("Not Yet Implemented");
		}
		protected final Constructor<? extends UniFormula> constructor;
		protected final Pattern end_pattern;
		protected final Pattern start_pattern;

		@Override
		public boolean check(final FormulaConstructor p_const,
												 final String[] p_formula,
												 final HashMap<String, Formula> p_funcs) {
			Matcher m = pattern.matcher(p_formula[0]);
			boolean flag = false;
			while (m.find()) {
				flag = true;
				String match = m.group();

				Matcher start_m = start_pattern.matcher(match);
				if (!start_m.find()) {
					throw new AssertionError("Impossibly Malformed method (name): "
									+ match);
				}
				String start_operator = start_m.group();

				Matcher end_m = end_pattern.matcher(match);
				if (!end_m.find()) {
					throw new AssertionError("Impossibly Malformed method (name): "
									+ match);
				}
				String end_operator = end_m.group();

				Formula f = p_const.interpret(match.substring(start_operator.length(),
								match.length() - end_operator.length()));
				if (constructor != null) {
					try {
						f = constructor.newInstance(match, f);
					} catch (InstantiationException | IllegalAccessException
									| IllegalArgumentException | InvocationTargetException ex) {
						LOG.log(Level.SEVERE, null, ex);
						throw new AssertionError(ex);
					}
				}
				String key = p_const.comment_string + p_funcs.size();
				p_funcs.put(key, f);
				p_formula[0] = Pattern.compile(match, Pattern.LITERAL)
								.matcher(p_formula[0]).replaceFirst(key);
			}
			return flag;
		}
	}

	public static abstract class Check<T extends Formula> {
		public Check() {
			throw new UnsupportedOperationException("Not Yet Implemented");
		}
		protected final Pattern pattern;

		public abstract boolean check(final FormulaConstructor p_const,
																	final String[] p_formula,
																	final HashMap<String, Formula> p_funcs);
	}

	public static class MethodCheck extends Check<Formula> {
		public MethodCheck() {
			throw new UnsupportedOperationException("Not Yet Implemented");
		}
		protected final Pattern bracket_pattern;
		protected final Constructor<? extends MultiFormula> constructor;
		protected final Pattern name_pattern;
		protected final Pattern separator_pattern;

		@Override
		public boolean check(FormulaConstructor p_const, String[] p_formula,
												 HashMap<String, Formula> p_funcs) {
			Matcher m = pattern.matcher(p_formula[0]);
			boolean flag = false;
			while (m.find()) {
				flag = true;
				String match = m.group();

				Matcher name_m = name_pattern.matcher(match);
				if (!name_m.find()) {
					throw new AssertionError("Impossibly Malformed method (name): "
									+ match);
				}
				String name = name_m.group();

				Matcher bracket_m = bracket_pattern.matcher(match);
				if (!bracket_m.find()) {
					throw new AssertionError("Impossibly Malformed method (bracket): "
									+ match);
				}

				String[] args = separator_pattern.split(bracket_m.group());
				Formula[] params;
				if (args.length == 1 && args[0].isEmpty()) {
					params = new Formula[0];
				} else {
					params = new Formula[args.length];
					for (int i = 0; i < args.length; i++) {
						params[i] = p_const.interpret(args[i], p_funcs);
					}
				}

				String key = p_const.comment_string + p_funcs.size();
				try {
					p_funcs.put(key, constructor.newInstance(match, params));
				} catch (InstantiationException | IllegalAccessException
								| IllegalArgumentException | InvocationTargetException ex) {
					LOG.log(Level.SEVERE, null, ex);
					throw new AssertionError(ex);
				}

			}
			return flag;
		}
	}
}
