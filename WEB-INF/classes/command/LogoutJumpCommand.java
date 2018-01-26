package command;

import tera.*;

class LogoutJumpCommand extends AbstractCommand{

    public ResponseContext execute(ResponseContext resc){
        resc.setTarget("logout");
    	System.out.println("logoutjump");

        return resc;
    }

}
