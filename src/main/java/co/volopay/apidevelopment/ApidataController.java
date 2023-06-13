package co.volopay.apidevelopment;
import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApidataController{
    
    
    @GetMapping("/api/total_items")
    public int getTotalItemsSoldInQ3(
        @RequestParam String startStr,
        @RequestParam String endStr,
        @RequestParam String dept
    )  {
        String csvFilePath = "D:/Desktop/data.csv"; // Replace with the actual path to your CSV file
        
        int totalSales = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            Date start= new SimpleDateFormat("yyyy-mm-dd").parse(startStr);
            Date end= new SimpleDateFormat("yyyy-mm-dd").parse(endStr);
            while ((line = br.readLine()) != null) {
                String[] values = line.split(","); // Assuming CSV data is comma-separated
                String dateString = values[1];
                if(dateString.length()>10){
                String dateStr = dateString.substring(0, 10);
                Date date = new SimpleDateFormat("yyyy-mm-dd").parse(dateStr);
                int comp1 = start.compareTo(date);
                int comp2 =end.compareTo(date);
                if(comp1<=0 && comp2>=0 && dept.equalsIgnoreCase(values[3])){
                    totalSales += Integer.parseInt(values[5]);
                }
            }

                
                }   
            }
            catch(Exception e){e.printStackTrace();}
            return totalSales;
        }
        
}
        