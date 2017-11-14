import java.util.ArrayList;
import java.util.List;


public class Bosque {
	
        public static Bosque instancia;
        
	protected List<Pote> potes;
	
        public static void main(String[] args) {
            instancia = new Bosque();
        }
        
	public Bosque() {
		potes = new ArrayList<Pote>();
		for (int i = 0; i < 20; i++) {
			potes.add(new Pote(i));
		}
	}
	
	
	
	
	public Pote entrada() {
		return potes.get(0);
	}

}
