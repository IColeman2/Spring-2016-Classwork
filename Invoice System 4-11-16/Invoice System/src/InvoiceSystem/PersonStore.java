package InvoiceSystem;
import java.util.HashMap;
import java.util.Map;

public class PersonStore {
	Map<String, Person> data;
	public PersonStore(){
		Map<String, Person> data = new HashMap<String, Person>();
	}
	
	public void add(Person joe){
		data.put(joe.getPersonCode(), joe);
	}
	
	public Person get(String personCode){
		return data.get(personCode);
	}
}
