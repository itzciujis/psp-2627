# Programació de sistemes i procesesos

Conjunto de actividades evaluables (40% de la nota) para la asignatura PSP. También incluye los proyectos (60% de la nota)

Escuela: Digitech.

Curso: 2026-2027

## Consideraciones importantes sobre las actividades
- En este repositorio encontrarás los anunciados a cada una de las actividades de la asignatura.
- Verás que no están todos los ejercicios. Debes de ir actualizando el repositorio a medida que el profesor vaya añadiendo ejercicios.
- Para mantener actualizados los enunciados ves al [Aula de repos](https://josue.alcantaramoreno.com/aula/) y mantenlos actualizados, en local también.
- Dentro de cada ejercicio, encontrarás el enunciado en un archivo README.md como este. En ese mismo archivo verás la fecha límite de entrega.
- Entregar fuera de esa fecha supone suspender la actividad. Los cambios hechos a posteriori también los descartaré.
- El 40% de la nota de un Resultado de Evaluación sale de aquí (nota continua).

## Estructura de carpetas

Como ya sabes, cada ejercicio corresponde a un tema de la asignatura.

Cada tema, está dentro de un bloque

La estructura es la siguiente:

``` shell
/bloque-1 # Procesos e hilos
  /tema-1
    /apuntes/
      # también puedes encontrarlos en el aula virtual
    /ejemplos/
      # código de clase (referencia, no evaluable)
    /examen-practicas/
      # práctica de examen (no evaluable)
    /actividad-1/
      README.md # enunciado actividad 1
    /actividad-2/
      README.md # enunciado actividad 2
    /actividad-3/
      README.md # enunciado actividad 3
    /actividad-4/
      README.md # enunciado actividad 4
    /actividad-5/
      README.md
    /actividad-6/
      README.md
  /tema-2
    /apuntes/
      # también puedes encontrarlos en el aula virtual
  /proyecto-bloque
    README.md
```

> Es un ejemplo. Es muy probable que al final de curso hayan más actividades.

## Cómo entregar tu solución a una actividad.

| Si usas la IA, quiero que subas la conversación. Si no sabes hacerlo, pregúntale a Google.

Dentro de la carpeta de cada actividad (junto al `README.md` del enunciado) subirás tu código Java en un **package** con el nombre de la actividad (`actividad1`, `actividad2`, …). Así no chocan los `Main` entre actividades. No uses una carpeta `/solución`.

Esta es la estructura que espero:

``` shell
/bloque-1
  /tema-1
    /actividad-1/
      README.md
      /actividad1/       # package actividad1;
        Main.java
        ...
    /actividad-2/
      README.md
      /actividad2/       # package actividad2;
        Main.java
        ...
    /actividad-3/
      README.md
      /actividad3/
        ...
    /actividad-4/
      README.md
      /actividad4/
        ...
  /tema-2
    /actividad-5/
      README.md
      ...
```

## Proyectos

Como ya sabes, para cada bloque de temas, hay un proyecto.

### Consideraciones importantes

En esta asignatura tenemos 3 bloques:

1. Procesos e hilos (RA 1 + RA 2)
2. Sockets y servicios (RA 3 + RA 4)
3. Seguridad y criptografia (RA5)

Dentro de un bloque, además de las carpetas de actividades (con sus enunciados y soluciones), hay una carpeta con el enunciado proyecto del bloque.

Dentro de esa carpeta de bloque UNO del grupo tiene que entregar la solución

Esto es un ejemplo:

``` shell
/bloque-1
  /tema-1
    /actividad-1/ # README.md + package actividad1/
    /actividad-2/ # README.md + package actividad2/
    /actividad-3/ # README.md + package actividad3/
    /actividad-4/ # README.md + package actividad4/
  /tema-2
    /actividad-5/
    /actividad-6/
  /proyecto
    README.md # enunciado del proyecto
    # tu código del proyecto aquí (mismo criterio: package, sin /solución)
```
