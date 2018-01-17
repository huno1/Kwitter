package tera;

class HunoCommand extends AbstractCommand{
    
    public ResponseContext execute(ResponseContext resc){
        
        resc.setTarget("huno");
        
        return resc;
    }
    
}