/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wormsim.numerics.game;

import com.wormsim.numerics.Player;
import com.wormsim.numerics.formula.Formula;
import com.wormsim.numerics.formula.Formula.Constant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author ah810
 */
public class Game {
	public Game(Node initial, Player... players) {
		this.initial = initial;
		this.players = Collections.unmodifiableList(Arrays.asList(players));
	}
	private final Node initial;
	private final List<Player> players;

	@SuppressWarnings("ReturnOfCollectionOrArrayField")
	public List<Player> getPlayers() {
		return players;
	}

	public void solve() {
		// How to solve a game.	
		LinkedList<Choices> resolved = new LinkedList<>();
		LinkedList<Choices> to_resolve = new LinkedList<>();
		to_resolve.add(new Choices(initial, Constant.ONE, players.size()));
		// Begin at the beginning.
		while (!to_resolve.isEmpty()) {
			Choices c = to_resolve.remove();
			if (c.getNode() instanceof TerminalNode) {
				resolved.add(c.resolve().toArray(new Choices[1])[0]);
			} else {
				to_resolve.addAll(c.resolve());
			}
		}
	}
}
