package command;

import model.Model;

public class TurnLeftCommand implements Command {
    private final Model model;

    public TurnLeftCommand(Model model) {
        this.model = model;
    }

    @Override
    public void execute() {
        model.turnLeft();
    }
}