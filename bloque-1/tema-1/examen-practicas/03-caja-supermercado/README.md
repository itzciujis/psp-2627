# Ejercicio 3 — "Caja del supermercado": memoria compartida y sincronización

- *Tipo*: práctica examen (no evaluable)
- *RA 1*: Desenvolupa aplicacions compostes per diversos processos reconeixent i aplicant principis de programació paral·lela.

## Contexto

Varias cajeras cobran artículos y suman al **mismo contador**. Sin protección hay condición de carrera. Con exclusión mutua el total es correcto.

## Objetivo

1. Sin sincronización: el total suele ser **menor** del esperado.
2. Con `synchronized` **o** `ReentrantLock`: el total es **siempre** el correcto.

## Elementos a modelar

1. **`Caja`** (o `Contador`)
   - `int articulos` (o similar).
   - `cobrar()` suma 1.
   - `getTotal()`.
2. **`Cajera`** (`Runnable`)
   - Comparte la misma `Caja`.
   - En `run()`, llama a `cobrar()` un número fijo de veces (p. ej. **2000**).
3. **`Main`**
   - **4** cajeras × 2000 cobros → esperado **8000**.
   - `start`, `join`, imprimir el total.

## Requisitos técnicos

- **Dos versiones** (paquetes, carpetas o flag documentado):
  1. Sin sincronización (demostrar el fallo).
  2. Con `synchronized` **o** `lock` / `unlock` en `finally`.
- Con sync, el total debe ser **siempre 8000**.
- No hace falta `wait`/`notify`: solo exclusión mutua.

## Criterios orientativos

| Criterio | Peso |
|---|---|
| Demostrar el fallo sin sync | 25% |
| Solución correcta (total = 8000) | 40% |
| Varios hilos + `join` + contador compartido | 20% |
| Calidad del código (`ejercicio3`) | 15% |

## Pista

`articulos++` no es atómico. La sección crítica es ese incremento.
