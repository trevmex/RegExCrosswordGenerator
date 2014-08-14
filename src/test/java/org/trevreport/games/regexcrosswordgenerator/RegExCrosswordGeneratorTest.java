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
        assertEquals(regExCrosswordGenerator.getNthRow(0), solution.substring(0, 2));
        assertEquals(regExCrosswordGenerator.getNthRow(1), solution.substring(3, 5));
        assertEquals(regExCrosswordGenerator.getNthRow(2), solution.substring(6, 8));
    }

    @Test(expectedExceptions = IndexOutOfBoundsException.class, expectedExceptionsMessageRegExp = "Row number must be less then \\d+\\.")
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
        assertEquals(regExCrosswordGenerator.getNthColumn(0), String.valueOf(solution.charAt(0)) + solution.charAt(3) + solution.charAt(6));
        assertEquals(regExCrosswordGenerator.getNthColumn(1), String.valueOf(solution.charAt(1)) + solution.charAt(4) + solution.charAt(7));
        assertEquals(regExCrosswordGenerator.getNthColumn(2), String.valueOf(solution.charAt(2)) + solution.charAt(5) + solution.charAt(8));
    }

    @Test(expectedExceptions = IndexOutOfBoundsException.class, expectedExceptionsMessageRegExp = "Column number must be less then \\d+\\.")
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