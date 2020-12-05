package classes;

import java.util.HashMap;
import java.util.Map;

public class Veiculo {
    private int id;
    private String origem;
    private String destino;
    private Map<String, String> assentos = new HashMap<String, String>();

    public Map<String, String> getAssentos() {
        return assentos;
    }

    public void setAssentos(Map<String, String> assentos) {
        this.assentos = assentos;
    }
}
