package util;

import tera.RequestContext;
import beans.PostBean;

import javax.servlet.http.Part;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.text.SimpleDateFormat;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;

@MultipartConfig(maxFileSize=1000000000)
public class ImageUpload{
  public PostBean execute(RequestContext reqc){

  	HttpServletRequest req = (HttpServletRequest)reqc.getRequest();
  	String imagePath = null;
    PostBean pb = new PostBean();
  	try{
	  Part partImageName = req.getPart("file");
      Part partPostContent = req.getPart("postcontent");
	  String name = this.getFileName(partImageName);
      InputStream cnt = partPostContent.getInputStream();
      String content = this.convertInputStreamToString(cnt);
	  String path = req.getServletContext().getRealPath("/web-resources/");

	  partImageName.write(path + "/images/" + name);

  	  pb.setPostMedia("/images" + name);
      pb.setPostContent(content);
      System.out.println(content);
  	}catch(Exception e){
  		e.printStackTrace();
  	}
    return pb;
  }

	private String getFileName(Part part){
		String name = null;
		for(String dispotion : part.getHeader("Content-Disposition").split(";")){
			if(dispotion.trim().startsWith("filename")){
				name = dispotion.substring(dispotion.indexOf("=") + 1).replace("\"", "").trim();
				name = name.substring(name.lastIndexOf("\\") + 1);
				break;
			}
		}

		Date d = new Date();
		SimpleDateFormat d1 = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		String date = d1.format(d);

		name = date.concat(name);
		return name;
	}

    private String convertInputStreamToString(InputStream is) throws IOException {
	    InputStreamReader reader = new InputStreamReader(is, "UTF-8");
	    StringBuilder builder = new StringBuilder();
	    char[] buffer = new char[1024];
	    int read;
	    while (0 <= (read = reader.read(buffer))) {
	        builder.append(buffer, 0, read);
	    }
	    return builder.toString();
    }
}
