package PAS;
// SOU class
import com.openalpr.jni.Alpr;
import com.openalpr.jni.AlprPlate;
import com.openalpr.jni.AlprPlateResult;
import com.openalpr.jni.AlprResults;

import java.nio.file.Path;
import java.nio.file.Paths;

import javax.jws.soap.SOAPBinding;

import java.nio.file.Files;
public class SOU
{
    public static void main(String[] args) {

        String country = "US";
        String configfile = "../../resources/openalpr/openalpr.conf";
        String runtimeDataDir = "../../resources/openalpr/runtime_data";

        Alpr alpr = new Alpr(country, configfile, runtimeDataDir);
        alpr.setTopN(10);
        alpr.setDefaultRegion("wa");  
        
        System.out.print(alpr.getVersion());
    }
}

