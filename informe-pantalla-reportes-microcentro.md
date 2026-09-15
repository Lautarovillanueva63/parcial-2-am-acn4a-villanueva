# Informe — Pantalla "Reportes en Microcentro"

**Materia:** Aplicaciones Móviles
**Alumno:** Villanueva, Lautaro
**Comisión:** ACN4A
**Repositorio:** parcial-1-am-acn4a-villanueva

---

## 1. Objetivo de la pantalla

Mostrar en una lista los reportes activos de estacionamiento en la zona de Microcentro (CABA) — controles policiales y lugares libres — reportados por los propios usuarios, permitiendo agregar un nuevo reporte de forma rápida.

## 2. Descripción visual

La pantalla se divide en tres bloques:

- **Header**: fondo oscuro con el título "REPORTES - MICROCENTRO" y un ícono de perfil de usuario a la derecha.
- **Lista de reportes**: tarjetas apiladas verticalmente, cada una con un ícono de estado, el tipo de reporte (Control policial / Lugar libre) y la ubicación. El color de fondo de cada tarjeta indica el tipo: rojo para controles, verde para lugares libres.
- **Botón de acción**: botón inferior "+ REPORTAR" en color ámbar, para agregar un nuevo reporte a la lista.

## 3. Funcionalidad esperada

Al tocar el botón "+ REPORTAR", se despliega un diálogo (`AlertDialog`) donde el usuario elige el tipo de reporte (Control policial o Lugar libre). Según la elección, se abre un segundo diálogo con un menú desplegable (`Spinner`) que muestra únicamente las calles correspondientes a ese tipo de reporte (intersecciones para controles, direcciones puntuales para lugares libres). Al confirmar, se agrega dinámicamente una nueva tarjeta a la lista con el tipo, la calle elegida y el ícono correspondiente — sin recargar la pantalla ni requerir conexión a un servidor en esta primera entrega, simulando el comportamiento que tendría la app completa al recibir un reporte real de otro usuario.

## 4. Flujo de uso

1. El usuario abre la app y ve la lista de reportes activos en Microcentro.
2. Recorre la lista (scrollable) para ver los controles y lugares libres reportados.
3. Si detecta algo nuevo, toca "+ REPORTAR".
4. Elige el tipo de reporte (Control policial / Lugar libre) en el primer diálogo.
5. Selecciona la calle correspondiente en el desplegable del segundo diálogo y confirma con "Reportar".
6. Se agrega una nueva tarjeta a la lista, con ícono, tipo y calle, visible de inmediato para el usuario.

## 5. Componentes técnicos utilizados

- `ConstraintLayout` — estructura general de la pantalla
- `LinearLayout` horizontal y vertical — organización interna de cada tarjeta y de la lista completa
- `TextView` — título del reporte y ubicación
- `Button` — acción de reportar
- `ImageView` — íconos de estado en cada tarjeta (control policial / lugar libre)
- `AlertDialog` — selección del tipo de reporte y confirmación de la calle
- `Spinner` + `ArrayAdapter` — menú desplegable con las calles disponibles según el tipo elegido
- Elemento agregado dinámicamente por código Java — nueva tarjeta al confirmar el reporte
- Variables organizadas en `colors.xml`, `strings.xml` y `dimens.xml`

## 6. Paleta y tipografía

**Colores:**
- Azul noche `#1E2A3B` — header
- Ámbar `#F4A73C` — botón de acción
- Rojo `#E5484D` — tarjetas de control policial
- Verde `#2F9E44` — tarjetas de lugar libre

**Tipografía:**
- Poppins (ExtraBold / SemiBold) — título y botón
- Inter (Medium / Regular) — contenido de las tarjetas
