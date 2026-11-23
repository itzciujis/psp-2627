# Ejercicio 2 — "Taller de coches": tres hilos, tres tareas

- *Tipo*: práctica examen (no evaluable)
- *RA 1*: Desenvolupa aplicacions compostes per diversos processos reconeixent i aplicant principis de programació paral·lela.

## Contexto

En un taller trabajan a la vez tres operarios. Cada uno es un **hilo**. El `main` abre el taller, los pone a trabajar y solo cierra cuando los tres han terminado.

## Objetivo

Programa Java con **al menos tres hilos** (además de `main`) concurrentes, con `sleep` para simular trabajo y `join` para cerrar bien.

## Elementos a modelar

1. **`Mecanico`** — implementa `Runnable` o hereda `Thread`. Varias intervenciones (p. ej. 5) con `Thread.sleep` entre ellas.
2. **`Chapa`** — varias reparaciones con `sleep`.
3. **`Pintura`** — varias capas / coches con `sleep`.
4. **`Main`** — crea los tres hilos, `start()`, `join()` y al final imprime “taller cerrado”.

Podéis usar tres clases o una parametrizada. Lo importante: **tres hilos concurrentes**.

## Requisitos técnicos

- Al menos un hilo con herencia de `Thread` **o** con `Runnable` (mejor las dos formas).
- Usad `start()`; **no** llaméis a `run()` a mano para lanzar el hilo.
- Varias iteraciones con `sleep` (duraciones distintas ayudan a ver el entrelazado).
- `main` hace `join` de los tres antes del mensaje final.
- Por consola debe verse el **nombre del hilo**.

## Qué debe verse

- Mensajes intercalados de los tres operarios.
- “taller cerrado” **después** de que acaben los tres.

## Criterios orientativos

| Criterio | Peso |
|---|---|
| Tres hilos con `start` (no `run`) | 35% |
| `join` correcto en `main` | 25% |
| `sleep` + nombres de hilo visibles | 20% |
| Código claro y organizado en el package `ejercicio2` | 20% |
