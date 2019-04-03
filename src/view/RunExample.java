package view;

import controller.MyController;

public class RunExample extends Command {
    private MyController ctrl;
    public RunExample(String key, String description,MyController ctrl){
        super(key,description);
        this.ctrl=ctrl;
    }
    @Override
    public void execute() {
        ctrl.allStep();
        //add exceptions not treated in the controller
    }
}
