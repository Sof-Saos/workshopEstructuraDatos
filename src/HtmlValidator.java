import java.util.Queue;
import java.util.Stack;

public class HtmlValidator {
	public static Stack<HtmlTag> isValidHtml(Queue<HtmlTag> tags) {
		Stack<HtmlTag> pila = new Stack<>();
		// Iteramos mientras haya elementos en la cola
		while (!tags.isEmpty()) {
			HtmlTag etiquetaHtml = tags.poll(); // Obtenemos el siguiente elemento de la cola

			if (etiquetaHtml.isSelfClosing()) {
				// Si es una etiqueta de auto-cierre, no hacemos nada y continuamos
				continue;
			} else if (etiquetaHtml.isOpenTag()) {
				// Si es una etiqueta de apertura, la agregamos a la pila
				pila.push(etiquetaHtml);
			} else if (!etiquetaHtml.isOpenTag()) {
				// Si es una etiqueta de cierre, comparamos con la etiqueta en la cima de la pila
				if (!pila.isEmpty()) {
					if (pila.peek().matches(etiquetaHtml)) {
						// Si coinciden, eliminamos la etiqueta de la cima de la pila
						pila.pop();
					} else {
						// Si no coinciden, retornamos la pila actual
						return pila;
					}
				} else {
					// Si la pila está vacía y encontramos una etiqueta de cierre, retornamos null
					return null;
				}
			}
		}

		// Al final del bucle, si la pila está vacía, todas las etiquetas se han cerrado correctamente
		// y retornamos una pila vacía para indicar que el HTML es válido.
		return pila;
	}
}

