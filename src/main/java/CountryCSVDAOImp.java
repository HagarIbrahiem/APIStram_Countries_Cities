
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class CountryCSVDAOImp {
     List<Country> countries = new ArrayList <Country>();

    public CountryCSVDAOImp( String CSVFileName) {
       countries =  ReadPyrmaidFromCSV(CSVFileName);
    }

    public List<Country> GetAllCountries() {
        return  countries ;
    }

    private List<Country> ReadPyrmaidFromCSV(String CSVFileName)
    {
       try {
            //read from File
            BufferedReader _BufferedReader=new BufferedReader(new FileReader(CSVFileName));
            String line = _BufferedReader.readLine();
            do {       
                line= _BufferedReader.readLine();
               if(line != null)
               {
                   String [] attributes = line.split (",",-1);
                        Country _Country = new Country();
                        _Country.setCode(attributes[0]);
                        _Country.setName(attributes[1]);
                        _Country.setContinent(attributes[2]);
                        _Country.setSurfaceArea(Double.parseDouble(attributes[3]));
                        _Country.setPopulation( Double.parseDouble(attributes[4]));
                        _Country.setGnp(Double.parseDouble ( attributes[5]));
                        _Country.setCapital(Integer.parseInt( attributes[6]) );
                        countries.add(_Country);
               }
           } while (line != null);
            _BufferedReader.close();
        } 
        catch (IOException ex) {
            System.err.println("Error" + ex.getMessage());
        }
       return  countries;
    }
   
}
