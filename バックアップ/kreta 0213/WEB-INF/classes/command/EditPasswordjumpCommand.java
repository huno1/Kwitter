package command;

import tera.*;

class EditPasswordJumpCommand extends AbstractCommand{

    public ResponseContext execute(ResponseContext resc){
        resc.setTarget("editpassword");
    	System.out.println("editpassword");

        return resc;
    }

}
