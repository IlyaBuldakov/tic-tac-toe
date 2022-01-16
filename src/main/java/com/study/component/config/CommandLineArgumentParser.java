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

package com.study.component.config;

import com.study.model.config.Level;
import com.study.model.config.PlayerType;
import com.study.model.config.UserInterface;

import java.util.Locale;

import static com.study.model.config.Level.*;
import static com.study.model.config.PlayerType.COMPUTER;
import static com.study.model.config.PlayerType.USER;
import static com.study.model.config.UserInterface.CONSOLE;
import static com.study.model.config.UserInterface.GUI;

/**
 * * @author study
 */
public class CommandLineArgumentParser {

    private final String[] args;

    public CommandLineArgumentParser(final String[] args) {
        this.args = args;
    }

    public CommandLineArguments parse() {
        PlayerType player1Type = null;
        PlayerType player2Type = null;
        UserInterface userInterface = null;
        Level level = null;
        for (final String arg : args) {
            if (USER.name().equalsIgnoreCase(arg) || COMPUTER.name().equalsIgnoreCase(arg)) {
                if (player1Type == null) {
                    player1Type = PlayerType.valueOf(arg.toUpperCase());
                } else if (player2Type == null) {
                    player2Type = PlayerType.valueOf(arg.toUpperCase());
                } else {
                    System.err.printf(
                            "Unnecessary parameter '%s', because player types already set ( '%s' and '%s')%n"
                            , arg
                            , player1Type
                            , player2Type);
                }
            } else if (GUI.name().equalsIgnoreCase(arg) || CONSOLE.name().equalsIgnoreCase(arg)) {
                if (userInterface == null) {
                    userInterface = UserInterface.valueOf(arg.toUpperCase(Locale.ROOT));
                }
            } else if (LEVEL1.name().equalsIgnoreCase(arg)
                    || LEVEL2.name().equalsIgnoreCase(arg)
                    || LEVEL3.name().equalsIgnoreCase(arg)) {
                if (level == null) level = Level.valueOf(arg.toUpperCase(Locale.ROOT));
                else {
                    System.err.printf(
                            "Invalid command line argument '%s' Level already set. '%s'%n"
                            , arg, level
                    );
                }
            } else {
                System.err.printf(
                        "Unsupported command line argument: '%s'%n"
                        , arg
                );
            }
        }
        if (userInterface == null) userInterface = CONSOLE;
        if (level == null) level = LEVEL3;
        if (player1Type == null) {
            return new CommandLineArguments(USER, COMPUTER, userInterface, level);
        } else if (player2Type == null) {
            return new CommandLineArguments(USER, player1Type, userInterface, level);
        } else {
            return new CommandLineArguments(player1Type, player2Type, userInterface, level);
        }
    }

    public static class CommandLineArguments {

        private final PlayerType player1Type;

        private final PlayerType player2Type;

        private final UserInterface userInterface;

        private final Level level;

        private CommandLineArguments(final PlayerType player1Type,
                                     final PlayerType player2Type,
                                     final UserInterface userInterface,
                                     final Level level) {
            this.player1Type = player1Type;
            this.player2Type = player2Type;
            this.userInterface = userInterface;
            this.level = level;
        }

        public PlayerType getPlayer1Type() {
            return player1Type;
        }

        public PlayerType getPlayer2Type() {
            return player2Type;
        }

        public UserInterface getUserInterface() {
            return userInterface;
        }

        public Level getLevel() {
            return level;
        }
    }
}
