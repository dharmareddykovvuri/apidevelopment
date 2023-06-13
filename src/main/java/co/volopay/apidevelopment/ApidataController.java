package co.volopay.apidevelopment;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.joda.time.format.*;
import org.joda.time.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApidataController{
    
    
    @GetMapping("/api/total_items")
    public int getTotalItemsSoldInQ3(
        @RequestParam("start") String startStr,
        @RequestParam("end") String endStr,
        @RequestParam("dept") String dept
    ) throws IOException, ParseException {
        
        String csvFilePath = "D:/Desktop/data.csv"; // Replace with the actual path to your CSV file
        
        int totalSales = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(","); // Assuming CSV data is comma-separated
                String dateString = values[1];
                 DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ssZ");
                DateTime start = formatter.parseDateTime(startStr);
                DateTime end = formatter.parseDateTime(endStr);
                DateTime dt=formatter.parseDateTime(dateString);
                if(start.isBefore(dt) && dt.isBefore(end) && dept.equals(values[3])){
                    totalSales +=Integer.parseInt(values[5]);
                }
              
                }
                
                
            }
            return totalSales;
        }
        
}
        