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

import com.study.component.console.ConsoleDataPrinter;
import com.study.component.console.ConsoleUserInputReader;
import com.study.component.keypad.TerminalNumericKeypadCellNumberConverter;
import com.study.model.Player;
import com.study.model.PlayerType;

import static com.study.component.Sign.O;
import static com.study.component.Sign.X;
import static com.study.model.PlayerType.USER;

/**
 * * @author study
 */
public class GameFactory {

    private final PlayerType player1Type;

    private final PlayerType player2Type;

    public GameFactory(final String[] args) {
        final CommandLineArgumentParser.PlayerTypes playerTypes =
                new CommandLineArgumentParser(args).parse();

        this.player1Type = playerTypes.getPlayer1Type();
        this.player2Type = playerTypes.getPlayer2Type();
    }

    public Game create() {

        final CellNumberConverter cellNumberConverter
                = new TerminalNumericKeypadCellNumberConverter();

        final DataPrinter dataPrinter =
                new ConsoleDataPrinter(cellNumberConverter);

        final UserInputReader userInputReader =
                new ConsoleUserInputReader(cellNumberConverter, dataPrinter);

        final Player player1;
        if (player1Type == USER) {
            player1 = new Player(X, new UserMove(dataPrinter, userInputReader));
        } else {
            player1 = new Player(X, new ComputerMove());
        }
        final Player player2;
        if (player2Type == USER) {
            player2 = new Player(O, new UserMove(dataPrinter, userInputReader));
        } else {
            player2 = new Player(O, new ComputerMove());
        }
        final boolean canSecondPlayerMakeFirstMove = player1Type != player2Type;
        return new Game(
                player1,
                player2,
                dataPrinter,
                new WinnerVerifier(),
                new CellVerifier(),
                canSecondPlayerMakeFirstMove
        );
    }
}
