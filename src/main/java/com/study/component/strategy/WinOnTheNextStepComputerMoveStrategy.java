package com.study.component.strategy;

import com.study.component.ComputerMoveStrategy;
import com.study.model.game.Sign;

/**
 * * @author study
 */
public class WinOnTheNextStepComputerMoveStrategy extends AbstractComputerMoveStrategy implements ComputerMoveStrategy {

    public WinOnTheNextStepComputerMoveStrategy() {
        super(2);
    }

    @Override
    protected Sign getFindSign(final Sign moveSign) {
        return moveSign;
    }
}
