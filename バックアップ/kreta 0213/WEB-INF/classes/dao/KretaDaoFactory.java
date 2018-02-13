package dao;

public class KretaDaoFactory extends AbstractDaoFactory{
  public KretaDao getKretaDao(){
    return new KretaDao();
  }
}
