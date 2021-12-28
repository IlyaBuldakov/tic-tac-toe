/*
 *    Copyright 2021. Study
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.study.component.console.keypad;

import com.study.component.console.CellNumberConverter;
import com.study.component.exceptions.IncorrectCellCoordinatesException;
import com.study.model.game.Cell;

import static java.lang.String.format;

/**
 * * @author study
 */
public class TerminalNumericKeypadCellNumberConverter implements CellNumberConverter {

    @Override
    public Cell toCell(final char number) {
        if (number >= '1' && number <= '9') {
            final int val = number - '0' - 1;
            return new Cell(val / 3, val % 3);
        } else {
            throw new IllegalArgumentException(
                    format("Incorrect cell number. " +
                            "Your number %s does not fall in the range from 1 to 9", number)
            );
        }
    }

    @Override
    public char toNumber(final Cell cell) {
        if (cell.getRow() >= 0 && cell.getRow() <= 2 && cell.getCol() >= 0 && cell.getCol() <= 2) {
            return (char) ('0' + (cell.getRow() * 3 + cell.getCol() + 1));
        } else {
            throw new IncorrectCellCoordinatesException(
                    format("Illegal cell row and column coordinates (index must to be from 0 to 2). " +
                            "You trying to find cell with row: %s and col: %s", cell.getRow(), cell.getCol())
            );
        }
    }
}
