package org.example.tpo05;

import org.springframework.jmx.export.naming.MetadataNamingStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.zone.ZoneRulesException;

@Controller
public class TimeController {

    private final MetadataNamingStrategy metadataNamingStrategy;

    public TimeController(MetadataNamingStrategy metadataNamingStrategy) {
        this.metadataNamingStrategy = metadataNamingStrategy;
    }

    @GetMapping("/current-time")
    @ResponseBody
    public String getTime(@RequestParam(value = "format", defaultValue = "HH:mm:ss.SSSS dd-MM-yyyy") String format,@RequestParam(value = "timezone", defaultValue = "UTC") String timezone) {
        ZoneId zoneId = null;
        String timeError="";
        try {
            zoneId = ZoneId.of(timezone);
        }catch (ZoneRulesException|IllegalArgumentException e){
            zoneId = ZoneId.of("UTC");
            timeError = e.getMessage();
        }

        ZonedDateTime zonedDateTime = ZonedDateTime.now(zoneId);
        DateTimeFormatter formatter=null;
        String formatError = "";
        try {
            formatter = DateTimeFormatter.ofPattern(format);
        }catch (IllegalArgumentException e) {
            formatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSSS dd-MM-yyyy");
            formatError = e.getMessage();
        }
        String datetime = "<div>"+zonedDateTime.format(formatter) + "</div> <div>" + timeError + "</div><div>" + formatError + "</div>";
        return datetime;
    }
    @GetMapping("/current-year")
    @ResponseBody
    public String getYear() {
        LocalDate localDate = LocalDate.now();
        return localDate.getYear()+"";
    }
}
