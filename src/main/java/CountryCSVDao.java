
import java.util.List;


public interface CountryCSVDao {
        List<Country> GetAllPyrmaids();
    void CreateCountry(String [] pyrmaidMetaData);
}
