package command;

import model.Model;

public class StopAccelerateCommand implements Command {

    private final Model model;

    public StopAccelerateCommand(Model model) {
        this.model = model;
    }

    @Override
    public void execute() {
        model.stopAcceleration();
    }

}
