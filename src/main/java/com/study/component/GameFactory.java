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

import com.study.component.keypad.TerminalNumericKeypadCellNumberConverter;
import com.study.model.Player;
import com.study.model.PlayerType;

import static com.study.component.Sign.O;
import static com.study.component.Sign.X;
import static com.study.model.PlayerType.COMPUTER;
import static com.study.model.PlayerType.USER;

/**
 * * @author study
 */
public class GameFactory {

    private final PlayerType playerType1 = USER;

    private final PlayerType playerType2 = COMPUTER;

    public GameFactory(final String[] args) {
        // TODO
    }

    public Game create() {
        final CellNumberConverter cellNumberConverter = new TerminalNumericKeypadCellNumberConverter();

        return new Game(
                new DataPrinter(cellNumberConverter),
                // FIXME
                new Player(X, new ComputerMove()),
                new Player(O, new ComputerMove()),
                new WinnerVerifier(),
                new CellVerifier(),
                true);
    }
}
