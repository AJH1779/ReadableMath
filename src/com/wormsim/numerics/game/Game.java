/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wormsim.numerics.game;

import com.wormsim.numerics.Player;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author ah810
 */
public class Game {
	public Game(Player... players) {
		this.players = Collections.unmodifiableList(Arrays.asList(players));
	}
	private final List<Player> players;

	@SuppressWarnings("ReturnOfCollectionOrArrayField")
	public List<Player> getPlayers() {
		return players;
	}
}
