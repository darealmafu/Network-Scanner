package zone.mafu;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your network prefix with dots. (ex: 192.168.1.)"+
                "\nThe example should be your network prefix on most routers, or 182.168.0."+
                "\nSome networks, however, have different prefixes, which is why I leave this as an option."
                +"\nIf I ever update this program later, I'll add automatic detection of this."); //although i'm not sure if you can have things be changed on the third number on a network. i can't remember if i've seen that before or not. pls no bully.
        String networkPrefix = sc.nextLine();

        String cmd = "";
        if(System.getProperty("os.name").startsWith("Windows")) {
            // For Windows
            // I don't have a windows computer to check if this works but I read on microsoft's website that they use /n
            cmd = "ping /n 1 " + networkPrefix;
        } else {
            // For Linux and MacOS
            cmd = "ping -c 1 " + networkPrefix;
        }

        Process myProcess;
        for(int i = 0; i < 256; i++) {
            try {
                myProcess = Runtime.getRuntime().exec(cmd + i);
                myProcess.waitFor();

                if (myProcess.exitValue() == 0) {
                    System.out.println(networkPrefix + i + " status: ONLINE");
                } else {
                    System.out.println(networkPrefix + i + " status: UNREACHABLE");
                }
            } catch (Exception e) {
                System.err.println(e);
            }
        }

        /**
         * in the future i could make it so that it doesn't have to wait for one to finish to start the next, possibly via multithreading, although there might be a more simple approach.
         * to be frank i wrote this at ~10:45pm because i realized i didn't program anything today (i've been trying to make it a habit to program at least once a day)
         * since i'm going to bed soon (and i promised my friend i'd go to sleep before 11:15) i'll leave the program as-is and (maybe) work on / improve it later
         */

    }
}
