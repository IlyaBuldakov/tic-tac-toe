package com.study.component.strategy;

import com.study.component.ComputerMoveStrategy;
import com.study.model.game.Cell;
import com.study.model.game.GameTable;
import com.study.model.game.Sign;

/**
 * * @author study
 */
public abstract class AbstractComputerMoveStrategy implements ComputerMoveStrategy {

    public final boolean tryToMakeMove(final GameTable gameTable, final Sign sign) {
        Sign findSign = getFindSign(sign);
        return isDoubleSignByRows(gameTable, findSign, sign) ||
                isDoubleSignByCols(gameTable, findSign, sign) ||
                isDoubleSignByMainDiagonal(gameTable, findSign, sign) ||
                isDoubleSignBySecondaryDiagonal(gameTable, findSign, sign);
    }

    protected abstract Sign getFindSign(Sign moveSign);

    @SuppressWarnings("Convert2MethodRef")
    private boolean isDoubleSignByRows(final GameTable gameTable, final Sign findSign, final Sign sign) {
        for (int i = 0; i < 3; i++) {
            if (isDoubleSignUsingLambda(gameTable, findSign, sign, i, (k, j) -> new Cell(k, j)))
                return true;
        }
        return false;
    }

    private boolean isDoubleSignByCols(final GameTable gameTable, final Sign findSign, final Sign sign) {
        for (int i = 0; i < 3; i++) {
            if (isDoubleSignUsingLambda(gameTable, findSign, sign, i, (k, j) -> new Cell(j, k))) {
                return true;
            }
        }
        return false;
    }

    private boolean isDoubleSignByMainDiagonal(final GameTable gameTable, final Sign findSign, final Sign sign) {
        return isDoubleSignUsingLambda(gameTable, findSign, sign, -1, (k, j) -> new Cell(j, j));
    }

    private boolean isDoubleSignBySecondaryDiagonal(final GameTable gameTable, final Sign findSign, final Sign sign) {
        return isDoubleSignUsingLambda(gameTable, findSign, sign, -1, (k, j) -> new Cell(j, 2 - j));
    }

    private boolean isDoubleSignUsingLambda(final GameTable gameTable,
                                            final Sign findSign,
                                            final Sign sign,
                                            final int i,
                                            final Lambda lambda) {
        int countEmptyCells = 0;
        int countSignCells = 0;
        Cell emptyCell = null;
        for (int j = 0; j < 3; j++) {
            Cell cell = lambda.convert(i, j);
            if (gameTable.isEmpty(cell)) {
                emptyCell = cell;
                countEmptyCells++;
            } else if (gameTable.getSign(cell) == findSign) {
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

    @FunctionalInterface
    private interface Lambda {

        Cell convert(int k, int j);
    }
}
