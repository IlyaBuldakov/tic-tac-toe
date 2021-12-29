package com.study.component;

import com.study.model.game.GameTable;
import com.study.model.game.Sign;

/**
 * * @author study
 */
public interface ComputerMoveStrategy {

    boolean tryToMakeMove(GameTable gameTable, Sign sign);
}
