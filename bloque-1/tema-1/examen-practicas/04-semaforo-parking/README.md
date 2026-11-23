# Ejercicio 4 — "Semáforo del parking": wait, notify y notifyAll

- *Tipo*: práctica examen (no evaluable)
- *RA 1*: Desenvolupa aplicacions compostes per diversos processos reconeixent i aplicant principis de programació paral·lela.

## Contexto

Varios coches esperan el verde. Necesitan una **condición**, no solo un Lock:

- `wait()`: suelta el monitor y espera.
- `notify()`: despierta a **uno**.
- `notifyAll()`: despierta a **todos**.

`sleep` no despierta a quien está en `wait`.

## Objetivo

Comparar `notify` y `notifyAll` con varios coches esperando el semáforo.

## Elementos a modelar

1. **`Semaforo`** — monitor compartido.
   - Condición tipo `boolean verde`.
   - `esperarVerde(String coche)`: mientras no haya verde, `wait()`.
   - `ponerVerdeConNotify()` / `ponerVerdeConNotifyAll()`.
2. **`Coche`** (`Runnable`) — espera el verde y, al despertar, imprime que arranca.
3. **`Operario`** (`Runnable`, recomendable) — tras un `sleep` corto, pone el semáforo en verde.
4. **`Main`** — dos demos:
   1. **3 coches** + `notify` → solo **uno** arranca de inmediato.
   2. **3 coches** + `notifyAll` → **los tres** arrancan.

## Requisitos técnicos

- `wait` / `notify` / `notifyAll` siempre dentro de `synchronized` sobre el mismo objeto.
- Espera en **`while`**, no solo `if`.
- Tras el demo de `notify`, limpieza con `notifyAll` si hace falta (y un comentario que lo explique).
- La traza debe dejar claro cuántos coches despertaron en cada demo.

## Criterios orientativos

| Criterio | Peso |
|---|---|
| `synchronized` + `while` + `wait` | 30% |
| Demo `notify`: solo un coche | 25% |
| Demo `notifyAll`: los tres | 25% |
| Traza clara y código en `ejercicio4` | 20% |

## Pista

Si el operario solo hace `sleep` y nadie hace `notify`/`notifyAll`, los coches se quedan colgados.
