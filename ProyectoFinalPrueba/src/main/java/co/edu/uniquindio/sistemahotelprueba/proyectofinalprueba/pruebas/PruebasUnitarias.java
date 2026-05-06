package co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.pruebas;

import co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.Enum.*;
import co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.Estructuras.*;
import co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.model.*;
import co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.servicios.ServicioAcceso;
import co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.servicios.ServicioAlertas;

public class PruebasUnitarias {

    private static int pruebas = 0;
    private static int exitosas = 0;
    private static int fallidas = 0;


    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════╗");
        System.out.println("║     PRUEBAS UNITARIAS — TECH-PARK UQ            ║");
        System.out.println("╚══════════════════════════════════════════════════╝\n");

        probarListaEnlazada();
        probarArbolBST();
        probarColaPrioridad();
        probarGrafoDijkstra();
        probarReglasNegocio();
        probarSetArbol();

        System.out.println("\n╔══════════════════════════════════════════════════╗");
        System.out.println("║  RESULTADO: " + exitosas + "/" + pruebas + " pruebas pasaron              ║");
        System.out.println("║  Fallidas: " + fallidas + "                                  ║");
        System.out.println("╚══════════════════════════════════════════════════╝");
    }

    // ─── PRUEBA 1: LISTA ENLAZADA ─────────────────────────────────

    private static void probarListaEnlazada() {
        System.out.println("═══ PRUEBA 1: ListaEnlazada ═══");

        // 1a. Inserción al frente
        ListaEnlazada<String> lista = new ListaEnlazada<>();
        lista.insertarAlFrente("C");
        lista.insertarAlFrente("B");
        lista.insertarAlFrente("A");
        afirmar("1a. insertarAlFrente: A queda primero",
                lista.getCabeza().dato.equals("A"));

        afirmar("1b. getTamanio() == 3", lista.getTamanio() == 3);

        String encontrado = lista.buscarPrimero(s -> s.equals("B"));
        afirmar("1c. buscarPrimero('B') encontrado", encontrado != null && encontrado.equals("B"));

        lista.eliminar("B");
        afirmar("1d. eliminar('B') → tamaño == 2", lista.getTamanio() == 2);
        afirmar("1d. 'B' ya no existe", !lista.contiene(s -> s.equals("B")));

        ListaEnlazada<Integer> nums = new ListaEnlazada<>();
        nums.insertarAlFinal(10);
        nums.insertarAlFinal(20);
        nums.insertarAlFinal(30);
        afirmar("1e. insertarAlFinal: orden 10,20,30",
                nums.aLista().toString().equals("[10, 20, 30]"));

        ListaEnlazada<String> vacia = new ListaEnlazada<>();
        afirmar("1f. estaVacia() == true", vacia.estaVacia());

        System.out.println();
    }


    private static void probarArbolBST() {
        System.out.println("═══ PRUEBA 2: ArbolBST ═══");

        ArbolBST<String> arbol = new ArbolBST<>();

        arbol.insertar("ATR-005", "Tobogán");
        arbol.insertar("ATR-002", "Torre");
        arbol.insertar("ATR-008", "Tren");
        arbol.insertar("ATR-001", "Montaña Rusa");
        arbol.insertar("ATR-006", "Piscina");
        afirmar("2a. tamanio == 5 tras insertar 5", arbol.getTamanio() == 5);

        afirmar("2b. buscar('ATR-002') == 'Torre'",
                "Torre".equals(arbol.buscar("ATR-002")));

        afirmar("2c. buscar('ATR-999') == null",
                arbol.buscar("ATR-999") == null);

        java.util.List<String> inorden = arbol.inorden();
        afirmar("2d. inorden[0] == 'Montaña Rusa' (ATR-001 primero)",
                inorden.get(0).equals("Montaña Rusa"));
        afirmar("2d. inorden[4] == 'Tren' (ATR-008 último)",
                inorden.get(4).equals("Tren"));

        arbol.eliminar("ATR-005");
        afirmar("2e. eliminar ATR-005 → tamanio == 4", arbol.getTamanio() == 4);
        afirmar("2e. ATR-005 ya no existe", !arbol.contiene("ATR-005"));
        afirmar("2e. ATR-002 sigue existiendo", arbol.contiene("ATR-002"));

        arbol.insertar("ATR-002", "Torre Actualizada");
        afirmar("2f. clave duplicada actualiza dato",
                "Torre Actualizada".equals(arbol.buscar("ATR-002")));
        afirmar("2f. tamanio no cambia al actualizar", arbol.getTamanio() == 4);

        System.out.println();
    }


    private static void probarColaPrioridad() {
        System.out.println("═══ PRUEBA 3: ColaPrioridad (Min-Heap) ═══");

        Visitante vGeneral  = new Visitante("VT1","General","D1",20,"h", Rol.VISITANTE, 1.60,50000,null);
        Visitante vFastPass = new Visitante("VT2","FastPass","D2",25,"h",Rol.VISITANTE, 1.65,80000,null);
        Visitante vGeneral2 = new Visitante("VT3","General2","D3",22,"h",Rol.VISITANTE, 1.55,30000,null);

        Ticket tGen  = new Ticket("TG1", TipoTicket.GENERAL,   45000, vGeneral);
        Ticket tFP   = new Ticket("TF1", TipoTicket.FAST_PASS, 80000, vFastPass);
        Ticket tGen2 = new Ticket("TG2", TipoTicket.GENERAL,   45000, vGeneral2);

        ColaPrioridad<EntradaEnCola> cola = new ColaPrioridad<>();
        cola.insertar(new EntradaEnCola(vGeneral, tGen));
        cola.insertar(new EntradaEnCola(vFastPass, tFP));
        cola.insertar(new EntradaEnCola(vGeneral2, tGen2));

        EntradaEnCola primero = cola.extraerMinimo();
        afirmar("3a. Fast-Pass sale PRIMERO (RN-C01)",
                primero.getVisitante().getNombre().equals("FastPass"));

        EntradaEnCola segundo = cola.extraerMinimo();
        afirmar("3b. Segundo sale 'General' (llegó primero RN-C02)",
                segundo.getVisitante().getNombre().equals("General"));

        EntradaEnCola tercero = cola.extraerMinimo();
        afirmar("3b. Tercero sale 'General2'",
                tercero.getVisitante().getNombre().equals("General2"));

        afirmar("3c. cola.estaVacia() tras extraer todos", cola.estaVacia());

        ColaPrioridad<EntradaEnCola> cola2 = new ColaPrioridad<>();
        cola2.insertar(new EntradaEnCola(vFastPass, tFP));
        EntradaEnCola peekResult = cola2.peek();
        afirmar("3d. peek() no extrae (tamanio sigue 1)",
                cola2.getTamanio() == 1 && peekResult != null);

        cola2.vaciar();
        afirmar("3e. vaciar() → estaVacia()", cola2.estaVacia());

        System.out.println();
    }


    private static void probarGrafoDijkstra() {
        System.out.println("═══ PRUEBA 4: Grafo y Dijkstra ═══");

        Grafo grafo = new Grafo();
        Atraccion a1 = new Atraccion("G-001","Nodo1",TipoAtraccion.OTRA,10,0,0,0);
        Atraccion a2 = new Atraccion("G-002","Nodo2",TipoAtraccion.OTRA,10,0,0,0);
        Atraccion a3 = new Atraccion("G-003","Nodo3",TipoAtraccion.OTRA,10,0,0,0);
        Atraccion a4 = new Atraccion("G-004","Nodo4",TipoAtraccion.OTRA,10,0,0,0);

        grafo.agregarSendero(a1, a2, 10.0);
        grafo.agregarSendero(a2, a3, 20.0);
        grafo.agregarSendero(a1, a3, 50.0);
        grafo.agregarSendero(a3, a4, 5.0);

        java.util.List<Atraccion> ruta = grafo.dijkstra("G-001", "G-004");
        afirmar("4a. ruta no vacía", !ruta.isEmpty());
        afirmar("4a. ruta comienza en Nodo1", ruta.get(0).getId().equals("G-001"));
        afirmar("4a. ruta termina en Nodo4", ruta.get(ruta.size()-1).getId().equals("G-004"));
        afirmar("4a. ruta tiene 4 nodos (óptima)", ruta.size() == 4);

        java.util.List<Atraccion> rutaBFS = grafo.bfs("G-001", "G-003");
        afirmar("4b. BFS: ruta G-001→G-003 con 2 pasos",
                rutaBFS.size() == 2);

        a4.cambiarEstado(EstadoAtraccion.CERRADA, "Test cierre");
        java.util.List<Atraccion> rutaCerrada = grafo.dijkstra("G-001", "G-004");
        afirmar("4c. RN-G01: ruta vacía si destino CERRADO", rutaCerrada.isEmpty());

        a4.cambiarEstado(EstadoAtraccion.ACTIVA, null);
        java.util.List<Atraccion> rutaMismoNodo = grafo.dijkstra("G-001", "G-001");
        afirmar("4d. origen==destino: ruta tiene 1 nodo", rutaMismoNodo.size() == 1);

        System.out.println();
    }


    private static void probarReglasNegocio() {
        System.out.println("═══ PRUEBA 5: Reglas de Negocio ═══");


        ServicioAlertas alertas   = new ServicioAlertas();
        ServicioAcceso acceso     = new ServicioAcceso(alertas);

        Atraccion montana = new Atraccion("RN-001","MontañaTest",
                TipoAtraccion.MECANICA_ALTURA,10,1.40,12,5000);
        Visitante adulto  = new Visitante("V1","Adulto","D1",25,"h",Rol.VISITANTE, 1.70,50000,null);
        Visitante nino    = new Visitante("V2","Niño","D2",8,"h",Rol.VISITANTE, 1.10,20000,null);
        Visitante sinSaldo= new Visitante("V3","SinSaldo","D3",20,"h",Rol.VISITANTE, 1.60,1000,null);
        Ticket ticketAdulto  = new Ticket("T1",TipoTicket.GENERAL,45000,adulto);
        Ticket ticketNino    = new Ticket("T2",TipoTicket.GENERAL,45000,nino);
        Ticket ticketSinSaldo= new Ticket("T3",TipoTicket.GENERAL,45000,sinSaldo);

        Zona zona = new Zona("Z-TEST","Zona Test",100);
        zona.agregarAtraccion(montana);

        ResultadoAcceso r1 = acceso.procesarAcceso(adulto, montana, ticketAdulto);
        afirmar("5a. Adulto con saldo accede OK", r1.esAutorizado());

        ResultadoAcceso r2 = acceso.procesarAcceso(nino, montana, ticketNino);
        afirmar("5b. Niño (8 años) rechazado por edad mínima 12", !r2.esAutorizado());

        ResultadoAcceso r3 = acceso.procesarAcceso(sinSaldo, montana, ticketSinSaldo);
        afirmar("5c. Sin saldo ($1000 < $5000) rechazado", !r3.esAutorizado());

        Atraccion atrMantenimiento = new Atraccion("RN-002","AtrTest",
                TipoAtraccion.OTRA,10,0,0,0);
        Zona zona2 = new Zona("Z-T2","Zona2",5000);
        zona2.agregarAtraccion(atrMantenimiento);
        Ticket tTest = new Ticket("T4",TipoTicket.GENERAL,45000,adulto);

        for (int i = 0; i < 499; i++) {
            atrMantenimiento.registrarIngreso();
        }
        boolean disparo = atrMantenimiento.registrarIngreso();
        afirmar("5d. Visita 500 dispara mantenimiento", disparo);
        afirmar("5d. Estado cambia a EN_MANTENIMIENTO",
                atrMantenimiento.getEstado() == EstadoAtraccion.EN_MANTENIMIENTO);

        Ticket tExtra = new Ticket("T5",TipoTicket.GENERAL,45000,adulto);
        ResultadoAcceso rCerrada = acceso.procesarAcceso(adulto, atrMantenimiento, tExtra);
        afirmar("5e. Atracción EN_MANTENIMIENTO deniega acceso", !rCerrada.esAutorizado());

        Atraccion acuatica   = new Atraccion("RN-003","Acuática",TipoAtraccion.ACUATICA,10,0,0,0);
        Atraccion mecanica   = new Atraccion("RN-004","Mecánica",TipoAtraccion.MECANICA_ALTURA,10,0,0,0);
        Atraccion otra       = new Atraccion("RN-005","Otra",TipoAtraccion.OTRA,10,0,0,0);
        java.util.List<Atraccion> todas = java.util.Arrays.asList(acuatica, mecanica, otra);

        alertas.activarAlertaClimatica(TipoClima.TORMENTA_ELECTRICA, todas);
        afirmar("5f. Acuática CERRADA por clima", acuatica.getEstado() == EstadoAtraccion.CERRADA);
        afirmar("5f. Mecánica CERRADA por clima", mecanica.getEstado() == EstadoAtraccion.CERRADA);
        afirmar("5f. Otra NO se cierra por clima (RN-AL02)", otra.getEstado() == EstadoAtraccion.ACTIVA);

        System.out.println();
    }


    private static void probarSetArbol() {
        System.out.println("═══ PRUEBA 6: SetArbol (Favoritos) ═══");

        SetArbol<String> set = new SetArbol<>();

        set.agregar("ATR-001", "Montaña Rusa");
        set.agregar("ATR-002", "Torre");
        set.agregar("ATR-003", "Tirolesa");
        afirmar("6a. tamanio == 3 tras 3 inserciones", set.getTamanio() == 3);

        set.agregar("ATR-001", "Montaña Rusa (duplicado)");
        afirmar("6b. Duplicado no aumenta tamaño (RN-H02)", set.getTamanio() == 3);

        afirmar("6c. contiene('ATR-002') == true", set.contiene("ATR-002"));
        afirmar("6c. contiene('ATR-999') == false", !set.contiene("ATR-999"));

        set.eliminar("ATR-002");
        afirmar("6d. eliminar reduce tamaño", set.getTamanio() == 2);
        afirmar("6d. ATR-002 ya no existe", !set.contiene("ATR-002"));

        Visitante vis = new Visitante("VS1","Test","D99",20,"h",Rol.VISITANTE, 1.60,10000,null);
        Atraccion a1  = new Atraccion("FAV-001","FavAtr1",TipoAtraccion.OTRA,5,0,0,0);
        Atraccion a2  = new Atraccion("FAV-002","FavAtr2",TipoAtraccion.OTRA,5,0,0,0);
        vis.agregarFavorito(a1);
        vis.agregarFavorito(a2);
        vis.agregarFavorito(a1);
        afirmar("6e. Visitante.favoritos sin duplicados",
                vis.getFavoritos().size() == 2);
        afirmar("6e. esFavorita('FAV-001') == true",
                vis.esFavorita("FAV-001"));

        System.out.println();
    }


    private static void afirmar(String descripcion, boolean condicion) {
        pruebas++;
        if (condicion) {
            exitosas++;
            System.out.println("  EXITOSA " + descripcion);
        } else {
            fallidas++;
            System.out.println("   FALLO: " + descripcion);
        }
    }
}
