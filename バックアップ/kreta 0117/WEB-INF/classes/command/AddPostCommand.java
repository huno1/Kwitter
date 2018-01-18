package command;

class AddPostCommand extends AbstractCommand{

    public ResponseContext execute(ResponseContext resc){
      RequestContext reqc = getRequestContext();

      String[] postContent = reqc.getParameter("postcontent");

      PostBean pb = new PostBean();

      pb.setPPostContent(postContent[0]);

      OracleConnectionManager ocm = OracleConnectionManager.getInstance();
  		ocm.beginTransaction();

      Connection cn = null;
      PreparedStatement st = null;

      try{

        cn = OracleConnectionManager.getInstance().getConnection();

        String sql = "insert into Post(pPostId ,pPostContent) values(postid_seq.nextval,?)";

        st = cn.prepareStatement(sql);

        st.setString(1, p.getPid());
        st.executeUpdate();

      }catch(Exception e){
      e.printStackTrace();
      }finally{

    }

      AbstractDaoFactory factory = AbstractDaoFactory.getFactory();

  		ProductsDao dao = factory.getProductsDao();

  		ocm.commit();
  		ocm.closeConnection();

        resc.setTarget("start");

        return resc;
    }

}
