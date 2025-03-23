package command;

import model.Model;

public class TurnRightCommand implements Command {
    private final Model model;

    public TurnRightCommand(Model model) {
        this.model = model;
    }

    @Override
    public void execute() {
        model.turnRight();
    }
}
