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

package com.study;

import com.study.component.*;
import com.study.component.config.CommandLineArgumentParser;
import com.study.component.console.CellNumberConverter;
import com.study.component.console.ConsoleDataPrinter;
import com.study.component.console.ConsoleGameOverHandler;
import com.study.component.console.ConsoleUserInputReader;
import com.study.component.console.keypad.DesktopNumericKeypadCellNumberConverter;
import com.study.component.strategy.FirstMoveToTheCenterComputerMoveStrategy;
import com.study.component.strategy.RandomComputerMoveStrategy;
import com.study.component.strategy.WinNowComputerMoveStrategy;
import com.study.model.config.PlayerType;
import com.study.model.config.UserInterface;
import com.study.model.game.Player;
import com.study.swing.GameWindow;

import static com.study.model.config.PlayerType.USER;
import static com.study.model.config.UserInterface.GUI;
import static com.study.model.game.Sign.O;
import static com.study.model.game.Sign.X;

/**
 * * @author study
 */
public class GameFactory {

    private final PlayerType player1Type;

    private final PlayerType player2Type;

    final UserInterface userInterface;

    public GameFactory(final String[] args) {

        final CommandLineArgumentParser.CommandLineArguments commandLineArguments =
                new CommandLineArgumentParser(args).parse();
        userInterface = commandLineArguments.getUserInterface();
        player1Type = commandLineArguments.getPlayer1Type();
        player2Type = commandLineArguments.getPlayer2Type();

    }

    public Game create() {

        final ComputerMoveStrategy[] strategies = {
                new WinNowComputerMoveStrategy(),
                new FirstMoveToTheCenterComputerMoveStrategy(),
                new RandomComputerMoveStrategy()
        };

        final GameWindow gameWindow;

        final DataPrinter dataPrinter;

        final UserInputReader userInputReader;

        final GameOverHandler gameOverHandler;

        if (userInterface == GUI) {
            gameWindow = new GameWindow();
            dataPrinter = gameWindow;
            userInputReader = gameWindow;
            gameOverHandler = gameWindow;
        } else {
            final CellNumberConverter cellNumberConverter
                    = new DesktopNumericKeypadCellNumberConverter();
            dataPrinter = new ConsoleDataPrinter(cellNumberConverter);
            userInputReader = new ConsoleUserInputReader(cellNumberConverter, dataPrinter);
            gameOverHandler = new ConsoleGameOverHandler(dataPrinter);
        }

        final Player player1;
        if (player1Type == USER) {
            player1 = new Player(X, new UserMove(dataPrinter, userInputReader));
        } else {
            player1 = new Player(X, new ComputerMove(strategies));
        }
        final Player player2;
        if (player2Type == USER) {
            player2 = new Player(O, new UserMove(dataPrinter, userInputReader));
        } else {
            player2 = new Player(O, new ComputerMove(strategies));
        }
        final boolean canSecondPlayerMakeFirstMove = player1Type != player2Type;
        return new Game(
                player1,
                player2,
                dataPrinter,
                new WinnerVerifier(),
                new CellVerifier(),
                canSecondPlayerMakeFirstMove,
                gameOverHandler);
    }
}
