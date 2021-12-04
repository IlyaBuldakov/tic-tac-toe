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
import com.study.model.game.Cell;

/**
 * * @author study
 */
public class DesktopNumericKeypadCellNumberConverter implements CellNumberConverter {

    private final char[][] table = {
            {'7', '8', '9'},
            {'4', '5', '6'},
            {'1', '2', '3'}
    };

    @Override
    public Cell toCell(final char number) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (table[i][j] == number) return new Cell(i, j);
            }
        }
        return null;
    }

    @Override
    public char toNumber(final Cell cell) {
        return table[cell.getRow()][cell.getCol()];
    }

}
