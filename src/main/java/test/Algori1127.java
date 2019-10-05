package test;


import java.util.Scanner;

/**
 *
 */
public class Algori1127 {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        String  str = "";
        while (scan.hasNextLine()) {
            if (scan.nextLine() == null || scan.nextLine()==""){
                break;
            }
            str += scan.nextLine() + "\t";
        }

        System.out.println(str);
    }
}
