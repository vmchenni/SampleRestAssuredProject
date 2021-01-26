package Runner;

import io.restassured.path.json.JsonPath;

public class Assignment8 {
    public static void main(String args[]){
        JsonPath js=new JsonPath(PayLoad.fnReturnPayLoad());
//        Print number of courses
        System.out.println(js.getInt("courses.size()"));
        System.out.println("Purchase Amount:-"+js.getInt("dashboard.purchaseAmount"));
        System.out.println("First Course is :-"+js.getString("courses[0].title"));
//        Print all courses and their prices
        for(int i=0;i<js.getInt("courses.size()");i++){
            System.out.println("Course Title is :- "+js.getString("courses["+i+"].title")+" and Prices is "+js.getInt("courses["+i+"].price"));
        }
    }
}
