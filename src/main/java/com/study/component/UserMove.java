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

package com.study.component;

import com.study.model.game.Cell;
import com.study.model.game.GameTable;
import com.study.model.game.Sign;

/**
 * * @author study
 */
public class UserMove implements Move {

    private final DataPrinter dataPrinter;

    private final UserInputReader userInputReader;

    public UserMove(final DataPrinter dataPrinter,
                    final UserInputReader userInputReader) {
        this.dataPrinter = dataPrinter;
        this.userInputReader = userInputReader;
    }

    public void make(final GameTable gameTable, Sign sign) {
        while (true) {
            final Cell cell = userInputReader.getUserInput();
            if (gameTable.isEmpty(cell)) {
                gameTable.setSign(cell, sign);
                return;
            } else {
                dataPrinter.printErrorMessage("Can't make a move, " +
                        "because the cell is not free! Try again!");
            }
        }
    }
}
