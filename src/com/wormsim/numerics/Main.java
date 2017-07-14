/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wormsim.numerics;

import static com.wormsim.numerics.BasicMath.sum;
import com.wormsim.numerics.formula.Formula;
import com.wormsim.numerics.formula.Formula.Constant;
import com.wormsim.numerics.formula.Formula.Multiply;
import com.wormsim.numerics.formula.Formula.Subtract;
import com.wormsim.numerics.formula.Formula.Variable;
import com.wormsim.numerics.game.DecisionNode;
import com.wormsim.numerics.game.Game;
import com.wormsim.numerics.game.IndependentDecisionNode;
import com.wormsim.numerics.game.NaturalNode;
import com.wormsim.numerics.game.SimultaneousDecisionNode;
import com.wormsim.numerics.game.TerminalNode;

/**
 *
 * @author ah810
 */
public class Main {
	public static void main(String[] args) {
		// TODO: Implement a test. Probably the thing I did already.
		Player p1 = new Player("Strain 1");
		Player p2 = new Player("Strain 2");

		Formula a = new Variable("a", "Non-Invest Score");
		Formula b = new Variable("b", "Invest Score");
		Formula c = new Variable("c", "Competition Cost");
		Formula bc = new Multiply(b, c);

		Formula pa = new Variable("p_A", "Probability Together").setBounds(0.0, 1.0);
		Formula pt = new Subtract(Constant.ONE, pa);

		Formula pg = new Variable("p_G", "Probability Good").setBounds(0.0, 1.0);
		Formula pb = new Subtract(Constant.ONE, pg);

		// All player individual outcomes
		TerminalNode best = new TerminalNode("Good", b);
		TerminalNode worst = new TerminalNode("Long", Constant.ZERO);
		TerminalNode safe = new TerminalNode("Short", a);
		TerminalNode compete = new TerminalNode("Good", bc);

		// Alone Tree
		NaturalNode long_before_nature = new NaturalNode(
						"Environment (Non-Competitive)")
						.addOutcome(pg, best)
						.addOutcome(pb, worst);
		DecisionNode after_nature_bad = new DecisionNode("B", "Investment")
						.addOutcome("Short", safe)
						.addOutcome("Long", worst);
		NaturalNode alone_honesty = new NaturalNode("Honesty")
						.addOutcome(pg, best)
						.addOutcome(pb, after_nature_bad);
		DecisionNode alone_before_nature = new DecisionNode("B", "Investment")
						.addOutcome("Short", safe)
						.addOutcome("Long", long_before_nature);
		IndependentDecisionNode alone = new IndependentDecisionNode("A", "Alone")
						.addOutcome("Honesty", alone_honesty)
						.addOutcome("Dishonesty", alone_before_nature);

		// Together Tree
		NaturalNode compete_before_nature = new NaturalNode(
						"Environment (Competitive)")
						.addOutcome(pg, compete)
						.addOutcome(pb, worst);
		SimultaneousDecisionNode decide_before_nature = new SimultaneousDecisionNode(
						"B",
						"Dishonesty")
						.addChoice("Short")
						.addChoice("Long")
						.addOutcome((p) -> sum(p) == 2, compete_before_nature)
						.addIndependentOutcome((p) -> p == 0, safe)
						.addIndependentOutcome((p) -> p == 1, long_before_nature);

		SimultaneousDecisionNode decide_after_nature = new SimultaneousDecisionNode(
						"B",
						"Dishonesty")
						.addChoice("Short")
						.addChoice("Long")
						.addIndependentOutcome((p) -> p == 0, safe)
						.addIndependentOutcome((p) -> p == 1, worst);
		NaturalNode together_honesty = new NaturalNode("Honesty")
						.addOutcome(pg, compete)
						.addOutcome(pb, decide_after_nature);

		SimultaneousDecisionNode together = new SimultaneousDecisionNode("A",
						"Together")
						.addChoice("Honesty")
						.addChoice("Dishonesty")
						.addOutcome((p) -> sum(p) == 0, together_honesty)
						.addOutcome((p) -> sum(p) > 0, decide_before_nature);

		NaturalNode initial = new NaturalNode("Initial")
						.addOutcome(pa, alone)
						.addOutcome(pt, together);

		Game game = new Game(initial, p1, p2);
		game.solve();
	}
}
