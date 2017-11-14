import java.util.ArrayList;
import java.util.List;


public class Pote {
	
	protected int id;
	protected volatile int moedas;
	protected List<Pote> conexoes;
	protected List<Cachorro> dormindo;
	
	public Pote(int _id) {
		id = _id;
		moedas = 4;
		conexoes = new ArrayList<Pote>();
		dormindo = new ArrayList<Cachorro>();
	}
	
	public int getID() {
		return id;
	}
	
	public synchronized int pegarMoedas(int mochila) {
		int loot = 0;
		if (moedas >= 3)
			loot = 3;
		else
			loot = moedas;
		int soma = mochila+loot;
		if (soma > 20) {
			loot -= soma-20;
		}
		moedas -= loot;
		return loot;
	}
	
	public void adicionarDorminhoco(Cachorro c) {
		dormindo.add(c);
	}
	
	public boolean temDorminhoco() {
		return !dormindo.isEmpty();
	}
        
        public void removerDorminhoco(Cachorro c) {
            dormindo.remove(c);
        }
	
	public synchronized void depositarMoeda() {
		moedas++;
		for (Cachorro c : dormindo) {
			c.notify();
		}
	}
        
        public List<Pote> getConexoesValores() {
            List<Pote> aux = new ArrayList();
            aux.addAll(conexoes);
            return aux;
        }
	
	

}
