import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class ManejoDeExcel {
    private static final String NOMBRE_ARCHIVO = "miarchivo.xlsx";

    // Método para crear un archivo de Excel y escribir en él
    public static void crearArchivoExcel() {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet hoja = workbook.createSheet("MiHoja");

            // Crear una fila y agregar encabezados
            Row filaEncabezados = hoja.createRow(0);
            crearCelda(filaEncabezados, 0, "Nombre");
            crearCelda(filaEncabezados, 1, "Edad");

            // Crear filas y agregar datos
            Row fila1 = hoja.createRow(1);
            crearCelda(fila1, 0, "Juan");
            crearCelda(fila1, 1, 30);

            Row fila2 = hoja.createRow(2);
            crearCelda(fila2, 0, "María");
            crearCelda(fila2, 1, 25);

            // Guardar el libro de trabajo en un archivo
            try (FileOutputStream outputStream = new FileOutputStream(NOMBRE_ARCHIVO)) {
                workbook.write(outputStream);
            }

            System.out.println("Archivo de Excel creado con éxito.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para editar un archivo de Excel
    public static void editarArchivoExcel(Cell celda, String nuevoValor) {
        celda.setCellValue(nuevoValor); // Cambiar el valor de la celda

        // Guardar los cambios en el archivo de Excel
        try (FileOutputStream fos = new FileOutputStream(NOMBRE_ARCHIVO)) {
            celda.getSheet().getWorkbook().write(fos);
            System.out.println("Archivo de Excel editado con éxito.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para eliminar un archivo de Excel
    public static void eliminarArchivoExcel() {
        File archivo = new File(NOMBRE_ARCHIVO);

        if (archivo.delete()) {
            System.out.println("Archivo de Excel eliminado con éxito.");
        } else {
            System.out.println("No se pudo eliminar el archivo de Excel.");
        }
    }

    public static void main(String[] args) {
        // Ejemplo de uso de los métodos
        crearArchivoExcel();

        // Obtener la celda que deseas editar (por ejemplo, segunda fila, primera columna)
        try (FileInputStream fis = new FileInputStream(NOMBRE_ARCHIVO);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet hoja = workbook.getSheetAt(0);
            Row fila = hoja.getRow(1);
            Cell celda = fila.getCell(0);

            // Editar la celda
            editarArchivoExcel(celda, "Nuevo Valor");

        } catch (IOException e) {
            e.printStackTrace();
        }

        eliminarArchivoExcel();
    }

    private static void crearCelda(Row fila, int indice, Object valor) {
        Cell celda = fila.createCell(indice);

        if (valor instanceof String) {
            celda.setCellValue((String) valor);
        } else if (valor instanceof Integer) {
            celda.setCellValue((Integer) valor);
        }
    }
}
