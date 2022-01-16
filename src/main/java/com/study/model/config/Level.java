package com.study.model.config;

import com.study.component.ComputerMoveStrategy;
import com.study.component.strategy.*;

/**
 * * @author study
 */
public enum Level {

    LEVEL1(new ComputerMoveStrategy[]{
            new FirstMoveToTheCenterComputerMoveStrategy(),
            new RandomComputerMoveStrategy()
    }),

    LEVEL2(new ComputerMoveStrategy[]{
            new WinNowComputerMoveStrategy(),
            new PreventUserWinComputerMoveStrategy(),
            new FirstMoveToTheCenterComputerMoveStrategy(),
            new RandomComputerMoveStrategy()
    }),

    LEVEL3(new ComputerMoveStrategy[]{
            new WinNowComputerMoveStrategy(),
            new PreventUserWinComputerMoveStrategy(),
            new WinOnTheNextStepComputerMoveStrategy(),
            new FirstMoveToTheCenterComputerMoveStrategy(),
            new RandomComputerMoveStrategy()
    });

    private final ComputerMoveStrategy[] strategies;

    public ComputerMoveStrategy[] getStrategies() {
        return strategies.clone();
    }

    Level(ComputerMoveStrategy[] strategies) {
        this.strategies = strategies;
    }
}
