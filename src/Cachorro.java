
import java.util.List;


public class Cachorro {
	
	protected int id;
	protected Cacador dono;
	protected volatile int mochila;
	protected Pote local;
	
	public Cachorro(int _id, Cacador _dono) {
		id = _id;
		dono = _dono;
		mochila = 0;
		local = null;
	}
	
	public int getID() {
		return id;
	}
	
	public Cacador getDono() {
		return dono;
	}
	
	public int getMoedasDaMochila() {
		return mochila;
	}
	
	public int entregarMoedasDaMochila() {
		int aux = mochila;
		mochila = 0;
		return aux;
	}
	
	public void adicionarMoedasNaMochila(int q) {
		mochila += q;
		if (mochila == 20) {
			dono.coletarMoedas(this);//REDO possivel estagnacao no pote
		}
	}
	
	public void rotina() {
		local = Bosque.instancia.entrada();
		while (mochila+dono.getMoedas() < 50) {
                        int moedas = local.pegarMoedas(mochila);
                        while (moedas == 0) {
                            try {
                                this.wait(6000);
                            } catch (InterruptedException e) {
                                //Interrompido pelo cachorro vermelho
                                moedas = local.pegarMoedas(mochila);
                                if (moedas == 0) {
                                    try {
                                        this.wait(3000);
                                    } catch (InterruptedException e2) {
                                        //NADA
                                    }
                                }
                            }
                            //moedas = local.pegarMoedas(mochila); <<< não faz sentido
                        }
                        mochila += moedas;
                        if (mochila >= 20) {
                                dono.coletarMoedas(this);
                                break;
                        } else {
                            this.procurarPote();
                        }
		}
	}
        
        public void procurarPote() {
            List<Pote> aux = local.getConexoesValores();
            local = aux.get((int)(Math.random()*aux.size()));
        }
	

}
