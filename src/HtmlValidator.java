import java.util.Queue;
import java.util.Stack;

public class HtmlValidator {
	public static Stack<HtmlTag> isValidHtml(Queue<HtmlTag> tags) {
		// Creamos una pila para rastrear las etiquetas abiertas.
		Stack<HtmlTag> pila = new Stack<>();

		for (HtmlTag etiquetaHtml : tags) {
			// Si la etiqueta es de auto-cierre, la ignoramos y continuamos con la siguiente etiqueta.
			if (etiquetaHtml.isSelfClosing()) {
				continue;
			}
			// Si es una etiqueta de apertura, la agregamos a la pila.
			else if (etiquetaHtml.isOpenTag()) {
				pila.push(etiquetaHtml);
			}
			// Si es una etiqueta de cierre, la comparamos con la etiqueta en la cima de la pila.
			else if (!etiquetaHtml.isOpenTag()) {
				if (!pila.isEmpty()) {
					// Comparamos la etiqueta de cierre con la etiqueta en la cima de la pila.
					if (pila.peek().matches(etiquetaHtml)) {
						// Si coinciden, eliminamos la etiqueta de la cima de la pila.
						pila.pop();
					} else {
						// Si no coinciden, significa que las etiquetas no están equilibradas, por lo que retornamos la pila en su estado actual.
						return pila;
					}
				} else {
					// Si la pila está vacía y encontramos una etiqueta de cierre, significa que no hay etiqueta de apertura correspondiente, por lo que retornamos null para indicar un error.
					return null;
				}
			}
		}
		// Al final del bucle, si la pila está vacía, todas las etiquetas se han cerrado correctamente y retornamos una pila vacía para indicar que el HTML es válido.
		return pila;
	}
}
