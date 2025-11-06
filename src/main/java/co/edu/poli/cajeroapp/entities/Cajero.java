package co.edu.poli.cajeroapp.entities;

import java.util.HashMap;
import java.util.Map;

public class Cajero {
	private Map<Integer, Integer> billetes;
	 public Cajero() {
	        billetes = new HashMap<>();
	        // Inicializacion cajero con valores
	        billetes.put(100000, 10); 
	        billetes.put(50000, 10);  
	        billetes.put(20000, 10);  
	        billetes.put(10000, 10);  
	    }
	 public int getDineroTotal() {
	        int total = 0;
	        
	        for (Map.Entry<Integer, Integer> entry : billetes.entrySet()) {
	            total += entry.getKey() * entry.getValue();
	        }
	        return total;
	    }
	 public Map<Integer, Integer> getBilletesDisponibles() {
	        return billetes;
	   }
	 public boolean retirarDinero(int monto) {
	        if (monto > getDineroTotal()) {
	            //en la cuenta no hay suficiente dinero
	        		return false; 
	        }

	        Map<Integer, Integer> copiaBilletes = new HashMap<>(billetes);
	        int restante = monto;

	        for (int valor : copiaBilletes.keySet().stream().sorted((a, b) -> b - a).toList()) {
	            int disponibles = copiaBilletes.get(valor);
	            int usar = Math.min(disponibles, restante / valor);
	            restante -= usar * valor;
	            copiaBilletes.put(valor, disponibles - usar);
	        }

	        if (restante == 0) {
	            billetes = copiaBilletes;
	            return true;
	        } else {
	            return false; // No se puede dar el monto exacto con los billetes disponibles
	        }
	    }
	 public void agregarBilletes(int valor, int cantidad) {
	        billetes.put(valor, billetes.getOrDefault(valor, 0) + cantidad);
	    }
	 
}
