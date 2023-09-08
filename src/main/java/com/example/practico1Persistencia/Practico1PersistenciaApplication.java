package com.example.practico1Persistencia;

import com.example.practico1Persistencia.Entidades.*;
import com.example.practico1Persistencia.Repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class Practico1PersistenciaApplication {

    @Autowired
    ConfiguracionGeneralRepository configuracionGeneralRepository;
    @Autowired
    RubroRepository rubroRepository;
    @Autowired
    ProductoRepository productoRepository;
    @Autowired
    DetallePedidoRepository detallePedidoRepository;
    @Autowired
    FacturaRepository facturaRepository;
    @Autowired
    PedidoRepository pedidoRepository;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    DomicilioRepository domicilioRepository;
    @Autowired
    ClienteRepository clienteRepository;


    public static void main(String[] args) {
        SpringApplication.run(Practico1PersistenciaApplication.class, args);
    }

    @Bean
    CommandLineRunner init(){
        return args -> {
            System.out.println("-------------------------FUNCIONANDO-------------------------");

            //DEFINO CONFIGURACION GENERAL
            ConfiguracionGeneral conf = ConfiguracionGeneral.builder()
                    .tokenMercadoPago("rgerg")
                    .cantidadCocineros(5)
                    .emailEmpresa("comidaRica@gmail.com")
                    .build();

            //GUARDO CONFIGURACION GENERAL EN LA BASE DE DATOS
            configuracionGeneralRepository.save(conf);

            //DEFINO LOS RUBROS
            Rubro rubro1 = Rubro.builder()
                    .denominacion("BEBIDA")
                    .build();
            Rubro rubro2 = Rubro.builder()
                    .denominacion("COMIDA")
                    .build();
            Rubro rubro3 = Rubro.builder()
                    .denominacion("POSTRE")
                    .build();

            //DEFINO PRODUCTOS
            Producto rabas = Producto.builder()
                    .denominacion("RABAS")
                    .foto("/.../rabas.jpg/")
                    .precioCompra(3000d)
                    .precioVenta(3500d)
                    .receta(" Delicadas anillas de calamar fresco, sazonadas y recubiertas con un " +
                            "crujiente rebozado de harina de trigo, se doran a la perfección en aceite " +
                            "de oliva, creando una textura crujiente por fuera y un interior tierno y jugoso")
                    .stockActual(3)
                    .stockMinimo(1)
                    .tiempoEstimadoCocina(30)
                    .tipo(Producto.Tipo.MANUFACTURADO)
                    .unidadMedida("kg")
                    .build();

            Producto vino = Producto.builder()
                    .denominacion("VINO TINTO")
                    .foto("/.../vino.jpg/")
                    .precioCompra(1500d)
                    .precioVenta(3000d)
                    .receta(" Elaborado con uvas cuidadosamente seleccionadas y envejecido con mimo" +
                            " en barricas de roble durante años")
                    .stockActual(8)
                    .stockMinimo(5)
                    .tiempoEstimadoCocina(2)
                    .tipo(Producto.Tipo.INSUMO)
                    .unidadMedida("litros")
                    .build();

            Producto tiramisu = Producto.builder()
                    .denominacion("TIRAMISÚ")
                    .foto("/.../tiramisu.jpg/")
                    .precioCompra(1500d)
                    .precioVenta(2000d)
                    .receta("Meticulosamente elaborado con capas de suave mascarpone, delicados bizcochos " +
                            "empapados en café recién hecho y un toque de cacao en polvo ")
                    .stockActual(6)
                    .stockMinimo(3)
                    .tiempoEstimadoCocina(45)
                    .tipo(Producto.Tipo.MANUFACTURADO)
                    .unidadMedida("gramos")
                    .build();

            //ASOCIO LOS PRODUCTOS CON SU RUBRO CORRESPONDIENTE Y GUARDO EN BD
            rubro1.agregarProductos(vino);
            rubro2.agregarProductos(rabas);
            rubro3.agregarProductos(tiramisu);

            rubroRepository.save(rubro1);
            rubroRepository.save(rubro2);
            rubroRepository.save(rubro3);

            productoRepository.save(rabas);
            productoRepository.save(vino);
            productoRepository.save(tiramisu);

            //DEFINO DETALLE PEDIDO
            DetallePedido detallePedido1 = DetallePedido.builder()
                    .cantidad(1)
                    .producto(rabas)
                    .subtotal(3500d)
                    .build();

            DetallePedido detallePedido2 = DetallePedido.builder()
                    .cantidad(2)
                    .producto(tiramisu)
                    .subtotal(2000)
                    .build();

            //DEFINO PEDIDO
            Pedido pedido = Pedido.builder()
                    .tipoEnvio(Pedido.TipoEnvio.DELIVERY)
                    .fecha("7/9/2023")
                    .estado(Pedido.Estado.PREPARACION)
                    .horaEstimada("22:15")
                    .build();

            //ASOCIO DETALLE DE PEDIDO CON PEDIDO Y CALCULO TOTAL
            pedido.agregarDetallePedido(detallePedido1);
            pedido.agregarDetallePedido(detallePedido2);
            pedido.setTotal(pedido.Total());

            //DEFINO FACTURA
            Factura factura = Factura.builder()
                    .descuento(20d)
                    .numero(4235)
                    .fecha(pedido.getFecha())
                    .formaPago(Factura.FormaPago.MPAGO)
                    .build();
            factura.setTotal(factura.Total(pedido));

            //ASOCIO PEDIDO CON FACTURA
            pedido.setFactura(factura);

            //DEFINO USUARIOS
            Usuario usuario1 =Usuario.builder()
                    .rol("CLIENTE")
                    .nombre("Flor")
                    .password("123")
                    .build();
            Usuario usuario2 =Usuario.builder()
                    .rol("EMPLEADO")
                    .nombre("Maria")
                    .password("321A")
                    .build();

            //AGREGO EL PEDIDO A USUARI
            usuario1.agregarPedido(pedido);

            //DEFINO CLIENTE
            Cliente cliente = Cliente.builder()
                    .nombre("Flor")
                    .apellido("Gubiotti")
                    .email("florgubiotti@gmail.com")
                    .telefono("346234523")
                    .build();

            //ASOCIO CLIENTE CON PEDIDO
            cliente.agregarPedidos(pedido);

            //DEFINO DOMICILIO
            Domicilio domicilio1 = Domicilio.builder()
                    .calle("Comodoro")
                    .numero("1470")
                    .localidad("Guaymallén")
                    .build();
            Domicilio domicilio2 = Domicilio.builder()
                    .calle("Ruiseñor")
                    .numero("6373")
                    .localidad("Guaymallén")
                    .build();

            //GUARDO DOMICILIO EN BD
            domicilioRepository.save(domicilio1);
            domicilioRepository.save(domicilio2);

            //ASOCIO DOMICILIO CON CLIENTE
            domicilio1.setClientes(cliente);
            domicilio2.setClientes(cliente);

            //ASOCIO DOMICILIO CON PEDIDO
            domicilio1.agregarPedidos(pedido);

            //GUARDO USUARIO EN BD
            usuarioRepository.save(usuario1);
            usuarioRepository.save(usuario2);

            //GUARDO PEDIDO EN BD
            pedidoRepository.save(pedido);

            //GUARDO CLIENTE EN BD
            clienteRepository.save(cliente);

            //GUARDO DIRECCION NUEVAMENTE EN BD
            domicilioRepository.save(domicilio1);
            domicilioRepository.save(domicilio2);

            //GUARDO FACTURA EN BD
            facturaRepository.save(factura);

            //GUARDO DETALLE DE PEDIDO EN BD
            detallePedidoRepository.save(detallePedido1);
            detallePedidoRepository.save(detallePedido2);


            //RECUPERO LOS DATOS
            System.out.println("-------------------RECUPERANDO DATOS------------------");

            //Recupero Configuracion General
            ConfiguracionGeneral conGralRecuperada = configuracionGeneralRepository.findById(conf.getId()).orElse(null);
            if(conGralRecuperada != null){
                System.out.println("---CONFIGURACION GENERAL---");
                System.out.println("Token de Mercado pago:" + conGralRecuperada.getTokenMercadoPago());
                System.out.println("Email: " + conGralRecuperada.getEmailEmpresa());
                System.out.println("Cantidad de cocineros: " + conGralRecuperada.getCantidadCocineros());
                System.out.println("----------------------------------------------");
            }

            // Recupero Productos
            List<Producto> productos = productoRepository.findAll();
            if (productos != null){
                for (Producto producto : productos) {
                    producto.mostrarProducto();
                    System.out.println("----------------------------------------------");
                }
            }

            // Recupero Rubros
            List<Rubro> rubros = rubroRepository.findAll();
            if (rubros != null){
                for (Rubro rubro : rubros) {
                    rubro.mostrarRubro();
                    System.out.println("-----------------------------------");
                }
            }

            // Recupero Usuarios
            List<Usuario> usuarios = usuarioRepository.findAll();
            if (usuarios != null){
                for (Usuario usuario : usuarios) {
                    usuario.mostrarUsuario();
                    System.out.println("-----------------------------------");
                }
            }

            //Recupero Cliente
            List<Cliente> clientes = clienteRepository.findAll();
            if (clientes != null){
                for (Cliente client : clientes) {
                    client.mostrarCliente();
                    System.out.println("-----------------------------------");
                }
            }

            //Recupero Domicilio
            List<Domicilio> domicilios = domicilioRepository.findAll();
            if (domicilios != null){
                for (Domicilio domicilio : domicilios) {
                    domicilio.mostrarDomicilio();
                    System.out.println("-----------------------------------");
                }
            }

            //Recupero Pedido
            List<Pedido> pedidos = pedidoRepository.findAll();
            if (pedidos != null){
                for (Pedido ped : pedidos) {
                    ped.mostrarPedido();
                    System.out.println("-----------------------------------");
                }
            }

            //Recupero Factura
            List<Factura> facturas = facturaRepository.findAll();
            if (facturas != null){
                for (Factura fact : facturas) {
                    fact.mostrarFactura();
                    System.out.println("-----------------------------------");
                }
            }
        };
    }
}

