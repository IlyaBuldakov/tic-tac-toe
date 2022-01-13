package com.study.component.strategy;

import com.study.component.ComputerMoveStrategy;
import com.study.model.game.Sign;

/**
 * * @author study
 */
public class PreventUserWinComputerMoveStrategy extends AbstractComputerMoveStrategy implements ComputerMoveStrategy {

    @Override
    protected Sign getFindSign(final Sign moveSign) {
        return moveSign.getOppositeSign();
    }
}
