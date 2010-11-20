import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MainFotos {

	/**
	 * @param args
	 */
	public static void main(String[] args)throws IOException {
		File dir= new File("D:\\personal\\fotos\\timba\\temp");
		char temp= 'A';
		File[] files= dir.listFiles();
		SimpleDateFormat format= new SimpleDateFormat("dd-MM-yy'.jpg'");
		String previousName= null;
		for (File file : files) {
			if (file.getName().endsWith(".jpg")) {
				Date date= new Date(file.lastModified());
				String newName= format.format(date);
				File newFile= new File(dir, newName);
				if (newFile.exists() && newName.equals(previousName)) {
					newFile= new File(dir, newName.replace(".jpg", "-"+(temp++)+".jpg"));
				} else {
					temp='A';
				}
				move(file , newFile);
				newFile.setLastModified(file.lastModified());
				previousName= newName;
				file.deleteOnExit();
			}
		}
		System.exit(0);

	}
	
	private static void move(File from, File to) throws IOException {
		FileInputStream in= new FileInputStream(from);
		FileOutputStream out= new FileOutputStream(to);
		byte[] data= new byte[1024];
		int size= 0;
		while((size= in.read(data)) != -1) {
			out.write(data, 0, size);
		}
		out.flush();
		out.close();
		in.close();
		
	}

}
