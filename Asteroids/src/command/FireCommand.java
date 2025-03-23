package command;

import model.Model;

public class FireCommand implements Command {
    private final Model model;

    public FireCommand(Model model) {
        this.model = model;
    }

    @Override
    public void execute() {
        model.spawnBullet();
    }
}