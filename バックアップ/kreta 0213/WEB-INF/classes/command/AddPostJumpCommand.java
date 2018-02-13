package command;

import tera.*;

class AddPostJumpCommand extends AbstractCommand{

    public ResponseContext execute(ResponseContext resc){
        resc.setTarget("addpost");
    	System.out.println("addpost");

        return resc;
    }

}
