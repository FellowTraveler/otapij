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

        Init, Opt_InitSettings, Opt_LoadSettings, Opt_UpdateSettings, Opt_LoadNativeDependencies, LoadOTAPI, InitOTAPI, SetupPasswordImage, SetupPasswordCallback, LoadWallet, Opt_SwitchWallet, LoadMoneychangerGUI;
    }

    private static class CurrentState {

        private static Stages _state = Stages.Init;
        private static Set<Stages> _completedStages = EnumSet.noneOf(Stages.class);
        private static Set<Stages> _failedStages = EnumSet.noneOf(Stages.class);
        private static Set<Stages> _availabeStages = EnumSet.noneOf(Stages.class);

        static  {
            _availabeStages.add(Stages.Opt_InitSettings);
            _availabeStages.add(Stages.Opt_LoadSettings);
            _availabeStages.add(Stages.Opt_UpdateSettings);
            _availabeStages.add(Stages.Opt_LoadNativeDependencies);
            _availabeStages.add(Stages.LoadOTAPI);
        }

        public static Stages getState() {
            return _state;
        }

        public static void setState(Stages state) throws OutOfOrderException {
            if (!_completedStages.contains(_state)
                    && !_failedStages.contains(_state)) {
                throw new OutOfOrderException("You Must Set The current Stage as complete or failed first!");
            }

            if (_completedStages.contains(state)) {
                throw new OutOfOrderException("This Stage Have Already Been Completed!");
            }

            if (!_availabeStages.contains(state)) {
                throw new OutOfOrderException("This Stage is not Availabe");
            }

            _state = state;
            _availabeStages.remove(state);
        }

        public static void setCompletedStage(Stages state) throws OutOfOrderException {
            if (_completedStages.contains(state)) {
                throw new OutOfOrderException("This State Have Already Been Completed!");
            }
            _failedStages.remove(state);
            _completedStages.add(state);
        }

        public static void setFailedStage(Stages state) throws OutOfOrderException {
            if (_completedStages.contains(state)) {
                throw new OutOfOrderException("This State Have Already Been Completed!");
            }
            _failedStages.add(state);
        }

        public static void addAvailableStage(Stages stage) throws OutOfOrderException {
            if (_completedStages.contains(stage)) {
                throw new OutOfOrderException("We have already completed this Stage!");
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
    }

    public static void setStageFailed() throws OutOfOrderException {
        CurrentState.setFailedStage(CurrentState.getState());
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

            case LoadOTAPI:
                CurrentState.addAvailableStage(Stages.InitOTAPI);
                break;

            case InitOTAPI:
                CurrentState.addAvailableStage(Stages.SetupPasswordImage);
                break;
                
            case SetupPasswordImage :
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