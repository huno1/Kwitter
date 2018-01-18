package tera;

public class AddPostJunpCommand extends AbstractCommand{
  public ResponseContext execute(ResponseContext resc){
    RequestContext reqc=getRequestContext();

    resc.setTarget("addpost");
    return resc;
  }
}
