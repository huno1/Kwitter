package command;

import tera.*;

class LoginJumpCommand extends AbstractCommand{

    public ResponseContext execute(ResponseContext resc){
        resc.setTarget("login");
    	System.out.println("loginjump");

        return resc;
    }

}
