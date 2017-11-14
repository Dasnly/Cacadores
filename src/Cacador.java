import java.util.ArrayList;
import java.util.List;


public class Cacador {

	protected String nome;
	protected volatile int moedas;
	protected int qtdCachorros;
	protected List<Cachorro> cachorros;
	
	public Cacador(String _nome, int _qtdCachorros) {
		nome = _nome;
		moedas = 0;
		qtdCachorros = _qtdCachorros;
		cachorros = new ArrayList<Cachorro>();
		for (int i = 0; i < qtdCachorros; i++) {
			cachorros.add(new Cachorro(i, this));
		}
	}
	
	public Cachorro getCachorro(int id) {
		return cachorros.get(id);
	}
	
	public List<Cachorro> getTodosCachorros() {
		ArrayList<Cachorro> aux = new ArrayList<Cachorro>();
		aux.addAll(cachorros);
		return aux;
	}
	
	public synchronized void coletarMoedas(Cachorro c) {
		cachorros.get((c.getID()+1)%qtdCachorros).rotina();
		moedas += c.entregarMoedasDaMochila();
	}
	
	public synchronized int getMoedas() {
		return moedas;
	}

}
