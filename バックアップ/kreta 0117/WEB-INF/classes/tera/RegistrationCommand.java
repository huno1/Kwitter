package tera;

import dao.AbstractDaoFactory;
import dao.ProductsDao;
import dao.OracleConnectionManager;

class RegistrationCommand extends AbstractCommand{
    
    public ResponseContext execute(ResponseContext resc){
        
        RequestContext reqc = getRequestContext();
        
        String[] loginid = reqc.getParameter("loginid");
        String[] name = reqc.getParameter("name");
        String[] password = reqc.getParameter("password");
        
        Product product = new Product();
        
        product.setPid(loginid[0]);
        product.setName(name[0]);
        product.setPrice(password[0]);
		
		OracleConnectionManager ocm = OracleConnectionManager.getInstance();
		ocm.beginTransaction();
        
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        
		ProductsDao dao = factory.getProductsDao();
        
		dao.addProduct(product);
		
		ocm.commit();
		ocm.closeConnection();
        
        resc.setTarget("start");
        
        return resc;
    }
    
}