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

import com.study.model.game.GameTable;
import com.study.model.game.Player;

import java.util.Random;

/**
 * * @author study
 */
public class Game {

    private final Player player1;

    private final Player player2;

    private final WinnerVerifier winnerVerifier;

    private final CellVerifier cellVerifier;

    private final boolean canSecondPlayerMakeFirstMove;

    private final DataPrinter dataPrinter;

    private final GameOverHandler gameOverHandler;

    public Game(final Player player1,
                final Player player2,
                final DataPrinter dataPrinter,
                final WinnerVerifier winnerVerifier,
                final CellVerifier cellVerifier,
                final boolean canSecondPlayerMakeFirstMove,
                final GameOverHandler gameOverHandler) {
        this.player1 = player1;
        this.player2 = player2;
        this.dataPrinter = dataPrinter;
        this.winnerVerifier = winnerVerifier;
        this.cellVerifier = cellVerifier;
        this.canSecondPlayerMakeFirstMove = canSecondPlayerMakeFirstMove;
        this.gameOverHandler = gameOverHandler;
    }

    public void play() {

        final Player[] players = new Player[]{player1, player2};

        dataPrinter.printInstructions();
        final GameTable gameTable = new GameTable();
        if (canSecondPlayerMakeFirstMove && new Random().nextBoolean()) {
            players[1].makeMove(gameTable);
            dataPrinter.printGameTable(gameTable);
        }

        while (true) {
            for (Player player : players) {
                player.makeMove(gameTable);
                dataPrinter.printGameTable(gameTable);
                if (winnerVerifier.isWinner(gameTable, player)) {
                    dataPrinter.printInfoMessage(player + "WIN!");
                    gameOverHandler.gameOver();
                    return;
                }
                if (cellVerifier.allCellsFilled(gameTable)) {
                    dataPrinter.printInfoMessage("SORRY, DRAW!");
                    gameOverHandler.gameOver();
                    return;
                }
            }
        }
    }
}
