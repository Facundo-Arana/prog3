package ejercicioEntregable_TP2;

import java.util.ArrayList;

public class TreeWithNode {

	public static void main(String[] args) {
		int[] valoresIniciales = new int[] { 8, 4, 12, 2, 1, 3, 7, 11, 25, 9 };

		TreeWithNode miArbol = new TreeWithNode(valoresIniciales);

		miArbol.printPreOrder();
		System.out.println(miArbol.suma());
		System.out.println(miArbol.getMaxElem());
		System.out.println(miArbol.getHeight());
		System.out.println(miArbol.getLongestBranch());
		miArbol.getLongestBranch();
		System.out.println(miArbol.getElementAtLevel(2));
		System.out.println(miArbol.getFrontera());

		miArbol.add(23);
		miArbol.add(21);
		miArbol.delete(12);
		System.out.println(miArbol.getMaxElem());
		System.out.println(miArbol.getHeight());
		System.out.println(miArbol.getLongestBranch());
		System.out.println(miArbol.getElementAtLevel(2));
		System.out.println(miArbol.getFrontera());

		miArbol.add(65);
		miArbol.delete(21);
		miArbol.delete(8);
		miArbol.add(5);

		miArbol.printPreOrder();
		System.out.println(miArbol.getMaxElem());
		System.out.println(miArbol.getHeight());
		System.out.println(miArbol.getLongestBranch());
		System.out.println(miArbol.getElementAtLevel(2));
		System.out.println(miArbol.getFrontera());
	}

	private TreeNode root;

	public TreeWithNode() {
		this.root = null;
	}

	public void add(Integer value) {
		if (isEmpty()) {
			this.root = new TreeNode(value);
		} else {
			add(this.root, value);
		}
	}

	// O(n * h)
	// n = la cantidad total de nodos
	// h = el tamaño del arreglo values
	public TreeWithNode(int[] values) {
		int i = 0;
		if (isEmpty()) {
			this.root = new TreeNode(values[i++]);
		}
		while (i < values.length) {
			this.add(root, values[i++]);
		}
	}

	// O(n) siendo n la cantidad total de nodos
	// en el peor de los casos es un arbol enredadera
	private void add(TreeNode actual, Integer value) {
		if (actual.getValue() > value) {

			if (actual.hasLeft()) {
				add(actual.getLeft(), value);
			} else {
				actual.setLeft(new TreeNode(value));
			}
		}
		if (actual.getValue() < value) {

			if (actual.hasRight()) {
				add(actual.getRight(), value);
			} else {
				actual.setRight(new TreeNode(value));
			}
		}
	}

	// O(n) siendo n la cantidad de nodos
	// se deben recorrer todo los nodos para saber la rama mas grande
	public ArrayList<Integer> getLongestBranch() {
		return (isEmpty()) ? null : root.getLongestBranch();
	}

	// O(n) siendo n la cantidad de nodos
	// se deben recorrer todo los nodos para saber cuales son hojas
	public ArrayList<Integer> getFrontera() {
		return (isEmpty()) ? null : root.getFrontera();
	}

	// O(n) siendo n la cantidad de nodos
	// en el peor de los casos se busca el ultimo nivel
	public ArrayList<Integer> getElementAtLevel(Integer level) {
		return (isEmpty() || level < 0) ? null : root.getElementAtLevel(level, 0);
	}

	// O(n) siendo n la cantidad de nodos
	// siempre recorre cada uno de los nodos
	public Integer getHeight() {
		return (isEmpty()) ? null : root.getHeight(0);
	}

	// O(h) siendo n la cantidad total de nodos
	// en el peor de los casos es un arbol enredadera
	public Integer getMaxElem() {
		return (isEmpty()) ? null : this.getMaxElem(root);
	}

	private Integer getMaxElem(TreeNode cursor) {
		return (cursor.hasRight()) ? getMaxElem(cursor.getRight()) : cursor.getValue();
	}

	// O(1)
	public Integer getRoot() {
		return (isEmpty()) ? null : root.getValue();
	}

	// O(1)
	public boolean isEmpty() {
		return root == null;
	}

