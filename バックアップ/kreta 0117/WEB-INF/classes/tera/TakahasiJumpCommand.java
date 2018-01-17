package tera;


class TakahassiJumpCommand extends AbstractCommand{

    public ResponseContext execute(ResponseContext resc){
        resc.setTarget("takahasi");

        return resc;
    }

}
