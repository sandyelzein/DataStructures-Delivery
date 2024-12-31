
public class Van {

	public int vanid = 0;
	public static int vannum = 0;
	public int reg_plate;
	public PQofOrders p;

	public Van(int plate) {
		reg_plate = plate;
		vanid = vannum + 1;
		vannum++;
	}

	public void setPriorityQueue(PQofOrders pq) {
		p = pq;
	}

	public int getReg_plate() {
		return reg_plate;
	}

	public void setReg_plate(int reg_plate) {
		this.reg_plate = reg_plate;
	}

	public void display() {
		p.pqueuedisplay();
	}

	public String toString() {
		String format = "| %-10s | %-15s |%n";
		StringBuilder sb = new StringBuilder();

		sb.append(String.format(format, "van id", "reg plate"));

		sb.append(String.format(format, vanid, reg_plate));

		return sb.toString();
	}

}