	// O(n)
	// se deben recorrer todo los nodos para saber cuales son hojas
	public ArrayList<Integer> suma() {
		return (isEmpty()) ? null : suma(root, 0);
	}

	private ArrayList<Integer> suma(TreeNode cursor, Integer value) {
		int sum = value + cursor.getValue();
		ArrayList<Integer> list = new ArrayList<Integer>();
		if (!cursor.hasChild()) {
			list.add(sum);
		} else {
			if (cursor.hasLeft()) {
				list.addAll(suma(cursor.getLeft(), sum));
			}
			if (cursor.hasRight()) {
				list.addAll(suma(cursor.getRight(), sum));
			}
		}
		return list;
	}

	// O(h) siendo n la cantidad total de nodos
	// en el peor de los casos el elemento buscado no esta en el arbol
	public boolean hasElem(Integer e) {
		return (isEmpty()) ? false : root.hasElem(e);
	}

	// O(h)
	public boolean delete(Integer e) {
		return (isEmpty()) ? false : this.delete(e, root);
	}

	private boolean delete(Integer e, TreeNode cursor) {
		boolean find = false;

		if (cursor.getValue() > e) {
			find = deleteLeft(cursor, e);
		} else if (cursor.getValue() < e) {
			find = deleteRight(cursor, e);
		} else {
			if (root.hasLeft()) {
				Integer replace = getMaxElem(root.getLeft());
				root.setValue(replace);
				this.deleteLeft(root, replace);
			} else {
				this.root = root.getRight();
			}
			find = true;
		}
		return find;
	}

	/**
	 * Dirigir el borrado hacia la izquierda del cursor
	 * 
	 * @param cursor nodo actual
	 * @param e      elemento a borrar
	 */
	private boolean deleteLeft(TreeNode cursor, Integer e) {
		if (!cursor.hasLeft()) {
			return false;
		}
		TreeNode left = cursor.getLeft();
		if (left.getValue() == e) {
			if (!left.hasChild()) {
				cursor.setLeft(null);
			} else {
				this.deleteNode(cursor, left);
			}
			return true;
		}
		return this.delete(e, left);
	}

	/**
	 * Dirigir el borrado hacia la derecha del cursor
	 * 
	 * @param cursor nodo actual
	 * @param e      elemento a borrar
	 */
	private boolean deleteRight(TreeNode cursor, Integer e) {
		if (!cursor.hasRight()) {
			return false;
		}
		TreeNode right = cursor.getRight();
		if (right.getValue() == e) {
			if (!right.hasChild()) {
				cursor.setRight(null);
			} else {
				this.deleteNode(cursor, right);
			}
			return true;
		}
		return this.delete(e, right);
	}

	/**
	 * 
	 * @param cursor elemento superior
	 * @param tmp    elemento a borrar o reemplazar
	 */
	private void deleteNode(TreeNode cursor, TreeNode tmp) {
		if (!tmp.hasChilds()) {
			if (tmp.hasLeft()) {
				if (cursor.getValue() < tmp.getValue()) {
					cursor.setRight(tmp.getLeft());
				} else {
					cursor.setLeft(tmp.getLeft());
				}
			} else {
				if (cursor.getValue() < tmp.getValue()) {
					cursor.setRight(tmp.getRight());
				} else {
					cursor.setLeft(tmp.getRight());
				}
			}
		} else {
			Integer replace = getMaxElem(tmp.getLeft());
			tmp.setValue(replace);
			this.deleteLeft(tmp, replace);
		}
	}

	/*************************** Metodos print() **************************/

	// O(n) siendo n la cantidad total de nodos
	// siempre recorre cada uno de los nodos
	public void printPreOrder() {
		String info = (isEmpty()) ? "empty" : root.printPreOrder();
		System.out.println(info);
	}

	// O(n) siendo n la cantidad total de nodos
	// siempre recorre cada uno de los nodos
	public void printInOrder() {
		String info = (isEmpty()) ? "empty" : root.printInOrder();
		System.out.println(info);
	}

	// O(n) siendo n la cantidad total de nodos
	// siempre recorre cada uno de los nodos
	public void printPosOrder() {
		String info = (isEmpty()) ? "empty" : root.printPosOrder();
		System.out.println(info);
	}

}
