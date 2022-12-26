import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Start {

	public static void main(String[] args) {

		int jobsNumber=0;
		System.out.println("Enter the number of Jobs");

		Scanner in = new Scanner(System.in);
		jobsNumber=in.nextInt();

		List<Job> listOfJobs = new  ArrayList<Job>();

		while(jobsNumber>0) {
			System.out.println("Enter job start time, end time, and earnings");
			Job job = new Job();
			String startTime=in.next();
			try {
				int time = Integer.parseInt(startTime);
				if(time<0 || time>2359 || startTime.length()!=4) {
					System.out.println("Wrong Start Time value!");
				}
				Date date=new Date();
				int hours = Integer.parseInt(startTime.substring(0,2));
				if(hours < 0 || hours > 23) {
					System.out.println("Wrong hour!");
					break;
				}else {
					date.setHours(hours);
				}
				int mins = Integer.parseInt(startTime.substring(2,4));
				if(mins < 0 || mins > 59) {
					System.out.println("Wrong mins!");
					break;
				}else {
					date.setMinutes(mins);
				}
				job.setStartTime(date);
			}
			catch(Exception ex) {
				System.out.println("Wrong Start Time format!");
			}

			String endTime=in.next();
			try {
				int time = Integer.parseInt(endTime);
				if(time<0 || time>2359 || endTime.length()!=4) {
					System.out.println("Wrong End Time value!");
				}
				Date date=new Date();
				int hours = Integer.parseInt(endTime.substring(0,2));
				if(hours < 0 || hours > 23) {
					System.out.println("Wrong hour!");
					break;
				}else {
					date.setHours(hours);
				}
				int mins = Integer.parseInt(endTime.substring(2,4));
				if(mins < 0 || mins > 59) {
					System.out.println("Wrong mins!");
					break;
				}else {
					date.setMinutes(mins);
				}
				job.setEndTime(date);


				if(job.getEndTime().before(job.getStartTime())) {
					System.out.println("Task can not end before starting!");
					return;
				}
			}
			catch(Exception ex) {
				System.out.println("Wrong End Time format!");
			}

			int profit = in.nextInt();
			job.setProfit(profit);
			listOfJobs.add(job);

			jobsNumber--;

		}
		if(listOfJobs.size()==0) {
			System.out.println("No data found");
		}
		else if(listOfJobs.size()>1) {
			//		sort the array in asc order
			Collections.sort(listOfJobs,Comparator.comparingDouble(Job ::getProfit));


			//		remove the last job of the array (job with most profit)
			listOfJobs.remove(listOfJobs.size()-1);


			System.out.println("Remaining tasks: "+ listOfJobs.size());
			double remainingEarnings=0;
			for(int i=0;i<listOfJobs.size();i++) {
				remainingEarnings += listOfJobs.get(i).getProfit();
			}
			System.out.println("Remaining earnings: "+ remainingEarnings);
		}else {
			System.out.println("Remaining tasks: 1");
			System.out.println("Remaining earnings: "+ listOfJobs.get(0).getProfit());
		}


	}

}
