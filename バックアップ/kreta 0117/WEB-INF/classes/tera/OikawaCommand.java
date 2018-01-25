package tera;

class OikawaCommand extends AbstractCommand{

    public ResponseContext execute(ResponseContext resc){
        resc.setTarget("oikawa");

        return resc;
    }

}
