# Ejercicio 5 — "Escaneo de fotos": secuencial y paralelo

- *Tipo*: práctica examen (no evaluable)
- *RA 1*: Desenvolupa aplicacions compostes per diversos processos reconeixent i aplicant principis de programació paral·lela.

## Contexto

Hay que escanear **600 fotos**. Cada foto tarda **20 ms** (`Thread.sleep`) y aporta **1**. Primero todo seguido; después partido en tramos que corren a la vez.

## Objetivo

Dos programas en el package `ejercicio5`:

1. **`Secuencial`** — solo `main`. Escanea las 600 fotos una detrás de otra.
2. **`Paralelo`** — **6 hilos**. Cada uno escanea **100 fotos** en un tramo sin pisarse:
   - hilo 1: fotos 1–100
   - hilo 2: 101–200
   - hilo 3: 201–300
   - hilo 4: 301–400
   - hilo 5: 401–500
   - hilo 6: 501–600

En ambos el total debe ser **600**.

## Requisitos técnicos

- Cada foto: `sleep(20)` y resultado 1.
- En paralelo, cada hilo escribe **solo en su tramo** (array propio). Sin solapes.
- No hace falta sincronizar el cálculo: no hay contador compartido.
- `main` hace `start` + `join` y **después** junta los tramos e imprime el total.
- Secuencial ≈ **12 s** (600 × 20 ms). Paralelo ≈ **2 s** (100 × 20 ms). Si tardan igual, los hilos no van en paralelo.

## Criterios orientativos

| Criterio | Peso |
|---|---|
| Ambos acaban en **600** | 30% |
| 6 hilos y tramos disjuntos | 25% |
| Sin contador compartido | 20% |
| `main` junta tras `join` | 15% |
| El paralelo es claramente más rápido | 10% |

## Pista

Si todos hacen `total++` sobre la misma variable, volvéis al problema de la actividad 3. Aquí cada hilo rellena su array; `main` suma al final.
