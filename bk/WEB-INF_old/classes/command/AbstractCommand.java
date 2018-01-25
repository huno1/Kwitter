package command;

import tera.RequestContext;
import tera.ResponseContext;


public abstract class AbstractCommand {
    
    private RequestContext _reqContext;
    
    public void init(RequestContext reqc){
        _reqContext = reqc;
    }
    
    protected RequestContext getRequestContext(){
        return _reqContext;
    }
    
    public abstract ResponseContext execute(ResponseContext resc);
}