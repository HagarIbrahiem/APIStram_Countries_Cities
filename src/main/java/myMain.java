
import java.io.IOException;
import java.util.Comparator;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.stream.Collector;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.Set;
import java.util.Map;
import java.util.List;
import java.util.Optional;

// 
// Decompiled by Procyon v0.5.36
// 

public class myMain {
    
     public static void main(final String[] args) {
         CityCSVDAOImp _CityCSVDAOImp = new CityCSVDAOImp("D:\\Cities.csv");
         List<City> cityLst = (List<City>)_CityCSVDAOImp.GetAllCities();
         CountryCSVDAOImp _CountryCSVDAOImp = new CountryCSVDAOImp("D:\\Countries.csv");
         List<Country> countryLst = (List<Country>)_CountryCSVDAOImp.GetAllCountries();
        
         //MapCountryCities(cityLst);
        
       // SortCities(cityLst);
        
        //GetCountryPopulation(countryLst);
         
        //GetAverageCountryPopulation(countryLst);
        
        //GetMaxCountryPopulation(countryLst);
        
        GetHighestPopulationCityPerCountry (cityLst );
        
        GetHighestPopulationCapital (cityLst ,countryLst );
  
    }
     
     public static void MapCountryCities( List<City> citylst) {
            Map<String, Set<String>> cityCountryMap = citylst.stream()
                                      .collect(
                                           Collectors.groupingBy(
                                               City::getCountryCode, 
                                           Collectors.mapping(City::getName,Collectors.toSet())
                                                              ));

        for (Map.Entry<String,Set<String>> entry : cityCountryMap.entrySet())
            System.out.println("Key = " + entry.getKey() +
                             ", Value = " +entry.getValue());
    }
    
    public static void SortCities( List<City> cityLst) {
        try {
             InputStreamReader _InputStreamReader = new InputStreamReader(System.in);
             BufferedReader _BufferedReader = new BufferedReader(_InputStreamReader);
            System.out.println(" Please Enter Country Code");
             String _countryCode = _BufferedReader.readLine();
             
             cityLst.stream().filter(c -> c.getCountryCode().equals(_countryCode) ).sorted(Comparator.comparingDouble(City::getPopulation).reversed()).forEach(c-> System.out.println(c.getName() + " " + c.getPopulation()));
        }
        catch (IOException ex) {
           
        }
    }

     public static void GetCountryPopulation( List<Country> countryLst) {
     
      countryLst.stream().forEach(c -> System.out.println(c.getName () + " : " + c.getPopulation()));
     }
     
     public static void GetAverageCountryPopulation( List<Country> countryLst) {
         System.out.println("Average Country Population  = " + 
               countryLst.stream() .mapToDouble(Country::getPopulation) .average().getAsDouble());                     
     }
     
    public static void GetMaxCountryPopulation( List<Country> countryLst) {
         System.out.println("Max Country Population  = " + 
               countryLst.stream() .mapToDouble(Country::getPopulation) .max().getAsDouble());                     
     }
    
    public static void GetHighestPopulationCityPerCountry (List<City> citylst ) {
      Map<String, List<City>> CountrycodeToCitiesMap = citylst.stream()
                                               .collect(Collectors.groupingBy(City::getCountryCode));
      
        List<City> ListofCityPerCountry= new ArrayList<City>();
       for ( Map.Entry<String,List<City>> entry : CountrycodeToCitiesMap.entrySet())
       {
            for (City c  : CountrycodeToCitiesMap.get(entry.getKey()))
                ListofCityPerCountry = entry.getValue();
                Optional<City> highestPopulationCityWrapper= entry.getValue().stream().collect(Collectors.maxBy(Comparator.comparingDouble(City::getPopulation)));
               System.out.println("Country :" + entry.getKey() + " Highest City ( " +   highestPopulationCityWrapper.get().getName() + " ) With Population of : " + highestPopulationCityWrapper.get().getPopulation()  );
       }
    }
    
    public static void GetHighestPopulationCapital (List<City> citylst , List<Country> countrylst )
    {
      List<Integer> CapitalsIDs = countrylst.stream()
                             .map(Country::getCapital)
                             .collect(Collectors.toList());
      List <City> capitals = new ArrayList<City>() ;
     for (Integer capitalID : CapitalsIDs)
     {
         for ( City city : citylst)
         {
             if (capitalID == city.getId())
             {
                 capitals.add(city);
             }
         }
     }
       City highiestPopulationCapital =  citylst.stream().max(Comparator.comparingDouble(City :: getPopulation)).get();
        System.out.println("Capital City With Highest Population is : " +highiestPopulationCapital.getName() +" With Population of " + highiestPopulationCapital.getPopulation());
    }
  }
    

