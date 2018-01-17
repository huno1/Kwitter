package tera;


class OzakiCommand extends AbstractCommand{

    public ResponseContext execute(ResponseContext resc){
        resc.setTarget("ozaki");

        return resc;
    }

}
