import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

class Materia {
    private String nombre;
    private List<Materia> correlativas;

    public Materia(String nombre) {
        this.nombre = nombre;
        this.correlativas = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public List<Materia> getCorrelativas() {
        return correlativas;
    }

    public void agregarCorrelativa(Materia materia) {
        correlativas.add(materia);
    }
}

class Inscripcion {
    
    private List<Materia> materias;

    public Inscripcion() {
        this.materias = new ArrayList<>();
    }

    public void agregarMateria(Materia materia) {
        materias.add(materia);
    }

    public boolean aprobada() {
        for (Materia materia : materias) {
            if (!verificarCorrelatividad(materia)) {
                return false;
            }
        }
        return true;
    }
    
    private boolean verificarCorrelatividad(Materia materia) {
        for (Materia correlativa : materia.getCorrelativas()) {
            if (!materias.contains(correlativa)) {
                return false;
            }
        }
        return true;
    }
}
public class InscripcionTest {

    @Test
    public void testInscripcionAprobada() {
        Materia algoritmos = new Materia("Algoritmos y Estructuras de Datos");
        Materia paradigmas = new Materia("Paradigmas de Programación");
        Materia diseño = new Materia("Diseño de Sistemas");

        paradigmas.agregarCorrelativa(algoritmos);
        diseño.agregarCorrelativa(paradigmas);

        Inscripcion inscripcion = new Inscripcion();
        inscripcion.agregarMateria(diseño);
        inscripcion.agregarMateria(paradigmas);
        inscripcion.agregarMateria(algoritmos);

        assertTrue(inscripcion.aprobada());
    }

    @Test
    public void testInscripcionRechazada() {
        Materia algoritmos = new Materia("Algoritmos y Estructuras de Datos");
        Materia paradigmas = new Materia("Paradigmas de Programación");
        Materia diseño = new Materia("Diseño de Sistemas");

        paradigmas.agregarCorrelativa(algoritmos);
        diseño.agregarCorrelativa(paradigmas);

        Inscripcion inscripcion = new Inscripcion();
        inscripcion.agregarMateria(diseño);
        inscripcion.agregarMateria(algoritmos);

        assertFalse(inscripcion.aprobada());
    }

    @Test
    public void testInscripcionSinCorrelativas() {
        Materia algoritmos = new Materia("Algoritmos y Estructuras de Datos");
        Materia paradigmas = new Materia("Paradigmas de Programación");
        Materia diseño = new Materia("Diseño de Sistemas");

        paradigmas.agregarCorrelativa(algoritmos);

        Inscripcion inscripcion = new Inscripcion();
        inscripcion.agregarMateria(diseño);
        inscripcion.agregarMateria(paradigmas);

        assertTrue(inscripcion.aprobada());
    }

    @Test
    public void testInscripcionVacia() {
        Inscripcion inscripcion = new Inscripcion();
        assertTrue(inscripcion.aprobada());
    }
}

public class Main {
    public static void main(String[] args) {
        // Crear materias
        Materia algoritmos = new Materia("Algoritmos y Estructuras de Datos");
        Materia paradigmas = new Materia("Paradigmas de Programación");
        Materia diseño = new Materia("Diseño de Sistemas");

        // Establecer correlatividades
        paradigmas.agregarCorrelativa(algoritmos);
        diseño.agregarCorrelativa(paradigmas);

        // Crear inscripción
        Inscripcion inscripcion = new Inscripcion();
        inscripcion.agregarMateria(diseño);
        inscripcion.agregarMateria(paradigmas);
        inscripcion.agregarMateria(algoritmos);

        // Verificar si la inscripción está aprobada
        if (inscripcion.aprobada()) {
            System.out.println("La inscripción está aprobada");
        } else {
            System.out.println("La inscripción está rechazada");
        }
        
    }
}


