package com.study.component.strategy;

import com.study.component.ComputerMoveStrategy;
import com.study.model.game.Cell;
import com.study.model.game.GameTable;
import com.study.model.game.Sign;

/**
 * * @author study
 */
public class WinNowComputerMoveStrategy implements ComputerMoveStrategy {

    @Override
    public boolean tryToMakeMove(final GameTable gameTable, final Sign sign) {
        return isDoubleSignByRows(gameTable, sign) ||
                isDoubleSignByCols(gameTable, sign) ||
                isDoubleSignByMainDiagonal(gameTable, sign) ||
                isDoubleSignBySecondaryDiagonal(gameTable, sign);
    }

    private boolean isDoubleSignByRows(final GameTable gameTable, final Sign sign) {
        for (int i = 0; i < 3; i++) {
            int countEmptyCells = 0;
            int countSignCells = 0;
            Cell emptyCell = null;
            for (int j = 0; j < 3; j++) {
                Cell cell = new Cell(i, j);
                if (gameTable.isEmpty(cell)) {
                    emptyCell = cell;
                    countEmptyCells++;
                } else if (gameTable.getSign(cell) == sign) {
                    countSignCells++;
                } else {
                    break;
                }
            }
            if (countSignCells == 2 && countEmptyCells == 1) {
                gameTable.setSign(emptyCell, sign);
                return true;
            }
        }
        return false;
    }

    private boolean isDoubleSignByCols(final GameTable gameTable, final Sign sign) {
        for (int i = 0; i < 3; i++) {
            int countEmptyCells = 0;
            int countSignCells = 0;
            Cell emptyCell = null;
            for (int j = 0; j < 3; j++) {
                Cell cell = new Cell(j, i);
                if (gameTable.isEmpty(cell)) {
                    emptyCell = cell;
                    countEmptyCells++;
                } else if (gameTable.getSign(cell) == sign) {
                    countSignCells++;
                } else {
                    break;
                }
            }
            if (countSignCells == 2 && countEmptyCells == 1) {
                gameTable.setSign(emptyCell, sign);
                return true;
            }
        }
        return false;
    }

    private boolean isDoubleSignByMainDiagonal(final GameTable gameTable, final Sign sign) {
        int countEmptyCells = 0;
        int countSignCells = 0;
        Cell emptyCell = null;
        for (int j = 0; j < 3; j++) {
            Cell cell = new Cell(j, j);
            if (gameTable.isEmpty(cell)) {
                emptyCell = cell;
                countEmptyCells++;
            } else if (gameTable.getSign(cell) == sign) {
                countSignCells++;
            } else {
                break;
            }
        }
        if (countSignCells == 2 && countEmptyCells == 1) {
            gameTable.setSign(emptyCell, sign);
            return true;
        }
        return false;
    }

    private boolean isDoubleSignBySecondaryDiagonal(final GameTable gameTable, final Sign sign) {
        int countEmptyCells = 0;
        int countSignCells = 0;
        Cell emptyCell = null;
        for (int j = 0; j < 3; j++) {
            Cell cell = new Cell(j, 2 - j);
            if (gameTable.isEmpty(cell)) {
                emptyCell = cell;
                countEmptyCells++;
            } else if (gameTable.getSign(cell) == sign) {
                countSignCells++;
            } else {
                break;
            }
        }
        if (countSignCells == 2 && countEmptyCells == 1) {
            gameTable.setSign(emptyCell, sign);
            return true;
        }
        return false;
    }
}
