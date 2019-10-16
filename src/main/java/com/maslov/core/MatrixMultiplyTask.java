package com.maslov.core;

public class MatrixMultiplyTask implements Runnable {

	private final float[][] matrixA;
	private final float[][] matrixB;
	private final float[][] matrixC;
	private final int startRow;
	private final int rowsCount;

	MatrixMultiplyTask(float[][] matrixA, float[][] matrixB,
			float[][] matrixC, int startRow, int rowsCount) {
		this.matrixA = matrixA;
		this.matrixB = matrixB;
		this.matrixC = matrixC;
		this.startRow = startRow;
		this.rowsCount = rowsCount;
	}

	@Override
	public void run() {
		for (int i = startRow; i < startRow + rowsCount; i++) {
			for (int j = 0; j < matrixB.length; j++) {
				for (int k = 0; k < matrixA.length; k++) {
					matrixC[i][j] += matrixA[i][k] * matrixB[k][j];
				}
			}
		}
	}
}
