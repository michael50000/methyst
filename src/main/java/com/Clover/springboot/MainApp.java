package com.Clover.springboot;




import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.Clover.springboot.Services.CommonService;

@SpringBootApplication
public class MainApp {
	
   public static void main(String[] args) {
      SpringApplication.run(MainApp.class, args);
      CommonService cs=new CommonService();
      
      System.out.println(cs.getStudents());
      //System.out.println("=============Delete list================");
      //cs.delete(3);
      //System.out.println("=============Delete list=======d=========");
      
      
      
      
   } 
}
