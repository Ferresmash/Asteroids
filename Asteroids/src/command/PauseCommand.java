package command;

import controller.Controller;

public class PauseCommand implements Command {
    private final Controller controller;

    public PauseCommand(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void execute() {
        if (controller.isRunning()) {
            controller.pause();
        } else {
            controller.start();
        }
    }
}