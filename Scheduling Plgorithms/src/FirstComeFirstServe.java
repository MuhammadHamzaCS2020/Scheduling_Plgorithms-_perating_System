import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FirstComeFirstServe {
	final int SIZE=10;
	private int[] ProcessID=new int[SIZE];
	private int[] ArrivalTime=new int[SIZE];
	private int[] BurstTime=new int[SIZE];
	private int[] WaitingTime=new int[SIZE];
	private int TotalWaitingTime=0;
	private int TotalTurnAroundTime=0;

	public FirstComeFirstServe() {

	}

	// This Function Accept a string and convert it into Integer....
	public int ConvertStringToInt(String str) {
		int sum=0,i=0,temp;
		while(i<str.length()) {
			temp=str.charAt(i)-48;
			sum=sum*10;
			sum+=temp;
			i++;
		}
		return sum;
	}
	/*
	 * This function load processes from file to data structure's respectively...
	 */
	public void LoadData() {
		try {
			File TextFile = new File("RandomData.txt");
			BufferedReader fw = new BufferedReader(new FileReader(TextFile));
			String str=null;
			String[] Spliter=null;
			int i=0;
			while ((str = fw.readLine()) != null) {
				//System.out.println(str);
				Spliter=str.split("//");
				this.ProcessID[i]=ConvertStringToInt(Spliter[0]);
				this.ArrivalTime[i]=ConvertStringToInt(Spliter[1]);
				this.BurstTime[i]=ConvertStringToInt(Spliter[2]);
				i++;				
			}
			fw.close();
		} catch (IOException iox) {
			iox.printStackTrace();
		}
	}
	/*
	 * Simple display the processes
	 */
	public void Display() {
		System.out.println("PID\tATime\tBTime");
		for (int i = 0; i < ProcessID.length; i++) {
			System.out.println(this.ProcessID[i]+"\t"+this.ArrivalTime[i]+"\t"+this.BurstTime[i]);				
		}
	}
	/*
	 * This function sort on the base of Arrival time 
	 */
	public void ArrivalTimeSorting() {
		for(int i=0; i<ProcessID.length; i++) {
			for(int j=0; j<ProcessID.length; j++) {
				if(ArrivalTime[i]<this.ArrivalTime[j]) {
					Swapping(i,j);
				}
			}
		}
	}
	/*
	 * This function simply swap the i and j indexes of all Attributes of of same process
	 */
	public void Swapping(int i,int j) {
		// process Swapping
		ProcessID[i]=ProcessID[i]+ProcessID[j];
		ProcessID[j]=ProcessID[i]-ProcessID[j];
		ProcessID[i]=ProcessID[i]-ProcessID[j];
		// ArrivalTime Swapping
		ArrivalTime[i]=ArrivalTime[i]+ArrivalTime[j];
		ArrivalTime[j]=ArrivalTime[i]-ArrivalTime[j];
		ArrivalTime[i]=ArrivalTime[i]-ArrivalTime[j];
		// BurstTime Swapping
		BurstTime[i]=BurstTime[i]+BurstTime[j];
		BurstTime[j]=BurstTime[i]-BurstTime[j];
		BurstTime[i]=BurstTime[i]-BurstTime[j];
	}
	/*
	 * This Function Return the sum of all less from index t...
	 */
	public int BurstTimeSum(int t) {
		int sum=0;
		for(int i=0; i<t; i++) {
			sum=sum+this.BurstTime[i];
		}
		return sum;
	}
	/*
	 * Calculate the Waiting, Response, and TurnAround Time 
	 */
	public void FCFS_Simulation() {

		for(int i=ProcessID.length-1; i>0; i--) {
			this.WaitingTime[i]=(this.ArrivalTime[0]+BurstTimeSum(i))-this.ArrivalTime[i];
		}
		int sum=0;
		System.out.println();
		for(int i=0;i<ProcessID.length; i++) {
			//System.out.println(i+"  "+WaitingTime[i]);
			this.TotalWaitingTime=this.TotalWaitingTime+WaitingTime[i];
			this.TotalTurnAroundTime=this.TotalTurnAroundTime+(WaitingTime[i]+BurstTime[i]);

		}
		System.out.println("Averge Waiting Time: "+(double)this.TotalWaitingTime/this.ProcessID.length);
		System.out.println("Averge Turn Around Time: "+(double)this.TotalTurnAroundTime/this.ProcessID.length);
		System.out.println("Averge Response Time: "+(double)this.TotalWaitingTime/this.ProcessID.length);

	}

	public static void main(String[] args) {
		FirstComeFirstServe obj=new FirstComeFirstServe();
		obj.LoadData();
		obj.Display();
		System.out.println("After the Sorting");
		obj.ArrivalTimeSorting();
		obj.Display();

		obj.FCFS_Simulation();
	}

}
