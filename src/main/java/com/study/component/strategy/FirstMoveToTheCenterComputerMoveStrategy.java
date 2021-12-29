package com.study.component.strategy;

import com.study.component.ComputerMoveStrategy;
import com.study.model.game.Cell;
import com.study.model.game.GameTable;
import com.study.model.game.Sign;

/**
 * * @author study
 */
public class FirstMoveToTheCenterComputerMoveStrategy implements ComputerMoveStrategy {

    @Override
    public boolean tryToMakeMove(final GameTable gameTable, final Sign sign) {
        final Cell center = new Cell(1, 1);
        if (gameTable.isEmpty(center)) {
            gameTable.setSign(center, sign);
            return true;
        } else
            return false;
    }
}
