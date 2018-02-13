package command;

import tera.*;

class EditProfileJumpCommand extends AbstractCommand{

    public ResponseContext execute(ResponseContext resc){
        resc.setTarget("editprofile");
    	System.out.println("editprofile");

        return resc;
    }

}
