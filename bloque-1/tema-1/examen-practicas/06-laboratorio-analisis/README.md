# Ejercicio 6 — "Laboratorio de análisis": dos procesos y JSON

- *Tipo*: práctica examen (no evaluable)
- *RA 1*: Desenvolupa aplicacions compostes per diversos processos reconeixent i aplicant principis de programació paral·lela.

## Contexto

Dos procesos no comparten memoria: se hablan por un archivo con **un objeto JSON** (mismo enfoque que la actividad 6 / ejemplo 12).

Aquí son **6 muestras**. El resultado de la muestra N es **N × 5**.

## Código de partida

Reutilizad `Canal` y `Mensaje` de la actividad 6 (o del ejemplo 12). No reimplementéis el formato JSON a mano.

El canal debe apuntar a un fichero en esta carpeta del ejercicio (p. ej. `canal.txt` junto al package).

## Objetivo

1. **`Main`** envía el trabajo, espera la respuesta y **guarda** el resultado. No calcula.
2. **`Analista`** (el otro proceso) recibe la orden, avisa, calcula y responde.

Hay que lanzar los dos a la vez (dos Run / dos terminales). Primero el analista, luego main.

## Mensajes

| Momento | JSON (ejemplo) |
|---|---|
| Orden | `{"tipo":"ejecuta","tarea":1}` |
| Trabajando | `{"tipo":"trabajando","tarea":1}` |
| Respuesta | `{"tipo":"completa","tarea":1,"resultado":5}` |
| Cierre | `{"tipo":"fin"}` |

Para la tarea 1 el resultado es **5** (1 × 5). Las claves son esas; no otras.

## Qué tiene que pasar

Para N de 1 a **6**:

1. `Main` envía `ejecuta` de esa tarea.
2. `Analista` lee, imprime que trabaja y envía `trabajando`.
3. Espera la pausa del canal (p. ej. 5 s), calcula **N × 5** y envía `completa`.
4. `Main` guarda el resultado. Pausa antes del siguiente.
5. Tras la sexta, `Main` envía `fin` y el analista cierra.

Resultados esperados:

| Tarea | Resultado |
|---|---|
| 1 | 5 |
| 2 | 10 |
| 3 | 15 |
| 4 | 20 |
| 5 | 25 |
| 6 | 30 |
| **Suma** | **105** |

## Requisitos técnicos

- Sin memoria compartida: solo el archivo.
- Usad `Canal.enviar` / `Canal.recibir` (no parseéis JSON a mano).
- Resultado = N × 5 (no el cuadrado del ejemplo ni el ×3 de la actividad 6).

## Criterios orientativos

| Criterio | Peso |
|---|---|
| Usan el `Canal` sin cambiar el formato | 25% |
| `trabajando` + `completa` con N × 5 | 25% |
| 6 resultados y suma **105** | 30% |
| Dos procesos y `fin` cierra al analista | 10% |
| Traza comprensible | 10% |

## Pista

Si copiáis la actividad 6 tal cual (8 encargos × 3), la suma será 108 y no vale. Aquí son 6 muestras y cada una vale el quíntuple de su número.
