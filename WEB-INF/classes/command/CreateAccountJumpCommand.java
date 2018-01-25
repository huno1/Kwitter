package command;

import tera.RequestContext;
import tera.ResponseContext;


class CreateAccountJumpCommand extends AbstractCommand{

    public ResponseContext execute(ResponseContext resc){
        resc.setTarget("createaccount");

        return resc;
    }

}
