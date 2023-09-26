import java.util.Queue;
import java.util.Stack;

public class HtmlValidator {
	public static Stack<HtmlTag> isValidHtml(Queue<HtmlTag> tags) {
		// Crear una pila para rastrear las etiquetas de apertura
		Stack<HtmlTag> pila = new Stack<>();

		// Crear una pila para almacenar etiquetas incorrectas
		Stack<HtmlTag> etiquetasIncorrectas = new Stack<>();

		while (!tags.isEmpty()) {
			HtmlTag actualTag = tags.poll();

			// Si el HTML tiene un solo elemento, retornar null
			if (tags.isEmpty() && pila.isEmpty()) {
				return null;
			}

			// Si la etiqueta actual es de autocierre, continúa con la siguiente
			if (actualTag.isSelfClosing()) {
				continue;
			}

			// Si la etiqueta actual es una etiqueta de apertura y no es de autocierre
			if (actualTag.isOpenTag() && !actualTag.isSelfClosing()) {
				// Push la etiqueta actual en la pila temporal
				pila.push(actualTag);
			} else if (pila.isEmpty()) {
				// Si encontramos una única etiqueta de cierre que no es self-closing, retornar null (error)
				return null;
			} else {
				// Si la etiqueta actual es una etiqueta de cierre
				// Obtener la etiqueta de apertura correspondiente de la pila temporal
				HtmlTag etiquetaAbierta = pila.pop();

				// Si la etiqueta de cierre no coincide con la etiqueta de apertura, almacenarlas como etiquetas incorrectas
				if (!actualTag.matches(etiquetaAbierta)) {
					etiquetasIncorrectas.push(etiquetaAbierta);
					etiquetasIncorrectas.push(actualTag);
				}
			}
		}

		// Si la pila de etiquetas de apertura no está vacía, significa que hay etiquetas sin cerrar
		if (!pila.isEmpty()) {
			return pila;
		}

		// Si hay etiquetas incorrectas, devolverlas como resultado
		if (!etiquetasIncorrectas.isEmpty()) {
			return etiquetasIncorrectas;
		}

		// Si no se encontraron errores, devolver una pila vacía para indicar que el HTML es válido
		return new Stack<>();
	}

}



