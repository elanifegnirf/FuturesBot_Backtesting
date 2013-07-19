import java.util.LinkedList;
import java.util.Queue;


public class KSindex implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Queue<Double> kspercent = new LinkedList<Double>();
	private double openindex;
	
	public KSindex (){
	}
	
	public void addPercent(double input){
		kspercent.offer(input);
	}
	public double getPercent(){
		return kspercent.poll();
	}

	public double getOpenindex() {
		return openindex;
	}

	public void setOpenindex(double openindex) {
		this.openindex = openindex;
	}
}
