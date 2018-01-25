package tera;


class FirstCommand extends AbstractCommand{
    
    public ResponseContext execute(ResponseContext resc){
        resc.setTarget("func1");
        
        return resc;
    }
    
}