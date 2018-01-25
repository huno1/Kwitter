package tera;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AbstractDaoFactory;
import dao.ProductsDao;
import dao.OracleConnectionManager;

import command.AbstractCommand;
import command.CommandFactory;

public class FrontServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res)
    throws IOException, ServletException {
		
        req.setCharacterEncoding("UTF-8");
        
		ApplicationController app = new WebApplicationController();
		
        RequestContext reqc = app.getRequest(req);
        
		ResponseContext resc = app.handleRequest(reqc);
        
		resc.setResponse(res);
		
		app.handleResponse(reqc, resc);
		
    }
    public void doPost(HttpServletRequest req, HttpServletResponse res)
    throws IOException, ServletException {
        
        doGet(req, res);
        
    }
}

/*
abstract class CommandFactory{
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

class InputCommand extends AbstractCommand{
    
    public ResponseContext execute(ResponseContext resc){
        resc.setTarget("input");
        
        return resc;
    }
    
}

class AddProductCommand extends AbstractCommand{
    
    public ResponseContext execute(ResponseContext resc){
        
        RequestContext reqc = getRequestContext();
        
        String[] pid = reqc.getParameter("pid");
        String[] name = reqc.getParameter("name");
        String[] price = reqc.getParameter("price");
        
        Product product = new Product();
        
        product.setPid(pid[0]);
        product.setName(name[0]);
        product.setPrice(price[0]);
		
		//ProductRecord r = new ProductRecord();
		
		//r.setPid(pid);
		//r.setUid(uid);
		
		OracleConnectionManager ocm = OracleConnectionManager.getInstance();
		ocm.beginTransaction();
        
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        
		ProductsDao dao = factory.getProductsDao();
        //ProductsRecordDao rdao = factory.getProductsRecordDao();
        
		dao.addProduct(product);
        //rdao.addProduct(r);
		
		ocm.commit();
		ocm.closeConnection();
        
        resc.setTarget("start");
        
        return resc;
    }
    
}

class GetProductsCommand extends AbstractCommand{
    
    public ResponseContext execute(ResponseContext resc){
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        ProductsDao dao = factory.getProductsDao();
        resc.setResult(dao.getAllProducts());
		
        resc.setTarget("view");
        
        return resc;
    }
    
}
*/

interface ApplicationController {
	RequestContext getRequest(Object request);
	
	ResponseContext handleRequest(RequestContext req);
	
	void handleResponse(RequestContext reqc, ResponseContext resc);
}

class WebApplicationController implements ApplicationController {
	public RequestContext getRequest(Object request){
		RequestContext reqc = new WebRequestContext();
		
		reqc.setRequest(request);
		
		return reqc;
	}
	
	public ResponseContext handleRequest(RequestContext reqc){
		AbstractCommand command = CommandFactory.getCommand(reqc);
		command.init(reqc);
		
		ResponseContext resc = command.execute(new WebResponseContext());
		
		return resc;
	}
	
	public void handleResponse(RequestContext reqc, ResponseContext resc){
		
		HttpServletRequest req = (HttpServletRequest) reqc.getRequest();
		HttpServletResponse res = (HttpServletResponse) resc.getResponse();
		
		req.setAttribute("result", resc.getResult());
		
		RequestDispatcher rd = req.getRequestDispatcher(resc.getTarget());
		
		try{
			rd.forward(req, res);
		}catch(ServletException e){
			
		}catch(IOException ee){
			
		}
	}
}
