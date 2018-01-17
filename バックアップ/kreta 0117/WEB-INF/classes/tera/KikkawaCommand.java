package tera;

class KikkawaCommand extends AbstractCommand{

    public ResponseContext execute(ResponseContext resc){

        resc.setTarget("kikkawa");

        return resc;
    }

}
