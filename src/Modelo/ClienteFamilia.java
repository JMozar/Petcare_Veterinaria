   package Modelo;

public class ClienteFamilia {
    private static int contador=0;
    private int codigo;
    
    private String Apellido_Familia;
    private String Num_Ctab;
    private String Direccion;
    private String telefono;

    private ClientePersonaArreglo integrantesFamilia; //una familia esta compuesta por varias personas
    private MascotaArreglo mascotasFamilia;//las familias tienen varias mascotas

    //constructor
    
    public ClienteFamilia( ) {
    }

    public ClienteFamilia(String Apellido_Familia, String Num_Ctab, String Direccion, String telefono) {
        this.codigo=contador;
        contador++;
        this.Apellido_Familia = Apellido_Familia;
        this.Num_Ctab = Num_Ctab;
        this.Direccion = Direccion;
        this.telefono = telefono;       
        this.integrantesFamilia = new ClientePersonaArreglo(30);
        this.mascotasFamilia = new MascotaArreglo(30);
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getApellido_Familia() {
        return Apellido_Familia;
    }

    public void setApellido_Familia(String Apellido_Familia) {
        this.Apellido_Familia = Apellido_Familia;
    }

    public String getNum_Ctab() {
        return Num_Ctab;
    }

    public void setNum_Ctab(String Num_Ctab) {
        this.Num_Ctab = Num_Ctab;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    //Administrar INTEGRANTES de la familia
    //Agregar integrantes
    
    public void AñadirPersonas(ClientePersona ClientePersona) {  
        //CAMBIAR EL ID A el de la familia
        this.integrantesFamilia.agregar(ClientePersona);
        ClientePersona.setFamilia(this);
        
    }
    
    
    //Eliminar algun integrante de la familia
    public void EliminarPersonaAsginada(int indice) {
        
        integrantesFamilia.devolverPersona(indice).setFamilia(null);
        integrantesFamilia.eliminar(indice);
    }
    
    //Administrar MASCOTAS de la familia
    //añadir mascotas
    public void AñadirMascotas(Mascota mascota) {
        mascotasFamilia.agregar(mascota);
        mascota.setDueño(this);
        
    }
    //eliminar mascotas
    public void EliminarMascotaAsginada(int indice) {//indice es el numero de la mascota a eliminar
        
        mascotasFamilia.devolverMascota(indice).setDueño(null);
        mascotasFamilia.eliminar(indice);
        
        
    }
        public ClientePersonaArreglo getIntegrantesFamilia() {
        return integrantesFamilia;
    }

    public void setIntegrantesFamilia(ClientePersonaArreglo integrantesFamilia) {
        this.integrantesFamilia = integrantesFamilia;
    }

    public MascotaArreglo getMascotasFamilia() {
        return mascotasFamilia;
    }

    public void setMascotasFamilia(MascotaArreglo mascotasFamilia) {
        this.mascotasFamilia = mascotasFamilia;
    }
    

    @Override
    public String toString() {
        return "ClienteFamilia{" +this.codigo+ " - Apellido_Familia=" + Apellido_Familia + ", Num_Ctab=" + Num_Ctab + ", Direccion=" + Direccion + ", telefono=" + telefono + '}';
    }

    public void mostrarIntegrantes() {
        System.out.println("Los integrantes de la familia " + Apellido_Familia + " son: ");
        System.out.println(integrantesFamilia.toString());
    }

    public void mostrarMascotas() {
        System.out.println("Las mascotas de la familia : " + Apellido_Familia + "son");
        System.out.println(mascotasFamilia.toString());
    }

}
