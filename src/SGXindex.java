import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class SGXindex implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private List<Double> SGXpercent = new ArrayList<Double>();
	private List<Double> SGXHistory = new ArrayList<Double>();
	private List<Double> SGXGap = new ArrayList<Double>();
	private double openindex;

	public static void main(String args[]) {
		try {
			FileInputStream fis = new FileInputStream("SGX.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			SGXindex sgx = (SGXindex) ois.readObject();
			ois.close();
			sgx.setOpenindex(287.3);
			double tmp1= (287.3 / 288.7) -1;
			double tmp2= (8120.0/8152.0) - 1;
			sgx.addGap(tmp1 - tmp2);
			
			FileOutputStream fos;
			fos = new FileOutputStream("SGX.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(sgx);
			oos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public SGXindex() {
	}

	private void addPercent(double input) {
		if (SGXpercent.size() >= 10) {
			SGXpercent.remove(0);
		}
		SGXpercent.add(input);
	}

	private void addHistory(double input) {
		if (SGXHistory.size() >= 10) {
			SGXHistory.remove(0);
		}
		SGXHistory.add(input);
	}

	public List<Double> getAllPercent() {
		return SGXpercent;
	}
	
	public Double getLastPercent() {
		if (SGXpercent.size() > 0)
			return SGXpercent.get(SGXpercent.size() - 1);
		else
			return 0.0d;
	}

	public double getOpenindex() {
		return openindex;
	}

	public void setOpenindex(double input) {
		addHistory(input);
		addPercent((input / this.openindex) - 1);
		this.openindex = input;
	}

	public void setPreSettle(double input) {
		this.openindex = input;
	}
	
	public void addGap(double input){
		if (SGXGap.size() >= 10) {
			SGXGap.remove(0);
		}
		SGXGap.add(input);
	}
	
	public double getlastSGX() {
		if (SGXGap.size() > 0)
			return SGXGap.get(SGXGap.size() - 1);
		else
			return 0;
	}
}
