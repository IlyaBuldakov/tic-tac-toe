package com.study.component.strategy;

import com.study.component.ComputerMoveStrategy;
import com.study.model.game.Sign;

/**
 * * @author study
 */
public class WinNowComputerMoveStrategy extends AbstractComputerMoveStrategy implements ComputerMoveStrategy {

    public WinNowComputerMoveStrategy() {
        super(1);
    }

    @Override
    protected Sign getFindSign(final Sign moveSign) {
        return moveSign;
    }
}
