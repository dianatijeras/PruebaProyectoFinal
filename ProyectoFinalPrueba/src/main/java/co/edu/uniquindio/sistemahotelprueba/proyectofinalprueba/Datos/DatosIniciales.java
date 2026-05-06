package co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.Datos;

import co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.Enum.Rol;
import co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.Enum.TipoAtraccion;
import co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.model.*;
import co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.servicios.ServicioParque;

import java.time.LocalDateTime;

public class DatosIniciales {

    // ─── VISITANTES ──────────────────────────────────────────────
    public static Visitante VISITANTE_ANA;
    public static Visitante VISITANTE_CARLOS;
    public static Visitante VISITANTE_SOFIA;
    public static Visitante VISITANTE_JUAN;
    public static Visitante VISITANTE_MARIA;

    // ─── OPERADORES ──────────────────────────────────────────────
    public static Operador OPERADOR_JUAN_ZONA1;
    public static Operador OPERADOR_PEDRO_ZONA2;
    public static Operador OPERADOR_LUCIA_ZONA3;

    // ─── ADMINISTRADOR ───────────────────────────────────────────
    public static Administrador ADMIN_DIRECTOR;

    // ─── ZONAS ───────────────────────────────────────────────────
    public static Zona ZONA_AVENTURA;
    public static Zona ZONA_ACUATICA;
    public static Zona ZONA_INFANTIL;

    // ─── ATRACCIONES ─────────────────────────────────────────────
    public static Atraccion MONTANA_RUSA;
    public static Atraccion TORRE_CAIDA;
    public static Atraccion TOBOGAN_GIGANTE;
    public static Atraccion PISCINA_OLAS;
    public static Atraccion CARRUSEL;
    public static Atraccion TREN_INFANTIL;
    public static Atraccion CASA_TERROR;
    public static Atraccion TIROLESA;

    // ─── SHOWS ───────────────────────────────────────────────────
    public static Show SHOW_ACROBATICO;
    public static Show SHOW_MAGICO;
    public static Show SHOW_AGUA;

    // ─── PARQUE ──────────────────────────────────────────────────
    public static Parque PARQUE;

    /**
     * Inicializa todos los datos del sistema. Retorna el parque listo para usar.
     */
    public static Parque cargar(ServicioParque servicioParque) {
        Parque parque = servicioParque.getParque();

        crearPersonas();
        crearZonasYAtracciones(parque);
        construirGrafo(parque);
        asignarOperadores(servicioParque);
        registrarUsuarios(parque);
        crearShows();

        System.out.println("\n Datos iniciales cargados correctamente.");
        System.out.println("   Parque: " + parque.getNombre());
        System.out.println("   Zonas: " + parque.getZonas().size());
        System.out.println("   Atracciones: " + parque.todasLasAtracciones().size());
        System.out.println("   Nodos en grafo: " + parque.getMapa().getNumeroNodos());
        System.out.println("   Senderos: " + parque.getMapa().getNumeroAristas());

        return parque;
    }

    // ─── CREACIÓN DE PERSONAS ────────────────────────────────────

    private static void crearPersonas() {
        VISITANTE_ANA    = new Visitante("VIS-001", "Ana García",      "1001",  25, "hash1", Rol.VISITANTE, 1.65, 150_000.0, null);
        VISITANTE_CARLOS = new Visitante("VIS-002", "Carlos Pérez",    "1002",  17, "hash2", Rol.VISITANTE, 1.55,  80_000.0, null);
        VISITANTE_SOFIA  = new Visitante("VIS-003", "Sofía Ramírez",   "1003",  30, "hash3", Rol.VISITANTE, 1.70, 200_000.0, "foto_sofia.jpg");
        VISITANTE_JUAN   = new Visitante("VIS-004", "Juan Martínez",   "1004",   8, "hash4", Rol.VISITANTE, 1.10,  20_000.0, null);
        VISITANTE_MARIA  = new Visitante("VIS-005", "María López",     "1005",  35, "hash5", Rol.VISITANTE, 1.60, 300_000.0, null);

        // Operadores
        OPERADOR_JUAN_ZONA1  = new Operador("OP-001", "Juan Operador",  "2001", 28, "ophash1", Rol.OPERADOR, ZONA_ACUATICA);
        OPERADOR_PEDRO_ZONA2 = new Operador("OP-002", "Pedro Operador", "2002", 32, "ophash2", Rol.OPERADOR, ZONA_INFANTIL);
        OPERADOR_LUCIA_ZONA3 = new Operador("OP-003", "Lucía Operadora","2003", 26, "ophash3", Rol.OPERADOR, ZONA_AVENTURA);

        // Administrador
        ADMIN_DIRECTOR = new Administrador("ADM-001", "Director TechPark", "3001", 45, "admhash", Rol.ADMINISTRADOR, PARQUE);
    }

    // ─── CREACIÓN DE ZONAS Y ATRACCIONES ─────────────────────────

