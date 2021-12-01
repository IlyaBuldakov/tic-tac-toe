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

import com.study.model.GameTable;

import java.util.Random;

/**
 * * @author study
 */
public class Game {

    private final DataPrinter dataPrinter;

    private final ComputerMove computerMove;

    private final UserMove userMove;

    private final WinnerVerifier winnerVerifier;

    private final CellVerifier cellVerifier;

    public Game(final DataPrinter dataPrinter,
                final ComputerMove computerMove,
                final UserMove userMove,
                final WinnerVerifier winnerVerifier,
                final CellVerifier cellVerifier) {
        this.dataPrinter = dataPrinter;
        this.computerMove = computerMove;
        this.userMove = userMove;
        this.winnerVerifier = winnerVerifier;
        this.cellVerifier = cellVerifier;
    }

    public void play() {

        final Move[] moves = new Move[]{userMove, computerMove};

        System.out.println("Use the following mapping table to specify a cell using numbers from 1 to 9:");
        dataPrinter.printMappingTable();
        final GameTable gameTable = new GameTable();
        if (new Random().nextBoolean()) {
            moves[1].make(gameTable);
            dataPrinter.printGameTable(gameTable);
        }

        while (true) {
            for (Move move : moves) {
                move.make(gameTable);
                dataPrinter.printGameTable(gameTable);
                if (move instanceof UserMove) {
                    if (winnerVerifier.isUserWin(gameTable)) {
                        System.out.println("YOU WIN!");
                        printGameOver();
                        return;
                    }
                } else {
                    if (winnerVerifier.isComputerWin(gameTable)) {
                        if (winnerVerifier.isComputerWin(gameTable)) {
                            System.out.println("COMPUTER WIN!");
                            printGameOver();
                            return;
                        }
                    }
                }
                if (cellVerifier.allCellsFilled(gameTable)) {
                    System.out.println("SORRY, DRAW!");
                    printGameOver();
                    return;
                }
            }
        }
    }

    private void printGameOver() {
        System.out.println("GAME OVER!");
    }
}
