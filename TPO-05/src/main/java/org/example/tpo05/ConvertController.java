package org.example.tpo05;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ConvertController {

    @PostMapping("/convert")
    @ResponseBody
    public String convert(@RequestParam(value = "number", defaultValue = "") String number,
                          @RequestParam(value = "fromBase", defaultValue = "") String fromBase,
                          @RequestParam(value = "toBase", defaultValue = "") String toBase) {

        int from=10;
        String fromBaseError = "";
        try {
            from = Integer.parseInt(fromBase);
            if (from<=1 || from>36){
                throw new NumberFormatException("Base must be between 2 and 36");
            }
        }catch (NumberFormatException e){
            fromBaseError= "<div> fromBase Error: "+e.getMessage() + "</div><div>using 10</div> ";
        }
        int to=10;
        String toBaseError = "";
        try {
            to = Integer.parseInt(toBase);
            if (to<=1 || to>36){
                throw new NumberFormatException("Base must be between 2 and 36");
            }
        }catch (NumberFormatException e){
            toBaseError= "<div> fromBase Error: "+e.getMessage() + "</div><div>using 10</div>";
        }
        int num = 0;
        try {
            num = Integer.parseInt(number, from);
        } catch (NumberFormatException e) {
            return "<div> Number Error: "+e.getMessage() + "</div>";
        }
        String original = "<div> ORIGINAL: " + number + "</div>";
        String dec = "<div> DEC: " +num + "</div>";
        String bin = "<div> BIN: " +Integer.toBinaryString(num) + "</div>";
        String oct = "<div> OCT: " +Integer.toOctalString(num) + "</div>";
        String hex = "<div> HEX: "+Integer.toHexString(num) + "</div>";
        String result = "<div> BASE "+ to +": " + Integer.toString(num, to);
        String resultString = "<div class='result-container'><h1>Converted:</h1>" + dec + bin + oct + hex + result  + fromBaseError + toBaseError + "<div <br> <a href='/converter.html'> Back</a></div></div>";
        String head="<head><link rel='stylesheet' href='/stylesheets/style.css'></head>";
        return head+resultString;
    }

}
