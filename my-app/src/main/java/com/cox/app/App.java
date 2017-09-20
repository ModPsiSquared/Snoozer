package com.cox.app;


public class App {
    public static void main(String[] args) {

        try {
            int w = Integer.parseInt(args[0]);
            float p = Float.parseFloat(args[1]);

            TimeLineSolver tls = new TimeLineSolver(w,p);
            TimeLine solution = tls.solve();

            if (solution != null) {
                System.out.println("Solution:");
                System.out.println(solution.formatString());
            } else {
                System.out.println("No solution for:");
                System.out.println("\tw:" + String.valueOf(w));
                System.out.println("\tp:" + String.valueOf(p));
            }

        } catch (TimeLineIllegalParameter tlip) {
            printUsage(tlip.getMessage());
        } catch (Exception e){
            printUsage();
        }


    }
    public static void printUsage(){
        printUsage(null);
    }

    public static void printUsage(String errorMessage) {
        System.out.println("*************************************************************");
        if ((errorMessage != null) && (!errorMessage.isEmpty())) {
            System.out.println(errorMessage);
        }
        System.out.println("First parameter should be w");
        System.out.println("Second paramter should be p");
        System.out.println("*************************************************************");
    }
}




