import java.io.*;

public class ManejoDeArchivos {

    // Método para crear un archivo y escribir en él
    public static void crearArchivo(String ruta, String contenido) {
        try {
            FileWriter escritor = new FileWriter(ruta);
            escritor.write(contenido);
            escritor.close();
            System.out.println("Archivo creado y contenido escrito con éxito.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para leer un archivo y mostrar su contenido
    public static void leerArchivo(String ruta) {
        try {
            BufferedReader lector = new BufferedReader(new FileReader(ruta));
            String linea;
            while ((linea = lector.readLine()) != null) {
                System.out.println(linea);
            }
            lector.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para editar un archivo
    public static void editarArchivo(String ruta, String lineaExistente, String nuevaLinea) {
        try {
            BufferedReader lector = new BufferedReader(new FileReader(ruta));
            StringBuilder contenido = new StringBuilder();
            String linea;
            while ((linea = lector.readLine()) != null) {
                contenido.append(linea).append("\n");
            }
            lector.close();

            int indice = contenido.indexOf(lineaExistente);
            if (indice != -1) {
                contenido.insert(indice + lineaExistente.length() + 1, nuevaLinea + "\n");

                BufferedWriter escritor = new BufferedWriter(new FileWriter(ruta));
                escritor.write(contenido.toString());
                escritor.close();
                System.out.println("Archivo editado con éxito.");
            } else {
                System.out.println("La línea especificada no fue encontrada en el archivo.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para eliminar un archivo
    public static void eliminarArchivo(String ruta) {
        File archivo = new File(ruta);
        if (archivo.delete()) {
            System.out.println("Archivo eliminado con éxito.");
        } else {
            System.out.println("No se pudo eliminar el archivo.");
        }
    }

    public static void main(String[] args) {
        String rutaArchivo = "miarchivo.txt";
        String contenido = "Este es el contenido del archivo.";

        // Ejemplos de uso de los métodos
        crearArchivo(rutaArchivo, contenido);
        leerArchivo(rutaArchivo);
        editarArchivo(rutaArchivo, "contenido", "nueva línea");
        leerArchivo(rutaArchivo);
        //eliminarArchivo(rutaArchivo);
    }
}