    private static void crearZonasYAtracciones(Parque parque) {
        // ── Zona Aventura ────────────────────────────────────────
        ZONA_AVENTURA = new Zona("Z-001", "Zona Aventura", 300);

        // id, nombre, tipo, capacidadCiclo, alturaMin(m), edadMin, costoAdicional
        MONTANA_RUSA = new Atraccion("ATR-001", "Montaña Rusa",     TipoAtraccion.MECANICA_ALTURA, 24, 1.40, 12,  5_000.0);
        TORRE_CAIDA  = new Atraccion("ATR-002", "Torre de Caída",   TipoAtraccion.MECANICA_ALTURA, 8,  1.45, 14,  8_000.0);
        TIROLESA     = new Atraccion("ATR-003", "Tirolesa Extrema", TipoAtraccion.MECANICA_ALTURA, 4,  1.50, 16, 12_000.0);
        CASA_TERROR  = new Atraccion("ATR-004", "Casa del Terror",  TipoAtraccion.OTRA,            15, 0.00, 12,  3_000.0);

        ZONA_AVENTURA.agregarAtraccion(MONTANA_RUSA);
        ZONA_AVENTURA.agregarAtraccion(TORRE_CAIDA);
        ZONA_AVENTURA.agregarAtraccion(TIROLESA);
        ZONA_AVENTURA.agregarAtraccion(CASA_TERROR);
        parque.agregarZona(ZONA_AVENTURA);

        // ── Zona Acuática ────────────────────────────────────────
        ZONA_ACUATICA = new Zona("Z-002", "Zona Acuática", 200);

        TOBOGAN_GIGANTE = new Atraccion("ATR-005", "Tobogán Gigante",  TipoAtraccion.ACUATICA, 6,  1.20, 8, 4_000.0);
        PISCINA_OLAS    = new Atraccion("ATR-006", "Piscina de Olas",  TipoAtraccion.ACUATICA, 50, 0.00, 5, 0.0);

        ZONA_ACUATICA.agregarAtraccion(TOBOGAN_GIGANTE);
        ZONA_ACUATICA.agregarAtraccion(PISCINA_OLAS);
        parque.agregarZona(ZONA_ACUATICA);

        // ── Zona Infantil ────────────────────────────────────────
        ZONA_INFANTIL = new Zona("Z-003", "Zona Infantil", 150);

        CARRUSEL      = new Atraccion("ATR-007", "Carrusel Mágico",   TipoAtraccion.OTRA, 20, 0.00, 3, 0.0);
        TREN_INFANTIL = new Atraccion("ATR-008", "Tren Infantil",     TipoAtraccion.OTRA, 30, 0.00, 2, 0.0);

        ZONA_INFANTIL.agregarAtraccion(CARRUSEL);
        ZONA_INFANTIL.agregarAtraccion(TREN_INFANTIL);
        parque.agregarZona(ZONA_INFANTIL);

        // Registrar todas en el catálogo ABB del parque
        for (Zona zona : parque.getZonas()) {
            for (Atraccion atraccion : zona.getAtracciones()) {
                parque.registrarAtraccion(atraccion);
            }
        }
    }

    // ─── CONSTRUCCIÓN DEL GRAFO ──────────────────────────────────

    /**
     * Construye el mapa físico del parque conectando atracciones con senderos.
     * Los pesos representan distancia en metros entre atracciones.
     *
     * Topología del grafo:
     *
     *   [MontañaRusa]──50──[TorreCaída]──80──[Tirolesa]──60──[CasaTerror]
     *         │                                                     │
     *        100                                                    70
     *         │                                                     │
     *   [TobogánGigante]──40──[PiscinaOlas]                [CarruselMágico]──30──[TrenInfantil]
     */
    private static void construirGrafo(Parque parque) {
        parque.getMapa().agregarSendero(MONTANA_RUSA,    TORRE_CAIDA,     50.0);
        parque.getMapa().agregarSendero(TORRE_CAIDA,     TIROLESA,        80.0);
        parque.getMapa().agregarSendero(TIROLESA,        CASA_TERROR,     60.0);
        parque.getMapa().agregarSendero(MONTANA_RUSA,    TOBOGAN_GIGANTE, 100.0);
        parque.getMapa().agregarSendero(TOBOGAN_GIGANTE, PISCINA_OLAS,    40.0);
        parque.getMapa().agregarSendero(CASA_TERROR,     CARRUSEL,        70.0);
        parque.getMapa().agregarSendero(CARRUSEL,        TREN_INFANTIL,   30.0);
    }

    // ─── ASIGNACIÓN DE OPERADORES ────────────────────────────────

    private static void asignarOperadores(ServicioParque servicioParque) {
        servicioParque.asignarOperador(OPERADOR_JUAN_ZONA1,  "Z-001");
        servicioParque.asignarOperador(OPERADOR_PEDRO_ZONA2, "Z-002");
        servicioParque.asignarOperador(OPERADOR_LUCIA_ZONA3, "Z-003");

        ADMIN_DIRECTOR.setParqueGestionado(servicioParque.getParque());
    }

    // ─── REGISTRO DE USUARIOS ────────────────────────────────────

    private static void registrarUsuarios(Parque parque) {
        parque.registrarUsuario(VISITANTE_ANA);
        parque.registrarUsuario(VISITANTE_CARLOS);
        parque.registrarUsuario(VISITANTE_SOFIA);
        parque.registrarUsuario(VISITANTE_JUAN);
        parque.registrarUsuario(VISITANTE_MARIA);
        parque.registrarUsuario(OPERADOR_JUAN_ZONA1);
        parque.registrarUsuario(OPERADOR_PEDRO_ZONA2);
        parque.registrarUsuario(OPERADOR_LUCIA_ZONA3);
        parque.registrarUsuario(ADMIN_DIRECTOR);
    }

    // ─── SHOWS ───────────────────────────────────────────────────

    private static void crearShows() {
        SHOW_ACROBATICO = new Show("SHW-001", "Show Acrobático",
                ZONA_AVENTURA,
                LocalDateTime.now().withHour(14).withMinute(0), 45);
        SHOW_MAGICO     = new Show("SHW-002", "Magia en el Parque",
                ZONA_INFANTIL,
                LocalDateTime.now().withHour(16).withMinute(0), 30);
        SHOW_AGUA       = new Show("SHW-003", "Show Acuático",
                ZONA_ACUATICA,
                LocalDateTime.now().withHour(17).withMinute(30), 60);
    }
}
