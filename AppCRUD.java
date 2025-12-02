package Package.model;

import jakarta.persistence.*;
import java.util.List;

public class AppCRUD {

    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("UnidadPeleadores");

    public static void main(String[] args) {

        eliminarTodosPeleadores();
        eliminarTodosEventos();

        Evento evento1 = crearEvento("UFC 300");
        Evento evento2 = crearEvento("Boxeo Mania");
        Evento evento3 = crearEvento("UFC 229");

        Peleador p1 = crearPeleador("Conor McGregor", "MMA", 22, 6, evento1);
        Peleador p2 = crearPeleador("Tyson Fury", "Boxeo", 34, 0, evento2);
        Peleador p3 = crearPeleador("Khabib","MMA",29, 0,evento3);

        leerPeleadores();
        leerEventos();

        actualizarPeleador(p1.getId(), "Conor 'The Notorious' McGregor");
        eliminarPeleador(p2.getId());

        leerPeleadores();
        leerEventos();

        emf.close();
    }

    public static Peleador crearPeleador(String nombre, String estilo, int victorias, int derrotas, Evento evento) {
        EntityManager em = emf.createEntityManager();
        Peleador peleador = new Peleador(nombre, estilo);

        Estadistica stats = new Estadistica(victorias, derrotas);
        stats.setPeleador(peleador);
        peleador.getEstadisticas().add(stats);

        peleador.getEventos().add(evento);
        evento.getPeleadores().add(peleador);

        em.getTransaction().begin();
        em.persist(peleador);
        em.getTransaction().commit();
        em.close();
        return peleador;
    }

    public static Evento crearEvento(String nombre) {
        EntityManager em = emf.createEntityManager();
        Evento evento = new Evento(nombre);
        em.getTransaction().begin();
        em.persist(evento);
        em.getTransaction().commit();
        em.close();
        return evento;
    }

    public static void leerPeleadores() {
        EntityManager em = emf.createEntityManager();
        List<Peleador> lista = em.createQuery("SELECT p FROM Peleador p", Peleador.class).getResultList();
        System.out.println("==== Lista de Peleadores ====");
        lista.forEach(System.out::println);
        System.out.println("=============================");
        em.close();
    }

    public static void leerEventos() {
        EntityManager em = emf.createEntityManager();
        List<Evento> lista = em.createQuery("SELECT e FROM Evento e", Evento.class).getResultList();
        System.out.println("==== Lista de Eventos ====");
        lista.forEach(e -> {
            System.out.println(e);
            e.getPeleadores().forEach(p -> System.out.println("  Participa: " + p.getNombre()));
        });
        System.out.println("=============================");
        em.close();
    }

    public static void actualizarPeleador(Long id, String nuevoNombre) {
        EntityManager em = emf.createEntityManager();
        Peleador peleador = em.find(Peleador.class, id);
        if (peleador != null) {
            em.getTransaction().begin();
            peleador.setNombre(nuevoNombre);
            em.getTransaction().commit();
            System.out.println("Peleador actualizado: " + peleador);
        } else {
            System.out.println("No existe ningún peleador con ID " + id);
        }
        em.close();
    }

    public static void eliminarPeleador(Long id) {
        EntityManager em = emf.createEntityManager();
        Peleador peleador = em.find(Peleador.class, id);
        if (peleador != null) {
            em.getTransaction().begin();
            em.remove(peleador);
            em.getTransaction().commit();
            System.out.println("Peleador eliminado: " + peleador);
        } else {
            System.out.println("No existe ningún peleador con ID " + id);
        }
        em.close();
    }

    public static void eliminarTodosPeleadores() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        List<Peleador> lista = em.createQuery("SELECT p FROM Peleador p", Peleador.class).getResultList();
        
        for (Peleador p : lista) {
            em.remove(p);
        }

        em.getTransaction().commit();
        em.close();
    }

    public static void eliminarTodosEventos() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Evento").executeUpdate();
        em.getTransaction().commit();
        em.close();
    }
}
