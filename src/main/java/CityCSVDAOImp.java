
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class CityCSVDAOImp {
    
    List<City> Cities = new ArrayList <City>();

    public CityCSVDAOImp( String CSVFileName) {
       Cities =  ReadPyrmaidFromCSV(CSVFileName);
    }
    
    public List<City> GetAllCities() {
        return  Cities;
    }
    
    private List<City> ReadPyrmaidFromCSV(String CSVFileName)
    {
       try {
            //read from File
            BufferedReader _BufferedReader=new BufferedReader(new FileReader(CSVFileName));
            String line = _BufferedReader.readLine();
            do {       
                line= _BufferedReader.readLine();
               if(line != null)
               {
                   String [] attributes = line.split(",",-1);
                        City _City = new City ();
                        _City.setId( Integer.parseInt( attributes[0]));
                        _City.setName(attributes[1]);
                        _City.setPopulation(Integer.parseInt(attributes[2]));
                        _City.setCountryCode(attributes[3]);
                        Cities.add(_City);
               }
           } while (line != null);
            _BufferedReader.close();
        } 
        catch (IOException ex) {
            System.err.println("Error" + ex.getMessage());
        }
       return  Cities;
    }
    
    
}
