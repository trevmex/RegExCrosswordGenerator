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

import org.apache.commons.lang.RandomStringUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class RegExCrosswordGeneratorTest {

    private final static int SOLUTION_LENGTH = 9;
    private final static int ROWS = 3;
    private final static int COLUMNS = 3;
    private final static int NON_DIVISIBLE_ROWS = 4;
    private final static int LOWER_BOUND = 0;

    private String solution;
    private RegExCrosswordGenerator regExCrosswordGenerator;

    @BeforeTest
    public void setUp() throws Exception {
        solution = RandomStringUtils.randomAlphanumeric(SOLUTION_LENGTH);
        regExCrosswordGenerator = RegExCrosswordGenerator.createNewInstance(solution, ROWS);
    }

    // TODO: Make the more random
    @Test
    public void testFindNthRow_shouldReturnCorrectRow() throws Exception {
        for (int i = 0; i < SOLUTION_LENGTH; i++) {
            assertEquals(regExCrosswordGenerator.getNthRow(i), solution.substring(i, i + ROWS - 1));
        }
    }

    @Test(expectedExceptions = IndexOutOfBoundsException.class, expectedExceptionsMessageRegExp = "Row number must be less than \\d+\\.")
    public void testFindNthRow_shouldNotAcceptRowsGreaterThanExist() throws Exception {
        regExCrosswordGenerator.getNthRow(ROWS);
    }

    @Test(expectedExceptions = IndexOutOfBoundsException.class, expectedExceptionsMessageRegExp = "Row number must be positive\\.")
    public void testFindNthRow_shouldNotAcceptRowsLessThanExist() throws Exception {
        regExCrosswordGenerator.getNthRow(LOWER_BOUND - 1);
    }

    // TODO: Make the more random
    @Test
    public void testFindNthColumn_shouldReturnCorrectColumn() throws Exception {
        for (int i = 0; i < SOLUTION_LENGTH; i++) {
            assertEquals(regExCrosswordGenerator.getNthColumn(i), String.valueOf(solution.charAt(i)) + solution.charAt(i + ROWS) + solution.charAt(i + ROWS * 2));
        }
    }

    @Test(expectedExceptions = IndexOutOfBoundsException.class, expectedExceptionsMessageRegExp = "Column number must be less than \\d+\\.")
    public void testFindNthColumn_shouldNotAcceptColumnsGreaterThanExist() throws Exception {
        regExCrosswordGenerator.getNthColumn(COLUMNS);
    }

    @Test(expectedExceptions = IndexOutOfBoundsException.class, expectedExceptionsMessageRegExp = "Column number must be positive\\.")
    public void testFindNthColumn_shouldNotAcceptColumnsLessThanExist() throws Exception {
        regExCrosswordGenerator.getNthColumn(LOWER_BOUND - 1);
    }

    @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Solution cannot be null\\.")
    public void testCreateNewInstance_shouldNotAcceptNullSolution() throws Exception {
        RegExCrosswordGenerator.createNewInstance(null, ROWS);
    }

    @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Number of rows must be divisible by \\d+\\.")
    public void testCreateNewInstance_shouldNotAcceptInvalidRows() throws Exception {
        RegExCrosswordGenerator.createNewInstance(solution, NON_DIVISIBLE_ROWS);
    }

    @Test
    public void testCreateNewInstance_shouldAcceptValidParameters() throws Exception {
        assertEquals(regExCrosswordGenerator.toString(), "RegExCrosswordGenerator{solution='" + solution + "', rows=" + ROWS + "}");
    }
}