package PTA.Firstquestion;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int year = 0;
        int month = 0;
        int day = 0;
        int choice = input.nextInt();
        if(choice == 1) {
            int m = 0;
            year = Integer.parseInt(input.next());
            month = Integer.parseInt(input.next());
            day = Integer.parseInt(input.next());
            DateUtil date = new DateUtil(year, month, day);
            if(!date.checkInputValidity()) {
                System.out.println("Wrong Format");
                System.exit(0);
            }
            m = input.nextInt();
            if(m < 0) {
                System.out.println("Wrong Format");
                System.exit(0);
            }
            System.out.print(date.getYear() + "-" + date.getMonth() + "-" + date.getDay() + " next " + m + " days is:");
            System.out.println(date.getNextNDays(m).showDate());
        } else if(choice == 2) {
            int n = 0;
            year = Integer.parseInt(input.next());
            month = Integer.parseInt(input.next());
            day = Integer.parseInt(input.next());
            DateUtil date = new DateUtil(year, month, day);
            if(!date.checkInputValidity()) {
                System.out.println("Wrong Format");
                System.exit(0);
            }
            n = input.nextInt();
            if(n < 0) {
                System.out.println("Wrong Format");
                System.exit(0);
            }
            System.out.print(date.getYear() + "-" + date.getMonth() + "-" + date.getDay() + " previous " + n + " days is:");
            System.out.println(date.getPreviousNDays(n).showDate());
        } else if(choice == 3) {
            year = Integer.parseInt(input.next());
            month = Integer.parseInt(input.next());
            day = Integer.parseInt(input.next());
            int anotherYear = Integer.parseInt(input.next());
            int anotherMonth = Integer.parseInt(input.next());
            int anotherDay = Integer.parseInt(input.next());
            DateUtil fromDate = new DateUtil(year, month, day);
            DateUtil toDate = new DateUtil(anotherYear, anotherMonth, anotherDay);
            if(fromDate.checkInputValidity() && toDate.checkInputValidity()) {
                System.out.println("The days between " + fromDate.showDate() +
                        " and " + toDate.showDate() + " are:"
                        + fromDate.getDaysofDates(toDate));
            } else {
                System.out.println("Wrong Format");
                System.exit(0);
            }
        } else {
            System.out.println("Wrong Format");
            System.exit(0);
        }
    }
}


