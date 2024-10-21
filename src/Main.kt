import java.util.*
import java.text.SimpleDateFormat

data class Cliente(
    val Codigo: Int,
    var Nombre: String,
    var Telefono: String,
    var Correo: String
)

data class Orden(
    val Codigo: Int,
    val cliente: Cliente,
    val pago: Pago,
    var TipoComprobante: String,
    var FechaVenta: Date,
    var Estado: EstadoOrden,
    var MontoTotal: Double
)

data class DetalleVenta(
    val Codigo: Int,
    val orden: Orden,
    val producto: Producto?,
    var Cantidad: Int?,
    var Descuento: Double
)

data class Producto(
    val Codigo: Int,
    var Nombre: String,
    var Precio: Double,
    var Impuesto: Double,
)

data class Pago(
    val Codigo: Int,
    var Descripcion: String
)

enum class EstadoOrden {
    PENDIENTE, PAGADO, PROCESANDO, ENVIADO, ENTREGADO
}

fun main() {
    val productos = listOf(
        Producto(1, "Laptop", 1500.00, 0.18),
        Producto(2, "Smartphone", 800.00, 0.18),
        Producto(3, "Tablet", 400.00, 0.18),
        Producto(4, "Monitor", 250.00, 0.18),
        Producto(5, "Teclado", 50.00, 0.18),
        Producto(6, "Mouse", 30.00, 0.18),
        Producto(7, "Impresora", 120.00, 0.18),
        Producto(8, "Disco Duro Externo", 100.00, 0.18),
        Producto(9, "Memoria USB", 20.00, 0.18),
        Producto(10, "Cámara Web", 70.00, 0.18),
        Producto(11, "Proyector", 600.00, 0.18),
        Producto(12, "Auriculares", 45.00, 0.18),
        Producto(13, "Altavoces", 90.00, 0.18),
        Producto(14, "Micrófono", 65.00, 0.18),
        Producto(15, "Router WiFi", 85.00, 0.18),
        Producto(16, "Smartwatch", 220.00, 0.18),
        Producto(17, "Televisor", 1100.00, 0.18),
        Producto(18, "Consola de Videojuegos", 500.00, 0.18),
        Producto(19, "Cargador Portátil", 35.00, 0.18),
        Producto(20, "Batería Externa", 45.00, 0.18)
    )

    val pagos = listOf(
        Pago(1, "Tarjeta de crédito"),
        Pago(2, "Efectivo"),
        Pago(3, "Cheque")
    )

    val cliente = Cliente(1,"","","")
    print("Ingresa tu nombre: ")
    val nombre = readLine()
    cliente.Nombre = nombre.toString()
    print("Ingresa tu telefono: ")
    val telefono = readLine()
    cliente.Telefono = telefono.toString()
    print("Ingresa tu correo: ")
    val correo = readLine()
    cliente.Correo = correo.toString()
    var pago = Pago(0, "")
    var orden = Orden(
        Codigo = 1,
        cliente = cliente,
        pago = pago,
        TipoComprobante = "Factura",
        FechaVenta = Date(),
        Estado = EstadoOrden.PROCESANDO,
        MontoTotal = 0.0
    )

    var _MontoTotal = 0.0
    var ListaDetalle = mutableListOf<DetalleVenta>()
    var n = 0
    while (n == 0){
        println("Elige un producto: ")
        productos.forEach {
                producto -> println(producto.Codigo.toString() + ". " + producto.Nombre)
        }

        print("Producto (Codigo): ")
        val _producto = readLine()
        val codigoProducto: Int? = _producto?.toIntOrNull()
        val producto: Producto = productos.find { it.Codigo == codigoProducto }!!

        print("Cantidad: ")
        val cantidad = readLine()
        val _cantidad: Int = cantidad?.toIntOrNull()!!

        val detalle = DetalleVenta(
            Codigo = 1,
            orden = orden,
            producto = producto,
            Cantidad = _cantidad,
            Descuento = 0.0
        )

        _MontoTotal += _cantidad * producto.Precio

        ListaDetalle.add(detalle)
        println("¿Quieres comprar otro producto? ")
        println("1. Si")
        println("2. No")
        val opcion = readLine()
        if(opcion == "2"){
            n = 1
        }
    }
    orden.MontoTotal = _MontoTotal
    println("Elige un tipo de pago:")
    pagos.forEach {
        pago -> println(pago.Codigo.toString() + ". " + pago.Descripcion)
    }

    println("Esta es tu Orden de Venta ${cliente.Nombre}")
    println("Cliente: ${cliente.Nombre}")
    println("Telefono: ${cliente.Telefono}")
    println("Correo: ${cliente.Correo}")
    val formato = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
    println("Fecha y hora: ${formato.format(orden.FechaVenta)} \n")
    println("Producto \t Precio \t Cantidad")
    ListaDetalle.forEach{
        detalleVenta -> println(detalleVenta.producto!!.Nombre + "\t" + detalleVenta.producto.Precio.toString() + "\t" + detalleVenta.Cantidad!!.toString())
    }
    println()
    println("Monto Total: ${orden.MontoTotal}")


    //println("Hola, ${cliente.Nombre}!")
    
    //println("Hello World!")
}