package tera;

class MaruyamaCommand extends AbstractCommand{

    public ResponseContext execute(ResponseContext resc){

        resc.setTarget("maruyama");

        return resc;
    }

}
