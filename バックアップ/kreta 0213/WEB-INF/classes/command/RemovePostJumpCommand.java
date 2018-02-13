package command;

import tera.*;

class RemovePostJumpCommand extends AbstractCommand{

    public ResponseContext execute(ResponseContext resc){
        resc.setTarget("removepost");

        return resc;
    }

}
