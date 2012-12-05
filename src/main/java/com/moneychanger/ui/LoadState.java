/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moneychanger.ui;

import java.util.EnumSet;
import java.util.Set;

/**
 *
 * Tracks the progress of loading up Moneychanger.
 * Static Class
 *
 * @author Cameron Garnham
 */
public class LoadState {

    public static enum Stages {

        Init, Opt_InitSettings, Opt_LoadSettings, Opt_UpdateSettings, LoadNativeLibraries, InitOTAPI, SetupPasswordImage, SetupPasswordCallback, LoadWallet, Opt_SwitchWallet, LoadMoneychangerGUI;
    }

    private static class CurrentState {

        private static Stages _state = Stages.Init;
        private static Set<Stages> _completedStages = EnumSet.noneOf(Stages.class);
        private static Set<Stages> _failedStages = EnumSet.noneOf(Stages.class);
        private static Set<Stages> _availabeStages = EnumSet.noneOf(Stages.class);

        static {
            _availabeStages.add(Stages.Opt_InitSettings);
            _availabeStages.add(Stages.LoadNativeLibraries);
        }

        public static Stages getState() {
            return _state;
        }

        public static void setState(Stages state) throws OutOfOrderException {
            if (!_completedStages.contains(_state)
                    && !_failedStages.contains(_state)) {
                throw new OutOfOrderException("You must set the current state as complete or failed first!");
            }

            if (_completedStages.contains(state)) {
                throw new OutOfOrderException("This state has already been completed.");
            }

            if (!_availabeStages.contains(state)) {
                throw new OutOfOrderException("This state is not available");
            }

            _state = state;
            _availabeStages.remove(state);
        }

        public static void setCompletedStage(Stages state) throws OutOfOrderException {
            if (_completedStages.contains(state)) {
                throw new OutOfOrderException("This state has already been completed.");
            }
            _failedStages.remove(state);
            _completedStages.add(state);
        }

        public static void setFailedStage(Stages state) throws OutOfOrderException {
            if (_completedStages.contains(state)) {
                throw new OutOfOrderException("This state has already been completed.");
            }
            _failedStages.add(state);
        }

        public static void addAvailableStage(Stages stage) throws OutOfOrderException {
            if (_completedStages.contains(stage)) {
                throw new OutOfOrderException("We have already completed this stage!");
            }

            if (_state == stage) {
                throw new OutOfOrderException("We are on this stage NOW!");
            }

            _availabeStages.add(stage);
        }

        public static void removeAvailableStage(Stages stage) throws OutOfOrderException {
            _availabeStages.remove(stage);
        }

        public static Boolean isStageComplete(Stages stage) {
            return _completedStages.contains(stage);
        }
    }

    public static Stages getStage() {
        return CurrentState.getState();
    }

    public static void setStageComplete() throws OutOfOrderException {
        CurrentState.setCompletedStage(CurrentState.getState());
        System.out.println("Completed Stage: " + CurrentState.getState().toString());
        System.out.println();
    }

    public static void setStageFailed() throws OutOfOrderException {
        CurrentState.setFailedStage(CurrentState.getState());
        System.err.println("Stage " + CurrentState.getState().toString() + " failed!");
        System.out.println();
    }

    public static Boolean isStageComplete() {
        return CurrentState.isStageComplete(getStage());
    }

    public static Boolean isThisStageComplete(Stages stage) {
        return CurrentState.isStageComplete(stage);
    }

    public static Stages Progress(Stages nextState) throws OutOfOrderException {

        CurrentState.setState(nextState);

        switch (CurrentState.getState()) {
            // Add Next Stage Options

            case Opt_InitSettings:
                CurrentState.addAvailableStage(Stages.Opt_LoadSettings);
                break;

            case Opt_LoadSettings:
                CurrentState.addAvailableStage(Stages.Opt_UpdateSettings);
                break;

            case LoadNativeLibraries:
                CurrentState.addAvailableStage(Stages.InitOTAPI);
                break;

            case InitOTAPI:
                CurrentState.addAvailableStage(Stages.SetupPasswordImage);
                break;

            case SetupPasswordImage:
                CurrentState.addAvailableStage(Stages.SetupPasswordCallback);
                break;

            case SetupPasswordCallback:
                CurrentState.addAvailableStage(Stages.LoadWallet);
                break;

            case LoadWallet:
                CurrentState.addAvailableStage(Stages.Opt_SwitchWallet);
                CurrentState.addAvailableStage(Stages.LoadMoneychangerGUI);
                break;
        }

        System.out.println();
        System.out.println("Started Stage: " + CurrentState.getState().toString());

        return CurrentState.getState();
    }

    public static class OutOfOrderException extends Exception {

        private String locationsChecked;

        public OutOfOrderException() {
            super();             // call superclass constructor
            locationsChecked = "none";
        }

        public OutOfOrderException(String err) {
            super(err);     // call super class constructor
            locationsChecked = err;  // save message
        }

        public String getError() {
            return locationsChecked;
        }
    }
}