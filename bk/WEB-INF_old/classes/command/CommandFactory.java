package command;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import tera.RequestContext;
import tera.ResponseContext;

public abstract class CommandFactory{
    public static AbstractCommand getCommand(RequestContext rc){
        
        AbstractCommand command = null;
        
        Properties prop = new Properties();
        
        try{
            // WEB-INF/properties
			prop.load(CommandFactory.class.getClassLoader().getResourceAsStream("../properties/command.properties"));
            
            String name = prop.getProperty(rc.getCommandPath());
            
            Class c = Class.forName(name);
            
            command = (AbstractCommand)c.newInstance();
            
        }catch(FileNotFoundException fe){
            throw new RuntimeException(fe.getMessage(), fe);
        }catch(IOException ioe){
            throw new RuntimeException(ioe.getMessage(), ioe);
        }catch(ClassNotFoundException ce){
            throw new RuntimeException(ce.getMessage(), ce);
        }catch(InstantiationException ie){
            throw new RuntimeException(ie.getMessage(), ie);
        }catch(IllegalAccessException iae){
            throw new RuntimeException(iae.getMessage(), iae);
        }
        
        return command;
    }
}