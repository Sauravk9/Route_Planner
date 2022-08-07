import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class RouteImp {
    int count = 0;
    private Object showAllConnections;


    public static void main(String[] args) throws IOException {
        RouteImp rimp = new RouteImp();
        Route [] route =rimp.readFile();

       System.out.println("Enter the fromCity name");
        Scanner sc = new Scanner(System.in);
       String fromcity = sc.next();
        System.out.println("Enter the toCity name");
        String toCity = sc.next()

       //Route [] routeDirectFlights =rimp.showDirectFlights(route,fromcity);
        //rimp.dispDirectFlights(routeDirectFlights);
        //rimp.sortDirectFlights(routeDirectFlights);
        rimp.showAllConnections(route,fromcity,toCity);

    }
    public Route[] readFile() throws IOException {

        int index = 0;
        File file = new File("main/resources/routes.csv");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        String line,line1;
        while ((line = br.readLine())!= null)
        {
            if(!line.equals(""))
            {
                count++;
            }
            //line = br.readLine();

        }
        Route r[] = new Route[count];
        //System.out.println(count);
        BufferedReader br1 = new BufferedReader(new FileReader(file));
        while ((line1 = br1.readLine())!= null) {

            if(!line1.equals(""))
            {
                String[] s = line1.split(", ");

                String source = s[0];
                String destination = s[1];
                String time = s[3];
                long distance = Long.parseLong(s[2].trim());

                String cost = s[4];
                //System.out.format("%10s %10s %10s %10s","ToCity    Destination    Distance    Time   Cost");
                System.out.format("%14s %25s %15s %15s",s[0],s[1],s[3],s[4],s[2]);
                System.out.println();
                System.out.println("---------------------------------------------------------------------------------------------------");

                Route route = new Route(source, destination,  distance,time, cost);
                //System.out.println(Arrays.toString(r));
                r[index] = route;
                //count++;
                index++;
                //line1 = br1.readLine();
            }
        }
        return r;
    }
    /*public void disp(String[] route)
    {
        System.out.format("%10,%10,%20,%30", "From","TO","Time","Distance","Cost");
        for(int i = 0;i<route.length;i++)
        {
            String[] route1 = route[i].split(",");
            System.out.format("%10,%10,%20,%30",route[0],route[1],route[2],route[3],route[4]);
        }
    }*/
    public Route[] showDirectFlights(Route[] route,String fromcity) {

        int count1 = 0;
        Route[] k = route;
        Route[] directRoute=null;

        for (Route value : k) {
            if (value.getSource().equalsIgnoreCase(fromcity))
                count1++;
        }
        if(count1!=0)
        {
            int index1 =0;
           directRoute = new Route[count1];


            for(Route r1 : k)
            {
                if(r1.getSource().equalsIgnoreCase(fromcity))
                {
                    directRoute[index1] = r1;
                    //System.out.println(r1.getSource()+""+r1.getDestination()+""+r1.);
                    index1++;
                }

            }
        }
        else
        {
            System.out.println("We are sorry. At this point of time, we do not have any information on flights from"+fromcity+" to your  desierd  destination");
        }
        sortDirectFlights(directRoute);
        //dispDirectFlights(directRoute);
        return directRoute;
    }
    public void dispDirectFlights(Route [] routeDirectFlights)
    {
        for(Route c : routeDirectFlights)
        {
            System.out.println(c.getSource()+" "+c.getDestination()+" "+c.getDistance()+" "+c.getTime()+" "+c.getCost());
        }


    }
    public void sortDirectFlights(Route[] directFlights) {

        Route[] sortRoute = directFlights;


        for (int i = 0; i < sortRoute.length; i++)
        {
            for (int j = 0; j < sortRoute.length - 1 - i; j++)
            {
                if (sortRoute[j+1].getDestination().compareTo(sortRoute[j ].getDestination()) < 0)
                {
                   Route temp = sortRoute[j];
                    sortRoute[j] = sortRoute[j + 1];
                    sortRoute[j + 1] = temp;
                }

            }
            /*for(int k = 0;k<sortRoute.length;k++)
            {
                System.out.println(sortRoute[k]);
            }*/
        }

    }
    public void showAllConnections(Route[] routeInfo, String fromCity, String toCity)
    {
        int check = 0;
        int count =0;

      Route[] directConnected =   showDirectFlights(routeInfo,fromCity);
      if(directConnected != null)
      {
          for (Route r3 : directConnected) {
              if (r3.getDestination().equals(toCity)) {
                  check++;
              }
          }
          Route[] nonDirect = new Route[directConnected.length - check];
          for (int i = 0; i < directConnected.length; i++) {
              if (toCity.equals(directConnected[i].getDestination())) {

                  System.out.println(directConnected[i].getSource()+" "+directConnected[i].getDestination()+" "+directConnected[i].getDistance()+" "+directConnected[i].getTime()+" "+directConnected[i].getCost());
                  System.out.println();
              }
              else
              {
                 nonDirect[count] = directConnected[i];
                 count++;
              }
          }
          for(Route r: nonDirect)
          {
              Node node = new Node(r);

              if(checkAllRoutes(node,routeInfo,toCity)!= null) {
                  dispIntermediateRoutes(node);
              }
          }
      }


    }
    public Node checkAllRoutes(Node sourceRoute, Route [] allRoutes, String toCity)
    {
        if(sourceRoute.getData().getDestination().equals(toCity))
        {
            //System.out.println(sourceRoute.getData().getSource());
            return sourceRoute;

        }
        else
        {
            for(Route r4 : allRoutes)
            {
                if(sourceRoute.getData().getDestination().equals(r4.getSource()))
                {

                    Node newNode= checkAllRoutes(new Node(r4),allRoutes,toCity);
                    if(newNode!=null)
                    {
                        sourceRoute.setNodeNext(newNode);
                        return newNode;
                    }

                }

            }

        }
        return null;
    }
    public void dispIntermediateRoutes(Node node)
    {
        if(node!=null)
        {
            System.out.println(node.getData().getSource()+" "+node.getData().getDestination()+" "+node.getData().getDistance()+" "+node.getData().getTime()+" "+node.getData().getCost());
            dispIntermediateRoutes(node.getNodeNext());
        }
    }
}
