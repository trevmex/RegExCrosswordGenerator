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

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class RegExCrosswordGeneratorTest {

    private String solution;
    private RegExCrosswordGenerator regExCrosswordGenerator;

    @BeforeTest
    public void setUp() throws Exception {
        solution = "abcdefghi";
        regExCrosswordGenerator = RegExCrosswordGenerator.createNewInstance(solution, 3);
    }

    // TODO: Make the more random
    @Test
    public void testFindNthRow_shouldReturnCorrectRow() throws Exception {
        assertEquals(regExCrosswordGenerator.getNthRow(0), "abc");
        assertEquals(regExCrosswordGenerator.getNthRow(1), "def");
        assertEquals(regExCrosswordGenerator.getNthRow(2), "ghi");
    }

    @Test(expectedExceptions = IndexOutOfBoundsException.class, expectedExceptionsMessageRegExp = "Row number must be less than \\d+\\.")
    public void testFindNthRow_shouldNotAcceptRowsGreaterThanExist() throws Exception {
        regExCrosswordGenerator.getNthRow(3);
    }

    @Test(expectedExceptions = IndexOutOfBoundsException.class, expectedExceptionsMessageRegExp = "Row number must be positive\\.")
    public void testFindNthRow_shouldNotAcceptRowsLessThanExist() throws Exception {
        regExCrosswordGenerator.getNthRow(-1);
    }

    // TODO: Make the more random
    @Test
    public void testFindNthColumn_shouldReturnCorrectColumn() throws Exception {
        assertEquals(regExCrosswordGenerator.getNthColumn(0), "adg");
        assertEquals(regExCrosswordGenerator.getNthColumn(1), "beh");
        assertEquals(regExCrosswordGenerator.getNthColumn(2), "cfi");
    }

    @Test(expectedExceptions = IndexOutOfBoundsException.class, expectedExceptionsMessageRegExp = "Column number must be less than \\d+\\.")
    public void testFindNthColumn_shouldNotAcceptColumnsGreaterThanExist() throws Exception {
        regExCrosswordGenerator.getNthColumn(3);
    }

    @Test(expectedExceptions = IndexOutOfBoundsException.class, expectedExceptionsMessageRegExp = "Column number must be positive\\.")
    public void testFindNthColumn_shouldNotAcceptColumnsLessThanExist() throws Exception {
        regExCrosswordGenerator.getNthColumn(-1);
    }

    @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Solution cannot be null\\.")
    public void testCreateNewInstance_shouldNotAcceptNullSolution() throws Exception {
        RegExCrosswordGenerator.createNewInstance(null, 3);
    }

    @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Number of rows must be divisible by \\d+\\.")
    public void testCreateNewInstance_shouldNotAcceptInvalidRows() throws Exception {
        RegExCrosswordGenerator.createNewInstance(solution, 4);
    }

    @Test
    public void testCreateNewInstance_shouldAcceptValidParameters() throws Exception {
        assertEquals(regExCrosswordGenerator.toString(), "RegExCrosswordGenerator{solution='" + solution + "', rows=" + 3 + "}");
    }
}