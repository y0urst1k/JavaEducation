/**
 * Задание 6: матрицы — вывод, транспонирование, умножение.
 */
package ru.university.lab2.arrays;

import java.util.Random;

public class Task06Matrix {
    public void run() {
        int[][] A = createMatrix(2, 3);
        int[][] B = createMatrix(3, 2);
        printMatrix("Матрица A (2x3)", A);
        printMatrix("Матрица B (3x2)", B);
        printMatrix("Транспонированная A", transpose(A));
        int[][] C = multiply(A, B);
        if (C != null) printMatrix("A × B (2x2)", C);
    }

    private int[][] createMatrix(int rows, int cols) {
        int[][] mat = new int[rows][cols];
        Random rand = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                mat[i][j] = rand.nextInt(10);
            }
        }
        return mat;
    }

    private void printMatrix(String label, int[][] mat) {
        System.out.println(label + ":");
        for (int[] row : mat) {
            for (int x : row) {
                System.out.printf("%4d", x);
            }
            System.out.println();
        }
    }

    private int[][] transpose(int[][] mat) {
        int rows = mat.length, cols = mat[0].length;
        int[][] result = new int[cols][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[j][i] = mat[i][j];
            }
        }
        return result;
    }

    /**
     * Умножение: A[m×n] × B[n×p] = C[m×p]
     * Если n не совпадает — возвращаем null.
     */
    private int[][] multiply(int[][] A, int[][] B) {
        int m = A.length, n = A[0].length;
        int p = B[0].length;
        if (n != B.length) {
            System.out.println("Размеры матриц не согласованы для умножения.");
            return null;
        }

        int[][] C = new int[m][p];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < p; j++) {
                for (int k = 0; k < n; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return C;
    }
}
