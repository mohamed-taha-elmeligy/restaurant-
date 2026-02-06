package com.emts.util;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

public class Console {

    private static final Scanner scanner = new Scanner(System.in);

    private Console(){}

    public static void print(Object s){
        System.out.println(s);
    }

    public static void equals(){
        System.out.println("=================================================================================================================================");
    }
    public static void separator() {
        System.out.println("---------------------------------------------------");
    }
    public static void plus() {
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
    }

    public static void line(){
        System.out.println();
    }

    public static String stringIn(){
        return scanner.nextLine();
    }


    public static int intIn(){
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }

    public static LocalDate dateIn() {
        return LocalDate.parse(scanner.nextLine());
    }


    public static BigDecimal decimalIn(){
        BigDecimal value = scanner.nextBigDecimal();
        scanner.nextLine();
        return value;
    }

    public static boolean boolIn(){
        boolean value = scanner.nextBoolean();
        scanner.nextLine();
        return value;
    }

}
