/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wormsim.numerics;

import java.util.stream.DoubleStream;

/**
 *
 * @author ah810
 */
public class BasicMath {

	public static double sum(double[] values) {
		return DoubleStream.of(values).sum();
	}
}
