//https://github.com/lopezperezjuancarlos/Funcional_listas
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Main {

    // Supplier: entrega las listas de datos (no recibe nada, regresa algo)
    static Supplier<List<Integer>> obtenerNumeros = () -> Arrays.asList(12, -5, 78, 33, 50, 64, 7, -20, 91, 46, 18, 55);
    static Supplier<List<Double>> obtenerCelsius = () -> Arrays.asList(0.0, 25.0, 37.5, 100.0, -10.0);
    static Supplier<List<String>> obtenerNombres = () -> Arrays.asList("Ana", "Carlos", "Luis", "Fernanda", "Pedro", "Sofía", "Miguel", "María", "Eva");

    // BiConsumer: recibe la etiqueta y el resultado, y los imprime
    static BiConsumer<String, Object> imprimir = (etiqueta, resultado) -> System.out.println(etiqueta + ": " + resultado);

    // Predicates reutilizables
    static Predicate<Integer> esPar = n -> n % 2 == 0; // true si el número es par

    // Function reutilizable
    static Function<Integer, Integer> alCuadrado = n -> n * n; // número por sí mismo

    // BinaryOperator reutilizable
    static BinaryOperator<Integer> sumar = (a, b) -> a + b; // suma dos números

    public static void main(String[] args) {
        // Repositorio GitHub: https://github.com/TU_USUARIO/NOMBRE_REPO
        System.out.println("Datos: " + obtenerNumeros.get());
        System.out.println("Nombres: " + obtenerNombres.get());
        System.out.println();

        ejercicio01_sumar();
        ejercicio02_maximo();
        ejercicio03_minimo();
        ejercicio04_contar();
        ejercicio05_pares();
        ejercicio06_mayoresQue50();
        ejercicio07_contarPositivos();
        ejercicio08_rango();
        ejercicio09_cuadrado();
        ejercicio10_por10();
        ejercicio11_temperaturas();
        ejercicio12_paresAlCuadrado();
        ejercicio13_sumaPares();
        ejercicio14_promedioMayores50();
        ejercicio15_maximoPares();
        ejercicio16_menorAMayor();
        ejercicio17_mayorAMenor();
        ejercicio18_tresMasGrandes();
        ejercicio19_filtrarNombres();
        ejercicio20_nombresLargos();
        ejercicio21_mayusculas();
        ejercicio22_ordenarNombres();
        ejercicio23_buscarNumero();
        ejercicio24_todosCumplen();
        ejercicio25_algunoCumple();
    }

    // Suma todos los números
    static void ejercicio01_sumar() {
        Integer total = obtenerNumeros.get().stream().reduce(0, sumar);
        imprimir.accept("Ejercicio 1 - Suma", total);
    }

    // Busca el número más grande
    static void ejercicio02_maximo() {
        Comparator<Integer> comparador = Integer::compare; // compara dos números
        Integer max = obtenerNumeros.get().stream().max(comparador).orElse(null);
        imprimir.accept("Ejercicio 2 - Máximo", max);
    }

    // Busca el número más pequeño
    static void ejercicio03_minimo() {
        Comparator<Integer> comparador = Integer::compare; // compara dos números
        Integer min = obtenerNumeros.get().stream().min(comparador).orElse(null);
        imprimir.accept("Ejercicio 3 - Mínimo", min);
    }

    // Cuenta cuántos elementos hay
    static void ejercicio04_contar() {
        long cantidad = obtenerNumeros.get().stream().count();
        imprimir.accept("Ejercicio 4 - Cantidad de elementos", cantidad);
    }

    // Se queda solo con los pares
    static void ejercicio05_pares() {
        List<Integer> pares = obtenerNumeros.get().stream().filter(esPar).collect(Collectors.toList());
        imprimir.accept("Ejercicio 5 - Números pares", pares);
    }

    // Se queda con los mayores que 50
    static void ejercicio06_mayoresQue50() {
        Predicate<Integer> mayorQue50 = n -> n > 50; // true si pasa de 50
        List<Integer> resultado = obtenerNumeros.get().stream().filter(mayorQue50).collect(Collectors.toList());
        imprimir.accept("Ejercicio 6 - Mayores que 50", resultado);
    }

    // Cuenta los positivos
    static void ejercicio07_contarPositivos() {
        Predicate<Integer> esPositivo = n -> n > 0; // true si es mayor que cero
        long cantidad = obtenerNumeros.get().stream().filter(esPositivo).count();
        imprimir.accept("Ejercicio 7 - Cantidad de positivos", cantidad);
    }

    // Números entre 10 y 60 (incluidos)
    static void ejercicio08_rango() {
        BiPredicate<Integer, Integer> noMenorQue = (n, minimo) -> n >= minimo; // n no baja del mínimo
        BiPredicate<Integer, Integer> noMayorQue = (n, maximo) -> n <= maximo; // n no pasa del máximo
        List<Integer> resultado = obtenerNumeros.get().stream()
                .filter(n -> noMenorQue.test(n, 10) && noMayorQue.test(n, 60))
                .collect(Collectors.toList());
        imprimir.accept("Ejercicio 8 - Números entre 10 y 60", resultado);
    }

    // Eleva cada número al cuadrado
    static void ejercicio09_cuadrado() {
        List<Integer> resultado = obtenerNumeros.get().stream().map(alCuadrado).collect(Collectors.toList());
        imprimir.accept("Ejercicio 9 - Al cuadrado", resultado);
    }

    // Multiplica cada número por 10 e imprime con Consumer
    static void ejercicio10_por10() {
        Function<Integer, Integer> por10 = n -> n * 10; // multiplica por 10
        Consumer<Integer> imprimirElemento = n -> System.out.print(n + " "); // imprime un elemento
        System.out.print("Ejercicio 10 - Por 10: ");
        obtenerNumeros.get().stream().map(por10).forEach(imprimirElemento);
        System.out.println();
    }

    // Pasa de Celsius a Fahrenheit
    static void ejercicio11_temperaturas() {
        Function<Double, Double> aFahrenheit = c -> c * 9 / 5 + 32; // fórmula C a F
        List<Double> resultado = obtenerCelsius.get().stream().map(aFahrenheit).collect(Collectors.toList());
        imprimir.accept("Ejercicio 11 - Celsius " + obtenerCelsius.get() + " a Fahrenheit", resultado);
    }

    // Primero filtra pares y luego eleva al cuadrado
    static void ejercicio12_paresAlCuadrado() {
        List<Integer> resultado = obtenerNumeros.get().stream()
                .filter(esPar).map(alCuadrado).collect(Collectors.toList());
        imprimir.accept("Ejercicio 12 - Pares al cuadrado", resultado);
    }

    // Suma solo los pares
    static void ejercicio13_sumaPares() {
        Integer total = obtenerNumeros.get().stream().filter(esPar).reduce(0, sumar);
        imprimir.accept("Ejercicio 13 - Suma de pares", total);
    }

    // Promedio de los mayores que 50
    static void ejercicio14_promedioMayores50() {
        Predicate<Integer> mayorQue50 = n -> n > 50; // true si pasa de 50
        Double promedio = obtenerNumeros.get().stream()
                .filter(mayorQue50).collect(Collectors.averagingInt(Integer::intValue));
        imprimir.accept("Ejercicio 14 - Promedio de mayores que 50", promedio);
    }

    // El más grande entre los pares
    static void ejercicio15_maximoPares() {
        Comparator<Integer> comparador = Comparator.naturalOrder(); // orden normal
        Integer max = obtenerNumeros.get().stream().filter(esPar).max(comparador).orElse(null);
        imprimir.accept("Ejercicio 15 - Máximo de pares", max);
    }

    // Ordena de menor a mayor
    static void ejercicio16_menorAMayor() {
        Comparator<Integer> ascendente = Comparator.naturalOrder(); // de menor a mayor
        List<Integer> resultado = obtenerNumeros.get().stream().sorted(ascendente).collect(Collectors.toList());
        imprimir.accept("Ejercicio 16 - Menor a mayor", resultado);
    }

    // Ordena de mayor a menor
    static void ejercicio17_mayorAMenor() {
        Comparator<Integer> descendente = Comparator.reverseOrder(); // de mayor a menor
        List<Integer> resultado = obtenerNumeros.get().stream().sorted(descendente).collect(Collectors.toList());
        imprimir.accept("Ejercicio 17 - Mayor a menor", resultado);
    }

    // Toma los tres más grandes
    static void ejercicio18_tresMasGrandes() {
        Comparator<Integer> descendente = Comparator.reverseOrder(); // de mayor a menor
        List<Integer> resultado = obtenerNumeros.get().stream().sorted(descendente).limit(3).collect(Collectors.toList());
        imprimir.accept("Ejercicio 18 - Tres más grandes", resultado);
    }

    // Nombres que empiezan con M
    static void ejercicio19_filtrarNombres() {
        Predicate<String> empiezaConM = nombre -> nombre.startsWith("M"); // true si inicia con M
        List<String> resultado = obtenerNombres.get().stream().filter(empiezaConM).collect(Collectors.toList());
        imprimir.accept("Ejercicio 19 - Nombres que empiezan con M", resultado);
    }

    // Nombres con más de 5 letras
    static void ejercicio20_nombresLargos() {
        Predicate<String> masDe5 = nombre -> nombre.length() > 5; // true si tiene más de 5 letras
        List<String> resultado = obtenerNombres.get().stream().filter(masDe5).collect(Collectors.toList());
        imprimir.accept("Ejercicio 20 - Nombres con más de 5 caracteres", resultado);
    }

    // Pasa los nombres a mayúsculas
    static void ejercicio21_mayusculas() {
        Function<String, String> aMayusculas = nombre -> nombre.toUpperCase(); // convierte a mayúsculas
        List<String> resultado = obtenerNombres.get().stream().map(aMayusculas).collect(Collectors.toList());
        imprimir.accept("Ejercicio 21 - Mayúsculas", resultado);
    }

    // Ordena los nombres de la A a la Z
    static void ejercicio22_ordenarNombres() {
        Comparator<String> alfabetico = Comparator.naturalOrder(); // orden alfabético
        List<String> resultado = obtenerNombres.get().stream().sorted(alfabetico).collect(Collectors.toList());
        imprimir.accept("Ejercicio 22 - Nombres ordenados", resultado);
    }

    // Busca el número 91 en la lista
    static void ejercicio23_buscarNumero() {
        Supplier<Integer> numeroBuscado = () -> 91; // el número que queremos encontrar
        BiPredicate<Integer, Integer> esIgual = (n, buscado) -> n.equals(buscado); // compara dos números
        boolean encontrado = obtenerNumeros.get().stream()
                .filter(n -> esIgual.test(n, numeroBuscado.get()))
                .findFirst().isPresent();
        imprimir.accept("Ejercicio 23 - ¿Existe el " + numeroBuscado.get() + "?", encontrado);
    }

    // ¿Todos son menores que 100?
    static void ejercicio24_todosCumplen() {
        Predicate<Integer> menorQue100 = n -> n < 100; // true si es menor que 100
        boolean todos = obtenerNumeros.get().stream().allMatch(menorQue100);
        imprimir.accept("Ejercicio 24 - ¿Todos son menores que 100?", todos);
    }

    // ¿Alguno es negativo?
    static void ejercicio25_algunoCumple() {
        Predicate<Integer> esNegativo = n -> n < 0; // true si es menor que cero
        boolean alguno = obtenerNumeros.get().stream().anyMatch(esNegativo);
        imprimir.accept("Ejercicio 25 - ¿Alguno es negativo?", alguno);
    }
}
