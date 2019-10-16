package com.maslov.utils;

import java.util.Random;

public class Utils {

	public static float[][] generateSquareRandomMatrix(int size) {
		return generateSquareRandomMatrix(size, false);
	}

	public static float[][] generateSquareNullMatrix(int size) {
		return generateSquareRandomMatrix(size, true);
	}

	private static float[][] generateSquareRandomMatrix(int size,
			boolean nullMatrix) {
		Random random = new Random();
		float[][] matrix = new float[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				matrix[i][j] = nullMatrix ? 0.00000f : random.nextFloat();
			}
		}
		return matrix;
	}
}