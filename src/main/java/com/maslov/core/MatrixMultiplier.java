package com.maslov.core;

public interface MatrixMultiplier {
	/**
	 * Method designed to multiply two matrices in iterative way sequentionally
	 *
	 * @param matrixA multiplicand matrix
	 * @param matrixB multiplier matrix
	 * @return product of two matrices
	 */
	float[][] multiplySequential(float[][] matrixA, float[][] matrixB);

	/**
	 * Method designed to multiply two matrices in iterative way parallel
	 *
	 * @param matrixA multiplicand matrix
	 * @param matrixB multiplier matrix
	 * @return product of two matrices
	 */
	float[][] multiplyParallel(float[][] matrixA, float[][] matrixB);
}
