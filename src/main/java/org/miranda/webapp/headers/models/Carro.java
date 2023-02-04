package org.miranda.webapp.headers.models;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;



public class Carro {
	private List<ItemCarro> items;

	public Carro() {
		items = new LinkedList<>();
	}

	public void addItem(ItemCarro item) {
		if (items.contains(item)) {
			Optional<ItemCarro> findAny = items.stream().filter(i -> i.equals(item)).findAny();
			if (findAny.isPresent()) {
				ItemCarro itemCarro = findAny.get();
				itemCarro.setCantidad(itemCarro.getCantidad() + 1);
			}
		} else {
			items.add(item);
		}
	}

	public List<ItemCarro> getItems() {
		return items;
	}

	public int getTotal() {
		// return items.stream().mapToInt(i -> i.getImporte()).sum();
		return items.stream().mapToInt(ItemCarro::getImporte).sum();
	}

	public void removeProductos(List<String> productoIds) {
		if (productoIds != null) {
			productoIds.forEach(this::removeProducto);
			// que es lo mismo a:
			// productoIds.forEach(productoId -> removeProducto(productoId));
		}
	}

	public void removeProducto(String productoId) {
		Optional<ItemCarro> producto = findProducto(productoId);
		producto.ifPresent(itemCarro -> items.remove(itemCarro));
	}

	public void updateCantidad(String productoId, int cantidad) {
		Optional<ItemCarro> producto = findProducto(productoId);
		producto.ifPresent(itemCarro -> itemCarro.setCantidad(cantidad));
	}

	private Optional<ItemCarro> findProducto(String productoId) {
		return items.stream().filter(itemCarro -> productoId.equals(Long.toString(itemCarro.getProducto().getId())))
				.findAny();
	}

}
