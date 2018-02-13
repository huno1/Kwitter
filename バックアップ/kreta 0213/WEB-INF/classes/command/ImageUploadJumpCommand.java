package command;

import tera.*;

class ImageUploadJumpCommand extends AbstractCommand{

    public ResponseContext execute(ResponseContext resc){
        resc.setTarget("imageupload");

        return resc;
    }

}
