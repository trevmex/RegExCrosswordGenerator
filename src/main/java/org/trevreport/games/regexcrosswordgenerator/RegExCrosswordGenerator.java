/*
 * Copyright 2014 Trevor Menagh
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.trevreport.games.regexcrosswordgenerator;

/**
 * Regular Expression Crossword Generator.
 *
 * @author trevmex
 * @since 8/12/2014
 */
public final class RegExCrosswordGenerator {
    private final static int LOWER_BOUND = 0;

    private final String solution;
    private final int rows;
    private final int columns;

    private RegExCrosswordGenerator(String solution, int rows) {
        verifySolution(solution);
        this.solution = solution;

        verifyRows(rows);
        this.rows = rows;

        this.columns = solution.length() / rows;
    }

    /**
     * Creates a new RexExCrosswordGenerator.
     *
     * @param solution the string containing the solution of the regex crossword.
     * @param rows     the number of rows in the puzzle.
     * @return a new instance of RexExCrosswordGenerator.
     */
    public static RegExCrosswordGenerator createNewInstance(String solution, int rows) {
        return new RegExCrosswordGenerator(solution, rows);
    }

    public String getNthRow(int row) {
        verifyNthRow(row);

        return solution.substring(row * rows, row * rows + rows);
    }

    public String getNthColumn(int column) {
        verifyNthColumn(column);

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < rows; i++) {
            stringBuilder.append(solution.charAt(columns * i + column));
        }

        return stringBuilder.toString();
    }

    private void verifySolution(String solution) {
        if (solution == null) {
            throw new IllegalArgumentException("Solution cannot be null.");
        }
    }

    private void verifyRows(int rows) {
        int length = solution.length();

        if (length % rows != 0) {
            throw new IllegalArgumentException("Number of rows must be divisible by " + length + ".");
        }
    }

    private void verifyNthRow(int row) {
        verifyNth("Row", row, rows);
    }

    private void verifyNthColumn(int column) {
        verifyNth("Column", column, columns);
    }

    private void verifyNth(String name, int number, int upperBound) {
        if (number < LOWER_BOUND) {
            throw new IndexOutOfBoundsException(name + " number must be positive.");
        }
        if (number >= upperBound) {
            throw new IndexOutOfBoundsException(name + " number must be less than " + upperBound + ".");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RegExCrosswordGenerator that = (RegExCrosswordGenerator) o;

        return rows == that.rows && solution.equals(that.solution);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int result = solution.hashCode();

        result = 31 * result + rows;

        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "RegExCrosswordGenerator{" +
                "solution='" + solution + '\'' +
                ", rows=" + rows +
                '}';
    }
}
