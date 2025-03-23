package command;

import model.Model;

public class AccelerateCommand implements Command {
    private final Model model;

    public AccelerateCommand(Model model) {
        this.model = model;
    }

    @Override
    public void execute() {
        model.accelerate();
    }
}




