package mx.com.devare.coppelstore.modelo;

/**
 * Created by Angel on 30/09/2017.
 */

public class Producto {
    private String id;
    private String uriImagen;
    private String nombre;
    private String precio;
    private String stock;
    private String proveedor;

    public Producto() {
    }

    public Producto(String id, String uriImagen, String nombre, String precio, String proveedor, String stock) {
        this.id = id;
        this.uriImagen=uriImagen;
        this.nombre = nombre;
        this.precio = precio;
        this.proveedor = proveedor;
        this.stock = stock;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUriImagen() {
        return uriImagen;
    }

    public void setUriImagen(String uriImagen) {
        this.uriImagen = uriImagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }
}
