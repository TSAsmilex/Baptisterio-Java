public class User {
    // dni, name
    private String dni;
    private String name;

    public User(String name, String dni){
        this.name = name;
        this.dni  = dni;
    }

    public String getDni() {
        return dni;
    }

    public String getName() {
        return name;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        User o = (User) obj;
        return o.getDni().equals(dni) && o.getName().equals(name);
    }

}
