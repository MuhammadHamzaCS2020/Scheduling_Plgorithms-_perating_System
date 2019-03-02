import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class DriverClass {
	final int SIZE=10;
	public static void main(String[] args) {
		DriverClass obj=new DriverClass();
		obj.GenerateProcesses();
	}
	/*
	 * This Function simple generate the processes...
	 */
	public void GenerateProcesses() {
		try {
			String str =null; 
			File TextFile = new File("RandomData.txt");
			FileWriter fw = new FileWriter(TextFile);
	        Random rand = new Random(); 
	        

	        int ArrivalTime,BurstTime,Priority; 
			for(int i=0; i<SIZE; i++) {
				ArrivalTime=rand.nextInt(SIZE);
				BurstTime=rand.nextInt(SIZE+SIZE);
				Priority=rand.nextInt(SIZE);
				str=i+"//"+ArrivalTime+"//"+BurstTime+"//"+Priority;
				fw.write(str);
				fw.write("\n");
			}
			fw.close();

		} catch (IOException iox) {
			iox.printStackTrace();
		}

	}
}
