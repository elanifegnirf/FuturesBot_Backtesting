import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UpdownNew implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double direction;
	private List<Integer> sgxvol = new ArrayList<Integer>();
	private List<Date> sgxdate = new ArrayList<Date>();

	public static void main(String args[]) {
		try {
			FileInputStream fis = new FileInputStream("Updown.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			UpdownNew up = (UpdownNew) ois.readObject();
			ois.close();

			FileOutputStream fos = new FileOutputStream("Updown.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(up);
			oos.close();
		} catch (Exception ex) {
			System.out.println("Exception thrown during writing Options: "
					+ ex.toString());
		}
	}

	public UpdownNew(int direction) {
		this.direction = direction;
	}

	public UpdownNew() {
	}

	public double getDirection() {
		return direction;
	}

	public void setDirection(double direction) {
		this.direction = direction;
	}
	
	public void addsgxvol(Date date, int input){
		if (sgxvol.size() >= 5) {
			sgxvol.remove(0);
			sgxdate.remove(0);
		}
		sgxvol.add(input);
		sgxdate.add(date);
	}
	
	public List<String> getsgxRec(){
		List<String> list = new ArrayList<String>();
		int i =0;
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyyMMdd");
		for(int vol:sgxvol){
			list.add(dateFormat.format(sgxdate.get(i)) + ":" + vol);
			i++;
		}
		return list;
	}
	
	public List<Integer> getsgxvol() {
		return sgxvol;
	}
	
	public List<Date> getsgxdate() {
		return sgxdate;
	}
}
