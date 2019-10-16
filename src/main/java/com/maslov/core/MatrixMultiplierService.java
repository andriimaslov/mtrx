package com.maslov.core;

import com.maslov.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MatrixMultiplierService implements MatrixMultiplier {

	@Override
	public float[][] multiplySequential(float[][] matrixA,
			float[][] matrixB) {
		validateMatrices(matrixA, matrixB);

		int size = matrixA.length;
		float[][] matrixC = Utils.generateSquareNullMatrix(size);

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				for (int k = 0; k < size; k++) {
					matrixC[i][j] += matrixA[i][k] * matrixB[k][j];
				}
			}
		}
		return matrixC;
	}

	@Override
	public float[][] multiplyParallel(float[][] matrixA,
			float[][] matrixB) {
		validateMatrices(matrixA, matrixB);

		int size = matrixA.length;
		float[][] matrixC = Utils.generateSquareNullMatrix(size);
		int numProcessors = getAvailableProcessors(size);
		int rowsPerThread = matrixA.length / numProcessors;

		ExecutorService executorService = Executors.newFixedThreadPool(
				numProcessors);
		List<CompletableFuture> list = new ArrayList<>();
		int startRow = 0;
		for (int i = 0; i < numProcessors; i++) {
			list.add(CompletableFuture.runAsync(
					new MatrixMultiplyTask(matrixA, matrixB, matrixC, startRow,
							rowsPerThread), executorService));
			startRow += rowsPerThread;
		}
		list.add(CompletableFuture.runAsync(
				new MatrixMultiplyTask(matrixA, matrixB, matrixC, startRow,
						matrixA.length % numProcessors), executorService));
		executorService.shutdown();
		CompletableFuture.allOf(list.toArray(new CompletableFuture[0])).join();
		return matrixC;
	}

	private void validateMatrices(float[][] matrixA, float[][] matrixB) {
		validateSquare(matrixA[0].length, matrixB.length);
		validateSize(matrixA.length);
	}

	private void validateSquare(int aColumns, int bRows) {
		if (aColumns != bRows) {
			throw new IllegalArgumentException("Input accepts only square matrix!");
		}
	}

	private void validateSize(int length) {
		if (length != 0 & length >= 10000) {
			throw new IllegalArgumentException(
					"Size of the matrix is limited. Allowed size range [1....10000]");
		}
	}

	private int getAvailableProcessors(int size) {
		return size < Runtime.getRuntime().availableProcessors() ? size :
				Runtime.getRuntime().availableProcessors();
	}
}
