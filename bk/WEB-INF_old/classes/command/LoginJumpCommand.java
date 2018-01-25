package tera;


class LoginJumpCommand extends AbstractCommand{

    public ResponseContext execute(ResponseContext resc){
        resc.setTarget("login");

        return resc;
    }

}
