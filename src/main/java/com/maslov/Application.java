package com.maslov;

import com.maslov.core.MatrixMultiplier;
import com.maslov.core.MatrixMultiplierService;
import com.maslov.utils.Utils;

public class Application {

	private static final MatrixMultiplier matrixService = new MatrixMultiplierService();

	public static void main(String[] args) {
		final int size = getInputSize(args);

		System.out.println("Sequential multiplying started");
		final long start = System.nanoTime();
		matrixService.multiplySequential(
				Utils.generateSquareRandomMatrix(size),
				Utils.generateSquareRandomMatrix(size));
		final long end = System.nanoTime();

		System.out.printf("Time consumed: %s seconds\n",
				(end - start) / 1000000000);

		System.out.println("Parallel multiplying started");
		final long startParallel = System.nanoTime();
		matrixService.multiplyParallel(Utils.generateSquareRandomMatrix(size),
				Utils.generateSquareRandomMatrix(size));
		final long endParallel = System.nanoTime();

		System.out.printf("Time consumed: %s seconds\n",
				(endParallel - startParallel) / 1000000000);
	}

	private static int getInputSize(String[] args) {
		int size;
		try {
			size = Integer.parseInt(args[0]);
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new IllegalArgumentException(
					"No argument was passed");
		}
		return size;
	}
}
