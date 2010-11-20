import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;


public class MainCMP {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
	      BufferedReader in=new BufferedReader(new FileReader("articles.txt"));
	      Map types= new HashMap();

	      String line= null;
	      while((line= in.readLine()) != null) {
	    	  line= "/iwmnt"+line;
	    	  File file= new File(line);
	    	  if (file.exists() && file.isFile()) {
			      BufferedReader in2=new BufferedReader(new FileReader(file));
			      String line2= null;
			      boolean dtdFinded= false;
			      while((line2= in2.readLine()) != null) {
			    	  int last= line2.indexOf(".dtd\">");
			    	  if (last != -1) {
			    		  int begin= line2.indexOf(" \"");
			    		  String type= line2.substring(begin+2, last+4);
			    		  increase(types, type);
			    		  dtdFinded= true;
			    		  break;
			    	  }
			      }
			      if (!dtdFinded) {
		    		  increase(types, "notDTDT");
			    	  //System.out.println(line);
			      }
	    	  } else {
	    		  increase(types, "fileNotFound");
		    	  //System.out.println(line);
	    	  }
	      }
	      System.out.println(types);

	}
	
	
	static void increase(Map types, String type) {
		  Integer sum= (Integer)types.get(type);
		  if (sum == null) {
			  sum= new Integer(1);
		  } else {
			  sum= new Integer(sum.intValue()+1);
		  }
		  types.put(type, sum);
	}

}
