import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.lang.Math;
import java.util.*; 
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;   
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String input; boolean flag1 = true;

        
        OXC oxc1 = new OXC("LP-1");
        OXC oxc2 = new OXC("LP-2");
        OXC oxc3 = new OXC("LP-3");
        OXC oxc4 = new OXC("LP-4");
        OXC oxc5 = new OXC("LP-5");
        OXC oxc6 = new OXC("LP-6");

        /* Connect OXCs to setup core network */
        new Link(oxc1, oxc2);
        new Link(oxc2, oxc3);
        new Link(oxc3, oxc4);
        new Link(oxc4, oxc5);
        new Link(oxc5, oxc1);
        new Link(oxc1, oxc6);
        new Link(oxc6, oxc3);

        /* Initialize IP routers and connect each one to corresponding OXC */
        IP_Router r1 = new IP_Router("NODE-1");  new Link(r1, oxc1);
        IP_Router r2 = new IP_Router("NODE-2");  new Link(r2, oxc2);
        IP_Router r3 = new IP_Router("NODE-3");  new Link(r3, oxc3);
        IP_Router r4 = new IP_Router("NODE-4");  new Link(r4, oxc4);
        IP_Router r5 = new IP_Router("NODE-5");  new Link(r5, oxc5);
        IP_Router r6 = new IP_Router("NODE-6");  new Link(r6, oxc6);
        
        Scanner sc= new Scanner(System.in);    //System.in is a standard input stream  
        System.out.print("enter the number of to allocate ");  
        int n= sc.nextInt(); 
        int [][] arr = new int[n][4];
        int min=1;
        int max=6;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 4; j++) {
                arr[i][j] =   ThreadLocalRandom.current().nextInt(min, max + 1);
                //System.out.print(arr[i][j] + " ");
            }
 
            System.out.print( "    ");
        }
    
        
        Scanner userIn = new Scanner(System.in);
        int i=0;
        while (true) {
            int var1, var2;
            if (i >= n) { break; }

            System.out.print("\nList of connected node: ");
            for (IP_Router r : IP_Router.routers) {
                System.out.print( r.getName() + " ");
            } System.out.println();

            
            var1 = arr[i][0];
            System.out.print("\nSource node is: "+var1);
            var2 = arr[i][1];
            System.out.print("\nDestination node is : "+var2+'\n');

            i++;
            
            new LightPath(IP_Router.routers.get(var1 - 1), IP_Router.routers.get(var2 - 1)).findLightPath();
        }

        System.out.print("Percentage of the number of lightpaths blocked = " +
                (LightPath.noOfLightPathsReq - LightPath.noOfLightPathsCreated) / (double) LightPath.noOfLightPathsReq * 100 + "%");
    }
}


