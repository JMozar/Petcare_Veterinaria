package Modelo;

public class CalendarioVacuna {
    private int numVacunas;
    private Vacuna[] vacunas;

    private String especie;


    public CalendarioVacuna (String especie) {
        this.especie = especie;
        generarVacunasegunEspecie(especie);
        
    }
    public void añadirVacunas(){
        
    }

    private void generarVacunasegunEspecie(String especie) {
        if (especie == "Perro") {
            this.vacunas = new Vacuna[4];
            this.vacunas[0] = new Vacuna(1,"Polivalente + Distemper + Parvovirus", "1", "10g", "2 meses");
            this.vacunas[1] = new Vacuna(2,"Polivalente", "1", "10g", "4 meses");
            this.vacunas[2] = new Vacuna(3,"Polivalente+Antirrabico", "1", "10g", "6 meses");
            
            this.vacunas[3] = new Vacuna(4,"Polivalente+Antirrabico", "10g", "Anualmente", 0);
            this.numVacunas=4;
        }
        if (especie == "Gato") {
            this.vacunas = new Vacuna[5];
            this.vacunas[0] = new Vacuna(1,"Trivalente Felina", "1", "10g", "2 meses");
            this.vacunas[1] = new Vacuna(2,"Trivalente Felina", "2", "10g", "4 meses");
            this.vacunas[2] = new Vacuna(3,"Leucemia felina", "1", "10g", "6 meses");
            this.vacunas[3] = new Vacuna(4,"Antirabica", "1", "10g", "8 meses");
            
            this.vacunas[4]= new Vacuna(5,"Trivalente Felina + Antirrabico", "20g", "Anualmente", 0);
            this.numVacunas=5;
        }
        
        
    }


    public Vacuna[] devolverVacunas(){
        return this.vacunas;
    }

    public void mostrarCalendario() {
        System.out.println("El calendario de vacunas para los " + especie + " es: ");
        if (especie == "Perro") {
            for(int i=0;i<vacunas.length;i++){
                System.out.println(vacunas[i].toString());
            }
            
        }
        if (especie == "Gato") {
            for(int i=0;i<vacunas.length;i++){
                System.out.println(vacunas[i].toString());
            }
        }

        System.out.println("");
    }
    public String CalendarioVacunas() {
        String texto="";
        for (int i = 0; i < vacunas.length; i++) {
                texto=texto+"\n"+(vacunas[i].toString());
            }
        return texto;
    }

    public int getNumVacunas() {
        return numVacunas;
    }

    public void setNumVacunas(int numVacunas) {
        this.numVacunas = numVacunas;
    }
    
}
